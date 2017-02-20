package com.ifarma.ifarma.fragments.pharmacy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ifarma.ifarma.R;

import java.util.ArrayList;
import java.util.List;

public class PharmacyFragment extends Fragment {

    private View rootView;
    private ListView _configList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_pharmacy, container, false);
        _configList = (ListView) rootView.findViewById(R.id.config_list);

        final List<String> configOptions = getConfigOptions();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.config_item, configOptions);
        _configList.setAdapter(adapter);

        _configList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), configOptions.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private List<String> getConfigOptions(){
        List<String> list = new ArrayList<>();

        list.add("Editar informações");
        list.add("Editar conta");

        return list;
    }
}
