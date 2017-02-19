package com.ifarma.ifarma.adapters;

import android.support.v7.widget.RecyclerView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.model.Product;

import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by Rafael on 2/19/2017.
 */

public class MedicineSearchAdapter extends BaseAdapter implements Filterable {

    private ArrayList<Product> productList;
    private ArrayList<Product> mOriginalValues; // Original Values
    private ArrayList<Product> mDisplayedValues;    // Values to be displayed
    private LayoutInflater inflater;
    private View view;
    private Context context;
    public MedicineSearchAdapter(Context context, ArrayList<Product> mProductArrayList) {

        this.mOriginalValues = mProductArrayList;
        this.mDisplayedValues = mProductArrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDisplayedValues.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            view =  LayoutInflater.from(context)
                    .inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder(view);

        } else {
            view = convertView;
            holder = new ViewHolder(view);
        }

        holder.medName.setText(mDisplayedValues.get(position).getNameProduct());
        holder.medPrice.setText(mDisplayedValues.get(position).getPrice() +"");
        holder.medDescription.setText(mDisplayedValues.get(position).getDescription());
        
        return view;

    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                mDisplayedValues = (ArrayList<Product>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new Filter.FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Product> FilteredArrList = new ArrayList<Product>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<Product>(mDisplayedValues); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).getNameProduct();
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(mOriginalValues.get(i));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

}

