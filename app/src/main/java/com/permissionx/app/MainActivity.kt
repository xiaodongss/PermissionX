package com.permissionx.app

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.permissionx.library.PermissionX

class MainActivity : AppCompatActivity() {
    //    private var callback:((Boolean,List<String>)->Unit)?=null
//    fun requestNow(cb:(Boolean,List<String>)->Unit,vararg permissions:String){
//        callback = cb
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(permissions,1)
//        }
//    }
    var makeCallBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeCallBtn?.setOnClickListener {
            PermissionX.request(this,Manifest.permission.CALL_PHONE){
                allGranted,deniedList->{
                    if (allGranted){
                        Log.i("MainActivity","权限请求成功")
                    }else{
                        Toast.makeText(this, "请求$deniedList 失败", Toast.LENGTH_SHORT).show()
                    }
                    
            }
            }
        }
    }
}