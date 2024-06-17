public class NilaiController {
    // inisialisasi class atribute yang diperlukan untuk class NilaiController
    private String kodeMatkul; // kode matkul
    private int nilai; // nilai dari kode matkul

    public NilaiController(String kodeMatkul){
        this.kodeMatkul = kodeMatkul;
    } // constructor method NilaiController

    public String getKodeMatkul() {
        return kodeMatkul;
    } // getter untuk melihat kodeMatkul
    public int getNilai() {
        return nilai;
    } // getter untuk melihat nilai
    public void setNilai(int nilai) {
        this.nilai = nilai;
    } // setter untuk mengubah nilai
}
