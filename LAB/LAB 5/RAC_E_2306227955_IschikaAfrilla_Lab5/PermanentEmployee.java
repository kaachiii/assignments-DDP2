public class PermanentEmployee extends Employee implements AskForRaise { //TODO: impelementasikan sesuai UML diagram
    // atribut class PermanentEmployee
    public double baseSalary;
    public double raise;

    /*
     * Method ini digunakan sebagai constructor PermanentEmployee
     */
    public PermanentEmployee(String name, double salary) {
        super(name, salary); // instansiasi dari super
        this.baseSalary = salary;
        System.out.println(this.getClass().getSimpleName() + " dengan ID " + this.employeeId + " bernama " + this.name + " berhasil ditambahkan!\n");
    }

    /*
     * Method ini digunakan untuk menghitung salary dari PermanentEmployee
     * @return double
     */
    @Override
    public double calculateSalary() {
        return this.baseSalary + this.raise;
    }

    /*
     * Method ini digunakan untuk mengajukan kenaikan gaji PermanentEmployee
     * @return void
     */
    @Override
    public void askRaise(double raise) {
        this.raise += raise;
    }

    /*
     * Method ini digunakan untuk mencetak ContractEmployee
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[%d] %s | Salary : %.0f | Kenaikan : %.0f", this.employeeId, this.name, this.salary, this.raise);
    }
}