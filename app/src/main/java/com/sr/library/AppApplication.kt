package com.sr.library

import android.app.Application
import com.sr.library.data.di.component.DaggerStudentComponent
import com.sr.library.data.di.component.StudentComponent
import com.sr.library.data.di.module.DbModule

/**
 * Created by ramesh on 13-10-2021
 */
class AppApplication: Application() {

    companion object{
        lateinit var instance : Application
        lateinit var studentComponent: StudentComponent
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        studentComponent = initDagger()
    }

    private fun initDagger() = DaggerStudentComponent.builder()
        .dbModule(DbModule(this))
        .build()
}