package com.example.rajaongkir.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rajaongkir.R;
import com.example.rajaongkir.data.city.Results;
import com.example.rajaongkir.service.ExpedisiListener;
import com.example.rajaongkir.service.ExpedisiService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Inisialisasi field pada layout
    EditText et_weight;
    Spinner sp_kotaAsal, sp_kotaTujuan, sp_Expedisi;
    AppCompatButton btn_Submit;
    BottomNavigationView bottom;

    String kotaAsal = "", kotaTujuan = "", idKotaAsal = "", idKotaTujuan = "", expedisi = ";";
    List<String> listIdKota = new ArrayList<String>();
    List<String> listSpinnerKotaAsal = new ArrayList<String>();
    List<String> listSpinnerKotaTujuan = new ArrayList<String>();
    List<String> listExpedisi = new ArrayList<String>();

/*    public void resetForm(){
        et_weight.setText("");
        sp_Expedisi.setSelection(0);
        sp_kotaAsal.setSelection(0);
        sp_kotaTujuan.setSelection(0);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Submit = findViewById(R.id.btn_submit);
        et_weight = findViewById(R.id.et_Weight);
        sp_kotaAsal = findViewById(R.id.sp_kotaAsal);
        sp_kotaTujuan = findViewById(R.id.sp_kotaTujuan);
        sp_Expedisi = findViewById(R.id.sp_Expedisi);
        bottom = findViewById(R.id.menu_bawah);

        new ExpedisiService().getCityAPI(cityListener);

        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.cekOgkir :
                        Intent home = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(home);
                        finish();
                        break;
                    case R.id.feedback :
                        Intent feedback = new Intent(MainActivity.this, HistoryActivity.class);
                        startActivity(feedback);
                        finish();
                        break;
                }
                return true;
            }
        });

        sp_kotaAsal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                kotaAsal = adapterView.getItemAtPosition(position).toString();
                idKotaAsal = listIdKota.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_kotaTujuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                kotaTujuan = adapterView.getItemAtPosition(position).toString();
                idKotaTujuan = listIdKota.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_Expedisi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                expedisi = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_Submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et_weight.getText().toString())){
                    et_weight.setError("Berat tidak boleh kosong!");
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("kotaAsal",kotaAsal);
                    intent.putExtra("kotaTujuan",kotaTujuan);
                    intent.putExtra("idKotaAsal",idKotaAsal);
                    intent.putExtra("idKotaTujuan",idKotaTujuan);
                    intent.putExtra("weight",et_weight.getText().toString());
                    intent.putExtra("expedisi",expedisi);
                    startActivity(intent);
//                    finish();
                }
            }

        });

    }

    ExpedisiListener<List<Results>> cityListener = new ExpedisiListener<List<Results>>() {
        @Override
        public void onResponse(List<Results> items) {

            for(int i=0;i<items.size();i++){
                listSpinnerKotaAsal.add(items.get(i).getCityName());
                listSpinnerKotaTujuan.add(items.get(i).getCityName());
                listIdKota.add(items.get(i).getCityId());
            }

            listExpedisi.add("jne");
            listExpedisi.add("pos");
            listExpedisi.add("tiki");

            ArrayAdapter<String> adapterKotaAsal = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listSpinnerKotaAsal);
            adapterKotaAsal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_kotaAsal.setAdapter(adapterKotaAsal);

            ArrayAdapter<String> adapterKotaTujuan = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listSpinnerKotaTujuan);
            adapterKotaTujuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_kotaTujuan.setAdapter(adapterKotaTujuan);

            ArrayAdapter<String> adapterKurir = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listExpedisi);
            adapterKurir.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_Expedisi.setAdapter(adapterKurir);

        }

        @Override
        public void onFailure(String message) {
            Log.d("isi Error : ", message);
        }
    };
}