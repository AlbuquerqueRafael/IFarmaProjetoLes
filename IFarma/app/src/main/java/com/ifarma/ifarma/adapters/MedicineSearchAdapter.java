package com.ifarma.ifarma.adapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Filter;
import android.widget.Filterable;

import com.google.firebase.auth.FirebaseUser;
import com.ifarma.ifarma.R;
import com.ifarma.ifarma.activities.MainActivity;
import com.ifarma.ifarma.controllers.AuthenticationController;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnPharmaGetDataListener;
import com.ifarma.ifarma.fragments.user.CartFragment;
import com.ifarma.ifarma.fragments.user.UserFragment;
import com.ifarma.ifarma.holders.ViewHolder;
import com.ifarma.ifarma.model.Pharma;
import com.ifarma.ifarma.model.Product;
import com.ifarma.ifarma.services.AdapterService;
import com.ifarma.ifarma.services.CartService;
import com.ifarma.ifarma.util.Utils;

import java.util.ArrayList;
import java.util.List;

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
    private boolean _isSelecting;
    private List<Product> _selectedProducts;
    private FloatingActionButton addToCart;
    private View rootView;
    private final PublishSubject<Product> onClickSubject = PublishSubject.create();

    private boolean isPharmacy = false;
    public static final String FLAG_EMAIL = "currentEmail";

    public MedicineSearchAdapter(Context context, ArrayList<Product> mProductArrayList, View rootView, boolean isPharmacy) {

        this.mOriginalValues = mProductArrayList;
        this.mDisplayedValues = mProductArrayList;
        this.context = context;
        this.rootView = rootView;
        this.isPharmacy = isPharmacy;
        this.addToCart = (FloatingActionButton) rootView.findViewById(R.id.add_to_cart);

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        _selectedProducts = new ArrayList<Product>();

        holder.medName.setText(mDisplayedValues.get(position).getNameProduct());
        holder.medPrice.setText("R$ " + String.format("%.2f", mDisplayedValues.get(position).getPrice()));
        holder.medDescription.setText(mDisplayedValues.get(position).getDescription());
        holder.medPharmacy.setText(mDisplayedValues.get(position).getPharmacyName());

        final Product product = mDisplayedValues.get(position);

        if (isPharmacy) {
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                for (int i = 0; i < _selectedProducts.size(); i++) {
                    if (CartService.addToCart(_selectedProducts.get(i))){
                        AdapterService.reloadAdapter(1);
                    } else {
                        Toast.makeText(context, "Você já possui este item no seu carrinho! :)", Toast.LENGTH_SHORT).show();
                    }
                }
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!_isSelecting){
                        onClickSubject.onNext(product);

                        new AlertDialog.Builder(context)
                                .setTitle("Informações do Produto")
                                .setMessage("Nome: " + product.getNameProduct() + "\nLaboratório: " + product.getLab() + "\nDescrição: " + product.getDescription()
                                        + "\nFarmácia: " + product.getPharmacyName() + "\nPreço: R$ " + String.format("%.2f", product.getPrice()))
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                    } else {
                        if (!_selectedProducts.contains(product)) {
                            _selectedProducts.add(product);
                            v.setBackgroundColor(Color.parseColor("#cbedde"));
                        } else {
                            _selectedProducts.remove(product);
                            v.setBackgroundColor(Color.parseColor("#ffffff"));
                        }

                        _isSelecting = !_selectedProducts.isEmpty();

                        if (_isSelecting){
                            addToCart.setVisibility(View.VISIBLE);
                            addToCart.animate().scaleX(1f).scaleY(1f).start();
                        } else {
                            addToCart.setVisibility(View.GONE);
                            addToCart.animate().scaleX(0f).scaleY(0f).start();
                        }

                    }
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    if (!_selectedProducts.contains(product)) {
                        _selectedProducts.add(product);
                        v.setBackgroundColor(Color.parseColor("#cbedde"));
                    } else {
                        _selectedProducts.remove(product);
                        v.setBackgroundColor(Color.parseColor("#ffffff"));
                    }

                    _isSelecting = !_selectedProducts.isEmpty();

                    if (_isSelecting){
                        addToCart.setVisibility(View.VISIBLE);
                        addToCart.animate().scaleX(1f).scaleY(1f).start();
                    } else {
                        addToCart.setVisibility(View.GONE);
                        addToCart.animate().scaleX(0f).scaleY(0f).start();
                    }

                    return true;
                }
            });

        }else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!_isSelecting){
                        onClickSubject.onNext(product);

                        new AlertDialog.Builder(context)
                                .setTitle("Informações do Produto")
                                .setMessage("Nome: " + product.getNameProduct() + "\nLaboratório: " + product.getLab() + "\nDescrição: " + product.getDescription()
                                        + "\nFarmácia: " + product.getPharmacyName() + "\nPreço: R$ " + String.format("%.2f", product.getPrice()))
                                .setNegativeButton("Excluir ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        new AlertDialog.Builder(context)
                                                .setTitle("Tem certeza que deseja excluir " + product.getNameProduct() + " ?")
                                                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        FirebaseController.removeProduct(Utils.convertEmail(product.getPharmacyId()), product.getNameProduct());
                                                        Toast.makeText(context, "Produto excluído!", Toast.LENGTH_SHORT).show();
                                                        AdapterService.reloadAdapter(0);
                                                    }
                                                }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        }).show();

                                    }
                                })
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                    }
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (!_selectedProducts.contains(product)) {
                        _selectedProducts.add(product);
                       v.setBackgroundColor(Color.parseColor("#cbedde"));
                   } else {
                       _selectedProducts.remove(product);
                        v.setBackgroundColor(Color.parseColor("#ffffff"));
                    }

                    _isSelecting = !_selectedProducts.isEmpty();

                   if (_isSelecting){
                       if(addToCart != null){
                           addToCart.setVisibility(View.VISIBLE);
                           addToCart.animate().scaleX(1f).scaleY(1f).start();
                       }

                    } else {
                        addToCart.setVisibility(View.GONE);
                       addToCart.animate().scaleX(0f).scaleY(0f).start();
                    }

                    return true;
                }
            });
        }



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

    private boolean isPharmacy(){
        Intent intent = ((Activity) context).getIntent();
        boolean isPharma = false;
        System.out.println(intent.hasExtra("isPharmacy"));
        if (intent.hasExtra("isPharmacy")) {
            isPharma = intent.getExtras().getBoolean("isPharmacy");
        }

        return isPharma;
    }

}

