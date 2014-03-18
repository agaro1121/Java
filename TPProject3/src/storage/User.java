package storage;

import java.util.Date;

public class User {

	private int userID;
	private String password;
	private String firstname;
	private String lastname;
	private String username;

	private String country;
	private String streetNumber;
	private String city;
	private String postalcode;

	private String homeNumber;
	private String cellNumber;
	private String email;

	private String permissions;
	private boolean isVerified = false;

	private Date dateRegistered;
	private Date verified;

	private String status;

	private boolean isAdmin = false;
	private boolean isShare_Holder = false;
	private boolean isSE_Manager = false;
	private boolean isBroker = false;

/** Constructors **/
	public User(){}
//	public User(int userID,String firstname){
//		this.userID = userID;
//		this.firstname = firstname;
//	}
//	public User(int userID,String firstname,String lastname){
//		this.userID = userID;
//		this.firstname = firstname;
//		this.lastname = lastname;
//	}
//	public User(int userID,String firstname,String lastname,String password){
//		this.userID = userID;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.password = password;
//	}
	public User(int userID,String firstname,String lastname,String password,String username){
		this.userID = userID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}
	/*********************************************************/
	@Override
	public String toString(){
		return username;
	}

	/***********************  GETTERS  ***********************************/
	public int getUserID() {
		return userID;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getCountry() {
		return country;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public String getCity() {
		return city;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public String getHomeNumber() {
		return homeNumber;
	}
	public String getCellNumber() {
		return cellNumber;
	}
	public String getEmail() {
		return email;
	}
	public String getPermissions() {
		return permissions;
	}
	public boolean isVerified() {
		return isVerified;
	}
	public Date getDateRegistered() {
		return dateRegistered;
	}
	public Date getVerified() {
		return verified;
	}
	public String getStatus() {
		return status;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public boolean isShare_Holder() {
		return isShare_Holder;
	}
	public boolean isSE_Manager() {
		return isSE_Manager;
	}
	public boolean isBroker() {
		return isBroker;
	}

	/***********************  SETTERS  ***********************************/
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	public void setVerified(boolean isVerified){
		this.isVerified = isVerified;
	}
	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}
	public void setVerified(Date verified) {
		this.verified = verified;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public void setShare_Holder(boolean isShare_Holder) {
		this.isShare_Holder = isShare_Holder;
	}
	public void setSE_Manager(boolean isSE_Manager) {
		this.isSE_Manager = isSE_Manager;
	}
	public void setBroker(boolean isBroker) {
		this.isBroker = isBroker;
	}
}
