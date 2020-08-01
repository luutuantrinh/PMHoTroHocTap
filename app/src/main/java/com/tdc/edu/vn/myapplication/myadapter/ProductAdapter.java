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


public class ProductAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] mainTitle;
    private final String[] subTile;
    private final Integer[] imgid;

    public ProductAdapter(Activity context,String[] mainTitle,String[] subTile,Integer[] imgid){
        super(context, R.layout.product,mainTitle);
        this.context = context;
        this.mainTitle = mainTitle;
        this.subTile =subTile;
        this.imgid = imgid;
    }
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.product,null,true);
        TextView title = (TextView) row.findViewById(R.id.produc_Title);
        TextView subTitle = (TextView) row.findViewById(R.id.subTile);
        ImageView img = (ImageView) row.findViewById(R.id.imgaeView);

        title.setText(mainTitle[position]);
        subTitle.setText(subTile[position]);
        img.setImageResource(imgid[position]);

        return row;
    }
}
