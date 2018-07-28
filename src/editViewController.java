

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

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


        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/testt?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root")) {
            Statement statement = connection.createStatement();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from persons_new WHERE ID = '"+idNum+"' ");
            while (rs.next()){

                String foo = rs.getString("id");
                System.out.println(foo);

            }
            stmt.executeUpdate("UPDATE persons_new SET first_name = '"+name+"', address = '"+address+"', age = '"+age+"', salary = '"+salary+"' WHERE ID = '"+idNum+"'");





        }  catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        resetEditView();








    }
    public void resetEditView(){
        nameUpdated.clear();
        ageUpdated.clear();
        addressUpdated.clear();
        salaryUpdated.clear();
    }




    public void getID(int id){
        this.id = id;
    }

    public void employeesButtonPressed(ActionEvent event) throws IOException {
        Parent secondSceneLoader = FXMLLoader.load(getClass().getResource("secondViewTable.fxml"));
        Scene secondScene = new Scene(secondSceneLoader);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(secondScene);
        window.show();
    }












}
