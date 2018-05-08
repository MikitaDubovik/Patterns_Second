package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Mediator.Components.*;
import sample.Mediator.Filter;
import sample.Mediator.Mediator;
import sample.Model.Task;
import sample.Observer.Editor;
import sample.Observer.Listeners.CsvUpdaterListener;
import sample.Observer.Listeners.XmlUpdaterListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Display {
    private ArrayList<Task> tasks;
    private boolean flag;
    private Editor editor;

    /////Graph
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private BarChart<String, Number> barChart;
//////////////////////////////////

    ////Table
    @FXML
    private TableView<Task> tableTask;

    @FXML
    private TableColumn<Task, String> projectName, aim, aimId, planedHours, people, factHours, hoursLeft;

    @FXML
    private ChooseNameProj chooseNameProj;
    @FXML
    private ChooseNameTask chooseNameTask;
    @FXML
    private ChooseId chooseId;
    @FXML
    private ChoosePlanedHours choosePlanedHours;
    @FXML
    private ChooseFactHours chooseFactHours;

    @FXML
    private TextField number;
/////////////////////////////////////


    ////Add
    @FXML
    private TextField idField, planedHoursFields, peopleField, factHoursField, hoursLeftField;

    @FXML
    private ChoiceBox<String> choiceProjName, choiceTaskName;

    /////////////////////////////////////////////
    public void onRefresh() {
        Mediator mediator = new Filter();
        FilteredList<Task> taskList = null;
        ArrayList<Task> tableNumber = null;

        if (chooseNameProj.getSelectionModel().getSelectedItem() != null) {
            mediator.RegisterComponent(chooseNameProj);
            taskList = mediator.FilterByChooseNameProj(tableTask, taskList);
        }

        if (chooseNameTask.getSelectionModel().getSelectedItem() != null) {
            mediator.RegisterComponent(chooseNameTask);
            taskList = mediator.FilterByChooseNameTask(tableTask, taskList);
        }

        if (chooseId.getSelectionModel().getSelectedItem() != null) {
            mediator.RegisterComponent(chooseId);
            taskList = mediator.FilterByChooseId(tableTask, taskList);
        }

        if (choosePlanedHours.getSelectionModel().getSelectedItem() != null) {
            mediator.RegisterComponent(choosePlanedHours);
            taskList = mediator.FilterByChoosePlanedHours(tableTask, taskList);
        }

        if (chooseFactHours.getSelectionModel().getSelectedItem() != null) {
            mediator.RegisterComponent(chooseFactHours);
            taskList = mediator.FilterByChooseFactHours(tableTask, taskList);
        }

        if (number.getText().isEmpty() == false) {
            NumberFilter component = new NumberFilter();
            component.setTextField(number);
            mediator.RegisterComponent(component);
            tableNumber = mediator.FilterByChooseNumber(tableTask, taskList);
        }

        if ((taskList != null && !taskList.isEmpty()) || tableNumber != null) {
            if (tableNumber != null) {
                tableTask.getItems().clear();
                InitDate(tableNumber);
            } else if (taskList != null && !taskList.isEmpty()) {
                ArrayList<Task> taskArrayList = new ArrayList<>(taskList);
                tableTask.getItems().clear();
                InitDate(taskArrayList);
            }
        } else {
            tableTask.getItems().clear();
            InitDate(this.tasks);
        }
    }

    public void InitDate(ArrayList<Task> tasks) {
        if (!flag)
            this.tasks = tasks;
        Map<String, Integer> lineChartFill = new HashMap<>();
        Map<String, Integer> barChartFill = new HashMap<>();

        Set<String> nameProj = new HashSet<>();
        Set<String> nameTask = new HashSet<>();
        Set<String> id = new HashSet<>();
        Set<String> chPlanedHours = new HashSet<>();
        Set<String> factHours = new HashSet<>();

        for (Task task : tasks) {
            int number = Integer.parseInt(task.getPeople());
            int planedHours = Integer.parseInt(task.getPlanedHours());
            if (lineChartFill.get(task.getAim()) != null) {
                number += lineChartFill.get(task.getAim());
                planedHours += barChartFill.get(task.getAim());
            }

            lineChartFill.put(task.getAim(), number);
            barChartFill.put(task.getAim(), planedHours);

            tableTask.getItems().add(task);

            nameProj.add(task.getProjectName());
            nameTask.add(task.getAim());
            id.add(task.getAimId());
            chPlanedHours.add(task.getPlanedHours());
            factHours.add(task.getFactHours());
        }

        TableFill();

        if (!flag) {
            LineChartFill(lineChartFill);

            BarChartFill(barChartFill);
        }

        flag = true;

        ObservableList<String> availableChoices = FXCollections.observableArrayList(nameProj);
        chooseNameProj.setItems(availableChoices);
        choiceProjName.setItems(availableChoices);

        availableChoices = FXCollections.observableArrayList(nameTask);
        chooseNameTask.setItems(availableChoices);
        choiceTaskName.setItems(availableChoices);

        availableChoices = FXCollections.observableArrayList(id);
        chooseId.setItems(availableChoices);

        availableChoices = FXCollections.observableArrayList(chPlanedHours);
        choosePlanedHours.setItems(availableChoices);

        availableChoices = FXCollections.observableArrayList(factHours);
        chooseFactHours.setItems(availableChoices);
    }

    private void TableFill() {
        projectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        aim.setCellValueFactory(new PropertyValueFactory<>("aim"));
        aimId.setCellValueFactory(new PropertyValueFactory<>("aimId"));
        planedHours.setCellValueFactory(new PropertyValueFactory<>("planedHours"));
        people.setCellValueFactory(new PropertyValueFactory<>("people"));
        factHours.setCellValueFactory(new PropertyValueFactory<>("factHours"));
        hoursLeft.setCellValueFactory(new PropertyValueFactory<>("hoursLeft"));
    }

    private void LineChartFill(Map<String, Integer> map) {

        XYChart.Series series = new XYChart.Series();
        series.setName("Line of the people");
        for (Map.Entry entry : map.entrySet()) {
            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }

        lineChart.getData().add(series);
    }

    private void BarChartFill(Map<String, Integer> map) {

        XYChart.Series series = new XYChart.Series();
        series.setName("Histogram of the workability");
        for (Map.Entry entry : map.entrySet()) {
            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }

        barChart.getData().add(series);
    }

    //////////ADD///////////////
    public void onAdd() {

        Task task = new Task(choiceProjName.getValue(), choiceTaskName.getValue(),
                idField.getText(), planedHoursFields.getText(),
                peopleField.getText(), factHoursField.getText(),
                hoursLeftField.getText(), "20.01.2018");
        tasks.add(task);
        editor = new Editor();
        editor.events.subscribe("add", new XmlUpdaterListener());
        editor.events.subscribe("add", new CsvUpdaterListener());
        try {
            editor.addFile(tasks);
            tableTask.getItems().clear();
            InitDate(tasks);
        } catch (Exception e) {

        }
    }
    ///////////////////////////

    ////////Save//////////////
    public void onSave() throws IOException {
        BufferedWriter  file = new BufferedWriter(new FileWriter("src/resources/Filters.txt"));
        String tempchooseNameProj = chooseNameProj.getValue();
        if (tempchooseNameProj == null)
            tempchooseNameProj = "Null";

        String tempchooseNameTask = chooseNameTask.getValue();
        if (tempchooseNameTask == null)
            tempchooseNameTask = "Null";

        String tempchooseId = chooseId.getValue();
        if (tempchooseId == null)
            tempchooseId = "Null";

        String tempchoosePlanedHours = choosePlanedHours.getValue();
        if ( tempchoosePlanedHours == null)
            tempchoosePlanedHours = "Null";

        String tempchooseFactHours = chooseFactHours.getValue();
        if (tempchooseFactHours == null)
            tempchooseFactHours = "Null";

        String tempnumber = number.getText();
        if(tempnumber.isEmpty() || tempnumber==null)
            tempnumber = "Null";

        String temp = "ChooseNameProj = " + tempchooseNameProj + " chooseNameTask = " + tempchooseNameTask + " ChooseId = " + tempchooseId
                      + " ChoosePlanedHours = " + tempchoosePlanedHours + " ChooseFactHours = " + tempchooseFactHours + " Number = " + tempnumber;
        file.write(temp);
        file.close();
    }
    //////////////////////////
}
