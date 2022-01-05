package id.ac.unpar.informatika.prasyaratif.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
    BerbintangFragment berbintangFragment;

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
        berbintangFragment = BerbintangFragment.newInstance(listMK);

        changePage(PAGE_BERBINTANG);
    }

    public void changePage(int page) {
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (page){
            case PAGE_BERANDA:
                popAllDetailFromStacks();
                fragmentTransaction.replace(R.id.fragment_container, this.berandaUtama);
                break;
            case PAGE_BERBINTANG:
                popAllDetailFromStacks();
                fragmentTransaction.replace(R.id.fragment_container, this.berbintangFragment);
                break;
            case PAGE_TENTANG:
                popAllDetailFromStacks();
                fragmentTransaction.replace(R.id.fragment_container, this.tentangFragment);
                break;
            case PAGE_DETAIL:
                fragmentTransaction
                        .setCustomAnimations(R.anim.slide_left_in, R.anim.nothing, R.anim.nothing, R.anim.slide_right_out)
                        .replace(R.id.fragment_container, DetilFragment.newInstance(this.matkulDetailStack.getLast()))
                        .addToBackStack(null);
                break;
        }

        fragmentTransaction.commit();
    }

    private void popAllDetailFromStacks(){
        while(!matkulDetailStack.isEmpty()){
            matkulDetailStack.pollLast();
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onBackPressed(){
        if (binding.drawerLayout.isOpen()){
            binding.drawerLayout.close();
        } else {
            super.onBackPressed();
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
                changePage(PAGE_BERANDA);
                return true;
            case R.id.menu_item_berbintang:
                presenter.showMataKuliahBerbintang();
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