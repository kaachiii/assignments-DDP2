import java.util.Scanner; // import Scanner untuk menerima input

public class KalkulatorIP { // kelas KalkulatorIP untuk menjalankan program utama
    public static void main(String[] args) { // fungsi utama yang ada di dalam kelas KalkulatorIP
        Scanner input = new Scanner(System.in); // membuat objek Scanner baru yang bernama input

        // inisialisasi semua variabel yang diperlukan
        int jumlahMatkul = 0;
        double jumlahMutu = 0;
        double mutuLulus = 0;
        double sksLulus = 0;
        double jumlahSks = 0;
        double ips = 0;
        double ipk = 0;

        // validasi input jumlah mata kuliah yang dimasukkan
        boolean validasiMatkul = true;
        while (validasiMatkul) { // selama validasiMatkul == true loop akan terus berjalan
            System.out.print("Masukkan jumlah mata kuliah: ");
            jumlahMatkul = input.nextInt(); // menyimpan input user yang berupa int ke dalam variabel jumlahMatkul
            if (jumlahMatkul >= 0) {validasiMatkul = false;} // ketika jumlahMatkul >= 0, maka loop akan berhenti
            else {System.out.println("Jumlah mata kuliah yang diambil tidak dapat negatif, silahkan isi kembali");}
        }

        // looping sesuai banyak mata kuliah yang dimasukkan
        for (int i = 0; i < jumlahMatkul; i++){
            System.out.print("Masukkan nama mata kuliah ke-" + (i + 1) + ": ");
            input.nextLine();
            String namaMatkul = input.nextLine(); // menyimpan input user yang berupa String ke dalam variabel namaMatkul

            // validasi input banyak SKS yang dimasukkan
            int sks = 0;
            boolean validasiSks = true;
            while (validasiSks) { // selama validasiSKS == true loop akan terus berjalan
                System.out.print("Masukkan jumlah sks: ");
                sks = input.nextInt(); // menyimpan input user yang berupa int ke dalam variabel sks
                if (sks > 0){validasiSks = false;} // ketika sks > 0, maka loop akan berhenti
                else {System.out.println("Jumlah SKS mata kuliah yang diambil tidak dapat negatif atau 0, silahkan isi kembali");}
            } jumlahSks += sks; // update jumlahSks total

            // validasi input nilai yang dimasukkan
            double nilai = 0;
            boolean validasiNilai = true;
            while (validasiNilai) { // selama validasiNilai == true loop akan terus berjalan
                System.out.print("Masukkan nilai: ");
                nilai = input.nextDouble(); // menyimpan input user yang berupa double ke dalam variabel nilai
                if (nilai >= 0 && nilai <= 100){validasiNilai = false;} // ketika nilai berada dalam range 0-100, maka loop akan berhenti
                else {System.out.println("Nilai mata kuliah tidak valid, silahkan isi kembali");}
            }

            // inisialisasi tipe data mutu dan index
            double mutu;
            String index;
            // branch untuk mengidentifikasi index dan mutu dari nilai yang dimasukkan
            if (nilai >= 85){ // cek apakah nilai >= 85
                index = "A";
                mutu = 4 * sks;
            }
            else if (nilai >= 80){ // cek apakah 80 <= nilai < 85
                index = "A-";
                mutu = 3.7 * sks;
            }
            else if (nilai >= 75){ // cek apakah 75 <= nilai < 80
                index = "B+";
                mutu = 3.3 * sks;
            }
            else if (nilai >= 70){ // cek apakah 70 <= nilai < 75
                index = "B";
                mutu = 3 * sks;
            }
            else if (nilai >= 65){ // cek apakah 65 <= nilai < 70
                index = "B-";
                mutu = 2.7 * sks;
            }
            else if (nilai >= 60){ // cek apakah 60 <= nilai < 65
                index = "C+";
                mutu = 2.3 * sks;
            }
            else if (nilai >= 55){ // cek apakah 55 <= nilai < 60
                index = "C";
                mutu = 2 * sks;
            }
            else if (nilai >= 40){ // cek apakah 40 <= nilai < 55
                index = "D";
                mutu = sks;
            }
            else{
                index = "E"; // cek apakah nilai < 40
                mutu = 0;
            } jumlahMutu += mutu;

            if (nilai >= 55){ // cek apakah nilai lulus
                mutuLulus += mutu;
                sksLulus += sks;
            }

            // mencetak index dan mutu mata kuliah yang diambil
            System.out.println("Nilai huruf mata kuliah " + namaMatkul +
                    " adalah " + index + " dengan mutu " + String.format("%.2f", mutu) + "\n");
        }
        // cek apakah jumlahSks > 0 dan sksLulus > 0
        if (jumlahSks > 0){ips += jumlahMutu/jumlahSks;}
        if (sksLulus > 0){ipk += mutuLulus/sksLulus;}

        System.out.println("Jumlah mutu: " + String.format("%.2f", jumlahMutu)); // mencetak jumlah mutu
        System.out.println("Jumlah sks diambil: " + String.format("%.2f", jumlahSks)); // mencetak jumlah sks
        System.out.println("IP Semester: " + String.format("%.2f", ips)); // mencetak IP semester
        System.out.println("Jumlah mutu lulus: " + String.format("%.2f", mutuLulus)); // mencetak jumlah mutu lulus
        System.out.println("Jumlah sks lulus: " + String.format("%.2f", sksLulus)); // mencetak sks lulus
        System.out.println("IP Kumulatif: " + String.format("%.2f", ipk)); // mencetak IP kumulatif
    }
}