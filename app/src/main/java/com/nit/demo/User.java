package com.nit.demo;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String name;
    private String time;

    public User()
    {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name, String time)
    {
        this.name = name;
        this.time = time;
    }

    public String getName(){

        return name;
    }

    public void setName(String Name)
    {
        name = Name;
    }
    public String getTime(){
        return time;
    }

    public void setTime(String Time){
        time = Time;
    }

    public User(Parcel parcel)
    {
        name = parcel.readString();
        time = parcel.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(name);
        parcel.writeString(time);

    }
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>()
    {
        public User createFromParcel(Parcel in)
        {
            return new User(in);
        }
        public User[] newArray(int size)
        {
            return new User[size];
        }
    };

}
