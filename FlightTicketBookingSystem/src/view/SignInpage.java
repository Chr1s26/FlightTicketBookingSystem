package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Service.AuthenticationService;
import exception.IncorrectPasswordException;
import exception.IncorrectUserNameException;

public class SignInpage {
	private JFrame frame;
	private JLabel userLablel;
	private JTextField userTextField;
	
	private JLabel passLabel;
	private JPasswordField passField;
	
	private JLabel confirmPassLabel;
	private JPasswordField confirmPassField;
	
	private JLabel emailLabel;
	private JTextField emailField;
	
	private JButton signInButton;
	private JButton resetButton;

	public SignInpage() {
		initializeComponents();
		setupLayout();
		setupsignInButtonAction();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void setupsignInButtonAction() {
		this.signInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = userTextField.getText();
				String password = new String(passField.getPassword());
				String confirmPassword = new String(confirmPassField.getPassword());
				if(password == confirmPassword) {
				try {
				AuthenticationService.login(username,password);
				LoginWindow homePage = new LoginWindow();
				}
				
				catch (IncorrectPasswordException e2) {
					JOptionPane.showMessageDialog(frame, e2.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				catch (IncorrectUserNameException e3) {
					JOptionPane.showMessageDialog(frame, e3.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}}
				else {
					JOptionPane.showMessageDialog(frame,"Confirm password is not same with password");
				}
				
				} 
			}
		);
}

	private void initializeComponents() {
		this.frame = new JFrame("Login Window");
		this.userLablel = new JLabel("Username:");
		this.userTextField = new JTextField(15);
		
		this.passLabel = new JLabel("Password:");
		this.passField = new JPasswordField(15);
		
		this.confirmPassLabel = new JLabel("Confirm Password");
		this.confirmPassField = new JPasswordField(15);
		
		this.emailLabel = new JLabel("Email");
		this.emailField = new JTextField(20);
		
		this.signInButton = new JButton("Sign in");
		this.resetButton = new JButton("Reset");

	}

	private void setupLayout() {
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(450, 300);
		this.frame.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 10, 10, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;

		frame.add(this.userLablel, constraints);

		constraints.gridx = 1;

		frame.add(this.userTextField, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;

		frame.add(this.passLabel, constraints);

		constraints.gridx = 1;

		frame.add(this.passField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		
		frame.add(this.confirmPassLabel,constraints);
		
		constraints.gridx = 1;
		
		frame.add(this.confirmPassField,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		
		frame.add(this.emailLabel,constraints);
		
		constraints.gridx = 1;
		
		frame.add(this.emailField,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;

		frame.add(this.signInButton, constraints);

		constraints.gridx = 1;

		frame.add(this.resetButton, constraints);

	}
}
