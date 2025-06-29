package cn.lonesome.sms.mapper;

import cn.lonesome.sms.model.entity.Teacher;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yale
 * @version 1.0
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    @Select("select count(*) from chenwk_teacher03")
    int selectCount();

    @Insert("call insert_teacher(#{id}, #{name}, #{gender}, #{birth_year}, #{college_name}, #{phone}, #{title}, #{password})")
    int insert(@Param("id") long id, @Param("name") String name, @Param("gender") String gender,
               @Param("birth_year") int birthYear, @Param("college_name") String collegeName,
               @Param("phone") String phone, @Param("title") String title, @Param("password") String password);

    @Select("select count(*) from chenwk_teacher03 where cwk_id03=#{id}")
    int checkId(@Param("id") long id);

    @Update("update chenwk_teacher03 " +
            "set cwk_name03=#{name}, cwk_gender03=#{gender}, cwk_birth_year03=#{birth_year}, " +
            "cwk_college_id03=(select cwk_id03 from chenwk_college03 where cwk_name03=#{college_name}), " +
            "cwk_phone03=#{phone}, cwk_title03=#{title} " +
            "where cwk_id03=#{id}")
    int update(@Param("id") long id, @Param("name") String name, @Param("gender") String gender,
               @Param("birth_year") int birthYear, @Param("college_name") String collegeName,
               @Param("phone") String phone, @Param("title") String title);

    @Delete("delete from chenwk_teacher03 where cwk_id03=#{id}")
    int delete(@Param("id") long id);

    @Select("select chenwk_teacher03.cwk_id03, chenwk_teacher03.cwk_name03, cwk_gender03, cwk_birth_year03, cwk_phone03, cwk_title03, " +
            "chenwk_college03.cwk_name03 as cwk_college_name03 " +
            "from chenwk_teacher03, chenwk_college03 " +
            "where chenwk_teacher03.cwk_college_id03 = chenwk_college03.cwk_id03 " +
            "order by chenwk_teacher03.cwk_id03 " +
            "limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name"),
            @Result(column = "cwk_gender03", property = "gender"),
            @Result(column = "cwk_birth_year03", property = "birthYear"),
            @Result(column = "cwk_phone03", property = "phone"),
            @Result(column = "cwk_title03", property = "jobTitle"),
            @Result(column = "cwk_college_name03", property = "college")
    })
    ArrayList<Teacher> selectAll(@Param("offset") int offset, @Param("limit") int limit);

    @Select("select chenwk_teacher03.cwk_id03, chenwk_teacher03.cwk_name03, cwk_gender03, cwk_birth_year03, cwk_phone03, cwk_title03, " +
            "chenwk_college03.cwk_name03 as cwk_college_name03 " +
            "from chenwk_teacher03, chenwk_college03 " +
            "where chenwk_teacher03.cwk_college_id03 = chenwk_college03.cwk_id03 " +
            "and chenwk_college03.cwk_name03=#{college_name} " +
            "order by chenwk_teacher03.cwk_id03 " +
            "limit #{offset}, #{limit} ")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name"),
            @Result(column = "cwk_gender03", property = "gender"),
            @Result(column = "cwk_birth_year03", property = "birthYear"),
            @Result(column = "cwk_college_name03", property = "college"),
            @Result(column = "cwk_phone03", property = "phone"),
            @Result(column = "cwk_title03", property = "jobTitle")
    })
    ArrayList<Teacher> selectByCollegeName(@Param("offset") int offset, @Param("limit") int limit, @Param("college_name") String collegeName);

    @Update("call teacher_change_password(#{password}, #{id})")
    int changePassword(@Param("password") String password, @Param("id") long id);

    @Select("select count(*) from chenwk_teacher03 where cwk_id03=#{id} and cwk_password03=#{password}")
    int login(@Param("id") long id, @Param("password") String password);

    @Select("select chenwk_teacher03.cwk_id03, chenwk_teacher03.cwk_name03, cwk_gender03, cwk_birth_year03, cwk_phone03, cwk_title03, " +
            "chenwk_college03.cwk_name03 as cwk_college_name03 " +
            "from chenwk_teacher03, chenwk_college03 " +
            "where chenwk_teacher03.cwk_college_id03 = chenwk_college03.cwk_id03 " +
            "and chenwk_teacher03.cwk_id03=#{id} ")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name"),
            @Result(column = "cwk_gender03", property = "gender"),
            @Result(column = "cwk_birth_year03", property = "birthYear"),
            @Result(column = "cwk_college_name03", property = "college"),
            @Result(column = "cwk_phone03", property = "phone"),
            @Result(column = "cwk_title03", property = "jobTitle")
    })
    Teacher selectOne(@Param("id") long id);

    Page<Teacher> selectPage(Page<Teacher> teacherPage, QueryWrapper<Teacher> queryWrapper);


    @Select("<script>" +
            "SELECT chenwk_teacher03.cwk_id03, chenwk_teacher03.cwk_name03, cwk_gender03, cwk_birth_year03, cwk_phone03, cwk_title03, " +
            "chenwk_college03.cwk_name03 as cwk_college_name03 " +
            "FROM chenwk_teacher03 " +
            "LEFT JOIN chenwk_college03 ON chenwk_teacher03.cwk_college_id03 = chenwk_college03.cwk_id03 " +
            "WHERE 1=1 " +
            "<if test='name != null and name != \"\"'>AND chenwk_teacher03.cwk_name03 LIKE CONCAT('%', #{name}, '%') </if>" +
            "<if test='gender != null and gender != \"\"'>AND chenwk_teacher03.cwk_gender03 LIKE CONCAT('%', #{gender}, '%') </if>" +
            "<if test='college != null and college != \"\"'>AND chenwk_college03.cwk_name03 LIKE CONCAT('%', #{college}, '%') </if>" +
            "<if test='jobTitle != null and jobTitle != \"\"'>AND chenwk_teacher03.cwk_title03 LIKE CONCAT('%', #{jobTitle}, '%') </if>" +
            "ORDER BY chenwk_teacher03.cwk_id03 " +
            "LIMIT #{offset}, #{limit}" +
            "</script>")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name"),
            @Result(column = "cwk_gender03", property = "gender"),
            @Result(column = "cwk_birth_year03", property = "birthYear"),
            @Result(column = "cwk_college_name03", property = "college"),
            @Result(column = "cwk_phone03", property = "phone"),
            @Result(column = "cwk_title03", property = "jobTitle")
    })
    ArrayList<Teacher> selectByConditions(@Param("offset") int offset, @Param("limit") int limit,
                                          @Param("name") String name, @Param("gender") String gender,
                                          @Param("college") String college, @Param("jobTitle") String jobTitle);

    @Select("<script>" +
            "SELECT COUNT(*) " +
            "FROM chenwk_teacher03 " +
            "LEFT JOIN chenwk_college03 ON chenwk_teacher03.cwk_college_id03 = chenwk_college03.cwk_id03 " +
            "WHERE 1=1 " +
            "<if test='name != null and name != \"\"'>AND chenwk_teacher03.cwk_name03 LIKE CONCAT('%', #{name}, '%') </if>" +
            "<if test='gender != null and gender != \"\"'>AND chenwk_teacher03.cwk_gender03 = #{gender} </if>" +
            "<if test='college != null and college != \"\"'>AND chenwk_college03.cwk_name03 = #{college} </if>" +
            "<if test='jobTitle != null and jobTitle != \"\"'>AND chenwk_teacher03.cwk_title03 = #{jobTitle} </if>" +
            "</script>")
    int selectCountByConditions(@Param("name") String name, @Param("gender") String gender,
                                @Param("college") String college, @Param("jobTitle") String jobTitle);

}
