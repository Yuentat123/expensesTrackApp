package com.example.expensesmonthlytracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_btn;
    ImageView empty_image;
    TextView empty_record;
    CustomAdapter customAdapter;
    DatabaseHelper Database;
    ArrayList<String> acc_id,spend_petrol,spend_groceries,spend_dining,spend_ewallet,spend_others,total_spend,
            petrol_cashback,groceries_cashback,dining_cashback,ewallet_cashback,others_cashback,total_cashback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_btn = findViewById(R.id.add_btn);
        empty_image = findViewById(R.id.empty_imageview);
        empty_record = findViewById(R.id.empty_record_text);

        Intent intent = getIntent();

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = intent.getStringExtra("id");
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        Database = new DatabaseHelper(MainActivity.this);
        acc_id = new ArrayList<>();
        spend_petrol= new ArrayList<>();
        spend_groceries = new ArrayList<>();
        spend_dining = new ArrayList<>();
        spend_ewallet = new ArrayList<>();
        spend_others = new ArrayList<>();
        total_spend = new ArrayList<>();
        petrol_cashback = new ArrayList<>();
        groceries_cashback = new ArrayList<>();
        dining_cashback = new ArrayList<>();
        ewallet_cashback = new ArrayList<>();
        others_cashback = new ArrayList<>();
        total_cashback = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, acc_id, spend_petrol,
                spend_groceries,spend_dining,spend_ewallet,spend_others,total_spend,
                petrol_cashback,groceries_cashback,dining_cashback,ewallet_cashback,others_cashback,total_cashback);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        Cursor cursor = Database.readAllRecords();
        if(cursor.getCount() == 0){
            empty_image.setVisibility(View.VISIBLE);
            empty_record.setVisibility(View.VISIBLE);
        }
        else{
            while(cursor.moveToNext()){
                acc_id.add(cursor.getString(0));
                spend_petrol.add(cursor.getString(1));
                spend_groceries.add(cursor.getString(2));
                spend_dining.add(cursor.getString(3));
                spend_ewallet.add(cursor.getString(4));
                spend_others.add(cursor.getString(5));
                total_spend.add(cursor.getString(6));
                petrol_cashback.add(cursor.getString(7));
                groceries_cashback.add(cursor.getString(8));
                dining_cashback.add(cursor.getString(9));
                ewallet_cashback.add(cursor.getString(10));
                others_cashback.add(cursor.getString(11));
                total_cashback.add(cursor.getString(12));
            }
            empty_image.setVisibility(View.GONE);
            empty_record.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all records?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper database = new DatabaseHelper(MainActivity.this);
                database.deleteAllData();
                recreate();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }
}