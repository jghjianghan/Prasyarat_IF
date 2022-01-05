//package id.ac.unpar.informatika.prasyaratif.view;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//import id.ac.unpar.informatika.prasyaratif.R;
//import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;
//
//public class SemesterAdapter extends RecyclerView.Adapter<SemesterAdapter.ViewHolder>{
//    List<List<MataKuliah>> semester;
//
//    public SemesterAdapter(List<List<MataKuliah>> semester){
//        this.semester = semester;
//    }
//
//    @NonNull
//    @Override
//    public SemesterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new SemesterAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.semester_rv_item, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MataKuliahAdapter.ViewHolder holder, int position) {
//        String nama = semester.get(position).getNama();
//        boolean isExpandable = true;
//
//
//        if(isExpandable){
//            holder.ivArrow.setImageResource(android.R.drawable.btn_star_big_on);
//        } else {
//            holder.ivArrow.setImageResource(android.R.drawable.btn_star_big_off);
//        }
//
//        holder.tvNamaMataKuliah.setText(nama);
//    }
//
//    @Override
//    public int getItemCount() {
//        return semester.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder{
//
//        TextView tvSemester;
//        ImageView ivArrow;
////        RecyclerView
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            tvSemester = itemView.findViewById(R.id.tv_semester);
//            ivArrow = itemView.findViewById(R.id.iv_arrow);
//        }
//
//    }
//}
