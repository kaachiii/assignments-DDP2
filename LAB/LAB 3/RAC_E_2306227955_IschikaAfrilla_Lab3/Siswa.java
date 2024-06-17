public class Siswa {
    // inisialisasi class atribute yang diperlukan untuk class Siswa
    private String npm; // npm siswa
    private NilaiController[] listNilai = new NilaiController[100]; // array untuk menampung nilai milik siswa
    private int counterMatkul = 0; // counter untuk menghitung berapa mata kuliah yang diambil oleh siswa

    public Siswa(String npm){
        this.npm = npm;
    } // constructor method Siswa

    public String ambilMatkul(MataKuliah mataKuliah) {
        String output = ""; // inisialisasi String untuk menampung output
        if (mataKuliah.getJumlahSiswa() <= mataKuliah.getKapasitas()) {
            // cek apakah kapasitas mata kuliah masih ada
            mataKuliah.getListSiswa()[mataKuliah.getJumlahSiswa()] = this; // simpan Siswa ke dalam list siswa
            mataKuliah.setJumlahSiswa(mataKuliah.getJumlahSiswa() + 1); // set jumlah siswa + 1
            listNilai[this.getCounterMatkul()] = new NilaiController(mataKuliah.getKodeMatkul()); // simpan nilai ke dalam list nilai
            this.counterMatkul++;
            output += String.format("Siswa dengan NPM %s berhasil mengambil matkul dengan kode %s", this.getNpm(), mataKuliah.getKodeMatkul());
        }
        else {
            output += String.format("Siswa dengan NPM %s gagal mengambil matkul dengan kode %s\n", this.getNpm(), mataKuliah.getKodeMatkul());
        }
        return output;
    }

    public String tampilkanNilai() {
        String output = ""; // inisialisasi String untuk menampung output
        if (this.getCounterMatkul() <= 0) { // cek apakah ada mata kuliah yg diambil
            output += "Siswa belum mengambil mata kuliah :v";
        }
        else {
            for (int i = 0; i < this.getCounterMatkul(); i++){
                output += String.format("Kode matkul %s memiliki nilai %d",
                        listNilai[i].getKodeMatkul(), listNilai[i].getNilai());
            }
        }
        return output;
    }

    public NilaiController[] getListNilai() {
        return listNilai;
    } // getter untuk melihat list nilai
    public int getCounterMatkul(){
        return this.counterMatkul;
    } // getter untuk melihat banyak matkul yg telah diambil
    public String getNpm() {
        return npm;
    } // getter untuk melihat npm Siswa
}
