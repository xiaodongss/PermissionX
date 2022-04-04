package com.permissionx.library

import android.app.Activity
import androidx.fragment.app.FragmentActivity

object PermissionX {
    private const val TAG = "InvisibleFragment"
    fun request(activity: FragmentActivity, vararg permissions:String, callback:PermissionCallback){
        val fragmentManager = activity.supportFragmentManager
        val exitFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (exitFragment!=null){
            exitFragment as InvisibleFragment
        }else{
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback,*permissions)
    }
}