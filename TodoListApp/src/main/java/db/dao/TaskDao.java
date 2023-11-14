package db.dao;


import com.example.todolistapp.models.Task;
import db.MySQLConnection;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskDao extends MySQLConnection implements Dao<Task> {
    Connection conn = getConnection();
    @Override
    public Optional<Task> findById(int id) {
        Optional<Task> optionalTask = Optional.empty();
        String query = "select * from tasks where id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            if ( rs.next() )
            {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setStatus(rs.getBoolean("status"));
                // task.setDueDate(rs.getDate("dueDate"));
                optionalTask = Optional.of(task);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optionalTask;
    }
    @Override
    public List<Task> findAll() {

        List<Task> taskList = FXCollections.observableArrayList();
        String query = "select * from tasks";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setStatus(rs.getBoolean("status"));
                task.setDueDate(rs.getDate("dueDate"));
                task.setLabel(rs.getString("label"));
                taskList.add(task);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskList;
    }

    @Override
    public boolean save(Task task) {
        String query = "insert into tasks " +
                " (name, description, status, dueDate, label)" +
                " values (?, ?, ?, ? ,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setBoolean(3, task.getStatus());
            ps.setDate(4, task.getDueDate());
            ps.setString(5,task.getLabel());
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean update(Task task) {
        String query = "update tasks set name=?, description=?, status=?, dueDate=?, label=?  where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setBoolean(3, task.getStatus());
            ps.setDate(4, task.getDueDate());
            ps.setString(5, task.getLabel());
            ps.setInt(6, task.getId());

            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int task_id) {
        String query = "delete from tasks where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, task_id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Task, Integer> getTaskToExcelReport(){
        Map<Task, Integer> results = new HashMap<>();

        List<Task> tasks = findAll();

        for (Task task: tasks) {
            results.put(task, task.getId());
        }

        return results;
    }

    public int[] getTotalCompleteAndIncompleteTask(){
        int[] totals = new int[2];

        String query = "select * from (\n" +
                "    ( select count(t.status) as incompletas\n" +
                "        from tasks t\n" +
                "        where t.status = false\n" +
                "    ) as incompletas,\n" +
                "    ( select count(t.status) as completas\n" +
                "          from tasks t\n" +
                "          where t.status = true\n" +
                "    ) as completas\n" +
                ")";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){

                totals[0] = rs.getInt("incompletas");
                totals[1] = rs.getInt("completas");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return totals;
    }
}