

//import com.mysql.cj.protocol.Resultset;
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
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;

public class firstViewController implements Initializable {

    @FXML
    private TextField nameTextField,ageTextField,addressTextField,salaryTextField;

    String queryy = "CREATE TABLE IF NOT EXISTS testt.persons_new(`id` INT(10) NOT NULL AUTO_INCREMENT,`first_name` VARCHAR(45) NULL ,`address` VARCHAR(45) NULL,`age` INT(10) NULL,`salary` INT(20) NULL, PRIMARY KEY (`id`));";



    public void submitButtonPushed(ActionEvent event) throws Exception{
        Integer age =Integer.parseInt(ageTextField.getText());
        Integer salary = Integer.parseInt(salaryTextField.getText());
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        Person person = new Person(name,age,address,salary);

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/testt?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root")){


            Statement st = connection.createStatement();

            st.execute("INSERT INTO persons_new (first_name,address,age,salary) VALUES ('"+person.getName()+"', '"+person.getAddress()+"', '"+person.getAge()+"', "+person.getSalary()+")");
        }catch (Exception e ){
            System.out.println(e);

        }

        resetForm();


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root")){
            String query = "CREATE DATABASE IF NOT EXISTS TESTT ;";
            String query2 = queryy;

            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.execute(query2);
        }catch (Exception e ){
            System.out.println(e);

        }
        setPersonsDatabase();






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

    public void setPersonsDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/testt?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root")) {

            Statement statement = connection.createStatement();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from persons_new");


            if (rs.next() == false) {

                Statement st = connection.createStatement();
                st.executeUpdate("INSERT INTO persons_new (first_name,address,age,salary) " +
                        "VALUES ('Jack', 'Doe', 12, 0 )");
                st.executeUpdate("INSERT INTO persons_new (first_name,address,age,salary) " +
                        "VALUES ('Johnnie', 'Doe', 21, 12000 )");
                st.executeUpdate("INSERT INTO persons_new (first_name,address,age,salary) " +
                        "VALUES ('Jim', 'Doe', 18, 6000 )");
                st.executeUpdate("INSERT INTO persons_new (first_name,address,age,salary) " +
                        "VALUES ('Jameson', 'Doe', 40, 15000 )");
            }else{
                System.out.println("Database already populated");
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }











}