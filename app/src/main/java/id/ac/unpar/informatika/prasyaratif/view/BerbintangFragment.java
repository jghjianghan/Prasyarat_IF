package id.ac.unpar.informatika.prasyaratif.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.ac.unpar.informatika.prasyaratif.R;
import id.ac.unpar.informatika.prasyaratif.databinding.FragmentBerbintangBinding;
import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BerbintangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BerbintangFragment extends Fragment {

    private List<MataKuliah> favoriteMK;
    private FragmentBerbintangBinding binding;
    public FragmentListener listener;
    private FavoriteAdapter adapter;

    public BerbintangFragment() {
        // Required empty public constructor
    }

    public static BerbintangFragment newInstance(List<MataKuliah> favoriteMK) {
        BerbintangFragment fragment = new BerbintangFragment();
        fragment.favoriteMK = favoriteMK;

        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBerbintangBinding.inflate(inflater, container, false);
        adapter = new FavoriteAdapter(favoriteMK, listener);
        binding.lvFavorite.setAdapter(adapter);

        return binding.getRoot();
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