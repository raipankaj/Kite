package lib.pankaj.kfnet.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import lib.pankaj.kfnet.viewmodels.NetworkViewModel

fun AppCompatActivity.initKite(): NetworkViewModel {
    return ViewModelProviders.of(this).get(NetworkViewModel::class.java)
}

fun Fragment.initKite(): NetworkViewModel {
    return ViewModelProviders.of(this).get(NetworkViewModel::class.java)
}
