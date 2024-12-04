package com.example.erp_qr.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.erp_qr.adapter.VacationAdapter
import com.example.erp_qr.databinding.FragmentVacationBinding
import com.example.erp_qr.vaction.VacationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VacationFragment : Fragment() {

    private var _binding: FragmentVacationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VacationViewModel by viewModels()
    private val vacationAdapter by lazy { VacationAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentVacationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.vacationRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = vacationAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.vacationData.observe(viewLifecycleOwner) { vacationList ->
            vacationAdapter.submitList(vacationList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}