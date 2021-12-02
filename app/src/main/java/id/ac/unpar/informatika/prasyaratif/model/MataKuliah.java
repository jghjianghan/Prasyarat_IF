package id.ac.unpar.informatika.prasyaratif.model;

public class MataKuliah {
    private String nama;
    private String kode;
    private int semester;
    private boolean wajib;
    private int sks;

    public MataKuliah(String nama, String kode, int semester, boolean wajib, int sks) {
        this.nama = nama;
        this.kode = kode;
        this.semester = semester;
        this.wajib = wajib;
        this.sks = sks;
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
}
