package com.example.rajaongkir.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rajaongkir.R;
import com.example.rajaongkir.adapter.HistoryAdapter;
import com.example.rajaongkir.dao.HistoryContact;
import com.example.rajaongkir.dao.HistoryPresenter;
import com.example.rajaongkir.entity.DataFeedback;
import com.example.rajaongkir.entity.FeedbackDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity implements HistoryContact.view{

    private FeedbackDatabase feedbackDatabase;
    private HistoryPresenter historyPresenter;
    private HistoryAdapter historyAdapter;

    private Button submit;
    private RecyclerView recyclerView;
    private EditText etName, etExpedisi, etFeedback;

    private int id = 0;
    boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        submit = findViewById(R.id.submit);
        etName = findViewById(R.id.etName);
        etExpedisi = findViewById(R.id.etExpedisi);
        etFeedback = findViewById(R.id.etFeedback);
        recyclerView = findViewById(R.id.rc_main);

        feedbackDatabase = FeedbackDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        historyPresenter = new HistoryPresenter(this);
        historyPresenter.readData(feedbackDatabase);
        submit.setOnClickListener(this);
    }

    @Override
    public void successAdd() {
        Toast.makeText(this,"Berhasil", Toast.LENGTH_SHORT).show();
        historyPresenter.readData(feedbackDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this,"Berhasil Menghapus Data",Toast.LENGTH_SHORT).show();
        historyPresenter.readData(feedbackDatabase);
    }

    @Override
    public void resetForm() {
        etName.setText("");
        etExpedisi.setText("");
        etFeedback.setText("");
        submit.setText("Submit");
    }

    @Override
    public void getData(List<DataFeedback> list) {
        historyAdapter = new HistoryAdapter(this,list,this);
        recyclerView.setAdapter(historyAdapter);
    }

    @Override
    public void editData(DataFeedback item) {
        etName.setText(item.getName());
        etExpedisi.setText(item.getExpedisi());
        etFeedback.setText(item.getFeedback());
        id = item.getId();
        edit = true;
        submit.setText("EDIT DATA");
    }

    @Override
    public void deleteData(DataFeedback item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        historyPresenter.deleteData(item,feedbackDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_menu_delete)
                .show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.submit) {
            if (etName.getText().toString().equals("") || etExpedisi.getText().toString().equals("") ||
                    etFeedback.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Harap isi Semua data", Toast.LENGTH_SHORT).show();
            } else {
                if (!edit) {
                    historyPresenter.insertData(etName.getText().toString(), etExpedisi.getText().toString(), etFeedback.getText().toString(), feedbackDatabase);
                } else {
                    historyPresenter.editData(etName.getText().toString(), etExpedisi.getText().toString(), etFeedback.getText().toString(),
                            id, feedbackDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
    }
}