package trab3.bubbles.pieces;

import trab3.bubbles.Board;

import java.util.function.Predicate;

public class DiagonalBubble extends SelectableBubble {
	// TODO - A classe que estende e o código implementado é só para que possa ser instanciado
	public DiagonalBubble(Board b, int l, int c, int cl) { super(b, l, c); }
	public int getColor() {	return 5; }

}
