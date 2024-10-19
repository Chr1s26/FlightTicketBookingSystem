package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutFocusTraversalPolicy;

import Dao.CustomerDaoImpl;
import Dao.TicketDaoImpl;
import Model.Customer;
import Model.Ticket;

public class TicketUpdateForm extends BaseWindow {
	private JLabel ticketIdLabel;
	private JTextField ticketIdValue;

	private JLabel customerLabel;
	private JLabel scheduleLabel;
    private JLabel seatLabel;

	private JButton createButton;
	private JButton cancelButton;
    private Ticket ticket;
	private JPanel panel;
    private TicketListingPage parentPage;
	private TicketDaoImpl ticketDao;

	public TicketUpdateForm(TicketListingPage parentPage, int ticketId) {
        this.ticketDao = new TicketDaoImpl();
        this.ticket = this.ticketDao.getById(ticketId);
        this.parentPage = parentPage;
//		initializeComponent();
		prepareBaseWindow();
	}

//	public void initializeComponent() {
//		ticketIdLabel = new JLabel("Ticket Id");
//		NameLabel = new JLabel("Customer Name");
//
//		createButton = new JButton("Create");
//		cancelButton = new JButton("Cancel");
//
//		panel = new JPanel();
//		panel.setLayout(new GridLayout(4,4));
//		panel.add(customerIdLabel);
//		panel.add(customerIdValue);
//		panel.add(customerNameLabel);
//		panel.add(customerNameValue);
//		panel.add(customerEmailLabel);
//		panel.add(customerEmailValue);
//		panel.add(createButton);
//		panel.add(cancelButton);
//
//		addActionOnUpdateButton();
//		this.baseWindow.add(panel,BorderLayout.NORTH);
//	}

	public void addActionOnUpdateButton() {
		this.createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(baseWindow, "Successfully created Ticket !!!");
			}
		});
	}

	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Ticket Information");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
}
