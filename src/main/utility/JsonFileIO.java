package utility;

import model.Task;
import parsers.TaskParser;
import persistence.Jsonifier;

import java.io.*;
import java.util.List;

// File input/output operations
public class JsonFileIO {
    public static final File jsonDataFile = new File("./resources/json/tasks.json");


    // EFFECTS: attempts to read jsonDataFile and parse it
    //           returns a list of tasks from the content of jsonDataFile
    public static List<Task> read() {
        TaskParser taskParser = new TaskParser();
        String jsonInfor = readJson(jsonDataFile);
        return taskParser.parse(jsonInfor);
    }


    public static String readJson(File file) {
        String message = "";

        String fileName = file.toString(); //Name of file to open
        String line = null;   //reference ne line at time
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                message += (line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return message.toString();
    }

     //EFFECTS: saves the tasks to jsonDataFile
    public static void write(List<Task> tasks) {
        String filename = jsonDataFile.getPath();
        try {
            File file = new File(filename);
            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(Jsonifier.taskListToJson(tasks).toString());
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("sorry, no can do");
        }
    }

}




























