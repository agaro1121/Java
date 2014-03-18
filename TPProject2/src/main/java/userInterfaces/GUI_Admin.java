package userInterfaces;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import storage.Status;
import classInterfaces.Interface_Controller;
import classInterfaces.Interface_View;
//TODO table with ability to click on username	?????
public class GUI_Admin extends JFrame implements Interface_View {
	private Interface_Controller controller;

	private CardLayout layout = new CardLayout();

	private JButton addUser = new JButton("Add User");
	private JButton removeUser = new JButton("Remove User");
	private JButton banUser = new JButton("Ban User");
	private JButton viewLogs = new JButton("View Logs");

	private JPanel cards;
	private JPanel card1 = new JPanel();
	private JPanel card2 = new JPanel();
	private final String adminControls = "Admin Controls";
	private final String statusChange = "Status Change";

	private JLabel splash = new JLabel("Administrative Controls");
	private Font f = new Font("serif", Font.PLAIN, 24);

	private GridBagConstraints c = new GridBagConstraints();

	private JTextArea ta = new JTextArea(15,40);
	private JScrollPane userOutput = new JScrollPane(ta);

	private JButton back = new JButton("<-- Back");
	private JButton finish = new JButton("Finish");
	private JTextField username = new JTextField("Type Username...",20);

	private buttonHandler handler = new buttonHandler();

	private String command = new String();
	private String action = new String();


	public void getInitialDisplayNUserInput() {
		setTitle("Administrative Controls");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(layout);

		back.addActionListener(handler);
		finish.addActionListener(handler);

		//CARD 1
		card1.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		splash.setFont(f);
		card1.add(splash,c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		addUser.addActionListener(handler);
		card1.add(addUser,c);


		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		removeUser.addActionListener(handler);
		card1.add(removeUser,c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		banUser.addActionListener(handler);
		card1.add(banUser,c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		viewLogs.setEnabled(false);
		card1.add(viewLogs,c);

		//CARD 2
		card2.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 30;
		card2.add(back,c);

		ta.setEditable(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		card2.add(userOutput,c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		card2.add(username,c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 30;
		card2.add(finish,c);


		cards = new JPanel(new CardLayout());
		cards.add(card1,adminControls);
		cards.add(card2,statusChange);

		add(cards,"central");

		setSize(600,400);
		setVisible(true);
	}

	public void displayResult(Boolean result) {
	}

	public void setController(Interface_Controller c) {
		controller = c;
	}

	public void display(Object username){
		ta.append((String)username + "\n");
		ta.invalidate();
	}

	private class buttonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout)(cards.getLayout());
			if(e.getSource() == addUser){
				command = Status.ADD_USER;
				action = "added";
				cl.show(cards, statusChange);
				controller.getInput( (String)command);
			}
			if(e.getSource() == removeUser){
				command = Status.REMOVE_USER;
				action = "removed";
				cl.show(cards, statusChange);
				controller.getInput( (String)command);
			}
			if(e.getSource() == banUser){
				command = Status.BAN_USER;
				action = "banned";
				cl.show(cards, statusChange);
				controller.getInput( (String)command);
			}
			if(e.getSource() == back){
				cl.show(cards, adminControls);
				ta.invalidate();
			}
			if(e.getSource() == finish){
				if(command.equals(Status.ADD_USER)){
					controller.getInput(username.getText(),command);
				}else{
					controller.getInput(username.getText(), command);
				}
				JOptionPane.showMessageDialog(null, "User " + action + " successfully!");
				ta.setText("");
				ta.invalidate();
				controller.getInput( (String)command);
			}
		}
	}
}
