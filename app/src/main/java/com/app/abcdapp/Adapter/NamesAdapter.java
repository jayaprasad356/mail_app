package com.app.abcdapp.Adapter;

import android.annotation.SuppressLint;
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


public class NamesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final Activity activity;
    ArrayList<GenerateCodes> generateCodes;
    Dialog dialog;
    FindMissingFragment findMissingFragment;
    Session session;

    public NamesAdapter(Activity activity, ArrayList<GenerateCodes> generateCodes, Dialog dialog, FindMissingFragment findMissingFragment) {
        this.activity = activity;
        this.generateCodes = generateCodes;
        this.dialog = dialog;
        this.findMissingFragment = findMissingFragment;
    }





    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.names_list_layout, parent, false);
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


        }


        String codes = cities1.getId_number();
        //String styledText = "<style type='text/css'>body{ color: #ffffff; font-size: 14.60dp; }</style>" + codes;
     //   holder.tvCodes.loadDataWithBaseURL(null, styledText, "text/html", "UTF-8", null);
        holder.tvCodes.setBackgroundColor(0); // Set background color to transparent
        String styledText = "<html><head><style type='text/css'>body { color: #ffffff; font-size: 12dp; }</style></head><body>" + codes + "</body></html>";
        holder.tvCodes.loadDataWithBaseURL("file:///android_asset/", styledText, "text/html", "UTF-8", null);


        String name = cities1.getStudent_name();
//        String styledText1 = "<style type='text/css'>body{ color: #ffffff; font-size: 14.60dp; }</style>" + name;
//        holder.tvName.loadDataWithBaseURL(null, styledText1, "text/html", "UTF-8", null);
        holder.tvName.setBackgroundColor(0);// Set background color to transparent
        String styledText1 = "<html><head><style type='text/css'>body { color: #ffffff; font-size: 12dp; }</style></head><body>" + name + "</body></html>";
        holder.tvName.loadDataWithBaseURL("file:///android_asset/", styledText1, "text/html", "UTF-8", null);




        holder.tvCodes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    dialog.dismiss();
                    findMissingFragment.setNameValue(cities1.getStudent_name());
                }
                return true; // Consume the touch event
            }
        });

        holder.tvName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    dialog.dismiss();
                    findMissingFragment.setNameValue(cities1.getStudent_name());
                }
                return true; // Consume the touch event
            }
        });





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                findMissingFragment.setNameValue(cities1.getStudent_name());

            }
        });





    }


    @Override
    public int getItemCount() {
        return generateCodes.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {


        final WebView tvCodes,tvName;
        final RelativeLayout rlMain;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvCodes = itemView.findViewById(R.id.tvCodes);
            rlMain = itemView.findViewById(R.id.rlMain);

        }
    }
}