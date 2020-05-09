package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		BorderPane root = new BorderPane();
		TableView table = new TableView();
		Accordion accordion = new Accordion();
		ToolBar toolBar = new ToolBar();
		Stage stage = new Stage();

		final ObservableList<Person> data = FXCollections.observableArrayList(new Person("Veizu", "Ileana", "344A3"));

		Image imageOk = new Image(getClass().getResourceAsStream("pictures/upload.png"));
		Button btn_informations = new Button("Informations", new ImageView(imageOk));
		Button btn_application = new Button("Application", new ImageView(imageOk));
		btn_application.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					applicationButton(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		TitledPane pane1 = new TitledPane("Nume", new Label("Veizu"));
		TitledPane pane2 = new TitledPane("Prenume", new Label("Ileana"));
		TitledPane pane3 = new TitledPane("Grupa", new Label("344A3"));

		accordion.getPanes().add(pane1);
		accordion.getPanes().add(pane2);
		accordion.getPanes().add(pane3);
		toolBar.getItems().add(btn_informations);
		toolBar.getItems().add(btn_application);

		table.setEditable(true);

		TableColumn firstNameCol = new TableColumn("Nume");
		firstNameCol.setMinWidth(100);
		TableColumn lastNameCol = new TableColumn("Prenume");
		lastNameCol.setMinWidth(100);
		TableColumn grupaCol = new TableColumn("Grupa");

		firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("nume"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("prenume"));
		grupaCol.setCellValueFactory(new PropertyValueFactory<Person, String>("grupa"));

		table.setItems(data);
		table.getColumns().addAll(firstNameCol, lastNameCol, grupaCol);

		root.setLeft(accordion);
		root.setTop(toolBar);
		root.setCenter(table);

		Scene scene = new Scene(root);
		stage.setWidth(600);
		stage.setHeight(400);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static class Person {
		private final SimpleStringProperty nume;
		private final SimpleStringProperty prenume;
		private final SimpleStringProperty grupa;

		private Person(String fName, String lName, String egrupa) {
			this.nume = new SimpleStringProperty(fName);
			this.prenume = new SimpleStringProperty(lName);
			this.grupa = new SimpleStringProperty(egrupa);
		}

		public String getNume() {
			return nume.get();
		}

		public void setNume(String fName) {
			nume.set(fName);
		}

		public String getPrenume() {
			return prenume.get();
		}

		public void setPrenume(String fName) {
			prenume.set(fName);
		}

		public String getGrupa() {
			return grupa.get();
		}

		public void setGrupa(String fName) {
			grupa.set(fName);
		}

	}

	public void applicationButton(ActionEvent event) throws IOException {
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("ApplicationPage.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(1420);
		window.setHeight(600);
		window.setScene(tableViewScene);
		window.show();
	}

}
