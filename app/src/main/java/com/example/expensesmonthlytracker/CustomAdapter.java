package com.example.expensesmonthlytracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList acc_id,spend_petrol,spend_groceries,spend_dining,spend_ewallet,spend_others,
            total_spend, petrol_cashback,groceries_cashback,dining_cashback,ewallet_cashback,others_cashback
            , total_cashback;

    Animation translate_anim;

    CustomAdapter(Activity activity, Context context, ArrayList acc_id,ArrayList spend_petrol, ArrayList spend_groceries, ArrayList spend_dining, ArrayList spend_ewallet, ArrayList spend_others, ArrayList total_spend,
                  ArrayList petrol_cashback,ArrayList groceries_cashback,ArrayList dining_cashback,ArrayList ewallet_cashback,ArrayList others_cashback, ArrayList total_cashback) {
        this.activity = activity;
        this.context = context;
        this.acc_id = acc_id;
        this.spend_petrol = spend_petrol;
        this.spend_groceries = spend_groceries;
        this.spend_dining = spend_dining;
        this.spend_ewallet = spend_ewallet;
        this.spend_others = spend_others;
        this.total_spend = total_spend;
        this.petrol_cashback = petrol_cashback;
        this.groceries_cashback = groceries_cashback;
        this.dining_cashback = dining_cashback;
        this.ewallet_cashback = ewallet_cashback;
        this.others_cashback = others_cashback;
        this.total_cashback = total_cashback;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.acc_id_text.setText(String.valueOf(acc_id.get(position)));
        holder.spend_petrol_text.setText(String.valueOf(spend_petrol.get(position)));
        holder.spend_groceries_text.setText(String.valueOf(spend_groceries.get(position)));
        holder.spend_dining_text.setText(String.valueOf(spend_dining.get(position)));
        holder.spend_ewallet_text.setText(String.valueOf(spend_ewallet.get(position)));
        holder.spend_others_text.setText(String.valueOf(spend_others.get(position)));
        holder.total_spend_text.setText(String.valueOf(total_spend.get(position)));
        holder.petrol_cashback_text.setText(String.valueOf(petrol_cashback.get(position)));
        holder.groceries_cashback_text.setText(String.valueOf(groceries_cashback.get(position)));
        holder.dining_cashback_text.setText(String.valueOf(dining_cashback.get(position)));
        holder.ewallet_cashback_text.setText(String.valueOf(ewallet_cashback.get(position)));
        holder.others_cashback_text.setText(String.valueOf(others_cashback.get(position)));
        holder.total_cashback_text.setText(String.valueOf(total_cashback.get(position)));
        holder.layout_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(acc_id.get(position)));
                intent.putExtra("petrol", String.valueOf(spend_petrol.get(position)));
                intent.putExtra("groceries", String.valueOf(spend_groceries.get(position)));
                intent.putExtra("dining", String.valueOf(spend_dining.get(position)));
                intent.putExtra("ewallet", String.valueOf(spend_ewallet.get(position)));
                intent.putExtra("others", String.valueOf(spend_others.get(position)));
                intent.putExtra("total", String.valueOf(total_spend.get(position)));
                intent.putExtra("petrolcb", String.valueOf(petrol_cashback.get(position)));
                intent.putExtra("groceriescb", String.valueOf(groceries_cashback.get(position)));
                intent.putExtra("diningcb", String.valueOf(dining_cashback.get(position)));
                intent.putExtra("ewalletcb", String.valueOf(ewallet_cashback.get(position)));
                intent.putExtra("otherscb", String.valueOf(others_cashback.get(position)));
                intent.putExtra("totalcb", String.valueOf(total_cashback.get(position)));

                activity.startActivityForResult(intent,1 );
            }
        });
    }

    @Override
    public int getItemCount() {
        return acc_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView acc_id_text,spend_petrol_text, spend_groceries_text, spend_dining_text, spend_ewallet_text, spend_others_text,total_spend_text,
                petrol_cashback_text,groceries_cashback_text,dining_cashback_text,ewallet_cashback_text,others_cashback_text, total_cashback_text;
        LinearLayout layout_main;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            acc_id_text = itemView.findViewById(R.id.acc_id_text);
            spend_petrol_text = itemView.findViewById(R.id.spend_petrol_text);
            spend_groceries_text= itemView.findViewById(R.id.spend_groceries_text);
            spend_dining_text = itemView.findViewById(R.id.spend_dining_text);
            spend_ewallet_text  = itemView.findViewById(R.id.spend_ewallet_text);
            spend_others_text  = itemView.findViewById(R.id.spend_others_text);
            total_spend_text = itemView.findViewById(R.id.total_spend_text);
            petrol_cashback_text = itemView.findViewById(R.id.petrol_cashback_text);
            groceries_cashback_text = itemView.findViewById(R.id.groceries_cashback_text);
            dining_cashback_text = itemView.findViewById(R.id.dining_cashback_text);
            ewallet_cashback_text = itemView.findViewById(R.id.ewallet_cashback_text);
            others_cashback_text = itemView.findViewById(R.id.others_cashback_text);
            total_cashback_text = itemView.findViewById(R.id.total_cashback_text);

            layout_main = itemView.findViewById(R.id.mainLayout);

            //animate recyclerview
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            layout_main.setAnimation(translate_anim);
        }
    }
}

