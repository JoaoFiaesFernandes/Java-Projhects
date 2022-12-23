package trab3.bubbles;

import trab3.bubbles.pieces.Bubble;
import trab3.bubbles.strategies.Strategy;
import trab3.bubbles.strategies.StrategyGravitational;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BubbleGameFrame extends JFrame implements GameListener {
    public static final int GRID_WIDTH = 35;
    public static final Color HOLE_COLOR = Color.CYAN;
    public static ScorePanel currentgame = new ScorePanel("Current Game");
    public static ScorePanel bestgame = new ScorePanel("Best Values");
    /**
     * @Author João Fernandes
     */

    public final Itens[] crplayer = {
            new Itens("new player", e -> System.exit(0)),
            new Itens("statistics", e -> System.exit(0)),
            new Itens("clear", e -> System.exit(0))
    };
    public final Itens[] player = {
            new Itens("save", e -> System.exit(0)),
            new Itens("load", e -> System.exit(0)),
            new Itens("top 10", e -> System.exit(0))
    };
    public final Itens[] stat = {
            new Itens("gravitational", e -> System.exit(0)),
            new Itens("shifter", e -> System.exit(0))
    };
    /**
     * @Author João Fernandes
     */

    protected final JPanel board;
    protected Game game;
    public final Itens[] menuGameItens = {
            new Itens("start", e -> game.start()),
            new Itens("stop", e -> game.stop()),
            new Itens("How to play", e -> {
                try {
                    game.help();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }),
            new Itens("exit", e -> System.exit(0))
    };
    // << Implementação da inteface BubbleListener */
    protected Color[] colors = {HOLE_COLOR, Color.WHITE, Color.BLACK,
            Color.RED, Color.GREEN, Color.BLUE,
            Color.YELLOW, Color.PINK, Color.MAGENTA};
    JButton button = new JButton(); //create the button
    //ScorePanel current, best; //TODO
    //Players statistics;       //TODO
    private Timer t = new Timer(1000, this::updateTime);

    public BubbleGameFrame(String title, Game g, Strategy s) {
        super(title);
        setIconImage(new ImageIcon("src/trab3/bubbles.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container cp = this.getContentPane();

        game = g;
        game.addListener(this);
        game.setStrategy(s);
        cp.add(board = createBoard(g));


        /**
         * @Author João Fernandes
         */

        JPanel cg = new JPanel(new GridLayout(1, 2));
        cg.add(currentgame);
        cg.add(bestgame);

        //adding elements


        cp.add(cg, BorderLayout.NORTH);


        /**
         * @Author João Fernandes
         */

        button.setText("Start");
        button.addActionListener(e -> {
            JButton b = (JButton) e.getSource(); //gets the source of the button
            System.out.println(b.getText()); //control
            if (button.getText().equals("Start")) { //if its false changes the button
                game.start(); //sets the new game function
            } else {
                game.stop();
            }
        });

        cp.add(button, BorderLayout.SOUTH); //creates the location of the button


        /**
         * @Author João Fernandes
         */

        JMenuBar mb = new JMenuBar();
        mb.add(createMenu("Game", menuGameItens));
        mb.add(createMenu("Current Player", crplayer));
        mb.add(createMenu("Players", player));
        mb.add(createMenu("Strategy", stat));
        setJMenuBar(mb);

        pack();
        setResizable(false);
    }


    // <<  Métodos auxiliares >>
    private static JMenu createMenu(String title, Itens... itens) {
        JMenu menu = new JMenu(title);
        for (Itens item : itens) {
            JMenuItem mi = item.getMenuItem();
            if (item.getAction() != null)
                mi.addActionListener(item.getAction());
            menu.add(mi);
        }
        return menu;
    }


    private static JPanel createBoard(Game game) {
        JPanel p = new JPanel(new GridLayout(game.getNumberOfLines(), game.getNumberOfColumns()));
        for (int i = 0; i < game.getNumberOfLines() * game.getNumberOfColumns(); ++i) {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(GRID_WIDTH, GRID_WIDTH));
            b.setBackground(HOLE_COLOR);
            int pos = i;
            b.addActionListener(e -> game.select(pos / game.getNumberOfColumns(), pos % game.getNumberOfColumns()));
            p.add(b);
        }

        return p;
    }

    public static void main(String[] args) {
        new BubbleGameFrame("Bubbles",
                new BubbleGame(14, 12),
                new StrategyGravitational()).setVisible(true);
    }


    /**
     * @param actionEvent
     * @Author João Fernandes
     */

    private void updateTime(ActionEvent actionEvent) {
        currentgame.setTime(game.getTime());
    }


    // Obter o componente que se encontra em determinada linha/coluna
    protected JComponent getComponent(int line, int col) {
        return (JComponent) board.getComponent(line * game.getNumberOfColumns() + col);
    }

    public void selected(Bubble b) {
        getComponent(b.getLine(), b.getColumn()).setBackground(Color.GRAY);
    }

    public void unselected(Bubble p) {
        getComponent(p.getLine(), p.getColumn()).setBackground(colors[p.getColor() + 1]);
    }

    // << Implementação da inteface GameListener >>
    public void scoreChange(Score s) {
        currentgame.setScore(s);
    }

    /**
     * @Author João Fernandes
     */
    public void gameStart(Score s) {
        String msg = String.format("Time is %d, start with %d bubbles and %d points", s.time, s.bubbles, s.points);
        JOptionPane.showMessageDialog(this, msg, "START GAME", JOptionPane.PLAIN_MESSAGE);
        scoreChange(s);
        t.start();
        button.setText("Stop");

    }

    /**
     * @Author João Fernandes
     */

    public void gameStop(Score s) {
        String msg = String.format("In %d seconds remain %d bubbles with %d points", s.time, s.bubbles, s.points);
        JOptionPane.showMessageDialog(this, msg, "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
        scoreChange(s);
        button.setText("Start");
    }

    public static class Itens {
        private JMenuItem munItem;
        private ActionListener listener;

        public Itens(String text, ActionListener al) {
            this(new JMenuItem(text), al);
        }

        public Itens(JMenuItem mi, ActionListener al) {
            munItem = mi;
            listener = al;
        }

        public JMenuItem getMenuItem() {
            return munItem;
        }

        public ActionListener getAction() {
            return listener;
        }
    }
}