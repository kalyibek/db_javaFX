package com.example.demofx.repositories;

import com.example.demofx.DBConnection.DBConnector;
import com.example.demofx.entities.Employee;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeRepo {
    DBConnector dbConnector = new DBConnector();
    ObservableList<Employee> listOfEmployee = FXCollections.observableArrayList();


    public EmployeeRepo() throws IOException {
    }

    public ObservableList<Employee> getEmployeeList(){
        listOfEmployee.clear();
        try {
            Connection connection = dbConnector.getConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(dbConnector.prop.getProperty("db.allEmployee"));
            resultSet.beforeFirst();
            while (resultSet.next()){
                Employee employee = new Employee(
                        resultSet.getString("Fname"),
                        resultSet.getString("Lname"),
                        resultSet.getInt("Ssn"),
                        resultSet.getDate("Bdate"),
                        resultSet.getString("Address"),
                        resultSet.getString("Sex"),
                        resultSet.getInt("Salary"),
                        resultSet.getLong("Super_ssn"),
                        resultSet.getInt("Dnumber")
                );
                listOfEmployee.add(employee);

            }
            statement.close();
            connection.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listOfEmployee;
    }
    public Employee getEmployeeBySsn(Integer ssn){
        Employee requiredEmployee = null;
        for (Employee e:
             listOfEmployee) {
            if(e.getSsn()==ssn){
                requiredEmployee = e;
            }
        }
        return requiredEmployee;
    }


}
