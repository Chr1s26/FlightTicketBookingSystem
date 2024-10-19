package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BaseWindow {
	public JFrame baseWindow;
	private String title;
	private String[][] tableData;
	private String[] columns;
	private JTable dataTableTemplate;
	private JScrollPane scrollPane;

	public BaseWindow() {
		initializeBaseFrame();
	}
	
	private void initializeBaseFrame() {
		this.baseWindow = new JFrame(this.title);
		this.baseWindow.setLayout(new BorderLayout());
		this.baseWindow.setLocation(200,300);
		this.baseWindow.setSize(400,400);
		this.baseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void createDataTable(String[][] data, String[] column) {
		this.tableData = data;
		this.columns = column;
		this.dataTableTemplate = new JTable(this.tableData,columns);
		this.scrollPane = new JScrollPane(this.dataTableTemplate);
		this.baseWindow.add(this.scrollPane,BorderLayout.CENTER);
	}

	public JTable getDataTableTemplate() {
		return dataTableTemplate;
	}

	public void setTitle(String title) {
		this.baseWindow.setTitle(title);
	}
}
