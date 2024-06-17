// import modul yang dibutuhkan
import java.util.ArrayList;
import java.util.Scanner;

public class PacilValley {
    private final static Scanner in = new Scanner(System.in); // scanner untuk menerima input
    private static ArrayList<Employee> employees = new ArrayList<>(); // array untuk menampung objek employee

    private static void printSeparator() {
        System.out.println("=".repeat(64));
    }

    public static void employeeList() {
        int totalEmployee = employees.size(); // variabel untuk menyimpan panjang dari arrayList

        if (totalEmployee == 0) {
            System.out.println("PacilValley belum memiliki karyawan :(\n");
            return;
        }

        printSeparator();
        System.out.println("PacilValley memiliki total " + totalEmployee + " karyawan:");
        // mencetak semua employee yang ada di dalam arrayList
        for (int j = 0; j < totalEmployee; j++) {
            System.out.println(employees.get(j));
        } printSeparator();

    }

    public static void hireEmployee() {
        // method untuk memperkerjakan employee
        System.out.print("Nama: ");
        String nama = in.nextLine();

        System.out.print("Pengalaman Kerja (tahun): ");
        int pengalamanKerja = Integer.parseInt(in.nextLine());

        System.out.print("Base Salary (IDR): ");
        double gajiPokok = Integer.parseInt(in.nextLine());

        String role;
        while (true) {
            System.out.print("Role Employee: ");
            role = in.nextLine();

            if (role.equalsIgnoreCase("Engineer")) {
                // Meminta input dan instansiasi employee -> engineer
                System.out.print("Project Fee (IDR): ");
                double biayaProyek = Integer.parseInt(in.nextLine());
                // membuat objek engineer baru
                Engineer engineer = new Engineer(nama, pengalamanKerja, gajiPokok, 0, biayaProyek);
                employees.add(engineer); // memasukkan objek engineer ke dalam arrayList
                ((Employee) engineer).CalculateSalary(); // menghitung pendapatan employee
                int engineerID = (employees.indexOf(engineer) + 1); // menyimpan ID employee
                engineer.setEmployeeID(engineerID);
                System.out.println("\n" + engineer.getClass().getSimpleName() + " dengan ID " + engineerID
                        + " bernama " + engineer.getName() + " berhasil dihire!\n");
                break;

            } else if (role.equalsIgnoreCase("Salesman")) {
                // Meminta input dan instansiasi employee -> salesman
                System.out.print("Commission Fee (%): ");
                double biayaKomisi = Integer.parseInt(in.nextLine());
                // membuat objek salesman baru
                Salesman salesman = new Salesman(nama, pengalamanKerja, gajiPokok, 0, biayaKomisi);
                employees.add(salesman); // memasukkan objek salesman ke dalam arrayList
                ((Employee) salesman).CalculateSalary(); // menghitung pendapatan employee
                int salesmanID = employees.indexOf(salesman) + 1; // menyimpan ID employee
                salesman.setEmployeeID(salesmanID);
                System.out.println("\n" + salesman.getClass().getSimpleName() + " dengan ID " + salesmanID
                        + " bernama " + salesman.getName() + " berhasil dihire!\n");
                break;

            } else if (role.equalsIgnoreCase("Accountant")) {
                // Meminta input dan instansiasi employee
                System.out.print("Hourly Rate (IDR): ");
                double biayaPerJam = Integer.parseInt(in.nextLine());
                // membuat objek accountant baru
                Accountant accountant = new Accountant(nama, pengalamanKerja, gajiPokok, biayaPerJam);
                employees.add(accountant); // memasukkan objek accountant ke dalam arrayList
                ((Employee) accountant).CalculateSalary(); // menghitung pendapatan employee
                int accountantID = (employees.indexOf(accountant) + 1); // menyimpan ID employee
                accountant.setEmployeeID(accountantID);
                System.out.println("\n" + accountant.getClass().getSimpleName() + " dengan ID " + accountantID
                        + " bernama " + accountant.getName() + " berhasil dihire!\n");
                break;
            } else {
                System.out.println("\nRole employee tidak valid, silahkan input kembali dengan nilai yang benar!\n");
            }
        }
        // TODO: Menambahkan employee

    }

    public static void logEmployeeSalary() {
        if (employees.isEmpty()) {
            System.out.println("PacilValley belum memiliki karyawan :(\n");
            return;
        }
        // Meminta ID dan validasi ID
        // Meminta input data dan hitung gaji berdasarkan tipe employee
        while (true) {
            System.out.print("Masukkan employee ID: ");
            int ID = Integer.parseInt(in.nextLine());
            boolean ditemukan = false;
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getEmployeeID() == ID) {
                    ditemukan = true;
                    String namaKelas = employees.get(i).getClass().getSimpleName();
                    System.out.println("Employee bernama " + employees.get(i).getName() +
                            " dengan role " + namaKelas + " berhasil dipilih!");
                    if (namaKelas.equals("Engineer")) {
                        System.out.print("Jumlah assigned project: ");
                        int assignedProject = Integer.parseInt(in.nextLine());
                        ((Engineer) employees.get(i)).setTotalProject(assignedProject);
                        // mengitung salary engineer
                        ((Engineer) employees.get(i)).CalculateSalary();
                    } else if (namaKelas.equals("Salesman")) {
                        System.out.print("Jumlah sales (IDR): ");
                        int jumlahSales = Integer.parseInt(in.nextLine());
                        ((Salesman) employees.get(i)).setTotalSales(jumlahSales);
                        // menghitung salary salesman
                        ((Salesman) employees.get(i)).CalculateSalary();
                    } else {
                        System.out.print("Jumlah jam bekerja: ");
                        int jamKerja = Integer.parseInt(in.nextLine());
                        ((Accountant) employees.get(i)).setTotalHoursWorked(jamKerja);
                        // menghitung salary accountant
                        ((Accountant) employees.get(i)).CalculateSalary();

                        // mencetak gaji employee
                    } System.out.println("Gaji " + employees.get(i).getName() + " bulan ini adalah " +
                            employees.get(i).getFinalSalary() + " IDR!\n");
                            return;
                }
            } if (!ditemukan) {
                System.out.println("\nEmployee dengan ID " + ID + " tidak ditemukan! Silahkan masukkan ID yang sesuai.\n");
            }
        }
    }

    private static void printMenu() {
        // cetak pilihan menu
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("[1] Employee List");
        System.out.println("[2] Hire Employee");
        System.out.println("[3] Log Employee Salary");
        System.out.println("[4] Exit");
        System.out.println("=".repeat(64));
    }

    public static void main(String[] args) {
        System.out.println("Selamat datang di PacilValley!");
        while (true) {
            printMenu();
            System.out.print("Input: ");
            int pilihan = Integer.parseInt(in.nextLine());
            // call method sesuai dengan input dari user
            if (pilihan == 1) {
                employeeList();
            } else if (pilihan == 2) {
                hireEmployee();
            } else if (pilihan == 3) {
                logEmployeeSalary();
            } else {
                System.out.println("Terima kasih telah menggunakan layanan PacilValley ~ !");
                break;
            }
        }
    }
}