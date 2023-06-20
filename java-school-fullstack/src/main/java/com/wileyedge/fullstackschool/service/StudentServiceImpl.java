package com.wileyedge.fullstackschool.service;

import com.wileyedge.fullstackschool.dao.CourseDao;
import com.wileyedge.fullstackschool.dao.StudentDao;
import com.wileyedge.fullstackschool.model.Course;
import com.wileyedge.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE
    @Autowired
    StudentDao studentDao;
    @Autowired
    CourseDao courseDao;
    List<Student> students = null;
    List<Course> courses = null;
    
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

    	students = studentDao.getAllStudents();
        return students;

        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE

    	Student student = null;
    	try {
    		student = studentDao.findStudentById(id);
    	} catch (DataAccessException e) {
    		student.setStudentFirstName("Student Not Found");
    		student.setStudentLastName("Student Not Found");
    	}
    	return student;

        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE

    	Student s = null;
    	
    	if(student.getStudentFirstName().isBlank() || student.getStudentLastName().isBlank()) {
    		student.setStudentFirstName("First Name blank, student NOT added");
    		student.setStudentLastName("Last Name blank, student NOT added");
    		return student;
    	} else {
    		s = studentDao.createNewStudent(student);
    	}
        return s;

        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE

    	if(id != student.getStudentId()) {
    		String response = "IDs do not match, student not updated";
    		student.setStudentFirstName(response);
    		student.setStudentLastName(response);
    	} else {
    		studentDao.updateStudent(student);
    	}
    	return student;

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE

    	studentDao.deleteStudent(id);

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

    	courses = courseDao.getAllCourses();
    	boolean outcome = true;
    	for(Student s : students) {
    		if(s.getStudentId() == studentId) {
    			if(s.getStudentFirstName().equals("Student Not Found")) {
    				System.out.println("Student not found");
    				outcome = false;
    			}
    		}
    	}
    	for(Course c : courses) {
    		if(c.getCourseId() == courseId) {
    			if(c.getCourseName().equals("Course Not Found")) {
    				System.out.println("Course not found");
    				outcome = false;
    			}
    		}
    	}
    	if(outcome) {
    		studentDao.deleteStudentFromCourse(studentId, courseId);
    		System.out.println("Student: " + studentId + " deleted from course: " + courseId);
    	}

        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

    	try {
    		boolean outcome = true;
        	for(Student s : students) {
        		if(s.getStudentId() == studentId) {
        			if(s.getStudentFirstName().equals("Student Not Found")) {
        				System.out.println("Student not found");
        				outcome = false;
        			}
        		}
        	}
        	for(Course c : courses) {
        		if(c.getCourseId() == courseId) {
        			if(c.getCourseName().equals("Course Not Found")) {
        				System.out.println("Course not found");
        				outcome = false;
        			}
        		}
        	}
        	if(outcome) {
        		studentDao.deleteStudentFromCourse(studentId, courseId);
        		System.out.println("Student: " + studentId + " added to course: " + courseId);
        	}
    	} catch(Exception e) {
    		System.out.println("Student: " + studentId + " already enrolled in course: " + courseId);
    	}

        //YOUR CODE ENDS HERE
    }
}
