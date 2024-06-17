import java.util.*;

public class Battlefield {
    // inisiasi generics yang digunakan
    public WarriorList<Warrior> warriorList = new WarriorList<Warrior>();
    // instansiasi scanner
    private Scanner scanner = new Scanner(System.in);

    public void runMenu() {
        while (true) {
            // Menu utama
            System.out.println("\nWelcome to the Battlefield Simulator!");
            System.out.println("1. Add Warrior");
            System.out.println("2. Display Warriors");
            System.out.println("3. Simulate Battle");
            System.out.println("4. Revive Warrior");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) { // switch option user
                case 1:
                    addWarrior(); // menambahkan warrior
                    break;
                case 2:
                    displayWarriors(); // menampilkan warrior
                    break;
                case 3:
                    simulateBattle(); // simulasi pertarungan
                    break;
                case 4:
                    revive(); // membangkitkan warrior yang mati
                    break;
                case 5:
                    System.out.println("--------Game Over--------");
                    System.out.println("░░░░░░░█▐▓▓░████▄▄▄█▀▄▓▓▓▌█");
                    System.out.println("░░░░░▄█▌▀▄▓▓▄▄▄▄▀▀▀▄▓▓▓▓▓▌█");
                    System.out.println("░░░▄█▀▀▄▓█▓▓▓▓▓▓▓▓▓▓▓▓▀░▓▌█");
                    System.out.println("░░█▀▄▓▓▓███▓▓▓███▓▓▓▄░░▄▓▐█");
                    System.out.println("░█▌▓▓▓▀▀▓▓▓▓███▓▓▓▓▓▓▓▄▀▓▓▐█");
                    System.out.println("▐█▐██▐░▄▓▓▓▓▓▀▄░▀▓▓▓▓▓▓▓▓▓▌█▌");
                    System.out.println("█▌███▓▓▓▓▓▓▓▓▐░░▄▓▓███▓▓▓▄▀▐█");
                    System.out.println("█▐█▓▀░░▀▓▓▓▓▓▓▓▓▓██████▓▓▓▓▐█");
                    System.out.println("▌▓▄▌▀░▀░▐▀█▄▓▓██████████▓▓▓▌█▌");
                    System.out.println("▌▓▓▓▄▄▀▀▓▓▓▀▓▓▓▓▓▓▓▓█▓█▓█▓▓▌█▌");
                    System.out.println("█▐▓▓▓▓▓▓▄▄▄▓▓▓▓▓▓█▓█▓█▓█▓▓▓▐█");
                    return;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, 3, 4, or 5.");
            }
        }
    }

    // Method untuk tambah warrior ke Arraylist
    private void addWarrior() {
        // Minta tipe warrior
        System.out.println();
        System.out.println("Select type of warrior:");
        System.out.println("1. Tank");
        System.out.println("2. Archer");
        System.out.println("3. Mage");
        int type = getValidInt("Choose an option: ", 1, 3);

        System.out.print("Enter Warrior name: ");
        String name = scanner.nextLine().trim();
        int health = getValidInt("Enter Warrior health (500 to 5000): ", 500, 5000);
        int attack = getValidInt("Enter Warrior attack (30 to 1000): ", 30, 1000);
        int defense = getValidInt("Enter Warrior defense (0 to 250): ", 0, 250);

        Warrior warrior = null; // instansiasi warrior

        // validasi input sesuai tipe warrior
        if (type == 1) {
            int shield = getValidInt("Enter shield strength (0 to 500): ", 0, 500);
            // instansiasi warrior Tank
            warrior = new Tank(name, attack, defense, health, shield);

        } else if (type == 2) {
            double criticalRate = getValidDouble("Enter critical rate (0.0 to 1.0): ", 0.0, 1.0);
            double criticalDamage = getValidDouble("Enter critical damage multiplier (1.0 to 5.0): ", 1.0, 5.0);
            // instansiasi warrior Archer
            warrior = new Archer(name, attack, defense, health, criticalRate, criticalDamage);

        } else if (type == 3) {
            // instansiasi warrior Mage
            warrior = new Mage(name, attack, defense, health);
        }

        // Tambah warrior ke List
        warriorList.addWarrior(warrior);
        System.out.println("\n" + warrior.getName() + " has been added to the battle.");
    }

    // Method untuk validasi int
    private int getValidInt(String prompt, int min, int max) {
        int input; // inisialisasi input
        do {
            System.out.print(prompt); // print prompt
            while (!scanner.hasNextInt()) {
                // validasi valid int
                System.out.println("That's not a valid number!");
                System.out.print(prompt);
                scanner.next();
            }
            input = scanner.nextInt();
            scanner.nextLine();

            // validasi range int
            if (input < min || input > max) {
                System.out.println("Please enter a value between " + min + " and " + max + ".");
            }
        } while (input < min || input > max);
        return input;
    }

    // Method untuk validasi double
    private double getValidDouble(String prompt, double min, double max) {
        double input; // inisialisasi input
        do {
            System.out.print(prompt); // print prompt
            while (!scanner.hasNextDouble()) {
                // validasi valid double
                System.out.println("That's not a valid number!");
                System.out.print(prompt);
                scanner.next();
            }
            input = scanner.nextDouble();
            scanner.nextLine();

            // validasi range double
            if (input < min || input > max) {
                System.out.println("Please enter a value between " + min + " and " + max + ".");
            }
        } while (input < min || input > max);
        return input;
    }

    // Method untuk display semua warrior
    public void displayWarriors() {
        // Sort menggunakan collections berdasarkan nama warrior
        Collections.sort(warriorList.getWarriors());

        System.out.println("\nCurrent warriors in the battlefield:");
        System.out.println(
                "+------------+-----------------+---------+---------+---------+---------+------------+------------+");
        System.out.printf("| %-10s | %-15s | %-7s | %-7s | %-7s | %-7s | %-10s | %-10s |\n", "Type", "Name", "Attack",
                "Defense", "Health", "Shield", "Crit Rate", "Crit Dmg");
        System.out.println(
                "+------------+-----------------+---------+---------+---------+---------+------------+------------+");

        // mencetak semua warrior di dalam List
        for (Warrior war: warriorList.getWarriors()){
            war.displayStats();
            System.out.println(
                    "+------------+-----------------+---------+---------+---------+---------+------------+------------+");
        }
    }

    // Method untuk simulasi attack
    public void simulateBattle() {

        // ambil List warrior yang masih bertarung dan yang sudah mati
        List<Warrior> warList = warriorList.getWarriors();
        Queue<Warrior> fallenList = warriorList.getFallenWarriors();
        Warrior attacker = null; // inisialisasi attacker
        Warrior defender = null; // inisialisasi defender

        if (warList.size() >= 2) {
            // cek apakah minimal ada 2 warrior untuk battle
            Collections.sort(warList); // sort warList
            for (Warrior war : warList) {
                // set ID warrior
                war.setID(warriorList.getWarriors().indexOf(war) + 1);
            }
            System.out.println("Select the attacking warrior:");

            // mencetak semua warrior di dalam list
            for (Warrior war4 : warList) {
                System.out.println(war4.getID() + ". " + war4.getName());
            }
            System.out.println();

            // input index attacker
            int attackerIndex = getValidInt("Choose a warrior: ", 1, warList.size());
            attacker = warList.get(attackerIndex - 1);

            System.out.println("Select the defending warrior:");
            // inisialisasi list untuk menyimpan ID
            ArrayList<Integer> arrayIdDefender = new ArrayList<Integer>();
            // mencetak semua defender yang tersedia
            for (Warrior war2 : warList) {
                if (war2.getID() != attacker.getID()) {
                    arrayIdDefender.add(war2.getID());
                    System.out.println(war2.getID() + ". " + war2.getName());
                }
            }

            Collections.sort(arrayIdDefender);
            System.out.println();
            int defenderIndex = getValidInt("Choose a warrior: ", arrayIdDefender.get(0), arrayIdDefender.get(arrayIdDefender.size() - 1));
            if (arrayIdDefender.contains(defenderIndex)){ // cek apakah index defender ada di arrayID defender
                defender = warList.get(defenderIndex - 1);
            }

            // Simulasi attacking dan defending beserta outputnya
            System.out.println();
            System.out.println(attacker.getName() + " is attacking " + defender.getName());
            attacker.attack(defender);

            // cek apakah defender masih hidup
            if (defender.isAlive()) {
                System.out.println(defender.getName() + " survived the attack with " + defender.getHealth() + " health remaining.");
            }
            else {
                System.out.println(defender.getName() + " has fallen in battle.");
                System.out.println(defender.getName() + " has been removed from the battle.");
                warList.remove(defender); // hapus defender yang sudah mati
                fallenList.offer(defender); // tambahkan defender yang sudah mati ke fallenlist
            }
        }
        else { // cek apakah ada warrior yang cukup untuk battle
            System.out.println("Not enough warriors for a battle. Please add more warriors.");
        }
    }

    // Method untuk membangkitkan warrior
    public void revive() {
        // cek apakah list fallen warriors kosong
        if (warriorList.getFallenWarriors().isEmpty()){
            System.out.println("There are currently no warriors to revive.");
        }
        else{
            // simpan elemen fallen warrior pada indeks pertama
            Warrior war = warriorList.getFallenWarriors().element();
            // simpan nama fallen warrior dan menghapusnya dari Queue
            String fallenName = warriorList.getFallenWarriors().remove().getName();
            // masukkan fallen warrior ke warrior list
            warriorList.getWarriors().add(war);
            System.out.println("Reviving " + fallenName + "...");
            System.out.println("Succesfully revived " + fallenName + "!");
            war.revive(); // call method untuk revive warrior
        }
    }

    public static void main(String[] args) {
        // instansiasi battlefield
        Battlefield battlefield = new Battlefield();
        battlefield.runMenu(); // run battlefield
    }
}
