package com.sr.library.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sr.library.data.dao.StudentDao
import com.sr.library.data.model.entity.StudentDetailsModel

/**
 * Created by ramesh on 17-10-2021
 */
@Database(entities = [StudentDetailsModel::class], version = 1)
abstract class LibraryDatabase : RoomDatabase(){

    abstract fun getStudentDetailsDao() : StudentDao
}