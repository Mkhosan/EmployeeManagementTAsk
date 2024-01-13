package com.zaio.Services;

import com.zaio.Entity.Employee;
import com.zaio.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

//    create save method to save employee
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long Id) {
        return employeeRepository.findById(Id);
    }
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }
}
