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
import com.example.demofx.entities.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditEmployeeFormController {
    DBConnector dbConnector = new DBConnector();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField addressField;

    @FXML
    private DatePicker bithdatePicker;

    @FXML
    private TextField dnumberField;

    @FXML
    private Button editButton;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField salaryField;

    @FXML
    private ChoiceBox<String> sexChoicer;

    @FXML
    private TextField ssnField;

    @FXML
    private TextField sssnField;

    @FXML
    void editEmployee(MouseEvent event) throws SQLException, IOException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        Integer Ssn = Integer.parseInt(ssnField.getText());
        LocalDate birthdate = bithdatePicker.getValue();
        String address = addressField.getText();
        String sex = sexChoicer.getValue();
        Integer salary = Integer.parseInt(salaryField.getText());
        Long sssn = Long.parseLong(sssnField.getText());
        Integer Dnumber = Integer.parseInt(dnumberField.getText());
        if(firstName.isEmpty()||lastName.isEmpty()||Ssn==0||birthdate==null||address.isEmpty()||sex.isEmpty()||salary==0||sssn==0||Dnumber==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Something went wrong");
            alert.setContentText("Please fill all data");
            alert.showAndWait();
        }else {
            updateInDB(firstName,lastName,Ssn,birthdate,address,sex,salary,sssn,Dnumber);
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EmployeeTable.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }

    }
    private void updateInDB(String fN , String lN , Integer ssn ,LocalDate bdate ,String address , String sex , Integer salary ,Long sssn , Integer dNumber) throws IOException, SQLException {
        Connection connection = dbConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(dbConnector.prop.getProperty("db.employeeUpdate"));
        Employee employeeForEdit = EmployeeTableController.requiredEmployeeForEdit;
        preparedStatement.setString(1,fN);
        preparedStatement.setString(2,lN);
        preparedStatement.setString(3,ssn.toString());
        preparedStatement.setString(4,bdate.toString());
        preparedStatement.setString(5,address);
        preparedStatement.setString(6,sex);
        preparedStatement.setString(7,salary.toString());
        preparedStatement.setString(8,sssn.toString());
        preparedStatement.setString(9,dNumber.toString());
        preparedStatement.setString(10,employeeForEdit.getSsn().toString());
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    @FXML
    void initialize() {
    Employee employeeForEdit = EmployeeTableController.requiredEmployeeForEdit;
    firstNameField.setText(employeeForEdit.getFirst_name());
    lastNameField.setText(employeeForEdit.getLast_name());
    ssnField.setText(employeeForEdit.getSsn().toString());
    bithdatePicker.setValue(LocalDate.of(Integer.parseInt(employeeForEdit.getYear()),Integer.parseInt(employeeForEdit.getMonth()),Integer.parseInt(employeeForEdit.getDay())));
    addressField.setText(employeeForEdit.getAddress());
    sexChoicer.getItems().add("M");
    sexChoicer.getItems().add("F");
    sexChoicer.setValue(employeeForEdit.getSex());
    salaryField.setText(employeeForEdit.getSalary().toString());
    sssnField.setText(employeeForEdit.getSuper_ssn().toString());
    dnumberField.setText(employeeForEdit.getD_number().toString());
    }

}
