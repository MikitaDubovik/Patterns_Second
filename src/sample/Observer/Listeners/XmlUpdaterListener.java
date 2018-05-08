package sample.Observer.Listeners;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import sample.Model.Task;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class XmlUpdaterListener implements EventListener {
    @Override
    public void update(ArrayList<Task> tasks) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement =
                    doc.createElementNS("", "projects");

            doc.appendChild(rootElement);
            for (Task task: tasks) {
                rootElement.appendChild(getInfo(doc,task));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File("src/resources/Project_tracking.xml"));
            transformer.transform(source, file);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Node getInfo(Document doc, Task task) {
        Element language = doc.createElement("info");

        language.appendChild(getInfoElements(doc, "project-name", task.getProjectName()));

        language.appendChild(getInfoElements(doc, "aim", task.getAim()));

        language.appendChild(getInfoElements(doc, "aim-id", task.getAimId()));

        language.appendChild(getInfoElements(doc, "planed-hours", task.getPlanedHours()));

        language.appendChild(getInfoElements(doc, "people", task.getPeople()));

        language.appendChild(getInfoElements(doc, "fact-hours", task.getFactHours()));

        language.appendChild(getInfoElements(doc,"hours-left", task.getHoursLeft()));

        language.appendChild(getInfoElements(doc, "date", task.getDate()));
        return language;
    }

    private static Node getInfoElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
