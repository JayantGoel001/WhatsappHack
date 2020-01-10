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
        var number:String=""
        if(intent.action== Intent.ACTION_PROCESS_TEXT)
        {
            number=intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)!!.toString()
        }
        if(number.contains(" ")) {
            if(number.length==11) {
                number = "91"+number.substring(0, 5) + number.substring(6)
                Log.i("NUMBER", number)
            }
            else if(number.length==14)
            {
                number=number.substring(0,2)+number.substring(3,8)+number.substring(9)
                Log.i("NUMBER",number)
            }
            else if(number.length==15)
            {
                number=number.substring(1,3)+number.substring(4,9)+number.substring(10)
                Log.i("NUMBER",number)
            }
        }
        if (number.isDigitsOnly()) {
            startWhatsAPP(number)
        } else {
            Toast.makeText(this, "Please Check the Number", Toast.LENGTH_SHORT).show()
        }

    }

    private fun startWhatsAPP(number: String) {
        val intent=Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        val num =number
        intent.data= Uri.parse("https://wa.me/$num")
        if(packageManager.resolveActivity(intent,0)!=null)
        {
            startActivity(intent)
        }
        else
        {
            Toast.makeText(this,"Please Install WhatsAPP",Toast.LENGTH_SHORT).show()
        }
        finish()
    }
}
