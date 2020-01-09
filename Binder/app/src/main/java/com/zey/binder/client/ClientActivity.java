package com.zey.binder.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zey.binder.Book;
import com.zey.binder.BookManager;
import com.zey.binder.R;
import com.zey.binder.Stub;

public class ClientActivity extends AppCompatActivity {

    private static final String TAG = "ClientActivity";

    private BookManager bookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(v -> {
            if (bookManager == null) {
                return;
            }

            try {
                Book book = new Book("《文明》", "张三");
                //Proxy发送数据
                bookManager.addBook(book);

                Log.i(TAG, bookManager.getBooks().toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        bindService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.zey.binder", "com.zey.binder.server.RemoteService"));
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "onServiceConnected: ");
            //Proxy
            bookManager = Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected: ");
            bookManager = null;
        }
    };
}
