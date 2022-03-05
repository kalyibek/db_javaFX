package com.example.demofx;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.demofx.DBConnection.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreateDepartmentFormController {
    DBConnector dbConnector = new DBConnector();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextField departmentNameField;

    @FXML
    private TextField departmentNumberField;

    @FXML
    private TextField mgrSsnField;

    @FXML
    private DatePicker mgrStartDatePicker;

    @FXML
    void addNewDepartment(MouseEvent event) throws SQLException, IOException {

        String dname = departmentNameField.getText();
        Integer dnumber = Integer.parseInt(departmentNumberField.getText());
        LocalDate startDate = mgrStartDatePicker.getValue();
        Long mgrSsn = Long.parseLong(mgrSsnField.getText());
        if (dname.isEmpty()||dnumber==0||startDate==null||mgrSsn==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Something went wrong");
            alert.setContentText("Please fill all data");
            alert.showAndWait();
        } else {
            insertInDB(dname,dnumber,startDate,mgrSsn);
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DepartmentsTable.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }

    }

    @FXML
    void initialize() {

    }

    private void insertInDB(String dna , Integer dnu , LocalDate sdt , Long mssn) throws IOException, SQLException {

        Connection connection = dbConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(dbConnector.prop.getProperty("db.departmentInsert"));
        preparedStatement.setString(1,dna);
        preparedStatement.setInt(2,dnu);
        preparedStatement.setString(3,mssn.toString());
        preparedStatement.setString(4,sdt.toString());
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

}
