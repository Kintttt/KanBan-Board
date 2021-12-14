package com.week9.taskmanagerapp.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class TaskModel {

    @Id
    @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    private Long taskId;

    @Column(nullable = false, columnDefinition = "text")
    private String taskTitle;

    @Column(nullable = false, columnDefinition = "text")
    private String taskDescription;

    @Column(nullable = false, columnDefinition = "text")
    private String taskTag;

    @Column(nullable = false, columnDefinition = "text")
    private String taskStatus;

    @Column(nullable = false, updatable = false)
    private Long posterId;

    @Column(updatable = false)
    private String posterName;


    public TaskModel(String taskTitle, String taskDescription, String taskTag, String taskStatus, Long posterId, String posterName) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskTag = taskTag;
        this.taskStatus = taskStatus;
        this.posterId = posterId;
        this.posterName = posterName;
    }

    public TaskModel(String taskTitle, String taskDescription, String taskTag) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskTag = taskTag;
    }
}
