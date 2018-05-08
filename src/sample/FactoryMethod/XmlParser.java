package sample.FactoryMethod;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sample.Model.Task;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class XmlParser implements Parser {
    @Override
    public ArrayList<Task> Parse(File file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            NodeList nList = doc.getElementsByTagName("info");
            ArrayList<Task> taskList = new ArrayList<>();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Task task = new Task();
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    task.setProjectName(eElement.getElementsByTagName("project-name").item(0).getTextContent());
                    task.setAim(eElement.getElementsByTagName("aim").item(0).getTextContent());
                    task.setAimId(eElement.getElementsByTagName("aim-id").item(0).getTextContent());
                    task.setPlanedHours(eElement.getElementsByTagName("planed-hours").item(0).getTextContent());
                    task.setPeople(eElement.getElementsByTagName("people").item(0).getTextContent());
                    task.setFactHours(eElement.getElementsByTagName("fact-hours").item(0).getTextContent());
                    task.setHoursLeft(eElement.getElementsByTagName("hours-left").item(0).getTextContent());
                    task.setDate(eElement.getElementsByTagName("date").item(0).getTextContent());

                    taskList.add(task);
                }
            }
            return taskList;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
