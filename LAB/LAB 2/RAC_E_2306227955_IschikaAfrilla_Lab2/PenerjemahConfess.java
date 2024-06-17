import java.util.Scanner; // import scanner untuk membaca input dari user

public class PenerjemahConfess { // membuat class PenerjemahConfess
    public static void main(String[] args) { // fungsi main utama program
        Scanner input = new Scanner(System.in); // membuat objek scanner baru
        String binary = ""; // inisialisasi string untuk menampung binary sementara
        String message = ""; // inisialisasi string untuk menampung terjemahan message
        System.out.println("Masukkan confession dalam bentuk kode (ketik 'selesai' untuk keluar):");
        boolean start = true;
        while (start) {
            String line = input.nextLine(); // menerima input dari user
            if (line.equals("selesai")) {
                start = false; // akan berhenti meminta input ketika user mengetik "selesai"
            } // kalo ketemu abjad string kosongkan dan dikirim
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c == '\n') {
                    message += '\n';}
                else if (c == '1' || c == '0') {
                    binary += c;} // jika c == 1 atau c == 0 maka c akan disimpan ke binary
                else if (!Character.isDigit(c) && binary != "") {
                    // Karakter non digit menyebabkan permulaan kode biner baru sehingga binary yang
                    // telah disimpan akan dipass ke dalam fungsi reversedBinary dan binaryToAscii
                    // yang akan mereturn char ASCII-nya dan disimpan ke dalam variabel message
                    message += reversedBinary(binary);
                    binary = "";} // binary akan dikosongkan kembali untuk menampung char berikutnya
            }
        } System.out.println(message); // mencetak message
    }

    public static char reversedBinary(String binary) {
        // method untuk membalik binary karena akan diseleksi mulai dari kiri
        String str = ""; // inisialisasi str untuk menampung reversed binary
        for (int i = binary.length() - 1; i >= 0; i--) {
            str += binary.charAt(i); // loop dari indeks paling akhir
        } return binaryToAscii(str, 0); // invoke method binaryToAscii
    }

    public static char binaryToAscii(String reversedBinary, int n) {
        // method untuk mengekstrak binary ke ASCII secara rekursif
        int digit = reversedBinary.charAt(0) - '0'; // untuk mendapatkan nilai 1 atau 0 dari string binary
        int value = digit * (int) Math.pow(2, n); // kalikan digit dengan 2 pangkat n
        int remainingLength = reversedBinary.length() - 1; // mencari sisa panjang dr string binary
        if (remainingLength > 0) { // jika sisa panjang > 0, maka akan lanjut seleksi digit berikutnya secara rekursif
            value += binaryToAscii(reversedBinary.substring(1), n + 1);
        } return (char) value; // mengembalikan char dari value
    }
}