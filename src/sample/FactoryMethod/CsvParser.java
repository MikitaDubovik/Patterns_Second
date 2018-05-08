package sample.FactoryMethod;

import sample.Model.Task;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvParser implements Parser {
    @Override
    public ArrayList<Task> Parse(File file) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(file.toPath().toString()));
            ArrayList<Task> taskList = new ArrayList<>();
            Boolean flag = false;
            for (String line : lines) {
                Task task = new Task();
                String[] result = line.split(";");
                if (flag) {
                    task.setProjectName(result[0]);
                    task.setAim(result[1]);
                    task.setAimId(result[2]);
                    task.setPlanedHours(result[3]);
                    task.setPeople(result[4]);
                    task.setFactHours(result[5]);
                    task.setHoursLeft(result[6]);
                    task.setDate(result[7]);
                    taskList.add(task);
                }
                flag = true;
            }
            return taskList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
