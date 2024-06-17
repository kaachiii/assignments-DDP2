public abstract class Warrior implements Comparable<Warrior>/* TODO */ {
    //Inisiasi atribut
    protected String name;
    protected int attack;
    protected int defense;
    protected int health;
    protected int numRevived;
    protected int id;

    // instansiasi warrior
    public Warrior(String name, int attack, int defense, int health) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
    }
    
    public String getName() {
        return name;
    } // getter name

    public int getHealth() { // getter health
        if (!this.isAlive()){
            this.health = 0;
        }
        return this.health;
    }

    public int getAttack() {return attack; } // getter attack

    public int getDefense() { // getter defense
        return defense;
    }

    public int getNumRevived() { // getter num revived
        return numRevived;
    }
    
    public void attack(Warrior target) { // menyerang target
        System.out.println(this.name + " attacks " + target.name + " for " + this.attack + " damage.");
        target.takeDamage(this.attack);
    }

    public void takeDamage(int damage) {
        int reducedDamage = damage - this.defense;
        if (reducedDamage < 0) {
            reducedDamage = 0;
        }
        this.health -= reducedDamage;
        if (this.health < 0) {this.health = 0;}
        System.out.println(this.name + " takes " + reducedDamage + " damage, remaining health: " + this.health);
    }

    // abstract method untuk display status
    public abstract void displayStats();
    // setter ID
    public abstract void setID(int id);
    // getter ID
    public abstract int getID();

    public boolean isAlive() { // cek apakah warrior masih hidup
        return this.health > 0;
    }

    public void revive() {
        numRevived++; // tambahkan jumlah kematian
    }

    public int compareTo(Warrior other) {
        // membandingkan nama warrior agar bisa disort
        return this.getName().compareTo(other.getName());
    }

}
