package service;

import model.Epic;
import model.States;
import model.Subtask;
import model.Task;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private int nextId = 1;
    private final HashMap <Integer, Task> tasks = new HashMap<>();
    private final HashMap <Integer, Epic> epics = new HashMap<>();
    private final HashMap <Integer, Subtask> subtasks = new HashMap<>();
    private HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();

    public HistoryManager getInMemoryHistoryManager() {
        return inMemoryHistoryManager;
    }

    // Получить текущий Id
    public int getNextId() {
        return nextId;
    }

    // Получение списка всех задач. Тип задачи "Task".
    @Override
    public ArrayList<Task> getListTasks () {
        return new ArrayList<>(tasks.values());
    }

    // Удаление всех задач. Тип задачи "Task".
    @Override
    public void deleteAllTasks () {
        tasks.clear();
    }

    // Получение задачи по идентификатору. Тип задачи "Task".
    @Override
    public Task getTaskById(Integer id) {
        Task task = tasks.get(id);

        // Добавим задачу в историю просмотров
        inMemoryHistoryManager.add(task);

        return task;
    }

    // Создание задачи. Тип задачи "Task".
    @Override
    public void createTask(Task task) {
        task.setId(nextId);
        tasks.put(task.getId(), task);
        nextId++;
    }

    // Обновление задачи. Тип задачи "Task".
    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    // Удаление задачи по идентификатору. Тип задачи "Task".
    @Override
    public void deleteTaskById(Integer id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        }
    }

    // Получение списка всех задач. Тип задачи "Epic".
    @Override
    public ArrayList<Epic> getListEpics() {
        return new ArrayList<>(epics.values());
    }

    // Удаление всех задач. Тип задачи "Epic".
    @Override
    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    // Получение задачи по идентификатору. Тип задачи "Epic".
    @Override
    public Epic getEpicById(Integer id) {
        Epic epic = epics.get(id);

        // Добавим эпик в историю просмотров
        inMemoryHistoryManager.add(epic);

        return epic;
    }

    // Создание задачи. Тип задачи "Epic".
    @Override
    public void createEpic(Epic epic) {
        epic.setId(nextId);
        epics.put(epic.getId(), epic);
        nextId++;
    }

    // Обновление задачи. Тип задачи "Epic".
    @Override
    public void updateEpic(Epic epic) {
        final Epic savedEpic = epics.get(epic.getId());
        savedEpic.setName(epic.getName());
        savedEpic.setDescription(epic.getDescription());
    }

    // Удаление задачи по идентификатору. Тип задачи "Epic".
    @Override
    public void deleteEpicById(Integer id) {
        if (epics.containsKey(id)) {
            // Сперва удалим все подзадачи, учитывая существующую (теоретически) каскадную зависимость подзадач от эпика
            Epic epic = epics.get(id);
            for (Integer subtask : epic.getSubtasks()) {
                subtasks.remove(subtask);
            }

            // Теперь можно удалять эпик
            epics.remove(id);
        }
    }

    // Смена статуса задачи. Тип задачи "Epic".
    @Override
    public void updateStateEpic(Epic epic){
        ArrayList<Integer> subs = epic.getSubtasks();

        if (subs.isEmpty()) {
            epic.setState(States.NEW);
            return;
        }

        States status = null;

        for (int id : subs) {
            final Subtask subtask = subtasks.get(id);

            if (subtask == null) {
                continue;
            }

            if (status == null) {
                status = subtask.getState();
                continue;
            }

            if (status.equals(subtask.getState()) && !status.equals(States.IN_PROGRESS)) {
                continue;
            }

            epic.setState(States.IN_PROGRESS);
            return;
        }

        if (status == null) {
            status = States.NEW;
        }

        epic.setState(status);
    }

    // Получение списка всех подзадач (Subtask) определённого эпика (Epic).
    @Override
    public ArrayList<Integer> getSubtask(Epic epic) {
        return new ArrayList<>(epic.getSubtasks());
    }

    // Получение списка всех задач. Тип задачи "Subtask".
    @Override
    public ArrayList<Subtask> getListSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    // Удаление всех задач. Тип задачи "Subtask".
    @Override
    public void deleteAllSubtasks() {
        subtasks.clear();

        // Обнулим списки и пересчитаем статусы у всех эпиков
        for (Epic epic : epics.values()) {
            epic.getSubtasks().clear();
            updateStateEpic(epic);
        }
    }

    // Получение задачи по идентификатору. Тип задачи "Subtask".
    @Override
    public Subtask getSubtaskById(Integer id) {
        Subtask subtask = subtasks.get(id);

        // Добавим подзадачу в историю просмотров
        inMemoryHistoryManager.add(subtask);

        return subtask;
    }

    // Создание задачи. Тип задачи "Subtask".
    @Override
    public void createSubtask(Subtask subtask) {
        int epicId = subtask.getEpicId();
        subtask.setId(nextId);
        subtasks.put(subtask.getId(), subtask);

        // Добавим подзадачу в эпик
        Epic epic = epics.get(epicId);
        epic.getSubtasks().add(subtask.getId());

        // пересчтиаем статус у Эпика
        updateStateEpic(epic);
        nextId++;
    }

    // Обновление задачи. Тип задачи "Subtask".
    @Override
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);

        // пересчтиаем статус у Эпика
        Epic epic = getEpicById(subtask.getEpicId());
        updateStateEpic(epic);
    }

    // Удаление задачи по идентификатору. Тип задачи "Subtask".
    @Override
    public void deleteSubtaskById(Integer id) {
        if (subtasks.containsKey(id)) {
            // Получим подзадачу по Id
            Subtask subtask = subtasks.get(id);

            // Получим эпик по Id
            Epic epic = epics.get(subtask.getEpicId());

            //Удалим подзадачу
            subtasks.remove(id);

            //Удалим подзадачу из списка эпика
            epic.getSubtasks().remove(id);

            // пересчтиаем статус у Эпика
            updateStateEpic(epic);
        }
    }
}