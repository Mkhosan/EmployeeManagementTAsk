package com.zaio.Controllers;

import com.zaio.Entity.Employee;
import com.zaio.MessageHandling.Response;
import com.zaio.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class EmployeeController {

//    declares a private and final instance variable employeeService of type EmployeeService
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
        if (employee.getName() == null || employee.getName().isBlank() ||
                employee.getDepartment() == null || employee.getDepartment().isBlank() ||
                employee.getDesignation() == null || employee.getDesignation().isBlank()) {
            return ResponseEntity.badRequest().body(new Response("Invalid employee data. Please provide all required fields."));
        }
        Employee employeeSaved = employeeService.save(employee);
        return ResponseEntity.ok(employeeSaved);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployee();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Object> getAllEmployeeById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build(); // Return a bad request response
        }

        // Check if the employee with the given ID exists
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isEmpty()) {
            return ResponseEntity.badRequest().body(new Response("Employee with ID " + id + " not found."));
        }
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Object> removeEmployee(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build(); // Return a bad request response
        }

        // Check if the employee with the given ID exists
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isEmpty()) {
            return ResponseEntity.badRequest().body(new Response("Employee with ID " + id + " not found."));
        }
        employeeService.deleteEmployee(employee.get());
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Data has been Created"));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee updatedEmployee, @PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build(); // Return a bad request response
        }

        // Check if the employee with the given ID exists
        Optional<Employee> employeeOpt = employeeService.findById(id);
        if (employeeOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(new Response("Employee with ID " + id + " not found."));
        }

        Employee employeeObj = employeeOpt.get();

        employeeObj.setName(updatedEmployee.getName());
        employeeObj.setDesignation(updatedEmployee.getDesignation());
        employeeObj.setDepartment(updatedEmployee.getDepartment());
        employeeObj.setSalary(updatedEmployee.getSalary());

        Employee updatedEmployeeObj = employeeService.save(employeeObj);
        return ResponseEntity.ok(updatedEmployeeObj);
    }
}
