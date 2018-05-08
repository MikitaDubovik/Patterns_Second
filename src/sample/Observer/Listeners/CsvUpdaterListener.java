package sample.Observer.Listeners;

import sample.Model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvUpdaterListener implements EventListener {
    private static final char DEFAULT_SEPARATOR = ';';
    @Override
    public void update(ArrayList<Task> tasks) {
        String csvFile = "src/resources/Project_tracking.csv";
        try{
        FileWriter writer = new FileWriter(csvFile);
            writeLine(writer, Arrays.asList("project-name", "aim", "aim-id", "planed-hours", "people", "fact-hours","hours-left","date"));
            for (Task t: tasks) {
                List<String> list = new ArrayList<>();

                list.add(t.getProjectName());
                list.add(t.getAim());
                list.add(t.getAimId());
                list.add(t.getPlanedHours());
                list.add(t.getPeople());
                list.add(t.getFactHours());
                list.add(t.getHoursLeft());
                list.add(t.getDate());

                writeLine(writer, list);
            }

            writer.flush();
            writer.close();
        }
        catch (Exception e){
        }
    }

    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());


    }
}
