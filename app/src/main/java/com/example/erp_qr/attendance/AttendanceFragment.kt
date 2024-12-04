package com.example.erp_qr.attendance

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.erp_qr.R
import com.example.erp_qr.adapter.AttendanceAdapter
import com.example.erp_qr.databinding.FragmentAttendanceBinding
import com.example.erp_qr.decorator.CalendarDecorators
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AttendanceFragment : Fragment() {
    private lateinit var binding: FragmentAttendanceBinding
    private val viewModel: AttendanceViewModel by viewModels()
    private val adapter  = AttendanceAdapter()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAttendanceBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            this.viewModel = this@AttendanceFragment.viewModel
        }
        setRecyclerView()

        setObserve()
        setupCalendarView()

        return binding.root
    }

    private fun setRecyclerView(){
        binding.attendanceRv.adapter = adapter
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupCalendarView(){
        binding.calendarView.setTitleFormatter(MonthArrayTitleFormatter(resources.getTextArray(R.array.custom_months)));
        binding.calendarView.setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.custom_weekdays)));

        binding.calendarView.addDecorator(CalendarDecorators.todayDecorator(requireContext()))

        // 캘린더 범위 선택 리스너
        binding.calendarView.setOnRangeSelectedListener(OnRangeSelectedListener { _, dates ->
            if (dates.isNotEmpty()) {

                binding.calendarView.removeDecorators()
                binding.calendarView.addDecorator(CalendarDecorators.todayDecorator(requireContext()))

                val startDate = dates.first() // 범위의 시작 날짜
                val endDate = dates.last()   // 범위의 끝 날짜

                // 시작/끝 날짜 데코레이터 추가
                binding.calendarView.addDecorator(CalendarDecorators.rangeDecorator(requireContext(), startDate, endDate))
                binding.calendarView.addDecorator(CalendarDecorators.startAndEndDateDecorator(requireContext(), startDate, true))
                binding.calendarView.addDecorator(CalendarDecorators.startAndEndDateDecorator(requireContext(), endDate, false))

                viewModel.filterAttendanceByDateRange(startDate.date,endDate.date)
            }
        })
        // 단일 날짜 선택 리스너
        binding.calendarView.setOnDateChangedListener { _, date, selected ->
            binding.calendarView.addDecorator(CalendarDecorators.dayDecorator(requireContext()))
            if(selected) {
                binding.calendarView.setSelectionColor(Color.RED)
                val selectedDate = date.date // 선택된 날짜 (LocalDate 형식)
                viewModel.filterAttendanceByDate(selectedDate) // ViewModel에 선택된 날짜 전달
            } else{
                binding.calendarView.clearSelection()
                val currentMonth = date.month.toString()
                viewModel.loadAttendanceForMonth(currentMonth)
            }
        }
        // **월 변경 리스너 추가**
        binding.calendarView.setOnMonthChangedListener { _, date ->
            val newMonth = date.month.toString() // 새로 선택된 월 (ex: "10")
            binding.calendarView.clearSelection()
            viewModel.loadAttendanceForMonth(newMonth) // ViewModel에 새 월 전달
        }
    }

    fun setObserve(){
        viewModel.filteredAttendance.observe(viewLifecycleOwner){ attendanceList ->
            // RecyclerView 업데이트
            adapter.submitList(attendanceList) // 어댑터에 데이터 전달
        }
        viewModel.attendanceData.observe(viewLifecycleOwner){attendanceList ->
            adapter.submitList(attendanceList)
        }
    }
}
