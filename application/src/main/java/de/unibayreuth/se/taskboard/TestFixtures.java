package de.unibayreuth.se.taskboard;

import de.unibayreuth.se.taskboard.business.domain.Task;
import de.unibayreuth.se.taskboard.business.domain.User;
import de.unibayreuth.se.taskboard.business.ports.UserService;
import de.unibayreuth.se.taskboard.business.ports.TaskService;
import org.apache.commons.lang3.SerializationUtils;

import java.util.List;
import java.util.stream.Collectors;

public class TestFixtures {
    private static final List<User> USERS = List.of(
            new User("Alice"),
            new User("Bob"),
            new User("Charlie")
    );

    private static final List<Task> TASKS = List.of(
            new Task("Task 1", "Description 1"),
            new Task("Task 2", "Description 2"),
            new Task("Task 3", "Description 3"),
            new Task("Task 4", "Description 4"),
            new Task("Task 5", "Description 5"),
            new Task("Task 6", "Description 6"),
            new Task("Task 7", "Description 7"),
            new Task("Task 8", "Description 8"),
            new Task("Task 9", "Description 9"),
            new Task("Task 10", "Description 10"),
            new Task("Task 11", "Description 11"),
            new Task("Task 12", "Description 12"),
            new Task("Task 13", "Description 13"),
            new Task("Task 14", "Description 14"),
            new Task("Task 15", "Description 15"),
            new Task("Task 16", "Description 16"),
            new Task("Task 17", "Description 17"),
            new Task("Task 18", "Description 18"),
            new Task("Task 19", "Description 19"),
            new Task("Task 20", "Description 20")
    );

    public static List<User> getUsers() {
        return USERS.stream()
                .map(SerializationUtils::clone) // prevent issues when tests modify the fixture objects
                .toList();
    }

    public static List<Task> getTasks() {
        return TASKS.stream()
                .map(SerializationUtils::clone) // prevent issues when tests modify the fixture objects
                .toList();
    }

    public static List<User> createUsers(UserService userService) {
        return USERS.stream()
                .map(SerializationUtils::clone) // prevent issues when tests modify the fixture objects
                .map(userService::create)
                .collect(Collectors.toList());
    }

    public static List<Task> createTasks(TaskService taskService) {
        return TASKS.stream()
                .map(SerializationUtils::clone) // prevent issues when tests modify the fixture objects
                .map(taskService::create)
                .collect(Collectors.toList());
    }
}
