package id.ac.unpar.informatika.prasyaratif.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.unpar.informatika.prasyaratif.R;
import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;

public class MataKuliahAdapter extends RecyclerView.Adapter<MataKuliahAdapter.ViewHolder> {
    List<MataKuliah> mataKuliahList;
    String[] dummyMatkul;

    public MataKuliahAdapter(/*List<MataKuliah> mataKuliahList*/String[] input){
//        this.mataKuliahList = mataKuliahList;
        dummyMatkul = input;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        String namaMatkul = dummyCoffee[position];
        holder.tvNamaMataKuliah.setText(dummyMatkul[position]);
    }

    @Override
    public int getItemCount() {
//        return mataKuliahList.size();
        return dummyMatkul.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvKodeMataKuliah, tvJumlahSks, tvNamaMataKuliah, wajib;
        ImageButton isFavorite;

        public ViewHolder(View itemView) {
            super(itemView);
//            this.kodeMataKuliah = itemView.findViewById(R.id.tv_kodeMatkul);
//            this.jumlahSks = itemView.findViewById(R.id.tv_sks);
//            this.namaMataKuliah = itemView.findViewById(R.id.tv_namaMatkul);
            tvNamaMataKuliah = itemView.findViewById(R.id.tv_namaMatkul);
        }

    }

}

//source :
//https://www.youtube.com/watch?v=9rcrYFO1ogc
//https://www.youtube.com/watch?v=CTvzoVtKoJ8
