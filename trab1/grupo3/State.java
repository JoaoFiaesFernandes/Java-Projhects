package trab1.grupo3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;


public abstract class State implements Comparable <State>{
    // o campo name só pode ser afetado no construtor
    public final String name;

    // o construtor recebe por parametro o nome do estado
    protected State(String nm){name = nm;}

    public State find( Predicate<State> pred) {return pred.test(this) ? this: null;}

    public String getDescription(String prefix) {return prefix + name + " - ";}

    //perguntar
    public int compareTo(State other){
        return Integer.compare(other.getArea(),getArea());
    }

    public abstract boolean isSovereign();

    public abstract int getArea();

    public final String toString () {return getDescription("");}


}
