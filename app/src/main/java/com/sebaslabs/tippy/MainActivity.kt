package com.sebaslabs.tippy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView

private const val TAG = "MainActivity";
private const val INITIAL_TIP_PERCENT = 12;

class MainActivity : AppCompatActivity() {
    private lateinit var baseInput: EditText
    private lateinit var percent: TextView
    private lateinit var percentValue: SeekBar
    private lateinit var tipValue: TextView
    private lateinit var totalValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        start of the code
        baseInput = findViewById(R.id.baseInput)
        percent = findViewById(R.id.percent)
        percentValue = findViewById(R.id.percentValue)
        tipValue = findViewById(R.id.tipValue)
        totalValue = findViewById(R.id.totalValue)

        percentValue.progress = INITIAL_TIP_PERCENT
        percent.text = "$INITIAL_TIP_PERCENT %"

//        update the values on the seekbar and log the values
        percentValue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "Value updated to $progress")
                percent.text = "$progress%"
                computeTipAndTotal()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        });

///        do updates on the baseInput and listen to its changes
        baseInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "After text changes $s")
//
                computeTipAndTotal()
            }
        })
    }

    private fun computeTipAndTotal() {

//get the tip percent
        val baseAmount = baseInput.text.toString().toDouble()
        val tipPercent = percentValue.progress;

        val tpAmount = baseAmount * tipPercent / 100
        val total = baseAmount + tpAmount

        tipValue.text = tpAmount.toString()
        totalValue.text = total.toString()
    }
}