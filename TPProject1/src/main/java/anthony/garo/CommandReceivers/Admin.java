package anthony.garo.CommandReceivers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import Interfaces.Receiver;
import anthony.garo.Main.User;
import anthony.garo.Storage.Storage;

public class Admin implements Receiver{
	private Logger admin = Logger.getLogger("AdminLog");
	private String username;

	public void removeUser(){
		this.displayUsers("verified");

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.print("Enter username to be removed: ");
			username = input.readLine();
			Storage.getInstance().removeUser(username);
			admin.info(username + " was REMOVED");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addUser(){
		this.displayUsers("unverified");

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.print("Enter username to be verified: ");
			username = input.readLine();
			Storage.getInstance().verifyUser(username);
			admin.info(username + " was VERIFIED");
		} catch (IOException e) {
			e.printStackTrace();
		}		

	}

	public void banUser(){
		this.displayUsers("all");

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.print("Enter username to be banned: ");
			username = input.readLine();
			Storage.getInstance().banUser(username);
			admin.info(username + " was BANNED");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void displayUsers(String type){
		List<User> users = new ArrayList<User>();
		users = Storage.getInstance().XMLToUser();

		if(type.equalsIgnoreCase("verified")){
			for (User user : users) {
				if(user.getIsVerified() && !user.getStatus().equals("removed")){
					System.out.println(user);
				}
			}
		}

		else if(type.equalsIgnoreCase("unverified")){
			for (User user : users) {
				if(!user.getIsVerified()){
					System.out.println(user);
				}
			}

		}
		
		else if(type.equals("all")){
			for (User user : users) {
				System.out.println(user);
			}
		}
	}

}
