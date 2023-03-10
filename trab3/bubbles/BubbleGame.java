package trab3.bubbles;

import trab3.bubbles.pieces.*;
import trab3.bubbles.strategies.Strategy;
import trab3.bubbles.strategies.StrategyGravitational;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.IntUnaryOperator;

public class BubbleGame implements Game, Board {
    // A DUMMY_BUBBLE é uma bolha fiticia que permite que não
    //  existam testes aos limites do tabuleiro.
    protected final static Bubble DUMMY_BUBBLE = new Dummy();
    // Utilizado para preencher aleatóriamente as cores no tabuleiro
    private static final Random randomize = new Random();

    // Posições em que podem ser colocadas bolhas - usado no preenchimento aleatório
    private final int[] positionsShuffle; // Depende da dimensão do tabuleiro

    // Percentagem de bolhas para cada cor.
    private final int[] percentagePerColor = {1, 1, 21, 21, 21, 12, 12, 12};
    private final int[] numberPerColor; // Depende da dimensão do tabuleiro

    private final Bubble[][] board; // Tabuleiro de jogo - existem 2 linhas
    // a mais e por cada linha 2 colunas.

    // Objectos que pretendem ser avisados de modificações no jogo
    private final ArrayList<GameListener> listener = new ArrayList<>();
    long timeStart, // Colocado com a hora currente no start.
            timeStop;  // Colocado a 0 no start e atualizado com a hora currente do stop.
    // Estratégia do jogo -
    private Strategy strategy = new StrategyGravitational();
    // Informação que vai sendo atualizada ao longo do jogo
    private int bubbles, points;

    public BubbleGame(int numberLines, int numberColumns) {
        board = new Bubble[numberLines + 2][numberColumns + 2];

        int numberOfBubbles = numberLines * numberColumns;
        numberPerColor = calculateNumberPerColor(percentagePerColor, numberOfBubbles, 2);
        positionsShuffle = fillPositions(numberOfBubbles, IntUnaryOperator.identity());

        // Preencher o tabuleiro com a bolha fiticia - DUMMY_BUBBLE.
        for (int line = 0; line < board.length - 1; ++line)
            Arrays.fill(board[line], DUMMY_BUBBLE);
        board[board.length - 1] = board[0];
    }

    // << Implementação da interface Board >>
    public final int getNumberOfColumns() {
        return board[0].length - 2;
    }

    public final int getNumberOfLines() {
        return board.length - 2;
    }

    public Bubble getBubble(int l, int c) {
        return board[l + 1][c + 1];
    }

    public void putBubble(Bubble b) {
        board[b.getLine() + 1][b.getColumn() + 1] = b;
        unselected(b);
    }

    public void putHole(int line, int column) {
        putBubble(new Hole(this, line, column));
        if (line == getNumberOfLines() - 1)
            strategy.freeColumn(this, column);
    }

    // << Implementação da interface  BubbleListener >>
    public void selected(Bubble b) {
        strategy.add(b);
        listener.forEach(li -> li.selected(b));
    }

    public void unselected(Bubble b) {
        listener.forEach(li -> li.unselected(b));
    }

    // << Implementação da interface Game >>
    public final Score getScore() {
        return new Score(getTime(), bubbles, points);
    }

    public int getTime() {
        long time = (timeStop == 0) ? System.currentTimeMillis() : timeStop;
        return (int) (time - timeStart) / 1000;
    }

    public void addListener(GameListener v) {
        listener.add(v);
    }

    public void setStrategy(Strategy s) {
        stop();
        strategy = s;
    }

    /**
     * @Author João Fernandes
     */

    public void select(int line, int column) {
        //variavel de estado incluir referente ao jogo
        if(timeStop != 0){
            return;
        }
        Bubble b = getBubble(line, column);
        if (b.isSelected()) {
            int n = strategy.removeSelected(this);
            bubbles -= n;


            // TODO - actualizar o número de bubbles e
            //        os pontos tendo em conta a cor e o n


            listener.forEach(li -> li.scoreChange(getScore()));
        } else
            strategy.select(b);
    }

    /**
     * @Author João Fernandes
     */

    public void start() {
        if (timeStop == 0 && timeStart !=0) return;
        timeStop = 0;
        timeStart = System.currentTimeMillis();
        points = 0;
        bubbles = getNumberOfLines() * getNumberOfColumns();
        fillBoard(positionsShuffle.length, numberPerColor, positionsShuffle);
        listener.forEach(li -> li.gameStart(getScore()));
    }

    public void stop() {
        if (timeStop < timeStart) {
            timeStop = System.currentTimeMillis();
            // TODO - actualizar os pontos tendo em conta o tempo
            listener.forEach(li -> li.gameStop(getScore()));
        }
    }



    /**
     * @Author João Fernandes
     */

    //Writing the How to play file in the board plane
    public void help() throws IOException {
        try(BufferedReader read = new BufferedReader(new FileReader("bubbles.txt"))){ //reads the how to play file)
            String line; //aux string for collecting each line
            String ft = "How to play: \n"; //String for concat
            while((line = read.readLine()) != null){
                ft = ft + line + "\n"; //creating the concat text
            }
            JOptionPane.showMessageDialog(null,ft);//display of the message

        }catch (IOException discard){
            throw new IOException();
        }
    }


    // << Métodos auxiliares para preencher o tabuleiro >>

    /**
     * Produz um array com posições do tabuleiro que deveram ser preenchidas.
     * As posições são calculadas por uma determinada função.
     *
     * @param numberOfBubbles - numero de posições a preencher.
     * @param operation       - função para calculo da posição
     * @return o array de posições
     */
    private int[] fillPositions(int numberOfBubbles, IntUnaryOperator operation) {
        int[] array = new int[numberOfBubbles];
        for (int pos = 0; pos < numberOfBubbles; ++pos)
            array[pos] = operation.applyAsInt(pos);
        return array;
    }

    /**
     * TODO - Descrever o algoritmo usado
     *
     * @param percentages    - percentagem que deve ter cada cor
     * @param totalOfBubbles - numero total de bolhas
     * @return número de bolhas por cor
     */
    private int[] calculateNumberPerColor(int[] percentages, int totalOfBubbles, int minimum) {
        // TODO - calcular o número de peças. Os dois primeiros (While e Black) tem que ter pelo menos minimum.
        // este código é para substituir só funciona para esta dimensão de tabuleiro
        return new int[]{2, 2, 35, 35, 35, 20, 20, 19};
    }

    /**
     * TODO - Explicar o algoritmo e comentar o código
     *
     * @param numberOfBubbles
     * @param numbersColor    número de peças para cada cor
     * @param positions       array que contém todas as posiçoes das bolhas
     * @return
     */
    private void fillBoard(int numberOfBubbles, int[] numbersColor, int[] positions) {
        for (int color = 0; color < numbersColor.length; ++color) {
            for (int numberOfColor = 0; numberOfColor < numbersColor[color]; ++numberOfColor) {
                int randomIndex = randomize.nextInt(numberOfBubbles--);
                int pos = positions[randomIndex]; // Posição a ser ocupada
                positions[randomIndex] = positions[numberOfBubbles];
                positions[numberOfBubbles] = pos;
                int line = pos / getNumberOfColumns(), column = pos % getNumberOfColumns();
                switch (color) {
                    case 0 -> putBubble(new White(this, line, column));
                    case 1 -> putBubble(new Black(this, line, column));
                    default -> {
                        if (color < numbersColor.length - 3)
                            putBubble(new CrossBubble(this, line, column, color));
                        else
                            putBubble(new DiagonalBubble(this, line, column, color));
                    }
                }
            }
        }
    }
}
