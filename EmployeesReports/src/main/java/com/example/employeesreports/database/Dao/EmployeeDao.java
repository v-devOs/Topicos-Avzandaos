package com.example.employeesreports.database.Dao;

import com.example.employeesreports.database.MySQLConnection;
import com.example.employeesreports.models.Employee;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class EmployeeDao extends MySQLConnection implements Dao<Employee> {
    Connection conn = getConnection();
    @Override
    public Optional<Employee> findById(int id) {
        Optional<Employee> optionalEmployee = Optional.empty();
        String query = "select * from employee where id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if( rs.next() ){
                Employee employee = createEmpleoyee(rs);
                
                optionalEmployee = Optional.of(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optionalEmployee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = FXCollections.observableArrayList();
        String query = "select * from employees limit 10";

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()){
                employees.add( createEmpleoyee(rs) );
            }

            for (Employee emp : employees
                 ) {
                System.out.println( emp.getFirstName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return employees;
    }

    @Override
    public boolean save(Employee record) {
        return false;
    }

    @Override
    public boolean update(Employee record) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
    
    private Employee createEmpleoyee(ResultSet rs ){
        try{
            return new Employee(
                    rs.getInt("emp_no"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("gender"),
                    rs.getDate("hire_date")
            );
        }catch ( SQLException e ){
            throw new RuntimeException(e);
        }
    }
}
