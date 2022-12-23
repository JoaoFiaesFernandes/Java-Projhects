package trab1.grupo2;

/*1. Defina a interface Athlete e a classe AthleteTest tendo em conta:
    Athele a = new AthleteTest("Diogo Ribeiro");
    system.out.println( a ); -> Diogo Ribeiro
 */

public class AthleteTest implements Athlete{
        String name;

        public AthleteTest(String a) {
            name = a; //guardar o nome da String passada
        }

        public String getName() {
            return name;
        }

        public String toString() {
            return getName();
        }

        public boolean equals(Object obj ) { //verificação
            if(!(obj instanceof AthleteTest)){
                return false;
            }
           AthleteTest q = (AthleteTest) obj; //cast realizado
            return name.equals(q.name);
        }
}

