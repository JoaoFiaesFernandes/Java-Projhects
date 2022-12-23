package trab2.grupo1;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.function.BiConsumer;

public class Window extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    private static final int LINES = 10;
    private static final int CHAR_PER_LINE = 10;


    public Window() {
        super("Expression");                   // definir o nome da window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);                      // definir tamanho da janela

        Container content = getContentPane();
        content.setLayout(new BorderLayout());      // defenir layout como BorderLayout


        // Criar butão
        JButton evaluateButton = new JButton("evaluate");   // criar botão evaluate
        content.add(evaluateButton, BorderLayout.SOUTH);    // meter na região sul
        evaluateButton.addActionListener(e->evaluateAction(e));


        // Criar area de input de texto file name

        fileName.setBorder(new TitledBorder("file name")); // definir o titulo do text field
        fileName.setEditable(true);                        // definir se é editavel
        content.add(fileName, BorderLayout.NORTH);         // meter na região norte


        JPanel center = new JPanel(new GridLayout(1, 2));
        content.add(center, BorderLayout.CENTER);

        // Criar area de texto expression

        expression.setBorder(new TitledBorder("expression"));        // definir o titulo do text field
        expression.setEditable(false);                            // definir se é editavel
        center.add(expression, BorderLayout.WEST);                 // meter na região west


        // Criar area de texto result

        result.setBorder(new TitledBorder("result"));         // definir o titulo do text field
        result.setEditable(false);                            // definir se é editavel
        center.add(result, BorderLayout.EAST);                 // meter na região east

    }

    JTextField fileName = new JTextField(20);
    JTextArea expression = new JTextArea(LINES, CHAR_PER_LINE);
    JTextArea result = new JTextArea(LINES, CHAR_PER_LINE);

    private void evaluateAction(ActionEvent action){
        BiConsumer<String, Integer> name = (exp, res) -> {
            expression.append(exp + '\n');
            result.append(exp + res + '\n');
        };
        try{
        StreamsUtils.evaluate(fileName.getText(), name);
    }catch(IOException exception){
        JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }

    public static void main(String[] args) {
        JFrame window = new Window();
        window.setVisible(true);
    }
}
