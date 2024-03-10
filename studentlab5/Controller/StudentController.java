package com.example.studentlab5.Controller;

import com.example.studentlab5.APIResponse.APIResponse;
import com.example.studentlab5.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController//addStudent
@RequestMapping("api/v1/student")
public class StudentController {
    /*Create a controller called studentController with the following endpoints.
    - GET /name : return name of student
    - GET /age : return age of student
    - GET / college/degree: return bachelor or diploma or master
    - GET / study/status: return true if graduated and false if un-graduated
    - GET /students : return array of students*/
//---------------------------
    ArrayList<Student> students = new ArrayList<>();

    //--------------------------
    //endPoint - GET /name : return name of student
    @GetMapping("getName/{index}")
    public String getName(@PathVariable int index) {
        if (index < students.size()) {
            return students.get(index).getName();
        }

        APIResponse response = new APIResponse("INDEX NOT VALID");
        return response.getResponse();
    }

    //----------------------------------------------
    //    - GET /age : return age of student
    @GetMapping("getAge/{index}")
    public APIResponse getAge(@PathVariable int index) {
        if (index < students.size()) {
            int age = students.get(index).getAge();
            String respone = Integer.toString(age);
            return new APIResponse("Age is :" + respone);
        } else {
            return new APIResponse("INDEX NOT VALID");
        }

    }

    //    - GET / college/degree: return bachelor or diploma or master
    @GetMapping("/getDegree/{index}")
    public APIResponse getDegree(@PathVariable int index) {
        if (index < students.size()) {
            String info = "Name is:" + students.get(index).getName() + "\t"
                    + "Age is: " + students.get(index).getAge() + "\t" + "Degree is: "
                    + "( " + students.get(index).getDegree() + " )";
            return new APIResponse(info);
        } else return new APIResponse("INDEX NOT VALID");

    }//    - GET / study/status: return true if graduated and false if un-graduated

    @GetMapping("/getStatus/{index}")
    public APIResponse getStatus(@PathVariable int index) {
        if (index < students.size()) {
            if (students.get(index).getStatus().equalsIgnoreCase("graduated")) {
                return new APIResponse("True");
            } else if (students.get(index).getStatus().equalsIgnoreCase("ungraduated")) {
                return new APIResponse("False");
            }
        } else {
            return new APIResponse("INDEX NOT VALID");
        }
        return new APIResponse("INDEX NOT VALID");
    }


    //    - GET /students : return array of students*/
    @GetMapping("/getAllStudents")
    public ArrayList<Student> getStudents() {
        return students;
    }

    //----------------------CRUD---------ADDCreate----------------
    @PostMapping("/addStudent")
    public APIResponse addStudent(@RequestBody Student student) {
        students.add(student);
        return new APIResponse("Student " + student.getName() + " is added Successfully");
    }

    //----------------------UPDATE
    @PutMapping("/updateinfo/{index}")
    public APIResponse updateInfo(@PathVariable int index, @RequestBody Student student) {
        if (index < students.size()) {
            students.set(index, student);
            return new APIResponse("Updated successfully");
        } else {
            return new APIResponse("INDEX NOT VALID");
        }
    }//-----------------------------DELETE

    @DeleteMapping("/delete/{index}")
    public APIResponse delete(@PathVariable int index) {
        if (index < students.size()) {
            String info="Student: "+students.get(index).getName();
            students.remove(index);
            return new APIResponse(info+" deleted successfully");
        } else {
            return new APIResponse("NOT VALID INDEX");
        }

    }

    ///--------------------------------GET-ONE-STUDENT
    @GetMapping("/getStudent/{index}")
    public Student getStudent(@PathVariable int index) {
        if (index < students.size()) {
            return students.get(index);
        } else return null;
    }
}
