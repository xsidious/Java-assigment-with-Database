import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class editViewController implements Initializable {


    @FXML
    private TextField nameUpdated,addressUpdated,ageUpdated,salaryUpdated;
    private int id;






    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void updateButtonPressed() throws IOException {
        String name = nameUpdated.getText();
        String address = addressUpdated.getText();
        Integer age = Integer.parseInt(ageUpdated.getText());
        Integer salary = Integer.parseInt(salaryUpdated.getText());
        Integer idNum =id ;
        Person person = new Person(idNum, name, address, age, salary);
        System.out.println(person.toString());


//        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/testt?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root")) {
//
//        }  catch (Exception e) {
//            System.err.println("Got an exception! ");
//            System.err.println(e.getMessage());
//        }





    }

    public void getID(int id){
        this.id = id;
    }








}
