package com.example.expensesmonthlytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME="Cash_Back.db"; //database's name
    private Context context;

    DatabaseHelper(Context context) {
        super(context, "Cash_Back.db", null, 1);
    }

    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table records(id INTEGER primary key AUTOINCREMENT, spend_petrol TEXT, " +
                "spend_groceries TEXT, spend_dining TEXT, spend_ewallet TEXT, spend_others TEXT, total_spend TEXT," +
                "petrol_cashback TEXT, groceries_cashback TEXT, dining_cashback TEXT, ewallet_cashback,others_cashback TEXT, total_cashback TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("drop table if exists records");
    }

    public Boolean addRecords(String petrol, String groceries, String dining, String ewallet, String others,String total,
                              String petrol_cashback,String groceries_cashback, String dining_cashback,String ewallet_cashback, String others_cashback
            ,String total_cashback) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cont = new ContentValues();

        cont.put("spend_petrol", petrol);
        cont.put("spend_groceries", groceries);
        cont.put("spend_dining", dining);
        cont.put("spend_ewallet", ewallet);
        cont.put("spend_others", others);
        cont.put("total_spend", total);
        cont.put("petrol_cashback", petrol_cashback);
        cont.put("groceries_cashback", groceries_cashback);
        cont.put("dining_cashback", dining_cashback);
        cont.put("ewallet_cashback", ewallet_cashback);
        cont.put("others_cashback", others_cashback);
        cont.put("total_cashback", total_cashback);

        long result = database.insert("records", null, cont);
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    Cursor readAllRecords(){
        String query = "SELECT * FROM records";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public Boolean updateData(String id,String petrol, String groceries, String dining, String ewallet, String others,String total,
                              String petrol_cashback,String groceries_cashback, String dining_cashback,String ewallet_cashback,
                              String others_cashback, String total_cashback){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cont = new ContentValues();

        cont.put("spend_petrol", petrol);
        cont.put("spend_groceries", groceries);
        cont.put("spend_dining", dining);
        cont.put("spend_ewallet", ewallet);
        cont.put("spend_others", others);
        cont.put("total_spend", total);
        cont.put("petrol_cashback", petrol_cashback);
        cont.put("groceries_cashback", groceries_cashback);
        cont.put("dining_cashback", dining_cashback);
        cont.put("ewallet_cashback", ewallet_cashback);
        cont.put("others_cashback", others_cashback);
        cont.put("total_cashback", total_cashback);

        long result = database.update("records", cont, "id=?", new String[] {id});
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean deleteOneRow(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.delete("records", "id=?", new String[] {id});
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    void deleteAllData() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM records");
    }
}


