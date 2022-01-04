package id.ac.unpar.informatika.prasyaratif.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<MataKuliah>> getData() {
        HashMap<String, List<MataKuliah>> expandableListDetail = new HashMap<String, List<MataKuliah>>();

        List<MataKuliah> sem1 = new ArrayList<>();
        sem1.add(new MataKuliah("Pemodelan untuk Komputasi", "AIF181101", 1, true, 3));
        sem1.add(new MataKuliah("Matematika Diskret", "AIF181133", 1, true, 2));
        sem1.add(new MataKuliah("Pemodelan untuk Komputasi", "AIF181101", 1, true, 3));

        List<MataKuliah> sem2 = new ArrayList<>();
        sem2.add(new MataKuliah("Pemrograman Kompetitif 1", "AIF181101", 2, false, 2));
        sem2.add(new MataKuliah("Pemodelan untuk Komputasi", "AIF181101", 2, true, 3));
        sem2.add(new MataKuliah("Pemodelan untuk Komputasi", "AIF181101", 2, true, 1));

//        List<String> sem3 = new ArrayList<>();
//        sem3.add("Teknik Presentasi");
//        sem3.add("Algoritma dan Struktur Data");
//        sem3.add("Struktur Diskret");
//        sem3.add("Pemrograman Berorientasi Objek");
//        sem3.add("Statistika untuk Komputasi");
//        sem3.add("Estetika");
//        sem3.add("Pendidikan Agama Katolik");

        expandableListDetail.put("Semester 1", sem1);
        expandableListDetail.put("Semester 2", sem2);
//        expandableListDetail.put("Semester 3", sem3);

        return expandableListDetail;
    }
}
