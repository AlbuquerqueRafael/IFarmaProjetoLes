package com.ifarma.ifarma.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import com.ifarma.ifarma.R;

public class SearchFragment extends Fragment {

    private View rootView;
    private EditText _searchInput;
    private ListView _listView;
    private FloatingActionButton _filterButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_search, container, false);

        _searchInput = (EditText) rootView.findViewById(R.id.search_box);
        _listView = (ListView) rootView.findViewById(R.id.content_listview);

        _searchInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _searchInput.setFocusableInTouchMode(true);
                _searchInput.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(_searchInput, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        return rootView;
    }
}
