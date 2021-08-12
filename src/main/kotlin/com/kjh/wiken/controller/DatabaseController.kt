package com.kjh.wiken.controller

import com.kjh.wiken.service.DatabaseService
import com.kjh.wiken.vo.Database
import com.kjh.wiken.vo.Database2
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class DatabaseController(private val databaseService: DatabaseService) {




//   department 테이블에 대분류(광역시, 도) 및 시군구를 가진 데이터들을 정제해서
//   대분류를 id로 하고 시군구 이름을 가진 테이블을 생성하는 프로그램
    @RequestMapping("/getDepartments")
    @ResponseBody
    fun getDepartments(): List<Database>{
        var dbList: List<Database> = arrayListOf()

//        237개의 정제되지 않은 데이터
        dbList = databaseService.getDepartments()



        for(department in dbList){
            val cityName = department.cityName
            val departmentName = department.departmentName

            databaseService.insertDepartment(cityName, departmentName)

        }


        return dbList

    }
}