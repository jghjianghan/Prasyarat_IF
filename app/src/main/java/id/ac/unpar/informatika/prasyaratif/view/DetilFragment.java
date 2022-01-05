package id.ac.unpar.informatika.prasyaratif.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import id.ac.unpar.informatika.prasyaratif.R;
import id.ac.unpar.informatika.prasyaratif.databinding.FragmentDetilMatkulBinding;
import id.ac.unpar.informatika.prasyaratif.databinding.ItemListBinding;
import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;

public class DetilFragment extends Fragment {
    public DetilFragment() {}

    private FragmentDetilMatkulBinding binding;
    private MataKuliah mataKuliah;
    private FragmentListener listener;
    private View.OnClickListener cardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d("change page", "clicked");
            listener.showDetailFragment((MataKuliah) view.getTag());
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetilMatkulBinding.inflate(inflater, container, false);

        if (mataKuliah != null){
            populateData();
        }

        return binding.getRoot();
    }

    private void populateData(){
        binding.tvNamaMatkul.setText(mataKuliah.getNama());
        binding.tvKodeMatkul.setText(mataKuliah.getKode());
        binding.tvSks.setText(mataKuliah.getSks() + " sks");
        binding.tvWajib.setText(mataKuliah.isWajib() ? R.string.matkul_wajib:R.string.matkul_tidak_wajib);

        //insights
        if (mataKuliah.getPrasyaratLulusBagi() < MataKuliah.CRITICAL_PRASYARAT_LULUS_LIMIT){
            binding.layoutInsightLulus.setVisibility(View.GONE);
        } else {
            binding.tvInsightLulus.setText(String.format(getString(R.string.insignt_lulus), mataKuliah.getPrasyaratLulusBagi()));
        }
        if (mataKuliah.getPrasyaratTempuhBagi() < MataKuliah.CRITICAL_PRASYARAT_TEMPUH_LIMIT){
            binding.layoutInsightTempuh.setVisibility(View.GONE);
        } else {
            binding.tvInsightTempuh.setText(String.format(getString(R.string.insignt_tempuh), mataKuliah.getPrasyaratTempuhBagi()));
        }
        if (mataKuliah.getPanjangRantaiKeBawah() < MataKuliah.CRITICAL_DEPTH_LIMIT){
            binding.layoutInsightDepth.setVisibility(View.GONE);
        } else {
            binding.tvInsightDepth.setText(String.format(getString(R.string.insignt_depth), mataKuliah.getPanjangRantaiKeBawah()));
        }

        populatePrasyarat(binding.sectionSyaratLulus, mataKuliah.getPrasyaratLulus(), binding.tvNoPrasLulus);
        populatePrasyarat(binding.sectionSyaratTempuh, mataKuliah.getPrasyaratTempuh(), binding.tvNoPrasTempuh);
        populatePrasyarat(binding.sectionSyaratBersama, mataKuliah.getPrasyaratBersamaan(), binding.tvNoPrasBersama);

        binding.ivStar.setImageResource(mataKuliah.getIsFavorite() ? android.R.drawable.btn_star_big_on : android.R.drawable.btn_star_big_off);

        binding.ivStar.setOnClickListener((View view) -> {
            Log.d("favorite", "clicked");
            mataKuliah.toggleIsFavorite();
            binding.ivStar.setImageResource(mataKuliah.getIsFavorite() ? android.R.drawable.btn_star_big_on : android.R.drawable.btn_star_big_off);
        });

        if (mataKuliah.getBerlakuAngkatan() == 0){
            binding.tvBerlakuAngkatan.setText(R.string.matkul_tidak_ada_angkatan);
        } else {
            binding.tvBerlakuAngkatan.setText(Integer.toString(mataKuliah.getBerlakuAngkatan()));
        }
    }

    private void populatePrasyarat(ViewGroup parent, List<MataKuliah> daftarPrasysrat, View emptyLabel){
        if (!daftarPrasysrat.isEmpty()){
            emptyLabel.setVisibility(View.GONE);

            for(MataKuliah mkPras : daftarPrasysrat){
                ItemListBinding itemBinding = ItemListBinding.inflate(LayoutInflater.from(getContext()), parent, false);

                itemBinding.tvNamaMatkul.setText(mkPras.getNama());
                itemBinding.tvSks.setText(mkPras.getSks() + " sks");
                itemBinding.tvKodeMatkul.setText(mkPras.getKode());

                itemBinding.tvWajib.setVisibility(mkPras.isWajib() ? View.VISIBLE : View.INVISIBLE);
                itemBinding.ivInsight.setVisibility(mkPras.isCritical() ? View.VISIBLE : View.INVISIBLE);
                itemBinding.ivStar.setImageResource(mkPras.getIsFavorite() ? android.R.drawable.btn_star_big_on : android.R.drawable.btn_star_big_off);

                itemBinding.getRoot().setTag(mkPras);
                itemBinding.getRoot().setOnClickListener(cardClickListener);
                parent.addView(itemBinding.getRoot());
            }
        }
    }

    public static DetilFragment newInstance(MataKuliah mataKuliah){
        DetilFragment fragment = new DetilFragment();
        fragment.mataKuliah = mataKuliah;

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            this.listener = (FragmentListener) context;
        } else {
            throw new ClassCastException(context.toString() + " Must Implement Fragment Listener");
        }
    }
}
