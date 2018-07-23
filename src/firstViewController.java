import javafx.application.Platform;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;

public class firstViewController implements Initializable {

    @FXML
    private TextField nameTextField,ageTextField,addressTextField,salaryTextField;



    public void submitButtonPushed(ActionEvent event) throws Exception{
        Integer age =Integer.parseInt(ageTextField.getText());
        Integer salary = Integer.parseInt(salaryTextField.getText());
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        Person person = new Person(name,age,address,salary);

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/new_database_for_assigment?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root")){
            Statement st = connection.createStatement();
            st.execute("INSERT INTO PERSON (first_name,address,age,salary) VALUES ('"+person.getName()+"', '"+person.getAddress()+"', '"+person.getAge()+"', "+person.getSalary()+")");
        }catch (Exception e ){
            System.out.println(e);

        }

        resetForm();


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    public void resetForm(){
        nameTextField.clear();
        ageTextField.clear();
        addressTextField.clear();
        salaryTextField.clear();
    }


    public void closeButtonPressed(){

        Platform.exit();

    }

    public void employeesButtonPressed(ActionEvent event) throws IOException {
        Parent secondSceneLoader = FXMLLoader.load(getClass().getResource("secondViewTable.fxml"));
        Scene secondScene = new Scene(secondSceneLoader);
       // secondScene.getStylesheets().add("JavaFX/style.css");
        //this part gets the stage information

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(secondScene);
        window.show();
    }





}