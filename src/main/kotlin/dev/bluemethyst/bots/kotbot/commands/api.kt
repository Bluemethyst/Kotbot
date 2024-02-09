package dev.bluemethyst.bots.kotbot.commands

import me.jakejmattson.discordkt.commands.commands
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import me.jakejmattson.discordkt.arguments.AnyArg

@Serializable
data class JokeResponse(
    val type: String,
    val setup: String,
    val punchline: String,
    val id: Int
)
fun api() = commands("Api") {

    slash("Joke", "Get a random joke!") {
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

    slash("Run-Api", "Test a GET request on a desired API") {
        execute(AnyArg("API-Link")) {
            val client = HttpClient.newBuilder().build()
            val request = HttpRequest.newBuilder()
                .uri(URI.create(args.first))
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())

            respondPublic {
                url = args.first
                title = "Api response"
                description = "```${response.body()}```"
                color = discord.configuration.theme
            }
        }
    }
}