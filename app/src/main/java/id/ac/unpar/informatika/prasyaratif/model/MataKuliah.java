package id.ac.unpar.informatika.prasyaratif.model;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Model untuk Mata Kuliah
 */
public class MataKuliah {

    private String nama;
    private String kode;
    private int semester;
    private boolean wajib;
    private int sks;
    private List<MataKuliah> prasyaratTempuh;
    private List<MataKuliah> prasyaratLulus;
    private List<MataKuliah> prasyaratBersamaan;
    private int berlakuAngkatan;
    private boolean isFavorite;
    private int prasyaratLulusBagi;
    private int prasyaratTempuhBagi;
    private int panjangRantaiKeBawah;

    public MataKuliah(String nama,
                      String kode,
                      int semester,
                      boolean wajib,
                      int sks
    ) {
        this.nama = nama;
        this.kode = kode;
        this.semester = semester;
        this.wajib = wajib;
        this.sks = sks;

    }

    public MataKuliah(
            List<MataKuliah> prasyaratTempuh,
            List<MataKuliah> prasyaratLulus,
            List<MataKuliah> prasyaratBersamaan,
            Integer berlakuAngkatan,
            boolean isFavorite,
            int prasyaratTempuhBagi,
            int prasyaratLulusBagi
    ) {
        this.prasyaratTempuh = prasyaratTempuh;
        this.prasyaratLulus = prasyaratLulus;
        this.prasyaratBersamaan = prasyaratBersamaan;
        this.berlakuAngkatan = berlakuAngkatan;
        this.isFavorite = isFavorite;
        this.prasyaratTempuhBagi = prasyaratTempuhBagi;
        this.prasyaratLulusBagi = prasyaratLulusBagi;
    }

    /**
     * Ini konstruktor yang lengkap
     * @param nama Nama mata kuliah
     * @param kode Kode mata kuliah
     * @param semester Semester mata kuliah
     * @param wajib Apakah mata kuliah ini wajib?
     * @param sks Jumlah sks dari mata kuliah ini
     * @param prasyaratTempuh Daftar prasyarat tempuh dari mata kuliah ini
     * @param prasyaratLulus Daftar prasyarat lulus dari mata kuliah ini
     * @param prasyaratBersamaan Daftar prasyarat bersamaan dari mata kuliah ini
     * @param berlakuAngkatan Berlaku mulai angkatan?
     * @param isFavorite Apakah mata kuliah ini merupakan mata kuliah favorit?
     */
    public MataKuliah(
            String nama,
            String kode,
            int semester,
            boolean wajib,
            int sks,
            List<MataKuliah> prasyaratTempuh,
            List<MataKuliah> prasyaratLulus,
            List<MataKuliah> prasyaratBersamaan,
            int berlakuAngkatan,
            boolean isFavorite
    ) {
        this.nama = nama;
        this.kode = kode;
        this.semester = semester;
        this.wajib = wajib;
        this.sks = sks;
        this.prasyaratTempuh = prasyaratTempuh;
        this.prasyaratLulus = prasyaratLulus;
        this.prasyaratBersamaan = prasyaratBersamaan;
        this.berlakuAngkatan = berlakuAngkatan;
        this.isFavorite = isFavorite;
    }

    public String getNama() {
        return nama;
    }

    public String getKode() {
        return kode;
    }

    public int getSemester() {
        return semester;
    }

    public boolean isWajib() {
        return wajib;
    }

    public int getSks() {
        return sks;
    }

    public List<MataKuliah> getPrasyaratTempuh() {
        return prasyaratTempuh;
    }

    public List<MataKuliah> getPrasyaratLulus() {
        return prasyaratLulus;
    }

    public List<MataKuliah> getPrasyaratBersamaan() {
        return prasyaratBersamaan;
    }

    /**
     * @return Mata kuliah ini berlaku mulai angkatan berapa jika ada, jika tidak ada, mengembalikan nilai 0
     */
    public int getBerlakuAngkatan() {
        return berlakuAngkatan;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean toggleIsFavorite() {
        isFavorite = !isFavorite;
        return isFavorite;
    }

    public int getPrasyaratLulusBagi() {
        return prasyaratLulusBagi;
    }

    public void setPrasyaratLulusBagi(int prasyaratLulusBagi) {
        this.prasyaratLulusBagi = prasyaratLulusBagi;
    }

    public int getPrasyaratTempuhBagi() {
        return prasyaratTempuhBagi;
    }

    public void setPrasyaratTempuhBagi(int prasyaratTempuhBagi) {
        this.prasyaratTempuhBagi = prasyaratTempuhBagi;
    }

    public int getPanjangRantaiKeBawah() {
        return panjangRantaiKeBawah;
    }

    public void setPanjangRantaiKeBawah(int panjangRantaiKeBawah) {
        this.panjangRantaiKeBawah = panjangRantaiKeBawah;
    }

    @Override
    public String toString() {
        String[] daftarPrasyaratLulus = new String[prasyaratLulus.size()];
        for(int i = 0; i<daftarPrasyaratLulus.length; i++){
            daftarPrasyaratLulus[i] = prasyaratLulus.get(i).getKode();
        }
        String[] daftarPrasyaratTembuh = new String[prasyaratTempuh.size()];
        for(int i = 0; i<daftarPrasyaratTembuh.length; i++){
            daftarPrasyaratTembuh[i] = prasyaratTempuh.get(i).getKode();
        }
        String[] daftarPrasyaratBersamaan = new String[prasyaratBersamaan.size()];
        for(int i = 0; i<daftarPrasyaratBersamaan.length; i++){
            daftarPrasyaratBersamaan[i] = prasyaratBersamaan.get(i).getKode();
        }
        return "MataKuliah{" +
                "nama='" + nama + '\'' +
                ", kode='" + kode + '\'' +
                ", semester=" + semester +
                ", wajib=" + wajib +
                ", sks=" + sks +
                ", isFavorite=" + isFavorite +
                ", berlakuAngkatan=" + berlakuAngkatan +
                ", prasyaratLulusBagi=" + prasyaratLulusBagi +
                ", prasyaratTempuhBagi=" + prasyaratTempuhBagi +
                ", panjangRantaiKeBawah=" + panjangRantaiKeBawah +
                ", prasyarat_lulus=" + Arrays.toString(daftarPrasyaratLulus) +
                ", prasyarat_tempuh=" + Arrays.toString(daftarPrasyaratTembuh) +
                ", prasyarat_bersamaan=" + Arrays.toString(daftarPrasyaratBersamaan) +
                '}';
    }
}