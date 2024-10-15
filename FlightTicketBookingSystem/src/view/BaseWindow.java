package view;

import javax.swing.*;
import java.awt.*;

public class BaseWindow {
    public JFrame baseWindow;
    private String[][] tableData;
    private String[] columns;


    private String title;



    private JTable dataTableTample;
    private JScrollPane scrollPane;


    public BaseWindow(){
        initializeBaseFrame();
    }

    private void initializeBaseFrame(){
        this.baseWindow = new JFrame(this.title);
        this.baseWindow.setLayout(new BorderLayout());
        this.baseWindow.setLocation(200, 300);
        this.baseWindow.setSize(400, 400);
        this.baseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void createDataTable(String[][] data, String[] columns){
        this.tableData = data;
        this.columns = columns;
        this.dataTableTample = new JTable(this.tableData, this.columns);
        this.scrollPane = new JScrollPane(this.dataTableTample);
        this.baseWindow.add(this.scrollPane, BorderLayout.CENTER);
    }
    public void setTitle(String title) {
       this.baseWindow.setTitle(title);
    }

    public JTable getDataTableTample() {
        return dataTableTample;
    }
}
