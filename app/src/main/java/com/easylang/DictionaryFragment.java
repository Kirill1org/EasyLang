package com.easylang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DictionaryFragment extends Fragment {

    private Spinner spinnerInput;
    private Spinner spinnerOutput;

    private DictionaryViewModel mViewModel;
    private DictionaryAdapter dictionaryAdapter;

    public static DictionaryFragment newInstance() {
        return new DictionaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dictionary, container, false);
        spinnerInput = rootView.findViewById(R.id.input_spin);
        spinnerOutput = rootView.findViewById(R.id.output_spin);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        dictionaryAdapter = new DictionaryAdapter();
        recyclerView.setAdapter(dictionaryAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback(dictionaryAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DictionaryViewModel.class);
        mViewModel.getAll().observe(this, dictionaries -> dictionaryAdapter.setDictionaryItemList(dictionaries));

        ArrayAdapter<String> adapterInputLanguages = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_item,
                TranslateViewModel.getLANGUAGES());

        adapterInputLanguages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInput.setAdapter(adapterInputLanguages);
        spinnerOutput.setAdapter(adapterInputLanguages);

        spinnerInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerOutput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

        private DictionaryAdapter mAdapter;

        public SwipeToDeleteCallback(DictionaryAdapter adapter) {
            super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            mAdapter = adapter;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            mViewModel.delete(dictionaryAdapter.get(position));
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }
    }
}
