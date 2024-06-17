abstract class Employee{ // absract class Employee
    // atribut abstract class Employee
    public int employeeId;
    public static int employeeCnt = 0; // static variabel employeeCnt
    public String name;
    public double salary;

    /*
     * Method ini digunakan sebagai constructor Employee
     */
    public Employee(String name, double salary){
        this.name = name;
        this.salary = salary;
        this.employeeId = employeeCnt;
        employeeCnt++;
    }

    /*
     * Abstract method ini digunakan untuk menghitung salary dari Employee
     * @return double
     */
    public abstract double calculateSalary();

    /*
     * Abstract method ini digunakan untuk mencetak Employee
     * @return String
     */
    public abstract String toString();
}
