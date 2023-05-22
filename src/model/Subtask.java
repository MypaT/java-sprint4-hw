package model;

public class Subtask extends Task {
    private int epicId;

    // Конструктор для создания
    public Subtask(String name, String description, int epicId) {
        super(name, description);
        this.epicId = epicId;
    }

    // Конструктор для обновления
    public Subtask(int id, String name, String description, States state, int epicId) {
        super(id, name, description, state);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", state='" + super.getState() + '\'' +
                ", epicId=" + epicId +
                '}';
    }
}
