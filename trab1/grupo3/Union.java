package trab1.grupo3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public class Union extends State implements Composition {

    private final String tipo; // tipo da união
    private final ArrayList<State> states = new ArrayList<>();

    // O construtor recebe por parametro o nome e o tipo da uniao
    public Union(String nm, String tp){
        super(nm);
        tipo = tp ;
    }

    // o metodo isSovereign retorna false
    public boolean isSovereign(){
        return false;
    }


    // o metodo getArea retorna o somatorio das areas dos estados que agrega
    public int getArea(){
        int areaTotal = 0;
        for(int i = 0; i< states.size(); i++){
            areaTotal = areaTotal + states.get(i).getArea();
        }
        return areaTotal;
    }

    // o metodo find retorna a referencia para a propria uniao caso a propria uniao satisfaça o predicado
    // caso contrario se encontrar nos estados que agrega um estado que satisfaça o predicado retorna
    // a referencia para esse estado, se não encontrar retorna null
     public State find (Predicate<State> filter){
        State a;
        if(filter.test(this)){
            return this;
        }
        for(State s: states){
            a = s.find(filter);//usar o find sobre cada s passar como parametro o predicado que recebe
            if(a != null){
                return a;
            }
        }
        return null;
     }

    // o metodo append adiciona o estado passado por parametro à uniao caso não encontre um estado com o
    // mesmo nome na uniao. Usar o método find para verificar se existe um estado com o mesmo nome.
    // retorna a propria union.
     public Union append (State s) throws StateException {
        Predicate<State> p = new Predicate<State>() {
            public boolean test (State s2) {
                return (s.name.equals(s2.name));
            }
        };
        State aux = find(p);
        if(aux == null){
            states.add(s);
        }
           return this;
     }


    // o metodo iterator retorna um Iterator para os estados que agrega
    public Iterator<State> iterator(){
        return states.iterator();
        }



    // o metodo size retorna o numero de estados adicionados
    public int size(){
        return states.size();
    }

    // o metodo getfirst retorna o primeiro estado adicionado
    public State getFirst(){
    return states.get(0); //primeiro state inserido
    }

    // o metodo getLast retorna o ultimo estado adicionado
    public State getLast(){
        return states.get(states.size()-1); //ultimo state inserido
    }

    //falta
    @Override
    public String getDescription (String prefix){
        String des = super.getDescription(prefix) + tipo;
        for(int i = 0; i<states.size(); i++){
            des =  des + "\n"  + states.get(i).getDescription(prefix + "  ");
        }
    return des;

    }
}
