package com.zey.binder;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Book implements Parcelable {

    private static final String TAG = "Book";

    private String name;

    private String author;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    //反序列化
    protected Book(Parcel in) {
        Log.i(TAG, "Book: Parcel in");
        this.name = in.readString();
        this.author = in.readString();
    }

    //序列化
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.i(TAG, "writeToParcel: ");
        dest.writeString(name);
        dest.writeString(author);
    }

    @Override
    public int describeContents() {
        Log.i(TAG, "describeContents: ");
        return 0;
    }

    //反序列化
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            Log.i(TAG, "createFromParcel: ");
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            Log.i(TAG, "newArray: ");
            return new Book[size];
        }
    };

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
