package id.ac.unpar.informatika.prasyaratif.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import java.util.LinkedList;
import java.util.List;

import id.ac.unpar.informatika.prasyaratif.PrasyaratContract;
import id.ac.unpar.informatika.prasyaratif.R;
import id.ac.unpar.informatika.prasyaratif.databinding.ActivityMainBinding;
import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;
import id.ac.unpar.informatika.prasyaratif.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity
        implements PrasyaratContract.UI, FragmentListener, NavigationView.OnNavigationItemSelectedListener {

    BerandaUtama berandaUtama;
    TentangFragment tentangFragment;
    DetilFragment detilFragment;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    PrasyaratContract.Presenter presenter;
    ActivityMainBinding binding;

    private LinkedList<MataKuliah> matkulDetailStack = new LinkedList<>();

    private static final int PAGE_BERANDA = 1;
    private static final int PAGE_BERBINTANG = 2;
    private static final int PAGE_TENTANG = 3;
    private static final int PAGE_DETAIL = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.topAppBar.setNavigationOnClickListener((View view) -> {
            binding.drawerLayout.open();
        });
        binding.navigationView.setNavigationItemSelectedListener(this);

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

        this.changePage(PAGE_BERANDA);
    }

    /**
     * Menampilkan daftar mata kuliah yang sesuai dengan kata kunci pencarian di halaman Search
     *
     * @param listMK Data mata kuliah yang perlu ditampilkan
     */
    @Override
    public void displaySearchResult(List<MataKuliah> listMK) {

    }

    @Override
    public void showDetailFragment(MataKuliah mataKuliah){
        matkulDetailStack.addLast(mataKuliah);
        changePage(PAGE_DETAIL);
    }

    /**
     * Menampilkan daftar mata kuliah yang ditandai bintang pada halaman Favorit
     *
     * @param listMK
     */
    @Override
    public void displayFavorites(List<MataKuliah> listMK) {

    }

    public void changePage(int page) {
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (page){
            case PAGE_BERANDA:
                matkulDetailStack.clear();
                fragmentTransaction.replace(R.id.fragment_container, this.berandaUtama);
                break;
            case PAGE_BERBINTANG:
                matkulDetailStack.clear();
                break;
            case PAGE_TENTANG:
                matkulDetailStack.clear();
                fragmentTransaction.replace(R.id.fragment_container, this.tentangFragment);
                break;
            case PAGE_DETAIL:
                if (detilFragment == null){
                    detilFragment = new DetilFragment();
                }
                detilFragment.setMataKuliah(this.matkulDetailStack.getLast());
                fragmentTransaction.replace(R.id.fragment_container, detilFragment);
                break;
        }
//        if(page == 1){
//        } else if(page == 2){
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
    public void onBackPressed(){
        if (matkulDetailStack.isEmpty()){
            super.onBackPressed();
        } else {
            matkulDetailStack.pollLast();
            if (matkulDetailStack.isEmpty()){
                changePage(PAGE_BERANDA);
            } else {
                changePage(PAGE_DETAIL);
            }
        }
    }

    @Override
    public void closeApplication() {
        moveTaskToBack(true);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        binding.drawerLayout.close();
        switch (item.getItemId()){
            case R.id.menu_item_beranda:
                return true;
            case R.id.menu_item_berbintang:
                return true;
            case R.id.menu_item_repo:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/jghjianghan/Prasyarat_IF"));
                startActivity(browserIntent);
                break;
            case R.id.menu_item_tentang:
                if (tentangFragment == null){
                    tentangFragment = new TentangFragment();
                }
                changePage(PAGE_TENTANG);
                return true;
        }
        return false;
    }
}