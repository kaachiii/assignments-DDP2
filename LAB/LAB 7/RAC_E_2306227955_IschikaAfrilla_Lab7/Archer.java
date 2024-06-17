public class Archer extends Warrior {
    // inisiasi atribut
    private double criticalRate; 
    private double criticalDamage;
    private int ID;

    //Instansiasi Archer
    public Archer(String name, int attack, int defense, int health, double criticalRate, double criticalDamage) {
        super(name, attack, defense, health);
        this.criticalRate = criticalRate;
        this.criticalDamage = criticalDamage;
    }

    //Attack + crit untuk archer
    @Override
    public void attack(Warrior target) {
        if (Math.random() < this.criticalRate) {
            int criticalHitDamage = (int)(this.attack * this.criticalDamage);
            System.out.println(this.name + " lands a CRITICAL HIT!");
            target.takeDamage(criticalHitDamage);
        } else {
            target.takeDamage(this.attack);
        }
    }

    @Override
    public void revive() {
        super.revive();
        this.health = 1000; // kembalikan health = 1000
    }

    public double getCriticalRate() {return this.criticalRate;} // getter critical rate
    public double getCriticalDamage() {return this.criticalDamage;} // getter critical damage

    // Display stats
    public void displayStats() {
        System.out.printf("| %-10s | %-15s | %-7s | %-7s | %-7s | %-7s | %-10s | %-10s |\n", this.getClass().getName(), name, attack, defense, health, "", criticalRate, criticalDamage);
    }

    @Override
    public void setID(int id) { // setter ID
        this.ID = id;
    }

    @Override
    public int getID() { // getter ID
        return this.ID;
    }
}
