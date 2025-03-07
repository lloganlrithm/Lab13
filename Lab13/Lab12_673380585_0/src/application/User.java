package application;
//นายธนดล ไชยศิลา 673380585-0

public class User {
	
	private String Username;
	private String Email;
	
	//Constructor
	public User()
	{
		Username = "";
		Email = "";
	}
	public User(String Username, String Email)
	{
		this.Username = Username;
		this.Email = Email;
	}
	
	//Getter
	public String getUsername()
	{
		return Username;
	}
	public String getEmail()
	{
		return Email;
	}
	
	//Setter
	public void setUsername(String Username)
	{
		this.Username = Username;
	}
	public void setEmail(String Email)
	{
		this.Email = Email;
	}
}
