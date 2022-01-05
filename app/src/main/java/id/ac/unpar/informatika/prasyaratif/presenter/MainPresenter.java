package id.ac.unpar.informatika.prasyaratif.presenter;

import android.os.Looper;

import java.lang.reflect.Array;
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
    }

    @Override
    public void showMataKuliahBerbintang() {
        List<MataKuliah> favoriteMK = new ArrayList<>();
        List<List<MataKuliah>> mkPerSemester = mataKuliahService.getMataKuliahPerSemester();
        for(List<MataKuliah> semester : mkPerSemester){
            for (MataKuliah mk : semester){
                if (mk.getIsFavorite()){
                    favoriteMK.add(mk);
                }
            }
        }
        ui.displayFavorites(favoriteMK);
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
