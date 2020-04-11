package com.jazasoft.taskmanager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@NoArgsConstructor
@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotNull
    @Pattern(regexp = "critical|medium|low|high")
    private String priority;

    @ManyToOne
    private Label label;

    @Transient
    private Long labelId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(name, task.name) &&
                Objects.equals(priority, task.priority) &&
                Objects.equals(labelId, task.labelId);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name,
                priority,
                labelId);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority='" + priority + '\'' +
                ", labelId='" + labelId + '\'' +
                '}';
    }

}