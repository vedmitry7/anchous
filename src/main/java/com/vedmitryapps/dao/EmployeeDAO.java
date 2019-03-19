package com.vedmitryapps.dao;

import com.vedmitryapps.model.Employee;
import com.vedmitryapps.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class EmployeeDAO {

    @Autowired
    EmployeeRepository employeeRepository;


    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findOne(Long id){
        return employeeRepository.findById(id).get();
    }

    public void delete(Employee employee){
        employeeRepository.delete(employee);
    }


}
