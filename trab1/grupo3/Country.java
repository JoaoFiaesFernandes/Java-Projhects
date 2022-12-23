package trab1.grupo3;

public class Country extends State {

  private final int area;
  private final boolean sovereign;

    // No construtor recebe por parametro o nome, a area e se é soberano.
    public Country(String name, int a, boolean sovereign) {
        super(name); //redefenição de name
        area = a;
        this.sovereign = sovereign;
    }
    // o metodo getArea retorna a area passada no construtor
    public int getArea() {
        return area;
    }

    // o metodo isSovereign retorna o valor passado no construtor
    public boolean isSovereign() {
        return sovereign;
    }

    public String getDescription(String prefix) {
        String estado;
        if (sovereign == true) {
            estado = "Estado soberano";
        } else estado = "Estado autónomo";

        return super.getDescription(prefix) + estado + " (" + area + " km²)";

    }  //retorna o getdescription em state

}