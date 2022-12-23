package trab2.grupo2.utils;

import trab2.grupo2.AlgorithmUtils;

import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Families<C extends Collection<String>> {

    // O campo families é um contentor associativo em que a chave é o apelido da família e o valor associado é o conjunto
    // de nomes cujo apelido é igual ao da chave(membros da família).
    private Map<String, C> families;
    private Supplier<C> supplier;


    // O construtor recebe por parâmetro os fornecedores: supMap para obter o contentor associativo que armazena as
    // famílias; e supC para obter novas instâncias de coleções C quando a chave ainda não existe no contentor associativo
    // families.
    public Families(Supplier<Map<String, C>> supMap, Supplier<C> supC) {
        families = supMap.get();
        supplier = supC;
    }

    // Método estático que recebendo por parâmetro um nome retorna o apelido.
    public static String surname(String name) {
        String Result = null;
        Result = name.substring(name.lastIndexOf(" ") + 1); // guardar apenas o apelido que vem da string name
        return Result;
    }

    // Adiciona o nome ao contentor associativo. Na implementação usar o método allAll da alínea 1.c
    public void addName(String name) {
        try (BufferedReader br = new BufferedReader(new StringReader(name))) {
            AlgorithmUtils.addAll(br, families, Function.identity(), Families::surname, supplier); // usar addAll duvida
        }
        catch (IOException ignored) {

        }
    }

    // Lê o ficheiro names, onde cada linha contém nome completo de uma pessoa, e adicione os nomes ao contentor
    // associativo. A chave é o apelido da família e o valor associado é o conjunto de nomes cujo apelido é igual ao da
    // chave (membros da família). Na implementação usar o método addAll da alínea 1.c.
    public void addNames(File names) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(names))) {
            AlgorithmUtils.addAll(input, families, String::trim, Families::surname, supplier); //usar addAll duvida
        } catch (IOException ignored) {

        }
    }

    // Retorna o conjunto de apelidos existentes em families.
    public Set<String> getSurnames() {
        return families.keySet(); //returns the keyset
    }

    // Retorna o conjunto de nomes que existem em families que têm como apelido surname.
    public C getNames(String surname) {
        return families.get(surname); // retorna uma coleção com base na chave surname
    }

    // Produz e retorna o conjunto dos apelidos das famílias mais numerosa. O conjunto de apelidos deve ficar ordenado
    // por ordem decrescente. Na implementação usar o método estático max da classe Collections para obter o número
    // de membros das famílias mais numerosa e usar o método forEachIf da alínea 1.e para produzir o conjunto dos
    // apelidos
    public Set<String> getGreaterFamilies() {
        Set<String> s = new TreeSet<>();
        Map.Entry<String, C> values = Collections.max(families.entrySet(), Comparator.comparingInt(value -> value.getValue().size()));
        s.add(values.getKey());
        AlgorithmUtils.forEachIf(families, (k, v) -> v.size() == values.getValue().size(), (k, v) -> s.add(k));
        return s;
    }

    // Escreve no stream as famílias cujo apelido não consta no conjunto except. Por cada família a primeira linha contém
    // o apelido e o número de membros, as linhas seguintes contêm os nomes dos membros antecedidos do caracter tab.
    // Na implementação usar o método forEachIf da alínea 1.e.
    public void printFamilies(PrintWriter out, Set<String> except) {
        AlgorithmUtils.forEachIf(families, (k,v) -> !except.contains(k), (k,v)->{
            out.println(k + ": " + v.size());
            for(String name : v){
                out.println("\t" + name);
            }
        });
    }


    // Que execute a ação sobre cada nome. Na implementação usar o método forEachIf da alínea 1.d.
    public void forEachName(Consumer<String> action) {
        AlgorithmUtils.forEachIf(families, k -> true, action);
    }

    // Que execute a ação sobre cada par(apelido, coleção de nomes).
    public void forEach(BiConsumer<String, C> action) {
        AlgorithmUtils.forEachIf(families, (k,v) -> true, action);
    }
}