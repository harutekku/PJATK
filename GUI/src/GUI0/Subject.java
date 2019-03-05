package GUI0;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String name;
    private Person teacher;
    private List<Student> studentList=new ArrayList<>();

    public Subject(String na){
        name=na;
    }

    public void setTeacher(Person per){
        teacher=per;
    }

    public void addStudent(Student stud)throws TooManyStudentsException{
        if(studentList.size()<15){
            studentList.add(stud);
        }
        else{
            throw new TooManyStudentsException("Za duzo studentow");
        }
    }

    public String toString(){
        String ret="GUI, teacher: "+this.teacher.name+", students:";
        for(Student stu:studentList){
            ret+=" "+stu.name;
        }
        return ret;
    }
}
