 package trab1.grupo2;


/*
2.
Defina a classe Competition. O construtor recebe o tempo obtido na competição. O método getTime retorna o tempo
recebido no construtor e não pode ser redefinido. Os métodos getModality e getAthlete são abstratos. O método
toString retorna a string resultante da concatenação da modalidade com o nome do atleta separadas pelo carácter
dois pontos, seguindas do tempo. O método estático sumTimes retorna a soma dos tempos das competições recebidas
por parâmetro.

final getTime() // retorna o tempo recebido no construtor

abstract getModality() // retorna modalidade
abstract getAthlete() // retorna nome do atleta

toString -> "50 metros mariposa: Diogo Ribeiro - 22.96"
toString()
return getModality() + ": " + getAthlete() + " - " + getTime() ;

static sumTimes() // retorna a soma dos tempos das competições recebidas por parâmetro

 */

public abstract class Competition{
    private double time; //não pode ser redefenido

    public Competition (double time){
        this.time = time;
    }


    public final double getTime(){ //retona o tempo recebido no constrotor
        return time;
    }

    public abstract String getModality();

    public abstract Athlete getAthlete();

    public String toString(){
        return String.format(getModality() + ": " + getAthlete() + " - " + "%.02f",getTime());
    }

    public static double sumTimes (Competition ...c){
        double sum = 0; //para o calculo do tempos
        for(int i =0; i<c.length; i++){
            sum = c[i].time + sum;
        }
        return sum; //retorno da soma
    }


}

