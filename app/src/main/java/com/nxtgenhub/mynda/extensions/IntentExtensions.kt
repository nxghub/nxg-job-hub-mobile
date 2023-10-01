package com.nxtgenhub.mynda.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

inline fun <reified T : Any> Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    startActivityForResult(newIntent<T>(this, init), requestCode, options)
}

inline fun <reified T : Any> Context.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        startActivity(newIntent<T>(this, init), options)
    else
        startActivity(newIntent<T>(this, init))
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
inline fun <reified T : Any> Fragment.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    startActivityForResult(newIntent<T>(requireContext(), init), requestCode, options)
}

inline fun <reified T : Any> newIntent(
    context: Context,
    noinline init: Intent.() -> Unit = {}
): Intent {
    val intent = Intent(context, T::class.java)
    intent.init()
    return intent
}