package com.example.erp_qr.ui

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.erp_qr.R
import com.google.android.material.button.MaterialButton

object MaterialButtonBindingAdapters {

    @BindingAdapter("app:backgroundTint")
    @JvmStatic
    fun setBackgroundTint(button: MaterialButton, isSelected: Boolean) {
        val context: Context = button.context
        val color = if (isSelected) {
            ContextCompat.getColor(context, R.color.selectedBtn)
        } else {
            ContextCompat.getColor(context, R.color.white)
        }
        button.setBackgroundColor(color)
    }

    @BindingAdapter("android:textColor")
    @JvmStatic
    fun setTextColor(button: MaterialButton,isSelected: Boolean){
        val context: Context = button.context
        val color = if (isSelected) {
            ContextCompat.getColor(context, R.color.white)
        } else {
          ContextCompat.getColor(context,R.color.selectedBtn)
        }
        button.setTextColor(color)
    }

}