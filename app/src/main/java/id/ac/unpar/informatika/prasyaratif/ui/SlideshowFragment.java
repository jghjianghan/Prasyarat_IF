package id.ac.unpar.informatika.prasyaratif.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.prasyarat.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private com.example.prasyarat.ui.slideshow.SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(com.example.prasyarat.ui.slideshow.SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void cobabrowser(View view) {
        String url="https://github.com/jghjianghan/Prasyarat_IF";
        Intent bukaBrowser = new Intent(Intent.ACTION_VIEW);
        bukaBrowser.setData(Uri.parse(url));
        startActivity(bukaBrowser);
    }
}