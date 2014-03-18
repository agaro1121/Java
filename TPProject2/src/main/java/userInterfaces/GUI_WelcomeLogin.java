package userInterfaces;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.Controller_Register;
import main.Mediator;
import main.StatusCheck;
import storage.Status;
import classInterfaces.Interface_Controller;
import classInterfaces.Interface_View;

public class GUI_WelcomeLogin extends JFrame implements Interface_View {
	private Interface_Controller controller;

	private JTextField username = new JTextField(15);
	private JPasswordField password = new JPasswordField(15);
	private JButton register = new JButton("Register");
	private JButton login = new JButton("Login");
	private JButton exit = new JButton("Exit");

	private GridLayout layout = new GridLayout(3,1);

	private JLabel welcome = new JLabel("Welcome to my Awesome Trading Platform!!!");
	private Font f = new Font("serif", Font.PLAIN, 24);

	private JPanel loginBox= new JPanel();
	private JPanel row1 = new JPanel();
	private JPanel row2 = new JPanel();
	private JPanel row3 = new JPanel();

	private buttonHandler handler = new buttonHandler();


	private JLabel user = new JLabel("Username");
	private JLabel pw = new JLabel("Password");


	public void getInitialDisplayNUserInput() {
		setTitle("Anthony Garo's Trading Platform");
		setLayout(layout);
		setDefaultCloseOperation(EXIT_ON_CLOSE);


		login.addActionListener(handler);
		register.addActionListener(handler);
		exit.addActionListener(handler);

		row1.add(user);
		row1.add(username);

		row2.add(pw);
		row2.add(password);

		row3.add(exit);
		row3.add(register);
		row3.add(login);

		loginBox.setLayout(new GridLayout(3, 1));
		loginBox.setPreferredSize(new Dimension(100, 100));
		loginBox.add(row1);
		loginBox.add(row2);
		loginBox.add(row3);


		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(f);

		add(welcome);
		add(loginBox);

		setSize(600,400);
		setVisible(true);
	}

	public void displayResult(Boolean result) {
		if(result){
			setVisible(false);
			if(new StatusCheck().execute(username.getText()).equalsIgnoreCase(Status.ADMIN)){
				Mediator.getInstance().handle(Status.ADMIN_VIEW);
			}
		}else{
			username.setText("LOGIN FAILED");
		}
	}
	public void setController(Interface_Controller c) {controller=c;}

	private class buttonHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == login){
				controller.getInput(username.getText(), String.valueOf(password.getPassword()));
			}
			if(e.getSource() == register){
				Mediator.getInstance().handle(Controller_Register.class.getName());
				setVisible(false);
			}
			if(e.getSource() == exit){
				System.exit(0);
			}
		}
	}

	public void display(Object s) {

	}
}
