package com.ifarma.ifarma.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Filter;
import android.widget.Filterable;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.activities.MainActivity;
import com.ifarma.ifarma.fragments.PharmaFragment;
import com.ifarma.ifarma.holders.PharmaHolder;
import com.ifarma.ifarma.holders.ViewHolder;
import com.ifarma.ifarma.model.Pharma;

import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Rafael on 2/19/2017.
 */

public class PharmaSearchAdapter extends RecyclerView.Adapter<PharmaHolder> implements Filterable {

    private ArrayList<Pharma> productList;
    private ArrayList<Pharma> mOriginalValues; // Original Values
    private ArrayList<Pharma> mDisplayedValues;    // Values to be displayed
    private LayoutInflater inflater;
    private View view;
    private Context context;
    private PharmaHolder holder;
    private final PublishSubject<Pharma> onClickSubject = PublishSubject.create();

    public PharmaSearchAdapter(Context context, ArrayList<Pharma> mProductArrayList) {

        this.mOriginalValues = mProductArrayList;
        this.mDisplayedValues = mProductArrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public PharmaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pharma_list_view, parent, false);
        holder = new PharmaHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(PharmaHolder holder, int position) {
        holder.pharmaName.setText(mDisplayedValues.get(position).getName());
        final Pharma pharma = mDisplayedValues.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSubject.onNext(pharma);

                Bundle bundles = new Bundle();
                Pharma pharm = pharma;

                // ensure your object has not null
                if (pharm != null) {
                    bundles.putSerializable("pharm", pharm);
                }


                MainActivity activity = (MainActivity) context;
                PharmaFragment pharmaFragment = new PharmaFragment();

                pharmaFragment.setArguments(bundles);
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        activity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, pharmaFragment);
                fragmentTransaction.commit();
            }
        });
    }

    public Observable<Pharma> getPositionClicks(){
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

                mDisplayedValues = (ArrayList<Pharma>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new Filter.FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Pharma> FilteredArrList = new ArrayList<Pharma>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<Pharma>(mDisplayedValues); // saves the original data in mOriginalValues
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
                        String data = mOriginalValues.get(i).getName();
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
