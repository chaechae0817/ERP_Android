package com.example.erp_qr.Fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.erp_qr.R
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // QR 코드 생성
        val qrCodeView: ImageView = view.findViewById(R.id.qr_code)
        val employeeID = "12345" // 예제 데이터
        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap: Bitmap = barcodeEncoder.encodeBitmap(
                employeeID, com.google.zxing.BarcodeFormat.QR_CODE, 400, 400
            )
            qrCodeView.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return view
    }
}