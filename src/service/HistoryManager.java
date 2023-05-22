package service;

import model.Task;

import java.util.List;

public interface HistoryManager {
    //Пометить задачи как просмотренные
    public void add(Task task);

    //Вернуть список задач
    public List<Task> getHistory();

    //Печать истории просмотренных задач
    void printHistory();
}
