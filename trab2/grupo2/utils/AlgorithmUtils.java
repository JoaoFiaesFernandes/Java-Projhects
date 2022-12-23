package trab2.grupo2;

import javax.swing.text.html.HTMLDocument;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class AlgorithmUtils {

    // a)
    // Que retorna true se a série estiver ordenada de forma crescente ou decrecente
    // segundo o comparador
    // compareValue, caso contrário false
    public static <E> boolean isOrdered(Collection<E> seq, Comparator<E> compareValue) {

        if (seq.isEmpty() || seq.size() == 1) // caso onde vazio ou 1 elemento
            return true;

        Iterator<E> iter = seq.iterator();
        E current, previous = iter.next();
        while (iter.hasNext()) {
            current = iter.next();
            if (compareValue.compare(previous, current) > 0)
                return false;
            previous = current;
        }
        return true;
    }

    // b)
    // Que produza uma lista com os valores da enésima subsequência ordenada, de
    // forma crescente segundo o comparador
    // cmp, existentes na sequência seq. Retorna uma lista vazia caso a sequência
    // não
    // contenha n subsequências. Lança a exceção de runtime illegalArgumentException
    // caso n seja menor do que 1.
    public static <E> List getSubSequences(Collection<E> seq, int n, Comparator<E> cmp) {
        List Result = List.copyOf(seq); // criar uma lista copyof seq
        Result.sort(cmp); // criar uma lista ordenada

        // caso onde n é menor que 1
        if (n < 1)
            throw new IllegalArgumentException("n must be greater than zero"); // lança a exceção

        // caso onde n != subsequências
        if (seq.size() != n) {
            return null; // return lista vazia
        }

        // return lista ordenada
        return Result;
    }


    /**
     * @Author: 47597 - João Fernandes
     */
    // c)
    public static <K,V,C extends Collection<V>> void addAll(BufferedReader in, Map<K,C> m,
    Function<String, V> getValue, 
    Function<V, K> getKey, Supplier<C> supC) throws IOException {
        String str;
        while((str = in.readLine()) != null ){
            V v = getValue.apply(str);
            K k = getKey.apply(v);
            if(!m.containsKey(k)){ //if there is no key 
                C c = supC.get(); //creates the new colection
                c.add(v); //adds to it
                m.put(k, c); //adds the new collection and key to the contentor
            }
            else { //if there is a created adds the new value V to the collection
                m.get(k).add(v);
            }

        }

    }

    /**
     * @Author: 47597 - João Fernandes
     */

    // d)
    public static <K, V, C extends Collection<V>> void forEachIf(Map<K, C> m,
            Predicate<K> pred,
            Consumer<V> action) {
        K k;
        C c;
        for (Map.Entry<K, C> e : m.entrySet()) {
            k = e.getKey();
            if (pred.test(k)) {
                c = e.getValue();
                for (V v : c)
                    action.accept(v);
            }
        }
    }

    /**
     * @Author: 47597 - João Fernandes
     */

    // e)
    // Que recebendo por parâmetro o contentor associativo m, executa a ação para
    // cada par chave, coleção que obedece ao
    // predicado p.
    public static <K, V, C extends Collection<V>> void forEachIf(Map<K, C> m, BiPredicate<K, C> p, BiConsumer<K, C> action) {
        m.forEach((k, c) -> {
            if (p.test(k, c)) {
                action.accept(k, c);
            }
        });
    }
}