package gameoflife;

public class Generation {
    private int birth, death;

    public Generation(int birth, int death) {
        this.birth = birth;
        this.death = death;
    }

    public int getBorn() {
        return birth;
    }

    public int getDeath() {
        return death;
    }

    public void increaseBorn() {
        ++birth;
    }

    public void increaseDeath() {
        ++death;
    }

    public void decreaseBorn() {
        --birth;
    }

    public void decreaseDeath() {
        --death;
    }
}
