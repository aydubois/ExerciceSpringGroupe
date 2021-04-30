package com.alik.alik.service;

import com.alik.alik.BadRequestException;
import com.alik.alik.dao.CustomerRepository;
import com.alik.alik.entity.City;
import com.alik.alik.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.alik.alik.NotFoundException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int id) throws NotFoundException{
        return customerRepository.findById(id).orElseThrow(()->new NotFoundException("ce client n'exist pas"));
    }

    /*public Customer getCustomerByName(String name){
        return customers.stream().filter(order -> order.getName().equals(name)).findFirst().orElse(null);
    }*/

    public Customer addCustomer(Customer customer){
        if(customer.getName() == null || customer.getName().isEmpty() ){
            throw new BadRequestException("Input values can't be empty");
        };
        Customer newCustomer = new Customer(customer.getName());
        //get city name from external api
        City[] cities = getCityByZipcode(customer.getZipCode());
        if(cities.length > 0){
            newCustomer.setCity(cities[0].getName());
        }
        newCustomer.setZipCode(customer.getZipCode());
        customerRepository.save(newCustomer);
        return newCustomer;

    }

    public Customer editCustomer(int id,Customer customer) throws NotFoundException{
        if(customer.getName() == null || customer.getName().isEmpty()){
            throw new BadRequestException("Input values can't be empty");
        };
        Customer modifiedCustomer = customerRepository.findById(id).orElseThrow(()->new NotFoundException("ce client n'exist pas"));
        modifiedCustomer.setName(customer.getName());
        modifiedCustomer.setZipCode(customer.getZipCode());

        //get city name from external api
        City[] cities = getCityByZipcode(customer.getZipCode());
        if(cities.length > 0){
            modifiedCustomer.setCity(cities[0].getName());
        }
        customerRepository.save(modifiedCustomer);
        return modifiedCustomer;
    }

    public ResponseEntity<String> deleteCustomer(int id){
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
        return ResponseEntity.status(HttpStatus.OK).body("Le client ("+customer.getName()+") a bien été supprimré");
    }

    public City[] getCityByZipcode(String zipcode){
        String request = "";
        if(zipcode != null && !zipcode.isEmpty()){
            request+= "codePostal="+zipcode;
        }
        if(!request.isEmpty()){
            RestTemplate restTemplate = new RestTemplate();
            String baseUrl = "https://geo.api.gouv.fr/communes?";
            ResponseEntity<City[]> response = restTemplate.getForEntity(baseUrl+request+"&fields=nom",City[].class);
            return response.getBody();
        }else {
            return null;
        }
    }
}
