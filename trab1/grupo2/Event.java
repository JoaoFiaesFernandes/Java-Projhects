package trab1.grupo2;


/*
3. Defina a classe Event. O construtor recebe o atleta, o nome da modalidade e o tempo obtido na competição
Event e = new Event( new AthleteTest("Diogo Ribeiro"),"50 metros mariposa", 22.96);
system.out.println( e ); -> "50 metros mariposa: Diogo Ribeiro - 22.96"
 */

public class Event extends Competition{
    private Athlete athlete;
    private String mod;

    public Event (Athlete a, String m, double t){
        super (t);
        athlete = a;
        mod = m;
    }

    public String getModality(){
        return mod;
    }


    public Athlete getAthlete(){
        return athlete;
    }
}

