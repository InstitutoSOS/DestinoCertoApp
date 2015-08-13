package institutosos.org.br.destinocerto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private static final String TAG = "ScannerActivity";
    private ZXingScannerView _scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _scannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(_scannerView);                // Set the scanner view as the content view
        _scannerView.setFlash(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        _scannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        _scannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        _scannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, "Scanned a barcode:");
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        Intent intent = new Intent(this, PackageActivity.class);
        intent.putExtra(PackageActivity.BARCODE, rawResult.getText());
        startActivity(intent);
    }
}
