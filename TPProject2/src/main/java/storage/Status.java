package storage;

import main.Controller_Admin;
import main.Controller_Login;
import main.Controller_Register;

import commands.Command_AddUser;
import commands.Command_BanUser;
import commands.Command_RemoveUser;

public class Status {

	public static final String REMOVED = new String("removed");
	public static final String BANNED = new String("banned");
	public static final String ADMIN = new String("admin");
	public static final String SHARE_HOLDER = new String("share_holder");
	public static final String BROKER = new String("broker");
	public static final String SE_MANAGER = new String("se_manager");
	
	public static final String LOGIN = new String("login");
	public static final String REGISTER = new String("register");
	public static final String WELCOME = new String("welcome");
	
	public static final String ADMIN_VIEW = new String(Controller_Admin.class.getName());
	public static final String WELCOME_VIEW = new String(Controller_Login.class.getName());
	public static final String REGISTER_VIEW = new String(Controller_Register.class.getName());
	
	public static final String ADD_USER = new String(Command_AddUser.class.getName());
	public static final String REMOVE_USER = new String (Command_RemoveUser.class.getName());
	public static final String BAN_USER = new String (Command_BanUser.class.getName());
	
	public static final String SWING = new String("gui");
	public static final String CONSOLE = new String("console");
}
