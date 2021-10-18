package com.sr.library.ui.all_student

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sr.library.data.model.entity.StudentDetailsModel
import com.sr.library.databinding.RowStudentBinding

/**
 * Created by ramesh on 14-10-2021
 */
class AllStudentRowAdapter(val studentDetailsModelList :List<StudentDetailsModel>,val studentRowDataListener:StudentRowDataListener) : RecyclerView.Adapter<AllStudentRowAdapter.MyViewHolder>() {

    interface StudentRowDataListener{
        fun onDeleteClicked(phone: String)
        fun onEditClicked(studentDetailsModel: StudentDetailsModel)
    }

    class MyViewHolder(val binding: RowStudentBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        MyViewHolder(RowStudentBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var studentDetailsModel = studentDetailsModelList.get(position)
        holder.binding.lblName.text = studentDetailsModel.sname
        holder.binding.lblBook.text = studentDetailsModel.assignedBook
        holder.binding.lblMob.text = studentDetailsModel.sphone

        holder.binding.lblEdit.setOnClickListener {
            studentRowDataListener.onEditClicked(studentDetailsModel)
        }

        holder.binding.lblDelete.setOnClickListener {
            studentRowDataListener.onDeleteClicked(studentDetailsModel.sphone)
        }
    }

    override fun getItemCount() = studentDetailsModelList.size
}