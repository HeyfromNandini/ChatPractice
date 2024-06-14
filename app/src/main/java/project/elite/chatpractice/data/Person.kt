package project.elite.chatpractice.data

import android.os.Parcel
import android.os.Parcelable


data class UserInfo(
    val email: String,
    val image: String,
    val name: String?
) {
    constructor() : this("","", "")
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

