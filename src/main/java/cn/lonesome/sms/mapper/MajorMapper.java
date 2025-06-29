package cn.lonesome.sms.mapper;

import cn.lonesome.sms.model.entity.Major;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * @author Yale
 * @version 1.0
 */
@Mapper
public interface MajorMapper {
    @Select("select count(*) from chenwk_major03")
    int selectCount();

    @Select("select " +
            "chenwk_major03.cwk_id03 as major_id, " +
            "chenwk_major03.cwk_name03 as major_name, " +
            "chenwk_college03.cwk_name03 as college_name " +
            "from chenwk_major03, chenwk_college03 " +
            "where chenwk_college03.cwk_id03 = chenwk_major03.cwk_college_id03 " +
            "limit #{offset}, #{size}")
    @Results({
            @Result(column = "major_id", property = "id"),
            @Result(column = "major_name", property = "name"),
            @Result(column = "college_name", property = "collegeName")
    })
    ArrayList<Major> selectAll(@Param("offset") int offset, @Param("size") int size);

    @Select("select " +
            "chenwk_major03.cwk_id03 as major_id, " +
            "chenwk_major03.cwk_name03 as major_name, " +
            "chenwk_college03.cwk_name03 as college_name " +
            "from chenwk_major03, chenwk_college03 " +
            "where chenwk_college03.cwk_id03 = chenwk_major03.cwk_college_id03 " +
            "and chenwk_college03.cwk_name03 = #{college_name} " +
            "limit #{offset}, #{size}")
    @Results({
            @Result(column = "major_id", property = "id"),
            @Result(column = "major_name", property = "name"),
            @Result(column = "college_name", property = "collegeName")
    })
    ArrayList<Major> selectAllByCollegeName(@Param("offset") int offset, @Param("size") int size, @Param("college_name") String collegeName);

    @Insert("call insert_major(#{id}, #{name}, #{college_name})")
    int insert(@Param("id") int id, @Param("name") String name, @Param("college_name") String collegeName);

    @Update("update " +
            "chenwk_major03 " +
            "set " +
            "cwk_name03 = #{name}, " +
            "cwk_college_id03 = (select cwk_id03 from chenwk_college03 where cwk_name03 = #{college_name}) " +
            "where " +
            "cwk_id03 = #{id}")
    int update(@Param("id") int id, @Param("name") String name, @Param("college_name") String collegeName);

    @Delete("delete from chenwk_major03 where cwk_id03 = #{id}")
    int delete(@Param("id") int id);

    @Select("select count(*) from chenwk_major03 where cwk_id03 = #{id}")
    int checkId(@Param("id") int id);

    @Select("select count(*) from chenwk_major03 where cwk_name03 = #{name}")
    int checkName(@Param("name") String name);
}
