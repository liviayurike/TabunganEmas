package com.example.tabunganemas.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {
    private String nama;
    private String email;
    private String image;

    public Profile() {
    }

    public Profile(String nama, String email, String image) {
        this.nama = nama;
        this.email = email;
        this.image = image;
    }

    protected Profile(Parcel in) {
        nama = in.readString();
        email = in.readString();
        image = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(email);
        parcel.writeString(image);
    }
}
