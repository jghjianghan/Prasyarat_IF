package id.ac.unpar.informatika.prasyaratif.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.ac.unpar.informatika.prasyaratif.R;
import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;

public class MataKuliahAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<MataKuliah>> expandableListDetail;

    public MataKuliahAdapter(Context context,
                             List<String> expandableListTitle,
                             HashMap<String, List<MataKuliah>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        MataKuliah mataKuliah = (MataKuliah) getChild(listPosition, expandedListPosition);

        final String nama = mataKuliah.getNama();
        final String sks = Integer.toString(mataKuliah.getSks());
        final String kode = mataKuliah.getKode();
        final boolean wajib = mataKuliah.isWajib();
        boolean isFavorite = mataKuliah.getIsFavorite();

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_list, null);
        }
        TextView tvNama = (TextView) convertView.findViewById(R.id.tv_namaMatkul);
        TextView tvSks = (TextView) convertView.findViewById(R.id.tv_sks);
        TextView tvKode = (TextView) convertView.findViewById(R.id.tv_kodeMatkul);
        TextView tvWajib = (TextView) convertView.findViewById(R.id.tv_wajib);
        ImageView ivStar = convertView.findViewById(R.id.iv_star);

        tvNama.setText(nama);
        tvSks.setText(sks + " sks");
        tvKode.setText(kode);

        if(wajib){
            tvWajib.setVisibility(View.VISIBLE);
        } else {
            tvWajib.setVisibility(View.INVISIBLE);
        }

        if(isFavorite){
            ivStar.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            ivStar.setImageResource(android.R.drawable.btn_star_big_off);
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

//    public static class ViewHolder extends RecyclerView.ViewHolder{
//
//        TextView tvKodeMataKuliah, tvJumlahSks, tvNamaMataKuliah, tvWajib;
//        ImageView isFavorite;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            tvKodeMataKuliah = itemView.findViewById(R.id.tv_kodeMatkul);
//            tvJumlahSks = itemView.findViewById(R.id.tv_sks);
//            isFavorite = itemView.findViewById(R.id.ib_star);
//            tvWajib = itemView.findViewById(R.id.tv_wajib);
//            tvNamaMataKuliah = itemView.findViewById(R.id.tv_namaMatkul);
//        }
//
//    }

}

//source :
//https://www.youtube.com/watch?v=9rcrYFO1ogc
//https://www.youtube.com/watch?v=CTvzoVtKoJ8
