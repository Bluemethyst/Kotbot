package dev.bluemethyst.bots.kotbot.music

import me.jakejmattson.discordkt.arguments.AnyArg
import me.jakejmattson.discordkt.commands.commands


fun music() = commands("Music") {

    slash("Play", "Play a Spotify or YouTube link") {
        execute(AnyArg("Link")) {
            val link = args.first



            respondPublic {
                title = "Playing"
                description = link
                color = discord.configuration.theme
            }
        }
    }
}