package com.example.expensesmonthlytracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class AddActivity extends AppCompatActivity {
    DecimalFormat df = new DecimalFormat("####,####0.00");
    EditText petrol,groceries,dining,ewallet,others;
    Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        petrol = findViewById(R.id.spend_petrol);
        groceries = findViewById(R.id.spend_groceries);
        dining = findViewById(R.id.spend_dining);
        ewallet = findViewById(R.id.spend_ewallet);
        others = findViewById(R.id.spend_others);

        add_btn = findViewById(R.id.add_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper DB = new DatabaseHelper(AddActivity.this);

                Intent intent = getIntent();

                String id = intent.getStringExtra("id");
                String petr = petrol.getText().toString();
                String groc = groceries.getText().toString();
                String dini =dining.getText().toString();
                String ewal = ewallet.getText().toString();
                String othe = others.getText().toString();


                if(TextUtils.isEmpty(petr)){
                    Toast.makeText(AddActivity.this, "Please fill in the petrol price", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(TextUtils.isEmpty(groc)){
                        Toast.makeText(AddActivity.this, "Please fill in the grocery price", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(TextUtils.isEmpty(dini)){
                            Toast.makeText(AddActivity.this, "Please fill in the dining price.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(TextUtils.isEmpty(ewal)){
                                Toast.makeText(AddActivity.this, "Please fill in the e-wallet price.", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                if(TextUtils.isEmpty(othe)){
                                    Toast.makeText(AddActivity.this, "Please fill in the others price.", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    //Change the string into double to perform calculation
                                    double str1 = Double.parseDouble(petr);
                                    double str2 = Double.parseDouble(groc);
                                    double str3 = Double.parseDouble(dini);
                                    double str4 = Double.parseDouble(ewal);
                                    double str5 = Double.parseDouble(othe);

                                    double total= str1+str2+str3+str4+str5;
                                    double cb1=0,cb2 = 0,cb3= 0,cb4= 0,cb5= 0;

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
                                    String total_cashback=df.format(res2);
                                    String petrol_cashback=df.format(cb1);
                                    String groceries_cashback=df.format(cb2);
                                    String dining_cashback=df.format(cb3);
                                    String ewallet_cashback=df.format(cb4);
                                    String others_cashback=df.format(cb5);
                                    petr=df.format(str1);
                                    groc=df.format(str2);
                                    dini=df.format(str3);
                                    ewal=df.format(str4);
                                    othe=df.format(str5);
                                    String res=df.format(total);

                                    Boolean add = DB.addRecords(petr,groc,dini,ewal,othe,res,
                                            petrol_cashback,groceries_cashback,dining_cashback,
                                            ewallet_cashback,others_cashback,total_cashback);
                                    DB.close();
                                    if (add == true){
                                        Toast.makeText(AddActivity.this, "Record Added Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent1 = new Intent(AddActivity.this, MainActivity.class);
                                        intent1.putExtra("id",id);
                                        startActivity(intent1);
                                    }
                                    else{
                                        Toast.makeText(AddActivity.this, "Record Add Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                }

            }
        });
    }
}
