package trab1.grupo3;

import java.util.function.Predicate;

public interface Composition extends Iterable<State>{ //duvida

    public Composition append(State s) throws StateException;

    public State find(Predicate<State> pred);

    public int size();

    public State getFirst();

    public State getLast();



}
