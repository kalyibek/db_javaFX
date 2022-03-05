
package com.example.demofx;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.demofx.DBConnection.DBConnector;
import com.example.demofx.entities.Employee;
import com.example.demofx.repositories.EmployeeRepo;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EmployeeTableController {
    final EmployeeRepo employeeRepo = new EmployeeRepo();
    DBConnector dbConnector = new DBConnector();
    static Employee requiredEmployeeForEdit;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Employee> employeeTable;


    @FXML
    private TableColumn<Employee, String> addressColumn;

    @FXML
    private TableColumn<Employee, Date> birthdateColumn;

    @FXML
    private TableColumn<Employee, Integer> dnumberColumn;

    @FXML
    private TableColumn<Employee, String> firstNameColumn;

    @FXML
    private TableColumn<Employee, String> lastNameColumn;

    @FXML
    private TableColumn<Employee, Integer> salaryColumn;

    @FXML
    private TableColumn<Employee, String> sexColumn;

    @FXML
    private TableColumn<Employee, Integer> ssnColumn;

    @FXML
    private TableColumn<Employee, Long> superSSnColumn;


    @FXML
    private Button createNewEmployeeButton;
    @FXML
    private Button deleteEmployeeButton;
    @FXML
    private Button editButton;

    @FXML
    private Button seeDepartmentsButton;

    @FXML
    void deleteEmployee(MouseEvent event) throws IOException, SQLException {
        Integer Ssn = employeeTable.getSelectionModel().getSelectedItem().getSsn();
        Connection connection = dbConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(dbConnector.prop.getProperty("db.employeeDelete"));
        preparedStatement.setInt(1, Ssn);
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
        initializeTableValues();
    }


    public EmployeeTableController() throws IOException, IOException {
    }

    @FXML
    void createNewEmployee(MouseEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ChangeEmployeeForm.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void editEmployee(MouseEvent event) throws IOException {
        Integer Ssn = employeeTable.getSelectionModel().getSelectedItem().getSsn();
        requiredEmployeeForEdit = employeeRepo.getEmployeeBySsn(Ssn);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EditEmployeeForm.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();


    }

    @FXML
    void getDepartmentsInfo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DepartmentsTable.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("Fname"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("Lname"));
        ssnColumn.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("Bdate"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("Sex"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        superSSnColumn.setCellValueFactory(new PropertyValueFactory<>("Superssn"));
        dnumberColumn.setCellValueFactory(new PropertyValueFactory<>("Dnumber"));
        initializeTableValues();

    }

    public void initializeTableValues() {
        ObservableList<Employee> personList = employeeRepo.getEmployeeList();
        if (personList.size() > 0) {
            employeeTable.setItems(personList);
        }
    }

}

