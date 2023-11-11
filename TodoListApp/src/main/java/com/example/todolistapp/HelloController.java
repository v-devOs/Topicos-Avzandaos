package com.example.todolistapp;

import com.example.todolistapp.models.Task;
import com.example.todolistapp.reports.PDFTASKSReport;
import db.dao.TaskDao;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Random;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

public class HelloController implements Initializable {

    @FXML
    public Button btnGenerateReportTaskPDF;
    @FXML
    DatePicker datePicker;
    @FXML
    private TableView<Task> tableTask;
    @FXML
    TextField txtTitle, txtDescription;
    @FXML
    Button btnAdd, btnReset;
    TaskDao taskDao = new TaskDao();
    Boolean editMode = false;
    Task taskSelected = null;
    Random random = new Random();
    ObservableList<Task> tasksList = FXCollections.observableArrayList();
    public static final String DEST1 = "results/task_report_pdf.pdf";


    @FXML
    protected void onAddButtonClick(){
        if( editMode ){
            tasksList.get(tasksList.indexOf(taskSelected)).setName(txtTitle.getText());
            tasksList.get(tasksList.indexOf(taskSelected)).setDescription(txtDescription.getText());
            editMode = false;
            btnAdd.setText("Add");
        }
        else{
            Task newTask = new Task();
            newTask.setId(random.nextInt() * 1000);
            newTask.setName(txtTitle.getText());
            newTask.setDescription(txtDescription.getText());
            newTask.setStatus(false);
            newTask.setDueDate(Date.valueOf(datePicker.getValue()));
            tasksList.add(newTask);
            taskDao.save(newTask);
        }
        tableTask.refresh();
        onResetButtonClick();

    }
    @FXML
    protected void onResetButtonClick()
    {
        txtTitle.setText("");
        txtDescription.setText("");
        txtTitle.requestFocus();
        btnAdd.setText("Add");
    }
    private void showMessage(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableInfo();
    }

    private void initTableInfo(){
        tasksList = FXCollections.observableArrayList( taskDao.findAll() );
        tableTask.setItems(tasksList);
        tableTask.setColumnResizePolicy((param) -> true );
        Platform.runLater(() -> customResize(tableTask));
        //implements double click on tableview
        tableTask.setOnMouseClicked((mouseEvent)->{
            if (mouseEvent.getClickCount() == 2)
            {
                //load task info in the form
                taskSelected = tableTask.getSelectionModel().getSelectedItem();
                txtTitle.setText(taskSelected.getName());
                txtDescription.setText(taskSelected.getDescription());
                editMode = true;
                btnAdd.setText("Save");
            }
        });
    }

    public void customResize(TableView<?> view) {
        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth()+((tableWidth-width.get())/view.getColumns().size()));
            });
        }
    }

    @FXML
    protected void onGeneratePDFReport() throws IOException {
        new PDFTASKSReport().createPDF(DEST1);
        openFile(DEST1);
    }
    private void openFile(String filename)
    {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(filename);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }
}