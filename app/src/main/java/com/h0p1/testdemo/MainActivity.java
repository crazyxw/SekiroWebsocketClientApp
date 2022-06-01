package com.h0p1.testdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.h0p1.api.SekiroWebSocketClient;
import com.h0p1.api.fastjson.JSONObject;
import com.h0p1.api.interfaze.RequestHandler;
import com.h0p1.api.interfaze.SekiroRequest;
import com.h0p1.api.interfaze.SekiroResponse;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SekiroWebSocketClient webSocketClient = null;
        try {
            webSocketClient = new SekiroWebSocketClient(new URI("ws://192.168.1.5:5612/business-demo/register?group=aaa&clientId=android01"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        webSocketClient.registerSekiroHandler("hello", new RequestHandler() {
            public void handleRequest(SekiroRequest sekiroRequest, SekiroResponse sekiroResponse) {
                JSONObject jsonObject = sekiroRequest.getRequestJSONObject();
                jsonObject.put("hello", "i com from android");
                sekiroResponse.send(jsonObject);
            }
        });

    }
}