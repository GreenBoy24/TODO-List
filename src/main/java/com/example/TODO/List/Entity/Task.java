package com.example.TODO.List.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Java-doc
 */
@Entity
// TODO: лучше принудительно указать в какой балице лежат данные
public class Task {
    private UUID id;
    private String name;
    private String title;
    private Integer completed; // TODO: есть же значения- выполнено/не выполнено? зачем int? это же boolean
    private Date createdDate;
    private Date changedDate;
    private Integer priority; // TODO: приоритет лучше сделать перечислением

    // TODO: чтоб не писать методы гет и сет, можно воспольщзоваться аннотациями @Getter и @Setter

    @Id // TODO: Это лучше начелисть на поле а не на метод
    @Column(name = "id") // TODO: Это лучше начелисть на поле а не на метод
    @GeneratedValue(strategy = GenerationType.IDENTITY) // TODO: лучше принудитеотно вкоде генерить ID
    public UUID getId() {
        return id;
    }

    @Basic // TODO: Это лучше начелисть на поле а не на метод
    @Column(name = "name")// TODO: Это лучше начелисть на поле а не на метод
    public String getName() {
        return name;
    }

    @Basic// TODO: Это лучше начелисть на поле а не на метод
    @Column(name = "title")// TODO: Это лучше начелисть на поле а не на метод
    public String getTitle() {
        return title;
    }

    @Basic// TODO: Это лучше начелисть на поле а не на метод
    @Column(name = "completed")// TODO: Это лучше начелисть на поле а не на метод
    public Integer getCompleted() {
        return completed;
    }


    @Basic// TODO: Это лучше начелисть на поле а не на метод
    @Column(name = "createdDate")// TODO: Это лучше начелисть на поле а не на метод
    public Date  getCreatedDate() {
        return createdDate;
    }

    @Basic// TODO: Это лучше начелисть на поле а не на метод
    @Column(name = "changedDate")// TODO: Это лучше начелисть на поле а не на метод
    public Date  getChangedDate() {
        return changedDate;
    }

    @Basic// TODO: Это лучше начелисть на поле а не на метод
    @Column(name = "priority")// TODO: Это лучше начелисть на поле а не на метод
    public Integer getPriority() {
        return priority;
    }
}
