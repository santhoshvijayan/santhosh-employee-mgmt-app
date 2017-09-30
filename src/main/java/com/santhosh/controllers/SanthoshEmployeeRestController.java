package com.santhosh.controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.santhosh.models.Employee;
import com.santhosh.repositories.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class SanthoshEmployeeRestController {

	@GetMapping
	public Collection<Employee> employee() {
		return this.employeeRepository.findAll();
	}

	@GetMapping("/pi7ng")
	public String ping() {
		System.out.println("test");
		return "Test";
	}

	@GetMapping("/{id}")
	public Employee getTaskById(@PathVariable("id") String id) {
		return employeeRepository.findOne(id);
	}

	@PostMapping
	public void saveTask(@RequestBody @Valid Employee task) {
		task.setId(null);
		employeeRepository.save(task);
	}

	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable("id") String id) {
		employeeRepository.delete(id);
	}

	@PutMapping("/edit/{id}")
	public void editTask(@RequestBody @Valid Employee editedTask, @PathVariable("id") String id) {
		editedTask.setId(id);
		employeeRepository.save(editedTask);
	}
	
	
	@PostMapping("/api/upload")
    public void uploadFile(
            @RequestParam("file") MultipartFile uploadfile) {
		System.out.println("Success");
        if (!uploadfile.isEmpty()) {
        	try {
        		
        		byte[] bytes = uploadfile.getBytes();
                String completeData = new String(bytes);
                String[] rows = completeData.split("\n");
                Employee emp= new Employee();
                for(int i=1;i<rows.length;i++) {
                	String[] details=rows[i].split(";");
                	emp.setId(null);
                	emp.setEmpname(details[0].replaceAll("\"", ""));
                	emp.setCity(details[1].replaceAll("\"", ""));
                	emp.setPhone(details[2].replaceAll("\"", ""));
                	emp.setJoindate(new Date());
                	emp.setIsactive(true);
            		employeeRepository.save(emp);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	@Autowired
	EmployeeRepository employeeRepository;
}