package my_project.view;

import KAGO_framework.control.DatabaseController;
import my_project.control.ProgramController;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DatabaseGUI {

    private JPanel mainPanel;
    private JButton verbindungHerstellenButton;
    private JButton SQLAusführenButton;
    private JButton programmSchließenButton;
    private JTextPane outputPane;
    private JEditorPane inputPane;
    private JLabel connectionLabel;
    private DatabaseController dbController;

    private ProgramController programController;

    public DatabaseGUI(ProgramController programController, DatabaseController databaseController) {
        this.programController = programController;
        this.dbController = new DatabaseController();
        programmSchließenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                programController.closeProgram();
            }
        });
        SQLAusführenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo 3 Der eingegeben SQL-Code wird auf der Datenbank ausgeführt (Database-Controller verwenden). Dann wird entweder im Feld unter dem Button das Ergebnis angezeigt oder die Fehlermeldung bei SQL-Fehlern
                programController.getDBC().executeStatement(inputPane.getText());
                if(programController.getDBC().getCurrentQueryResult() == null){

                    outputPane.setText(programController.getDBC().getErrorMessage());

                } else{

                    StringBuilder output = new StringBuilder();
                    String array[][] = programController.getDBC().getCurrentQueryResult().getData();
                    for(int i = 0; i < array[i].length-1; i++){
                        output.append("\n");
                        for(int j = 0; j < array[j].length-1; j++){
                            output.append(array[i][j]);
                        }
                    }

                    outputPane.setText(output.toString());
                }
            }
        });
        verbindungHerstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo 1 Es wird eine SQL-Verbindung hergestellt (Database-Controller verwenden)
                programController.getDBC().connect();
                connectionLabel.setText("Connected");
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
