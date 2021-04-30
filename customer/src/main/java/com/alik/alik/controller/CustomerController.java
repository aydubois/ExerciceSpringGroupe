package com.alik.alik.controller;

import com.alik.alik.exception.NotFoundException;
import com.alik.alik.entity.Customer;
import com.alik.alik.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomers());
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable int id){
      return customerService.getCustomerById(id);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) throws NotFoundException{
        return customerService.addCustomer(customer);

    }

    @PutMapping("/{id}")
    public Customer editCustomer(@PathVariable int id, @RequestBody Customer customer) throws NotFoundException {
        return customerService.editCustomer(id,customer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) throws NotFoundException{
        return customerService.deleteCustomer(id);
    }
}
