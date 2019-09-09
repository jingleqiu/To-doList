package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TestProject {
    private String description;
    private Task listtasks;
    private Project p1;
    private Task task1;
    private Task task2;
    private Task task3;


    @BeforeEach
    public void runBefore() {
        p1 = new Project("listening");
        task1 = new Task("assignment");
        task1.setStatus(Status.TODO);
        task2 = new Task("reading");
        task2.setStatus(Status.IN_PROGRESS);
        task3 = new Task("abc");
        task3.setStatus(Status.DONE);
    }

    @Test
    void testAdd() {
        p1.add(task1);
        assertEquals(1, p1.getNumberOfTasks());
        p1.add(task2);
        assertEquals(2, p1.getNumberOfTasks());
        p1.add(task1);
        assertEquals(2, p1.getNumberOfTasks());
    }

    @Test
    void testRemove() {
        p1.add(task1);
        p1.add(task2);
        p1.remove(task1);
        assertEquals(1, p1.getNumberOfTasks());

    }

    @Test
    void testGetDescription() {
        p1.getDescription();
        assertEquals("listening", p1.getDescription());
    }

    @Test
    void testGetTask() {
        try {
            Project a = new Project(null);
            fail();
        } catch (EmptyStringException e) {
            System.out.println("Cannot construct a project with no description");
        }

        try {
            Project b = new Project("");
            fail();
        } catch (EmptyStringException e) {
            System.out.println("Cannot construct a project with no description");
        }

    }


// @Test
//    void testGetProcess() {
//     assertEquals(100, p1.getProgress());
//     p1.add(task3);
//     assertEquals(100, p1.getProgress());
//     p1.add(task2);
//     assertEquals(50, p1.getProgress());
//}


// @Test
//    void testIsCompleted() {
//     p1.add(task3);
//     assertTrue(p1.isCompleted());
//     p1.add(task2);
//     assertFalse(p1.isCompleted());
// }
//

    @Test
    void testEmptyprojectProcess() {
        Project pnull = new Project("nullproject");
        assertEquals(0, pnull.progress);
    }


    @Test
    void testTaskProcess() {
        Project p1 = new Project("project1");
        Task task1 = new Task("111");
        Task task2 = new Task("222");
        Task task3 = new Task("333");
        p1.add(task1);
        p1.add(task2);
        p1.add(task3);
        assertEquals(0, p1.getProgress());
    }

    @Test
    void testTask1Process() {
        Project p1 = new Project("project1");
        Task task1 = new Task("111");
        Task task2 = new Task("222");
        Task task3 = new Task("333");
        p1.add(task1);
        p1.add(task2);
        p1.add(task3);
        task1.setProgress(100);
        assertEquals(33, p1.getProgress());

    }

    @Test
    void testTasknewProcess() {
        Project p1 = new Project("project1");
        Task task1 = new Task("111");
        Task task2 = new Task("222");
        Task task3 = new Task("333");
        p1.add(task1);
        p1.add(task2);
        p1.add(task3);
        task1.setProgress(100);
        task2.setProgress(50);
        task3.setProgress(25);
        assertEquals(58, p1.getProgress());
        Project p2 = new Project("Project 2");
        assertEquals(0, p2.getProgress());

    }


    @Test
    void testNewProject() {
        Project p1 = new Project("project1");
        Task task1 = new Task("111");
        Task task2 = new Task("222");
        Task task3 = new Task("333");
        p1.add(task1);
        p1.add(task2);
        p1.add(task3);
        task1.setProgress(100);
        task2.setProgress(50);
        task3.setProgress(25);
        Project p2 = new Project("project2");
        Task task4 = new Task("444");
        p2.add(p1);
        p2.add(task4);
        assertEquals(29, p2.getProgress());
    }


    @Test
    void testGetEstimatedtime() {
        Project p1 = new Project("project1");
        Task task1 = new Task("111");
        Task task2 = new Task("222");
        Task task3 = new Task("333");
        p1.add(task1);
        p1.add(task2);
        p1.add(task3);
        assertEquals(0, p1.getEstimatedTimeToComplete());
    }


    @Test
    void testChangeTask1Time() {
        Project p1 = new Project("project1");
        Task task1 = new Task("111");
        Task task2 = new Task("222");
        Task task3 = new Task("333");
        p1.add(task1);
        p1.add(task2);
        p1.add(task3);
        task1.setEstimatedTimeToComplete(8);
        assertEquals(8, p1.getEstimatedTimeToComplete());
        task2.setEstimatedTimeToComplete(2);
        task3.setEstimatedTimeToComplete(10);
        assertEquals(20, p1.getEstimatedTimeToComplete());
        Project p2 = new Project("project2");
        Task task4 = new Task("444");
        task4.setEstimatedTimeToComplete(4);
        p2.add(task4);
        p2.add(p1);
        assertEquals(24, p2.getEstimatedTimeToComplete());


    }

    @Test
    void testGetTasks() {
        try {
            Project a = new Project("project1");
            a.getTasks();
            fail();
        } catch (UnsupportedOperationException e) {
            System.out.println("The project fails the UnsupportedOperationException");
        }


    }


    @Test
    void testIsComplete() {
        Project p1 = new Project("project1");
        Task task1 = new Task("111");
        Task task2 = new Task("222");
        Task task3 = new Task("333");
        p1.add(task1);
        p1.add(task2);
        p1.add(task3);
        assertFalse(p1.isCompleted());
        Project p2 = new Project(("project2"));
        assertFalse(p2.isCompleted());
        assertFalse(p2.contains(task1));
        Task task4 = new Task("444");
        task1.setProgress(100);
        task2.setProgress(100);
        task3.setProgress(100);
        assertTrue(p1.isCompleted());
        p1.add(task4);
        assertFalse(p1.isCompleted());

    }

    @Test
    void testContain() {
        try {
            Project pnull = new Project("pnull");
            pnull.contains(null);
            fail();
        } catch (NullArgumentException e) {
            System.out.println("Illegal argument: task is null");
        }


    }

    @Test
    void testEquals() {
        Project project1 = new Project("project 1");
        assertTrue(project1.equals(project1));
        Project project2 = new Project("project 1");
        assertTrue(project1.equals(project2));
        assertTrue(project1.hashCode() == project2.hashCode());
    }


    @Test
    void testIterator() {
        Priority p1 = new Priority(1);
        Priority p2 = new Priority(2);
        Priority p3 = new Priority(3);
        Priority p4 = new Priority(3);

        Project project1 = new Project("project 1");
        Task task1 = new Task("task 1");
        task1.setPriority(p1);
        Task task2 = new Task("task 2");
        task2.setPriority(p2);
        Task task3 = new Task("task 3");
        task3.setPriority(p3);
        Task task4 = new Task("task 4");
        task4.setPriority(p4);
        Project project2 = new Project("project 2");
        project2.setPriority(p2);
        project1.add(project2);
        project1.add(task1);
        project1.add(task2);
        project1.add(task3);
        project1.add(task4);

        for (Todo t : project1) {
            System.out.println(t.description + " " + t.getPriority());
        }
    }

    @Test
    void testIteratorexception() {
        Priority p1 = new Priority(1);
        Priority p2 = new Priority(2);
        Priority p3 = new Priority(3);
        Priority p4 = new Priority(3);

        Project project1 = new Project("project 1");
        Task task1 = new Task("task 1");
        task1.setPriority(p1);
        Task task2 = new Task("task 2");
        task2.setPriority(p2);
        Task task3 = new Task("task 3");
        task3.setPriority(p3);
        Task task4 = new Task("task 4");
        task4.setPriority(p4);
        Project project2 = new Project("project 2");
        project2.setPriority(p2);
        project1.add(project2);
        project1.add(task1);
        project1.add(task2);
        project1.add(task3);
        project1.add(task4);
        Iterator<Todo> iterator = project1.iterator();
        try {
            while (iterator.hasNext()) {
                System.out.println(iterator.next().getDescription());
            }
            System.out.println(iterator.next().getDescription());
            fail();
        } catch (NoSuchElementException e) {
            System.out.println("the test pass");
        }
    }
}
