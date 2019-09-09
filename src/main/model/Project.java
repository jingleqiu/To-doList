package model;

import model.exceptions.NullArgumentException;

import java.util.*;

// Represents a Project, a collection of zero or more Tasks
// Class Invariant: no duplicated task; order of tasks is preserved
public class Project extends Todo implements Iterable<Todo> {
    private List<Todo> tasks;

    // MODIFIES: this
    // EFFECTS: constructs a project with the given description
    //     the constructed project shall have no tasks.
    //  throws EmptyStringException if description is null or empty
    public Project(String description) {
        super(description);
        tasks = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: task is added to this project (if it was not already part of it)
    //   throws NullArgumentException when task is null
    public void add(Todo task) {
        if (this != task) {
            if (!contains(task)) {
                tasks.add(task);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes task from this project
    //   throws NullArgumentException when task is null
    public void remove(Todo task) {
        if (contains(task)) {
            tasks.remove(task);
        }
    }

    // EFFECTS: returns the description of this project
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns an unmodifiable list of tasks in this project.
    @Deprecated
    public List<Task> getTasks() {
        throw new UnsupportedOperationException();
    }

    // EFFECTS: returns an integer between 0 and 100 which represents
//     the percentage of completion (rounded down to the nearest integer).
//     the value returned is the average of the percentage of completion of
//     all the tasks and sub-projects in this project.
    public int getProgress() {
        int totaltime = 0;
        int percentage = 0;
        int count = 0;
        if (getNumberOfTasks() != 0) {
            for (Todo todo : tasks) {
                totaltime += todo.getProgress();
                count++;
            }
            return totaltime / count;
        } else {
            return 0; //stub
        }
    }


    // EFFECTS: returns the number of tasks (and sub-projects) in this project
    public int getNumberOfTasks() {
        return tasks.size();
    }

    // EFFECTS: returns true if every task (and sub-project) in this project is completed, and false otherwise
//     If this project has no tasks (or sub-projects), return false.
    public boolean isCompleted() {
        return getNumberOfTasks() != 0 && getProgress() == 100;
    }

    // EFFECTS: returns true if this project contains the task
    //   throws NullArgumentException when task is null
    public boolean contains(Todo task) {
        if (task == null) {
            throw new NullArgumentException("Illegal argument: task is null");
        }
        return tasks.contains(task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public int getEstimatedTimeToComplete() {
        int estimatetime = 0;
        for (Todo todo : tasks) {
            estimatetime += todo.getEstimatedTimeToComplete();
        }
        super.etcHours = estimatetime;
        return estimatetime; //stub
    }

    @Override
    public Iterator<Todo> iterator() {
        return new ProjectIterator();
    }

    private class ProjectIterator implements Iterator<Todo> {
        private int typenum = 1;
        private int seq = 0;
        private int acc = 0;
        //private int addr = -1;


        @Override
        public boolean hasNext() {
            return (acc < tasks.size());
        }

        @Override
        public Todo next() {
            Todo todo = null;
            if (typenum == 1) {
                todo = todo1();
            }
            if (typenum == 2) {
                todo = todo2();
            }
            if (typenum == 3) {
                todo = todo3();
            }
            if (typenum == 4) {
                todo = todo4();
            }
            return todo;
        }

        private Todo todo1() {
            int ptr = -1;
            for (Todo todo : tasks) {
                ptr++;
                if (ptr >= seq) {
                    if (todo.getPriority().isUrgent() && todo.getPriority().isImportant()) {
                        seq = ptr + 1;
                        acc++;
                        return todo;
                    }
                }
            }

            typenum = 2;
            seq = 0;
            return null;
        }

        private Todo todo2() {
            int ptr = -1;
            for (Todo todo : tasks) {
                ptr++;
                if (ptr >= seq) {
                    if (!todo.getPriority().isUrgent() && todo.getPriority().isImportant()) {
                        seq = ptr + 1;
                        acc++;
                        return todo;
                    }
                }
            }

            typenum = 3;
            seq = 0;
            return null;
        }

        private Todo todo3() {
            int ptr = -1;
            for (Todo todo : tasks) {
                ptr++;
                if (ptr >= seq) {
                    if (todo.getPriority().isUrgent() && !todo.getPriority().isImportant()) {
                        seq = ptr + 1;
                        acc++;
                        return todo;
                    }
                }
            }

            typenum = 4;
            seq = 0;
            return null;
        }

        private Todo todo4() {
            int ptr = -1;
            for (Todo todo : tasks) {
                ptr++;
                if (ptr >= seq) {
                    if (!todo.getPriority().isUrgent() && !todo.getPriority().isImportant()) {
                        seq = ptr + 1;
                        acc++;
                        return todo;
                    }
                }
            }

            throw new NoSuchElementException();
        }
    }
}




//            for (Todo todo : tasks) {
//                if (index == 0 && todo.getPriority().isUrgent() && todo.getPriority().isImportant()) {
//                    return zero(todo);
//                }
//                if (index == 1 && !todo.getPriority().isUrgent() && todo.getPriority().isImportant()) {
//                    return zero(todo);
//                }
//                if (index == 2 && todo.getPriority().isUrgent() && !todo.getPriority().isImportant()) {
//                    return zero(todo);
//                }
//                if (index == 3 && !todo.getPriority().isUrgent() && !todo.getPriority().isImportant()) {
//                    return zero(todo);
//                }
//            }
//            return null;
//        }
//        private Todo todo1() {
//            for (Todo todo : tasks) {
//                int i++;
//                seq = i + 1;
//            }
//        }
//
//        private void thro() {
//            if (!hasNext()) {
//                throw new NoSuchElementException();
//            }
//        }
//
//        private Todo zero(Todo todo) {
//            pla = addr;
//            if (addr >= (tasks.size() - 1)) {
//                index = index + 1;
//                pla = -1;
//                addr = -1;
//            }
//            acc = acc + 1;
//            return todo;
//        }
//    }
//}
