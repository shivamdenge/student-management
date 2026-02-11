package com.shivamdenge.Student_Management.controller;

import com.shivamdenge.Student_Management.dto.StudentDto;
import com.shivamdenge.Student_Management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/create-student")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentDto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable(name = "id") Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> updateRequiredField(@PathVariable(name = "id")Long id, @RequestBody Map<String,Object> updatedFields){
        return ResponseEntity.ok(studentService.updateRequiredField(id,updatedFields));
    }

}
