package andrewmcglynn86.badjokes.connection

import andrewmcglynn86.badjokes.dto.JokeResponse
import com.fasterxml.jackson.module.kotlin.*
import java.io.InputStreamReader
import java.net.HttpURLConnection

/**
 * Created by amcglynn on 16/09/2017.
 */
class BadJoke (var connection: HttpURLConnection){
    fun getJoke() : JokeResponse {
        connection.setRequestProperty("Accept", "application/json")
        connection.requestMethod = "GET"
        connection.connectTimeout = 5000
        connection.readTimeout = 5000

        var response: JokeResponse
        try {
            connection.connect()
            var inputStreamReader = InputStreamReader(connection.inputStream)
            val inputString = inputStreamReader.buffered().use {
                it.readText()
            }

            val mapper = jacksonObjectMapper()

            response = mapper.readValue<JokeResponse>(inputString)
        } catch (ex: Exception) {
            response = JokeResponse("0000000", "Could not load joke", 500)
        }

        return response
    }
}
