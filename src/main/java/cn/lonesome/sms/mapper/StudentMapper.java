package cn.lonesome.sms.mapper;

import cn.lonesome.sms.model.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * @author Yale
 * @version 1.0
 */
@Mapper
public interface StudentMapper {
    @Select("SELECT COUNT(*) FROM chenwk_student03 WHERE cwk_hometown03 LIKE CONCAT('%', #{p_hometown}, '%')")
    int getCountByHometown(@Param("p_hometown") String hometown);

    @Insert("call insert_student(#{id}, #{name}, #{gender}, #{birth_year}, #{class_name}, #{hometown}, #{password})")
    void insert(@Param("id") long id, @Param("name") String name,
                @Param("gender") String gender, @Param("birth_year") int birthYear,
                @Param("class_name") String className, @Param("hometown") String hometown, @Param("password") String password);

    @Delete("delete from chenwk_student03 where cwk_id03 = #{id}")
    void delete(@Param("id") long id);

    @Update("update chenwk_student03 " +
            "set cwk_name03 = #{name}, cwk_gender03 = #{gender}, cwk_birth_year03 = #{birth_year}, " +
            "cwk_class_id03 = (select cwk_id03 from chenwk_class03 where cwk_name03 = #{class_name}), " +
            "cwk_hometown03 = #{hometown} " +
            "where cwk_id03 = #{id}")
    void update(@Param("id") long id, @Param("name") String name,
                @Param("gender") String gender, @Param("birth_year") int birthYear,
                @Param("class_name") String className, @Param("hometown") String hometown);

    @Select("select count(*) from chenwk_student03 where cwk_id03 = #{id}")
    int checkId(@Param("id") long id);

