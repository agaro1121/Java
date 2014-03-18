package storage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class DataTransfer {
	private String insert = "INSERT INTO users VALUES (?,?,?,?,?,?,?)";
	private PreparedStatement generalStatement;
	private Connection connection;

	private SAXBuilder builder = new SAXBuilder();
	private File xmlFile = new File("Users.xml");
	private Document doc;

	public void transferData(){
		try {
			doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			List list = rootNode.getChildren("user");

			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(insert);

			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);

				generalStatement.setString(1,node.getAttributeValue("id"));
				generalStatement.setString(2, node.getChildText("firstname"));
				generalStatement.setString(3, node.getChildText("lastname"));
				generalStatement.setString(4, node.getChildText("username"));
				generalStatement.setString(5, node.getChildText("password"));
				generalStatement.setString(6, (node.getChildText("isVerified")));
				generalStatement.setString(7, node.getChildText("status"));

				generalStatement.execute();
			}
			connection.commit();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}
	}

	public static void main(String[] args){
		DataTransfer dt = new DataTransfer();
		dt.transferData();
	}
}
