package com.wynnsolutionsmyanmar.aesnoteencrypt.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.wynnsolutionsmyanmar.aesnoteencrypt.R
import com.wynnsolutionsmyanmar.aesnoteencrypt.SharedPreference
import kotlinx.android.synthetic.main.activity_add_note.*
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class DecryptActivity : AppCompatActivity() {

    val AES = "AES"

    var decDesc: String = ""
    var enc_dec: Boolean = false

    companion object {
        const val EXTRA_TITLE = ".EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = ".EXTRA_DESCRIPTION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.add_note_menu, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        return when (item?.itemId) {
//            R.id.save_note -> {
//                saveNote()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//
//    private fun saveNote() {
//        if (edit_text_title.text.toString().trim().isBlank() || edit_text_description.text.toString().trim().isBlank()) {
//            Toast.makeText(this, "Can not insert empty note!", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        val data = Intent().apply {
//            putExtra(EXTRA_TITLE, edit_text_title.text.toString())
//            putExtra(EXTRA_DESCRIPTION, edit_text_description.text.toString())
//        }
//
//        setResult(Activity.RESULT_OK, data)
//        finish()
//    }
    fun btnDec(view: View){
        decDesc  = decrypt(edit_text_description.text.toString(),edit_text_key.toString())
        edit_text_description.setText(decDesc)
        enc_dec = false
        Toast.makeText(this,"Decrypted",Toast.LENGTH_LONG).show()

    }

    @Throws(Exception::class)
    private fun decrypt(outputString: String, password: String): String {
        val key = generateKey(password)
        val c = Cipher.getInstance(AES)
        c.init(Cipher.DECRYPT_MODE, key)
        val decodedValue = Base64.decode(outputString, Base64.DEFAULT)
        val decValue = c.doFinal(decodedValue)
        return String(decValue)
    }

    @Throws(Exception::class)
    private fun generateKey(password: String): SecretKeySpec {
        val digest = MessageDigest.getInstance("SHA-256")
        val bytes = password.toByteArray(charset("UTF-8"))
        digest.update(bytes, 0, bytes.size)
        val key = digest.digest()
        return SecretKeySpec(key, "AES")
    }
}

