package id.ac.unpar.informatika.prasyaratif.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.unpar.informatika.prasyaratif.R;
import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;

public class BerandaUtama extends Fragment {
    private FragmentListener listener;
    RecyclerView recyclerView;
    MataKuliahAdapter adapter;
    List<MataKuliah> mataKuliahList;


    public BerandaUtama(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_berandautama, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new MataKuliahAdapter(mataKuliahList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public static BerandaUtama newInstance(List<List<MataKuliah>> mkPerSemester){
        BerandaUtama fragment = new BerandaUtama();
        fragment.mataKuliahList = mkPerSemester.get(0);
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
