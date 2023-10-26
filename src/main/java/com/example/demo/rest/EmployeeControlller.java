package com.example.demo.rest;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class EmployeeControlller {
    private EmployeeService employeeService;

    public EmployeeControlller(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            throw new RuntimeException("Employee id is not found");
        }
        return employee;
    }

    @PostMapping("/employee")
    public Employee add(@RequestBody Employee theEmployee) {
        theEmployee.setId(0);
        Employee employee = employeeService.save(theEmployee);
        return employee;
    }

    @PutMapping("/employee")
    public Employee update(@RequestBody Employee theEmployee) {
        Employee employee = employeeService.save(theEmployee);
        return employee;
    }
    @DeleteMapping("/employee/{theId}")
    public String deleteById(@PathVariable int theId){
        Employee tempEmployee= employeeService.findById(theId);
        if(tempEmployee== null){
            throw  new RuntimeException("Employee id not found");
        }

        employeeService.deleteById(theId);
        return "Deleted employee id: "+theId;
    }
}
