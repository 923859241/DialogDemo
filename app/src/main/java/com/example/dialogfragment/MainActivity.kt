package com.example.dialogfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.dialogfragment.view.GestureSelectComponent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //点击按钮
        testButton.setOnClickListener{
            val gestureDialog = GestureSelectComponent()
            //设置无内边框
            //gestureDialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.dialogFullScreen)//添加上面创建的style
            GestureSelectComponent.newInstance().show(supportFragmentManager,"myDialog")
        }

    }

}
