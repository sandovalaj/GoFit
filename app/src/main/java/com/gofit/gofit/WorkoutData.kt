import android.os.Parcelable
import android.os.Parcel

data class Workout(
    val id: Int,
    val img: String,
    val name: String,
    val description: String,
    val repetitions: String,
    val duration: Int,
    val met: Double) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(img)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(repetitions)
        parcel.writeInt(duration)
        parcel.writeDouble(met)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Workout> {
        override fun createFromParcel(parcel: Parcel): Workout {
            return Workout(parcel)
        }

        override fun newArray(size: Int): Array<Workout?> {
            return arrayOfNulls(size)
        }
    }
}