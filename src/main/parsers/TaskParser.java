package parsers;

import model.DueDate;
import model.Priority;
import model.Status;
import model.Task;
import model.exceptions.EmptyStringException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Represents Task parser
public class TaskParser {
    private boolean pass;
    private Task t1;
    private JSONObject task;
    private String description;
    private JSONObject priorityToJson;
    private Object timportant;
    private Object turgent;
    private Priority priority;
    private String status;
    private JSONArray tagsToJson;
    private JSONObject tagToJson;
    private  Object ttag;
    private JSONObject duedateToJSon;
    private DueDate duedate;
    private Date date;
    private Object year;
    private Object month;
    private Object dateo;
    private Object hour;
    private Object minute;



    // EFFECTS: iterates over every JSONObject in the JSONArray represented by the input
    // string and parses it as a task; each parsed task is added to the list of tasks.
    // Any task that cannot be parsed due to malformed JSON data is not added to the
    // list of tasks.
    // Note: input is a string representation of a JSONArray
    public List<Task> parse(String input) {
        List<Task> tasks = new ArrayList<>();
        JSONArray taskListToJson = new JSONArray(input);
        for (int i = 0; i < taskListToJson.length(); i++) {
            task = taskListToJson.getJSONObject(i);
            pass = true;
            t1 = parseTask(task);
            if (pass) {
                tasks.add(t1);
            }
        }
        return tasks;
    }


    private Task parseTask(JSONObject task) {
        t1 = parsedescription(task);
        if (pass == true) {
            parsePirority(task, t1);
            parseStatus(task, t1);
            parseTaglist(task, t1);
            parseDuedate(task, t1);
        }
        return t1;
    }

    private Task parsedescription(JSONObject task) {
        try {
            description = task.getString("description");
            t1 = new Task(description);
        } catch (JSONException e) {
            pass = false;
            System.out.println("the JSonobject task no description");
        } catch (EmptyStringException e) {
            pass = false;
            System.out.println("The description of task is empty");
        }
        return t1;
    }

    private void parsePirority(JSONObject task, Task t) {
        try {
            priorityToJson = task.getJSONObject("priority");
            timportant = priorityToJson.get("important");
            turgent = priorityToJson.get("urgent");
            Object[] o = new Object[]{timportant, turgent};
            isBoolean(o);
            priority = new Priority();
            priority.setImportant((Boolean) timportant);
            priority.setUrgent((Boolean) turgent);
            t.setPriority(priority);
        } catch (JSONException e) {
            pass = false;
            System.out.println("The JSonobject task no priority or the priority is not a boolean");
        }
    }

    private void isBoolean(Object[] objects) {
        for (Object o : objects) {
            if (!(o instanceof Boolean)) {
                throw new JSONException("Check the timportanat and turgent must be Boolean.");
            }
        }
    }

    private void parseStatus(JSONObject task, Task t) {
        try {
            status = task.getString("status");
            t.setStatus(Status.valueOf(status));
        } catch (JSONException e) {
            pass = false;
            System.out.println("The JSon object status is missing");
        }
    }


    private void parseTaglist(JSONObject task, Task t) {
        try {
            tagsToJson = task.getJSONArray("tags");
            for (int i = 0; i < tagsToJson.length(); i++) {
                tagToJson = tagsToJson.getJSONObject(i);
                ttag = tagToJson.get("name");
                isString(ttag);
                t.addTag((String) ttag);
            }
        } catch (JSONException e) {
            pass = false;
            System.out.println("The JSonobject task tags has no tag");
        }
    }

    private void isString(Object object) {
        if (!(ttag instanceof String)) {
            throw new JSONException("Check the ttag object must be String.");
        }
    }


    private void parseDuedate(JSONObject task, Task t) {
        try {
            if (task.get("due-date") != JSONObject.NULL) {
                duedateToJSon = task.getJSONObject("due-date");
            }
            if (duedateToJSon != null) {
                Object[] o = parseDuedateNext(duedateToJSon);
                isInteger(o);
                date = new Date((int) o[0] - 1900, (int) o[1], (int) o[2], (int) o[3], (int) o[4]);
                duedate = new DueDate(date);
                t1.setDueDate(duedate);
            }
        } catch (JSONException e) {
            pass = false;
            System.out.println("The JSonobject task tags has no DueDate or the duedate is not integer ");
        }
    }


    private Object[] parseDuedateNext(JSONObject duedateToJSon) {
        year = duedateToJSon.get("year");
        month = duedateToJSon.get("month");
        dateo = duedateToJSon.get("day");
        hour = duedateToJSon.get("hour");
        minute = duedateToJSon.get("minute");
        return new Object[]{year, month, dateo, hour, minute};
    }


    private void isInteger(Object[] objects) {
        for (Object o : objects) {
            if (!(o instanceof Integer)) {
                throw new JSONException("Check the duedate must be the Integer ");
            }
        }
    }

}

