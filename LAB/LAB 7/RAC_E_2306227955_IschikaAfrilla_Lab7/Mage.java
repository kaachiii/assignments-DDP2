public class Mage extends Warrior {
    // Inisiasi ID warrior
    private int ID;

    // instansiasi Mage
    public Mage(String name, int attack, int defense, int health) {
        super(name, attack, defense, health);
    }

    // Penetrating Attack untuk Mage
    @Override
    public void attack(Warrior target) {
        int damage = this.attack + target.getDefense();
        System.out.println(this.name + " casts a spell that ignores defense!");
        target.takeDamage(damage);
    }

    // menghidupkan kembali Mage
    @Override
    public void revive() {
        super.revive();
        this.health = 500; // mengisi health Mage kembali
    }

    //Display stats
    public void displayStats() {
        System.out.printf("| %-10s | %-15s | %-7s | %-7s | %-7s | %-7s | %-10s | %-10s |\n", this.getClass().getName(), this.getName(), this.getAttack(), this.getDefense(), this.getHealth(), "", "", "");
    }

    @Override
    public void setID(int id) {
        this.ID = id;
    } // setter ID

    @Override
    public int getID() {
        return this.ID;
    } // getter ID
}
