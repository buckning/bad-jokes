package andrewmcglynn86.badjokes

import android.os.Parcel
import android.os.Parcelable

class JokeResponse (val id: String, val joke: String, val status: Int): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(joke)
        parcel.writeInt(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<JokeResponse> {
        override fun createFromParcel(parcel: Parcel): JokeResponse {
            return JokeResponse(parcel)
        }

        override fun newArray(size: Int): Array<JokeResponse?> {
            return arrayOfNulls(size)
        }
    }
}
