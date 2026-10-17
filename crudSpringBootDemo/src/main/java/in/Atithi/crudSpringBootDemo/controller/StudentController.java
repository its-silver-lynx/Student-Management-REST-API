package in.Atithi.crudSpringBootDemo.controller;

import in.Atithi.crudSpringBootDemo.entity.Student;
import in.Atithi.crudSpringBootDemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    StudentService studentService;

    @Autowired
    StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        student.setDeleted(false);
        if(student.getAge()>100 || student.getAge()<0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        Student s1 = studentService.insertStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(s1);
    }

    @GetMapping("get")
    public ResponseEntity<Student> getStudent(@RequestParam long id){
        Student s = studentService.gettingStudent(id);

        if(s==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(s);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> ls = studentService.gettingAllStudent();
        if(ls.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(ls);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        Student s = studentService.updatingStudent(id, student);
        if(s==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        Boolean s = studentService.deletingStudent(id);
        if(s==false)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body("Student Deleted");
    }

    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<String> softDeleteStudent(@PathVariable Long id){
        Boolean present = studentService.delteSoftly(id);
        if(!present)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found To Delete");
        return ResponseEntity.status(HttpStatus.OK).body("Student Deleted Softly");
    }
}
