package service;

import model.Task;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

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
    public List<Task> getHistory() {
        return new ArrayList<>(historyTasks);
    };
}
