package org.abr.audreybr.service;

import org.abr.audreybr.dao.UserDAO;
import org.abr.audreybr.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static long currentId = 123;

    private final List<UserDAO> userDAOList = new ArrayList<>();

    public UserService() {
        super();
        userDAOList.add(new UserDAO(123L, "user123"));
        userDAOList.add(new UserDAO(456L, "user456"));
        userDAOList.add(new UserDAO(789L, "user789"));
    }

    public List<UserDTO> getAll() {

        return userDAOList.stream().map(this::daoToDto).collect(Collectors.toList());
    }

    public UserDTO get(String id) {
        final long userId = Long.parseLong(id);
        Optional<UserDAO> userDAOOptional = userDAOList.stream().filter(userDAO -> userDAO.getId() == userId).findFirst();

        return daoToDto(userDAOOptional.get());
    }

    public UserDTO create(UserDTO userDTO) {
        UserDAO userDAO = dtoToDao(userDTO);
        currentId++;
        userDAO.setId(currentId);
        userDAOList.add(userDAO);

        return daoToDto(userDAO);
    }

    public UserDTO update(UserDTO userDTO) {
        final long userId = userDTO.getId();
        Optional<UserDAO> userDAOOptional = userDAOList.stream().filter(userDAO -> userDAO.getId() == userId).findFirst();
        int userIndex = userDAOList.indexOf(userDAOOptional.get());
        userDAOList.set(userIndex, dtoToDao(userDTO));

        return daoToDto(userDAOList.get(userIndex));
    }


    public Long delete(String id) {
        final long userId = Long.parseLong(id);
        Optional<UserDAO> userDAOOptional = userDAOList.stream().filter(userDAO -> userDAO.getId() == userId).findFirst();
        userDAOList.remove(userDAOOptional.get());

        return userId;
    }


    private UserDTO daoToDto(UserDAO userDAO) {

        return new UserDTO(userDAO.getId(), userDAO.getName());
    }

    private UserDAO dtoToDao(UserDTO userDTO) {

        return new UserDAO(userDTO.getId(), userDTO.getName());
    }
}
