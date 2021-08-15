package com.example.biometricauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
TextView textView;private BiometricPrompt biometricPrompt;
BiometricPrompt.PromptInfo promptInfo;private Executor executor; @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);textView=(TextView)findViewById(R.id.text);
        try { biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString); textView.setText("Error");
                }@Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result); textView.setText("Success");
                }@Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed(); textView.setText("Failure"); }});
        }catch (IllegalArgumentException e){
            e.printStackTrace(); }
        promptInfo=new BiometricPrompt.PromptInfo.Builder().setTitle("Programmers World Authentication")
                .setNegativeButtonText("Cancel").setConfirmationRequired(false).build();
    } public void auth(View view) {
        BiometricManager biometricManager=BiometricManager.from(this);
        if (biometricManager.canAuthenticate()!=BiometricManager.BIOMETRIC_SUCCESS){
            textView.setText("Biometric not supported");
            return; }
        biometricPrompt.authenticate(promptInfo); }
        public void clickIn(View view) { Intent intent=new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent); }}