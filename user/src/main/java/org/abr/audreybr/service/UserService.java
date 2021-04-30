package org.abr.audreybr.service;

import org.abr.audreybr.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static long currentId = 123;

    private final List<UserDTO> userDAOList = new ArrayList<>();

    public UserService() {
        super();
        userDAOList.add(new UserDTO(123L, "user123"));
        userDAOList.add(new UserDTO(456L, "user456"));
        userDAOList.add(new UserDTO(789L, "user789"));
    }

    public List<UserDTO> getAll() {

        return userDAOList.stream().map(this::daoToDto).collect(Collectors.toList());
    }

    public UserDTO get(String id) {
        final long userId = Long.parseLong(id);
        Optional<UserDTO> userDAOOptional = userDAOList.stream().filter(userDAO -> userDAO.getId() == userId).findFirst();

        return daoToDto(userDAOOptional.get());
    }

    public UserDTO create(UserDTO userDTO) {
        UserDTO userDAO = dtoToDao(userDTO);
        currentId++;
        userDAO.setId(currentId);
        userDAOList.add(userDAO);

        return daoToDto(userDAO);
    }

    public UserDTO update(UserDTO userDTO) {
        final long userId = userDTO.getId();
        Optional<UserDTO> userDAOOptional = userDAOList.stream().filter(userDAO -> userDAO.getId() == userId).findFirst();
        int userIndex = userDAOList.indexOf(userDAOOptional.get());
        userDAOList.set(userIndex, dtoToDao(userDTO));

        return daoToDto(userDAOList.get(userIndex));
    }


    public Long delete(String id) {
        final long userId = Long.parseLong(id);
        Optional<UserDTO> userDAOOptional = userDAOList.stream().filter(userDAO -> userDAO.getId() == userId).findFirst();
        userDAOList.remove(userDAOOptional.get());

        return userId;
    }


    private UserDTO daoToDto(UserDTO userDAO) {

        return new UserDTO(userDAO.getId(), userDAO.getName());
    }

    private UserDTO dtoToDao(UserDTO userDTO) {

        return new UserDTO(userDTO.getId(), userDTO.getName());
    }
}
