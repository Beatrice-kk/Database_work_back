package cn.lonesome.sms.mapper;

import cn.lonesome.sms.model.entity.Class;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * @author Yale
 * @version 1.0
 */
@Mapper
public interface ClassMapper {
    @Select("select count(*) from chenwk_class03")
    int selectCount();

    @Select("select count(*) from chenwk_class03 " +
            "where cwk_major_id03 = " +
            "(select cwk_id03 from chenwk_major03 where cwk_name03 = #{major_name})")
    int selectCountByMajor(@Param("major_name") String majorName);

    @Select("select count(*) from chenwk_class03 " +
            "where cwk_major_id03 in " +
            "(select chenwk_major03.cwk_id03 " +
            "from chenwk_major03, chenwk_college03 " +
            "where cwk_college_id03 = chenwk_college03.cwk_id03 " +
            "and chenwk_college03.cwk_name03 = #{college_name})")
    int selectCountByCollege(@Param("college_name") String collegeName);

    @Select("select " +
            "chenwk_class03.cwk_id03, " +
            "chenwk_class03.cwk_name03, " +
            "cwk_count_of_students03, " +
            "chenwk_major03.cwk_name03 as cwk_major_name03, " +
            "chenwk_teacher03.cwk_name03 as cwk_teacher_name03 " +
            "from " +
            "chenwk_class03, chenwk_major03, chenwk_teacher03 " +
            "where chenwk_class03.cwk_major_id03 = chenwk_major03.cwk_id03 " +
            "and chenwk_class03.cwk_head_teacher_id03 = chenwk_teacher03.cwk_id03 " +
            "limit #{offset}, #{size}")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name"),
            @Result(column = "cwk_count_of_students03", property = "count"),
            @Result(column = "cwk_major_name03", property = "majorName"),
            @Result(column = "cwk_teacher_name03", property = "teacherName")
    })
    ArrayList<Class> selectAll(@Param("offset") int offset, @Param("size") int size);

    @Select("select " +
            "chenwk_class03.cwk_id03, " +
            "chenwk_class03.cwk_name03, " +
            "cwk_count_of_students03, " +
            "chenwk_major03.cwk_name03 as cwk_major_name03, " +
            "chenwk_teacher03.cwk_name03 as cwk_teacher_name03 " +
            "from " +
            "chenwk_class03, chenwk_major03, chenwk_teacher03 " +
            "where chenwk_class03.cwk_major_id03 = chenwk_major03.cwk_id03 " +
            "and chenwk_class03.cwk_head_teacher_id03 = chenwk_teacher03.cwk_id03 " +
            "and chenwk_major03.cwk_college_id03 = " +
            "(select cwk_id03 from chenwk_college03 " +
            "where cwk_name03 = #{college_name}) " +
            "limit #{offset}, #{size}")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name"),
            @Result(column = "cwk_count_of_students03", property = "count"),
            @Result(column = "cwk_major_name03", property = "majorName"),
            @Result(column = "cwk_teacher_name03", property = "teacherName")
    })
    ArrayList<Class> selectByCollege(@Param("offset") int offset, @Param("size") int size, @Param("college_name") String college);

    @Select("select " +
            "chenwk_class03.cwk_id03, " +
            "chenwk_class03.cwk_name03, " +
            "cwk_count_of_students03, " +
            "chenwk_major03.cwk_name03 as cwk_major_name03, " +
            "chenwk_teacher03.cwk_name03 as cwk_teacher_name03 " +
            "from " +
            "chenwk_class03, chenwk_major03, chenwk_teacher03 " +
            "where chenwk_class03.cwk_major_id03 = chenwk_major03.cwk_id03 " +
            "and chenwk_class03.cwk_head_teacher_id03 = chenwk_teacher03.cwk_id03 " +
            "and chenwk_major03.cwk_name03 = #{major_name} " +
            "limit #{offset}, #{size}")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name"),
            @Result(column = "cwk_count_of_students03", property = "count"),
            @Result(column = "cwk_major_name03", property = "majorName"),
            @Result(column = "cwk_teacher_name03", property = "teacherName")
    })
    ArrayList<Class> selectByMajor(@Param("offset") int offset, @Param("size") int size, @Param("major_name") String major);

    @Update("update " +
            "chenwk_class03 " +
            "set " +
            "cwk_name03 = #{name}, " +
            "cwk_major_id03 = (select cwk_id03 from chenwk_major03 where cwk_name03 = #{major_name}), " +
            "cwk_head_teacher_id03 = #{teacher_id} " +
            "where " +
            "cwk_id03 = #{id}")
    int update(@Param("id") int id, @Param("name") String name, @Param("major_name") String majorName, @Param("teacher_id") long teacherId);

    @Insert("call insert_class(#{id}, #{name}, #{major_name}, #{teacher_id})")
    int insert(@Param("id") int id, @Param("name") String name, @Param("major_name") String majorName, @Param("teacher_id") long teacherId);

    @Delete("delete from chenwk_class03 where cwk_id03 = #{id}")
    int delete(@Param("id") int id);

    @Select("select count(*) from chenwk_class03 where cwk_id03 = #{id}")
    int checkId(@Param("id") int id);

    @Select("select count(*) from chenwk_class03 where cwk_name03 = #{name}")
    int checkName(@Param("name") String name);
}
