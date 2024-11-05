package pe.gob.fondepes.demo.portal.certificaciones.classes

import android.os.Parcel
import android.os.Parcelable

data class Certificate(
    val title: String,
    val description: String,
    val date: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Certificate> {
        override fun createFromParcel(parcel: Parcel): Certificate {
            return Certificate(parcel)
        }

        override fun newArray(size: Int): Array<Certificate?> {
            return arrayOfNulls(size)
        }
    }
}
