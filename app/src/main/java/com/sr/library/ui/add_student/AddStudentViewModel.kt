package com.sr.library.ui.add_student

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.ViewModel
import com.sr.library.AppApplication
import com.sr.library.data.database.LibraryDatabase
import com.sr.library.data.model.entity.StudentDetailsModel
import com.sr.library.utils.CorountinesUtils
import javax.inject.Inject

/**
 * Created by ramesh on 14-10-2021
 */
class AddStudentViewModel : ViewModel() {
    private val TAG = "UpdateStudentViewModel"

    var databaseResultCallback : DatabaseResultCallback? = null

    @Inject
    lateinit var db : LibraryDatabase

    init {
        AppApplication.studentComponent.getDatabaseObject(this)
    }

    fun saveStudentDetails(studentDetailsModel: StudentDetailsModel) {
        CorountinesUtils.main {
            try {
                db.getStudentDetailsDao().insertStudentDetail(studentDetailsModel)
                databaseResultCallback?.isOperationSuccessful(true,"Data saved successfully")
            }catch (ce : SQLiteConstraintException){
                databaseResultCallback?.isOperationSuccessful(false,"Mobile no already registred")
            }
        }

    }


}