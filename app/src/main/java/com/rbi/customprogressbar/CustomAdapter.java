package com.rbi.customprogressbar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> Description = new ArrayList<String>();
    private ArrayList<String> Amount = new ArrayList<String>();

    public CustomAdapter(Context context, ArrayList<String> Id, ArrayList<String> Description, ArrayList<String> Amount) {
        this.mContext = context;
        this.Id = Id;
        this.Description = Description;
        this.Amount = Amount;
    }

    @Override
    public int getCount() {
        return Id.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final viewHolder holder;
        databaseHelper = new DatabaseHelper(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_layout, null);
            holder = new viewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.tv_id);
            holder.Description = (TextView) convertView.findViewById(R.id.tv_dis);
            holder.Amount = (TextView) convertView.findViewById(R.id.tv_amt);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.id.setText(Id.get(position));
        holder.Description.setText(Description.get(position));
        holder.Amount.setText(Amount.get(position));
        return convertView;
    }

    public class viewHolder {
        TextView id;
        TextView Description;
        TextView Amount;
    }
}
