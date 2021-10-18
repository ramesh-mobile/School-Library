package com.sr.library.ui.add_student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sr.library.AppApplication
import com.sr.library.R
import com.sr.library.databinding.FragmentAddStudentBinding
import com.sr.library.utils.ViewUtil.hide
import com.sr.library.utils.ViewUtil.print
import com.sr.library.utils.ViewUtil.show
import com.sr.library.utils.ViewUtil.snack
import java.util.regex.Pattern

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AddStudentFragment : Fragment(),DatabaseResultCallback {

    private val TAG = "UpdateStudentFragment"

    private var viewbinding: FragmentAddStudentBinding? = null
    lateinit var addStudentViewModel : AddStudentViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = viewbinding!!

    lateinit var progressBar : ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewbinding = FragmentAddStudentBinding.inflate(inflater, container, false)
        addStudentViewModel = ViewModelProvider(this).get(AddStudentViewModel::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addStudentViewModel.databaseResultCallback = this
        progressBar = binding.progressBar
        binding.btnShowAll.setOnClickListener {
            findNavController().navigate(R.id.action_AddStudentFragment_to_AllStudentFragment)
        }

        binding.btnSave.setOnClickListener {
            var txtPhone = binding.txtPhone.text.toString()
            var txtName = binding.txtName.text.toString()
            var bookId = binding.spinnerBook.selectedItem.toString()

            if(txtName.isBlank()) {
                binding.txtName.run {
                    error = getString(R.string.name_field_error)
                    snack(getString(R.string.name_field_error))
                }
                return@setOnClickListener
            }

            if(txtPhone.isBlank() || txtPhone.length != 10){
                binding.txtPhone.run {
                    error = getString(R.string.phone_field_error)
                    snack(getString(R.string.phone_field_error))

                }
                return@setOnClickListener
            }
            //check initial 1st letter should have 9,8,7,6 only
            else if(Pattern.compile("^[6-9]\\d{9}\$").matcher(txtPhone).matches()!=true){
                binding.txtPhone.run {
                    error = getString(R.string.phone_not_valid)
                    snack(getString(R.string.phone_not_valid))
                }
                return@setOnClickListener
            }

            progressBar.show()

            var studentComponent = AppApplication.studentComponent
            //var studentComponent = DaggerStudentComponent.builder().build()
            var studentDetailsModel = studentComponent.getStudentDetails()
            studentDetailsModel.setValues(txtPhone,txtName, bookId)

            print(TAG, "onViewCreated: ${binding.spinnerBook.selectedItem.toString()}, ${binding.spinnerBook.selectedItemId}")
            addStudentViewModel.saveStudentDetails(studentDetailsModel)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.txtName.setText("")
        binding.txtPhone.setText("")
        binding.spinnerBook.setSelection(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewbinding = null
    }


    override fun isOperationSuccessful(value: Boolean, msg:String) {
        print(TAG, "isOperationSuccessful: "+value)
        progressBar.hide()
        progressBar.snack(msg)
    }


}