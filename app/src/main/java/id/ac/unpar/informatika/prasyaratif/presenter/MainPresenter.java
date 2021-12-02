package id.ac.unpar.informatika.prasyaratif.presenter;

import java.util.ArrayList;
import java.util.List;

import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;

public class MainPresenter {
    /**
     * Method ini mengembalikan semua Mata Kuliah, dikelompokan per semester
     * Note: saat ini mengembalikan data dummy (6 matkul dalam 2 semester)
     * @return List Mata Kuliah per semseter
     */
    public List<List<MataKuliah>> getMataKuliah(){
        List<List<MataKuliah>>  mks = new ArrayList<>();
        List<MataKuliah> sem1 = new ArrayList<>();
        sem1.add(new MataKuliah("Pemodelan untuk Komputasi", "AIF181101", 1, true, 3));
        sem1.add(new MataKuliah("Pemodelan untuk Komputasi", "AIF181101", 1, true, 3));
        sem1.add(new MataKuliah("Pemodelan untuk Komputasi", "AIF181101", 1, true, 3));

        List<MataKuliah> sem2 = new ArrayList<>();
        sem2.add(new MataKuliah("Pemrograman Kompetitif 1", "AIF181101", 2, false, 2));
        sem2.add(new MataKuliah("Pemodelan untuk Komputasi", "AIF181101", 2, true, 3));
        sem2.add(new MataKuliah("Pemodelan untuk Komputasi", "AIF181101", 2, true, 1));

        mks.add(sem1);
        mks.add(sem2);

        return mks;
    }
}
