package model;

import model.exceptions.EmptyStringException;
import model.exceptions.InvalidProgressException;
import model.exceptions.NegativeInputException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask {
    private Task task1;
    private Task task2;
    private String description;
    private Priority priority;
    private Status status;
    private DueDate duedate;


    @BeforeEach
    public void runBefore() {
        task1 = new Task("Read book");
        task2 = new Task(" ");
        priority = new Priority(2);
        status = Status.TODO;
        duedate = new DueDate(new Date(119, 1, 7, 14, 6));


    }

    @Test
    void testConstructor() {
        assertEquals("Read book", task1.getDescription());
        assertEquals(" ", task2.getDescription());
        assertEquals(0, task1.progress);
        assertEquals(0, task1.etcHours);
    }

    @Test
    void testAddTag() {
        task1.addTag("assignment");
        assertEquals(1, task1.getTags().size());
        task1.addTag("assignment");
        assertEquals(1, task1.getTags().size());
        task1.addTag("book");
        assertEquals(2, task1.getTags().size());

    }

    @Test
    void testRemoveTag() {
        task1.addTag("speaking");
        task1.addTag("assignment");
        task1.addTag("book");
        task1.removeTag("assignment");
        assertEquals(2, task1.getTags().size());

    }

    @Test
    void testPriority() {
        task1.setPriority(priority);
        assertTrue(task1.getPriority().isImportant());
        try {
            Task t1 = new Task("11");
            t1.setPriority(null);
            fail();
        } catch (NullArgumentException e) {
           System.out.println("Illegal argument: priority is null");

        }
    }

    @Test
    void testStatus() {
        task1.setStatus(status);
        assertEquals(Status.TODO, task1.getStatus());
        try {
            Task t1 = new Task("11");
            t1.setStatus(null);
            fail();
        } catch (NullArgumentException e) {
            System.out.println("Illegal argument: status is null");
        }
    }


    @Test
    void GetProcess() {
        Task t1 = new Task("11");
        t1.setStatus(Status.DONE);
        assertEquals(100,t1.getProgress());
    }

    @Test
    void testDescription() {
        assertEquals("Read book", task1.getDescription());
        task2.setDescription("listening");
        assertEquals("listening", task2.getDescription());
        try {
            Task t = new Task("1");
            t.setDescription(null);
            fail();
        } catch (EmptyStringException e) {
            System.out.println("setDescription is called with no description");
        }
    }

    @Test
    void testDueDate() {
        task1.setDueDate(duedate);
//        DueDate testDueDate = new DueDate(new Date(119, 1,10,14,6));
        assertEquals(duedate.toString(), task1.getDueDate().toString());
    }

    @Test
    void testContainTag() {
        task1.addTag("Read book");
        task1.addTag("assignment");
        assertTrue(task1.containsTag("Read book"));
        assertFalse(task1.containsTag("exercise"));
        try {
            Tag tnull = new Tag(null);
            String a = null;
            task1.containsTag(a);
            fail();
        }catch (EmptyStringException e) {
            System.out.println("Invalid Argument: tag cannot be null");
        }

        try {
            Tag tnull = new Tag("");
            String a = "";
            task1.containsTag(a);
            fail();
        }catch (EmptyStringException e) {
            System.out.println("Invalid Argument: tag cannot be null");
        }


    }

    @Test
    void testToString() {
        task1.addTag("Read book");
        task1.setDueDate(duedate);
        task1.setStatus(status);
        task1.setPriority(priority);
        System.out.println(task1.toString());
  }

//    @Test
//    void testProcess() {
//        Task task1 = new Task("1");
//        task1.setStatus(Status.DONE);
//        assertEquals(100, task1.getProgress());
//
//    }

    @Test
    void testEstimatedTimeComplete() {
        try {
            Task task1 = new Task("1");
            task1.setEstimatedTimeToComplete(-2);
            fail();
        } catch (NegativeInputException e) {
            System.out.println("The hours  should be greater than 0");
        }
        task1.setEstimatedTimeToComplete(10);
        assertEquals(10,task1.getEstimatedTimeToComplete());

    }

    @Test
    void testProcess() {
        try {
            Task task1 = new Task("1");
            task1.setProgress(1022);
            fail();
        } catch (InvalidProgressException e) {
            System.out.println("The progress should be bigger or equal than 0 and smaller or equal 0");
        }
        try {
            task1.setProgress(-100);
            fail();
        } catch (InvalidProgressException e) {
            System.out.println("The progress should be bigger or equal than 0 and smaller or equal 0");
        }
        task1.setProgress(13);
        assertEquals(13, task1.getProgress());
    }

    @Test
    void testEquals() {
        Task task1 = new Task("11");
        assertTrue(task1.equals(task1));
        Task task2 = new Task("11");
        assertTrue(task2.equals(task2));
        assertTrue(task1.hashCode() == task2.hashCode());
        Task task3 = new Task ("33");
        assertFalse(task1.equals(task3));
        assertFalse(task3.hashCode() == task1.hashCode());
        task1.setPriority(new Priority(1));
        task2.setPriority(new Priority(1));
        task3.setPriority(new Priority(2));
        assertFalse(task1.equals(task3));
        assertTrue(task1.equals(task2));

    }





}











