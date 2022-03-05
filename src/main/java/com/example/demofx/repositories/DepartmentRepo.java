package com.example.demofx.repositories;

import com.example.demofx.DBConnection.DBConnector;
import com.example.demofx.entities.Department;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DepartmentRepo {
    DBConnector dbConnector = new DBConnector();
    ObservableList<Department> listOfDepartments = FXCollections.observableArrayList();

    public ObservableList<Department> getDepartmentsList(){
        listOfDepartments.clear();
        try {
            Connection connection = dbConnector.getConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(dbConnector.prop.getProperty("db.allDepartments"));
            resultSet.beforeFirst();
            while (resultSet.next()){
                Department department = new Department(
                        resultSet.getString("Dname"),
                        resultSet.getInt("Dnumber"),
                        resultSet.getLong("Mgr_ssn"),
                        resultSet.getDate("Mgr_start_date")
                );
                listOfDepartments.add(department);

            }
            statement.close();
            connection.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listOfDepartments;
    }
    public Department getDepartmentByNumber(Integer number){
        Department requiredDepartment = null;
        for (Department d:
             listOfDepartments) {
            if(d.getD_number()==number){
                requiredDepartment = d;
            }
        }
        return requiredDepartment;
    }
}
