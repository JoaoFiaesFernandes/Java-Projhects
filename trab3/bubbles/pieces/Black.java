package trab3.bubbles.pieces;

import trab3.bubbles.Board;

import java.util.function.Predicate;

public class Black extends SelectableBubble {
	// todo - A classe que estende e o código implementado é só para que possa ser instanciado
	public Black(Board b, int l, int c )   { super(b,l,c);   }
	public int getColor( )                 { return 1;       }
}
