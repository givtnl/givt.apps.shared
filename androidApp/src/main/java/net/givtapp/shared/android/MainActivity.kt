package net.givtapp.shared.android

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import android.text.Editable
import androidx.core.content.ContextCompat
import net.givtapp.codeshare.Greeting
import net.givtapp.codeshare.creditcards.CreditCardValidator


fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        val ccValidator = CreditCardValidator()

        val til_CC: TextInputLayout = findViewById(R.id.til_CC)
        til_CC.setErrorTextColor(ColorStateList.valueOf(ContextCompat.getColor(applicationContext, R.color.design_default_color_error)))
        til_CC.hint = "Wuk zoej ier moetn invuln?"

        til_CC.editText!!.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if (!ccValidator.cardNumberIsValid()) {
                    til_CC.error = "Invalid credit card"
                    til_CC.isErrorEnabled = true
                } else {
                    til_CC.isErrorEnabled = false
                    til_CC.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                ccValidator.creditCard.number = s.toString()
            }

            override fun afterTextChanged(s: Editable) {
                s.replace(0, s.length, ccValidator.creditCard.formatted)
            }
        })
    }
}
