package com.sr.library.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sr.library.data.model.entity.StudentDetailsModel

/**
 * Created by ramesh on 14-10-2021
 */
@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertStudentDetail(studentDetailsModel: StudentDetailsModel):Long

    @Query("update StudentDetailsModel set assigned_book = :newBook where sphone=:sphone")
    suspend fun updateStudentBook(sphone: String, newBook:String)

    @Query("select * from StudentDetailsModel")
    suspend fun getAllStudentDetails() : List<StudentDetailsModel>

    @Query("delete from StudentDetailsModel where sphone = :phone")
    suspend fun delete(phone: String)

    @Query("delete from StudentDetailsModel")
    suspend fun deleteAll()





}