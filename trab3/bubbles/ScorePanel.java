package trab3.bubbles;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * @Author João Fernandes
 */

public class ScorePanel extends JPanel {
    private JTextField time, bubbles, score;

    public ScorePanel(String st){
        setBorder(new TitledBorder(st));
        time = makeTextField("time");
        bubbles = makeTextField("bubbles");
        score = makeTextField("score");
    }

    public JTextField makeTextField(String title){
        JTextField t = new JTextField("", 5);
        t.setBorder(new TitledBorder(title));
        t.setEditable(false);
        t.setOpaque(false);
        this.add(t);
        return t;
    }

    public void setScore( Score s ) {
        bubbles.setText(s.bubbles + "");
        score.setText(s.points + "");
        setTime(s.time);
    }
    public void setTime( int t ) {
        time.setText(t + "");
    }
}
