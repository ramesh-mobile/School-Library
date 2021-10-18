package com.sr.library.ui.delete_student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sr.library.AppApplication
import com.sr.library.data.model.entity.StudentDetailsModel
import com.sr.library.databinding.FragmentUpdateStudentBinding
import com.sr.library.ui.add_student.DatabaseResultCallback
import com.sr.library.utils.ParamUtils
import com.sr.library.utils.ViewUtil.hide
import com.sr.library.utils.ViewUtil.print
import com.sr.library.utils.ViewUtil.show
import com.sr.library.utils.ViewUtil.snack


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UpdateStudentFragment : Fragment(),DatabaseResultCallback {

    private val TAG = "UpdateStudentFragment"

    private var viewbinding: FragmentUpdateStudentBinding? = null
    lateinit var updateStudentViewModel : UpdateStudentViewModel
    lateinit var progressBar : ProgressBar
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = viewbinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewbinding = FragmentUpdateStudentBinding.inflate(inflater, container, false)
        updateStudentViewModel = ViewModelProvider(this).get(UpdateStudentViewModel::class.java)
        return binding.root

    }
    var studentDetailsModel : StudentDetailsModel? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateStudentViewModel.databaseResultCallback = this

        var bookSpinner = binding.spinnerBook
        progressBar = binding.progressBar

        if(arguments!=null){
            studentDetailsModel = arguments?.getParcelable(ParamUtils.STUDENT_DATA)
            binding.txtName.setText(studentDetailsModel?.sname)
            binding.txtPhone.setText(studentDetailsModel?.sphone)

            val adapter = bookSpinner.adapter as ArrayAdapter<String>
            var pos = adapter.getPosition(studentDetailsModel?.assignedBook)
            bookSpinner.setSelection(pos)
        }
        else{
            print(TAG, "onViewCreated arugment is null")
        }

        binding.btnUpdate.setOnClickListener {
            var txtPhone = binding.txtPhone.text.toString()
            var txtName = binding.txtName.text.toString()
            var bookId = binding.spinnerBook.selectedItem.toString()

            progressBar.show()

            //getting object of from dagger component
            var studentDetailsModel = AppApplication.studentComponent.getStudentDetails()
            studentDetailsModel.setValues(txtPhone,txtName, bookId)

            //print(TAG, "onViewCreated: ${binding.spinnerBook.selectedItem.toString()}, ${binding.spinnerBook.selectedItemId}")
            updateStudentViewModel.updateStudentDetails(studentDetailsModel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewbinding = null
    }

    override fun isOperationSuccessful(value: Boolean, msg: String) {
        print(TAG, "isOperationSuccessful: "+value)
        progressBar.hide()
        progressBar.snack(msg)
        if(value)
            findNavController().popBackStack()
    }

}