package org.abr.audreybr.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {

    private Long id;

    private String Name;

    public UserDTO(Long id, String name) {
        this.id = id;
        Name = name;
    }
}
