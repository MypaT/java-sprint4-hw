package model;

public class Task {
    private int id;
    private String name;
    private String description;
    private States state = States.NEW;

    // Конструктор для создания
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Конструктор для обновления
    public Task(int id, String name, String description, States state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
