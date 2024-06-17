import java.util.*;

public class WarriorList <Warrior> {
    // instansiasi konkret class yaitu ArrayList
    private List<Warrior> warriors = new ArrayList<Warrior>();
    // instansiasi konkret class yaitu LinkedList
    private Queue<Warrior> fallenWarriors = new LinkedList<Warrior>();

    public void addWarrior(Warrior warrior) {
        // menambahkan warrior ke list warriors
        warriors.add(warrior);
    }

    public void removeWarrior(Warrior warrior) {
        // menghapus warrior dari warriors
        warriors.remove(warrior);
    }

    public List<Warrior> getWarriors() {
        // getter warriors
        return warriors;
    }

    public void addFallenWarrior(Warrior warrior) {
        // menambahkan warrior ke fallen warrior
        fallenWarriors.add(warrior);
    }

    public Queue<Warrior> getFallenWarriors() {
        // getter fallen warriors
        return fallenWarriors;
    }
}
