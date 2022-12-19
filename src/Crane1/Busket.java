package Crane1;

import java.util.Stack;

public class Busket {
    private final Stack<Doll> values;

    private Busket(Stack<Doll> values) {
        this.values = values;
    }

    public static Busket createEmpty(){
        return new Busket(new Stack<>());
    }

    public void pileUp(Doll doll){
        values.add(doll);
    }

    public boolean isEmpty(){
        return values.empty();
    }

    public Doll checkTop(){
        return values.peek();
    }

    public void removeTop() {
        values.pop();
    }

    @Override
    public String toString() {
        return "Busket{" +
                "values=" + values +
                '}';
    }
}