    @Select("select " +
            "chenwk_student03.cwk_id03, chenwk_student03.cwk_name03, cwk_gender03, " +
            "cwk_birth_year03, cwk_hometown03, chenwk_class03.cwk_name03 as cwk_class_name03, " +
            "cwk_credits03 " +
            "from chenwk_student03, chenwk_class03 " +
            "where cwk_class_id03 = chenwk_class03.cwk_id03 " +
            "limit #{offset}, #{limit}")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name"),
            @Result(column = "cwk_gender03", property = "gender"),
            @Result(column = "cwk_credits03", property = "credits"),
            @Result(column = "cwk_birth_year03", property = "birthYear"),
            @Result(column = "cwk_hometown03", property = "hometown"),
            @Result(column = "cwk_class_name03", property = "className")
    })
    ArrayList<Student> selectAll(@Param("offset") int offset, @Param("limit") int limit);

    @Select("select " +
            "chenwk_student03.cwk_id03, chenwk_student03.cwk_name03, cwk_gender03, " +
            "cwk_birth_year03, cwk_hometown03, chenwk_class03.cwk_name03 as cwk_class_name03, " +
            "cwk_credits03 " +
            "from chenwk_student03, chenwk_class03 " +
            "where cwk_class_id03 = chenwk_class03.cwk_id03 " +
            "and chenwk_class03.cwk_name03 = #{class_name}")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name"),
            @Result(column = "cwk_gender03", property = "gender"),
            @Result(column = "cwk_credits03", property = "credits"),
            @Result(column = "cwk_birth_year03", property = "birthYear"),
            @Result(column = "cwk_hometown03", property = "hometown"),
            @Result(column = "cwk_class_name03", property = "className")
    })
    ArrayList<Student> selectByClass(@Param("offset") int offset, @Param("limit") int limit, @Param("class_name") String className);

    @Select("select " +
            "chenwk_student03.cwk_id03, chenwk_student03.cwk_name03, cwk_gender03, " +
            "cwk_birth_year03, cwk_hometown03, chenwk_class03.cwk_name03 as cwk_class_name03, " +
            "cwk_credits03 " +
            "from chenwk_student03, chenwk_class03 " +
            "where cwk_class_id03 = chenwk_class03.cwk_id03 " +
            "and chenwk_class03.cwk_major_id03 = " +
            "(select cwk_id03 from chenwk_major03 where cwk_name03 = #{major_name})")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name"),
            @Result(column = "cwk_gender03", property = "gender"),
            @Result(column = "cwk_credits03", property = "credits"),
            @Result(column = "cwk_birth_year03", property = "birthYear"),
            @Result(column = "cwk_hometown03", property = "hometown"),
            @Result(column = "cwk_class_name03", property = "className")
    })
    ArrayList<Student> selectByMajor(@Param("offset") int offset, @Param("limit") int limit, @Param("major_name") String majorName);

    @Select("select " +
            "chenwk_student03.cwk_id03, chenwk_student03.cwk_name03, cwk_gender03, " +
            "cwk_birth_year03, cwk_hometown03, chenwk_class03.cwk_name03 as cwk_class_name03, " +
            "cwk_credits03 " +
            "from chenwk_student03, chenwk_class03 " +
            "where cwk_class_id03 = chenwk_class03.cwk_id03 " +
            "and chenwk_class03.cwk_major_id03 in " +
            "(select cwk_id03 from chenwk_major03 " +
            "where cwk_college_id03 = " +
            "(select cwk_id03 from chenwk_college03 where cwk_name03 = #{college_name}))")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name"),
            @Result(column = "cwk_gender03", property = "gender"),
            @Result(column = "cwk_credits03", property = "credits"),
            @Result(column = "cwk_birth_year03", property = "birthYear"),
            @Result(column = "cwk_hometown03", property = "hometown"),
            @Result(column = "cwk_class_name03", property = "className")
    })
    ArrayList<Student> selectByCollege(@Param("offset") int offset, @Param("limit") int limit, @Param("college_name") String collegeName);

    @Select("select count(*) from chenwk_student03")
    int selectCount();

    @Select("select count(*) from chenwk_student03 " +
            "where cwk_class_id03 = (select cwk_id03 from chenwk_class03 where cwk_name03 = #{class_name})")
    int selectCountByClass(@Param("class_name") String className);

    @Select("select count(*) from chenwk_student03 " +
            "where cwk_class_id03 in " +
            "(select cwk_id03 from chenwk_class03 " +
            "where cwk_major_id03 = " +
            "(select cwk_id03 from chenwk_major03 where cwk_name03 = #{major_name}))")
    int selectCountByMajor(@Param("major_name") String majorName);

    @Select("select count(*) from chenwk_student03 " +
            "where cwk_class_id03 in " +
            "(select cwk_id03 from chenwk_class03 " +
            "where cwk_major_id03 in " +
            "(select cwk_id03 from chenwk_major03 " +
            "where cwk_college_id03 = " +
            "(select cwk_id03 from chenwk_college03 where cwk_name03 = #{college_name})))")
    int selectCountByCollege(@Param("college_name") String collegeName);

    @Select("select count(*) from chenwk_student03 where cwk_id03 = #{id} and cwk_password03 = #{password}")
    int login(@Param("id") long id, @Param("password") String password);

    @Select("select " +
            "chenwk_student03.cwk_id03, chenwk_student03.cwk_name03, cwk_gender03, " +
            "cwk_birth_year03, cwk_hometown03, chenwk_class03.cwk_name03 as cwk_class_name03, " +
            "cwk_credits03 " +
            "from chenwk_student03, chenwk_class03 " +
            "where cwk_class_id03 = chenwk_class03.cwk_id03 " +
            "and chenwk_student03.cwk_id03 = #{id} ")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name"),
            @Result(column = "cwk_gender03", property = "gender"),
            @Result(column = "cwk_credits03", property = "credits"),
            @Result(column = "cwk_birth_year03", property = "birthYear"),
            @Result(column = "cwk_hometown03", property = "hometown"),
            @Result(column = "cwk_class_name03", property = "className")
    })
    Student selectOne(@Param("id") long id);

    @Update("call student_change_password(#{password}, #{id})")
    int changePassword(@Param("password") String password, @Param("id") long id);

    @Select("select distinct cwk_hometown03 from chenwk_student03")
    ArrayList<String> selectHometownList();

    @Select("SELECT " +
            "s.cwk_id03, s.cwk_name03, s.cwk_gender03, s.cwk_birth_year03, s.cwk_hometown03, " +
            "c.cwk_name03 AS cwk_class_name03, s.cwk_credits03 " +
            "FROM chenwk_student03 s, chenwk_class03 c " +
            "WHERE s.cwk_class_id03 = c.cwk_id03 " +
            "AND s.cwk_hometown03 LIKE CONCAT('%', #{hometown}, '%') " +
            "LIMIT #{limit} OFFSET #{offset}")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name"),
            @Result(column = "cwk_gender03", property = "gender"),
            @Result(column = "cwk_credits03", property = "credits"),
            @Result(column = "cwk_birth_year03", property = "birthYear"),
            @Result(column = "cwk_hometown03", property = "hometown"),
            @Result(column = "cwk_class_name03", property = "className")
    })
    ArrayList<Student> selectByHometown(@Param("offset") int offset,
                                        @Param("limit") int limit,
                                        @Param("hometown") String hometown);


    @Select("<script>" +
            "SELECT chenwk_student03.cwk_id03, chenwk_student03.cwk_name03, cwk_gender03, cwk_birth_year03, cwk_hometown03, " +
            "chenwk_class03.cwk_name03 as cwk_class_name03, cwk_credits03 " +
            "FROM chenwk_student03 " +
            "LEFT JOIN chenwk_class03 ON chenwk_student03.cwk_class_id03 = chenwk_class03.cwk_id03 " +
            "WHERE 1=1 " +
            "<if test='name != null and name != \"\"'>AND chenwk_student03.cwk_name03 LIKE CONCAT('%', #{name}, '%') </if>" +
            "<if test='gender != null and gender != \"\"'>AND chenwk_student03.cwk_gender03 LIKE CONCAT('%', #{gender}, '%') </if>" +
            "<if test='studentClass != null and studentClass != \"\"'>AND chenwk_class03.cwk_name03 LIKE CONCAT('%', #{studentClass}, '%') </if>" +
            "<if test='hometown != null and hometown != \"\"'>AND chenwk_student03.cwk_hometown03 LIKE CONCAT('%', #{hometown}, '%') </if>" +
            "ORDER BY chenwk_student03.cwk_id03 " +
            "LIMIT #{offset}, #{limit}" +
            "</script>")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name"),
            @Result(column = "cwk_gender03", property = "gender"),
            @Result(column = "cwk_birth_year03", property = "birthYear"),
            @Result(column = "cwk_hometown03", property = "hometown"),
            @Result(column = "cwk_class_name03", property = "className"),
            @Result(column = "cwk_credits03", property = "credits")
    })
    ArrayList<Student> selectByConditions(@Param("offset") int offset, @Param("limit") int limit,
                                          @Param("name") String name, @Param("gender") String gender,
                                          @Param("studentClass") String studentClass, @Param("hometown") String hometown);

    @Select("<script>" +
            "SELECT COUNT(*) " +
            "FROM chenwk_student03 " +
            "LEFT JOIN chenwk_class03 ON chenwk_student03.cwk_class_id03 = chenwk_class03.cwk_id03 " +
            "WHERE 1=1 " +
            "<if test='name != null and name != \"\"'>AND chenwk_student03.cwk_name03 LIKE CONCAT('%', #{name}, '%') </if>" +
            "<if test='gender != null and gender != \"\"'>AND chenwk_student03.cwk_gender03 = #{gender} </if>" +
            "<if test='studentClass != null and studentClass != \"\"'>AND chenwk_class03.cwk_name03 = #{studentClass} </if>" +
            "<if test='hometown != null and hometown != \"\"'>AND chenwk_student03.cwk_hometown03 = #{hometown} </if>" +
            "</script>")
    int selectCountByConditions(@Param("name") String name, @Param("gender") String gender,
                                @Param("studentClass") String studentClass, @Param("hometown") String hometown);

}
