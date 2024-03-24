package com.example.rpolab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.rpolab1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'rpolab1' library on application startup.
    static {
        System.loadLibrary("rpolab1");
    }

    private ActivityMainBinding binding;

    static {
        //System.loadLibrary("native-lib");
        System.loadLibrary("mbedcrypto");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());

        int res = initRng();
        byte[] v = randomBytes(10);
        tv.setText(String.valueOf(v));
    }

    /**
     * A native method that is implemented by the 'rpolab1' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public static native int initRng();
    public static native byte[] randomBytes(int no);
}