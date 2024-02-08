package dev.bluemethyst.bots.kotbot.commands

import me.jakejmattson.discordkt.commands.commands
import kotlin.math.roundToInt


fun basics() = commands("Basics") {

    slash("Ping", "Check the bots latency") {
        execute {
            val ping = discord.kord.gateway.averagePing
            val pingInFloat = ping?.inWholeMilliseconds?.toFloat()
            val roundedPing = pingInFloat?.roundToInt()
            respondPublic {
                title = "Bot Latency: ${roundedPing}ms"
                color = discord.configuration.theme
            }
        }
    }

    slash("Hello", "A 'Hello World' command") {
        execute {
            respondPublic("Hello World!")
        }
    }

    slash("Embed", "Create an embed message") {
        execute {
            respondPublic {
                title = "This is an embed"
                color = discord.configuration.theme
            }
        }
    }
}