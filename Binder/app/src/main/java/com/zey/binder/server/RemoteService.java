package com.zey.binder.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.zey.binder.Book;
import com.zey.binder.Stub;

import java.util.ArrayList;
import java.util.List;

public class RemoteService extends Service {

    private static final String TAG = "RemoteService";

    private List<Book> books = new ArrayList<>();

    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return bookManager;
    }

    //Stub接收数据
    private Stub bookManager = new Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            Log.i(TAG, "getBooks: " + books);
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Log.i(TAG, "addBook: book: " + book);
            books.add(book);
        }
    };
}
