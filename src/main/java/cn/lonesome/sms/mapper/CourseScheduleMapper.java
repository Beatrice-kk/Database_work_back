package cn.lonesome.sms.mapper;

import cn.lonesome.sms.model.dto.CourseScheduleDto;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * @author Yale
 * @version 1.0
 */
@Mapper
public interface CourseScheduleMapper {
    @Select("select count(*) from chenwk_teacher_course_schedule")
    int selectAllCount();

    @Select("select count(*) from chenwk_teacher_course_schedule where cwk_teacher_id03=#{teacherId}")
    int selectCountByTeacherId(long teacherId);

    @Select("select count(*) from chenwk_teacher_course_schedule where cwk_term03=#{term}")
    int selectCountByTerm(String term);

    @Select("select count(*) from chenwk_teacher_course_schedule where cwk_year03=#{year}")
    int selectCountByYear(int year);

    @Select("select count(*) from chenwk_teacher_course_schedule where cwk_class_name03=#{className}")
    int selectCountByClassName(String className);

    @Select("select cwk_course_id03, cwk_course_name03, cwk_class_name03, cwk_teacher_name03, cwk_teacher_id03, " +
            "cwk_year03, cwk_term03 " +
            "from chenwk_teacher_course_schedule " +
            "limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_course_id03", property = "courseId"),
            @Result(column = "cwk_course_name03", property = "courseName"),
            @Result(column = "cwk_class_name03", property = "className"),
            @Result(column = "cwk_teacher_name03", property = "teacherName"),
            @Result(column = "cwk_teacher_id03", property = "teacherId"),
            @Result(column = "cwk_year03", property = "year"),
            @Result(column = "cwk_term03", property = "term")
    })
    ArrayList<CourseScheduleDto> selectAll(int offset, int limit);

    @Select("select cwk_course_id03, cwk_course_name03, cwk_class_name03, cwk_teacher_name03, cwk_teacher_id03, " +
            "cwk_year03, cwk_term03 " +
            "from chenwk_teacher_course_schedule " +
            "where cwk_teacher_id03=#{teacherId} " +
            "limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_course_id03", property = "courseId"),
            @Result(column = "cwk_course_name03", property = "courseName"),
            @Result(column = "cwk_class_name03", property = "className"),
            @Result(column = "cwk_teacher_name03", property = "teacherName"),
            @Result(column = "cwk_teacher_id03", property = "teacherId"),
            @Result(column = "cwk_year03", property = "year"),
            @Result(column = "cwk_term03", property = "term")
    })
    ArrayList<CourseScheduleDto> selectByTeacherId(long teacherId, int offset, int limit);

    @Select("select cwk_course_id03, cwk_course_name03, cwk_class_name03, cwk_teacher_name03, cwk_teacher_id03, " +
            "cwk_year03, cwk_term03 " +
            "from chenwk_teacher_course_schedule " +
            "where cwk_term03=#{term} " +
            "limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_course_id03", property = "courseId"),
            @Result(column = "cwk_course_name03", property = "courseName"),
            @Result(column = "cwk_class_name03", property = "className"),
            @Result(column = "cwk_teacher_name03", property = "teacherName"),
            @Result(column = "cwk_teacher_id03", property = "teacherId"),
            @Result(column = "cwk_year03", property = "year"),
            @Result(column = "cwk_term03", property = "term")
    })
    ArrayList<CourseScheduleDto> selectByTerm(String term, int offset, int limit);

    @Select("select cwk_course_id03, cwk_course_name03, cwk_class_name03, cwk_teacher_name03, cwk_teacher_id03, " +
            "cwk_year03, cwk_term03 " +
            "from chenwk_teacher_course_schedule " +
            "where cwk_year03=#{year} " +
            "limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_course_id03", property = "courseId"),
            @Result(column = "cwk_course_name03", property = "courseName"),
            @Result(column = "cwk_class_name03", property = "className"),
            @Result(column = "cwk_teacher_name03", property = "teacherName"),
            @Result(column = "cwk_teacher_id03", property = "teacherId"),
            @Result(column = "cwk_year03", property = "year"),
            @Result(column = "cwk_term03", property = "term")
    })
    ArrayList<CourseScheduleDto> selectByYear(int year, int offset, int limit);

    @Select("select cwk_course_id03, cwk_course_name03, cwk_class_name03, cwk_teacher_name03, cwk_teacher_id03, " +
            "cwk_year03, cwk_term03 " +
            "from chenwk_teacher_course_schedule " +
            "where cwk_class_name03=#{className} " +
            "limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_course_id03", property = "courseId"),
            @Result(column = "cwk_course_name03", property = "courseName"),
            @Result(column = "cwk_class_name03", property = "className"),
            @Result(column = "cwk_teacher_name03", property = "teacherName"),
            @Result(column = "cwk_teacher_id03", property = "teacherId"),
            @Result(column = "cwk_year03", property = "year"),
            @Result(column = "cwk_term03", property = "term")
    })
    ArrayList<CourseScheduleDto> selectByClassName(String className, int offset, int limit);

    @Select("select cwk_course_id03, cwk_course_name03, cwk_class_name03, cwk_teacher_name03, cwk_teacher_id03, " +
            "cwk_year03, cwk_term03 " +
            "from chenwk_teacher_course_schedule " +
            "where cwk_class_name03=" +
            "(select chenwk_class03.cwk_name03 from chenwk_class03, chenwk_student03 " +
            "where chenwk_class03.cwk_id03=chenwk_student03.cwk_class_id03 and chenwk_student03.cwk_id03=#{studentId}) " +
            "and cwk_term03=#{term} " +
            "limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_course_id03", property = "courseId"),
            @Result(column = "cwk_course_name03", property = "courseName"),
            @Result(column = "cwk_class_name03", property = "className"),
            @Result(column = "cwk_teacher_name03", property = "teacherName"),
            @Result(column = "cwk_teacher_id03", property = "teacherId"),
            @Result(column = "cwk_year03", property = "year"),
            @Result(column = "cwk_term03", property = "term")
    })
    ArrayList<CourseScheduleDto> selectByClassNameAndTerm(Long studentId, String term, int offset, int limit);

    @Select("select count(*) " +
            "from chenwk_teacher_course_schedule " +
            "where cwk_class_name03= " +
            "(select chenwk_class03.cwk_name03 from chenwk_class03, chenwk_student03 " +
            "where chenwk_class03.cwk_id03=chenwk_student03.cwk_class_id03 and chenwk_student03.cwk_id03=#{studentId}) " +
            "and cwk_term03=#{term} ")
    int selectCountByClassNameAndTerm(Long studentId, String term);

    @Select("select cwk_course_id03, cwk_course_name03, cwk_class_name03, cwk_teacher_name03, cwk_teacher_id03, " +
            "cwk_year03, cwk_term03 " +
            "from chenwk_teacher_course_schedule " +
            "where cwk_term03=#{term} and cwk_teacher_id03=#{teacherId} " +
            "limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_course_id03", property = "courseId"),
            @Result(column = "cwk_course_name03", property = "courseName"),
            @Result(column = "cwk_class_name03", property = "className"),
            @Result(column = "cwk_teacher_name03", property = "teacherName"),
            @Result(column = "cwk_teacher_id03", property = "teacherId"),
            @Result(column = "cwk_year03", property = "year"),
            @Result(column = "cwk_term03", property = "term")
    })
    ArrayList<CourseScheduleDto> selectByTermAndTeacherId(String term, long teacherId, int offset, int limit);

    @Select("select count(*) " +
            "from chenwk_teacher_course_schedule " +
            "where cwk_term03=#{term} and cwk_teacher_id03=#{teacherId}")
    int selectcountByTermAndTeacherId(String term, long teacherId);

    @Insert("call insert_course_schedule(#{courseId},#{className}, #{teacherId})")
    void insertCourseSchedule(String courseId, String className, long teacherId);

    @Update("update chenwk_course_schedule03 " +
            "set cwk_teacher_id03=#{teacherId} " +
            "where cwk_course_id03=#{courseId} and cwk_class_id03 = (select cwk_id03 from chenwk_class03 where cwk_name03=#{className})")
    void updateCourseSchedule(String courseId, String className, long teacherId);

    @Delete("delete from chenwk_course_schedule03 " +
            "where cwk_course_id03=#{courseId} and cwk_class_id03 = (select cwk_id03 from chenwk_class03 where cwk_name03=#{className})")
    void deleteCourseSchedule(String courseId, String className);

    @Select("select count(*) from chenwk_course_schedule03 " +
            "where cwk_course_id03=#{courseId} and cwk_teacher_id03=#{teacherId}")
    int checkCourseIdAndTeacherId(String courseId, long teacherId);
}
