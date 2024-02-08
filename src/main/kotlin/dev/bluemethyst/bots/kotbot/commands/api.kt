package dev.bluemethyst.bots.kotbot.commands

import me.jakejmattson.discordkt.commands.commands
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class JokeResponse(
    val type: String,
    val setup: String,
    val punchline: String,
    val id: Int
)
fun api() = commands("Api") {

    slash("Api", "Run an api test") {
        execute {
            val client = HttpClient.newBuilder().build()
            val request = HttpRequest.newBuilder()
                .uri(URI.create("https://official-joke-api.appspot.com/random_joke"))
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())

            val jokeResponse = Json.decodeFromString<JokeResponse>(response.body())

            val jokeMessage = "${jokeResponse.setup}\n${jokeResponse.punchline}"

            respondPublic {
                title = "Api response"
                description = jokeMessage
                color = discord.configuration.theme
            }
        }
    }
}