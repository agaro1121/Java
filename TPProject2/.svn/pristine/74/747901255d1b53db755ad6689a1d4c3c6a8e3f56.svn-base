package userInterfaces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import storage.DTO;
import classInterfaces.Interface_Controller;
import classInterfaces.Interface_View;

public class GUI_Register extends JFrame implements Interface_View{
	private Interface_Controller controller;

	private JPanel firstName = new JPanel();
	private JPanel lastName = new JPanel();
	private JPanel password = new JPanel();

	private JTextField fn = new JTextField(15);
	private JTextField ln = new JTextField(15);
	private JPasswordField pw = new JPasswordField(15);

	private JButton submit = new JButton("Submit");

	private GridBagLayout layout = new GridBagLayout();;
	private GridBagConstraints c = new GridBagConstraints();

	private buttonHandler handler = new buttonHandler();

	private JLabel fname = new JLabel("First Name");
	private JLabel lname = new JLabel("Last Name");
	private JLabel passw = new JLabel("Password ");

	private DTO newUser = new DTO();

	private JPanel checkBoxes = new JPanel();
	private JCheckBox admin = new JCheckBox("Admin");
	private JCheckBox share_holder = new JCheckBox("Share Holder");
	private JCheckBox se_manager = new JCheckBox("Stock Exchange Manager");
	private JCheckBox broker = new JCheckBox("Broker");


	public void getInitialDisplayNUserInput() {
		setTitle("Registration");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(layout);

		submit.addActionListener(handler);

		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		firstName.add(fname);
		firstName.add(fn);
		add(firstName,c);

		c.gridx = 0;
		c.gridy = 1;
		lastName.add(lname);
		lastName.add(ln);
		add(lastName,c);


		c.gridx = 0;
		c.gridy = 2;
		password.add(passw);
		password.add(pw);
		add(password,c);

		c.fill = GridBagConstraints.SOUTHEAST;
		c.gridx = 1;
		c.gridy = 4;
		add(submit,c);

		admin.addItemListener(handler);
		share_holder.addItemListener(handler);
		broker.addItemListener(handler);
		se_manager.addItemListener(handler);

		checkBoxes.setLayout(new GridLayout(0,1));
		checkBoxes.add(admin);
		checkBoxes.add(share_holder);
		checkBoxes.add(broker);
		checkBoxes.add(se_manager);
		c.fill = GridBagConstraints.SOUTHEAST;
		c.gridx = 0;
		c.gridy = 3;
		add(checkBoxes,c);

		setSize(600,400);
		setVisible(true);

	}

	public void setController(Interface_Controller c) {this.controller=c;}	

	public void displayResult(Boolean result) {
	}

	private class buttonHandler implements ActionListener, ItemListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == submit){
				newUser.firstname = fn.getText();
				newUser.lastname = ln.getText();
				newUser.password = String.valueOf(pw.getPassword());

				if(newUser.firstname.isEmpty() || newUser.lastname.isEmpty() || newUser.password.isEmpty() ){	
					if(newUser.firstname.isEmpty()){
						fn.setText("ENTER FIRST NAME!!");
					}
					if(newUser.lastname.isEmpty()){
						ln.setText("ENTER LAST NAME!!");
					}
					JOptionPane.showMessageDialog(null, "Fill in all fields!!", "Missing Informaion", JOptionPane.ERROR_MESSAGE);
				}else{
					controller.getInput(newUser);
					JOptionPane.showMessageDialog(null, "Registration Successful!!\nYou will hear from an Administrator Soon");
					System.exit(0);
				}
			}
		}

		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if(e.getSource() == admin){
					newUser.isAdmin = true;
				}
				if(e.getSource() == share_holder){
					newUser.isShare_Holder = true;
				}
				if(e.getSource() == se_manager){
					newUser.isSE_Manager = true;
				}
				if(e.getSource() == broker){
					newUser.isBroker = true;
				}
			}
			if (e.getStateChange() == ItemEvent.DESELECTED) {
				if(e.getSource() == admin){
					newUser.isAdmin = false;
				}
				if(e.getSource() == share_holder){
					newUser.isShare_Holder = false;
				}
				if(e.getSource() == se_manager){
					newUser.isSE_Manager = false;
				}
				if(e.getSource() == broker){
					newUser.isBroker = false;
				}
			}
		}
	}

	public void display(Object s) {

	}
}
