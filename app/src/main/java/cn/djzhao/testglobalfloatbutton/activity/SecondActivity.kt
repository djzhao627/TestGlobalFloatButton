package cn.djzhao.testglobalfloatbutton.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import cn.djzhao.testglobalfloatbutton.FloatingButtonInterface
import cn.djzhao.testglobalfloatbutton.R

/**
 * SecondActivity
 *
 * @author djzhao
 * @date 22/03/10
 */
class SecondActivity : AppCompatActivity(), FloatingButtonInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}