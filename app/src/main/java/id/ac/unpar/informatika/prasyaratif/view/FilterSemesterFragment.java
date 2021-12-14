package id.ac.unpar.informatika.prasyaratif.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import id.ac.unpar.informatika.prasyaratif.databinding.FragmentFilterSemesterBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilterSemesterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilterSemesterFragment extends Fragment {

    // the fragment initialization parameters
    private static final String ARG_JUMLAH_SEMESTER = "jumlahSemester";

    private int jumlahSemester = 8;
    private FragmentFilterSemesterBinding binding;

    public FilterSemesterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param jumlahSemester Total jumlah semester
     * @return A new instance of fragment FilterSemesterFragment.
     */
    public static FilterSemesterFragment newInstance(int jumlahSemester) {
        FilterSemesterFragment fragment = new FilterSemesterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_JUMLAH_SEMESTER, jumlahSemester);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            jumlahSemester = getArguments().getInt(ARG_JUMLAH_SEMESTER);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilterSemesterBinding.inflate(inflater, container, false);
        binding.rvSemesterList.setAdapter(new SemesterListAdapter(jumlahSemester));
        binding.rvSemesterList.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Disable dimming animation
        ((SimpleItemAnimator) Objects.requireNonNull(binding.rvSemesterList.getItemAnimator())).setSupportsChangeAnimations(false);
        return binding.getRoot();
    }
}