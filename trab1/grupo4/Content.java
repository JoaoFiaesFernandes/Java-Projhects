package trab1.grupo4;

abstract public class Content extends Publication{

    // 3)

    // pagina de inicio
    private final int hp;

    // numero de paginas
    private final int np;

    protected Content(String prefix, String title, int hp, int np){
        super(prefix, title);
        this.hp = hp;
        this.np = np;
    }

    // retorna o numero de paginas recebino por parametro no construtor
    public final int getNumberOfPages(){
        return np;
    }

    // retorna a pagina inicial
    public final int getHomePage(){
        return hp;
    }

    // retorna a pagina final (Calculada tendo em conta a pagina inicial e o numero de paginas)
    public final int getFinalPage(){
        return np + hp - 1;
    }

    // o metodo pagesToString retorna uma string contendo a descrição das paginas caso
    // o numero de paginas seja 1 a descrição contem unicamente o numero da pagina inicial
    // caso contrario a descrição contém a pagina inicial e a pagina final separadas pelo caracter '-'

    public String pagesToString(){
        if (np == 1)
            return " " + getHomePage();
        else
            return " " + getHomePage() + "-" + getFinalPage();
    }

}
