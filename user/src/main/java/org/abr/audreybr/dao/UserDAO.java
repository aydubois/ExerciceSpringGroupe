package org.abr.audreybr.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDAO {

    private Long id;

    private String Name;

    public UserDAO(Long id, String name) {
        this.id = id;
        Name = name;
    }
}
