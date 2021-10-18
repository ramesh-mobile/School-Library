package com.sr.library.data.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

/**
 * Created by ramesh on 16-10-2021
 */
@Parcelize
@Entity
data class StudentDetailsModel(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "sphone")
    var sphone : String,

    @ColumnInfo(name = "sname")
    var sname : String,

    @ColumnInfo(name = "assigned_book")
    var assignedBook : String

):Parcelable{

    @Inject
    constructor() : this("","","")

    fun setValues(sphone: String,sname: String,assignedBook: String){
        this.sphone = sphone
        this.sname = sname
        this.assignedBook = assignedBook
    }


}
