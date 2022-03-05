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
import com.example.demofx.entities.Department;
import com.example.demofx.repositories.DepartmentRepo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class DepartmentsTableController {

    final DepartmentRepo departmentRepo = new DepartmentRepo();
    DBConnector dbConnector = new DBConnector();
    static Department requiredDepartmentForEdit ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button createNewEmployeeButton;

    @FXML
    private Button deleteDepartmentButton;
    @FXML
    private TableView<Department> departmentsTable;

    @FXML
    private TableColumn<Department, String> departmentNameColumn;

    @FXML
    private TableColumn<Department, Integer> departmentNumberColumn;

    @FXML
    private Button editDepartmentButton;

    @FXML
    private TableColumn<Department, Long> managerSsnColumn;

    @FXML
    private TableColumn<Department, Date> mgrStartDateColumn;

    @FXML
    private Button seeEmployeeButton;

    @FXML
    void createNewEmployee(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateDepartmentForm.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void deleteDepartment(MouseEvent event) throws IOException, SQLException {
        Integer departmentNumber = departmentsTable.getSelectionModel().getSelectedItem().getD_number();
        Connection connection = dbConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(dbConnector.prop.getProperty("db.departmentDelete"));
        preparedStatement.setInt(1,departmentNumber);
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
        initializeTableValues();
    }

    @FXML
    void editDepartment(MouseEvent event) throws IOException {
    Integer dnumber = departmentsTable.getSelectionModel().getSelectedItem().getD_number();
    requiredDepartmentForEdit = departmentRepo.getDepartmentByNumber(dnumber);
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EditDepartmentForm.fxml")));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();


    }

    @FXML
    void getEmployeeInfo(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EmployeeTable.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void initialize() {
        departmentNameColumn.setCellValueFactory(new PropertyValueFactory<>("Dname"));
        departmentNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Dnumber"));
        managerSsnColumn.setCellValueFactory(new PropertyValueFactory<>("MgrSsn"));
        mgrStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("MgrStartDate"));
        initializeTableValues();

    }
    public void initializeTableValues(){
        ObservableList<Department> departmentList = departmentRepo.getDepartmentsList();
        if(departmentList.size() > 0){
            departmentsTable.setItems(departmentList);
        }
    }

}

