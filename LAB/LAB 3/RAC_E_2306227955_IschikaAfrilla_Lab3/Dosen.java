public class Dosen {
    // inisialisasi class atribute yg diperlukan untuk class Dosen
    private String idDosen; // id dosen
    private MataKuliah mataKuliah; // mata kuliah milik dosen

    public Dosen(String idDosen, MataKuliah mataKuliah){
        // constructor method Dosen
        this.idDosen = idDosen;
        this.mataKuliah = mataKuliah;
    }

    public String beriNilai(String npm, int nilai) {
        String output = ""; // inisialisasi String untuk menampung output
        Siswa[] daftarSiswa= this.getMataKuliah().getListSiswa(); // inisialisasi array untuk menampung siswa yang
                                                                    // mengambil mata kuliah milik dosen
        Boolean berhasil = false;
        for (int i = 0; i < daftarSiswa.length; i++){
            if (daftarSiswa[i] != null && daftarSiswa[i].getNpm().equals(npm)){
                berhasil = true;
                NilaiController[] listNilai= daftarSiswa[i].getListNilai(); // inisialisasi array untuk menampung nilai dari
                                                                            // siswa yang mengambil mata kuliah milik dosen
                for (int k = 0; k < daftarSiswa[i].getCounterMatkul(); k++) {
                    if (listNilai[k].getKodeMatkul().equals(this.getMataKuliah().getKodeMatkul())) {
                        // cek kode mata kuliah
                        listNilai[k].setNilai(nilai); // call method setNilai
                    }
                }
            }
        }
        if (berhasil){
            output += String.format("%s berhasil memberikan nilai kepada siswa dengan NPM %s", this.getIdDosen(), npm);
        }
        else {
            output += String.format("%s gagal memberikan nilai kepada siswa dengan NPM %s", this.getIdDosen(), npm);
        }
        return output;
    }

    public String getIdDosen() { // getter untuk melihat idDosen
        return idDosen;
    }

    public MataKuliah getMataKuliah() { // getter untuk melihat mataKuliah milik dosen
        return mataKuliah;
    }
}
