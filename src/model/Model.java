package model;

import controllerview.second.SecondC;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Model {

    public Model(){}


    public ArrayList readFile(String pathname){
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


    public boolean isCorrectLogin(String name, String password){
        try {
            //read all lines in login_data file and save them in a list
            Scanner sc = new Scanner(new File("./login_files/login_data.txt"));
            ArrayList<String> login_data = new ArrayList<String>();
            while (sc.hasNextLine()) {
                login_data.add(sc.nextLine());
            }

            //remove all empty elements in List
            login_data.removeAll(Arrays.asList("", null));

            //check provided login data with login list
            for (int i = 0; i < login_data.size() - 1; i++) {

                //Username in TextField on an even number in login list and Password in TextField the same as next index in List
                if (name.equals(login_data.get(i)) && i % 2 == 0 && password.equals(login_data.get(i + 1))) {
                    return true;
                }
            }
        }
        catch (Exception e){
            System.err.println("Something is wrong: " + e.getMessage());
            e.printStackTrace(System.err);
        }
        return false;
    }


    public String getRandomAnswer(String question) {

        try {
            //read all lines in the following files, delete empty lines and save them in lists
            ArrayList standard_answers = readFile("./eight_ball_files/standard_answers.txt");
            standard_answers.removeAll(Arrays.asList("", null));
            ArrayList trigger_words = readFile("./eight_ball_files/trigger_words.txt");
            trigger_words.removeAll(Arrays.asList("", null));

            int bonus = 0;

            //check for trigger words in question
            for (int i = 0; i < trigger_words.size(); i++) {

                if (question.toLowerCase().contains(trigger_words.get(i).toString().toLowerCase()))
                    bonus += 5;
            }

            Random r = new Random();
            int rNum = r.nextInt(standard_answers.size());

            //adding bonus of trigger words to random number to get index (the higher 'index' the more positive of an answer)
            int index = rNum + bonus;
            if (index >= standard_answers.size())
                index = standard_answers.size() - 1;

            return standard_answers.get(index).toString();
        }
        catch (Exception e) {
            System.err.println("Something is wrong: " + e.getMessage());
            e.printStackTrace(System.err);
        }
        return null;
    }

}