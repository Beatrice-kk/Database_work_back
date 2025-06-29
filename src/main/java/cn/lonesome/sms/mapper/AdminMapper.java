package cn.lonesome.sms.mapper;

import cn.lonesome.sms.model.entity.Admin;
import cn.lonesome.sms.model.entity.SchoolStatistics;

import org.apache.ibatis.annotations.*;

/**
 * @author Yale
 * @version 1.0
 */
@Mapper
public interface AdminMapper {
    @Select("select * from chenwk_admin03 where cwk_id03=#{username} and cwk_password03=#{password}")
    Admin login(Integer username, String password);

    @Select("select * from chenwk_admin03 where cwk_id03=#{username}")
    Admin selectByUsername(Integer username);

    @Select("select count(*) from chenwk_admin03 where cwk_id03=#{username}")
    int checkUsername(Integer username);

    @Update("call admin_change_password(#{password}, #{username})")
    void changePassword(Integer username, String password);

    @Insert("call insert_admin(#{id}, #{password}, #{role})")
    void insert(Integer id, String password, String role);

    @Update("update chenwk_admin03 set cwk_role03=#{role} where cwk_id03=#{username}")
    void updateRole(Integer username, String role);

    @Delete("delete from chenwk_admin03 where cwk_id03=#{username}")
    void delete(Integer username);


    @Select("SELECT " +
            "  (SELECT COUNT(*) FROM chenwk_student03) AS student_count, " +
            "  (SELECT COUNT(*) FROM chenwk_teacher03) AS teacher_count, " +
            "  (SELECT COUNT(*) FROM chenwk_course03) AS course_count, " +
            "  (SELECT COUNT(*) FROM chenwk_score03) AS enrollment_count")
    @Results({
            @Result(column = "student_count", property = "studentCount"),
            @Result(column = "teacher_count", property = "teacherCount"),
            @Result(column = "course_count", property = "courseCount"),
            @Result(column = "enrollment_count", property = "enrollmentCount")
    })
    SchoolStatistics getSchoolStatistics();

}
