package com.wynnsolutionsmyanmar.aesnoteencrypt.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hanks.passcodeview.PasscodeView
import com.wynnsolutionsmyanmar.aesnoteencrypt.R
import com.wynnsolutionsmyanmar.aesnoteencrypt.SharedPreference
import kotlinx.android.synthetic.main.activity_set_pin_code.*
import kotlinx.android.synthetic.main.content_set_pin_code.*

class UpdatePinCode : AppCompatActivity() {

    private lateinit var  passcodeView : PasscodeView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_pin_code)
        setSupportActionBar(toolbar)

        val sharedPreference: SharedPreference = SharedPreference(this)
        val passcodeView = findViewById(R.id.passcodeView) as PasscodeView

        passcodeView.setPasscodeLength(4).setListener(object : PasscodeView.PasscodeViewListener {
            override fun onFail() {
                Toast.makeText(application, "Wrong!!", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(number: String) {
                sharedPreference.save("code", passcodeView.getLocalPasscode().toString())
                Toast.makeText(application, "Passcode Updated", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UpdatePinCode, MainActivity::class.java)
                this@UpdatePinCode.startActivity(intent)
                finish()
            }
        })


        skip_pc!!.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }
}
