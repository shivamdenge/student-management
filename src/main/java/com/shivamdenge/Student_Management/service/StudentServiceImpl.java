package com.shivamdenge.Student_Management.service;

import com.shivamdenge.Student_Management.dto.StudentDto;
import com.shivamdenge.Student_Management.entity.StudentEntity;
import com.shivamdenge.Student_Management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public StudentDto getById(Long id) {
        Optional<StudentEntity> student = studentRepository.findById(id);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudent() {

        List<StudentEntity> studentEntityList = studentRepository.findAll();
        return studentEntityList.stream().map(studentEntity ->
                        modelMapper.map(studentEntity, StudentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        StudentEntity student = modelMapper.map(studentDto, StudentEntity.class);
        System.out.println(student.getDept());
        StudentEntity savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!isExisted(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found " + id);
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateRequiredField(Long id, Map<String, Object> updatedFields) {
        StudentEntity student = studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found " + id));
        updatedFields.forEach((key, value) -> {
            Field field = ReflectionUtils.getRequiredField(StudentEntity.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, student, value);
        });

        return modelMapper.map(studentRepository.save(student), StudentDto.class);
    }

    public boolean isExisted(Long id) {
        return studentRepository.existsById(id);
    }
}
