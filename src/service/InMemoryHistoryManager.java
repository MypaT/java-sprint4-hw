package service;

import model.Task;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final LinkedList<Task> historyTasks = new LinkedList<>();

    @Override
    public void add(Task task) {
        while (historyTasks.size() >= 10) {
            historyTasks.removeFirst();
        }

        historyTasks.add(task);
    };

    @Override
    public void delete(Task task) {
        if (historyTasks.contains(task)) historyTasks.removeAll(Collections.singletonList(task));
    };

    @Override
    public List<Task> getHistory() {
        return new LinkedList<>(historyTasks);
    };
}
