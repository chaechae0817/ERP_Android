package com.example.erp_qr.data

data class SalaryDTO(
    var employeeId: String,
    var monthlySalary: String,
    var allowanceDetails: Map<String,Double>,
    var totalAllowance: String,
    var deductionDetails: Map<String,Double>,
    var totalDeductions: String
)
