package com.tdc.edu.vn.myapplication.myadapter;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdc.edu.vn.myapplication.R;
import com.tdc.edu.vn.myapplication.modals.ConvertType;

import java.util.ArrayList;


public class ProductAdapter extends ArrayAdapter<ConvertType> {
    private final Activity context;
    private int layoutID;
    private ArrayList<ConvertType> types;
    private Context myContext;

    public ProductAdapter(Activity context,int resource,  ArrayList<ConvertType> types){
        super(context, resource, types);
        this.context = context;
        this.layoutID = resource;
        this.types = types;
        this.myContext = context;
    }
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.product,null,true);

        //Get view
        TextView title = (TextView) row.findViewById(R.id.product_Title);
        TextView subTitle = (TextView) row.findViewById(R.id.subTile);
        ImageView img = (ImageView) row.findViewById(R.id.imageView);
        ConvertType type = types.get(position);

        if(type.getTypeName().equals("currency")){
            title.setText(myContext.getResources().getString(R.string.lbl_currency));
        }else if(type.getTypeName().equals("weight")){
            title.setText(myContext.getResources().getString(R.string.lbl_weight));
        }else if(type.getTypeName().equals("data")){
            title.setText(myContext.getResources().getString(R.string.lbl_data));
        }else if(type.getTypeName().equals("distance")){
            title.setText(myContext.getResources().getString(R.string.lbl_distance));
        }else{
            title.setText(type.getTypeName());
        }
        Log.d("TEST" , "VALUE = " + type.getTypeName());
        subTitle.setText(type.getSubTitle());
        img.setImageResource(type.getImage());
        return row;
    }
}
