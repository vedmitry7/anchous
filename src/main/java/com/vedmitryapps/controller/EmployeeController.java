package com.vedmitryapps.controller;

import com.vedmitryapps.dto.EmployeeDTO;
import com.vedmitryapps.model.Company;
import com.vedmitryapps.model.Employee;
import com.vedmitryapps.repository.CompanyRepository;
import com.vedmitryapps.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CompanyRepository companyRepository;

    ModelMapper modelMapper = new ModelMapper();
    // user here is a prepopulated User instance


    @PostMapping()
    public Employee createEmployee(@Valid @RequestBody Employee employee, @RequestParam(value="company_id") Long companyId) {
        System.out.println("C id - " + companyId);
        Company company = companyRepository.findById(companyId).get();
        employee.setCompany(company);
        return employeeRepository.save(employee);
        //   return employeeDAO.save(employee);
    }

    @GetMapping()
    public List<EmployeeDTO> getAllEmployees(@RequestParam(value="name", defaultValue = "null") String name) {
        //    return employeeDAO.findAll();

   /*     if(name!=null && !name.equals("null")){
            System.out.println("Name - " + name);
        } else {
            System.out.println("without name");
        }*/

        List<EmployeeDTO> dtoList = new ArrayList<>();

        for (Employee employee:employeeRepository.findAll()
        ) {
            dtoList.add(modelMapper.map(employee, EmployeeDTO.class));
        }
        return dtoList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(value = "id") Long id) {


        Employee employee = employeeRepository.findById(id).get();

        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

        return ResponseEntity.ok(employeeDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id, @Valid @RequestBody Employee employee) {
        Employee employee1 = employeeRepository.findById(id).get();

        if (employee1 == null) {
            return ResponseEntity.notFound().build();
        }

        employee1.setName(employee.getName());
        employee1.setDesignation(employee.getDesignation());
        employee1.setExpertise(employee.getExpertise());
        employee1.setName(employee.getName());

        Employee updateEmployee = employeeRepository.save(employee1);

        return ResponseEntity.ok().body(updateEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable(value = "id") Long id) {
        Employee employee = employeeRepository.findById(id).get();
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();
    }
}
