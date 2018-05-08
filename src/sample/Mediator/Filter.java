package sample.Mediator;

import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.Mediator.Components.*;
import sample.Model.Task;

import java.util.ArrayList;
import java.util.Arrays;

public class Filter implements Mediator {
    private ChooseFactHours chooseFactHours;
    private ChooseId chooseId;
    private ChooseNameProj chooseNameProj;
    private ChooseNameTask chooseNameTask;
    private ChoosePlanedHours choosePlanedHours;
    private NumberFilter number;
    private TableView<Task> taskView;

    public FilteredList<Task> FilterByChooseFactHours(TableView<Task> tasks, FilteredList<Task> taskFilteredList) {
        if (taskView == null)
        {
            taskView = tasks;
        }

        if (taskFilteredList == null) {
            FilteredList<Task> filteredList = taskView.getItems().filtered(task -> task.getFactHours().equals(chooseFactHours.getSelectionModel().getSelectedItem()));
            return filteredList;
        } else {
            FilteredList<Task> filteredList = new FilteredList<>(taskFilteredList, task -> task.getFactHours().equals(chooseFactHours.getSelectionModel().getSelectedItem()));
            return filteredList;
        }
    }

    @Override
    public FilteredList<Task> FilterByChooseId(TableView<Task> tasks, FilteredList<Task> taskFilteredList) {
        if (taskView == null)
        {
            taskView = tasks;
        }

        if (taskFilteredList == null) {
            FilteredList<Task> filteredList = taskView.getItems().filtered(task -> task.getAimId().equals(chooseId.getSelectionModel().getSelectedItem()));
            return filteredList;
        } else {
            FilteredList<Task> filteredList = new FilteredList<>(taskFilteredList, task -> task.getAimId().equals(chooseId.getSelectionModel().getSelectedItem()));
            return filteredList;
        }
    }

    @Override
    public FilteredList<Task> FilterByChooseNameProj(TableView<Task> tasks, FilteredList<Task> taskFilteredList) {
        if (taskView == null)
        {
            taskView = tasks;
        }

        if (taskFilteredList == null) {
            FilteredList<Task> filteredList = taskView.getItems().filtered(task -> task.getProjectName().equals(chooseNameProj.getSelectionModel().getSelectedItem()));
            return filteredList;
        } else {
            FilteredList<Task> filteredList = new FilteredList<>(taskFilteredList, task -> task.getProjectName().equals(chooseNameProj.getSelectionModel().getSelectedItem()));
            return filteredList;
        }
    }

    @Override
    public FilteredList<Task> FilterByChooseNameTask(TableView<Task> tasks, FilteredList<Task> taskFilteredList) {
        if (taskView == null)
        {
            taskView = tasks;
        }

        if (taskFilteredList == null) {
            FilteredList<Task> filteredList = taskView.getItems().filtered(task -> task.getAim().equals(chooseNameTask.getSelectionModel().getSelectedItem()));
            return filteredList;
        } else {
            FilteredList<Task> filteredList = new FilteredList<>(taskFilteredList, task -> task.getAim().equals(chooseNameTask.getSelectionModel().getSelectedItem()));
            return filteredList;
        }
    }

    @Override
    public FilteredList<Task> FilterByChoosePlanedHours(TableView<Task> tasks, FilteredList<Task> taskFilteredList) {
        if (taskView == null)
        {
            taskView = tasks;
        }

        if (taskFilteredList == null) {
            FilteredList<Task> filteredList = taskView.getItems().filtered(task -> task.getPlanedHours().equals(choosePlanedHours.getSelectionModel().getSelectedItem()));
            return filteredList;
        } else {
            FilteredList<Task> filteredList = new FilteredList<>(taskFilteredList, task -> task.getPlanedHours().equals(choosePlanedHours.getSelectionModel().getSelectedItem()));
            return filteredList;
        }
    }

    @Override
    public ArrayList<Task> FilterByChooseNumber(TableView<Task> tasks, FilteredList<Task> taskFilteredList) {
        if (taskView == null)
            taskView = tasks;
        if(taskFilteredList != null) {
            TextField textField = number.getTextField();
            ArrayList<Task> toTable = new ArrayList<>(taskFilteredList);
            int number = Integer.parseInt(textField.getText());
            if (number > 0) {
                toTable = getTasks(number, toTable);
                if (toTable != null) return toTable;
            }
        } else {
            TextField textField = number.getTextField();
            int number = Integer.parseInt(textField.getText());
            if (number > 0) {
                ArrayList<Task> toTable = new ArrayList<>(taskView.getItems());
                toTable = getTasks(number, toTable);
                if (toTable != null) return toTable;
            } else
                return null;
        }
        return null;
    }

    private ArrayList<Task> getTasks(int number, ArrayList<Task> toTable) {
        if (toTable.size() > number) {
            Task[] array = new Task[number];
            for (int i = 0; i < number; i++) {
                array[i] = toTable.get(i);
            }
            toTable = new ArrayList<>(Arrays.asList(array));
            return toTable;
        }
        return null;
    }


    @Override
    public void RegisterComponent(Component component) {
        component.SetMediator(this);
        switch (component.GetName()) {
            case "ChooseFactHours":
                chooseFactHours = (ChooseFactHours) component;
                break;
            case "ChooseId":
                chooseId = (ChooseId) component;
                break;
            case "ChooseNameProj":
                chooseNameProj = (ChooseNameProj) component;
                break;
            case "ChooseNameTask":
                chooseNameTask = (ChooseNameTask) component;
                break;
            case "ChoosePlanedHours":
                choosePlanedHours = (ChoosePlanedHours) component;
                break;
            case "NumberFilter":
                number = (NumberFilter) component;
                break;
        }
    }
}
