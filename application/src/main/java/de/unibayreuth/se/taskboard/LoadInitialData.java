package de.unibayreuth.se.taskboard;

import de.unibayreuth.se.taskboard.business.domain.Task;
import de.unibayreuth.se.taskboard.business.domain.User;
import de.unibayreuth.se.taskboard.business.ports.TaskService;
import de.unibayreuth.se.taskboard.business.ports.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Load initial data into the list via the list service from the business layer.
 */
@Component
@RequiredArgsConstructor
@Slf4j
@Profile("dev")
class LoadInitialData implements InitializingBean {
    private final TaskService taskService;
    private final UserService userService;

    @Override
    public void afterPropertiesSet() {
        log.info("Deleting existing data...");
        userService.clear();
        taskService.clear();
        log.info("Loading initial data...");
        List<User> users = TestFixtures.createUsers(userService);
        List<Task> tasks = TestFixtures.createTasks(taskService);
        Task task1 = tasks.getFirst();
        task1.setAssigneeId(users.getFirst().getId());
        taskService.upsert(task1);
        Task task2 = tasks.getLast();
        task2.setAssigneeId(users.getLast().getId());
        taskService.upsert(task2);
        Task task3 = tasks.get(1);
        task3.setAssigneeId(users.get(0).getId());
        taskService.upsert(task3);
        Task task4 = tasks.get(2);
        task4.setAssigneeId(users.get(1).getId());
        taskService.upsert(task4);
        Task task5 = tasks.get(3);
        task5.setAssigneeId(users.get(2).getId());
        taskService.upsert(task5);
        Task task6 = tasks.get(4);
        task6.setAssigneeId(users.get(0).getId());
        taskService.upsert(task6);
        Task task7 = tasks.get(5);
        task7.setAssigneeId(users.get(1).getId());
        taskService.upsert(task7);
        Task task8 = tasks.get(6);
        task8.setAssigneeId(users.get(2).getId());
        taskService.upsert(task8);
        Task task9 = tasks.get(7);
        task9.setAssigneeId(users.get(0).getId());
        taskService.upsert(task9);
        Task task10 = tasks.get(8);
        task10.setAssigneeId(users.get(1).getId());
        taskService.upsert(task10);
        Task task11 = tasks.get(9);
        task11.setAssigneeId(users.get(2).getId());
        taskService.upsert(task11);
        Task task12 = tasks.get(10);
        task12.setAssigneeId(users.get(0).getId());
        taskService.upsert(task12);
    }
}