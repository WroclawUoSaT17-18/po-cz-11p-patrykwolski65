package com.exchange.extchange.ui.currencyList;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exchange.extchange.R;
import com.exchange.extchange.network.Rate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class RatingsAdapter extends RecyclerView.Adapter<RatingsAdapter.RatingHolder> {

    private final List<Rate> items = new ArrayList<>();

    public void updateItems(List<Rate> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RatingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RatingHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rating, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RatingHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<Rate> getItems() {
        return items;
    }

    static class RatingHolder extends RecyclerView.ViewHolder {

        private TextView textCode;
        private TextView textCurrency;
        private TextView textMid;

        RatingHolder(View itemView) {
            super(itemView);
            textCode = itemView.findViewById(R.id.text_code);
            textCurrency = itemView.findViewById(R.id.text_currency);
            textMid = itemView.findViewById(R.id.text_mid);
        }

        void bind(Rate rating) {
            textCode.setText(rating.getCode());
            textCurrency.setText(rating.getCurrency());
            textMid.setText(String.format(new Locale("pl", "PL"), "1%s = %.4f PLN", rating.getCode(), rating.getMid()));
        }
    }
}
