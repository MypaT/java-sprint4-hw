package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;

public interface TaskManager {
    // Получение списка всех задач. Тип задачи "Task".
    ArrayList<Task> getListTasks();

    // Удаление всех задач. Тип задачи "Task".
    void deleteAllTasks();

    // Получение задачи по идентификатору. Тип задачи "Task".
    Task getTaskById(Integer id);

    // Создание задачи. Тип задачи "Task".
    void createTask(Task task);

    // Обновление задачи. Тип задачи "Task".
    void updateTask(Task task);

    // Удаление задачи по идентификатору. Тип задачи "Task".
    void deleteTaskById(Integer id);

    // Получение списка всех задач. Тип задачи "Epic".
    ArrayList<Epic> getListEpics();

    // Удаление всех задач. Тип задачи "Epic".
    void deleteAllEpics();

    // Получение задачи по идентификатору. Тип задачи "Epic".
    Epic getEpicById(Integer id);

    // Создание задачи. Тип задачи "Epic".
    void createEpic(Epic epic);

    // Обновление задачи. Тип задачи "Epic".
    void updateEpic(Epic epic);

    // Удаление задачи по идентификатору. Тип задачи "Epic".
    void deleteEpicById(Integer id);

    // Смена статуса задачи. Тип задачи "Epic".
    void updateStateEpic(Epic epic);

    // Получение списка всех подзадач (Subtask) определённого эпика (Epic).
    ArrayList<Integer> getSubtask(Epic epic);

    // Получение списка всех задач. Тип задачи "Subtask".
    ArrayList<Subtask> getListSubtasks();

    // Удаление всех задач. Тип задачи "Subtask".
    void deleteAllSubtasks();

    // Получение задачи по идентификатору. Тип задачи "Subtask".
    Subtask getSubtaskById(Integer id);

    // Создание задачи. Тип задачи "Subtask".
    void createSubtask(Subtask subtask);

    // Обновление задачи. Тип задачи "Subtask".
    void updateSubtask(Subtask subtask);

    // Удаление задачи по идентификатору. Тип задачи "Subtask".
    void deleteSubtaskById(Integer id);
}
