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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstC implements Initializable {
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
  private void next() {
    //navigate from welcome screen to main screen
    System.out.println("Navigation started ...");
   
    SecondC.show(new Stage(),"Hello from Welcome Controller!");
    stage.close();
    }
}
