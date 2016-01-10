package xyz.davidng.dongaexchange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 1/10/2016.
 */
public class CustomAdapter extends ArrayAdapter<Exchange> {
    Context context;
    ArrayList<Exchange> arrayExchange;
    public CustomAdapter(Context context, int resource, List<Exchange> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayExchange = (ArrayList<Exchange>) objects;
    }

    // Custom listview su dung view holder pattern, han che viec goi findViewById qua nhieu.
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.custom_layout, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.image      = (ImageView) v.findViewById(R.id.imageViewFlag);
            viewHolder.type       = (TextView)v.findViewById(R.id.textViewType);
            viewHolder.muatienmat = (TextView)v.findViewById(R.id.textViewMua);
            viewHolder.bantienmat = (TextView)v.findViewById(R.id.textViewBan);
            v.setTag(viewHolder);
        }
        Exchange ex = arrayExchange.get(position);
        if(ex != null){
            ViewHolder viewHolder = (ViewHolder)v.getTag();
            // dung thu vien icon de hien thi anh GIF: https://github.com/koush/ion
            // picasso or Glide failed here =)
            Ion.with(viewHolder.image).load(ex.getImageurl());
            viewHolder.type.setText(ex.getType());
            viewHolder.muatienmat.setText(ex.getMuatienmat());
            viewHolder.bantienmat.setText(ex.getBantienmat());
        }
        return v;
    }

    static class ViewHolder{
        protected ImageView image;
        protected TextView type;
        protected TextView muatienmat;
        protected TextView bantienmat;
    }
}
