package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

@RestController
public class CustomerController {
    private static CustomerDAO customerDAO = new CustomerDAO("jdbc:sqlite:d:/SQLITE/100521.db");

    @GetMapping("/customer")
    public ArrayList<Customer> getCustomers(){
        ArrayList<Customer> customers = customerDAO.getAllCustomers();
        return customers;
    }

    @GetMapping("/customer/{id}")
    public Customer doGetCouponById(@PathVariable("id") int id)
    {
        Customer customer = customerDAO.getCustomerById(id);
        return customer;
    }

    @PostMapping("/customer")
    public void addCustomer(@RequestBody Customer c)
    {
        customerDAO.insertCustomer(c);
    }

    @PutMapping("/customer/{id}")
    public void updateCustomer(@PathVariable("id") int id, @RequestBody Customer update_c)
    {
        customerDAO.updateCustomer(update_c, id);
    }

    @DeleteMapping("/customer/{id}")
    public void delCustomerById(@PathVariable("id") int id)
    {
        customerDAO.deleteCustomer(id);
    }

}
