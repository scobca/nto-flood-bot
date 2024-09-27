import config.token

import com.elbekd.bot.Bot
import com.elbekd.bot.model.toChatId
import com.elbekd.bot.types.Message

fun main(args: Array<String>) {
    val bot = Bot.createPolling(token);

    bot.onCommand("/test") { (msg, _) ->
        bot.sendMessage(
            chatId = msg.chat.id.toChatId(),
            text = "Hello",
        )
    }
    bot.onMessage { message -> checkNewMessages(message, bot) }
    bot.start()
    println(token)
}

suspend fun checkNewMessages(msg: Message, bot: Bot) {
    val msgID = msg.messageId
    val msgText = msg.text
    val chatID = msg.chat.id.toChatId()

    bot.deleteMessage(chatID, msgID);
}
