package cn.lonesome.sms.mapper;

import cn.lonesome.sms.model.dto.StudentCourseDto;
import cn.lonesome.sms.model.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yale
 * @version 1.0
 */
@Mapper
public interface CourseMapper {

    @Select("select count(*) from chenwk_course03")
    int selectCount();

    @Select("select count(*) from chenwk_course03 where cwk_id03 = #{id}")
    int checkId(long id);

    @Select("select count(*) from chenwk_course03 "
            + "where cwk_college_id03 = (select cwk_id03 from chenwk_college03 where cwk_name03 = #{college}) ")
    int selectCountByCollege(String college);

    @Select("select count(*) from chenwk_course03 "
            + "where cwk_term03 = #{term}")
    int selectCountByTerm(String term);

    @Select("select count(*) from chenwk_course03 "
            + "where cwk_examination_method03 = #{examinationMethod}")
    int selectCountByExaminationMethod(String examinationMethod);

    @Select("select count(*) from chenwk_course03 "
            + "where cwk_attribute03 = #{attribute}")
    int selectCountByAttribute(String attribute);

    @Select("select distinct cwk_term03 from chenwk_course03")
    ArrayList<String> selectAllTerm();

    @Select("select distinct cwk_examination_method03 from chenwk_course03")
    ArrayList<String> selectAllExaminationMethod();

    @Select("select distinct cwk_attribute03 from chenwk_course03")
    ArrayList<String> selectAllAttribute();

    @Select("select chenwk_course03.cwk_id03, chenwk_course03.cwk_name03, cwk_term03, cwk_examination_method03, cwk_credit03, "
            + "cwk_hours03, cwk_attribute03, cwk_year03, cwk_final_exam_date03,chenwk_college03.cwk_name03 as cwk_college_name03 "
            + "from chenwk_course03, chenwk_college03 "
            + "where chenwk_course03.cwk_college_id03 = chenwk_college03.cwk_id03 "
            + "limit #{offset}, #{size}")
    @Results({
        @Result(column = "cwk_id03", property = "id"),
        @Result(column = "cwk_name03", property = "name"),
        @Result(column = "cwk_term03", property = "term"),
        @Result(column = "cwk_examination_method03", property = "examinationMethod"),
        @Result(column = "cwk_credit03", property = "credit"),
        @Result(column = "cwk_hours03", property = "hours"),
        @Result(column = "cwk_attribute03", property = "attribute"),
        @Result(column = "cwk_year03", property = "year"),
        @Result(column = "cwk_final_exam_date03", property = "examDate"),
        @Result(column = "cwk_college_name03", property = "collegeName")
    })
    ArrayList<Course> selectAll(@Param("offset") int offset, @Param("size") int size);

    @Select("select chenwk_course03.cwk_id03, chenwk_course03.cwk_name03, cwk_term03, cwk_examination_method03, cwk_credit03, "
            + "cwk_hours03, cwk_attribute03, cwk_year03,cwk_final_exam_date03, chenwk_college03.cwk_name03 as cwk_college_name03 "
            + "from chenwk_course03, chenwk_college03 "
            + "where chenwk_course03.cwk_college_id03 = chenwk_college03.cwk_id03 "
            + "and chenwk_college03.cwk_name03 = #{college} "
            + "limit #{offset}, #{size}")
    @Results({
        @Result(column = "cwk_id03", property = "id"),
        @Result(column = "cwk_name03", property = "name"),
        @Result(column = "cwk_term03", property = "term"),
        @Result(column = "cwk_examination_method03", property = "examinationMethod"),
        @Result(column = "cwk_credit03", property = "credit"),
        @Result(column = "cwk_hours03", property = "hours"),
        @Result(column = "cwk_attribute03", property = "attribute"),
        @Result(column = "cwk_year03", property = "year"),
        @Result(column = "cwk_final_exam_date03", property = "examDate"),

        @Result(column = "cwk_college_name03", property = "collegeName")
    })
    ArrayList<Course> selectByCollege(@Param("college") String college, @Param("offset") int offset, @Param("size") int size);

