package com.jjjbazar.smsgateway;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;

public class JjjApp extends Application {
    private static final String TAG = "JjjApp";

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            if (FirebaseApp.getApps(this).isEmpty()) {
                FirebaseApp.initializeApp(this);
            }
        } catch (Exception e) {
            Log.e(TAG, "Firebase init", e);
        }
        try {
            if (AppPrefs.isOnline(this)) {
                ScanScheduler.start(this);
                Log.d(TAG, "Process start → scheduler kept alive");
            }
        } catch (Exception e) {
            Log.e(TAG, "onCreate", e);
        }
    }
}
