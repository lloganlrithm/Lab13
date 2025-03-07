package application;
//นายธนดล ไชยศิลา 673380585-0

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Scene2Controller implements Initializable{
	
	private Stage stage;
	private Scene scene;
	
	@FXML
	private Label Show;
	
	@FXML
	private TableView<User> tableView;
	
	@FXML
	private TableColumn<User, String> Username;
	
	@FXML
	private TableColumn<User, String> Email;
	
	private TreeMap<String, User> users = new TreeMap<>();
	
	public void setList(TreeMap<String, User> users) 
	{
		this.users = users;
		if (users != null && !users.isEmpty()) 
		{
			for(var e: users.entrySet())
			{
				User User = new User(e.getValue().getUsername(),e.getValue().getEmail());
			    ObservableList<User> Users = tableView.getItems();
			    Users.add(User);
				tableView.setItems(Users);
			}
		} 
	}
	
	public void switchToScene1(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
		AnchorPane root = loader.load();		
		
		Scene1Controller scene1Controller = loader.getController();
		scene1Controller.setList(users);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		 Username.setCellValueFactory(new PropertyValueFactory<>("Username"));
		 Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
		 
		 Show.setVisible(false);
		 Show.setManaged(false);
		 
		 tableView.setOnMouseClicked(event -> {
	         User selectedPerson = tableView.getSelectionModel().getSelectedItem();
	         if (selectedPerson != null) 
	         {
	        	 Show.setVisible(true);
	    		 Show.setManaged(true);
	        	 Show.setText("Username: " + selectedPerson.getUsername() + "\nEmail: " + selectedPerson.getEmail());
	         }
	     });
	}
}
