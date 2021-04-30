package org.abr.audreybr.service;

import org.abr.audreybr.dao.UserRepository;
import org.abr.audreybr.dto.UserDTO;
import org.abr.audreybr.entity.Department;
import org.abr.audreybr.entity.User;
import org.abr.audreybr.exception.BadRequestException;
import org.abr.audreybr.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;


@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getUser(long id) {
        return repository.findById(id).orElseThrow(()->new NotFoundException("Cet utilisateur n'existe pas"));
    }

    public User login(String name) {
        try {
            return repository.findByName(name).get(0);
        }catch (Exception e){
            throw new NotFoundException("Cet utilisateur n'existe pas");
        }
    }

    public User create(User user) {
        if(user.getName() == null || user.getName().isEmpty()){
            throw  new BadRequestException("Input values can't be empty");
        }
        User newUser = new User(user.getName());
        Department[] departments = getDepartmentsByCode(user.getCode());

        if(departments!= null && departments.length > 0){
            newUser.setDepartment(departments[0].getNom());
        }
        newUser.setCode(user.getCode());
        repository.save(newUser);
        return newUser;
    }

    public User editUser(long id,User user) throws NotFoundException{
        if(user.getName() == null || user.getName().isEmpty()){
            throw new BadRequestException("Input values can't be empty");
        };
        User modifiedUser = repository.findById(id).orElseThrow(()->new NotFoundException("ce client n'exist pas"));
        modifiedUser.setName(user.getName());
        modifiedUser.setCode(user.getCode());

        //get city name from external api
        Department[] departments = getDepartmentsByCode(user.getCode());
        if(departments.length > 0){
            modifiedUser.setDepartment(departments[0].getNom());
        }
        repository.save(modifiedUser);
        return modifiedUser;
    }


    public ResponseEntity<String> deleteUser(long id){
        User user = getUser(id);
        repository.delete(user);
        return ResponseEntity.status(HttpStatus.OK).body("Le client ("+user.getName()+") a bien été supprimré");
    }

    public Department[] getDepartmentsByCode(String code){
        String request = "";
        if(code != null && !code.isEmpty()){
            request+= "code="+code;
        }
        if(!request.isEmpty()){
            RestTemplate restTemplate = new RestTemplate();
            String baseUrl = "https://geo.api.gouv.fr/departements?";
            ResponseEntity<Department[]> response = restTemplate.getForEntity(baseUrl+request+"&fields=nom", Department[].class);
            return response.getBody();
        }else {
            return null;
        }
    }
}
