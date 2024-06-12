package project.elite.chatpractice.data

import android.os.Parcel
import android.os.Parcelable


data class AllChats(
    val sender: String,
    val time: Long,
    val receiver: String,
    val message: String,
) {
    constructor() : this("", 0, "", "")
}




data class Chat(
    val id:Int,
    val message:String,
    val time:String,
    val direction:Boolean
)

val chatList = listOf(
    Chat(
        1,
        "Hey! How have you been?",
        "12:15 PM",
        true
    ),
    Chat(
        2,
        "Wanna catch up for a beer?",
        "12:17 PM",
        true
    ),
    Chat(
        3,
        "Awesome! Let’s meet up",
        "12:19 PM",
        false
    ),
    Chat(
        4,
        "Can I also get my cousin along? Will that be okay?",
        "12:20 PM",
        false
    ),
    Chat(
        5,
        "Yeah sure! get him too.",
        "12:21 PM",
        true
    ),
    Chat(
        6,
        "Hey! How have you been?",
        "12:15 PM",
        false
    ),
    Chat(
        7,
        "Wanna catch up for a beer?",
        "12:17 PM",
        true
    ),
    Chat(
        8,
        "Awesome! Let’s meet up",
        "12:19 PM",
        false
    ),
    Chat(
        9,
        "Can I also get my cousin along? Will that be okay?",
        "12:20 PM",
        false
    ),
    Chat(
        10,
        "Yeah sure! get him too.",
        "12:21 PM",
        true
    ),
)





data class UserInfo(
    val email: String,
    val name: String,
    val pfp: String?
) {
    constructor() : this("", "", null)
}

data class Person(
    val id: Int = 0,
    val name: String? = "",
    val icon: String? = ""
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}

