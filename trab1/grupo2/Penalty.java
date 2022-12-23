package trab1.grupo2;


/*
4.
Defina a classe Penalty. O construtor recebe a referência para a competição penalizada e o tempo de penalização.
A variável de instância competition só pode ser afetada no construtor. O tempo de uma competição com penalização é
o tempo da competição recebida por parâmetro acrescido do tempo de penalização. O atleta e o nome da modalidade
são os da competição recebida por parâmetro. O método toString retorna a string retornada pelo método toString
herdado concatenada com descrição da penalização entre parênteses retos (ver output do exemplo).

Event e = new Event( new AthleteTest("Arnaldo Abrantes"), "100 metros", 10.53);
System.out.println( e ); -> "100 metros: Arnaldo Abrantes - 10.53"

Penalty pe = new Penalty( e, 0.20);
System.out.println( pe ); -> "100 metros: Arnaldo Abrantes - 10.73 [10.53+0.20]


 */

public class Penalty extends Competition{
    public final Competition competition;
    final double pt; //penalty time


    public Penalty(Competition c, double tp){
        super(c.getTime() +  tp); //super funciona como um override
        competition = c;
        pt = tp;
    }

    public String getModality() {
    return competition.getModality();
    }

    public Athlete getAthlete(){
        return competition.getAthlete();
    }

    @Override
    public String toString(){
        return String.format(super.toString() + " [%.2f + %.2f]", competition.getTime(), pt);
    }
}


