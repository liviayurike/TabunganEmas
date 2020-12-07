package com.example.tabunganemas.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity(tableName = "profile")

public class Profile implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "profileId")
    private int id;
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
        id = in.readInt();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama);
        dest.writeString(email);
        dest.writeString(image);
    }
}
