package in.Atithi.crudSpringBootDemo.service;

import in.Atithi.crudSpringBootDemo.entity.Student;
import in.Atithi.crudSpringBootDemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;

    @Autowired
    StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student insertStudent(Student studentEntry){
        return studentRepository.save(studentEntry);
    }

    public Student gettingStudent(Long id){
        Optional<Student> st = studentRepository.findByIdAndDeletedIsFalse(id);
        if(st.isPresent())
            return st.get();
        return null;
    }

    public List<Student> gettingAllStudent(){
        List<Student> slist = studentRepository.findByDeletedIsFalse();
        return slist;
    }

    public Student updatingStudent(Long id, Student studentReq){
        Optional<Student> st = studentRepository.findById(id);
        if(st.isEmpty()){
            return null;
        }

        Student s = st.get();
        s.setAge(studentReq.getAge());
//        s.setId(studentReq.getId());//Id can not be changed, even if user tries to pass, I won't set it
        s.setEmail(studentReq.getEmail());
        s.setName(studentReq.getName());
        s.setRollNo(studentReq.getRollNo());
        s.setSubject(studentReq.getSubject());
        s.setDeleted(studentReq.getDeleted());//Soft_Delete can not be changed, even if user tries to pass it as true, I won't set it

        Student response = studentRepository.save(s);
        return response;
    }

    public Boolean deletingStudent(Long id){
        Boolean res = studentRepository.existsById(id);
        if(!res)
            return false;
        studentRepository.deleteById(id);
        return true;
    }

    public Boolean delteSoftly(Long id){
        Optional<Student> ispresent = studentRepository.findById(id);
        if(ispresent.isEmpty())
            return false;
        Student s = ispresent.get();
        s.setDeleted(true);
        studentRepository.save(s);
        return true;
    }
}
