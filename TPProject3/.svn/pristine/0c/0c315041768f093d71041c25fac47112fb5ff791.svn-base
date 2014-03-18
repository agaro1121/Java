package userInterfaces;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import storage.AppLiterals;
import classInterfaces.iController;
import classInterfaces.iView;
public class GUI_Admin extends JFrame implements iView {
	private iController controller;

	private CardLayout layout = new CardLayout();

	private JButton addUser = new JButton("Add User");
	private JButton removeUser = new JButton("Remove User");
	private JButton banUser = new JButton("Ban User");
	private JButton viewLogs = new JButton("View Logs");
	private JButton viewRequests = new JButton("View User Requests");
	private JButton back = new JButton("<-- Back");
	private JButton back2 = new JButton("<-- Back");
	private JButton finish = new JButton("Finish");

	private JPanel cards;
	private JPanel card1 = new JPanel();
	private JPanel card2 = new JPanel();
	private JPanel card3 = new JPanel();

	private final String adminControls = "Admin Controls";
	private final String statusChange = "Status Change";
	private final String userRequests = "User Requests";
	private String command = new String();
	private String action = new String();

	private JLabel splash = new JLabel("Administrative Controls");
	private Font f = new Font("serif", Font.PLAIN, 24);

	private JTextArea outputTextArea = new JTextArea(15,40);
	private JScrollPane userOutput = new JScrollPane(outputTextArea);


	private JTextField username = new JTextField("Type Username...",20);

	private ButtonHandler handler = new ButtonHandler();

	private GridBagConstraints c = new GridBagConstraints();

	/********************************* NEW STUFF ***********************************************/
	private JTable table;
	private JTableHeader header;
	private DefaultTableModel tableModel;
	private String[] status = {"New","In Progress","Complete"};
	private JComboBox statuses = new JComboBox(status);
	private List<Object[]> initialData = new ArrayList<Object[]>();
	private List<String> columnNames = new ArrayList<String>();
	private Object[][] data;
	private JButton submit = new JButton("Submit Change");
	private JScrollPane tableOutput;

	private Object requestStatus;
	private int request_id;
	/******************************************************************************************/

	public void getInitialDisplayNUserInput() {
		setTitle("Administrative Controls");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLayout(layout);

		back.addActionListener(handler);
		finish.addActionListener(handler);

		/******************** CARD 1 **************************/
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

		viewRequests.addActionListener(handler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		viewLogs.setEnabled(false);
		card1.add(viewRequests,c);


		/******************** CARD 2 **************************/
		card2.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 30;
		card2.add(back,c);

		outputTextArea.setEditable(false);
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

		/******************** CARD 3 **************************/
		card3.setLayout(new GridBagLayout());
		data = new Object[initialData.size()][];
		data = initialData.toArray(data);

		tableModel = new DefaultTableModel(initialData.toArray(data), columnNames.toArray()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 5;
			}
		};

		table = new JTable(tableModel);
		table.setGridColor(Color.black);
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(1).setPreferredWidth(2);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(2);
		table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(statuses));
		table.getModel().addTableModelListener((TableModelListener) handler);
		header = table.getTableHeader();
		header.setBackground(Color.LIGHT_GRAY);

		tableOutput = new JScrollPane(table);
		tableOutput.setPreferredSize(new Dimension(450,325));	

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;	
		card3.add(tableOutput,c);

		submit.addActionListener(handler);
		c.fill = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 1;
		card3.add(submit,c);

		back2.addActionListener(handler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		card3.add(back2,c);

		/*********************************************************/

		cards = new JPanel(new CardLayout());
		cards.add(card1,adminControls);
		cards.add(card2,statusChange);
		cards.add(card3,userRequests);

		add(cards,"central");

		setSize(600,400);
		setVisible(true);
	}

	public void setTableData(List<Object[]> data,List<String> columnNames){
		this.initialData = data;
		this.columnNames = columnNames;
	}

	public void setVisibility(Boolean show) {
		setVisible(show);
	}

	public void setController(iController c) {
		controller = c;
	}

	public void display(Object username){
		outputTextArea.append((String)username + "\n");
		outputTextArea.invalidate();
	}

	public void refreshTable(){
		tableModel.fireTableDataChanged();
	}
	
	private class ButtonHandler implements ActionListener,TableModelListener{
		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout)(cards.getLayout());
			if(e.getSource() == addUser){
				command = AppLiterals.ADD_USER;
				action = "added";
				cl.show(cards, statusChange);
				controller.getInput( (String)command);
			}
			if(e.getSource() == removeUser){
				command = AppLiterals.REMOVE_USER;
				action = "removed";
				cl.show(cards, statusChange);
				controller.getInput( (String)command);
			}
			if(e.getSource() == banUser){
				command = AppLiterals.BAN_USER;
				action = "banned";
				cl.show(cards, statusChange);
				controller.getInput( (String)command);
			}
			if(e.getSource() == back || e.getSource() == back2){
				cl.show(cards, adminControls);
				outputTextArea.invalidate();
			}
			if(e.getSource() == viewRequests){
				controller.getRequestData();
				cl.show(cards, userRequests);
			}
			if(e.getSource() == finish){
				controller.getInput(username.getText(),command);
				JOptionPane.showMessageDialog(null, "User " + action + " successfully!");
				outputTextArea.setText("");
				outputTextArea.invalidate();
				username.setText("");
				controller.getInput( (String)command);
			}
			if(e.getSource() == submit){
				controller.updateRequest(request_id, requestStatus.toString());
				if(requestStatus.toString().equals("Complete")){
					try {
						ImageIcon icon = new ImageIcon(new URL("http://localhost:8088/TPProject3/images/ErrorGangnam.jpg"));
						JOptionPane.showMessageDialog(null,null, "Easter Egg :)", JOptionPane.INFORMATION_MESSAGE, icon);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
				}
				controller.getRequestData();
			}
		}
		public void tableChanged(TableModelEvent e) {
			int row = e.getFirstRow();
			int column = e.getColumn();
			TableModel model = (TableModel)e.getSource();
			requestStatus = model.getValueAt(row, column).toString();
			request_id = Integer.parseInt(model.getValueAt(row, 0).toString());
		}
	}
}
