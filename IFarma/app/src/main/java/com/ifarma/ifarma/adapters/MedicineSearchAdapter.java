package com.ifarma.ifarma.adapters;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.widget.Filter;
import android.widget.Filterable;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.activities.MainActivity;
import com.ifarma.ifarma.fragments.PharmaFragment;
import com.ifarma.ifarma.holders.ViewHolder;
import com.ifarma.ifarma.model.Pharma;
import com.ifarma.ifarma.model.Product;

import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.Toast;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Rafael on 2/19/2017.
 */

public class MedicineSearchAdapter extends RecyclerView.Adapter<ViewHolder> implements Filterable {

    private ArrayList<Product> productList;
    private ArrayList<Product> mOriginalValues; // Original Values
    private ArrayList<Product> mDisplayedValues;    // Values to be displayed
    private LayoutInflater inflater;
    private View view;
    private Context context;
    private ViewHolder holder;
    private final PublishSubject<Product> onClickSubject = PublishSubject.create();


    public MedicineSearchAdapter(Context context, ArrayList<Product> mProductArrayList) {

        this.mOriginalValues = mProductArrayList;
        this.mDisplayedValues = mProductArrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.medName.setText(mDisplayedValues.get(position).getNameProduct());
        holder.medPrice.setText("R$ " + mDisplayedValues.get(position).getPrice() + "");
        holder.medDescription.setText(mDisplayedValues.get(position).getDescription());

        final Product product = mDisplayedValues.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSubject.onNext(product);

                new AlertDialog.Builder(context)
                        .setTitle("Informações do Produto")
                        .setMessage("Nome: " + product.getNameProduct() + "\nLaboratório: " + product.getLab()  + "\nDescrição: " + product.getDescription() + "\nPreço: R$ " + product.getPrice())
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    public Observable<Product> getPositionClicks(){
        return onClickSubject.asObservable();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mDisplayedValues.size();
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

