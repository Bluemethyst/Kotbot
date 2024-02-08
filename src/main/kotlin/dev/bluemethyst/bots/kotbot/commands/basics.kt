package dev.bluemethyst.bots.kotbot.commands

import me.jakejmattson.discordkt.commands.commands
import kotlin.math.roundToInt
import dev.kord.common.Color

val globalEmbedColor = Color(0x8c34eb)

fun basics() = commands("Basics") {
    //This block creates a new command called 'Hello'.
    //Descriptions are used for docs and help menus.

    slash("Ping", "Check the bots latency") {
        execute {
            val ping = discord.kord.gateway.averagePing
            val pingInFloat = ping?.inWholeMilliseconds?.toFloat()
            val roundedPing = pingInFloat?.roundToInt()
            respondPublic {
                title = "Bot Latency: ${roundedPing}ms"
                color = globalEmbedColor
            }
        }
    }

    slash("Hello", "A 'Hello World' command") {
        execute { //The 'execute' block is what code will be run when your command is invoked.
            //The 'respond' command can be used to send a message back to the user who ran the command.
            respondPublic("Hello World!")
        }
    }

    slash("Embed", "Create an embed message") {
        execute {
            //You can also respond with a Discord embed.
            respondPublic {
                title = "This is an embed"
                color = globalEmbedColor
            }
        }
    }
}