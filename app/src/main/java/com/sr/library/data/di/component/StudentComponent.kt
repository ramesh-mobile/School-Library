package com.sr.library.data.di.component

import com.sr.library.data.di.module.DbModule
import com.sr.library.data.model.entity.StudentDetailsModel
import com.sr.library.ui.add_student.AddStudentViewModel
import com.sr.library.ui.all_student.AllStudentViewModel
import com.sr.library.ui.delete_student.UpdateStudentViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * Created by ramesh on 17-10-2021
 */
@Singleton
@Component(modules = [DbModule::class])
interface StudentComponent {

    fun getStudentDetails() : StudentDetailsModel
    fun getDatabaseObject(addStudentViewModel: AddStudentViewModel)
    fun getDatabaseObject(addStudentViewModel: UpdateStudentViewModel)
    fun getDatabaseObject(addStudentViewModel: AllStudentViewModel)
}