package com.example.expensesmonthlytracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class UpdateActivity extends AppCompatActivity {

    DecimalFormat df = new DecimalFormat("####,####0.00");
    EditText petrol_input, groceries_input, dining_input, ewallet_input, others_input,total_input;
    ImageView reset_petrol, reset_groceries,reset_dining, reset_ewallet, reset_others;
    Button update_btn, delete_btn;


    String id, petrol,groceries,dining,ewallet,others,total,petrolcb,groceriescb,diningcb,ewalletcb,otherscb,totalcb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        petrol_input= findViewById(R.id.spend_petrol2);
        groceries_input= findViewById(R.id.spend_groceries2);
        dining_input= findViewById(R.id.spend_dining2);
        ewallet_input= findViewById(R.id.spend_ewallet2);
        others_input= findViewById(R.id.spend_others2);
        total_input= findViewById(R.id.total_spend2);
        update_btn = findViewById(R.id.update_btn);
        delete_btn = findViewById(R.id.delete_btn);

        reset_petrol = findViewById(R.id.reset_petrol);
        reset_groceries = findViewById(R.id.reset_groceries);
        reset_dining = findViewById(R.id.reset_dining);
        reset_ewallet = findViewById(R.id.reset_ewallet);
        reset_others = findViewById(R.id.reset_others);

        //get the data from the intent
        id = getIntent().getStringExtra("id");
        petrol = getIntent().getStringExtra("petrol");
        groceries = getIntent().getStringExtra("groceries");
        dining = getIntent().getStringExtra("dining");
        ewallet = getIntent().getStringExtra("ewallet");
        others = getIntent().getStringExtra("others");
        total = getIntent().getStringExtra("total");
        petrolcb = getIntent().getStringExtra("petrolcb");
        groceriescb = getIntent().getStringExtra("groceriescb");
        diningcb = getIntent().getStringExtra("diningcb");
        ewalletcb = getIntent().getStringExtra("ewalletcb");
        otherscb = getIntent().getStringExtra("otherscb");
        totalcb = getIntent().getStringExtra("totalcb");


        //setting intent data
        petrol_input.setText(petrol);
        groceries_input.setText(groceries);
        dining_input.setText(dining);
        ewallet_input.setText(ewallet);
        others_input.setText(others);
        total_input.setText(total);

        reset_petrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String petrol = petrol_input.getText().toString();
                double str = Double.parseDouble(petrol);
                double petrol_reset = str - str;
                petrol_input.setText("0.00");
                Toast.makeText(UpdateActivity.this, "Reset Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        reset_groceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String groceries = groceries_input.getText().toString();
                double str = Double.parseDouble(groceries);
                double groceries_reset = str - str;
                groceries_input.setText("0.00");
                Toast.makeText(UpdateActivity.this, "Reset Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        reset_dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dining = dining_input.getText().toString();
                double str = Double.parseDouble(dining);
                double dining_reset = str - str;
                dining_input.setText("0.00");
                Toast.makeText(UpdateActivity.this, "Reset Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        reset_ewallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ewallet = ewallet_input.getText().toString();
                double str = Double.parseDouble(ewallet);
                double ewallet_reset = str - str;
                ewallet_input.setText("0.00");
                Toast.makeText(UpdateActivity.this, "Reset Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        reset_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String others = others_input.getText().toString();
                double str = Double.parseDouble(others);
                double others_reset = str - str;
                others_input.setText("0.00");
                Toast.makeText(UpdateActivity.this, "Reset Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        //set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(id);
        }

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the input
                String petrol_update = petrol_input.getText().toString();
                String groceries_update = groceries_input.getText().toString();
                String dining_update = dining_input.getText().toString();
                String ewallet_update = ewallet_input.getText().toString();
                String others_update = others_input.getText().toString();

                //Change the string into double to perform calculation
                double str1 = Double.parseDouble(petrol_update);
                double str2 = Double.parseDouble(groceries_update);
                double str3 = Double.parseDouble(dining_update);
                double str4 = Double.parseDouble(ewallet_update);
                double str5 = Double.parseDouble(others_update);
                double total= str1+str2+str3+str4+str5;

                double cb1=0,cb2=0,cb3=0,cb4=0,cb5=0;

                //Compare the total and calculate the cashback for each category
                if(total>2000.00){
                    cb1 = (str1*8)/100;
                    cb2 = (str2*8)/100;
                    cb3 = (str3*0.2)/100;
                    cb4 = (str4*8)/100;
                    cb5 = (str5*0.2)/100;
                }
                else if (total<2000.00){
                    cb1 = (str1*1)/100;
                    cb2 = (str2*1)/100;
                    cb3 = (str3*0.2)/100;
                    cb4 = (str4*1)/100;
                    cb5 = (str5*0.2)/100;
                }

                //Calculate total cashback
                double res2= cb1+cb2+cb3+cb4+cb5;

                //Change double into two decimal string before store into SQLite database
                String petrol_cashback_update=df.format(cb1);
                String groceries_cashback_update=df.format(cb2);
                String dining_cashback_update=df.format(cb3);
                String ewallet_cashback_update=df.format(cb4);
                String others_cashback_update=df.format(cb5);
                String total_cashback_update=df.format(res2);;
                petrol_update=df.format(str1);
                groceries_update=df.format(str2);
                dining_update=df.format(str3);
                ewallet_update=df.format(str4);
                others_update=df.format(str5);
                String res=df.format(total);

                //and only then we call this
                DatabaseHelper DB = new DatabaseHelper(UpdateActivity.this);
                Boolean update = DB.updateData(id, petrol_update,groceries_update,dining_update,ewallet_update,others_update,res,
                        petrol_cashback_update,groceries_cashback_update,dining_cashback_update,ewallet_cashback_update,others_cashback_update,total_cashback_update);
                if(update == true){
                    Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    Toast.makeText(UpdateActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UpdateActivity.this, "Data Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete record [" + id + "] ?");
        builder.setMessage("Are you sure you want to delete record " + id + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper DB = new DatabaseHelper(UpdateActivity.this);
                Boolean delete = DB.deleteOneRow(id);
                if (delete == true) {
                    Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    Toast.makeText(UpdateActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateActivity.this, "Failed To Delete", Toast.LENGTH_SHORT).show();
                }
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