// Class Salesman adalah subclass dari Employee
public class Salesman extends Employee {
    // data field yang hanya dimiliki oleh Salesman
    private double totalSales;
    private double commissionFee;

    // Constructor serta Setter dan Getter
    public Salesman(String name, int yearsOfWork, double baseSalary, int totalSales, double commissionFee) {
        super(name, yearsOfWork, baseSalary);
        this.totalSales = totalSales;
        this.commissionFee = commissionFee;
    }
    public double getTotalSales() {
        return this.totalSales;
    }
    public void setTotalSales(double totalSales) {
        this.totalSales += totalSales;
    }
    public double getCommissionFee() {
        return this.commissionFee;
    }
    public void setCommissionFee(double commissionFee) {
        this.commissionFee = commissionFee;
    }
    // method untuk menghitung salary salesman
    public double CalculateSalary() {
        // TODO implementasikan method CalculateSalary yang merupakan method override dari class Employee
        double fee = this.commissionFee / 100 * this.totalSales;
        switch (this.jabatan) {
            case "Expert":
                fee = fee * 2;
                break;
            case "Senior":
                fee = fee * 1.5;
                break;
            case "Junior":
                fee = fee * 1;
                break;
        }
        this.setFinalSalary(this.getBaseSalary() + fee);
        return this.getFinalSalary();
    }

    @Override
    public String toString() { // method untuk mencetak salesman ke layar
        // TODO: implementasikan method toString yang merupakan method override dari class Object
        return  "\nNama: " + this.getName() +
                "\nPengalaman Kerja: " + this.getYearsOfWork() + " tahun" +
                "\nJabatan: " + this.getJabatan() +
                "\nRole: " + this.getClass().getSimpleName() +
                "\nBanyak Sales: " + this.getTotalSales() +
                "\nFinal Salary: " + this.getFinalSalary() + " IDR";
    }
}