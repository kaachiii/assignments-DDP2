public class Employee {
    // data field objek Employee
    private int employeeID;
    private String name;
    public String jabatan;
    private int yearsOfWork;
    private double baseSalary;
    private double finalSalary;

    // membuat constructor employee
    public Employee (String name, int yearsOfWork, double baseSalary) {
        this.name = name;
        this.yearsOfWork = yearsOfWork;
        this.baseSalary = baseSalary;
        if (yearsOfWork > 10) {
            this.jabatan = "Expert";
        }
        else if (yearsOfWork > 5) {
            this.jabatan = "Senior";
        }
        else {
            this.jabatan = "Junior";
        }
    }
    // setter dan getter
    public String getJabatan() {
        return this.jabatan;
    }
    public int getEmployeeID() {
        return this.employeeID;
    }
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getYearsOfWork() {
        return this.yearsOfWork;
    }
    public void setYearsOfWork(int yearsOfWork) {
        this.yearsOfWork = yearsOfWork;
    }
    public double getBaseSalary() {
        return this.baseSalary;
    }
    public void setBaseSalary(double baseSalary) {
    }
    public double getFinalSalary() {
        return this.finalSalary;
    }
    public void setFinalSalary(double finalSalary) {
        this.finalSalary = finalSalary;
    }

    // method untuk menghitung salary
    public double CalculateSalary() {
        switch (this.getJabatan()) {
            case "Expert":
                this.baseSalary = this.baseSalary * 2;
                break;
            case "Senior":
                this.baseSalary = this.baseSalary * 1.5;
                break;
            case "Junior":
                break;
        }
        return this.getFinalSalary();
    }
    @Override
    public String toString() {
        // method untuk mencetak objek employee
        return  "\nNama: " + this.getName() +
                "\nPengalaman Kerja: " + this.getYearsOfWork() + " tahun" +
                "\nJabatan: " + this.getJabatan() +
                "\nRole: " + this.getClass().getSimpleName();
    }
}
