package id.ac.unpar.informatika.prasyaratif.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.unpar.informatika.prasyaratif.R;
import id.ac.unpar.informatika.prasyaratif.databinding.SemesterItemBinding;

public class SemesterListAdapter extends RecyclerView.Adapter<SemesterListAdapter.ViewHolder> {
    private int selected = 0;
    private final int nSemester;

    public SemesterListAdapter(int nSemester){
        this.nSemester = nSemester;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.semester_item, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0){
            holder.binding.tvNamaItem.setText(R.string.filter_opt_all_semester);
        } else {
            holder.binding.tvNamaItem.setText("Semester " + position);
        }
        if (position == selected){
            holder.binding.ivCheck.setVisibility(View.VISIBLE);
        } else {
            holder.binding.ivCheck.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return nSemester + 1;
    }

    private void setSelected(int position){
        notifyItemChanged(selected);
        selected = position;
        notifyItemChanged(position);
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final SemesterItemBinding binding;

        public ViewHolder(View view, SemesterListAdapter adapter) {
            super(view);
            binding = SemesterItemBinding.bind(view);
            binding.getRoot().setOnClickListener((view1 -> adapter.setSelected(getBindingAdapterPosition())));
        }
    }
}
