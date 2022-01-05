package id.ac.unpar.informatika.prasyaratif;

import java.util.List;

import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;

public interface PrasyaratContract {
    public interface UI {
        /**
         * Menampilkan daftar mata kuliah per semester di halaman Beranda Utama
         * @param MKPerSemester Data mata kuliah per semester. MKPerSemester.get(i) mengembalikan list mata kuliah untuk semester ke-[i+1]
         */
        public void displayMataKuliah(List<List<MataKuliah>> MKPerSemester);

        /**
         * Menampilkan daftar mata kuliah yang sesuai dengan kata kunci pencarian di halaman Search
         * @param listMK Data mata kuliah yang perlu ditampilkan
         */
        public void displaySearchResult(List<MataKuliah> listMK);

        /**
         * Menampilkan daftar mata kuliah yang ditandai bintang pada halaman Favorit
         * @param listMK
         */
        public void displayFavorites(List<MataKuliah> listMK);
    }
    public interface Presenter {
        /**
         * Method ini menginisiasi pengambilan data ke API.
         * Mungkin hanya dipanggil sekali di awal
         */
        public void loadData();

        /**
         * Method ini mengambil data semua Mata Kuliah, dikelompokan per semester,
         * lalu menampilkannya ke UI
         */
        public void showMataKuliah();

        public void showMataKuliahBerbintang();

        /**
         * Mendapatkan data Mata Kuliah untuk semester tertentu, lalu menampilkannya ke UI
         * @param semester Semester yang mata kuliahnya ingin ditampilkan
         */
        public void showMataKuliah(int semester);

        /**
         * Memfilter Mata Kuliah yang nama/kodenya sesuai dengan keyword
         * @param keyword Kata kunci yang dipakai untuk memfilter data
         */
        public void showMataKuliah(String keyword);

        /**
         * Membalik status favorit dari suatu Mata Kuliah
         * @param mk Mata Kuliah yang status favoritnya ingin dibalik
         */
        public void toggleFavorite(MataKuliah mk);
    }
}
