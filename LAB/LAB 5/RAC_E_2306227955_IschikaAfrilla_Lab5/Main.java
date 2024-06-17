import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // atribut class Main
    static ArrayList<Employee> employeeList = new ArrayList<>(); // array untuk menampung daftar Employee
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Opening PacilRekrutmen
        System.out.println("Selamat Datang di PacilRekrutment\n");
        while (true) {

            // menampilkan menu pembuka
            printWelcomingMsg();
            System.out.print("Input: ");
            int actionCode = sc.nextInt();

            // switch aksi yang diinginkan oleh user
            switch (actionCode) {
                case 1:
                    printEmployeeList();
                    break;
                case 2:
                    hireEmployee();
                    break;
                case 3:
                    askForRaise();
                    break;
                case 4:
                    extendContract();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan layanan PacilRekrutment ~ !");
                    sc.close();
                    return;
                default:
                    unknownActionMsg();
                    break;
            }
        }
    }

    /*
     * Method ini digunakan untuk mencetak Employee yang ada pada EmployeeList
     * @return void
     */
    public static void printEmployeeList() {
        if (employeeList.isEmpty()){
            System.out.println("Tidak Ada Employee yang Terdaftar!!!\n");
        }
        else {
            displayPermanentEmployee(); // menampilkan daftar PermanentEmployee
            displayContractEmployee(); // menampilkan daftar Contract Employee
            displayInternEmployee(); // menampilkan daftar Intern Employee
        }
    }

    /*
     * Method ini digunakan untuk memperkerjakan Employee
     * @return void
     */
    public static void hireEmployee() {
        System.out.print("Nama: ");
        sc.nextLine();
        String nama = sc.nextLine();

        // cek apakah nama sudah terdaftar
        if (getEmployeeByNameOrId(nama) != null){
            System.out.println("Nama sudah terdaftar!!!\n");
        }
        else {
            System.out.print("Base Salary: ");
            double salary = sc.nextDouble();
            System.out.print("Status Employee (Permanent/Contract/Intern): ");
            sc.nextLine();
            String status = sc.nextLine();

            // case status == Permanent
            if (status.equalsIgnoreCase("Permanent")) {
                // instansiasi PermanentEmployee
                PermanentEmployee permanentEmployee = new PermanentEmployee(nama, salary);
                // calculate salary PermanentEmployee
                permanentEmployee.salary = permanentEmployee.calculateSalary();
                // add PermanentEmployee to employeeList
                employeeList.add(permanentEmployee);
            }
            // case status == Contract
            else if (status.equalsIgnoreCase("Contract")) {
                System.out.print("Lama Kontrak (Bulan): ");
                int contractDuration = sc.nextInt();
                // instansiasi ContractEmployee
                ContractEmployee contractEmployee = new ContractEmployee(nama, salary, contractDuration);
                // calculate salary ContractEmployee
                contractEmployee.salary = contractEmployee.calculateSalary();
                // add ContractEmployee to employeeList
                employeeList.add(contractEmployee);
            }
            // case status == Intern
            else if (status.equalsIgnoreCase("Intern")) {
                System.out.print("Lama Kontrak (Bulan): ");
                int contractDuration = sc.nextInt();
                // instansiasi InternEmployee
                InternEmployee internEmployee = new InternEmployee(nama, salary, contractDuration);
                // calculate salary InternEmployee
                internEmployee.salary = internEmployee.calculateSalary();
                // add InternEmployee to employeeList
                employeeList.add(internEmployee);
            }
        }
    }

    /*
     * Method ini digunakan untuk mengajukan kenaikan gaji
     * @return void
     */
    public static void askForRaise() {
        // cek apakah PermanentEmployee dan ContractEmployee kosong
        if (getPermanentEmployee().isEmpty() && getContractEmployee().isEmpty()){
            System.out.println("Tidak Ada Permanent atau Contract Employee yang Terdaftar!!!\n");
        }
        else {
            displayPermanentEmployee(); // menampilkan daftar PermanentEmployee
            displayContractEmployee(); // menampilkan daftar ContractEmployee
            System.out.print("Masukkan Nama/ID Employee: ");
            sc.nextLine();
            String nameOrId = sc.nextLine();

            // cek apakah Nama/ID terdaftar
            if (getEmployeeByNameOrId(nameOrId) == null) {
                System.out.println("Employee dengan Nama/ID " + nameOrId + " Tidak Ditemukan!!!\n");
            }

            // cek apakah Employee berstatus Intern
            else if (getEmployeeByNameOrId(nameOrId) instanceof InternEmployee){
                System.out.println("Intern Employee Tidak Bisa Mendapatkan Raise!!!\n");
            }
            else {
                System.out.print("Masukan Jumlah Kenaikan: ");
                double raise = sc.nextDouble();
                if (raise < 0) { // cek apakah kenaikan gaji negatif
                    System.out.println("Kenaikan Gaji Tidak Boleh Negatif!!!\n");
                } else {
                    Employee employee = getEmployeeByNameOrId(nameOrId);
                    if (employee == null) { // cek apakah Employee terdaftar sebagai Permanent/Contract Employee
                        System.out.println("Tidak Ada Permanent atau Contract Employee yang Terdaftar!!!\n");
                    } else {
                        if (employee instanceof PermanentEmployee) { // cek apakah employee berstatus PermanentEmployee
                            ((PermanentEmployee) employee).askRaise(raise); // askRaise employee
                        } else {
                            ((ContractEmployee) employee).askRaise(raise);
                        }
                        employee.salary = employee.calculateSalary(); // calculate salary employee
                        System.out.printf("Employee dengan Nama/ID %s Berhasil Dinaikkan Gajinya Sebesar %.0f\n%n", nameOrId, raise);
                    }
                }
            }
        }
    }

    /*
     * Method ini digunakan untuk memperpenjang kontrak
     * @return void
     */
    public static void extendContract() {
        // cek apakah ContractEmployee dan InternEmployee kosong
        if (getContractEmployee().isEmpty() && getInternEmployee().isEmpty()){
            System.out.println("Tidak Ada Contract atau Intern Employee yang Terdaftar!!!\n");
        }
        else{
            displayContractEmployee(); // menampilkan daftar ContractEmployee
            displayInternEmployee(); // menampilkan daftar InternEmployee
            System.out.print("Masukkan Nama/ID Employee: ");
            sc.nextLine();
            String nameOrId = sc.nextLine();

            // cek apakah Nama/ID terdaftar
            if (getEmployeeByNameOrId(nameOrId) == null) {
                System.out.println("Employee dengan Nama/ID " + nameOrId + " Tidak Ditemukan!!!");
            }

            // cek apakah Employee berstatus Permanent
            else if (getEmployeeByNameOrId(nameOrId) instanceof PermanentEmployee){
                System.out.println("PermanentEmployee Tidak Bisa Extend Kontrak!!!\n");
            }

            else {
                System.out.print("Masukkan Lama Extend Kontrak (Bulan): ");
                int duration = sc.nextInt();
                Employee employee = getEmployeeByNameOrId(nameOrId);
                // cek apakah employee terdaftar
                if (employee == null) {
                    System.out.println("Tidak Ada Contract atau Intern Employee yang Terdaftar!!!\n");
                } else {
                    if (employee instanceof ContractEmployee) { // cek apakah employee berstatus ContractEmployee
                        ((ContractEmployee) employee).extendContract(duration); // perpanjang kontrak employee
                    } else {
                        ((InternEmployee) employee).extendContract(duration); // perpanjang kontrak employee
                    }
                    employee.salary = employee.calculateSalary(); // calculate salary employee
                    System.out.println("Employee dengan Nama/ID " + nameOrId + " Berhasil Diperpanjang Kontraknya Selama " + duration + " Bulan\n");
                }
            }
        }
    }

    /*
     * Method ini digunakan untuk membantu menemukan Employee dengan menggunakan Nama/ID
     * @return Employee
     */
    public static Employee getEmployeeByNameOrId(String nameOrId) {
        for (Employee employee : employeeList) {
            // loop employeeList dan cek apakah nama dan ID mereka sama
            if (employee.name.equals(nameOrId) || Integer.toString(employee.employeeId).equals(nameOrId)) {
                return employee;
            }
        } return null;
    }

    /*
     * Method ini digunakan untuk menampilkan PermanentEmployee
     * @return void
     */
    public static void displayPermanentEmployee() {
        // cek apakah PermanentEmployee kosong
        if (getPermanentEmployee().isEmpty()) {
            return;
        }
        System.out.println("===== Pegawai Tetap =====");
        ArrayList<PermanentEmployee> permanentEmployees = getPermanentEmployee();
        for (PermanentEmployee employee : permanentEmployees) {
            System.out.println(employee); // cetak employee
        }
        System.out.println();
    }

    /*
     * Method ini digunakan untuk menampilkan ContractEmployee
     * @return void
     */
    public static void displayContractEmployee() {
        // cek apakah ContractEmployee kosong
        if (getContractEmployee().isEmpty()) {
            return;
        }
        System.out.println("===== Pegawai Kontrak =====");
        ArrayList<ContractEmployee> contractEmployees = getContractEmployee();
        for (ContractEmployee employee : contractEmployees) {
            System.out.println(employee); // cetak employee
        }
        System.out.println();
    }

    /*
     * Method ini digunakan untuk menampilkan InternEmployee
     * @return void
     */
    public static void displayInternEmployee() {
        // cek apakah InternEmployee kosong
        if (getInternEmployee().isEmpty()) {
            return;
        }
        System.out.println("===== Pegawai Intern =====");
        ArrayList<InternEmployee> internEmployees = getInternEmployee();
        for (InternEmployee employee : internEmployees) {
            System.out.println(employee); // cetak employee
        }
        System.out.println();
    }

    /*
     * Method ini digunakan untuk mendapatkan InternEmployee
     * @return ArrayList<InternEmployee>
     */
    public static ArrayList<InternEmployee> getInternEmployee() {
        ArrayList<InternEmployee> internEmployees = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee instanceof InternEmployee) { // cek apakah employee instanceof InternEmployee
                internEmployees.add((InternEmployee) employee);
            }
        }
        return internEmployees;
    }

    /*
     * Method ini digunakan untuk mendapatkan ContractEmployee
     * @return ArrayList<ContractEmployee>
     */
    public static ArrayList<ContractEmployee> getContractEmployee() {
        ArrayList<ContractEmployee> contractEmployees = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee instanceof ContractEmployee) { // cek apakah employee instanceof ContractEmployee
                contractEmployees.add((ContractEmployee) employee);
            }
        }
        return contractEmployees;
    }

    /*
     * Method ini digunakan untuk mendapatkan PermanentEmployee
     * @return ArrayList<PermanentEmployee>
     */
    public static ArrayList<PermanentEmployee> getPermanentEmployee() {
        ArrayList<PermanentEmployee> permanentEmployees = new ArrayList<>();
        for (Employee employee : employeeList) {
            // cek apakah employee instanceof PermanentEmployee
            if (employee instanceof PermanentEmployee) {
                permanentEmployees.add((PermanentEmployee) employee);
            }
        }
        return permanentEmployees;
    }

    /*
     * Method ini digunakan untuk menampilkan pesan pembuka
     * @return void
     */
    public static void printWelcomingMsg() {
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("[1] Employee List");
        System.out.println("[2] Hire Employee");
        System.out.println("[3] Raise Salary");
        System.out.println("[4] Extend Contract");
        System.out.println("[5] Exit");
        System.out.println("=".repeat(64));
    }

    /*
     * Method ini digunakan untuk keluar dari program
     * @return void
     */
    public static void unknownActionMsg() {
        System.out.println("Mohon masukkan opsi yang valid!\n");
    }
}