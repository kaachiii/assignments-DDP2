public class InternEmployee extends Employee implements ExtendContractDuration {
    // atribut class InternEmployee
    public int contractDuration;
    public double baseSalary;

    /*
     * Method ini digunakan sebagai constructor InternEmployee
     */
    public InternEmployee(String name, double salary, int contractDuration) {
        super(name, salary); // instansiasi dari super
        this.contractDuration = contractDuration;
        this.baseSalary = salary;
        System.out.println(this.getClass().getSimpleName() + " dengan ID " + this.employeeId + " bernama " + this.name + " berhasil ditambahkan!\n");
    }

    /*
     * Method ini digunakan untuk menghitung salary dari InternEmployee
     * @return double
     */
    @Override
    public double calculateSalary() {
        return this.baseSalary*getSalaryMultiplier();
    }

    /*
     * Method ini digunakan untuk memperpanjang kontrak InternEmployee
     * @return void
     */
    @Override
    public void extendContract(int duration) {
        this.contractDuration += duration;
    }

    /*
     * Method ini digunakan untuk mencetak InternEmployee
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[%d] %s | Salary : %.0f | Kontrak : %d Bulan", this.employeeId, this.name, this.salary, this.contractDuration);
    }

    /*
     * Method ini digunakan untuk membantu perhitungan salary dari InternEmployee
     * @return double
     */
    private double getSalaryMultiplier() {
        if (this.contractDuration > 12){
            return 1.5;
        }
        else if (this.contractDuration > 6){
            return 1.25;
        }
        return 1;
    }
}
