package id.ac.unpar.informatika.prasyaratif.view;

import android.view.LayoutInflater;

import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;

public interface FragmentListener {
    void showDetailFragment(MataKuliah mataKuliah);

    LayoutInflater getLayoutInflater();

    void closeApplication();

}