public class ContractEmployee extends Employee implements AskForRaise, ExtendContractDuration {
    // atribut class ContractEmployee
    public int contractDuration;
    public double baseSalary;
    public double raise;

    /*
     * Method ini digunakan sebagai constructor ContractEmployee
     */
    public ContractEmployee(String name, double salary, int contractDuration) {
        super(name, salary); // instansiasi dari super
        this.contractDuration = contractDuration;
        this.baseSalary = salary;
        System.out.println(this.getClass().getSimpleName() + " dengan ID " + this.employeeId + " bernama " + this.name + " berhasil ditambahkan!\n");
    }

    /*
     * Method ini digunakan untuk menghitung salary dari ContractEmployee
     * @return double
     */
    @Override
    public double calculateSalary() {
        return (this.baseSalary + this.raise)*getSalaryMultiplier();
    }

    /*
     * Method ini digunakan untuk mengajukan kenaikan gaji ContractEmployee
     * @return void
     */
    @Override
    public void askRaise(double raise) {
        this.raise += raise;
    }

    /*
     * Method ini digunakan untuk memperpanjang kontrak ContractEmployee
     * @return void
     */
    @Override
    public void extendContract(int duration) {
        this.contractDuration += duration;
    }

    /*
     * Method ini digunakan untuk mencetak ContractEmployee
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[%d] %s | Salary : %.0f | Kenaikan : %.0f  | Kontrak : %d", this.employeeId, this.name, this.salary, this.raise, this.contractDuration);
    }

    /*
     * Method ini digunakan untuk membantu perhitungan salary dari ContractEmployee
     * @return double
     */
    private double getSalaryMultiplier() {
        if (this.contractDuration > 12){
            return 2;
        }
        else if (this.contractDuration > 6){
            return 1.5;
        }
        return 1;
    }
}
