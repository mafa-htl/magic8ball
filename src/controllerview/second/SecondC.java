/**class SecondC
 * @author Matteo Falkenberg
 * @version 1.10, 06.12.2020
 */

package controllerview.second;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.*;

public class SecondC implements Initializable {

  @FXML private Label ball_label;
  @FXML private TextField question_field;

  private String myData = "";
  
  public static void show(Stage stage, String greeting) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(SecondC.class.getResource("secondV.fxml"));
      Parent root = fxmlLoader.load();
      
      //send data to MainController
      SecondC ctrl = fxmlLoader.getController();
      ctrl.setMyData(greeting);
      
      stage.setTitle("Magic 8 Ball");
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

  @FXML
  private void ask(){

    try {
      String question = question_field.getText();

      if(question.contains("?")) {

        //read all lines in the following files, delete empty lines and save them in lists
        ArrayList standard_answers = readFile("./eight_ball_files/standard_answers.txt");
        standard_answers.removeAll(Arrays.asList("", null));
        ArrayList special_answers = readFile("./eight_ball_files/special_answers.txt");
        special_answers.removeAll(Arrays.asList("", null));
        ArrayList special_questions = readFile("./eight_ball_files/special_questions.txt");
        special_questions.removeAll(Arrays.asList("", null));
        ArrayList trigger_words = readFile("./eight_ball_files/trigger_words.txt");
        trigger_words.removeAll(Arrays.asList("", null));

        int bonus = 0;

        if (special_questions.contains(question)) {
          //set Text of ball_label to answer of corresponding special question
          ball_label.setText(special_answers.get(special_questions.indexOf(question)).toString());
        }

        else{
          //check for trigger words in question
          for (int i = 0; i < trigger_words.size(); i++) {

            if (question.contains(trigger_words.get(i).toString().toLowerCase()))
              bonus += 5;
          }

          Random r = new Random();
          int rNum = r.nextInt(standard_answers.size());

          //adding bonus of trigger words to random number to get index (the higher 'index' the more positive of an answer)
          int index = rNum + bonus;
          if(index >= standard_answers.size())
            index = standard_answers.size() - 1;

          //set ball_label text to an answer in standard_answers
          ball_label.setText(standard_answers.get(index).toString());
        }
      }

      else{
        question_field.setText("");
        question_field.setPromptText("question must have a ?");
      }

    }
    catch (Exception e){
      System.err.println("Something is wrong: " + e.getMessage());
      e.printStackTrace(System.err);
    }
  }

  private ArrayList readFile(String pathname){
    try {
      Scanner sc = new Scanner(new File(pathname));
      ArrayList<String> file_data = new ArrayList<String>();
      while (sc.hasNextLine()) {
        file_data.add(sc.nextLine());
      }
      return file_data;
    }
    catch (Exception e){
      System.err.println("Something is wrong with path: " + e.getMessage());
      e.printStackTrace(System.err);
    }
    return null;
  }

}
