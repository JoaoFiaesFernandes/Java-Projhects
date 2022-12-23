package trab1.grupo1;

public class Example {
    public static void main(String[] args) {
        String txt= "A dimensão do int é 32 bits";
        Query q1= new Query(txt);
        System.out.println(q1); //ele vai buscar o toStrting para a escrita dos elementos da Query
        Query q2= new Query(txt);
        System.out.println( q1 == q2 );
        System.out.println( q1.equals( q2 ) );
        Object o = q2;
        System.out.println( q1.equals(o) );
        Query q3 = null; //modificado para o valor que se espera o resultado na consola
        System.out.println( q1.equals(q3) );//
        if ( q3 != null )
        System.out.println(q1==q3);
        }
}
