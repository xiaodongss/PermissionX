package com.permissionx.library

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

typealias PermissionCallback=(Boolean,List<String>)->Unit
class InvisibleFragment : Fragment() {
   private var  callback:PermissionCallback? = null
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<out String>>;
    fun requestNow(cb:PermissionCallback,vararg permissions:String){
        callback = cb


        requestPermissionLauncher.launch(permissions)
//        requestPermissions(permissions,1)
//        ActivityResultContracts.RequestMultiplePermissions
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
         requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                val deniedList = ArrayList<String>()
                for((key,value )in it){
                    if (!value){
                        deniedList.add(key)
                    }
                }
                val allGranted = deniedList.isEmpty()
                callback?.let { it(allGranted,deniedList) }
            }

    }
//用requestPermissionLauncher代替了这个方法
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//    }
}