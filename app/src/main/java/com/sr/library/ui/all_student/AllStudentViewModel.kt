package com.sr.library.ui.all_student

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sr.library.AppApplication
import com.sr.library.data.database.LibraryDatabase
import com.sr.library.data.model.entity.StudentDetailsModel
import com.sr.library.utils.CorountinesUtils
import javax.inject.Inject

/**
 * Created by ramesh on 14-10-2021
 */
class AllStudentViewModel: ViewModel() {
    private val TAG = "AllStudentViewModel"

    private var studDetModelMLD = MutableLiveData<List<StudentDetailsModel>>()

    val studDetModelLD get() = studDetModelMLD as LiveData<List<StudentDetailsModel>>

    @Inject
    lateinit var db : LibraryDatabase

    init {
        AppApplication.studentComponent.getDatabaseObject(this)
    }

    fun getAllDetails(){
        CorountinesUtils.main {
            studDetModelMLD.postValue(db.getStudentDetailsDao().getAllStudentDetails())
        }
    }

    fun onDelete(phone: String) {
        CorountinesUtils.main {
            db.getStudentDetailsDao().delete(phone)
            getAllDetails()
        }
    }
}