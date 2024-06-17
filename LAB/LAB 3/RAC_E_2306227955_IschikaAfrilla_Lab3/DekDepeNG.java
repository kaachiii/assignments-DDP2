import java.io.*;
import java.util.StringTokenizer;

/**
 * DekDepeNG
 */
public class DekDepeNG {
    // membuat class DekDepeNG sebagai class utama

    // inisialisasi input dan output
    private static InputReader in = new InputReader(System.in);
    private static OutputStream outputStream = System.out;
    private static PrintWriter out = new PrintWriter(outputStream);

    // inisialisasi class atribute yg diperlukan
    private static Dosen[] listDosen;
    private static Siswa[] listSiswa;
    private static MataKuliah[] listMataKuliah;

    public static void main(String[] args) {
        // main method untuk menjalankan program

        int jumlahDosen = in.nextInt(); // menyimpan int jumlah dosen ke dalam variabel jumlahDosen
        listDosen = new Dosen[jumlahDosen]; // membuat array baru untuk menyimpan dosen
        listMataKuliah = new MataKuliah[jumlahDosen]; // membuat array baru untuk menyimpan mata kuliah
        for (int i = 0; i < jumlahDosen; i++){
            String idDosen = in.next(); // menyimpan String id dosen ke dalam variabel idDosen
            String kodeMatkul = in.next(); // menyimpan String kode matkul ke dalam variabel kodeMatkul
            int kapasitas = in.nextInt(); // menyimpan int kapasitas kelas ke dalam variabel kapasitas
            MataKuliah mataKuliah = new MataKuliah(kodeMatkul, kapasitas); // membuat objek mataKuliah baru
            Dosen dosen = new Dosen(idDosen, mataKuliah); // membuat objek dosen baru
            listDosen[i] = dosen; // mengisi array dengan objek dosen
            listMataKuliah[i] = mataKuliah; // mengisi array dengan objek mata kuliah
        }
        
        int jumlahSiswa = in.nextInt(); // menyimpan int jumlah siswa ke dalam variabel jumlahSiswa
        listSiswa = new Siswa[jumlahSiswa]; // membuat array baru untuk menyimpan siswa
        for (int i = 0; i < jumlahSiswa; i++){
            String npm = in.next(); // menyimpan String npm ke dalam variabel npm
            Siswa siswa = new Siswa(npm); // membuat objek siswa baru
            listSiswa[i] = siswa; // mengisi array dengan objek siswa
        }

        int jumlahPerintah = in.nextInt(); // menyimpan int jumlah perintah ke dalam variabel jumlahPerintah
        for(int i = 0; i < jumlahPerintah; i++){
            String perintah = in.next(); // menyimpan String perintah ke dalam variabel perintah
            switch (perintah) {
                case "BERINILAI": { // untuk case BERINILAI
                    String idDosen = in.next(); // menyimpan String id dosen ke dalam variabel idDosen
                    String npm = in.next(); // menyimpan String npm ke dalam variabel npm
                    int nilai = in.nextInt(); // menyimpan int nilai ke dalam variabel nilai
                    beriNilai(idDosen, npm, nilai); // call method beriNilai
                    break;
                }
                case "CEKNILAI": { // untuk case CEKNILAI
                    String npm = in.next(); // menyimpan String npm ke dalam variabel npm
                    cekNilai(npm); // call method cekNilai
                    break;
                }
                case "AMBILMATKUL": { // untuk case AMBILMATKUL
                    String npm = in.next(); // menyimpan String npm ke dalam variabel npm
                    String kodeMatkul = in.next(); // menyimpan String kode matkul ke dalam variabel kodeMatkul
                    ambilMatkul(npm, kodeMatkul); // call method ambilMatkul
                    break;
                }
            }
        }
        out.close();
    }

    public static void beriNilai(String idDosen, String npm, int nilai) {
        // method untuk case beriNilai
        if (listDosen.length > 0 && listSiswa.length > 0) {
            for (int i = 0; i < listDosen.length; i++) {
                for (int j = 0; j < listSiswa.length; j++){
                    if (listDosen[i] != null && listDosen[i].getIdDosen().equals(idDosen) &&
                        listSiswa[j] != null && listSiswa[j].getNpm().equals(npm)) {
                        // cek apakah dosen ada di dalam list dan siswa ada di dalam list
                        out.println(listDosen[i].beriNilai(npm, nilai)); // call method beriNilai
                    }
                }
            }
        }
        else {
            out.println(String.format("%s gagal memberikan nilai kepada siswa dengan NPM %s", idDosen, npm));
        }
    }

    public static void cekNilai(String npm) {
        // menthod untuk case cekNilai
        if (listSiswa.length > 0 && listMataKuliah.length > 0){
            for (int i = 0; i < listSiswa.length; i++){
                if (listSiswa[i] != null && listSiswa[i].getNpm().equals(npm)) {
                    // cek apakah siswa ada di dalam list
                    out.println(listSiswa[i].tampilkanNilai()); // call method tampilkanNilai
                    break;
                }
            }
        }
        else {
            out.println("Siswa belum mengambil mata kuliah :v\n");
        }
    }

    public static void ambilMatkul(String npm, String kodeMatkul){
        // method untuk case ambilMatkul
        if (listSiswa.length > 0 && listMataKuliah.length > 0){
            for (int i = 0; i < listSiswa.length; i++){
                for (int j = 0; j < listMataKuliah.length; j++){
                    if (listSiswa[i] != null && listSiswa[i].getNpm().equals(npm)
                        && listMataKuliah[j] != null && listMataKuliah[j].getKodeMatkul().equals(kodeMatkul)) {
                        // cek apakah siswa ada di dalam list dan mata kuliah ada di dalam list
                        out.println(listSiswa[i].ambilMatkul(listMataKuliah[j])); // call method ambilMatkul
                    }
                }
            }
        }
        else {
            out.println(String.format("Siswa dengan NPM %s gagal mengambil matkul " +
                    "dengan kode %s", npm, kodeMatkul));
        }
    }
    
    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}