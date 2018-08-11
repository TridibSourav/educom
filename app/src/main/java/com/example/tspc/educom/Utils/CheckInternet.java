package com.masud.dev.examresultsbd.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.graphics.drawable.PathInterpolatorCompat;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;

public class CheckInternet extends AsyncTask<String, Boolean, Boolean> {

    private Context mContext;
    private Boolean status=false;

    public CheckInternet(Context mContext) {
        this.mContext = mContext;
    }
    @Override
    protected Boolean doInBackground(String... strings) {
        if (CheckConnectivity() && isOnline()){
            status=true;
        }
        return status;
    }

    private boolean isOnline() {
        try {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) { return false; }
    }
    private boolean CheckConnectivity() {
         boolean ConnectionStatus=false;
        try {
            NetworkInfo networkInfo = ((ConnectivityManager) this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            ConnectionStatus= networkInfo != null && networkInfo.isConnected();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ConnectionStatus;
    }
}
