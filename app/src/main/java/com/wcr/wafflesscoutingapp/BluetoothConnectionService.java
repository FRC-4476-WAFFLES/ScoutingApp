package com.wcr.wafflesscoutingapp;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Target;
import java.nio.charset.Charset;
import java.util.UUID;

public class BluetoothConnectionService {
    private static final String TAG = "BluetoothConnectionServ";
    private static final String appName = "BluetoothScoutData";
    private static final UUID UUID_WAFFLES = UUID.fromString("44764476-4476-4476-8000-00805F9B34FB");

    private final BluetoothAdapter wbtAdapter;
    Context mContext;
    private AcceptThread mWAcceptThread;
    private ConnectThread mWConnectThread;
    private ConnectedThread mWConnectedThread;
    private BluetoothDevice mDevice;
    private UUID deviceUUID;
    ProgressDialog mProgressDailog;

    public BluetoothConnectionService(Context mContext) {
        this.wbtAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mContext = mContext;
        start();
    }

    private class AcceptThread extends Thread{
        //local server socket
        private final BluetoothServerSocket wServerSocket;
        public AcceptThread(){
            BluetoothServerSocket tmp = null;
            //create a new listening server socket
            try {
                tmp = wbtAdapter.listenUsingInsecureRfcommWithServiceRecord(appName, UUID_WAFFLES);
                Log.d(TAG, "Accept Thread: Setting up server using: " + UUID_WAFFLES);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "AcceptThred: IOException: " + e.getMessage());
            }
            wServerSocket = tmp;
        }

        public void run(){
            Log.d(TAG, "run: AcceptThread running...");
            BluetoothSocket socket = null;
            //this is a blocking call and will only return on a successful connection or an exception
            try {
                Log.d(TAG, "run: RFCOM Server socket start...");
                socket = wServerSocket.accept();
                Log.d(TAG, "run: RFCOM server socket accepted connection.");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "AcceptThread IOException: " + e.getMessage());
            }

            if(socket != null){
                connected(socket, mDevice);
            }
            Log.i(TAG, "END AcceptThread ");
        }

        public void cancel(){
            Log.d(TAG, "cancel: Canceling AcceptThread. ");
            try {
                wServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "cancel: Close of AcceptThread serversocket failed. " + e.getMessage());
            }
        }
    }

    private class ConnectThread extends Thread{
        private BluetoothSocket wSocket;
        public ConnectThread(BluetoothDevice device, UUID uuid){
            mDevice = device;
            deviceUUID = uuid;
        }

        public void run(){
            BluetoothSocket tmp = null;
            Log.d(TAG, "RUN, mConnectThread ");

            //Get a bluetoothsocket for a connection with the given bluetooth device

            try {
                Log.d(TAG, "ConnectThread: trying to create InsecureRfcommSocket using UUID: " + UUID_WAFFLES);
                tmp = mDevice.createRfcommSocketToServiceRecord(deviceUUID);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "ConnectThreat: Could not connect to insecurerfcommsocket " + e.getMessage());
            }

            wSocket = tmp;
            //Always cancel discovery b/c it will slow down connection
            wbtAdapter.cancelDiscovery();

            try {
                //make a connection to the socket
                //this is another blocking call
                wSocket.connect();
                Log.d(TAG, "run: ConnectThread connected.");
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    wSocket.close();
                    Log.d(TAG, "run: Closed Socket");
                } catch (IOException e1) {
                    e1.printStackTrace();
                    Log.e(TAG, "mConnectThread: run: Unable to close connection in socket "+ e1.getMessage());
                }
                Log.d(TAG, "run: ConnectThread: could not connect to UUID: "+ UUID_WAFFLES);
            }
            connected(wSocket, mDevice);
        }

        public void cancel(){
            Log.d(TAG, "cancel: Closing Client Socket. ");
            try {
                wSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "cancel: Close of ConnectThread wSocket failed. " + e.getMessage());
            }
        }
    }


    public synchronized void start(){
        Log.d(TAG, "start");
        //cancel threads before starting new ones
        if(mWConnectThread != null){
            mWConnectThread.cancel();
            mWConnectThread = null;
        }

        if(mWAcceptThread == null){
            mWAcceptThread = new AcceptThread();
            mWAcceptThread.start();
        }
    }

    public void startClient(BluetoothDevice device, UUID uuid){
        Log.d(TAG, "startClient: Started.");
        //initprogress Dialog
        mProgressDailog = ProgressDialog.show(mContext, "Connecting Bluetooth", "Please Wait...", true);
        mWConnectThread = new ConnectThread(device, uuid);
        mWConnectThread.start();
    }

    private class ConnectedThread extends Thread{
        private final BluetoothSocket wSocket;
        private final OutputStream wOutputStream;
        private final InputStream wInputStream;

        public ConnectedThread(BluetoothSocket socket){
            Log.d(TAG, "ConnectedThread: starting...");
            wSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            //dismiss dialog box when connection established
            mProgressDailog.dismiss();

            try {
                tmpOut = wSocket.getOutputStream();
                tmpIn = wSocket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            wInputStream = tmpIn;
            wOutputStream = tmpOut;
        }

        public void run(){
            byte[] buffer = new byte[1024];//buffer store for the stream
            int bytes; //returned from read

            while (true){
                //read from the input stream
                try {
                    bytes = wInputStream.read(buffer);
                    String incommingMessage = new String(buffer, 0, bytes);
                    Log.d(TAG, "InputStream: " + incommingMessage);
                } catch (IOException e) {
                    Log.e(TAG, "write: Error reading inputStream. "+ e.getMessage());
                    break;
                }

            }
        }

        //call this from the main activity to send data to the remote device.
        public void write(byte[] bytes){
            String text = new String(bytes, Charset.defaultCharset());
            Log.d(TAG, "write: writing to outputstream: " + text);
            try {
                wOutputStream.write(bytes);
            } catch (IOException e) {
                Log.e(TAG, "write: Error writing to OutputStream. "+ e.getMessage());
            }
        }

        // call this form the main activity to shut down the connection
        public void cancel(){
            try {
                wSocket.close();
            }catch (IOException e){}
        }
    }

    private void connected(BluetoothSocket wSocket, BluetoothDevice mDevice) {
        Log.d(TAG, "connected: Starting...");

        //Start the thread to manage the connection and perform transmission
        mWConnectedThread = new ConnectedThread(wSocket);
        mWConnectedThread.start();
    }

    /*
    write to the connected thread in an unsynchronised manner
    out: the bytes to write
     */
    public void write(byte[] out){
        // Create temporary object
        ConnectedThread r;

        // Synchronize a copy of the ConnectedThread
        Log.d(TAG, "write: Write Called.");
        //perform the write
        mWConnectedThread.write(out);
    }
}
