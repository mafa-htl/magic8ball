/**class SecondC
 * @author Matteo Falkenberg
 * @version 1.10, 06.12.2020
 */

package controllerview.second;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SecondC implements Initializable {
  
  private String myData = "";
  
  public static void show(Stage stage, String greeting) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(SecondC.class.getResource("secondV.fxml"));
      Parent root = fxmlLoader.load();
      
      //send data to MainController
      SecondC ctrl = fxmlLoader.getController();
      ctrl.setMyData(greeting);
      
      stage.setTitle("Second");
      stage.setScene(new Scene(root, 500, 500));
      stage.show();
    }
    catch (Exception e) {
      System.err.println("Something wrong with secondV.fxml: " + e.getMessage());
      e.printStackTrace(System.err);
    }
  }
  
  public void setMyData(String myData) {
    this.myData = myData;
    System.out.println("(MainController) Data received: " + this.myData);
  }
  
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }
}