    @Select("select chenwk_course03.cwk_id03, chenwk_course03.cwk_name03, cwk_term03, cwk_examination_method03, cwk_credit03, "
            + "cwk_hours03, cwk_attribute03, cwk_year03, cwk_final_exam_date03,chenwk_college03.cwk_name03 as cwk_college_name03 "
            + "from chenwk_course03, chenwk_college03 "
            + "where chenwk_course03.cwk_college_id03 = chenwk_college03.cwk_id03 "
            + "and cwk_term03 = #{term} "
            + "limit #{offset}, #{size}")
    @Results({
        @Result(column = "cwk_id03", property = "id"),
        @Result(column = "cwk_name03", property = "name"),
        @Result(column = "cwk_term03", property = "term"),
        @Result(column = "cwk_examination_method03", property = "examinationMethod"),
        @Result(column = "cwk_credit03", property = "credit"),
        @Result(column = "cwk_hours03", property = "hours"),
        @Result(column = "cwk_attribute03", property = "attribute"),
        @Result(column = "cwk_year03", property = "year"),
        @Result(column = "cwk_final_exam_date03", property = "examDate"),

        @Result(column = "cwk_college_name03", property = "collegeName")
    })
    ArrayList<Course> selectByTerm(@Param("term") String term, @Param("offset") int offset, @Param("size") int size);

    @Select("select chenwk_course03.cwk_id03, chenwk_course03.cwk_name03, cwk_term03, cwk_examination_method03, cwk_credit03, "
            + "cwk_hours03, cwk_attribute03, cwk_year03, cwk_final_exam_date03,chenwk_college03.cwk_name03 as cwk_college_name03 "
            + "from chenwk_course03, chenwk_college03 "
            + "where chenwk_course03.cwk_college_id03 = chenwk_college03.cwk_id03 "
            + "and cwk_examination_method03 = #{examinationMethod} "
            + "limit #{offset}, #{size}")
    @Results({
        @Result(column = "cwk_id03", property = "id"),
        @Result(column = "cwk_name03", property = "name"),
        @Result(column = "cwk_term03", property = "term"),
        @Result(column = "cwk_examination_method03", property = "examinationMethod"),
        @Result(column = "cwk_credit03", property = "credit"),
        @Result(column = "cwk_hours03", property = "hours"),
        @Result(column = "cwk_attribute03", property = "attribute"),
        @Result(column = "cwk_year03", property = "year"),
        @Result(column = "cwk_final_exam_date03", property = "examDate"),

        @Result(column = "cwk_college_name03", property = "collegeName")
    })
    ArrayList<Course> selectByExaminationMethod(@Param("examinationMethod") String examinationMethod, @Param("offset") int offset, @Param("size") int size);

    @Select("select chenwk_course03.cwk_id03, chenwk_course03.cwk_name03, cwk_term03, cwk_examination_method03, cwk_credit03, "
            + "cwk_hours03, cwk_attribute03, cwk_year03,cwk_final_exam_date03, chenwk_college03.cwk_name03 as cwk_college_name03 "
            + "from chenwk_course03, chenwk_college03 "
            + "where chenwk_course03.cwk_college_id03 = chenwk_college03.cwk_id03 "
            + "and cwk_attribute03 = #{attribute} "
            + "limit #{offset}, #{size}")
    @Results({
        @Result(column = "cwk_id03", property = "id"),
        @Result(column = "cwk_name03", property = "name"),
        @Result(column = "cwk_term03", property = "term"),
        @Result(column = "cwk_examination_method03", property = "examinationMethod"),
        @Result(column = "cwk_credit03", property = "credit"),
        @Result(column = "cwk_hours03", property = "hours"),
        @Result(column = "cwk_attribute03", property = "attribute"),
        @Result(column = "cwk_year03", property = "year"),
        @Result(column = "cwk_final_exam_date03", property = "examDate"),

        @Result(column = "cwk_college_name03", property = "collegeName")
    })
    ArrayList<Course> selectByAttribute(@Param("attribute") String attribute, @Param("offset") int offset, @Param("size") int size);

