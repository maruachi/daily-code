package Crane1;

import java.util.Objects;

public class Doll {
    private static final int MAX_ID = 100;
    private static final Doll[] DOLLS = new Doll[MAX_ID+1];
    static {
        for(int id = 1; id <= MAX_ID; id++){
            DOLLS[id] = new Doll(id);
        }
    }

    private final int id;

    public Doll(int id) {
        if(id <= 0 || id > 100){
            throw new RuntimeException();
        }
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doll doll = (Doll) o;
        return id == doll.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Doll{" +
                "id=" + id +
                '}';
    }
}
