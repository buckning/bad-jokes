package andrewmcglynn86.badjokes.connection

import andrewmcglynn86.badjokes.dto.Joke
import com.fasterxml.jackson.module.kotlin.*
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by amcglynn on 16/09/2017.
 */
class OnlineJokeRepository(val jokeRepositoryUrl: String) : JokeRepository {
    @Throws(IOException::class)
    override fun getJoke() : Joke {
        var jokeUrl = URL(jokeRepositoryUrl)
        var connection = jokeUrl.openConnection() as HttpURLConnection
        connection.setRequestProperty("Accept", "application/json")
        connection.requestMethod = "GET"
        connection.connectTimeout = 5000
        connection.readTimeout = 5000

        var response: Joke
        connection.connect()
        var inputStreamReader = InputStreamReader(connection.inputStream)
        val inputString = inputStreamReader.buffered().use {
            it.readText()
        }

        val mapper = jacksonObjectMapper()

        response = mapper.readValue<Joke>(inputString)

        return response
    }
}
