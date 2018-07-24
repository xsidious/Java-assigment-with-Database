import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.management.Query;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static javax.swing.text.html.HTML.Tag.SELECT;

public class secondViewController implements Initializable {


    @FXML
    private TableView<Person> tableView;
    @FXML private TableColumn<Person, String> firstNameColumn;
    @FXML private TableColumn<Person, String> addressColumn;
    @FXML private TableColumn<Person, String> ageColumn;
    @FXML private TableColumn<Person, String> salaryColumn;

    private ObservableList<Person> Persons;
    private Person newPerson;
    private String firstName,address;
    private Integer age,salary;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("age"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("salary"));

        tableView.setItems(getPersons());




    }

    String query = "CREATE TABLE IF NOT EXISTS `test`.`persons_new` (\n" +
            "  `id` INT(10) NOT NULL AUTO_INCREMENT,\n" +
            "  `first_name` VARCHAR(45) NULL,\n" +
            "  `address` VARCHAR(45) NULL,\n" +
            "  `age` INT(10) NULL,\n" +
            "  `salary` INT(20) NULL,\n" +
            "  PRIMARY KEY (`id`),\n";

//    IF NONE EXISTS CREATE DATABASE new_database_for_assigment;" +
//            "



    public ObservableList<Person> getPersons(){
        ObservableList<Person> persons = FXCollections.observableArrayList();
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/testt?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root")){


            Statement st = connection.createStatement();
            //st.execute("SELECT * FROM new_database_for_assigment.person ;");
            Person person;


            ResultSet rs = st.executeQuery("SELECT * FROM testt.persons_new;" );
            while (rs.next()) {
                Person prs = new Person(rs.getString("first_name"),rs.getString("address"),rs.getInt("age"),rs.getInt("salary"));
                persons.add(prs);
            }



        }catch (Exception e ){
            System.out.println(e);

        }



        return persons;
    }





    public void closeButtonPressed(){

        Platform.exit();






    }

    public void addNewButtonPressed(ActionEvent event) throws IOException {
        Parent firstSceneLoader = FXMLLoader.load(getClass().getResource("firstViewCollectInfo.fxml"));
        Scene firstScene = new Scene(firstSceneLoader);
       // secondScene.getStylesheets().add("JavaFX/style.css");
        //this part gets the stage information

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(firstScene);
        window.show();
    }








}