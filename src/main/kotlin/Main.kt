import config.token

import com.elbekd.bot.Bot
import com.elbekd.bot.model.ChatId
import com.elbekd.bot.model.toChatId
import com.elbekd.bot.types.*

fun main(args: Array<String>) {
    val bot = Bot.createPolling(token)

    bot.onCommand("/test") { (msg, _) ->
        bot.sendMessage(
            chatId = msg.chat.id.toChatId(),
            text = "Hello",
        )
    }
    bot.onAnyUpdate { action -> actionController(action, bot) }
    bot.start()
    println(token)
}

suspend fun validateNewMessages(msgText: String, msgID: Long, chatID: ChatId, bot: Bot) {
    println(msgText)

    if ("E-word" in msgText) {
        bot.deleteMessage(chatID, msgID)
        println(1111)
    }
}

suspend fun actionController(action: Update, bot: Bot) {
    var msgText = ""
    var msgID: Long = 0
    var chatId: Long = 0

    if (action is UpdateMessage) {
        msgText = if (action.message.text != null) {
            action.message.text.toString()
        } else {
            action.message.caption.toString()
        }
        msgID = action.message.messageId
        chatId = action.message.chat.id
    } else if (action is UpdateEditedMessage) {
        println(action)

        msgText = if (action.editedMessage.text != null) {
            action.editedMessage.text.toString()
        } else {
            action.editedMessage.caption.toString()
        }
        msgID = action.editedMessage.messageId
        chatId = action.editedMessage.chat.id
    }

    validateNewMessages(msgText, msgID, chatId.toChatId(), bot)
}
