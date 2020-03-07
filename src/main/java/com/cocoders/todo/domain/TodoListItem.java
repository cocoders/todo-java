package com.cocoders.todo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "todolist")
public class TodoListItem {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String description;
    private Date createdAt;

    protected TodoListItem() {
    }

    public TodoListItem(UUID id, String description) {
        this.id = id;
        this.description = description;
        this.createdAt = new Date();
    }

    @Override
    public String toString() {
        return "TodoListItem{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoListItem)) return false;
        TodoListItem that = (TodoListItem) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getCreatedAt());
    }
}
