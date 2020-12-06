/**class FirstC
 * @author Matteo Falkenberg
 * @version 1.10, 06.12.2020
 */

package controllerview.first;

import controllerview.second.SecondC;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class FirstC implements Initializable {

  @FXML private TextField username_field;
  @FXML private TextField password_field;
  private Stage stage;
  
  public static void show(Stage stage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(FirstC.class.getResource("firstV.fxml"));
      Parent root = fxmlLoader.load();
      
      //get controller which is connected to this fxml file
      FirstC ctrl = fxmlLoader.getController();
      ctrl.stage = stage;
      
      stage.setTitle("Login");
      stage.setScene(new Scene(root, 400, 400));
      stage.show();
    }
    catch (IOException e) {
      System.err.println("Something wrong with firstV.fxml: " + e.getMessage());
      e.printStackTrace(System.err);
    }
  }
  
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }
  
  @FXML
  private void login() {

    Model model = new Model();

    String usrName = username_field.getText();
    String pwd = password_field.getText();

    boolean loginProvided = model.isCorrectLogin(usrName, pwd);

    //checks if provided login data is acceptible
    if(loginProvided == true) {
      //navigate from welcome screen to main screen
      System.out.println("Login successful ...");

      SecondC.show(new Stage(), "Hello from Welcome Controller!");
      stage.close();
    }
    else
      System.out.println("Login was unsuccessful");
  }
}
