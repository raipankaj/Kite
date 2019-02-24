package lib.pankaj.kfnet.data

import okhttp3.Response

data class NetworkCallModel<out T>(val model: T?, val rawResponse: Response?, val exception: Exception?)
