package trab2.grupo2.views;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @Author: 47597 - João Fernandes
 */


public class txtFrame extends JFrame {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 290;

    private static final String DEFAULT_FOLDER = "C:/Users/admin/Desktop";

    private static final int LINES = 10;
    private static final int CHAR_PER_LINE = 10;

    JTextArea list = new JTextArea(LINES, CHAR_PER_LINE);
    JTextField surname = new JTextField(0);
    JTextField name = new JTextField(0);

    private String namesave = "";
    private String surnamesave = "";

    public txtFrame() {
        super("Families"); // defining the name of the window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT); //window size
        setResizable(false);

        Container content = getContentPane();
        content.setLayout(new BorderLayout()); //BorderLayout


        /**
         * @Author: 47597 - João Fernandes
         */


        //creates the zone for the name and surname
        JPanel top = new JPanel(new GridLayout(1, 1));
        content.add(top, BorderLayout.NORTH);

        //CREATES THE DISPLAY AREA
        list.setBorder(new TitledBorder("list")); // name of the displayed data
        list.setEditable(false); //not editable



        //create the scroll
        JScrollPane s = new JScrollPane(list);
        s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        top.add(s, BorderLayout.LINE_END);


        /**
         * @Author: 47597 - João Fernandes
         */


        //creates the zone for the name and surname
        JPanel center = new JPanel(new GridLayout(1, 3));
        content.add(center, BorderLayout.CENTER);

        // creating button
        JButton button = new JButton("add"); //create the button
        content.add(button, BorderLayout.LINE_END);

        //surname area input
        surname.setBorder(new TitledBorder("surname"));
        surname.setEditable(true); //editable
        center.add(surname, BorderLayout.WEST);

        //name area input
        name.setBorder(new TitledBorder("name"));
        name.setEditable(true); //editable
        center.add(name, BorderLayout.EAST);


        /**
         * @Author: 47597 - João Fernandes
         */

        //creates the menus
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenuItem load = new JMenuItem("load");

        JMenu save = new JMenu("Save");
        JMenuItem names = new JMenuItem("names");
        JMenuItem families = new JMenuItem("families");

        JMenuItem clear = new JMenuItem("clear");
        JMenuItem exit = new JMenuItem("exit");

        file.add(load); //reads the file names
        file.add(save);

        save.add(names);
        save.add(families);

        file.add(clear);
        file.add(exit);


        /**
         * @Author: 47597 - João Fernandes
         */

        JMenu info = new JMenu("Info");
        menuBar.add(info);
        JMenuItem name = new JMenuItem("names");
        JMenuItem surnames = new JMenuItem("surnames");
        JMenuItem greater_families = new JMenuItem("greater families");
        JMenuItem family = new JMenuItem("family");
        JMenuItem familie = new JMenuItem("families");
        info.add(name);
        info.add(surnames);
        info.add(greater_families);
        info.add(family);
        info.add(familie);


        /**
         * @Author: 47597 - João Fernandes
         */


        //Action Events

        //-------- LIST ------------



        //-------- FILE ----------------

        names.addActionListener(this::savefile);
        families.addActionListener(this::savefile);
        load.addActionListener(this::loadfile);
        clear.addActionListener(this::clear);
        exit.addActionListener(this::exit);
/*
        //-------INFO--------------
        name.addActionListener(this::getNames);
        surnames.addActionListener(this::getSurnames);
        greater_families.addActionListener(this::getGreaterFamilies);
        family.addActionListener(this::printFamilies);
        familie.addActionListener(this::printFamilies);
*/
        setJMenuBar(menuBar);

    }

    /**
     * @Author: 47597 - João Fernandes
     */

    private void loadfile(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(DEFAULT_FOLDER);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt File", "txt");
        chooser.setFileFilter(filter);
        int returnval = chooser.showOpenDialog(null);
        if (returnval == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }
        try(FileReader r = new FileReader(chooser.getSelectedFile())){

        }
        catch (IOException ignored){

        }


    }

    /**
     * @Author: 47597 - João Fernandes
     */

    //saves the data
    //still need to implement the save function
    private void savefile(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(DEFAULT_FOLDER);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt File", "txt");
        chooser.setFileFilter(filter);



        int returnval = chooser.showSaveDialog(null);
        if (returnval == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to save this file: " +
                    chooser.getSelectedFile().getName());

            try (FileWriter w = new FileWriter(chooser.getSelectedFile())) {

            }
            catch (IOException ignored) {

            }
        }

    }

    /**
     * @Author: 47597 - João Fernandes
     */

    //clears the JTextArea
    public void clear(ActionEvent e) {
        list.setText("");
    }

    /**
     * @Author: 47597 - João Fernandes
     */

    //exits the program
    public void exit(ActionEvent e) {
        System.exit(0);
    }
}
