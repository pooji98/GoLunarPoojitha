package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login_Controller {

    @FXML
    public TextField campusId, password;
    @FXML
    public Button loginB;
    @FXML
    public Label userPassWrong;


    ResultSet rs = null;
    Statement st;

    int count;
    public static Connection connect = SqliteConnection.Connector();

    @FXML
    public void Login(ActionEvent event) throws Exception{

        try {

            if (!campusId.getText().isEmpty() && !password.getText().isEmpty()) {
                String user = campusId.getText();
                String pass = password.getText();


                st = (Statement) connect.createStatement();
                rs = st.executeQuery("SELECT * FROM StudentTable where Username = '" + user + "' AND Password_Plain = '" + pass+"'");

                if(rs.next()) {
                    if (rs.getString(16).equals(user) && rs.getString(17).equals(pass)) {
                        Stage stage;
                        Parent root;
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                        root = loader.load();

                        String name = rs.getString(2);
                        String gpa = rs.getString(5);
                        int deg = rs.getInt(4);
                        rs = st.executeQuery("select * from SubjectTable where SubjectId = '"+deg+"'");
                        String degree = rs.getString(2);

                        String studentID = rs.getString(1); //get studentID

                        MainMenu_Controller menu = loader.getController();
                        menu.setNameL(name);
                        menu.setGpaText(gpa);
                        menu.setDegreeText(degree);


                        stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Main Menu");
                        stage.show();

                        rs.close();
                    }

                }
                else{
                    userPassWrong.setText("The CampusId or Password is incorrect.");
                }



            }
        }
        catch (Exception e ){
            throw e;
        }
    }

    public void setCount(int c){
        count = c;
    }
    public int getCount(){
        return count;
    }

}
