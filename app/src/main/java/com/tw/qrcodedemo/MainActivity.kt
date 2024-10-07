package com.tw.qrcodedemo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : AppCompatActivity() {

    lateinit var tvResult:TextView
    lateinit var btnScan:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
        btnScan = findViewById(R.id.btnScan)

        btnScan.setOnClickListener {
            scannerLauncher.launch(
                ScanOptions().setPrompt("Scan Qr Code")
                    .setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            )
        }
    }

    private val scannerLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(ScanContract()) { result ->

        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            tvResult.text = result.contents
        }

    }

}