package id.ac.unpar.informatika.prasyaratif.presenter;

import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

import id.ac.unpar.informatika.prasyaratif.PrasyaratContract;
import id.ac.unpar.informatika.prasyaratif.UIThreadWrapper;
import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;
import id.ac.unpar.informatika.prasyaratif.service.MataKuliahService;

public class MainPresenter implements PrasyaratContract.Presenter, MataKuliahService.APIListener {
    PrasyaratContract.UI ui;
    MataKuliahService mataKuliahService = new MataKuliahService();
    UIThreadWrapper handler;

    public MainPresenter(PrasyaratContract.UI ui){
        this.ui = ui;
        handler = new UIThreadWrapper(Looper.getMainLooper(), ui);
    }

    /**
     * Method ini menginisiasi pengambilan data ke API.
     * Mungkin hanya dipanggil sekali di awal
     */
    @Override
    public void loadData() {

    }

    /**
     * Method ini mengambil data semua Mata Kuliah, dikelompokan per semester,
     * lalu menampilkannya ke UI
     * Note: saat ini mengembalikan data dummy (6 matkul dalam 2 semester)
     */
    @Override
    public void showMataKuliah(){
        mataKuliahService.fetchMataKuliah(this);
        List<List<MataKuliah>>  mks = new ArrayList<>();
        List<MataKuliah> sem1 = new ArrayList<>();
//        List<Semester>  mks = new ArrayList<>();
        sem1.add(new MataKuliah("Pemodelan untuk Komputasi", "AIF181101", 1, true, 3));
        sem1.add(new MataKuliah("Matematika Diskret", "AIF181133", 1, true, 2));
        sem1.add(new MataKuliah("Pemodelan untuk Komputasi", "AIF181101", 1, true, 3));

        List<MataKuliah> sem2 = new ArrayList<>();
        sem2.add(new MataKuliah("Pemrograman Kompetitif 1", "AIF181101", 2, false, 2));
        sem2.add(new MataKuliah("Pemodelan untuk Komputasi", "AIF181101", 2, true, 3));
        sem2.add(new MataKuliah("Pemodelan untuk Komputasi", "AIF181101", 2, true, 1));

        mks.add(sem1);
        mks.add(sem2);

        //call UI method
        ui.displayMataKuliah(mks);
    }

    /**
     * Mendapatkan data Mata Kuliah untuk semester tertentu, lalu menampilkannya ke UI
     *
     * @param semester Semester yang mata kuliahnya ingin ditampilkan
     */
    @Override
    public void showMataKuliah(int semester) {

    }

    /**
     * Memfilter Mata Kuliah yang nama/kodenya sesuai dengan keyword
     *
     * @param keyword Kata kunci yang dipakai untuk memfilter data
     */
    @Override
    public void showMataKuliah(String keyword) {

    }

    /**
     * Membalik status favorit dari suatu Mata Kuliah
     *
     * @param mk Mata Kuliah yang status favoritnya ingin dibalik
     */
    @Override
    public void toggleFavorite(MataKuliah mk) {

    }

    @Override
    public void onFetched(List<List<MataKuliah>> dataMataKuliahPerSemester) {
        handler.displayMataKuliahList(dataMataKuliahPerSemester);

    }
}
