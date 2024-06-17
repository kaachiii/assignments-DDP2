// TODO: Class Engineer adalah subclass dari Employee
public class Engineer extends Employee {
    // data field yang hanya dimiliki oleh Engineer
    private int totalProject;
    private double projectFee;

    // Constructor serta Setter dan Getter
    public Engineer (String name, int yearsOfWork, double baseSalary, int totalProject, double projectFee) {
        super(name, yearsOfWork, baseSalary);
        this.totalProject = totalProject;
        this.projectFee = projectFee;
    }
    public int getTotalProject() {
        return this.totalProject;
    }
    public void setTotalProject(int totalProject) {
        this.totalProject += totalProject;
    }
    public double getProjectFee() {
        return this.projectFee;
    }
    public void setProjectFee(double projectFee) {
        this.projectFee = projectFee;
    }
    public double CalculateSalary() {
        // method untuk menghitung salary dari Engineer
        double fee = this.projectFee * this.totalProject;
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
        // method untuk mencetak objek engineer ke layar
        return  "\nNama: " + this.getName() +
                "\nPengalaman Kerja: " + this.getYearsOfWork() + " tahun" +
                "\nJabatan: " + this.getJabatan() +
                "\nRole: " + this.getClass().getSimpleName() +
                "\nBanyak Project: " + this.getTotalProject() +
                "\nFinal Salary: " + this.getFinalSalary() + " IDR";
    }
}
