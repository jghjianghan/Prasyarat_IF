package id.ac.unpar.informatika.prasyaratif.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import id.ac.unpar.informatika.prasyaratif.PrasyaratContract;
import id.ac.unpar.informatika.prasyaratif.R;
import id.ac.unpar.informatika.prasyaratif.databinding.ActivityMainBinding;
import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;
import id.ac.unpar.informatika.prasyaratif.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements PrasyaratContract.UI, FragmentListener {

    BerandaUtama berandaUtama;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    PrasyaratContract.Presenter presenter;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.topAppBar.setNavigationOnClickListener((View view) -> {
            binding.drawerLayout.open();
        });
        binding.navigationView.setNavigationItemSelectedListener((MenuItem menuItem) -> {
            menuItem.setChecked(true);
            binding.drawerLayout.close();
            return true;
        });;

        this.fragmentManager = getSupportFragmentManager();

        presenter = new MainPresenter(this);
        presenter.showMataKuliah();

    }

    /**
     * Menampilkan daftar mata kuliah per semester di halaman Beranda Utama
     *
     * @param MKPerSemester Data mata kuliah per semester. MKPerSemester.get(i) mengembalikan list mata kuliah untuk semester ke-[i+1]
     */
    @Override
    public void displayMataKuliah(List<List<MataKuliah>> MKPerSemester) {
        this.berandaUtama = BerandaUtama.newInstance(MKPerSemester);

        this.changePage(1);
    }

    /**
     * Menampilkan daftar mata kuliah yang sesuai dengan kata kunci pencarian di halaman Search
     *
     * @param listMK Data mata kuliah yang perlu ditampilkan
     */
    @Override
    public void displaySearchResult(List<MataKuliah> listMK) {

    }

    /**
     * Menampilkan daftar mata kuliah yang ditandai bintang pada halaman Favorit
     *
     * @param listMK
     */
    @Override
    public void displayFavorites(List<MataKuliah> listMK) {

    }

    @Override
    public void changePage(int page) {
        fragmentTransaction = fragmentManager.beginTransaction();

        if(page == 1){
            fragmentTransaction.replace(R.id.fragment_container, this.berandaUtama);
        } //else if(page == 2){
//            fragmentTransaction.replace(R.id.fragment_container, this.detilMatakuliah);
//        } else if(page == 3){
//            fragmentTransaction.replace(R.id.fragment_container, this.repostiory);
//        } else if(page == 4){
//            fragmentTransaction.replace(R.id.fragment_container, this.filter);
//        } else if(page == 5){
//            fragmentTransaction.replace(R.id.fragment_container, this.about?);
//        }

        fragmentTransaction.commit();
    }

    @Override
    public void closeApplication() {
        moveTaskToBack(true);
        finish();
    }
}