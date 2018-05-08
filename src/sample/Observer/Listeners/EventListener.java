package sample.Observer.Listeners;

import sample.Model.Task;

import java.util.ArrayList;

public interface EventListener {
    public void update(ArrayList<Task> tasks);
}
