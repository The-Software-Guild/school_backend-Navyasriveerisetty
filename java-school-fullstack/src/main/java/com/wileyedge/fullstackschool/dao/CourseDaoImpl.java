package com.wileyedge.fullstackschool.dao;

import com.wileyedge.fullstackschool.dao.mappers.CourseMapper;
import com.wileyedge.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course createNewCourse(Course course) {
        //YOUR CODE STARTS HERE

        String sql = "INSERT INTO course VALUES(?,?,?,?)";
		jdbcTemplate.update(sql, course.getCourseId(), course.getCourseName(), course.getCourseDesc(),
		course.getTeacherId());
		return course;

        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE
        String query = "SELECT * FROM courses WHERE id = ?";
        return jdbcTemplate.query(query, new CourseMapper(), id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public Course findCourseById(int id) {
        //YOUR CODE STARTS HERE
       String query = "SELECT * FROM courses WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new CourseMapper(), id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateCourse(Course course) {
        //YOUR CODE STARTS HERE
        String updateQuery = "UPDATE courses SET name = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(updateQuery, course.getName(), course.getDescription(), course.getId());

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteCourse(int id) {
        //YOUR CODE STARTS HERE
        String deleteQuery = "DELETE FROM courses WHERE id = ?";
        jdbcTemplate.update(deleteQuery, id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteAllStudentsFromCourse(int courseId) {
        //YOUR CODE STARTS HERE
        String deleteQuery = "DELETE FROM students_courses WHERE course_id = ?";
        jdbcTemplate.update(deleteQuery, courseId);

        //YOUR CODE ENDS HERE
    }
}
