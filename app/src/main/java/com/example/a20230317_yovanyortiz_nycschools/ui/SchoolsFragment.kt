package com.example.a20230317_yovanyortiz_nycschools.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a20230317_yovanyortiz_nycschools.R
import com.example.a20230317_yovanyortiz_nycschools.data.model.domain.SchoolInfoDomain
import com.example.a20230317_yovanyortiz_nycschools.databinding.FragmentSchoolsBinding
import com.example.a20230317_yovanyortiz_nycschools.ui.adapter.SchoolsAdapter
import com.example.a20230317_yovanyortiz_nycschools.ui.viewmodel.NYCViewModel
import com.example.a20230317_yovanyortiz_nycschools.util.ResponseType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolsFragment : Fragment() {

    private var _binding: FragmentSchoolsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NYCViewModel by activityViewModels()

    private val mAdapter by lazy{
        SchoolsAdapter{
            viewModel.schoolsObject = it
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, DetailsFragment.newInstance())
                .commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSchoolsBinding.inflate(inflater,container,false)

        binding.rvSchools.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        viewModel.resultSchools.observe(viewLifecycleOwner){
            when(it){
                is ResponseType.LOADING -> {}
                is ResponseType.SUCCESS<List<SchoolInfoDomain>> -> {
                    initViews(it.response)
                }
                is ResponseType.ERROR -> {}
            }
        }
        viewModel.getSchoolsInfoFlow()

        return binding.root

    }


    private fun initViews(response: List<SchoolInfoDomain>) {
        response?.let {
            mAdapter.updateSchools(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}