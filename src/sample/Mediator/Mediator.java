package sample.Mediator;

import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableView;
import sample.Mediator.Components.Component;
import sample.Model.Task;

import java.util.ArrayList;

public interface Mediator {
    FilteredList<Task> FilterByChooseFactHours(TableView<Task> tasks, FilteredList<Task> taskFilteredList);
    FilteredList<Task> FilterByChooseId(TableView<Task> tasks, FilteredList<Task> taskFilteredList);
    FilteredList<Task> FilterByChooseNameProj(TableView<Task> tasks, FilteredList<Task> taskFilteredList);
    FilteredList<Task> FilterByChooseNameTask(TableView<Task> tasks, FilteredList<Task> taskFilteredList);
    FilteredList<Task> FilterByChoosePlanedHours(TableView<Task> tasks, FilteredList<Task> taskFilteredList);
    ArrayList<Task> FilterByChooseNumber(TableView<Task> tasks, FilteredList<Task> taskFilteredList);

    void RegisterComponent(Component component);
}
