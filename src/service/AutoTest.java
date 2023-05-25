package service;

import model.Epic;
import model.States;
import model.Subtask;
import model.Task;

import java.util.ArrayList;

// класс для автотестов
public class AutoTest {
    TaskManager taskManager = Managers.getDefault();
    InMemoryTaskManager inMemoryTaskManager = (InMemoryTaskManager) taskManager;
    HistoryManager historyManager = inMemoryTaskManager.getInMemoryHistoryManager();

    // тест из ТЗ №3. Тестирование всего функционала
    public void testingOfAllFunctionality() {
        // Создайте 2 задачи, один эпик с 2 подзадачами, а другой эпик с 1 подзадачей
        Task task1 = new Task("task_1", "description_task_1");
        taskManager.createTask(task1);
        int idTask1 = task1.getId();

        Task task2 = new Task("task_2", "description_task_2");
        taskManager.createTask(task2);
        int idTask2 = task2.getId();

        Epic epic1 = new Epic("epic_1", "description_epic_1");
        taskManager.createEpic(epic1);
        int idEpic1 = epic1.getId();

        Epic epic2 = new Epic("epic_2", "description_epic_2");
        taskManager.createEpic(epic2);
        int idEpic2 = epic2.getId();

        Subtask subtask1 = new Subtask("subtask_1", "description_subtask_1", idEpic1);
        taskManager.createSubtask(subtask1);
        int idSubtask1 = subtask1.getId();

        Subtask subtask2 = new Subtask("subtask_2", "description_subtask_2", idEpic1);
        taskManager.createSubtask(subtask2);
        int idSubtask2 = subtask2.getId();

        Subtask subtask3 = new Subtask("subtask_3", "description_subtask_3", idEpic2);
        taskManager.createSubtask(subtask3);
        int idSubtask3 = subtask3.getId();

        // Распечатайте списки эпиков, задач и подзадач
        System.out.println("Проверка после создания задач всех типов");
        for (Task task : taskManager.getListTasks()) {
            System.out.println(task);
        }
        for (Epic epic : taskManager.getListEpics()) {
            System.out.println(epic);
        }
        for (Subtask subtask : taskManager.getListSubtasks()) {
            System.out.println(subtask);
        }

        // Измените статусы созданных объектов, распечатайте.
        task1.setState(States.DONE);
        taskManager.updateTask(task1);
        task2.setState(States.IN_PROGRESS);
        taskManager.updateTask(task2);
        subtask1.setState(States.DONE);
        taskManager.updateSubtask(subtask1);
        subtask2.setState(States.IN_PROGRESS);
        taskManager.updateSubtask(subtask2);
        subtask3.setState(States.DONE);
        taskManager.updateSubtask(subtask3);

        // Проверьте, что статус задачи и подзадачи сохранился, а статус эпика рассчитался по статусам подзадач.
        System.out.println("Проверка после изменения статусов задач всех типов");
        for (Task task : taskManager.getListTasks()) {
            System.out.println(task);
        }
        for (Epic epic : taskManager.getListEpics()) {
            System.out.println(epic);
        }
        for (Subtask subtask : taskManager.getListSubtasks()) {
            System.out.println(subtask);
        }

        // И, наконец, попробуйте удалить одну из задач и один из эпиков.
        taskManager.deleteTaskById(idTask1);
        taskManager.deleteEpicById(idEpic1);

        // Контрольная проверка
        System.out.println("Проверка после удаления одной основной задачи и одного эпика");
        for (Task task : taskManager.getListTasks()) {
            System.out.println(task);
        }
        for (Epic epic : taskManager.getListEpics()) {
            System.out.println(epic);
        }
        for (Subtask subtask : taskManager.getListSubtasks()) {
            System.out.println(subtask);
        }

    }

