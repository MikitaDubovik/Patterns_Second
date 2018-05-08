package sample.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.FactoryMethod.CsvFactory;
import sample.FactoryMethod.Factory;
import sample.FactoryMethod.XmlFactory;
import sample.Model.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    public Button buttonExit;
    public Button buttonCsv;
    public Button buttonXml;


    public void ParseXml() throws IOException {
        File file = new File("src/resources/Project_tracking.xml");
        Factory factory = new XmlFactory();
        ArrayList<Task> taskList = factory.Parse(file);
        GoToView(taskList);
    }

    public void ParseCsv() throws IOException {
        File file = new File("src/resources/Project_tracking.csv");
        Factory factory = new CsvFactory();
        ArrayList<Task> taskList = factory.Parse(file);
        GoToView(taskList);
    }

    private void GoToView(ArrayList<Task> taskList) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "../View/Task.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(loader.load()));

        Display controller = loader.getController();
        controller.InitDate(taskList);

        stage.show();
    }

    public void Exit(){
        System.exit(0);
    }
}
