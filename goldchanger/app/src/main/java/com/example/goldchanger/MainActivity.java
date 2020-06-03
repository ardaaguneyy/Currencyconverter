package com.example.goldchanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText isim;
    Boolean ilkgiris;
    SharedPreferences sharedPreferences;
    String kayıt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("com.example.dovz", Context.MODE_PRIVATE);
        ilkgiris = sharedPreferences.getBoolean("giriskontrol",true);
        kayıt = sharedPreferences.getString("isimkayıt","");

        isim = findViewById(R.id.editText);
        if (ilkgiris==false){
            isim.setText(kayıt);
            isim.setEnabled(false);
        }
    }
    public void button(View view){
        String isimstring = isim.getText().toString();
        if (!isimstring.matches("")) {


            sharedPreferences.edit().putString("isimkayıt",isimstring).apply();
            sharedPreferences.edit().putBoolean("giriskontrol", false).apply();
            isim.setText(isimstring);
            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"İSMİNİZİ GİRİNİZ",Toast.LENGTH_SHORT).show();
        }

    }
}
