package com.example.exameneventosapp3

import android.os.Parcel
import android.os.Parcelable


data class Pharmacy(
    val id: String = "",
    val title: String = "",
    val telefonos: String? = null,
    val email: String? = null,
    val fax: String? = null,
    val postalCode: String = "",
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val description: String? = null,
    val lastUpdated: String = "",
    val neighborhood: String? = null,
    val additionalInfo: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    // No-argument constructor required for Firestore
    constructor() : this(
        id = "",
        title = "",
        telefonos = null,
        email = null,
        fax = null,
        postalCode = "",
        address = "",
        latitude = 0.0,
        longitude = 0.0,
        description = null,
        lastUpdated = "",
        neighborhood = null,
        additionalInfo = null
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(telefonos)
        parcel.writeString(email)
        parcel.writeString(fax)
        parcel.writeString(postalCode)
        parcel.writeString(address)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeString(description)
        parcel.writeString(lastUpdated)
        parcel.writeString(neighborhood)
        parcel.writeString(additionalInfo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pharmacy> {
        override fun createFromParcel(parcel: Parcel): Pharmacy {
            return Pharmacy(parcel)
        }

        override fun newArray(size: Int): Array<Pharmacy?> {
            return arrayOfNulls(size)
        }
    }
}