    @Update("update chenwk_course03 set "
            + "cwk_name03 = #{name}, "
            + "cwk_term03 = #{term}, "
            + "cwk_college_id03 = (select cwk_id03 from chenwk_college03 where cwk_name03 = #{collegeName}), "
            + "cwk_examination_method03 = #{examinationMethod}, "
            + "cwk_credit03 = #{credit}, "
            + "cwk_hours03 = #{hours}, "
            + "cwk_attribute03 = #{attribute}, "
            + "cwk_year03 = #{year} "
            + "cwk_final_exam_date03= #{examDate}"
            + "where cwk_id03 = #{id}")
    void update(@Param("id") int id, @Param("name") String name, @Param("term") String term, @Param("collegeName") String collegeName,
            @Param("examinationMethod") String examinationMethod, @Param("credit") double credit, @Param("hours") int hours,
            @Param("attribute") String attribute, @Param("year") int year);

    @Insert("call insert_course(#{id}, #{name}, #{collegeName}, #{term}, #{examinationMethod}, #{credit}, #{hours}, #{attribute}, #{year})")
    void insert(@Param("id") int id, @Param("name") String name, @Param("collegeName") String collegeName, @Param("term") String term,
            @Param("examinationMethod") String examinationMethod, @Param("credit") double credit, @Param("hours") int hours,
            @Param("attribute") String attribute, @Param("year") int year);

    @Delete("delete from chenwk_course03 where cwk_id03 = #{id}")
    void delete(@Param("id") int id);

    @Select("select * from chenwk_student_course "
            + "where cwk_student_id03 = #{studentId} "
            + "and cwk_term03 = #{term} "
            + "limit #{offset}, #{size}")
    @Results({
        @Result(column = "cwk_student_id03", property = "studentId"),
        @Result(column = "cwk_course_id03", property = "courseId"),
        @Result(column = "cwk_course_name03", property = "courseName"),
        @Result(column = "cwk_term03", property = "term"),
        @Result(column = "cwk_teacher_id03", property = "teacherId"),
        @Result(column = "cwk_teacher_name03", property = "teacherName"),
        @Result(column = "cwk_credit03", property = "credit")
    })
    ArrayList<StudentCourseDto> selectStudentCourseByTerm(@Param("studentId") long studentId, @Param("term") String term, @Param("offset") int offset, @Param("size") int size);

    @Select("select count(*) from chenwk_student_course "
            + "where cwk_student_id03 = #{studentId} "
            + "and cwk_term03 = #{term} ")
    int selectStudentCourseByTermCount(@Param("studentId") long studentId, @Param("term") String term);

    @Select("SELECT cwk_name03, cwk_final_exam_date03 FROM chenwk_course03 WHERE cwk_final_exam_date03 IS NOT NULL")
    @Results({
        @Result(column = "cwk_name03", property = "name"),
        @Result(column = "cwk_final_exam_date03", property = "examDate")
    })
    List<Course> getAllExams();

    @Select("SELECT c.cwk_name03, c.cwk_final_exam_date03 "
            + "FROM chenwk_score03 s "
            + "JOIN chenwk_course03 c ON s.cwk_course_id03 = c.cwk_id03 "
            + "WHERE s.cwk_student_id03 = #{studentId} AND c.cwk_final_exam_date03 IS NOT NULL")
    @Results({
        @Result(column = "cwk_name03", property = "name"),
        @Result(column = "cwk_final_exam_date03", property = "examDate")
    })
    List<Course> getStudentExams(@Param("studentId") long studentId);



    //占位符绑定参数，防止 SQL 注入
    @Select("SELECT c.cwk_name03, c.cwk_final_exam_date03 "
            + "FROM chenwk_course_schedule03 s "
            + "JOIN chenwk_course03 c ON s.cwk_course_id03 = c.cwk_id03 "
            + "WHERE s.cwk_teacher_id03 = #{teacherId} AND c.cwk_final_exam_date03 IS NOT NULL")
    @Results({
        @Result(column = "cwk_name03", property = "name"),
        @Result(column = "cwk_final_exam_date03", property = "examDate")
    })
    List<Course> getTeacherExams(@Param("teacherId") long teacherId);

}
