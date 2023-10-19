package com.example.employeesreports.DAO;


import com.example.employeesreports.database.MySQLConnection;
import com.example.employeesreports.models.Employees;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeesDao extends MySQLConnection implements Dao<Employees> {

    Connection conn = getConnection();
    ObservableList<Employees> listEmployees = FXCollections.observableArrayList();


    @Override
    public Optional<Employees> findById(int id) {
        Optional<Employees> optEmp = Optional.empty();
        String query = "select * from product where id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() )
            {
                Employees employee = new Employees();
                employee.setEmp_no(rs.getInt("emp_no"));
                employee.setFirst_name(rs.getString("first_name"));
                employee.setLast_name(rs.getString("last_name"));
                employee.setGender(rs.getString("gender").charAt(0));
                java.util.Date birth_date = null;
                java.util.Date hire_date = null;
                try {
                    birth_date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birth_date"));
                    hire_date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("hire_date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                employee.setBirth_date(birth_date);
                employee.setHire_date(hire_date);

                optEmp = Optional.of(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optEmp;
    }

    public List<Employees> findAll()
    {
        try {
            String query = "select * from employees limit 1000";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                java.util.Date birth_date = null;
                java.util.Date hire_date = null;
                try {
                    birth_date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birth_date"));
                    hire_date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("hire_date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                listEmployees.add(
                        new Employees(rs.getInt("emp_no"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("gender").charAt(0),
                                birth_date, hire_date));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listEmployees;
    }

    public Map<String, Integer> getTotalEmployeesDepartment()
    {
        Map<String, Integer> results = new HashMap<>();
        String sql = "select d.dept_name, count(*) as total " +
                "from departments d " +
                "join dept_emp e on d.dept_no = e.dept_no " +
                "group by 1 " +
                "order by 2 desc;";
        try(PreparedStatement ps = conn.prepareStatement(sql))
        {

            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    results.put(rs.getString("dept_name"), rs.getInt("total"));
                }
                return results;
            }

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Employees> getEmployeesByDepartment(String dept_no)
    {
        listEmployees.clear();
        try {
            String query = "select e.*, d.dept_name, s.salary\n" +
                    "from departments d\n" +
                    "join dept_emp de on d.dept_no = de.dept_no\n" +
                    "join employees e on de.emp_no = e.emp_no\n" +
                    "join salaries s on e.emp_no = s.emp_no\n" +
                    "where d.dept_no='" + dept_no + "' and de.to_date='9999-01-01' and s.to_date='9999-01-01'\n" +
                    "order by e.emp_no limit 1000;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                java.util.Date birth_date = null;
                java.util.Date hire_date = null;
                try {
                    birth_date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birth_date"));
                    hire_date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("hire_date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                listEmployees.add(
                        new Employees(rs.getInt("emp_no"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("gender").charAt(0),
                                birth_date, hire_date,
                                rs.getString("dept_name"),
                                rs.getDouble("salary"))
                );
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listEmployees;
    }

    @Override
    public boolean save(Employees employee)
    {
        try {
            String query = "insert into employees (first_name, last_name, gender, birth_date, hire_date) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, employee.getFirst_name());
            ps.setString(2, employee.getLast_name());
            ps.setString(3, employee.getGender() + "");
            ps.setDate(4, Date.valueOf(employee.getBirth_date().toString()));
            ps.setDate(5, Date.valueOf(employee.getHire_date().toString()));
            ps.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Employees employee)
    {
        try {
            String query = "update employees set first_name=?, last_name=?, gender=?, birth_date=?, hire_date=? where emp_no = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, employee.getFirst_name());
            ps.setString(2, employee.getLast_name());
            ps.setString(3, employee.getGender() + "");
            ps.setDate(4, Date.valueOf(employee.getBirth_date().toString()));
            ps.setDate(5, Date.valueOf(employee.getHire_date().toString()));
            ps.setInt(6, employee.getEmp_no());
            ps.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int employee_id)
    {
        try {
            String query = "delete from employees where emp_no = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, employee_id);
            ps.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

}
