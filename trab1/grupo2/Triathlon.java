package trab1.grupo2;

/*
6.
Defina a classe Triathlon. Tendo em conta que:

- No triatlo o mesmo atleta participa em três competições de modalidades distintas. O construtor lança a exceção
CompetitionException caso: a dimensão do array seja diferente de três (mensagem: "Triatlo: Número de competições
inválido"); as três competições não correspondam a modalidades distintas (mensagem: "Triatlo: Modalidades inválidas");
ou não sejam do mesmo atleta (mensagem: "Triatlo: Atleta inválido").

- O tempo do triatlo é o somatório dos tempos das três competições.

- O método getModality retorna "Triatlo";

- O método getAthlete retorna o atleta que realizou as três competições.

- O método get ThreeTimes retorna um array com o tempo de cada modalidade pela ordem inversa que as
    competições foram dadas no construtor.

- O método toString retorna uma string com a descrição da competição triatlo seguida da descrição da competição
    de cada modalidade, as descrições são separadas pelo caráter fim de linha.
 */

public class Triathlon extends Competition {

    public Competition [] competitions = new Competition[3];

    public Triathlon (Competition ...c){
    super(sumTimes(c));
    competitions = c;
    }

    @Override
    public String getModality() {
        return "Triatlo";
    }

    //feitooooo
    @Override
    public Athlete getAthlete() {
        Athlete ath = competitions[0].getAthlete(); //apenas quero uma dimensão para aceder ao nome do atleta
        return ath;
    }



    public double[] getThreeTimes(){
        double[] sum =  new double[competitions.length]; //com a mesma dimensão que o do array original
        int index = 0; //para utilizar para percorrer o array original
        for(int i = competitions.length - 1 ; i>=0; i--){
            sum[i] = competitions[index].getTime();
            index++;
        }
        return sum;
    }



    public String toString(){
    StringBuilder tri = new StringBuilder( super.toString());
        for(int i =  0; i<competitions.length; i++){
        tri.append(String.format(  "\n" + "\t- "+ competitions[i].toString()));
        }
        return tri.toString();
    }
}
