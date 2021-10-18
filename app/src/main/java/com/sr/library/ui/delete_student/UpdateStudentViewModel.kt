package com.sr.library.ui.delete_student

import androidx.lifecycle.ViewModel
import com.sr.library.AppApplication
import com.sr.library.data.database.LibraryDatabase
import com.sr.library.data.model.entity.StudentDetailsModel
import com.sr.library.ui.add_student.DatabaseResultCallback
import com.sr.library.utils.CorountinesUtils
import javax.inject.Inject

/**
 * Created by ramesh on 14-10-2021
 */
class UpdateStudentViewModel : ViewModel() {
    private val TAG = "UpdateStudentViewModel"

    @Inject
    lateinit var db : LibraryDatabase

    var databaseResultCallback : DatabaseResultCallback? = null

    init {
        AppApplication.studentComponent.getDatabaseObject(this)
    }

    fun updateStudentDetails(studentDetailsModel: StudentDetailsModel) {
        CorountinesUtils.main {

            try{
                db.getStudentDetailsDao().updateStudentBook(studentDetailsModel.sphone,studentDetailsModel.assignedBook)
                databaseResultCallback?.isOperationSuccessful(true,"Data updated successfully")
            }
            catch (ex: Exception){
                databaseResultCallback?.isOperationSuccessful(false,"Failed to update")
            }

        }
    }
}