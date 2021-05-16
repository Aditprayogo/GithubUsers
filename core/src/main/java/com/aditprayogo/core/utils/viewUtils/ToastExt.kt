package com.aditPrayogo.githubusers.utils.util

import android.app.Activity
import android.widget.Toast

fun Activity.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}