package com.ifarma.ifarma.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.activities.MainActivity;
import com.ifarma.ifarma.fragments.user.CartFragment;
import com.ifarma.ifarma.fragments.user.FinishBuyFragment;
import com.ifarma.ifarma.model.Product;
import com.ifarma.ifarma.services.AdapterService;
import com.ifarma.ifarma.services.CartService;

import java.util.Map;

/**
 * Created by Gabriel on 04/03/2017.
 */

public class CartListAdapter extends BaseAdapter{

    private View rootView;
    private LayoutInflater inflater;
    private Map<Product, Integer> cartList;
    private TextView _totalPrice;
    private Button _cancelButton;
    private Button _finishButton;
    private Context context;

    public CartListAdapter(Context context, Map<Product, Integer> cartList, View rootView) {

        this.rootView = rootView;
        this.cartList = cartList;
        this.context = context;

        _totalPrice = (TextView) rootView.findViewById(R.id.total_price);
        _cancelButton = (Button) rootView.findViewById(R.id.cancel_cart);
        _finishButton = (Button) rootView.findViewById(R.id.finalize_cart);

        _totalPrice.setText("R$ " + String.format("%.2f", getTotalPrice()));

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    private double getTotalPrice(){

        double soma = 0;

        for (Map.Entry<Product, Integer> entry : cartList.entrySet()) {
            soma += entry.getKey().getPrice() * entry.getValue();
        }

        return soma;
    }


    @Override
    public int getCount() {
        return cartList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView _medicinePrice;
        TextView _medicineName;
        TextView _medicinePharmacy;
        TextView _medicineDescription;
        TextView _medicineAmount;
        TextView _plusMedicineAmount;
        TextView _minusMedicineAmount;
        ImageView _removeMedicine;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();

        View rowView;
        rowView = inflater.inflate(R.layout.cart_item_list, null);

        holder._medicinePrice = (TextView) rowView.findViewById(R.id.medicine_price);
        holder._medicineName = (TextView) rowView.findViewById(R.id.medicine_name);
        holder._medicineDescription = (TextView) rowView.findViewById(R.id.medicine_description);
        holder._minusMedicineAmount = (TextView) rowView.findViewById(R.id.minus_ammount);
        holder._plusMedicineAmount = (TextView) rowView.findViewById(R.id.plus_ammount);
        holder._medicineAmount = (TextView) rowView.findViewById(R.id.total_amount);
        holder._medicinePharmacy = (TextView) rowView.findViewById(R.id.medicine_pharm);
        holder._removeMedicine = (ImageView) rowView.findViewById(R.id.remove_medicine);

        final Product currentProduct = (Product) cartList.keySet().toArray()[position];
        Integer currentProductAmount = cartList.get(currentProduct);

        holder._medicinePrice.setText("R$ " + String.format("%.2f", currentProduct.getPrice()));
        holder._medicineAmount.setText(String.valueOf(currentProductAmount));
        holder._medicinePharmacy.setText(currentProduct.getPharmacyName());
        holder._medicineName.setText(currentProduct.getNameProduct());
        holder._medicineDescription.setText(currentProduct.getDescription());

        holder._removeMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Alerta")
                        .setMessage("Você deseja remover o " + holder._medicineName.getText().toString() + " do carrinho?" )
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                CartService.removeFromCart(currentProduct);
                                AdapterService.reloadAdapter(1);

                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        _cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartService.closeCart();

                Toast.makeText(context, "O carrinho foi esvaziado!", Toast.LENGTH_SHORT).show();

                AdapterService.reloadAdapter(1);
            }
        });

        _finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new FinishBuyFragment());
                fragmentTransaction.commit();

            }
        });

        holder._minusMedicineAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(holder._medicineAmount.getText().toString()) > 1) {

                    int amount = Integer.parseInt(holder._medicineAmount.getText().toString());
                    holder._medicineAmount.setText(String.valueOf(amount - 1));

                    CartService.editCart(currentProduct, amount - 1);

                    double currentPrice = Double.parseDouble(_totalPrice.getText().toString().split(" ")[1].replace(",", "."));
                    double medicinePrice = Double.parseDouble(holder._medicinePrice.getText().toString().split(" ")[1].replace(",", "."));

                    _totalPrice.setText("R$ " + String.format("%.2f", currentPrice - medicinePrice));
                }
            }
        });

        holder._plusMedicineAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount = Integer.parseInt(holder._medicineAmount.getText().toString());
                holder._medicineAmount.setText(String.valueOf(amount + 1));

                CartService.editCart(currentProduct, amount + 1);

                double currentPrice = Double.parseDouble(_totalPrice.getText().toString().split(" ")[1].replace(",", "."));
                double medicinePrice = Double.parseDouble(holder._medicinePrice.getText().toString().split(" ")[1].replace(",", "."));

                _totalPrice.setText("R$ " + String.format("%.2f", currentPrice + medicinePrice));
            }
        });

        return rowView;
    }

}