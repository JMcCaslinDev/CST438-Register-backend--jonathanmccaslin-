package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;
	
	
	//As an administrator, I can add a student to the system.  
	//I input the student email and name.  The student email must not 
	//already exists in the system.
	
	@PostMapping("/student")
	public Boolean addStudent( @RequestParam("name") String name, @RequestParam("email") String email) {
		System.out.println("/student called.");
		System.out.println(name);
		System.out.println(email);
		
		Student student = studentRepository.findByEmail(email);
		System.out.println(student);
		if (student == null) {
			System.out.println("Student not found, storing new student");
			//push student email and name to database
			Student newStudent = new Student();
			newStudent.setName(name);
			newStudent.setEmail(email);
			studentRepository.save(newStudent);
			return true;
		}
		
		//return student id
		return false;
	}
	

	
	//As an administrator, I can put a HOLD 
	//on a student's registration.
	
	@PutMapping("/student/hold")
	public String putHoldOnStudent(@RequestParam("id") int id) {
		System.out.println(id);
		Student student = studentRepository.findById(id).orElse(null);
		System.out.println(student);

		//check if HOLD already held
		
		if(student.getStatus().equals("HOLD")) {
			
			//hold already set no need to set hold return false
			return "Hold already set, no need to set hold twice";
			
		} else if (student.getStatus() == null) {
			
			//status is null need to set HOLD and return 
			student.setStatus("HOLD"); 
			studentRepository.save(student);
			return "null Status Found, Hold has been set succesfully";
			
		} else {
			
			//hold not set need to set hold and return true
			student.setStatus("HOLD"); 
			studentRepository.save(student);
			return "Hold has been set succesfully";
			
		}
		
	}
	
	@PutMapping("/student/removeHold")
	public String removeHoldOnStudent(@RequestParam("id") int id) {
		System.out.println(id);
		Student student = studentRepository.findById(id).orElse(null);

		//check if HOLD already held
		
		if(student.getStatus().equals("HOLD")) {
			
			//HOLD is set remove hold and set to null
			student.setStatus(null); 
			studentRepository.save(student);
			return "HOLD found removing HOLD and setting to null";
			
		} else if (student.getStatus() == null) {
			
			//if null is found do nothing
			return "null Status Found, No HOLD found to remove.";
			
		} else {
			
			//shouldn't reach this if working properly
			return "Error something went wrong";
			
		}
		
	}
	
	
	
}


