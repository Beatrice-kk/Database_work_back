package cn.lonesome.sms.mapper;

import cn.lonesome.sms.model.dto.AllYearScoreDto;
import cn.lonesome.sms.model.entity.Score;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * @author Yale
 * @version 1.0
 */
@Mapper
public interface ScoreMapper {
    @Select("select count(*) from chenwk_teacher_course_score where cwk_term03=#{term}")
    int selectAllCount(String term);

    @Select("select count(*) from chenwk_teacher_course_score where cwk_course_id03=#{courseId} and cwk_term03=#{term}")
    int selectCountByCourseId(String courseId, String term);

    @Select("select count(*) from chenwk_teacher_course_score where cwk_student_id03=#{studentId} and cwk_term03=#{term}")
    int selectCountByStudentId(long studentId, String term);

    @Select("select count(*) from chenwk_teacher_course_score where cwk_teacher_id03=#{teacherId} and cwk_term03=#{term}")
    int selectCountByTeacherId(long teacherId, String term);

    @Select("select * from chenwk_teacher_course_score where cwk_term03=#{term} limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_course_id03", property = "courseId"),
            @Result(column = "cwk_course_name03", property = "courseName"),
            @Result(column = "cwk_class_name03", property = "className"),
            @Result(column = "cwk_teacher_name03", property = "teacherName"),
            @Result(column = "cwk_teacher_id03", property = "teacherId"),
            @Result(column = "cwk_score03", property = "score"),
            @Result(column = "cwk_term03", property = "term"),
            @Result(column = "cwk_student_id03", property = "studentId"),
            @Result(column = "cwk_student_name03", property = "studentName"),
            @Result(column = "cwk_retake03", property = "retake")
    })
    ArrayList<Score> selectAll(String term, int offset, int limit);

    @Select("select * from chenwk_teacher_course_score where cwk_course_id03=#{courseId} and cwk_term03=#{term} limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_course_id03", property = "courseId"),
            @Result(column = "cwk_course_name03", property = "courseName"),
            @Result(column = "cwk_class_name03", property = "className"),
            @Result(column = "cwk_teacher_name03", property = "teacherName"),
            @Result(column = "cwk_teacher_id03", property = "teacherId"),
            @Result(column = "cwk_score03", property = "score"),
            @Result(column = "cwk_term03", property = "term"),
            @Result(column = "cwk_student_id03", property = "studentId"),
            @Result(column = "cwk_student_name03", property = "studentName"),
            @Result(column = "cwk_retake03", property = "retake")
    })
    ArrayList<Score> selectByCourseId(String courseId, String term, int offset, int limit);

    @Select("select * from chenwk_teacher_course_score where cwk_student_id03=#{studentId} and cwk_term03=#{term} limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_course_id03", property = "courseId"),
            @Result(column = "cwk_course_name03", property = "courseName"),
            @Result(column = "cwk_class_name03", property = "className"),
            @Result(column = "cwk_teacher_name03", property = "teacherName"),
            @Result(column = "cwk_teacher_id03", property = "teacherId"),
            @Result(column = "cwk_score03", property = "score"),
            @Result(column = "cwk_term03", property = "term"),
            @Result(column = "cwk_student_id03", property = "studentId"),
            @Result(column = "cwk_student_name03", property = "studentName"),
            @Result(column = "cwk_retake03", property = "retake")
    })
    ArrayList<Score> selectByStudentId(long studentId, String term, int offset, int limit);

    @Select("select * " +
            "from chenwk_teacher_course_score " +
            "where cwk_teacher_id03=#{teacherId} and cwk_course_id03=#{courseId} " +
            "and cwk_class_name03=#{className} " +
            "limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_course_id03", property = "courseId"),
            @Result(column = "cwk_course_name03", property = "courseName"),
            @Result(column = "cwk_class_name03", property = "className"),
            @Result(column = "cwk_teacher_name03", property = "teacherName"),
            @Result(column = "cwk_teacher_id03", property = "teacherId"),
            @Result(column = "cwk_score03", property = "score"),
            @Result(column = "cwk_term03", property = "term"),
            @Result(column = "cwk_student_id03", property = "studentId"),
            @Result(column = "cwk_student_name03", property = "studentName"),
            @Result(column = "cwk_retake03", property = "retake")
    })
    ArrayList<Score> selectByTeacherId(
            @Param("teacherId") long teacherId,
            @Param("className") String className,
            @Param("courseId") String courseId,
            @Param("offset") int offset,
            @Param("limit") int limit);

    @Select("select count(*) " +
            "from chenwk_teacher_course_score " +
            "where cwk_teacher_id03=#{teacherId} and cwk_course_id03=#{courseId} " +
            "and cwk_class_name03=#{className}")
    int selectCountByTeacherId(long teacherId, String className, String courseId);

    @Select("select cwk_student_id03, cwk_student_name03, cwk_year03, cwk_gpa03, cwk_credit03 " +
            "from chenwk_student_score_year " +
            "where cwk_year03=#{year}")
    @Results({
            @Result(column = "cwk_student_id03", property = "studentId"),
            @Result(column = "cwk_student_name03", property = "studentName"),
            @Result(column = "cwk_year03", property = "year"),
            @Result(column = "cwk_gpa03", property = "gpa"),
            @Result(column = "cwk_credit03", property = "credit")
    })
    ArrayList<AllYearScoreDto> selectAllYearScore(int year);

    @Select("select * from chenwk_student_score_year " +
            "where cwk_class_name03=#{className} " +
            "and cwk_year03=#{year}")
    @Results({
            @Result(column = "cwk_student_id03", property = "studentId"),
            @Result(column = "cwk_student_name03", property = "studentName"),
            @Result(column = "cwk_year03", property = "year"),
            @Result(column = "cwk_gpa03", property = "gpa"),
            @Result(column = "cwk_credit03", property = "credit")
    })
    ArrayList<AllYearScoreDto> selectAllYearScoreByClass(int year, String className);

    @Select("select * from chenwk_student_score_year " +
            "where cwk_student_id03=#{studentId}")
    @Results({
            @Result(column = "cwk_student_id03", property = "studentId"),
            @Result(column = "cwk_student_name03", property = "studentName"),
            @Result(column = "cwk_year03", property = "year"),
            @Result(column = "cwk_gpa03", property = "gpa"),
            @Result(column = "cwk_credit03", property = "credit")
    })
    ArrayList<AllYearScoreDto> selectAllYearScoreByStudent(long studentId);

    @Select("select * from chenwk_student_score_year " +
            "where cwk_major_name03=#{majorName} " +
            "and cwk_year03=#{year}")
    @Results({
            @Result(column = "cwk_student_id03", property = "studentId"),
            @Result(column = "cwk_student_name03", property = "studentName"),
            @Result(column = "cwk_year03", property = "year"),
            @Result(column = "cwk_gpa03", property = "gpa"),
            @Result(column = "cwk_credit03", property = "credit")
    })
    ArrayList<AllYearScoreDto> selectAllYearScoreByMajor(int year, String majorName);

    @Select("select * from chenwk_teacher_course_ave_score limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_teacher_id03", property = "teacherId"),
            @Result(column = "cwk_teacher_name03", property = "teacherName"),
            @Result(column = "cwk_course_id03", property = "courseId"),
            @Result(column = "cwk_course_name03", property = "courseName"),
            @Result(column = "cwk_term03", property = "term"),
            @Result(column = "cwk_avg_score03", property = "score"),
    })
    ArrayList<Score> selectAllAvgScore(int offset, int limit);

    @Select("select count(*) from chenwk_teacher_course_ave_score")
    int selectCountAllAvgScore();

    @Select("select * from chenwk_teacher_course_ave_score " +
            "where cwk_teacher_id03=#{teacherId} " +
            "limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_teacher_id03", property = "teacherId"),
            @Result(column = "cwk_teacher_name03", property = "teacherName"),
            @Result(column = "cwk_course_id03", property = "courseId"),
            @Result(column = "cwk_course_name03", property = "courseName"),
            @Result(column = "cwk_term03", property = "term"),
            @Result(column = "cwk_avg_score03", property = "score"),
    })
    ArrayList<Score> selectAvgScoreByTeacherId(int offset, int limit, long teacherId);

    @Select("select count(*) from chenwk_teacher_course_ave_score " +
            "where cwk_teacher_id03=#{teacherId}")
    int selectCountAvgScoreByTeacherId(long teacherId);

    @Select("select * from chenwk_teacher_course_ave_score " +
            "where cwk_course_id03=#{courseId} " +
            "limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_teacher_id03", property = "teacherId"),
            @Result(column = "cwk_teacher_name03", property = "teacherName"),
            @Result(column = "cwk_course_id03", property = "courseId"),
            @Result(column = "cwk_course_name03", property = "courseName"),
            @Result(column = "cwk_term03", property = "term"),
            @Result(column = "cwk_avg_score03", property = "score"),
    })
    ArrayList<Score> selectAvgScoreByCourseId(int offset, int limit, String courseId);

    @Select("select count(*) from chenwk_teacher_course_ave_score " +
            "where cwk_course_id03=#{courseId}")
    int selectCountAvgScoreByCourseId(String courseId);

    @Insert("call update_score(#{studentId}, #{courseId}, #{teacherId}, #{score}, #{term})")
    int insertScore(long studentId, String courseId, long teacherId, String term, double score);
}
