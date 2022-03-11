package cn.djzhao.testglobalfloatbutton.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.djzhao.testglobalfloatbutton.FloatingButtonInterface
import cn.djzhao.testglobalfloatbutton.R

class MainActivity : AppCompatActivity(), FloatingButtonInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tips).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

    }
}