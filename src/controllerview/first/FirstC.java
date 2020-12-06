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
      
      stage.setTitle("Welcome");
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

    try {
      //read all lines in login_data file and save them in a list
      Scanner sc = new Scanner(new File("./login_files/login_data.txt"));
      ArrayList<String> login_data = new ArrayList<String>();
      while (sc.hasNextLine()) {
        login_data.add(sc.nextLine());
      }

      //remove all empty elements in List
      login_data.removeAll(Arrays.asList("", null));

      String usrName = username_field.getText();
      String pwd = password_field.getText();
      boolean loginProvided = false;

      //check provided login data with login list
      for (int i = 0; i < login_data.size() - 1; i++){

        //Username in TextField on an even number in login list and Password in TextField the same as next index in List
        if(usrName.equals(login_data.get(i)) && i % 2 == 0 && pwd.equals(login_data.get(i + 1))) {
          loginProvided = true;
          break;
        }
      }

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
    catch (Exception e){
      System.err.println("Something wrong: " + e.getMessage());
      e.printStackTrace(System.err);
    }
  }
}