    //Тестирование удаления подзадачи (исправление дефекта-долга по ТЗ №3)
    public void testingDeleteSubtask() {
        Epic epic1 = new Epic("epic_1", "description_epic_1");
        taskManager.createEpic(epic1);
        int idEpic1 = epic1.getId();

        Subtask subtask1 = new Subtask("subtask_1", "description_subtask_1", idEpic1);
        taskManager.createSubtask(subtask1);
        int idSubtask1 = subtask1.getId();

        Subtask subtask2 = new Subtask("subtask_2", "description_subtask_2", idEpic1);
        taskManager.createSubtask(subtask2);
        int idSubtask2 = subtask2.getId();

        System.out.println("Проверка после создания задач всех типов");
        for (Epic epic : taskManager.getListEpics()) {
            System.out.println(epic);
        }
        for (Subtask subtask : taskManager.getListSubtasks()) {
            System.out.println(subtask);
        }

        taskManager.deleteSubtaskById(idSubtask2);

        System.out.println("Проверка после удаления подзадачи");
        for (Epic epic : taskManager.getListEpics()) {
            System.out.println(epic);
        }
        for (Subtask subtask : taskManager.getListSubtasks()) {
            System.out.println(subtask);
        }
    }

    // Печать истории просмотров
    private void printHistory() {
        System.out.println("История просмотренных задач:");
        int i = 1;
        for (Task historyTask : historyManager.getHistory()) {
            System.out.println(i + ". " + historyTask);
            i++;
        }
    }

    // Тестирование истории просмотров задач
    public void testingHistoryManager() {
        // Создайте 2 задачи, один эпик с 2 подзадачами, а другой эпик с 1 подзадачей
        Task task1 = new Task("task_1", "description_task_1");
        taskManager.createTask(task1);
        int idTask1 = task1.getId();

        Task task2 = new Task("task_2", "description_task_2");
        taskManager.createTask(task2);
        int idTask2 = task2.getId();

        Epic epic1 = new Epic("epic_1", "description_epic_1");
        taskManager.createEpic(epic1);
        int idEpic1 = epic1.getId();

        Epic epic2 = new Epic("epic_2", "description_epic_2");
        taskManager.createEpic(epic2);
        int idEpic2 = epic2.getId();

        Subtask subtask1 = new Subtask("subtask_1", "description_subtask_1", idEpic1);
        taskManager.createSubtask(subtask1);
        int idSubtask1 = subtask1.getId();

        Subtask subtask2 = new Subtask("subtask_2", "description_subtask_2", idEpic1);
        taskManager.createSubtask(subtask2);
        int idSubtask2 = subtask2.getId();

        Subtask subtask3 = new Subtask("subtask_3", "description_subtask_3", idEpic2);
        taskManager.createSubtask(subtask3);
        int idSubtask3 = subtask3.getId();

        // Распечатайте списки эпиков, задач и подзадач
        System.out.println("Проверка после создания задач всех типов");
        for (Task task : taskManager.getListTasks()) {
            System.out.println(task);
        }
        for (Epic epic : taskManager.getListEpics()) {
            System.out.println(epic);
        }
        for (Subtask subtask : taskManager.getListSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("");
        printHistory();

        taskManager.getTaskById(idTask2);
        taskManager.getEpicById(idEpic1);
        taskManager.getSubtaskById(idSubtask3);

        System.out.println("");
        printHistory();

        taskManager.getTaskById(idTask2);
        taskManager.getEpicById(idEpic2);
        taskManager.getSubtaskById(idSubtask1);
        taskManager.getTaskById(idTask1);
        taskManager.getEpicById(idEpic2);
        taskManager.getSubtaskById(idSubtask2);

        System.out.println("");
        printHistory();

        taskManager.getTaskById(idTask1);
        taskManager.getEpicById(idEpic1);
        taskManager.getSubtaskById(idSubtask2);

        System.out.println("");
        printHistory();

        //taskManager.deleteAllTasks();
        taskManager.deleteAllEpics();
        //taskManager.deleteAllSubtasks();

        System.out.println("");
        printHistory();

        // Распечатайте списки эпиков, задач и подзадач
        System.out.println("");
        System.out.println("Проверка после удаления задач всех типов");
        for (Task task : taskManager.getListTasks()) {
            System.out.println(task);
        }
        for (Epic epic : taskManager.getListEpics()) {
            System.out.println(epic);
        }
        for (Subtask subtask : taskManager.getListSubtasks()) {
            System.out.println(subtask);
        }
    }
}
