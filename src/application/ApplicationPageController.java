package application;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import core.CustomImage;
import processors.ImageProcessingFactory;
import processors.ImageProcessingType;
import utils.Utils;
import javax.imageio.ImageIO;

public class ApplicationPageController implements Initializable{
	
	private static final String PASSWORD = "IGU";
	
	@FXML
	public AnchorPane anchorPane = new AnchorPane();
	
	@FXML
	public ImageView iv1, iv2;
	@FXML 
	public CheckBox cb1,cb2,cb3,cb4;
	@FXML
	public ComboBox<String> combobox;
	ObservableList<String> list = FXCollections.observableArrayList("Brightness", "Contrast");
		
	@FXML
	public String optiune;
	@FXML
	public TextField outputPictureTextField;
	@FXML
	public RadioButton newWindow;
	@FXML
	public ProgressBar progressBar;
	@FXML
	public PasswordField passwordField = new PasswordField();
	@FXML
	public Spinner<Integer> spinner;
	
	public FileChooser fileChooser = new FileChooser();
	public ImageProcessingType processingType;
	public Image image;
	public Image image2;
	public CustomImage inputImage;
	final int initialValueSpinner = 1;	
	public SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,255, initialValueSpinner, 1);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		spinner.setValueFactory(spinnerValueFactory);
		combobox.setItems(list);
		passwordField.setPromptText("Please introduce the secret password");
		}
	
	public void btn_upload(ActionEvent event) throws IOException {
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);
		
		
		if (selectedFile != null) 
			{
			image = new Image(selectedFile.toURI().toString(), 450, 0, true, true);
			inputImage = new CustomImage(ImageIO.read(selectedFile));
			iv1.setImage(image);	
			progressBar.setProgress(0.5);
			}
		else System.out.println("opa, file is not valid");
			
	}	
		
	public void showButton(ActionEvent event) throws IOException {
		String  outputPictureName;
		outputPictureName = outputPictureTextField.getText();
		int modificationValue = 0;
		String parola = passwordField.getText();
		
		// Selecteaza valoare cu care o sa  se modifice poza
		if (cb1.isSelected())
			modificationValue = 25;
		else if (cb2.isSelected())
			modificationValue = 50;
		else if (cb3.isSelected())
			modificationValue = 75;
		else if (cb4.isSelected())
			modificationValue = 150;
		else if (!spinner.getValue().equals(null))
			modificationValue = spinner.getValue();
		
		if (modificationValue < -255 || modificationValue > 255) {
            System.err.println("Please enter a value betweek -255 and 255.");
                System.exit(1);
		}
                
		// Alegem ce vrem sa modificam
		processingType = ImageProcessingType.getImageProcessingType(combobox.getValue());		 
		ImageProcessingFactory imageProcessingFactory = ImageProcessingFactory.getInstance(); 
		CustomImage outputImage = imageProcessingFactory.processImage(
                 inputImage, processingType, modificationValue);
		 
		 outputPictureName = outputPictureName + "." + Utils.IMAGE_FORMAT;
		 ImageIO.write(outputImage.getImage(), Utils.IMAGE_FORMAT, 
                 new File(outputPictureName));
		 
		 File  outputPictureFile = new File(outputPictureName);
		 image2 = new Image(outputPictureFile.toURI().toString(), 450, 0, true, true);
		 
		if (parola.equals(PASSWORD))
		{ 
			 if (newWindow.isSelected()) 
			 {		
				 progressBar.setProgress(1);
				 //Pagination
				 Pagination pagination = new Pagination(2);
				 pagination.setPageFactory(new Callback<Integer, Node>() {
			            @Override
			            public Node call(Integer indexPagina) {
			                return createPage(indexPagina);
			            }
			        });
				 
				 Stage stage = new Stage();
				 AnchorPane anchor = new AnchorPane();
			     AnchorPane.setTopAnchor(pagination, 10.0);
			     AnchorPane.setRightAnchor(pagination, 10.0);
			     AnchorPane.setBottomAnchor(pagination, 10.0);
			     AnchorPane.setLeftAnchor(pagination, 10.0);
			     anchor.getChildren().addAll(pagination);
			     Scene scene = new Scene(anchor, 500, 500);
			     stage.setScene(scene);
			     stage.show();
			 }
			 	
		 else 
		 	{
			 iv2.setImage(image2);
			 progressBar.setProgress(1);
		 	}
		 } else System.out.print("Parola nu functioneaza");
	}
	
	private VBox createPage(int pageIndex) {
        VBox box = new VBox();
        VBox element = new VBox();
        if (pageIndex == 0)
        {
        		iv1 = new ImageView(image);
        		 element.getChildren().addAll(iv1);
        }
        else if (pageIndex == 1)
        		{
        		iv2 = new ImageView(image2);
        		element.getChildren().addAll(iv2); 
        		}
          
        box.getChildren().add(element);
        return box;
    }
	
	public void applicationButton(ActionEvent event) throws IOException {
		 Parent tableViewParent = FXMLLoader.load(getClass().getResource("ApplicationPage.fxml"));
		 Scene tableViewScene = new Scene(tableViewParent);
		 
		 Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		 window.setScene(tableViewScene);
		 window.show();
	}
}
