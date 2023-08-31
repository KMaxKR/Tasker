package ks.msx.TaskManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "task_table")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task")
    private String content;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TaskType type;

    @Column(name = "date")
    private Date date;

    @Column(name = "user_id")
    private Long user_id_task;
}
