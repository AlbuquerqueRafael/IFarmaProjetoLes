package com.ifarma.ifarma.fragments.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ifarma.ifarma.R;


public class CartFragment extends Fragment {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (isCartEmpty()) {
            rootView = inflater.inflate(R.layout.fragment_empty_cart, container, false);

            Button searchMedicines = (Button) rootView.findViewById(R.id.search_medicines);
            searchMedicines.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.activity_main, new SearchFragment());
                    fragmentTransaction.commit();
                }
            });

        } else {
            rootView = inflater.inflate(R.layout.fragment_cart, container, false);

        }

        return rootView;
    }

    private boolean isCartEmpty(){
        return true;
    }
}
