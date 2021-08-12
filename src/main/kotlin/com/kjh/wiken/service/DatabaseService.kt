package com.kjh.wiken.service

import com.kjh.wiken.repository.DatabaseRepository
import com.kjh.wiken.vo.Database
import com.kjh.wiken.vo.Database2
import org.springframework.stereotype.Service


@Service
class DatabaseService(private val databaseRepository: DatabaseRepository) {
    fun getDepartments(): List<Database> {
        return databaseRepository.getDepartments()
    }



    fun insertDepartment(cityName: String, departmentName: String) {
        databaseRepository.insertDepartment(cityName, departmentName)
    }
}