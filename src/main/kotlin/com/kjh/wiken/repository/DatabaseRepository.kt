package com.kjh.wiken.repository

import com.kjh.wiken.vo.Database
import com.kjh.wiken.vo.Database2
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface DatabaseRepository {

    @Select(
        """
        SELECT DISTINCT(departmentName), cityName
        FROM department
        WHERE departmentName != '시군구'
    """
    )
    fun getDepartments(): List<Database>

    @Insert(
    """
    INSERT INTO totalDepartment
    SET cityId = (
    SELECT C.cityId
    FROM city C
    WHERE C.cityName = #{cityName}
    )
    , departmentName = #{departmentName}
    """
    )
    fun insertDepartment(
        @Param("cityName") cityName: String,
        @Param("departmentName") departmentName: String
    )

}