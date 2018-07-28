import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailedViewController implements Initializable {

    @FXML
    private Label idLabel,firstNameLabel,addressLabel,ageLabel,salaryLabel;
    @FXML
    private Button backButton;

    private Integer id,age,salary;
    private String name,address;



    @Override
    public void initialize(URL location, ResourceBundle resources) {





    }



    public void getPerson(int id, String name, String address, int age, int salary){
        idLabel.setText(String.valueOf(id));
        firstNameLabel.setText(name);
        addressLabel.setText(address);
        ageLabel.setText(String.valueOf(age));
        salaryLabel.setText(String.valueOf(salary));




    }

    public void backButtonPressed(ActionEvent event) throws IOException {
        Parent secondSceneLoader = FXMLLoader.load(getClass().getResource("secondViewTable.fxml"));
        Scene secondScene = new Scene(secondSceneLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(secondScene);
        window.show();
    }


}
