package com.web.learningBackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.learningBackend.model.Teacher;
import com.web.learningBackend.repository.Teacher_repository;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/teacher")

public class Teacher_controller {
    @Autowired
    private Teacher_repository teacher_repository;

    //Get All from Table
    @GetMapping("/getAll")
    public  ResponseEntity<?> getAll(){
        try{
            List<Teacher> teacherList = teacher_repository.findAll();

            if(teacherList.isEmpty()){
                return new ResponseEntity<>("No data", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(teacherList, HttpStatus.OK);
            }
        } catch (Exception non){
            return new ResponseEntity<>("Network Error", HttpStatus.BAD_REQUEST);
        }
    }
  
    //get by ID
    @GetMapping("/getby/{teacherid}")
    public ResponseEntity<?> getByID(@PathVariable int teacherid){
        try{
            Optional<Teacher> optionalTeachear = teacher_repository.findById(teacherid);

            if(optionalTeachear.isPresent()){
                return new ResponseEntity<>(optionalTeachear, HttpStatus.OK);

            }else{
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception non){
            return new ResponseEntity<>("Not Added", HttpStatus.BAD_REQUEST);
        }
    }

    //add in table
    @PostMapping("/Add")
    public ResponseEntity<?> AddTeacher(@RequestBody Teacher teacher){
        try{
            Teacher teacher1 = teacher_repository.save(teacher);
            return new ResponseEntity<>("Added", HttpStatus.OK);
        } catch (Exception non){
            return new ResponseEntity<>("Not Added", HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/{teacherid}")
    public ResponseEntity<?> deleteTeacher(@PathVariable int teacherid){
        try{
            teacher_repository.deleteById(teacherid);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception non){
            return new ResponseEntity<>("Not Deleted", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{teacherID}")
    public ResponseEntity<?> updateTeacher(@PathVariable int teacherID,@RequestBody Teacher teacher){
        try{
            if(teacher_repository.findById(teacherID).isPresent()){
                Teacher teacher1 = teacher_repository.save(teacher);
                
                teacher1.setEmail(teacher.getEmail());
                teacher1.setLink(teacher.getLink());
                teacher1.setSubjectcode(teacher.getSubjectcode());
                teacher1.setSubjectname(teacher.getSubjectname());
                teacher1.setSubjectdescription(teacher.getSubjectdescription());

                Teacher updateTeacher = teacher_repository.save(teacher1);

                return new ResponseEntity<>("SucessFull Update", HttpStatus.OK);

            }else{
                return new ResponseEntity<>("Not updated", HttpStatus.CONFLICT);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Not compeleted", HttpStatus.BAD_REQUEST);
        }
    }
}
