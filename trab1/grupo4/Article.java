package trab1.grupo4;

import trab1.grupo3.StateException;

public class Article extends Content{

    private final Publication publi;

    // Ao construtor da classe base deve ser passado como parametro os autores, o titulo a pagina inicial
    // e o numero de paginas calculado tendo em conta as paginas inicial e final

    public Article(String authors, String title, Publication p, int hp, int fp) throws PublicationException{
        super(authors, title, hp, fp - hp + 1); //p.getNumberOfPages() - hp
        this.publi = p;

        if(hp>fp)
            throw new PublicationException();
    }


    public Publication getPublication(){
        return publi;
    }

    // O metodo toString acrescenta à string retornada pelo metodo toString da classe base o titulo da
    // publicação e a descrição das paginas retornada pelo metodo pagesToString
    // ex: Tavares, miguel Sousa "E desembarcaram nas praias", Expresso, 5
    // ex: Salvador, João Miguel "A falar é que a gente se entende", Expresso, 34-38

    public String toString(){
        String des = super.toString() + "," + " " + publi.getTitle() + ",";
        return des + pagesToString();//ex: Salvador, João Miguel "A falar é que a gente se entende", Expresso, 34-38

    }
}
