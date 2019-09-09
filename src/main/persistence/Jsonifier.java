package persistence;


import model.DueDate;
import model.Priority;
import model.Tag;
import model.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

// Converts model elements to JSON objects
public class Jsonifier {

    // EFFECTS: returns JSON representation of tag
    public static JSONObject tagToJson(Tag tag) {
        JSONObject tagToJson = new JSONObject();
        tagToJson.put("name", tag.getName());
        return tagToJson;
    }

    // EFFECTS: returns JSON representation of priority
    public static JSONObject priorityToJson(Priority priority) {
        JSONObject priorityToJson = new JSONObject();
        priorityToJson.put("important", priority.isImportant());
        priorityToJson.put("urgent", priority.isUrgent());
        return priorityToJson;
    }

    // EFFECTS: returns JSON representation of dueDate
    public static JSONObject dueDateToJson(DueDate dueDate) {
        if (dueDate == null) {
            return null;
        } else {
            JSONObject dueDateToJson = new JSONObject();
            dueDateToJson.put("year", dueDate.getDate().getYear());
            dueDateToJson.put("month", dueDate.getDate().getMonth());
            dueDateToJson.put("day", dueDate.getDate().getDay());
            dueDateToJson.put("hour", dueDate.getDate().getHours());
            dueDateToJson.put("minute", dueDate.getDate().getMinutes());
            return dueDateToJson;
        }
    }

    // EFFECTS: returns JSON representation of task
    public static JSONObject taskToJson(Task task) {
        JSONObject taskToJson = new JSONObject();
        taskToJson.put("description", task.getDescription());
        JSONArray tagArray = new JSONArray();
        for (Tag t: task.getTags()) {
            tagArray.put(tagToJson(t));
        }
        taskToJson.put("tags",tagArray);
        taskToJson.put("due-date",dueDateToJson(task.getDueDate()));
        if (!taskToJson.has("due-date")) {
            taskToJson.put("due-date", JSONObject.NULL);
        }
        taskToJson.put("priority",priorityToJson(task.getPriority()));
        taskToJson.put("status",task.getStatus());
        return taskToJson;
    }

    // EFFECTS: returns JSON array representing list of tasks
    public static JSONArray taskListToJson(List<Task> tasks) {
        JSONArray taskListToJson = new JSONArray();
        for (int i = 0; i < tasks.size(); i++) {
            taskListToJson.put(taskToJson(tasks.get(i)));
        }
        return taskListToJson;
    }
}
