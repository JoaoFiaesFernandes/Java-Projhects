
package trab1.grupo4;

import java.util.ArrayList;

public class Chapter extends Content{

    public final int numberofChapter;
    private final Book book;


    public Chapter (String title, int chap, Book b, int hp, int np) {
        super( "Cap." + chap, title,hp,np); //Stringformat alterar
        this.book = b;
        this.numberofChapter = chap;
    }


    public Chapter (String title, Book b, int np) {
        super("Cap. 01", title, 1,  np); //vêm de content
        this.book = b;
        numberofChapter = 1;

    }


/*Ao construtor da classe base deve ser passado como primeiro parâmetro
         (prefix) a string “Cap. “ seguida de dois dígitos correspondentes ao número (Ex: “Cap. 03 –“).*/

    String getDescription(){
        return toString() + "in" + book.toString() + "," + pagesToString(); //vai buscar o da super classe publication, vai buscar ao book
    }

}


