import config.token

import com.elbekd.bot.Bot
import com.elbekd.bot.model.toChatId

fun main(args: Array<String>) {
    val bot = Bot.createPolling(token);

    bot.onCommand("/test") { (msg, _) ->
        bot.sendMessage(
            chatId = msg.chat.id.toChatId(),
            text = "Hello",
        )
    }
    bot.start()
    println(token)
}
