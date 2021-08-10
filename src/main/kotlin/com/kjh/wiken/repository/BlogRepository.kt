package com.kjh.wiken.repository

import com.kjh.wiken.vo.Ken
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface BlogRepository {
    @Select(
        """
    <script>
    SELECT K.*
    FROM ken AS K
    WHERE 1
    AND id IN
    <foreach collection="kenIds" item="kenId" index="index" separator="," open="(" close=")">
        #{kenId}
    </foreach>
    ORDER BY K.id DESC
    </script>
    """
    )
    fun getArticlesByKenIds(@Param("kenIds") kenIds: List<Int>): List<Ken>
}
