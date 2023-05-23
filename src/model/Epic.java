package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    List<Integer> subtasks = new ArrayList<>();

    // Конструктор для создания
    public Epic(String name, String description) {
        super(name, description);
    }

    // Конструктор для обновления
    public Epic(int id, String name, String description) {
        super(id, name, description, States.NEW);
    }

    public List<Integer> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Integer> subtasks) {
        this.subtasks = subtasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", state='" + super.getState() + '\'' +
                ", subtasks=" + subtasks +
                '}';
    }
}
