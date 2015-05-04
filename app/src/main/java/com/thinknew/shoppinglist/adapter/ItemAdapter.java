package com.thinknew.shoppinglist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.thinknew.shoppinglist.MainActivity;
import com.thinknew.shoppinglist.R;
import com.thinknew.shoppinglist.data.Item;

/**
 * Created by Magisus on 3/11/2015.
 */
public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeItem(int position) {
        items.remove(position);
    }

    public void updateItem(int index, Item item) {
        items.set(index, item);
    }

    public void removeAll() {
        items.clear();
    }

    public double getTotal(){
        double total = 0;
        for(Item item : items){
            total += item.getPriceEstimate();
        }
        return total;
    }

    public Iterator<Item> getIterator(){
        return items.iterator();
    }

    public void sortByType() {
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item lhs, Item rhs) {
                if(lhs.getType().getValue() >= rhs.getType().getValue()){
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    public void sortByName(){
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item lhs, Item rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
    }

    public void sortByCost() {
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item lhs, Item rhs) {
                return Double.compare(lhs.getPriceEstimate(), rhs.getPriceEstimate());
            }
        });
    }

    private class ViewHolder {
        TextView name;
        ImageView icon;
        TextView amountEstimate;
        CheckBox purchased;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.list_row, null);
            ViewHolder holder = new ViewHolder();
            holder.name = (TextView) v.findViewById(R.id.nameText);
            holder.icon = (ImageView) v.findViewById(R.id.iconImage);
            holder.amountEstimate = (TextView) v.findViewById(R.id.estAmountText);
            holder.purchased = (CheckBox) v.findViewById(R.id.purchasedCheckBox);
            v.setTag(holder);
        }
        final Item item = items.get(position);
        if (item != null) {
            ViewHolder holder = (ViewHolder) v.getTag();
            holder.name.setText(item.getName());
            holder.icon.setImageResource(item.getType().getIconId());
            holder.amountEstimate.setText(context.getString(R.string.currency_symbol) + String
                    .format("%1$,.2f",
                    item.getPriceEstimate()));
            holder.purchased.setChecked(item.isPurchased());
            holder.purchased.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setPurchased(((CheckBox) v).isChecked());
                }
            });

        }
        return v;
    }
}
