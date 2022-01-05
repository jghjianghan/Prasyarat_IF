package id.ac.unpar.informatika.prasyaratif.view;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.Fragment;

import java.util.List;

import id.ac.unpar.informatika.prasyaratif.databinding.ItemListBinding;
import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;

public class FavoriteAdapter extends BaseAdapter {
    private FragmentListener listener;
    private List<MataKuliah> favoriteList;
    public FavoriteAdapter(List<MataKuliah> favoriteList, FragmentListener listener){
        this.listener = listener;
        this.favoriteList = favoriteList;
        Log.d("favorite adapter", " "+ favoriteList.size());
        this.notifyDataSetChanged();
    }

    public void updateData(List<MataKuliah> favoriteList){
        this.favoriteList = favoriteList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return favoriteList.size();
    }

    @Override
    public Object getItem(int i) {
        return favoriteList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemListBinding binding = ItemListBinding.inflate(listener.getLayoutInflater(), viewGroup, false);
        MataKuliah mataKuliah = favoriteList.get(i);

        binding.tvNamaMatkul.setText(mataKuliah.getNama());
        binding.tvSks.setText(mataKuliah.getSks() + " sks");
        binding.tvKodeMatkul.setText(mataKuliah.getKode());

        binding.tvWajib.setVisibility(mataKuliah.isWajib() ? View.VISIBLE : View.INVISIBLE);
        binding.ivInsight.setVisibility(mataKuliah.isCritical() ? View.VISIBLE : View.INVISIBLE);
        binding.ivStar.setImageResource(mataKuliah.getIsFavorite() ? android.R.drawable.btn_star_big_on : android.R.drawable.btn_star_big_off);

        binding.getRoot().setOnClickListener((View v) -> {
            listener.showDetailFragment(mataKuliah);
        });

        return binding.getRoot();
    }
}
