package com.ifarma.ifarma.fragments.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.adapters.CartListAdapter;
import com.ifarma.ifarma.model.Product;
import com.ifarma.ifarma.services.AdapterService;
import com.ifarma.ifarma.services.CartService;

import java.util.Map;


public class CartFragment extends Fragment {

    private View rootView;
    private Map<Product, Integer> cartList;
    private ListView cartListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (isCartEmpty()) {
            rootView = inflater.inflate(R.layout.fragment_empty_cart, container, false);

            Button searchMedicines = (Button) rootView.findViewById(R.id.search_medicines);
            searchMedicines.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                AdapterService.reloadAdapter(0);
                }
            });

        } else {
            rootView = inflater.inflate(R.layout.fragment_cart, container, false);
            cartListView = (ListView) rootView.findViewById(R.id.cart_listview);

            cartList = CartService.getCartList();

            cartListView.setAdapter(new CartListAdapter(getContext(), cartList, rootView));

            setListViewHeightBasedOnChildren(cartListView);

        }

        return rootView;
    }

    private void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    private boolean isCartEmpty(){
        return CartService.getCartList().isEmpty();
    }
}
