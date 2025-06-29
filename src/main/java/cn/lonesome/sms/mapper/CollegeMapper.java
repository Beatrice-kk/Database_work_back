package cn.lonesome.sms.mapper;

import cn.lonesome.sms.model.entity.College;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * @author Yale
 * @version 1.0
 */
@Mapper
public interface CollegeMapper {
    @Select("select count(*) from chenwk_college03")
    int selectCount();

    @Select("select * from chenwk_college03 limit #{offset}, #{size}")
    @Results({
            @Result(column = "cwk_id03", property = "id"),
            @Result(column = "cwk_name03", property = "name")
    })
    ArrayList<College> selectAll(@Param("offset") int offset, @Param("size") int size);

    @Select("select count(*) from chenwk_college03 where cwk_id03 = #{id}")
    int checkId(@Param("id") int id);

    @Select("select count(*) from chenwk_college03 where cwk_name03 = #{name}")
    int checkName(@Param("name") String name);

    @Insert("call insert_college(#{id}, #{name})")
    int insert(@Param("id") int id, @Param("name") String name);

    @Update("update chenwk_college03 set cwk_name03 = #{name} where cwk_id03 = #{id}")
    int update(@Param("id") int id, @Param("name") String name);

    @Delete("delete from chenwk_college03 where cwk_id03 = #{id}")
    int delete(@Param("id") int id);
}
