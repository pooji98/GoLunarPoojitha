package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



import java.io.IOException;
import java.sql.SQLException;



/**
 * Controls the Behavior and state of the concise schedule page
 */
public class ConciseSchedule_controller {
    @FXML
    public TableColumn crnCol;
    @FXML
    public TableColumn subjectCol;
    @FXML
    public TableColumn courseCol;
    @FXML
    public TableColumn creditsCol;
    @FXML
    public TableColumn daysCol;
    @FXML
    public TableColumn timeCol;
    @FXML
    public TableColumn locationCol;
    @FXML
    public TableColumn instructorCol;
    //create object to retrieve student's schedule details from the database
    // with input parameter being the "name" of the student

    ScheduleDetails schedule = new ScheduleDetails(3518954);
                                                            //^^studentID
                                                            // variable
    @FXML
     public Button bMain, bRegistration, bPerInfo, bRecords;
    @FXML
    private TableView<Schedule> table = new TableView<>();
    @FXML
    public Label studentIDlabel;

    public ConciseSchedule_controller() throws SQLException {

    }

    @FXML
    public void initialize() {
        String studentIDtemp = "3518954";
        studentIDlabel.setText(studentIDtemp);
        //table = new TableView<Schedule>();
        //rows of the table corresponding to each class
        final ObservableList<Schedule> data = FXCollections.observableArrayList();

        //always check classid# crn if null, CRN methods return null if
        // classID# does not exist
        if (schedule.getID1crn() != null) {
            System.out.println("adding to data list: ID1");
            data.add(new Schedule(schedule.getID1crn(), schedule
                    .getID1subjectName(), schedule.getID1coursetitle(),
                    schedule.getID1credits(), schedule.getID1days(), schedule
                    .getID1time(), schedule.getID1location(), schedule
                    .getID1instructor(), schedule.getID1Desc()));
        }
        if (schedule.getID2crn() != null) {
            System.out.println("adding to data list: ID2");
            data.add(new Schedule(schedule.getID2crn(), schedule
                    .getID2subjectName(), schedule.getID2coursetitle(),
                    schedule.getID2credits(), schedule.getID2days(), schedule
                    .getID2time(), schedule.getID2location(), schedule
                    .getID2instructor(), schedule.getID2Desc()));

        }
        if (schedule.getID3crn() != null) {
            System.out.println("adding to data list: ID3");
            data.add(new Schedule(schedule.getID3crn(), schedule
                    .getID3subjectName(), schedule.getID3coursetitle(),
                    schedule.getID3credits(), schedule.getID3days(), schedule
                    .getID3time(), schedule.getID3location(), schedule
                    .getID3instructor(), schedule.getID3Desc()));
        }
        if (schedule.getID4crn() != null) {
            System.out.println("adding to data list: ID4");
            data.add(new Schedule(schedule.getID4crn(), schedule
                    .getID4subjectName(), schedule.getID4coursetitle(),
                    schedule.getID4credits(), schedule.getID4days(), schedule
                    .getID4time(), schedule.getID4location(), schedule
                    .getID4instructor(), schedule.getID4Desc()));
        }

        crnCol.setCellValueFactory(new PropertyValueFactory<Schedule, String>
                ("CRN"));


        subjectCol.setCellValueFactory(new PropertyValueFactory<Schedule, String>
                ("subjectName"));


        courseCol.setCellValueFactory(new PropertyValueFactory<Schedule, String>
                ("courseTitle"));


        creditsCol.setCellValueFactory(new PropertyValueFactory<Schedule,
                String>("credits"));


        daysCol.setCellValueFactory(new PropertyValueFactory<Schedule, String>
                ("days"));


        timeCol.setCellValueFactory(new PropertyValueFactory<Schedule, String>
                ("time"));


        locationCol.setCellValueFactory(new PropertyValueFactory<Schedule,
                String>("location"));


        instructorCol.setCellValueFactory(new PropertyValueFactory<Schedule,
                String>("instructor"));

        table.setItems(data);

    }

    //double click on a row shows the class description
    @FXML
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 2)  {    //Checking double click

            //for debugging purpose below to print to console
            //System.out.println(table.getSelectionModel().getSelectedItem()
            //        .getDescription());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Go Lunar 1.0");     //change stage title if wanted
            alert.setHeaderText(table.getSelectionModel().getSelectedItem()
                    .getCourseTitle());

            if (table.getSelectionModel().getSelectedItem().getDescription()
                    .isEmpty()) {
                alert.setContentText("No Description Available");
            } else {

                alert.setContentText(table.getSelectionModel().getSelectedItem()
                        .getDescription());
            }

            alert.showAndWait();
        }
    }


    public void mainBClicked(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void recordsBClicked(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Records.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Records Menu");
        stage.show();
    }

    public void infoBClicked(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("PersonalInfo.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Personal Information Menu");
        stage.show();
    }

    public void registrationBClicked(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Registration Menu");
        stage.show();
    }


}


