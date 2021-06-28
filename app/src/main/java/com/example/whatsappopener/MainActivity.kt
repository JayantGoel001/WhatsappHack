package com.example.whatsappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var number =""
        if(intent.action== Intent.ACTION_PROCESS_TEXT) {
            number=intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)!!.toString()
        }
        var correctNumber = ""
        for(i in number.indices){
            if (number[i] in '0'..'9') {
                correctNumber+=number[i]
            }
        }
        correctNumber = correctNumber.substring(correctNumber.length-10,correctNumber.length)
        correctNumber = "91$correctNumber"
        Log.d("Number", correctNumber)

        if (correctNumber.isDigitsOnly()) {
            startWhatsAPP(correctNumber)
        } else {
            Toast.makeText(this, "Please Check the Number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startWhatsAPP(number: String) {
        val intent=Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        intent.data= Uri.parse("https://wa.me/$number")
        if(packageManager.resolveActivity(intent,0)!=null) {
            startActivity(intent)
        }  else {
            Toast.makeText(this,"Please Install WhatsApp",Toast.LENGTH_SHORT).show()
        }
        finish()
    }
}
