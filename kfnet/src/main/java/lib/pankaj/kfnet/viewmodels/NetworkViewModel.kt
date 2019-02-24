package lib.pankaj.kfnet.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import lib.pankaj.kfnet.data.NetworkCallModel
import lib.pankaj.kfnet.network.Client
import java.io.File

class NetworkViewModel : ViewModel() {

    private val job = Job()
    val bgScope = CoroutineScope(Dispatchers.Default + job)

    inline fun <reified T> get(url: String): LiveData<NetworkCallModel<T>> {
        val mutableLiveData = MutableLiveData<NetworkCallModel<T>>()
        bgScope.launch(networkCallException) {
            Client.getExecute(url, mutableLiveData)
        }

        return mutableLiveData
    }

    inline fun <reified T> post(url: String, model: Any): LiveData<NetworkCallModel<T>> {
        val mutableLiveData = MutableLiveData<NetworkCallModel<T>>()
        bgScope.launch(networkCallException) {
            Client.postExecute(url, model, mutableLiveData)
        }

        return mutableLiveData
    }

    inline fun <reified T> uploadFile(url: String, file: File): LiveData<NetworkCallModel<T>>  {
        val mutableLiveData = MutableLiveData<NetworkCallModel<T>>()
        bgScope.launch(networkCallException) {
            Client.uploadFile(url, file, mutableLiveData)
        }

        return mutableLiveData
    }

    fun cancelNetworkCall() {
        Client.okHttpClient.dispatcher().queuedCalls().forEach { it.cancel() }
    }

    override fun onCleared() {
        super.onCleared()

        job.cancel()
    }

    val networkCallException = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("DATA", "Exc" + throwable)
        Log.e("DATA", "Exc" + Client.okHttpClient.dispatcher().queuedCallsCount())
    }

}