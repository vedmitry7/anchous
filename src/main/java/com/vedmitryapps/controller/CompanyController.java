package com.vedmitryapps.controller;

import com.vedmitryapps.model.Company;
import com.vedmitryapps.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

/*    @Autowired
    EmployeeDAO employeeDAO;*/

    @Autowired
    CompanyRepository companyRepository;

    @PostMapping()
    public Company createEmployee(@Valid @RequestBody Company company) {
        return companyRepository.save(company);
        //   return employeeDAO.save(employee);
    }

    @GetMapping()
    public List<Company> getAllEmployees(@RequestParam(value="name", defaultValue = "null") String name) {

        if(name!=null && !name.equals("null")){
            System.out.println("Name - " + name);
        } else {
            System.out.println("without name");
        }

         return companyRepository.findAll();
    }

   @GetMapping("/{id}")
    public ResponseEntity<Company> getEmployeeById(@PathVariable(value = "id") Long id) {

        Company company = companyRepository.findById(id).get();
        if (company == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(company);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Company> updateEmployee(@PathVariable(value = "id") Long id, @Valid @RequestBody Company company) {
        Company company1 = companyRepository.findById(id).get();

        if (company1 == null) {
            return ResponseEntity.notFound().build();
        }

        company1.setName(company.getName());
        company1.setName(company.getName());

       Company updateEmployee = companyRepository.save(company1);

       Map<String,String> map = new HashMap<>();

        return ResponseEntity.ok().body(updateEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Company> deleteEmployeeById(@PathVariable(value = "id") Long id) {
        Company employee = companyRepository.findById(id).get();
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        companyRepository.delete(employee);
        return ResponseEntity.ok().build();
    }
}
