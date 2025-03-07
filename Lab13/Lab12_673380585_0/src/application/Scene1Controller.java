package application;
//นายธนดล ไชยศิลา 673380585-0

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.BufferedWriter;

public class Scene1Controller implements Initializable {
	
	protected Stage stage;
	protected Scene scene;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private TextField emailField;
	
	private TreeMap<String, User> users = new TreeMap<>();
	
	public void firstEntry(ActionEvent event) throws IOException 
	{
		if(users.firstEntry() != null)
		{
			usernameField.setText(users.get(users.firstKey()).getUsername());	
			emailField.setText(users.get(users.firstKey()).getEmail());
		}		
	}

	public void lowerEntry(ActionEvent event) throws IOException {
		if(users.lowerEntry(usernameField.getText()) != null)
		{
			User user = users.get(users.lowerKey(usernameField.getText()));
	        if (user != null) {
	            usernameField.setText(user.getUsername());
	            emailField.setText(user.getEmail());
	        }
		}
	}

	public void higherEntry(ActionEvent event) throws IOException {
		if(users.higherEntry(usernameField.getText()) != null)
		{
			User user = users.get(users.higherKey(usernameField.getText()));
	        if (user != null) {
	            usernameField.setText(user.getUsername());
	            emailField.setText(user.getEmail());
	        }
		}
	}

	public void lastEntry(ActionEvent event) throws IOException {
		if(users.lastEntry() != null)
		{
			usernameField.setText(users.get(users.lastKey()).getUsername());	
			emailField.setText(users.get(users.lastKey()).getEmail());
		}
	}
	
	@FXML
	public void Submit(ActionEvent event)
	{
		String content = usernameField.getText() + "," + emailField.getText() + "\n";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\Levto\\Program\\OOP\\Lab12_673380585_0\\src\\application\\data.db",true));) {
			writer.append(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void switchToScene(ActionEvent event) throws IOException
	{
		try {
			BufferedReader reader = new BufferedReader(new FileReader("E:\\Levto\\Program\\OOP\\Lab12_673380585_0\\src\\application\\data.db"));
			String line;
			String[] field;
			User user;
			
			while((line=reader.readLine())!= null)
			{
				field = line.split(",");
				user = new User(field[0],field[1]);
				users.put(field[0], user);
			}
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
			AnchorPane root = loader.load();
			
			Scene2Controller scene2Controller = loader.getController();
			scene2Controller.setList(users);
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setList(TreeMap<String, User> users) 
	{
		this.users = users;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		try {
			BufferedReader reader = new BufferedReader(new FileReader("E:\\Levto\\Program\\OOP\\Lab12_673380585_0\\src\\application\\data.db"));
			String line;
			String[] field;
			User user;
			
			while((line=reader.readLine())!= null)
			{
				field = line.split(",");
				user = new User(field[0],field[1]);
				users.put(field[0], user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
