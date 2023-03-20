package com.zi.boundary;

import com.zi.entity.EmployeeEntity;
import com.zi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/saveEmployees")
    public ResponseEntity<String> saveEmployees(@RequestBody List<EmployeeEntity> employees) {
        employeeRepository.saveAll(employees);
        return ResponseEntity.ok("saved employees successfully");
    }

    @GetMapping("/searchEmployee")
    public ResponseEntity<List<EmployeeEntity>> searchEmployee(@RequestParam String lastName) {
        List<EmployeeEntity> employees = employeeRepository.findByLastName(lastName);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/deleteEmployee")
    public ResponseEntity<String> searchEmployee(@RequestParam Long id) {
        employeeRepository.deleteById(id);
        return ResponseEntity.ok("deleted the employee successfully");
    }

}
