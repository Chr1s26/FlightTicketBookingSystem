package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DTO.User;
import Dao.UserPolicyDaoImpl;
import Model.UserPolicy;
import Service.AuthenticationService;
import Service.SignUpService;
import exception.IncorrectPasswordException;
import exception.IncorrectUserNameException;
import exception.SignUpException;

public class SignUpPage {
	private JFrame frame;
	private JLabel userLablel;
	private JTextField userTextField;
	
	private JLabel passLabel;
	private JPasswordField passField;
	
	private JLabel confirmPassLabel;
	private JPasswordField confirmPassField;
	
	private JLabel emailLabel;
	private JTextField emailField;
	
	private JButton signUpButton;
	private JButton resetButton;
	
	private JComboBox<UserPolicy> policyComboBox;
	private JLabel policyLabel;
	
	private UserPolicyDaoImpl userPolicyDao;
	private SignUpService signUpService;
	private UserPolicy selectedUserPolicy;

	public SignUpPage() {
		initializeComponents();
		setupLayout();
		setUpActionPolicyComboBox();
		setupsignUpButtonAction();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void setupsignUpButtonAction() {
		this.signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = userTextField.getText();
				String password = new String(passField.getPassword());
				String confirmPassword = new String(confirmPassField.getPassword());
				String email = new String(emailField.getText());
				DTO.User user = new User(username,password,confirmPassword,email,selectedUserPolicy);
			
				try {
					signUpService.call(user);
					JOptionPane.showMessageDialog(frame,"User successfully created!!");
					new LoginWindow();
				}
				
				catch (SignUpException e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage());
				}
				
				} 
			}
		);
}
	
	private void setUpActionPolicyComboBox() {
		this.policyComboBox.addActionListener(e -> policyComboBoxAction());
	}
	
	public void policyComboBoxAction() {
		 this.selectedUserPolicy = (UserPolicy)this.policyComboBox.getSelectedItem();
	}

	private void initializeComponents() {
		this.userPolicyDao = new UserPolicyDaoImpl();
		this.signUpService = new SignUpService();
		
		this.frame = new JFrame("Login Window");
		this.userLablel = new JLabel("Username:");
		this.userTextField = new JTextField(15);
		
		this.passLabel = new JLabel("Password:");
		this.passField = new JPasswordField(15);
		
		this.confirmPassLabel = new JLabel("Confirm Password");
		this.confirmPassField = new JPasswordField(15);
		
		this.emailLabel = new JLabel("Email");
		this.emailField = new JTextField(20);
		
		this.policyLabel = new JLabel("Select a role");
		this.policyComboBox = new JComboBox<UserPolicy>(this.userPolicyDao.getAll().toArray(new UserPolicy[0]));
		
		this.signUpButton = new JButton("Sign Up");
		this.resetButton = new JButton("Reset");

	}

	private void setupLayout() {
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(450, 300);
		this.frame.setLayout(new GridBagLayout());
		this.frame.setTitle("Sign Up");

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
		
		frame.add(this.policyLabel,constraints);
		
		constraints.gridx = 1;
		
		frame.add(this.policyComboBox,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		
		frame.add(this.signUpButton, constraints);

		constraints.gridx = 1;

		frame.add(this.resetButton, constraints);

	}
}
