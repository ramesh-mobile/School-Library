package com.sr.library.ui.all_student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sr.library.R
import com.sr.library.data.model.entity.StudentDetailsModel
import com.sr.library.databinding.FragmentAllStudentBinding
import com.sr.library.utils.ParamUtils


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AllStudentFragment : Fragment(), AllStudentRowAdapter.StudentRowDataListener {

    private val TAG = "AllStudentFragment"

    private var viewBinding: FragmentAllStudentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = viewBinding!!
    private lateinit var viewModel: AllStudentViewModel
    private lateinit var allStudentRowAdapter: AllStudentRowAdapter

    private lateinit var recyclerView : RecyclerView

    private var studentDetailsModelList: MutableList<StudentDetailsModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentAllStudentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(AllStudentViewModel::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView  = binding.recyclerData

        allStudentRowAdapter = AllStudentRowAdapter(studentDetailsModelList,this)
        recyclerView.adapter = allStudentRowAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context,DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(activity)
        allStudentRowAdapter.notifyDataSetChanged()

        viewModel.studDetModelLD.observe(this.viewLifecycleOwner, {
            studentDetailsModelList.clear()
            studentDetailsModelList.addAll(it)
            allStudentRowAdapter.notifyDataSetChanged()
        })

        viewModel.getAllDetails()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun onDeleteClicked(phone: String) {
        viewModel.onDelete(phone)
    }

    override fun onEditClicked(studentDetailsModel: StudentDetailsModel) {
        var bundle = Bundle()
        bundle.putParcelable(ParamUtils.STUDENT_DATA,studentDetailsModel)
        findNavController().navigate(R.id.action_AllStudentFragment_to_EditStudentFragment,bundle)
    }
}