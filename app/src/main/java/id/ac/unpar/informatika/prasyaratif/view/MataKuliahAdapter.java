package id.ac.unpar.informatika.prasyaratif.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.unpar.informatika.prasyaratif.R;
import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;

public class MataKuliahAdapter extends RecyclerView.Adapter<MataKuliahAdapter.ViewHolder> {
    List<MataKuliah> mataKuliahList;

    public MataKuliahAdapter(List<MataKuliah> mataKuliahList){
        this.mataKuliahList = mataKuliahList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.semester_rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String kode = mataKuliahList.get(position).getKode();
        String sks = Integer.toString(mataKuliahList.get(position).getSks());
        String nama = mataKuliahList.get(position).getNama();
        String isWajib = "Wajib";
        boolean isExpandable;

        if(mataKuliahList.get(position).isWajib()){
            holder.tvWajib.setText(isWajib);
        }else{
            holder.tvWajib.setText("");
        }

        if(mataKuliahList.get(position).getIsFavorite()){
            holder.isFavorite.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            holder.isFavorite.setImageResource(android.R.drawable.btn_star_big_off);
        }

        holder.tvKodeMataKuliah.setText(kode);
        holder.tvJumlahSks.setText(sks);
        holder.tvNamaMataKuliah.setText(nama);
    }

    @Override
    public int getItemCount() {
        return mataKuliahList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvKodeMataKuliah, tvJumlahSks, tvNamaMataKuliah, tvWajib;
        ImageView isFavorite;

        public ViewHolder(View itemView) {
            super(itemView);
            tvKodeMataKuliah = itemView.findViewById(R.id.tv_kodeMatkul);
            tvJumlahSks = itemView.findViewById(R.id.tv_sks);
            isFavorite = itemView.findViewById(R.id.ib_star);
            tvWajib = itemView.findViewById(R.id.tv_wajib);
            tvNamaMataKuliah = itemView.findViewById(R.id.tv_namaMatkul);
        }

    }

}

//source :
//https://www.youtube.com/watch?v=9rcrYFO1ogc
//https://www.youtube.com/watch?v=CTvzoVtKoJ8
