package trab1.grupo4;

import java.util.List;
import java.util.function.Predicate;

public interface Composite<Chapter>{
    public Chapter get (Predicate<Chapter> pred);
    public List<Chapter> getAll();
    public Composite<Chapter> append(Chapter p);
}
