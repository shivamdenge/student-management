package com.shivamdenge.Student_Management.service;

import com.shivamdenge.Student_Management.dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentService {
    StudentDto getById(Long id);

    List<StudentDto> getAllStudent();

    StudentDto createStudent(StudentDto studentDto);

    void deleteStudent(Long id);

    StudentDto updateRequiredField(Long id, Map<String, Object> updatedFields);
}
