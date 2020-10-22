package com.example.TODO.List.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Task {
    private UUID id;
    private String name;
    private String title;
    private Integer completed;
    private Date createdDate;
    private Date changedDate;
    private Integer priority;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID getId() {
        return id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }


    @Basic
    @Column(name = "completed")
    public Integer getCompleted() {
        return completed;
    }


    @Basic
    @Column(name = "createdDate")
    public Date  getCreatedDate() {
        return createdDate;
    }

    @Basic
    @Column(name = "changedDate")
    public Date  getChangedDate() {
        return changedDate;
    }

    @Basic
    @Column(name = "priority")
    public Integer getPriority() {
        return priority;
    }
}
