package com.Bank.userManagement;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentController {

    Map<Integer,Student> db = new HashMap<>(); // we're using hashmap as Database.

    @GetMapping("/get_student")
    public Student getStudent (@RequestParam("id") int rollNo) { // get information - search from our map
        return db.get(rollNo);

//        this is the request in Postman for getting any rollNO : localhost:8080/get_student?id=3
    }


    @PostMapping("/add_student") // adding info in our map
    public String addStudent(@RequestBody Student student){
        int rollNo = student.getRollNo();
        db.put(rollNo, student);
        return "Student added successfully";
    }

//    for path variable we write this Structure /{id}
    @DeleteMapping("/delete/{id}")
    public String removeStudent(@PathVariable("id") int id) {

        if(!db.containsKey(id)) {
            return "invalid Id";
        }

        db.remove(id);
        return "student remove successfully!";
    }


    @PutMapping("/update")
    // see here how to pass multiple request parameters in function.
    public String updateStudent(@RequestParam("id") int id, @RequestParam("age") int age) {

        if(!db.containsKey(id)) return "Invalid Id";

        db.get(id).setAge(age); // we pass our id get from DB and set it.
        return "updated! ";

//        we use add here to access multiple RequestParams
//        request for put : localhost:1234/update?id=1&age=2323
    }


}
