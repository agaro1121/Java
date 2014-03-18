package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.IllegalAddException;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import classInterfaces.Interface_Storage;


public final class Storage_XML implements Interface_Storage{
	private Integer idstat=0;
	private Element firstname = new Element("firstname");
	private Element lastname = new Element("lastname");
	private Element username = new Element("username");
	private Element password = new Element("password");
	private Element status = new Element("status");
	private Element isVerified = new Element("isVerified");
	private Attribute id = new Attribute("id",idstat.toString());

	private Map<String,String> login = new HashMap<String,String>();

	private SAXBuilder builder = new SAXBuilder();
	private File xmlFile = new File("Users.xml");
	private Document doc;
	private List<User> users = new ArrayList<User>();

	private XMLOutputter xmlOutput = new XMLOutputter();

	/********************************** Methods ************************************/
	private final static Storage_XML storage = new Storage_XML();
	public static Storage_XML getInstance(){return storage;}

	private Storage_XML(){
		setID();
	}


	public Map<String, String> getLoginHash(){
		this.readAllUsers();
		return login;
	}

	public void setID(){
		try {
			doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			List list = rootNode.getChildren("user");
			idstat=list.size();

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public List<User> readAllUsers(){
		try {
			doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			List list = rootNode.getChildren("user");

			for (int i = 0; i < list.size(); i++) {
				User temp = new User();
				Element node = (Element) list.get(i);
				temp.setFirstname(node.getChildText("firstname"));
				temp.setLastname(node.getChildText("lastname"));
				temp.setUsername(node.getChildText("username"));
				temp.setPassword(node.getChildText("password"));
				temp.setStatus(node.getChildText("status"));
				temp.setIsVerified(new Boolean(node.getChildText("isVerified")));
				users.add(temp);
				login.put(node.getChildText("username"),node.getChildText("password"));	
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}


	public void appendUser(User newUser) {
		if(!xmlFile.exists()){
			createXML();
		}
		try {
			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			Element user = new Element("user");
			idstat++;
			user.setAttribute(id.setValue(idstat.toString()));
			user.addContent(firstname.setText(newUser.getFirstname()));
			user.addContent(lastname.setText(newUser.getLastname()));
			user.addContent(username.setText(newUser.getUsername().concat(idstat.toString())));
			user.addContent(password.setText(newUser.getPassword()));
			user.addContent(status.setText(newUser.getStatus()));
			user.addContent(isVerified.setText(newUser.getIsVerified().toString()));
			rootNode.addContent(user);

			user = null;

			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("Users.xml"));

		} catch (JDOMException e) {
		} catch (IOException e) {
		} catch (IllegalAddException e){}
	}



	private void createXML(){
		try {
			Element users = new Element("users");
			Document doc = new Document(users);
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("Users.xml"));
		} catch (IOException e) {}
	}

	//	private void deleteXML(){
	//		File users = new File("Users.xml");
	//		users.delete();
	//	}


	public void verifyUser(String username) {
		try {
			doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			Element user = rootNode.getChild("user");
			List list = rootNode.getChildren("user");

			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
				if(node.getChildText("username").equals(username)){
					System.out.println("found it!");
					node.getChild("isVerified").setText("true");
					break;
				}
			}

			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("Users.xml"));

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void changeStatus(String username, String newStatus) {
		try {
			doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			Element user = rootNode.getChild("user");
			List list = rootNode.getChildren("user");

			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
				if(node.getChildText("username").equals(username)){
					System.out.println("found it!");
					node.getChild("status").setText(newStatus);
					break;
				}
			}

			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("Users.xml"));

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, String> getUserByType(int type) {
		return null;
	}

	public List<String> getUserByStatus(String command) {
		return null;
	}
}
