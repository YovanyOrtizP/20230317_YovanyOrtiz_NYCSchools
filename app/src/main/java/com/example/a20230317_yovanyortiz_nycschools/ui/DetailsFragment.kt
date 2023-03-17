package com.example.a20230317_yovanyortiz_nycschools.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.a20230317_yovanyortiz_nycschools.R
import com.example.a20230317_yovanyortiz_nycschools.data.model.SatResultResponse
import com.example.a20230317_yovanyortiz_nycschools.data.model.domain.SchoolInfoDomain
import com.example.a20230317_yovanyortiz_nycschools.databinding.FragmentDetailsBinding
import com.example.a20230317_yovanyortiz_nycschools.databinding.FragmentSchoolsBinding
import com.example.a20230317_yovanyortiz_nycschools.ui.viewmodel.NYCViewModel
import com.example.a20230317_yovanyortiz_nycschools.util.ResponseType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {


    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NYCViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        initDetailsSchool(viewModel.schoolsObject)

        viewModel.resultSat.observe(viewLifecycleOwner) {
            when(it) {
                is ResponseType.LOADING -> {}
                is ResponseType.SUCCESS -> {
                    showScores(it.response.firstOrNull())
                }
                is ResponseType.ERROR -> {}
            }
        }
        viewModel.getSatInfoFlow()

        binding.backButton.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, SchoolsFragment())
                .commit()
        }

        return binding.root
    }

    private fun initDetailsSchool(selectedSchool: SchoolInfoDomain?) {
        selectedSchool?.let {
            binding.tvSchoolName.text = it.schoolName
            binding.tvOverview.text = it.overView
//            binding.tvWebsite.text = it.website
        }
//        } ?: let {
//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container_view, SchoolsFragment())
//                .commit()
//        }
    }

    private fun showScores(score: SatResultResponse?) {
        score?.let {
            binding.mathScore.text = "MathScore: ${it.satMathAvgScore}"
            binding.readingScore.text = "ReadingScore: ${it.satCriticalReadingAvgScore}"
            binding.writingScore.text = "WritingScore: ${it.satWritingAvgScore}"

        } ?: let{// mostrar no avaialble scores
            binding.mathScore.text = "MathScore not found"
            binding.readingScore.text = "ReadingScore not found"
            binding.writingScore.text = "WritingScore not found"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}