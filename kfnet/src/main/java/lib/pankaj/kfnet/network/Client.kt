package lib.pankaj.kfnet.network

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import lib.pankaj.kfnet.data.NetworkCallModel
import okhttp3.*
import java.io.File
import java.lang.Exception


object Client {

    val okHttpClient: OkHttpClient = OkHttpClient()

    inline fun <reified T> getExecute(url: String, mutableLiveData: MutableLiveData<NetworkCallModel<T>>) {

        val request = Request.Builder()
            .url(url)
            .build()

        okHttpClient.newCall(request).execute().use { response ->
            try {
                val convertToJson = convertToJson<T>(response)
                mutableLiveData.postValue(NetworkCallModel(convertToJson, response, null))
            } catch (e: Exception) {
                mutableLiveData.postValue(NetworkCallModel(null, null, e))
            }
        }
    }

    /*inline fun <reified T> enqueue(url: String, mutableLiveData: MutableLiveData<NetworkCallModel<T>>) {
        val request = Request.Builder()
            .url(url)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                mutableLiveData.postValue(NetworkCallModel(null, null, e))
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val convertToJson = convertToJson<T>(response)
                    mutableLiveData.postValue(NetworkCallModel(convertToJson, response, null))
                } catch (e: Exception) {
                    mutableLiveData.postValue(NetworkCallModel(null, null, e))
                }
            }
        })
    }*/

    inline fun <reified T> uploadFile(url: String, file: File, mutableLiveData: MutableLiveData<NetworkCallModel<T>>) {
        val requestBody = createMultipart("text/plain", file)

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        okHttpClient.newCall(request).execute().use { response ->
            try {
                val convertToJson = convertToJson<T>(response)
                mutableLiveData.postValue(NetworkCallModel(convertToJson, response, null))
            } catch (e: Exception) {
                mutableLiveData.postValue(NetworkCallModel(null, null, e))
            }
        }
    }

    fun createMultipart(mediaType: String, file: File): MultipartBody {
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "file", file.name,
                RequestBody.create(MediaType.parse(mediaType), file)
            )
            .build()
    }

    inline fun <reified T> uploadPhoto(url: String, file: File, mutableLiveData: MutableLiveData<NetworkCallModel<T>>) {
        val requestBody = createMultipart("image/png", file)

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        okHttpClient.newCall(request).execute().use { response ->
            try {
                val convertToJson = convertToJson<T>(response)
                mutableLiveData.postValue(NetworkCallModel(convertToJson, response, null))
            } catch (e: Exception) {
                mutableLiveData.postValue(NetworkCallModel(null, null, e))
            }
        }
    }

    inline fun <reified T> postExecute(url: String, model: Any, mutableLiveData: MutableLiveData<NetworkCallModel<T>>) {
        val JSON = MediaType.get("application/json; charset=utf-8")
        val requestBody = RequestBody.create(JSON, Gson().toJson(model))

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        okHttpClient.newCall(request).execute().use { response ->
            try {
                val convertToJson = convertToJson<T>(response)
                mutableLiveData.postValue(NetworkCallModel(convertToJson, response, null))
            } catch (e: Exception) {
                mutableLiveData.postValue(NetworkCallModel(null, null, e))
            }
        }
    }

    inline fun <reified T> convertToJson(response: Response): T {
        return Gson().fromJson<T>(response.body()?.string(), T::class.java)
    }
}