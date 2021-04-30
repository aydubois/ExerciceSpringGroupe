package org.abr.audreybr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String department;

    @Column
    private String code;

    @Column
    private boolean isAdmin;

    public User(String name, String department, String code, boolean isAdmin) {
        this.name = name;
        this.department = department;
        this.code = code;
        this.isAdmin = isAdmin;
    }

    public User(String name){
        this.name = name;
    }
}
