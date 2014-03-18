package storage;

public class SQL_Literals {

	public static String properties_XML = "src/main/java/jdbcProperties.xml";
	public static String properties_File = "src/main/java/jdbc.properties";

	public static String createUserTable = "CREATE TABLE USERS("
			+"	USER_ID     NUMBER(10), "
			+"	FIRST_NAME  VARCHAR(20), "
			+"	LAST_NAME   VARCHAR(20), "
			+"	USERNAME    VARCHAR(20), "
			+"	PASSWORD    VARCHAR(20), "
			+"	ISVERIFIED  VARCHAR(10), "
			+"	CONSTRAINT USER_ID_PK PRIMARY KEY (USER_ID) "
			+"	);";

	public static String createUserTypeTable = "CREATE TABLE USER_TYPE("
			+"TYPE_ID     NUMBER(2),"
			+"DESCRIPTION VARCHAR(20),"
			+"CONSTRAINT USER_TYPE_PK PRIMARY KEY (TYPE_ID )"
			+");";

	public static String createUserTypeBridgeTable = "CREATE TABLE USER_TYPE_BRIDGE("
			+"USER_ID   NUMBER(10), "
			+"TYPE_ID   NUMBER(2), "
			+"CONSTRAINT TYPE_ID_FK FOREIGN KEY (TYPE_ID) REFERENCES USER_TYPE(TYPE_ID), "
			+"CONSTRAINT USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID) "
			+");";

	public static String dropUserTable = "DROP TABLE USERS;";
	public static String dropUserTypeTable = "DROP TABLE USER_TYPE;";
	public static String dropUserTypeBridgeTable = "DROP TABLE USER_TYPE_BRIDGE;";

	public static String populateUserTypeTable = "INSERT INTO user_type VALUES (1,'A'); "
			+"INSERT INTO user_type VALUES (2,'B'); "
			+"INSERT INTO user_type VALUES (3,'SEM'); "	
			+"INSERT INTO user_type VALUES (4,'SH'); ";


	public static String getMaxID = "SELECT MAX(USER_ID) USER_ID FROM users";
	public static String getAllUsers = "SELECT USER_ID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD, ISVERIFIED, STATUS FROM users";
	public static String appendUser = "INSERT INTO users VALUES (?,?,?,?,?,?,?)";
	public static String verifyUser = "UPDATE USERS SET ISVERIFIED = 'true' WHERE username = ?";
	public static String changeStatus = "UPDATE users SET status = ? WHERE username = ?";
	public static String appendUserType = "INSERT INTO user_type_bridge VALUES (?,?)";

	public static String getUserByType = "SELECT u.username, type FROM users u "
			+"JOIN user_type_bridge utb ON u.user_id = utb.user_id "
			+"JOIN user_type ut ON ut.type_id = utb.type_id "
			+"WHERE ut.type_id = ?";
	
	public static String getAllUsersByType = "SELECT u.username, type FROM users u "
			+"JOIN user_type_bridge utb ON u.user_id = utb.user_id "
			+"JOIN user_type ut ON ut.type_id = utb.type_id ";
	
	public static String listUnverifiedUsers = "SELECT username FROM users WHERE isverified = 'false' ORDER BY user_id";
	public static String listUsersToBan = "SELECT username FROM users WHERE (status != 'banned' OR status is null) ORDER BY user_id";
	
	public static String listUserstoRemove = "SELECT username FROM users " +
			"WHERE (isverified = 'true'AND status != 'removed') OR (isverified = 'true'AND status is null) " +
			"ORDER BY user_id";

	public static String getLoginCredentials = "SELECT username, password FROM users";
}
