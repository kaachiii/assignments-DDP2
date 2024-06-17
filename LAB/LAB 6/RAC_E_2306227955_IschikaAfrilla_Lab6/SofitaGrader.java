// import semua modul yang dibutuhkan
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class SofitaGrader { // class SofitaGrader
    static Scanner sc = new Scanner(System.in); // instansiasi Scanner
    static File direktoriUtama = new File("."); // direktori utama program

    public static void main(String[] args) { // main method program utama
        try {
            System.out.println("Welcome to SOFITA GRADER!");
            while (true) {
                printWelcomingMsg(); // mencetak Welcoming Message
                sc.nextLine();
                System.out.print("Input: ");
                int actionCode = sc.nextInt();
                switch (actionCode) { // switch aksi yang diinginkan
                    case 1:
                        buatQuiz();
                        break;
                    case 2:
                        jawabQuiz();
                        break;
                    case 3:
                        nilaiQuiz();
                        break;
                    case 10:
                        sc.close();
                        return;
                }
            }
        }
        finally {
            sc.close();
        }
    }

    public class InvalidQuizException extends Exception{ 
        // class InvalidQuizException
        public InvalidQuizException(String message){
            super(message);
        }
    }

    public static void printWelcomingMsg() {
        // method untuk mencetak Welcoming Message
        System.out.println("=".repeat(64));
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("[1] Buat Quiz baru");
        System.out.println("[2] Input Jawaban Quiz");
        System.out.println("[3] Nilai Jawaban Quiz");
        System.out.println("[10] Exit");
        System.out.println("=".repeat(64));
    }

    public static void buatQuiz() {
        // method untuk membuat KJ Quiz
        System.out.println("\n---BUAT QUIZ---");
        makeFile();
    }

    public static void jawabQuiz() {
        // method untuk menjawab Quiz
        System.out.println("\n---JAWAB QUIZ---");
        printFolderList(); // mencetak daftar folder
        System.out.print("Pilih nama folder untuk diakses: ");
        sc.nextLine(); // Consume newline
        String folderName = sc.nextLine();
        // instansiasi folderQuiz
        File folderQuiz = new File(direktoriUtama, folderName);
        // cek apakah folderQuiz tersedia
        if (!folderQuiz.exists() || !folderQuiz.isDirectory()) {
            System.out.println("Folder tidak ditemukan.");
            return;
        }
    
        System.out.print("\nMasukkan nama murid: ");
        String studentName = sc.nextLine();
        String answerFileName = String.format("%s.txt", studentName);
        // instansiasi answerFile
        File answerFile = new File(folderQuiz, answerFileName);
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(answerFile))) {
            System.out.println("Masukkan jawaban:");
            File kjQuiz = findFile(folderQuiz, String.format("KJ %s.txt", folderQuiz.getName()));
            // cek apakah kj Quiz tersedia
            if (kjQuiz == null) {
                System.out.println("File KJ tidak ditemukan.");
                return;
            }

            // scan kj untuk membaca
            Scanner kjScanner = new Scanner(kjQuiz);
            int questionNumber = 1;
            // loop untuk menerima jawaban user
            while (kjScanner.hasNextLine()) {
                String question = kjScanner.nextLine();
                System.out.print(questionNumber + ". ");
                String answer = sc.nextLine(); // menerima jawaban dari user
                // cek apakah jawaban ada pada ABCD
                if (!"ABCD".contains(answer.toUpperCase())) {
                    System.out.println("Input tidak valid. Masukkan A, B, C, atau D.");
                    questionNumber--;
                    continue;
                }
                // tulis jawaban user ke file txt
                writer.write(questionNumber + ". " + answer + "\n");
                questionNumber++;
            }
            kjScanner.close(); // tutup kj Scanner
            System.out.println("Berhasil buat file " + answerFileName);
        } catch (FileNotFoundException e) { // exception ketika FileNotFound Exception
            System.err.println("Belum ada folder yang dibuat!");
        } catch (IOException e) { // exception ketika IOException
            System.err.println("Belum ada folder yang dibuat!");
        }
    }
    
    public static void printFolderList() {
        // method untuk mencetak daftar folder
        // simpan list of folders
        File[] folders = direktoriUtama.listFiles(File::isDirectory);
        if (folders != null && folders.length > 0) {
            // cetak folder yang ada
            System.out.println("Berikut adalah daftar folder yang ada:");
            System.out.println("-------------------------------");
            for (File folder : folders) {
                System.out.println("> " + folder.getName());
            }
            System.out.println("-------------------------------");
        } else {
            System.out.println("Tidak ada folder yang tersedia.");
        }
    }

    public static void nilaiQuiz() {
        // method untuk menginput nilai Quiz
        System.out.println("\n---NILAI QUIZ---");
        System.out.println("Berikut adalah daftar folder yang ada:");
        File pilihFolder = aksesFolder(); // akses folder

        if (pilihFolder != null){
            // call method find file
            File rekapSebelumnya = findFile(pilihFolder, String.format("Nilai Rekap %s.txt", pilihFolder.getName()));
            // cek apakah sudah ada rekap sebelumnya
            if (rekapSebelumnya != null) {
                rekapSebelumnya.delete();
                System.out.println("-------------------------------------");
                System.out.println("| ! Nilai Rekap akan di-overwrite ! |");
                System.out.println("-------------------------------------");
            }

            // simpan list of files
            File[] files = pilihFolder.listFiles();
            if (files.length == 1) {
                System.out.println("Belum ada yang input jawaban");
                return;
            }

            // call method find file
            File kjQuiz = findFile(pilihFolder, String.format("KJ %s.txt", pilihFolder.getName()));

            // write hasil rekap
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rekapSebelumnya))) {
                for (File file : files) {
                    if (!file.equals(rekapSebelumnya) && !file.equals(kjQuiz)) {
                        int score = countMatchingLines(file, kjQuiz);
                        writer.write(file.getName() + ": " + score);
                        writer.newLine();
                    }
                }
            } catch (IOException e) { // call IOException
                e.printStackTrace();
            }
        }
    }

    public static File makeFile() {
        // method untuk membuat file
        System.out.print("Masukkan nama folder baru: ");
        sc.nextLine();
        String inputNama = sc.nextLine();
        // list of file
        File contents[] = direktoriUtama.listFiles();

        // cek apakah nama files sudah ada sebelumnya
        for (File file : contents) {
            if (file.getName().equals(inputNama)) {
                System.out.println("Nama sudah terambil!");
                return file;
            }
        }

        // buat folder baru
        File folderBaru = new File(inputNama);
        folderBaru.mkdir();
        System.out.printf("Berhasil buat folder dengan nama %s\n\n", inputNama);
        makeKJ(folderBaru);
        return folderBaru;
    }

    public static void makeKJ(File folderQuiz) {
        // method untuk membuat KJ
        System.out.println("Silahkan input KJ untuk Quiz 1");
        System.out.print("Jumlah soal: ");
        int jmlSoal = sc.nextInt();

        String fileName = "KJ " + folderQuiz.getName() + ".txt";
        // instansiasi answer file
        File answerFile = new File(folderQuiz, fileName);
        // cek apakah file sudah ada
        if (answerFile.exists() && !answerFile.isDirectory()) {
            System.out.print(fileName + " already exists. Do you want to overwrite it? (y/n): ");
            if (sc.next().toLowerCase().equals("y")) {
                try {
                    answerFile.createNewFile();
                } catch (IOException e) {
                    System.err.println("Error creating file: " + e.getMessage());
                    return;
                }
            } else {
                System.out.println("Aborting quiz answer creation.");
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(answerFile))) {
            for (int i = 1; i <= jmlSoal; i++) {
                sc.nextLine();
                System.out.print(i + ". ");
                String answer = sc.nextLine();
                // cek apakah jawaban ada di dalam ABCD
                if (!"ABCD".contains(answer.toUpperCase())){
                    System.out.println("Input tidak valid. Masukkan A, B, C, atau D.");
                    i--;
                    continue;
                }
                // tulis jawaban ke dalam file txt
                writer.write(i + ". " + answer);
                writer.newLine();
            }
            System.out.println("Berhasil buat file " + fileName + "\n");
        } catch (IOException e) {
            System.err.println("Error buat file: " + e.getMessage());
        }
    }

    public static File aksesFolder() {
        // method untuk mengakses folder
        File[] folders = direktoriUtama.listFiles(File::isDirectory);
        // cek apakah sudah ada folder yang dibuat
        if (folders == null || folders.length == 0) {
            System.out.println("-----------------------------");
            System.out.println("Belum ada folder yang dibuat!");
            System.out.println("-----------------------------");
            return null;
        }
        else {
            // cetak nama folder
            System.out.println("-----------------------------");
            for (File folder : folders) {
                System.out.println("> " + folder.getName());
            }
            System.out.println("-----------------------------");
            System.out.print("Pilih nama folder untuk diakses: ");
            String folderName = sc.next();
            File folder = new File(direktoriUtama, folderName);
            // cek apakah sudah ada input jawaban dalam folder tersebut
            if (!folder.exists() || !folder.isDirectory()) {
                System.out.println("\nBelum ada yang input jawaban");
                return null;
            }
            return folder;}
    }

    public static File findFile(File selectedFolder, String fileName) {
        // method untuk mencari file
        File[] files = selectedFolder.listFiles();
        if (files != null) { // cek apakah files null
            for (File file : files) {
                if (file.getName().equals(fileName)) {
                    // cek apakah nama file sama dengan file yang dipilih
                    return file;
                }
            }
        }
        return null;
    }

    public static int countMatchingLines(File file, File kjFile) {
        // method untuk menghitung banyak baris yang jawabannya sama
        int matchingLines = 0;
        try (Scanner fileScanner = new Scanner(file); // scan untuk membaca file
             Scanner kjScanner = new Scanner(kjFile)) { // scan untuk membaca kjfile
            while (fileScanner.hasNextLine() && kjScanner.hasNextLine()) {
                String fileLine = fileScanner.nextLine();
                String kjLine = kjScanner.nextLine();
                if (fileLine.equals(kjLine)) { // cek apakah kjfile sama dengan file
                    matchingLines++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchingLines;
    }

    /**
     * Prints the names of all files in the given folder that do not have a ".java" extension.
     *
     * @param folderName the folder to search for files
     */
    
     public static void printCurrentDirectory() {
        printFiles(direktoriUtama);
    }

    /**
     * Prints the names of all files in the given folder that have a ".java" extension.
     *
     * @param folderName the folder to search for files
     */
    public static void printFiles(File folderName) {
        File contents[] = folderName.listFiles();
        for (File file : contents) {
            if (!file.getName().endsWith(".java")){
                System.out.printf("> %s\n",file.getName());
            }
        }
    }

    /**
     * Calculates the number of questions in a given file.
     * 
     * @param file the file containing the questions
     * @return the number of questions in the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public static int hitungSoal(File file) throws IOException {
        Scanner reader = new Scanner(file);
        int soalCount = 0;
        while (reader.hasNextLine()) {
            reader.nextLine();
            soalCount++;
        }
        reader.close();
        return soalCount;
    }
}