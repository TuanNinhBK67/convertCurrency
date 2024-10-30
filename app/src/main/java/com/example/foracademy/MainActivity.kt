package com.example.foracademy

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.widget.addTextChangedListener
import com.example.foracademy.ui.theme.ForAcademyTheme

class MainActivity : ComponentActivity(), View.OnClickListener {
    lateinit var text1 : EditText
    lateinit var text2 : EditText
    lateinit var spinner1 : Spinner
    lateinit var spinner2 : Spinner

    val exchangeRate = mapOf(
        "Egyptian Pound (EGP)" to 48.75,
        "South African Rand (ZAR)" to 19.17,
        "Nigerian Naira (NGN)" to 775,
        "Ghanaian Cedi (GHS)" to 11.44,
        "Kenyan Shilling (KES))" to 147.90,
        "Japanese Yen (JPY)" to 151.94,
        "Chinese Yuan (CNY)" to 7.29,
        "Indian Rupee (INR)" to 83.08,
        "Vietnamese Dong (VND)" to 24490,
        "Indonesian Rupiah (IDR)" to 15672,
        "South Korean Won (KRW)" to 1357,
        "Thai Baht (THB)" to 36.19,
        "Pakistani Rupee (PKR)" to 277,
        "Euro (EUR)" to 0.94,
        "British Pound (GBP)" to 0.78,
        "Swiss Franc (CHF)" to 0.91,
        "Norwegian Krone (NOK)" to 10.93,
        "Swedish Krona (SEK)" to 11.14,
        "Russian Ruble (RUB)" to 94.5,
        "Danish Krone (DKK)" to 7.00,
        "Saudi Riyal (SAR)" to 3.75,
        "UAE Dirham (AED)" to 3.67,
        "Israeli Shekel (ILS)" to 3.95,
        "Jordanian Dinar (JOD)" to 0.71,
        "Qatari Riyal (QAR)" to 3.64,
        "Turkish Lira (TRY)" to 28.86,
        "US Dollar (USD)" to 1,
        "Canadian Dollar (CAD)" to 1.39,
        "Brazilian Real (BRL)" to 4.91,
        "Argentine Peso (ARS)" to 350,
        "Mexican Peso (MXN)" to 18.15,
        "Colombian Peso (COP)" to 4349,
        "Chilean Peso (CLP)" to 925,
        "Australian Dollar (AUD)" to 1.57,
        "New Zealand Dollar (NZD)" to 1.67,
        "Fijian Dollar (FJD)" to 2.28,
        "Papua New Guinean Kina (PGK)" to 3.58,
        "Jamaican Dollar (JMD)" to 155.10,
        "Trinidad and Tobago Dollar (TTD)" to 6.80,
        "Bahamian Dollar (BSD)" to 1.00,
        "Belize Dollar (BZD)" to 2.00
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        text1 = findViewById(R.id.editText1)
        text2 = findViewById(R.id.editText2)
        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)

        val items : Array<String> = arrayOf("Egyptian Pound (EGP)",
                "South African Rand (ZAR)",
                "Nigerian Naira (NGN)",
                "Ghanaian Cedi (GHS)",
                "Kenyan Shilling (KES))",
                "Japanese Yen (JPY)",
                "Chinese Yuan (CNY)",
                "Indian Rupee (INR)",
                "Vietnamese Dong (VND)",
                "Indonesian Rupiah (IDR)",
                "South Korean Won (KRW)",
                "Thai Baht (THB)",
                "Pakistani Rupee (PKR)",
                "Euro (EUR)",
                "British Pound (GBP)",
                "Swiss Franc (CHF)",
                "Norwegian Krone (NOK)",
                "Swedish Krona (SEK)",
                "Russian Ruble (RUB)",
                "Danish Krone (DKK)",
                "Saudi Riyal (SAR)",
                "UAE Dirham (AED)",
                "Israeli Shekel (ILS)",
                "Jordanian Dinar (JOD)",
                "Qatari Riyal (QAR)",
                "Turkish Lira (TRY)",
                "US Dollar (USD)",
                "Canadian Dollar (CAD)",
                "Brazilian Real (BRL)",
                "Argentine Peso (ARS)",
                "Mexican Peso (MXN)",
                "Colombian Peso (COP)",
                "Chilean Peso (CLP)",
                "Australian Dollar (AUD)",
                "New Zealand Dollar (NZD)",
                "Fijian Dollar (FJD)",
                "Papua New Guinean Kina (PGK)",
                "Jamaican Dollar (JMD)",
                "Trinidad and Tobago Dollar (TTD)",
                "Bahamian Dollar (BSD)",
                "Belize Dollar (BZD)")
        val arrayAdapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)
        spinner1.adapter = arrayAdapter
        spinner2.adapter = arrayAdapter

        text1.addTextChangedListener{ if(text1.hasFocus()) convertCurrency() }
        text2.addTextChangedListener{ if(text2.hasFocus()) convertCurrency()}
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                convertCurrency()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                convertCurrency()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        text1.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                text1.hint = "Type"
                text2.hint = "result"
            }
        }
        text2.setOnFocusChangeListener{ _, hasFocus ->
            if(hasFocus){
                text1.hint = "result"
                text2.hint = "Tpye"
            }
        }
    }

    private fun convertCurrency() {
        val sourceCurrency = spinner1.selectedItem.toString()
        val destinationCurrency = spinner2.selectedItem.toString()

        val sourceRate : Double = (exchangeRate[sourceCurrency] ?: return) as Double
        val destinationRate : Double = (exchangeRate[destinationCurrency] ?: return) as Double

        val amount = if (text1.hasFocus()) {
            text1.text.toString().toDoubleOrNull() ?: return
        } else {
            text2.text.toString().toDoubleOrNull()?.let { it / (destinationRate / sourceRate) } ?: return
        }

        val result = if (text1.hasFocus()) amount * (destinationRate / sourceRate) else amount
        if (text1.hasFocus()) text2.setText(result.toString()) else text1.setText(result.toString())
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}

private operator fun Any.times(other: Double): Double {
    return this * other
}

private operator fun Any.div(other: Double): Double {
    return this / other
}



