package sample.FactoryMethod;

import sample.Model.Task;

import java.io.File;
import java.util.ArrayList;

public abstract class Factory {
    public ArrayList<Task> Parse(File file){
        Parser parser = CreateParser();
        return parser.Parse(file);
    }

    public abstract Parser CreateParser();
}
