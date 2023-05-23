package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getListTasks();

    void deleteAllTasks();

    Task getTaskById(Integer id);

    void createTask(Task task);

    void updateTask(Task task);

    void deleteTaskById(Integer id);

    List<Epic> getListEpics();

    void deleteAllEpics();

    Epic getEpicById(Integer id);

    void createEpic(Epic epic);

    void updateEpic(Epic epic);

    void deleteEpicById(Integer id);

    List<Integer> getSubtask(Epic epic);

    List<Subtask> getListSubtasks();

    void deleteAllSubtasks();

    Subtask getSubtaskById(Integer id);

    void createSubtask(Subtask subtask);

    void updateSubtask(Subtask subtask);

    void deleteSubtaskById(Integer id);
}
