package model;

import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Integer> subtasks = new ArrayList<>();

    // Конструктор для создания
    public Epic(String name, String description) {
        super(name, description);
    }

    // Конструктор для обновления
    public Epic(int id, String name, String description, States state) {
        super(id, name, description, state);
    }

    public ArrayList<Integer> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(ArrayList<Integer> subtasks) {
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
