package andrewmcglynn86.badjokes

import java.io.InputStreamReader
import java.net.HttpURLConnection

/**
 * Created by amcglynn on 16/09/2017.
 */
class BadJoke (var connection: HttpURLConnection){
    fun getJoke() : String {
        connection.setRequestProperty("Accept", "text/plain")
        connection.requestMethod = "GET"
        connection.connectTimeout = 5000
        connection.readTimeout = 5000

        var inputString: String
        try {
            connection.connect()
            var inputStreamReader = InputStreamReader(connection.inputStream)
            inputString = inputStreamReader.buffered().use {
                it.readText()
            }
        } catch (ex: Exception) {
            inputString = "Could not load joke";
        }

        return inputString
    }
}
