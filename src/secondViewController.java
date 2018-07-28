

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javax.management.Query;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.LinkedBlockingDeque;

import javafx.scene.control.Alert;


import static javax.swing.text.html.HTML.Tag.SELECT;

public class secondViewController implements Initializable {


    @FXML
    private TableView<Person> tableView;
    @FXML private TableColumn<Person, String> firstNameColumn;
    @FXML private TableColumn<Person, String> addressColumn;
    @FXML private TableColumn<Person, String> ageColumn;
    @FXML private TableColumn<Person, String> salaryColumn;
    @FXML private TableColumn<Person, String > idNumColumn;
    @FXML private TextField searchDatabase;

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
        idNumColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("idNumber"));
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
                Person prs = new Person(rs.getInt("id"),rs.getString("first_name"),rs.getString("address"),rs.getInt("age"),rs.getInt("salary"));
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

//    public void  selectedPersonEdit() throws IOException {
//        Integer id = tableView.getSelectionModel().getSelectedItem().getIdNumber();
//        System.out.println(id);
//
//
//    }


    public void searchDatabase(){

        ObservableList<Person> persons = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/testt?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root")){
            String searchTerm = searchDatabase.getText();


            Statement stmt = connection.createStatement();

            if (searchTerm.equals("")){
                ResultSet rs = stmt.executeQuery("SELECT * FROM persons_new");
                while (rs.next()) {
                    Person prs = new Person(rs.getInt("id"),rs.getString("first_name"),rs.getString("address"),rs.getInt("age"),rs.getInt("salary"));

                    persons.add(prs);
                }
            } else {

                ResultSet rs = stmt.executeQuery("SELECT * FROM persons_new WHERE first_name = '"+searchTerm+"' OR address = '"+searchTerm+"' ");
                while (rs.next()) {
                    Person prs = new Person(rs.getInt("id"),rs.getString("first_name"),rs.getString("address"),rs.getInt("age"),rs.getInt("salary"));

                    persons.add(prs);
                }


            }


        } catch (Exception e ){
            System.out.println(e);

        }
        tableView.setItems(persons);
        searchDatabase.setText("");



    }

    public void editButtonPushed(ActionEvent event) throws IOException {
        Integer id = tableView.getSelectionModel().getSelectedItem().getIdNumber();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editView.fxml"));

        Parent secondSceneLoader = loader.load();
        Scene secondScene = new Scene(secondSceneLoader);

        editViewController editViewControllero = loader.getController();
        editViewControllero.getID(id);

        //this part gets the stage information

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(secondScene);
        window.show();

    }


    public void detailedViewButtonPressed(ActionEvent event) throws IOException {
        Integer id = tableView.getSelectionModel().getSelectedItem().getIdNumber();
        String name = tableView.getSelectionModel().getSelectedItem().getName();
        String address = tableView.getSelectionModel().getSelectedItem().getAddress();
        Integer age = tableView.getSelectionModel().getSelectedItem().getAge();
        Integer salary = tableView.getSelectionModel().getSelectedItem().getSalary();



        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("detailedView.fxml"));

        Parent secondSceneLoader = loader.load();
        Scene secondScene = new Scene(secondSceneLoader);

        DetailedViewController detailedViewController = loader.getController();
        detailedViewController.getPerson(id,name,address,age,salary);

        //this part gets the stage information

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(secondScene);
        window.show();






    }


    public void deleteButtonPushed(){




        ObservableList<Person> persons = FXCollections.observableArrayList();
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/testt?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root")) {
            Integer idNum = tableView.getSelectionModel().getSelectedItem().getIdNumber();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM persons_new WHERE ID = "+idNum+" ");
            ResultSet rs = stmt.executeQuery("SELECT * FROM persons_new");
            while (rs.next()) {
                Person prs = new Person(rs.getInt("id"),rs.getString("first_name"),rs.getString("address"),rs.getInt("age"),rs.getInt("salary"));
                persons.add(prs);
            }



        }  catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        tableView.setItems(persons);




    }


    public void salaryOverButtonPushed(){


        ObservableList<Person> persons = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/testt?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root")){

            Statement stmt = connection.createStatement();


                ResultSet rs = stmt.executeQuery("SELECT * FROM persons_new WHERE salary > 1000");
                while (rs.next()) {
                    Person prs = new Person(rs.getInt("id"),rs.getString("first_name"),rs.getString("address"),rs.getInt("age"),rs.getInt("salary"));

                    persons.add(prs);
                }



        } catch (Exception e ){
            System.out.println(e);

        }
        tableView.setItems(persons);
        searchDatabase.setText("");


    }





    public void youngerThanButtonPushed(){


        ObservableList<Person> persons = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/testt?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root")){

            Statement stmt = connection.createStatement();


            ResultSet rs = stmt.executeQuery("SELECT * FROM persons_new WHERE age < 25");
            while (rs.next()) {
                Person prs = new Person(rs.getInt("id"),rs.getString("first_name"),rs.getString("address"),rs.getInt("age"),rs.getInt("salary"));

                persons.add(prs);
            }



        } catch (Exception e ){
            System.out.println(e);

        }
        tableView.setItems(persons);
        searchDatabase.setText("");


    }













}