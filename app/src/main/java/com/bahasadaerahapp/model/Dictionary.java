package com.bahasadaerahapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Dictionary implements Parcelable {

    // create implementation Parcelable to this class
    private String bhsIndo, bhsDaerah;


    public String getBhsIndo() {
        return bhsIndo;
    }

    public void setBhsIndo(String bhsIndo) {
        this.bhsIndo = bhsIndo;
    }

    public String getBhsDaerah() {
        return bhsDaerah;
    }

    public void setBhsDaerah(String bhsDaerah) {
        this.bhsDaerah = bhsDaerah;
    }

    public Dictionary() {
    }

    protected Dictionary(Parcel in) {
        bhsIndo = in.readString();
        bhsDaerah = in.readString();
    }

    public static final Creator<Dictionary> CREATOR = new Creator<Dictionary>() {
        @Override
        public Dictionary createFromParcel(Parcel in) {
            return new Dictionary(in);
        }

        @Override
        public Dictionary[] newArray(int size) {
            return new Dictionary[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bhsIndo);
        parcel.writeString(bhsDaerah);
    }
}
