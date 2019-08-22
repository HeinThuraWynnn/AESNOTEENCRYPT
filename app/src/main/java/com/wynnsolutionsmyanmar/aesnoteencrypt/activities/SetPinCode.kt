package com.wynnsolutionsmyanmar.aesnoteencrypt.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hanks.passcodeview.PasscodeView
import com.wynnsolutionsmyanmar.aesnoteencrypt.R
import com.wynnsolutionsmyanmar.aesnoteencrypt.SharedPreference

import kotlinx.android.synthetic.main.activity_set_pin_code.*
import kotlinx.android.synthetic.main.content_set_pin_code.*

class SetPinCode : AppCompatActivity() {
    private lateinit var  passcodeView : PasscodeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_pin_code)
        setSupportActionBar(toolbar)
        val sharedPreference: SharedPreference = SharedPreference(this)
        val pc = sharedPreference.getValueString("code")
        val passcodeView = findViewById(R.id.passcodeView) as PasscodeView
        if (pc == null){
            passcodeView.setPasscodeLength(4).setListener(object : PasscodeView.PasscodeViewListener {
                override fun onFail() {
                    Toast.makeText(application, "Wrong!!", Toast.LENGTH_SHORT).show()
                }

                override fun onSuccess(number: String) {
                    sharedPreference.save("code", passcodeView.getLocalPasscode().toString())
                    Toast.makeText(application, "Welcome", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SetPinCode, MainActivity::class.java)
                    this@SetPinCode.startActivity(intent)
                    finish()
                }
            })
        }else{
            passcodeView.setPasscodeLength(4).setLocalPasscode(pc).setListener(object : PasscodeView.PasscodeViewListener {
                override fun onFail() {
                    Toast.makeText(application, "Wrong!!", Toast.LENGTH_SHORT).show()
                }

                override fun onSuccess(number: String) {
                    Toast.makeText(application, "Welcome User", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SetPinCode, MainActivity::class.java)
                    this@SetPinCode.startActivity(intent)
                    finish()
                }
            })
        }



        skip_pc!!.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }


    }

}
