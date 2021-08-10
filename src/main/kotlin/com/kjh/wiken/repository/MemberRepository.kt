package com.kjh.wiken.repository

import com.kjh.wiken.vo.Member
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface MemberRepository {
    @Select(
        """
        SELECT *
        FROM `member` AS M
        WHERE M.loginId = #{loginId}
    """
    )
    fun getMemberByLoginId(@Param("loginId") loginId: String): Member?

    @Insert(
        """
        INSERT INTO `member`
        SET regDate = NOW(),
        updateDate = NOW(),
        loginId = #{loginId},
        loginPw = #{loginPw},
        `name` = #{loginPw},
        nickname = #{nickname},
        cellphoneNo = #{cellphoneNo},
        email = #{email}
    """
    )
    fun join(
        @Param("loginId") loginId: String,
        @Param("loginPw") loginPw: String,
        @Param("name") name: String,
        @Param("nickname") nickname: String,
        @Param("cellphoneNo") cellphoneNo: String,
        @Param("email") email: String
    )

    @Select(
        """
        SELECT LAST_INSERT_ID()
    """
    )
    fun getLastInsertId(): Int

    @Select(
        """
        SELECT *
        FROM `member` AS M
        WHERE M.id = #{id}
    """
    )
    fun getMemberById(@Param("id") id: Int): Member?

}
