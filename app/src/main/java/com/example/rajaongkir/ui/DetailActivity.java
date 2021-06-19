package com.example.rajaongkir.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.rajaongkir.adapter.ExpedisiAdapter;
import com.example.rajaongkir.R;
import com.example.rajaongkir.data.cost.CostsItem;
import com.example.rajaongkir.service.ExpedisiListener;
import com.example.rajaongkir.service.ExpedisiService;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    TextView tv_kotaAsal,tv_kotaTujuan,tv_kurir,tv_berat;

    String idKotaAsal,idKotaTujuan,NamaKotaAsal,NamaKotaTujuan,berat,kurir;

    RecyclerView rvItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_kotaAsal = findViewById(R.id.tvDetail_kotaAsal);
        tv_kotaTujuan = findViewById(R.id.tvDetail_kotaTujuan);
        tv_berat = findViewById(R.id.tvDetail_weight);
        tv_kurir = findViewById(R.id.tvDetail_expedisi);
        rvItem = findViewById(R.id.rvMain);

        idKotaAsal = getIntent().getStringExtra("idKotaAsal");
        idKotaTujuan = getIntent().getStringExtra("idKotaTujuan");
        NamaKotaAsal = getIntent().getStringExtra("kotaAsal");
        NamaKotaTujuan = getIntent().getStringExtra("kotaTujuan");
        berat = getIntent().getStringExtra("weight");
        kurir = getIntent().getStringExtra("expedisi");

        tv_kotaAsal.setText(NamaKotaAsal);
        tv_kotaTujuan.setText(NamaKotaTujuan);
        tv_kurir.setText(kurir);
        tv_berat.setText(berat + " gram");

        new ExpedisiService().getCostAPI(costListener, idKotaAsal,idKotaTujuan, Integer.parseInt(berat), kurir);
    }

    ExpedisiListener<List<CostsItem>> costListener = new ExpedisiListener<List<CostsItem>>() {
        @Override
        public void onResponse(List<CostsItem> items) {
            final LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
            layout.setOrientation(LinearLayoutManager.VERTICAL);
            rvItem.setLayoutManager(layout);

            Log.d("hasil", String.valueOf(items));

            ExpedisiAdapter adapter = new ExpedisiAdapter(items);
            rvItem.setAdapter(adapter);

        }

        @Override
        public void onFailure(String message) {
            Log.d("pesan","gagal ges");
        }
    };
}