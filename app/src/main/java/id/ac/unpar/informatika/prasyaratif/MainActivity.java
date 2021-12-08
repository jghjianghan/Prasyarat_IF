package id.ac.unpar.informatika.prasyaratif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import id.ac.unpar.informatika.prasyaratif.view.BerandaUtama;
import id.ac.unpar.informatika.prasyaratif.view.FragmentListener;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    
    BerandaUtama berandaUtama;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.berandaUtama = BerandaUtama.newInstance();

        this.fragmentManager = getSupportFragmentManager();

        this.changePage(1);
    }

    @Override
    public void changePage(int page) {
        fragmentTransaction = fragmentManager.beginTransaction();

        if(page == 1){
            fragmentTransaction.replace(R.id.fragment_container, this.berandaUtama);
        }

        fragmentTransaction.commit();
    }

    @Override
    public void closeApplication() {
        moveTaskToBack(true);
        finish();
    }

}