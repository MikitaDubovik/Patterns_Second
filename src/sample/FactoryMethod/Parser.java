package sample.FactoryMethod;

import sample.Model.Task;

import java.io.File;
import java.util.ArrayList;

public interface Parser {
    ArrayList<Task> Parse(File file);
}
