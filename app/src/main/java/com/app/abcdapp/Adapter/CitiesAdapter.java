package com.app.abcdapp.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.abcdapp.R;
import com.app.abcdapp.fragment.FindMissingFragment;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.Session;
import com.app.abcdapp.model.GenerateCodes;

import java.util.ArrayList;

import android.annotation.SuppressLint;



public class CitiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final Activity activity;
    ArrayList<GenerateCodes> generateCodes;
    Dialog dialog;
    FindMissingFragment findMissingFragment;
    Session session;

    public CitiesAdapter(Activity activity, ArrayList<GenerateCodes> generateCodes, Dialog dialog,FindMissingFragment findMissingFragment) {
        this.activity = activity;
        this.generateCodes = generateCodes;
        this.dialog = dialog;
        this.findMissingFragment = findMissingFragment;
    }





    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.cities_list_layout, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, @SuppressLint("RecyclerView") int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final GenerateCodes cities1 = generateCodes.get(position);


        session = new Session(activity);

        if (session.getData(Constant.THEME).equals("dark")) {
            holder.rlMain.setBackgroundResource(R.drawable.dark_list_bg);
        } else {
            holder.rlMain.setBackgroundResource(R.drawable.wallet_list_bg);


        }    // backgroud tint rlMain in #2D2B2B


        String codes = cities1.getId_number();
//        String styledText = "<style type='text/css'>body{ color: #ffffff; font-size: 14.60dp; }</style>" + codes;
//        holder.tvMobileNo.loadDataWithBaseURL(null, styledText, "text/html", "UTF-8", null);
        holder.tvMobileNo.setBackgroundColor(0); // Set background color to transparent
        String styledText = "<html><head><style type='text/css'>body { color: #ffffff; font-size: 12dp; }</style></head><body>" + codes + "</body></html>";
        holder.tvMobileNo .loadDataWithBaseURL("file:///android_asset/", styledText, "text/html", "UTF-8", null);



        String name = cities1.getStudent_name();
//        String styledText1 = "<style type='text/css'>body{ color: #ffffff; font-size: 14.60dp; }</style>" + name;
//        holder.tvName.loadDataWithBaseURL(null, styledText1, "text/html", "UTF-8", null);
        holder.tvName.setBackgroundColor(0); // Set background color to transparent
        String styledText1 = "<html><head><style type='text/css'>body { color: #ffffff; font-size: 12dp; }</style></head><body>" + name + "</body></html>";
        holder.tvName.loadDataWithBaseURL("file:///android_asset/", styledText1, "text/html", "UTF-8", null);


        String city = cities1.getEcity();
//        String styledText2 = "<style type='text/css'>body{ color: #ffffff; font-size: 14.60dp; }</style>" + city;
//        holder.tvCity.loadDataWithBaseURL(null, styledText2, "text/html", "UTF-8", null);
        holder.tvCity.setBackgroundColor(0); // Set background color to transparent
        String styledText2 = "<html><head><style type='text/css'>body { color: #ffffff; font-size: 12dp; }</style></head><body>" + city + "</body></html>";
        holder.tvCity.loadDataWithBaseURL("file:///android_asset/", styledText2, "text/html", "UTF-8", null);



        //   Glide.with(activity).load(quotationList.getImage()).placeholder(R.drawable.logo).into(holder.imgProduct);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                findMissingFragment.setCityValue(cities1.getEcity());

            }
        });


        holder.tvCity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    dialog.dismiss();
                    findMissingFragment.setCityValue(cities1.getEcity());
                }
                return true; // Consume the touch event
            }
        });


        holder.tvName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    dialog.dismiss();
                    findMissingFragment.setCityValue(cities1.getEcity());
                }
                return true; // Consume the touch event
            }
        });

        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    dialog.dismiss();
                    findMissingFragment.setCityValue(cities1.getEcity());
                }
                return true; // Consume the touch event
            }
        });


        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    dialog.dismiss();
                    findMissingFragment.setCityValue(cities1.getEcity());
                }
                return true; // Consume the touch event
            }
        });





    }


    @Override
    public int getItemCount() {
        return generateCodes.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final RelativeLayout rlMain;
        final WebView tvMobileNo,tvName ,tvCity;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMobileNo = itemView.findViewById(R.id.tvMobileNo);
            tvCity = itemView.findViewById(R.id.tvCity);
            rlMain = itemView.findViewById(R.id.rlMain);

        }
    }
}