package dev.bluemethyst.bots.kotbot

import dev.kord.gateway.ALL
import dev.kord.gateway.Intents
import dev.kord.gateway.PrivilegedIntent
import kotlinx.coroutines.flow.toList
import me.jakejmattson.discordkt.dsl.bot
import io.github.cdimascio.dotenv.dotenv
import me.jakejmattson.discordkt.dsl.CommandException
import me.jakejmattson.discordkt.dsl.ListenerException


val embedColor = java.awt.Color(0x8c34eb)

@OptIn(PrivilegedIntent::class)
fun main(args: Array<String>) {
    val token = dotenv()

    bot(token["TOKEN"]) {
        presence {
            playing("In Intellij")
        }
        onStart {
            val guilds = kord.guilds.toList()
            println("Guilds: ${guilds.joinToString { it.name }}")
        }
        configure {
            mentionAsPrefix = true
            logStartup = true
            documentCommands = true
            theme = embedColor
            intents = Intents.ALL
        }
        onException {
            if (exception is IllegalArgumentException)
                return@onException

            when (this) {
                is CommandException -> println("Exception '${exception::class.simpleName}' in command ${event.command?.name}")
                is ListenerException -> println("Exception '${exception::class.simpleName}' in listener ${event::class.simpleName}")
            }
        }
    }


}


