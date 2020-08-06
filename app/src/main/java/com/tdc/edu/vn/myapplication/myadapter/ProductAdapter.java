package com.tdc.edu.vn.myapplication.myadapter;

import android.app.Activity;
import android.content.Context;
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

    public ProductAdapter(Activity context, int resource, ArrayList<ConvertType> types) {
        super(context, resource, types);
        this.context = context;
        this.layoutID = resource;
        this.types = types;
        this.myContext = context;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.product, null, true);

        //Get view
        TextView title = (TextView) row.findViewById(R.id.product_Title);
        TextView subTitle = (TextView) row.findViewById(R.id.subTile);
        ImageView img = (ImageView) row.findViewById(R.id.imageView);

        ConvertType type = types.get(position);

        switch (type.getTypeName()) {
            case "currency":
                title.setText(myContext.getResources().getString(R.string.lbl_currency));
                break;
            case "weight":
                title.setText(myContext.getResources().getString(R.string.lbl_weight));
                break;
            case "distance":
                title.setText(myContext.getResources().getString(R.string.lbl_distance));
                break;
            case "data":
                title.setText(myContext.getResources().getString(R.string.lbl_data));
                break;
            case "time":
                title.setText(myContext.getResources().getString(R.string.lbl_time));
                break;
            case "area":
                title.setText(myContext.getResources().getString(R.string.lbl_area));
                break;
            default:
                title.setText(type.getTypeName());
                break;
        }

        subTitle.setText(type.getSubTitle());
        img.setImageResource(type.getImage());
        return row;
    }
}
