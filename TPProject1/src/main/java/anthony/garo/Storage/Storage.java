package anthony.garo.Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.IllegalAddException;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import anthony.garo.Main.User;

public final class Storage {
	private final static Storage storage = new Storage();

	private Integer idstat=0;

	private Element firstname = new Element("firstname");
	private Element lastname = new Element("lastname");
	private Element username = new Element("username");
	private Element password = new Element("password");
	private Element status = new Element("status");
	private Element isVerified = new Element("isVerified");
	private Attribute id = new Attribute("id",idstat.toString());



	private HashMap<String,String> login = new HashMap<String,String>();


	/*********************************************************************/
	public static Storage getInstance(){return storage;}

	private Storage(){setId();}


	public HashMap getLoginHash(){
		this.XMLToUser();
		return login;
	}

	private void setId(){//TODO setID
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("Users.xml");
			Document doc;
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
	public List<User> XMLToUser(){
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("Users.xml");
		Document doc;
		List<User> users = new ArrayList<User>();

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

	public void userToXML(User newUser) {
		File f = new File("Users.XML");
		if(f.exists()){

			try {
				SAXBuilder builder = new SAXBuilder();
				File xmlFile = new File("Users.xml");
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

				XMLOutputter xmlOutput = new XMLOutputter();

				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(doc, new FileWriter("Users.xml"));


			} catch (JDOMException e) {
				//e.printStackTrace();
			} catch (IOException e) {
				//e.printStackTrace();
			} catch (IllegalAddException e){
				//				e.printStackTrace();
			}


		}else{createXML();}
	}

	public void createXML(){

		try {
			Element users = new Element("users");
			Document doc = new Document(users);
			XMLOutputter xmlOutput = new XMLOutputter();

			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("Users.xml"));
		} catch (IOException e) {
			//e.printStackTrace();
		}

	}

	public void deleteXML(){
		File users = new File("Users.xml");
		users.delete();
	}

	public void verifyUser(String username) {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("Users.xml");
		Document doc;
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

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("Users.xml"));

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeUser(String username) {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("Users.xml");
		Document doc;
		try {
			doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			Element user = rootNode.getChild("user");
			List list = rootNode.getChildren("user");

			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
				if(node.getChildText("username").equals(username)){
					System.out.println("found it!");
					node.getChild("status").setText("removed");
					break;
				}
			}

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("Users.xml"));

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void banUser(String username) {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("Users.xml");
		Document doc;
		try {
			doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			Element user = rootNode.getChild("user");
			List list = rootNode.getChildren("user");

			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
				if(node.getChildText("username").equals(username)){
					System.out.println("found it!");
					node.getChild("status").setText("banned");
					break;
				}
			}

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("Users.xml"));

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
