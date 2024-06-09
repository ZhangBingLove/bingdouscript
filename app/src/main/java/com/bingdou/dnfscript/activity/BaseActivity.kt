package com.bingdou.dnfscript.activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.bingdou.dnfscript.tools.Logger

//基类，打印日志
open class BaseActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d("Create activity " + this);
    }

    override fun onResume() {
        super.onResume()
        Logger.d("onResume activity " + this);
    }


    override fun onRestart() {
        super.onRestart()
        Logger.d("onRestart activity " + this);
    }

    override fun onPause() {
        super.onPause()
        Logger.d("onPause activity " + this);
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.d("onDestroy activity " + this);
    }

    override fun onStop() {
        super.onStop()
        Logger.d("onStop activity " + this);
    }

    override fun onStart() {
        super.onStart()
        Logger.d("onStart activity " + this);
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Logger.d("onConfigurationChanged activity " + this);
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Logger.d("onNewIntent  activity" + this);
    }
}