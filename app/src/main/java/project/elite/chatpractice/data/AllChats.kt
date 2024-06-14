package project.elite.chatpractice.data

data class AllChats(
    val sender: String,
    val time: Long,
    val receiver: String,
    val message: String,
) {
    constructor() : this("", 0, "", "")
}