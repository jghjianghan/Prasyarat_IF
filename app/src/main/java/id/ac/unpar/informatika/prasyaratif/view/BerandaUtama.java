package id.ac.unpar.informatika.prasyaratif.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import id.ac.unpar.informatika.prasyaratif.R;
import id.ac.unpar.informatika.prasyaratif.model.ExpandableListDataPump;
import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;

public class BerandaUtama
        extends Fragment
        implements ExpandableListView.OnGroupExpandListener,
        ExpandableListView.OnGroupCollapseListener,
        ExpandableListView.OnChildClickListener,
        View.OnClickListener{

    private FragmentListener listener;
    RecyclerView recyclerView;

    List<List<MataKuliah>> mataKuliahList;

    ExpandableListView expandableListView;
    MataKuliahAdapter adapterMatkul;
    List<String> expandableListTitle;
    HashMap<String, List<MataKuliah>> expandableListDetail;


    public BerandaUtama(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.beranda_utama, container, false);
//        recyclerView = view.findViewById(R.id.main_rv);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        expandableListView = view.findViewById(R.id.expandableListView);
        expandableListDetail = new HashMap<>();
        expandableListTitle = new ArrayList<String>();

        for(int i = 0; i < mataKuliahList.size(); i++){
            String sem = "Semester " + (i+1);
            expandableListTitle.add(sem);
            expandableListDetail.put(sem, mataKuliahList.get(i));
        }

        adapterMatkul = new MataKuliahAdapter(getContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(adapterMatkul);

        return view;
    }

    public static BerandaUtama newInstance(List<List<MataKuliah>> mkPerSemester){
        BerandaUtama fragment = new BerandaUtama();
        fragment.mataKuliahList = mkPerSemester;
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

    @Override
    public void onGroupExpand(int groupPosition) {
        Toast.makeText(getActivity(),
                expandableListTitle.get(groupPosition) + " List Expanded.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGroupCollapse(int groupPosition) {
        Toast.makeText(getActivity(),
                expandableListTitle.get(groupPosition) + " List Collapsed.",
                Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(
                getActivity(),
                expandableListTitle.get(groupPosition)
                        + " -> "
                        + expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition), Toast.LENGTH_SHORT
        ).show();
        return false;
    }

    @Override
    public void onClick(View view) {

    }
}
