package com.kurs;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;


public class App 
{   
    InfoTableModel itm;
    JTable table;
    private JButton resetButton;
    private JButton saveButton;
    private JButton delButton;
    private JButton addButton;
    private JFrame frame;
    JSpinner spin1;
    JSpinner spin2;
    JSpinner spin3;
    JTextField nameField;

    private DBWorker dbw;
    
    App(File file){
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (Exception x){
                JOptionPane.showMessageDialog(frame, x.getMessage()+"\n"+x.getStackTrace(), "Сохранение невозможно", JOptionPane.ERROR_MESSAGE);
            }
        }
        dbw = new DBWorker(file);
    }

    private void createAndShowGUI(){

        frame = new JFrame("Курсовая");
        itm = new InfoTableModel();
        table = new JTable(itm);
        JPanel btmPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        ALClass al = new ALClass();

        resetButton = new JButton("Сброс");
        saveButton = new JButton("Сохранить");
        delButton = new JButton("Удалить");
        addButton = new JButton("Добавить");
        JPanel s1 = new JPanel();
        JPanel s2 = new JPanel();
        JPanel s3 = new JPanel();
        JLabel label1 = new JLabel("Мат.");
        JLabel label2 = new JLabel("Физ.");
        JLabel label3 = new JLabel("Рус.яз.");
        frame.setLayout(new BorderLayout());
        s1.add(label1,BorderLayout.NORTH);
        s2.add(label2,BorderLayout.NORTH);
        s3.add(label3,BorderLayout.NORTH);
        
        spin1 = new JSpinner(new SpinnerNumberModel(5,1,5,1));
        spin2 = new JSpinner(new SpinnerNumberModel(5,1,5,1));
        spin3 = new JSpinner(new SpinnerNumberModel(5,1,5,1));
        s1.add(spin1,BorderLayout.SOUTH);
        s2.add(spin2,BorderLayout.SOUTH);
        s3.add(spin3,BorderLayout.SOUTH);

        resetButton.setActionCommand("reset");
        resetButton.addActionListener(al);
        saveButton.setActionCommand("save");
        saveButton.addActionListener(al);
        delButton.setActionCommand("del");
        delButton.addActionListener(al);
        addButton.setActionCommand("add");
        addButton.addActionListener(al);
        nameField = new JTextField(30);
        btmPanel.add(nameField,BorderLayout.NORTH);
        btmPanel.add(s1,BorderLayout.CENTER);
        btmPanel.add(s2,BorderLayout.CENTER);
        btmPanel.add(s3,BorderLayout.CENTER);
        btmPanel.add(addButton,BorderLayout.SOUTH);
        btmPanel.add(resetButton, BorderLayout.WEST);
        btmPanel.add(saveButton, BorderLayout.WEST);
        btmPanel.add(delButton,BorderLayout.WEST);
        

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(scrollPane, BorderLayout.NORTH);
        frame.add(btmPanel, BorderLayout.SOUTH);
        
        

        frame.pack(); frame.setVisible(true);
        frame.setResizable(true);
    }

    private class ALClass implements java.awt.event.ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("save")) {
                try {
                    dbw.write();
                }
                catch (Exception x){
                    JOptionPane.showMessageDialog(frame, x.getMessage(), "Сохранение невозможно", JOptionPane.ERROR_MESSAGE);
                    x.printStackTrace();
                }
                return;
            }
            if (e.getActionCommand().equals("reset")) {
                try {
                    dbw.read();
                    itm.fireTableDataChanged();
                }
                catch (Exception x){
                    JOptionPane.showMessageDialog(frame, x.getMessage(), "Открытие невозможно", JOptionPane.ERROR_MESSAGE);
                    x.printStackTrace();
                }
                return;
            }
            if (e.getActionCommand().equals("del")) {
                dbw.removeMarkedEntries(true);
                itm.fireTableDataChanged();
                return;
            }
            if (e.getActionCommand().equals("add")) {
                ArrayList<Integer> marks = new ArrayList<Integer>();
                marks.add((Integer)(spin1.getValue()));
                marks.add((Integer)(spin2.getValue()));
                marks.add((Integer)(spin3.getValue()));
                dbw.addEntry(nameField.getText(),marks);
                itm.fireTableDataChanged();
                return;
            }
        }

    };

    public class InfoTableModel extends DefaultTableModel{
        String[] columnNames = {"ID","Имя","Математика","Физика",
                                "Русский язык","Средний балл","Метка"};
        
        
        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }
    
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }
    
        @Override
        public int getRowCount() {
            return dbw.getList().size();
        }

        public Class<?> getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
    
    
        @Override
        public Object getValueAt(int row, int col) {
            Info entry = dbw.getList().get(row);
            switch (col){
                case 0: return entry.getID();
                case 1: return entry.getName();
                case 2: return entry.getOcenka().get(0);
                case 3: return entry.getOcenka().get(1);
                case 4: return entry.getOcenka().get(2);
                case 5: return entry.meanOcenka();
                case 6: return entry.getMetka();
            }
            return null;
        }

        public boolean isCellEditable(int row, int col) {
            if (col==6) {
                return true;
            } else {
                return false;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            dbw.getList().get(row).setMetka((boolean)value);
            fireTableCellUpdated(row, col);
        }
 
    }

    public static void main( String[] args )
    {
        File file = new File("db.bin");
        App app = new App(file);
        app.createAndShowGUI();
    }
}
