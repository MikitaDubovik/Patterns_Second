package sample.Observer;

import sample.Model.Task;

import java.util.ArrayList;

public class Editor {

    public EventManager events;

    public Editor() {
        this.events = new EventManager("update", "add");
    }

    public void updateFile(ArrayList<Task> tasks) {
        events.notify("update", tasks);
    }

    public void addFile(ArrayList<Task> tasks) {
        events.notify("add", tasks);
    }
}
