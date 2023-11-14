package com.example.todolistapp;

import com.example.todolistapp.models.Task;
import com.example.todolistapp.reports.ExcelReport;
import com.example.todolistapp.reports.PDFTASKSReport;
import com.example.todolistapp.utils.AlertCustom;
import com.example.todolistapp.utils.OpenFile;
import com.example.todolistapp.utils.ResizeTable;
import db.dao.TaskDao;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

public class HelloController implements Initializable {
    @FXML
    public Button btnGenerateReportTaskPDF, btnGenerateReportTaskExcel, btnMarkAsDone, btnAdd, btnReset;
    @FXML
    DatePicker datePicker;
    @FXML
    private TableView<Task> tableTask;
    @FXML
    TextField txtTitle, txtDescription, txtLabel;
    @FXML
    GridPane chartsContainer;

    TaskDao taskDao = new TaskDao();
    Boolean editMode = false;
    Task taskSelected = null;
    ObservableList<Task> tasksList = FXCollections.observableArrayList();
    public static final String DEST1 = "results/task_report_pdf.pdf";
    public static final String DEST2 = "results/task_report_excel.xlsx";

    OpenFile openFile = new OpenFile();
    ResizeTable resizeTable = new ResizeTable();
    AlertCustom alertCustom = new AlertCustom();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableInfo();
        generateDashboard();
    }

    private void initTableInfo(){
        tasksList = FXCollections.observableArrayList( taskDao.findAll() );
        tableTask.setItems(tasksList);
        tableTask.setColumnResizePolicy((param) -> true );
        Platform.runLater(() -> resizeTable.customResize(tableTask));
        //implements double click on tableview
        tableTask.setOnMouseClicked((mouseEvent)->{
            if (mouseEvent.getClickCount() == 2)
            {
                //load task info in the form
                taskSelected = tableTask.getSelectionModel().getSelectedItem();
                txtTitle.setText(taskSelected.getName());
                txtDescription.setText(taskSelected.getDescription());
                txtLabel.setText(taskSelected.getLabel());
                datePicker.setValue(taskSelected.getDueDate().toLocalDate());
                editMode = true;
                btnAdd.setText("Save");
            }
        });
    }

    @FXML
    protected void onAddButtonClick(){
        if( editMode ){
            tasksList.get(tasksList.indexOf(taskSelected)).setName(txtTitle.getText());
            tasksList.get(tasksList.indexOf(taskSelected)).setDescription(txtDescription.getText());
            tasksList.get(tasksList.indexOf(taskSelected)).setLabel(txtLabel.getText());
            editMode = false;
            btnAdd.setText("Add");
            taskDao.update(taskSelected);
        }
        else{
            Task newTask = new Task();
            Task saved;
            newTask.setName(txtTitle.getText());
            newTask.setDescription(txtDescription.getText());
            newTask.setStatus(false);
            newTask.setDueDate(Date.valueOf(datePicker.getValue()));
            newTask.setLabel(txtLabel.getText());
            taskDao.save(newTask);
        }
        initTableInfo();
        onResetButtonClick();
        generateDashboard();

    }
    @FXML
    protected void onResetButtonClick()
    {
        txtTitle.setText("");
        txtDescription.setText("");
        txtTitle.requestFocus();
        txtLabel.setText("");
        datePicker.setValue(null);
        btnAdd.setText("Add");
        editMode = false;
    }
    @FXML
    protected void onMarkAsDoneTask(){

        if( taskSelected == null ){
            alertCustom.showMessage("Selecciona una tarea para marcar", Alert.AlertType.ERROR);
        }
        else{
            taskSelected.setStatus(true);
            taskDao.update(taskSelected);
            tableTask.refresh();
            generateDashboard();
            taskSelected = null;
            editMode = false;
            onResetButtonClick();
        }
    }

    @FXML
    protected void onDeleteTask(){
        if( taskSelected == null ){
            alertCustom.showMessage("Seleccione una tarea para borrar", Alert.AlertType.ERROR);
        }else{
            taskDao.delete(taskSelected.getId());
            initTableInfo();
            generateDashboard();
            taskSelected = null;
            editMode = false;
            onResetButtonClick();
        }
    }
    @FXML
    protected void onGeneratePDFReport() throws IOException {
        new PDFTASKSReport().createPDF(DEST1);
        openFile.openFile(DEST1);
    }

    @FXML
    protected void onGenerateExcelReport(){
        new ExcelReport().createExcel(DEST2);
        openFile.openFile(DEST2);
    }

    private void generateDashboard(){
        chartsContainer.add(getPieChart(), 0,0);
    }

    private PieChart getPieChart(){
        int[] totalTask = taskDao.getTotalCompleteAndIncompleteTask();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Tasks Inompletes", totalTask[0]),
                        new PieChart.Data("Tasks Completes", totalTask[1])
                );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Comparation of tasks");
        chart.setStyle("-fx-background-color: #202020");


        return chart;
    }
}