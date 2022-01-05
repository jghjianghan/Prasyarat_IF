package id.ac.unpar.informatika.prasyaratif.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.unpar.informatika.prasyaratif.R;
import id.ac.unpar.informatika.prasyaratif.databinding.FragmentDetilMatkulBinding;
import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;

public class DetilFragment extends Fragment {
    public DetilFragment() {}

    private FragmentDetilMatkulBinding binding;
    private MataKuliah mataKuliah;
    private FragmentListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetilMatkulBinding.inflate(LayoutInflater.from(getContext()), container, false);

        if (mataKuliah != null){
            populateData();
        }

        return binding.getRoot();
    }

    private void populateData(){
        binding.tvKodeMatkul.setText(mataKuliah.getKode());
        binding.tvSks.setText(Integer.toString(mataKuliah.getSks()));
        binding.tvWajib.setText(mataKuliah.isWajib() ? R.string.matkul_wajib:R.string.matkul_tidak_wajib);

        if (mataKuliah.getBerlakuAngkatan() == 0){
            binding.tvBerlakuAngkatan.setText(R.string.matkul_tidak_ada_angkatan);
        } else {
            binding.tvBerlakuAngkatan.setText(Integer.toString(mataKuliah.getBerlakuAngkatan()));
        }
    }

    public void setMataKuliah(MataKuliah mataKuliah){
        this.mataKuliah = mataKuliah;
//        populateData();
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
