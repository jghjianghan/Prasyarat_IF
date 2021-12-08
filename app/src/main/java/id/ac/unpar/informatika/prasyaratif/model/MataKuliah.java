package id.ac.unpar.informatika.prasyaratif.model;

import java.util.List;

public class MataKuliah {
    /**
    * Model untuk Mata Kuliah
    */

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
    private int prasyaratWajibBagi;
    private int prasyaratLulusBagi;


    public MataKuliah(String nama,
                      String kode,
                      int semester,
                      boolean wajib,
                      int sks
                      )
    {
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
            int berlakuAngkatan,
            boolean isFavorite,
            int prasyaratWajibBagi,
            int prasyaratLulusBagi
    )
    {
        this.prasyaratTempuh = prasyaratTempuh;
        this.prasyaratLulus = prasyaratLulus;
        this.prasyaratBersamaan = prasyaratBersamaan;
        this.berlakuAngkatan = berlakuAngkatan;
        this.isFavorite = isFavorite;
        this.prasyaratWajibBagi = prasyaratWajibBagi;
        this.prasyaratLulusBagi = prasyaratLulusBagi;
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

    public List<MataKuliah> getPrasyaratTempuh(){return prasyaratTempuh;}

    public List<MataKuliah> getPrasyaratLulus(){return prasyaratLulus;}

    public List<MataKuliah> getPrasyaratBersamaan(){return prasyaratBersamaan;}

    public int getBerlakuAngkatan(){return berlakuAngkatan;}

    public boolean getIsFavorite(){return isFavorite;}

    public void setIsFavorite(boolean isFavorite){
        this.isFavorite = isFavorite;
    }

    public boolean toggleIsFavorite(){return isFavorite;}

    public int getPrasyaratLulusBagi(){return prasyaratLulusBagi;}

    public void setPrasyaratLulusBagi(int prasyaratLulusBagi){
        this.prasyaratLulusBagi = prasyaratLulusBagi;
    }

    public int getPrasyaratWajibBagi(){return prasyaratWajibBagi;}

    public void setPrasyaratWajibBagi(int prasyaratWajibBagi){
        this.prasyaratWajibBagi = prasyaratWajibBagi;
    }

}
