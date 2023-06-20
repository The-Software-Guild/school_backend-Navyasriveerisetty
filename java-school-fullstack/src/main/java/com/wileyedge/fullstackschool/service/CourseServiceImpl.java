package com.wileyedge.fullstackschool.service;

import com.wileyedge.fullstackschool.dao.CourseDao;
import com.wileyedge.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE
    @Autowired
    CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        List<Course> courses = courseDao.getAllCourses();
        return courses;

        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE
    	
    	Course course = null;
    	try {
    		course = courseDao.findCourseById(id);
    	} catch (DataAccessException e) {
    		course.setCourseDesc("Course Not Found");
    		course.setCourseName("Course Not Found");
    	}
    	return course;

        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE
    	
    	Course c = null;
    	
    	if(course.getCourseName().isBlank() || course.getCourseDesc().isBlank()) {
    		course.setCourseName("Name blank, course NOT added");
    		course.setCourseDesc("Description blank, course NOT added");
    		return course;
    	} else {
    		c = courseDao.createNewCourse(course);
    	}
        return c;

        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE

    	if(id != course.getCourseId()) {
    		String response = "IDs do not match, course not updated";
    		course.setCourseDesc(response);
    		course.setCourseName(response);
    	} else {
    		courseDao.updateCourse(course);
    	}
    	return course;

        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE

    	courseDao.deleteCourse(id);
    	System.out.println("Course ID: " + id + " deleted");
    	
        //YOUR CODE ENDS HERE
    }
}
