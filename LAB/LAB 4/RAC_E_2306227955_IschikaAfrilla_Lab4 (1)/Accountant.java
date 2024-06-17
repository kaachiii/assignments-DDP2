// Class Accountant adalah subclass dari Employee
public class Accountant extends Employee {
    // data field yang hanya dimiliki oleh accountant
    private int totalHoursWorked;
    private double hourlyRate;

    // Constructor serta Setter dan Getter
    public Accountant(String name, int yearsOfWork, double baseSalary, double hourlyRate) {
        super(name, yearsOfWork, baseSalary);
        this.hourlyRate = hourlyRate;
    }
    public int getTotalHoursWorked() {
        return this.totalHoursWorked;
    }
    public void setTotalHoursWorked(int totalHoursWorked) {
        this.totalHoursWorked += totalHoursWorked;
    }
    public double getHourlyRate() {
        return this.hourlyRate;
    }
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    public double CalculateSalary() {
        // method untuk menghitung pendapatan accountant
        double fee = this.hourlyRate * this.totalHoursWorked;
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
    public String toString() {
        // method untuk mencetak objek accountant ke layar
        return  "\nNama: " + this.getName() +
                "\nPengalaman Kerja: " + this.getYearsOfWork() + " tahun" +
                "\nJabatan: " + this.getJabatan() +
                "\nRole: " + this.getClass().getSimpleName() +
                "\nTotal Jam Kerja: " + this.getTotalHoursWorked() +
                "\nFinal Salary: " + this.getFinalSalary() + " IDR";
    }
}
