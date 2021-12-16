//package id.ac.unpar.informatika.prasyaratif.view;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//import java.util.List;
//
//import id.ac.unpar.informatika.prasyaratif.PrasyaratContract;
//import id.ac.unpar.informatika.prasyaratif.R;
//import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;
//
//public class MainActivity extends AppCompatActivity implements PrasyaratContract.UI {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    /**
//     * Menampilkan daftar mata kuliah per semester di halaman Beranda Utama
//     *
//     * @param MKPerSemester Data mata kuliah per semester. MKPerSemester.get(i) mengembalikan list mata kuliah untuk semester ke-[i+1]
//     */
//    @Override
//    public void displayMataKuliah(List<List<MataKuliah>> MKPerSemester) {
//
//    }
//
//    /**
//     * Menampilkan daftar mata kuliah yang sesuai dengan kata kunci pencarian di halaman Search
//     *
//     * @param listMK Data mata kuliah yang perlu ditampilkan
//     */
//    @Override
//    public void displaySearchResult(List<MataKuliah> listMK) {
//
//    }
//
//    /**
//     * Menampilkan daftar mata kuliah yang ditandai bintang pada halaman Favorit
//     *
//     * @param listMK
//     */
//    @Override
//    public void displayFavorites(List<MataKuliah> listMK) {
//
//    }
//}