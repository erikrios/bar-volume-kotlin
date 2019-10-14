package com.erikriosetiawan.barvolumekotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtLength: EditText
    private lateinit var edtWidth: EditText
    private lateinit var edtHeigth: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtLength = findViewById(R.id.edt_length)
        edtWidth = findViewById(R.id.edt_width)
        edtHeigth = findViewById(R.id.edt_height)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_calculate -> {
                val inputLength = edtLength.text.toString().trim()
                val inputWidth = edtWidth.text.toString().trim()
                val inputHeight = edtHeigth.text.toString().trim()

                var isEmptyFields = false

                if (inputLength.isEmpty()) {
                    isEmptyFields = true
                    edtLength.error = "Field ini tidak boleh kosong"
                }

                if (inputWidth.isEmpty()) {
                    isEmptyFields = true
                    edtWidth.error = "Field ini tidak boleh kosong"
                }

                if (inputHeight.isEmpty()) {
                    isEmptyFields = true
                    edtHeigth.error = "Field ini tidak boleh kosong"
                }

                if (!isEmptyFields) {
                    val volume =
                        inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                    tvResult.text = volume.toString()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
}