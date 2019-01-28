package com.example.assemalturifi.barcodescannerandloadpdf;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //step5

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Timber.d("Cancelled some _TAG_");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Timber.d("Found some shit__TAG__");
                TextView scanned = findViewById(R.id.tvScannedData);
                scanned.setText("Scanned: " + result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    //step3
    public void scanBar(View view) {
        new IntentIntegrator(this).initiateScan();

    }



    //step1
    public void loadPDF(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("https://www.civitas.org.uk/pdf/Rowthorn_Immigration.pdf"), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);

    }
}
