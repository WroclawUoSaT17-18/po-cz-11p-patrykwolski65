package com.exchange.extchange.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.exchange.extchange.R;
import com.exchange.extchange.network.Rate;

import java.util.List;


public class RatingBaseListAdapter extends BaseAdapter {

    private List<Rate> items;

    public RatingBaseListAdapter(List<Rate> items) {
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rate_base, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Rate currentItem = (Rate) getItem(position);

        viewHolder.itemDescription.setText(currentItem.getCode());

        return convertView;
    }

    private class ViewHolder {
        TextView itemDescription;

        public ViewHolder(View view) {
            itemDescription = view.findViewById(R.id.text_code);
        }
    }
}
