package service;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> historyTasks = new ArrayList<>();

    //Пометить задачи как просмотренные
    @Override
    public void add(Task task) {
        while (historyTasks.size() >= 10) {
            historyTasks.remove(0);
        }

        historyTasks.add(task);
    };

    //Получить историю просмотренных задач
    @Override
    public List<Task> getHistory() {
        return historyTasks;
    };

    //Печать истории просмотренных задач
    @Override
    public void printHistory() {
        System.out.println("История просмотренных задач:");
        int i = 1;
        for (Task historyTask : historyTasks) {
            System.out.println(i + ". " + historyTask);
            i++;
        }
    }
}
