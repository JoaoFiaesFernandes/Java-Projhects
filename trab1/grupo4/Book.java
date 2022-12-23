
package trab1.grupo4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Book extends Publication implements Composite<Chapter>{

    // 6)
    private final String isbn;
    private final ArrayList<Chapter> chapters = new ArrayList();

    public Book (String authors, String title, String isbn){
        super(authors, title); //da classe publication
        this.isbn = isbn;
    }

    public Book append (String chapterTitle, int pages) throws PublicationException {
        if(this.get(p->p.getTitle().equals(chapterTitle))!=null){//lança exceção caso nome do capitulo ja existir
            throw new PublicationException("Invalid chapter");
        }

        if(this.chapters.size()==0){ //caso a lista de capitulos estever vazia
            chapters.add(new Chapter(chapterTitle, this, pages)); //adiciona um novo capitulo com numero e pagina inicial a 1
        }
        else { //adiciona um novo capitulo sendo que a pagina inicial será a pagina final do anterior mais 1
            chapters.add(new Chapter(chapterTitle, chapters.size() + 1, this, chapters.get(chapters.size() - 1).getFinalPage() + 1, pages));
        }
        return this;//retorna se a si proprio permitindo o uso consecutivo do metodo append
    }



    public int getNumberOfPages(){
        int a;
        a = chapters.size() - 1; //obter o numero de capitolos
        if(a>=0) {
            Chapter c = chapters.get(a); //localização do ultimo capitulo
            return c.getFinalPage();
        }else return 0;
    }

    public String getDescription() {
        String s = new String();
        if (this.chapters.size() == 0) {
            return toString() + " ISBN:" + isbn + getNumberOfPages();

        } else {
            for(int i = 0; i<this.chapters.size(); i++){
                s = s + this.chapters.get(i).getDescription();
            }
            return toString() + " ISBN:" + isbn + ", " + getNumberOfPages() + s;
        }
    }

    @Override
    public Chapter get(Predicate<Chapter> pred) {
        Chapter chap=null;
        for (Chapter c: chapters) {//percorre a lista de capitulos
            if(pred.test(c)){ chap=c;}//retorna o capitulo que obtece à condição
        }
        return chap;
    }

    @Override
    public List<Chapter> getAll() {
        ArrayList<Chapter> sorted = new ArrayList<>(chapters); //instaciar um arraylist
        //sorted.addAll(chapters);//adicionar os capitulos de chapters (addAll)
        chapters.sort((a,b)->a.getTitle().compareTo(b.getTitle()));//defenir um comparador recebe 2 chapters compara os titulos e chamar o metudo sort passando-lhe como parametro o comparador
        sorted.addAll(chapters);//adicionar os capitulos de chapters (addAll)
        return sorted;//retornar o novo array

    }

    @Override
    public Composite<Chapter> append(Chapter p) {
       throw new UnsupportedOperationException();
    }
}
