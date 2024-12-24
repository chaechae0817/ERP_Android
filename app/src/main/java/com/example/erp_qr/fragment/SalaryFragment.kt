package com.example.erp_qr.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.erp_qr.adapter.AllowanceAdapter
import com.example.erp_qr.adapter.DeductionAdapter
import com.example.erp_qr.databinding.FragmentSalaryBinding
import com.example.erp_qr.salary.SalaryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SalaryFragment : Fragment() {
    private lateinit var binding: FragmentSalaryBinding
    private val viewModel: SalaryViewModel by viewModels()

    private val allowanceAdapter = AllowanceAdapter()
    private val deductionAdapter = DeductionAdapter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSalaryBinding.inflate(inflater,container,false).apply {
            lifecycleOwner = viewLifecycleOwner
            this.viewModel = this@SalaryFragment.viewModel
        }

        setObserve()
        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.allowanceRv.layoutManager = LinearLayoutManager(requireContext())
        binding.deductionRv.layoutManager = LinearLayoutManager(requireContext())
        binding.allowanceRv.adapter = allowanceAdapter
        binding.deductionRv.adapter = deductionAdapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setObserve(){
        // ViewModel의 salaryData 변경 관찰 (RecyclerView 데이터 갱신)
        viewModel.salaryData.observe(viewLifecycleOwner) { salaryData ->
            salaryData?.let {
                Log.d("SalaryFragment", "Allowance Details: ${it.allowanceDetails}")
                Log.d("SalaryFragment", "Deduction Details: ${it.deductionDetails}")
                allowanceAdapter.submitList(it.allowanceDetails.entries.toList())
                deductionAdapter.submitList(it.deductionDetails.entries.toList())
            }
        }
        viewModel.selectedButton.observe(viewLifecycleOwner) {
            if (it == "allowance") {
                Log.d("SalaryFragment", "setObserve: allowance")
        }
            if (it == "deduction") {
                Log.d("SalaryFragment", "setObserve: deduction")
        }
    }

    }
}
