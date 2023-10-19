package com.example.employeesreports.DAO;



import com.example.employeesreports.database.MySQLConnection;
import com.example.employeesreports.models.Departments;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentsDao extends MySQLConnection implements Dao<Departments> {
    Connection conn = getConnection();

    public Departments getDepartment(String department_id)
    {
        Departments department = new Departments();
        try {
            String query = "select * from departments where dept_no = " + department_id;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next())
            {
                department.setDept_no(department_id);
                department.setDept_name(rs.getString("dept_name"));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return department;
    }

    @Override
    public Optional<Departments> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Departments> findAll() {
        List<Departments> departments = new ArrayList<>();
        try {
            String query = "select * from departments";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                departments.add(new Departments(rs.getString("dept_no"), rs.getString("dept_name")));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return departments;
    }

    @Override
    public boolean save(Departments record) {
        return false;
    }

    @Override
    public boolean update(Departments record) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
