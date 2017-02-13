package com.android_examples.generateqrcode_android_examplescom;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);
        QrScanner();
    }

    public void QrScanner(){


        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);

        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();         // Start camera

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
        finish();
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        mScannerView.stopCamera();
        // show the scanner result into dialog box.
        Intent intent = new Intent();
        String data = rawResult.getText();
        intent.putExtra("content", data);
        setResult(RESULT_OK, intent);
        finish();
        // If you would like to resume scanning, call this method below:
        // mScannerView.resumeCameraPreview(this);
    }
}