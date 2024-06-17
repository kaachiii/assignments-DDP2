public class MataKuliah {
    // inisialisasi class atribute yang diperlukan untuk class MataKuliah
    private String kodeMatkul; // kode mata kuliah
    private Siswa[] listSiswa; // untuk menampung siswa yg mengambil mata kuliah
    private int kapasitas; // kapasitas mata kuliah
    private int jumlahSiswa; // jumlah siswa yang mengambil mata kuliah

    public MataKuliah(String kodeMatkul, int kapasitas){
        // constructor method MataKuliah
        this.kodeMatkul = kodeMatkul;
        this.kapasitas = kapasitas;
        this.listSiswa = new Siswa[100]; // kapasitas listSiswa 100 siswa
    }

    public String getKodeMatkul() {
        return kodeMatkul;
    } // getter untuk melihat kodeMatkul

    public Siswa[] getListSiswa() {
        return listSiswa;
    } // getter untuk melihat listSiswa

    public int getKapasitas() {
        return kapasitas;
    } // getter untuk melihat kapasitas

    public void setJumlahSiswa(int jumlahSiswa) {
        this.jumlahSiswa = jumlahSiswa;
    } // setter untuk mengubah jumlah siswa yang mengambil mata kuliah

    public int getJumlahSiswa() {
        return jumlahSiswa;
    } // getter untuk melihat jumlah siswa yang mengambil mata kuliah
}
