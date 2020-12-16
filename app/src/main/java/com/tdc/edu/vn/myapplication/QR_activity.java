package com.tdc.edu.vn.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.tdc.edu.vn.myapplication.Model.QRGeoModel;
import com.tdc.edu.vn.myapplication.Model.QRURLModel;
import com.tdc.edu.vn.myapplication.Model.QRVCardModel;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QR_activity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView scannerView;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_layout);

        //init
        scannerView = (ZXingScannerView) findViewById(R.id.zxscan);
        txtResult = (TextView) findViewById(R.id.txt_result);

        //Request permission
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        scannerView.setResultHandler(QR_activity.this);
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(QR_activity.this, "You must accept permission", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();

    }

    @Override
    protected void onDestroy() {
        scannerView.stopCamera();
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
        scannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        //here we can receive rawResult
        processRawResult(rawResult.getText());

    }

    private void processRawResult(String text) {
        if (text.startsWith("BEGIN:")) {
            String[] tokens = text.split("\n");
            QRVCardModel qrvCardModel = new QRVCardModel();
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].startsWith("BEGIN:")) {
                    //Remove BEGIN to get type
                    qrvCardModel.setType(tokens[i].substring("BEGIN:".length()));
                } else if (tokens[i].startsWith("N:")) {
                    //Remove Name to get type
                    qrvCardModel.setName(tokens[i].substring("N:".length()));
                } else if (tokens[i].startsWith("ORG:")) {
                    //Remove ORG to get type
                    qrvCardModel.setOrg(tokens[i].substring("ORG:".length()));
                } else if (tokens[i].startsWith("TEL:")) {
                    //Remove tel to get type
                    qrvCardModel.setTel(tokens[i].substring("TEL:".length()));
                } else if (tokens[i].startsWith("URL:")) {
                    //Remove URL to get type
                    qrvCardModel.setUrl(tokens[i].substring("URL:".length()));
                } else if (tokens[i].startsWith("ADR:")) {
                    //Remove Address to get type
                    qrvCardModel.setAddress(tokens[i].substring("ADR:".length()));
                } else if (tokens[i].startsWith("NOTE:")) {
                    //Remove NOTE to get type
                    qrvCardModel.setNote(tokens[i].substring("NOTE:".length()));
                } else if (tokens[i].startsWith("SUMMARY:")) {
                    //Remove NOTE to get type
                    qrvCardModel.setSummary(tokens[i].substring("SUMMARY:".length()));
                } else if (tokens[i].startsWith("DTSTART:")) {
                    //Remove NOTE to get type
                    qrvCardModel.setDtstart(tokens[i].substring("DTSTART:".length()));
                } else if (tokens[i].startsWith("DTSTART:")) {
                    //Remove DTSTART to get type
                    qrvCardModel.setDtstart(tokens[i].substring("DTSTART:".length()));
                } else if (tokens[i].startsWith("DTEND:")) {
                    //Remove DTEND to get type
                    qrvCardModel.setDtend(tokens[i].substring("DTEND:".length()));
                }

                //Try to show
                txtResult.setText(qrvCardModel.getType());

            }

        } else if (text.startsWith("http://") ||
                text.startsWith("https://") ||
                text.startsWith("www.")) {
            QRURLModel qrurlModel = new QRURLModel(text);
            txtResult.setText(qrurlModel.getUrl());
        } else if (text.startsWith("geo:")) {
            QRGeoModel qrGeoModel = new QRGeoModel();
            String delims = "[ , ?q= ]+";
            String tokens[] = text.split(delims);

            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].startsWith("geo:")) {
                    qrGeoModel.setLat(tokens[i].substring("geo:".length()));
                }
            }
            qrGeoModel.setLat(tokens[0].substring("geo:".length()));
            qrGeoModel.setLng(tokens[1]);
            qrGeoModel.setGeo_place(tokens[2]);
            txtResult.setText(qrGeoModel.getLat() + "/" + qrGeoModel.getLng());


        } else {
            txtResult.setText(text);
        }

        scannerView.resumeCameraPreview(QR_activity.this);
    }
}