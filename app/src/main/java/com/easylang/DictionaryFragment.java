package com.easylang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DictionaryFragment extends Fragment {

    private Spinner spinnerInput;
    private Spinner spinnerOutput;

    private DictionaryViewModel mViewModel;
    private DictionaryAdapter dictionaryAdapter;

    private LiveData<List<Dictionary>> previousLiveData;

    private String selectedFrom;
    private String selectedTo;

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

        ArrayAdapter<String> adapterInputLanguages = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_item,
                Languages.getLangNames());

        adapterInputLanguages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInput.setAdapter(adapterInputLanguages);
        spinnerInput.setSelection(adapterInputLanguages.getPosition("English"));
        spinnerOutput.setAdapter(adapterInputLanguages);
        spinnerOutput.setSelection(adapterInputLanguages.getPosition("Russian"));

        spinnerInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedFrom = Languages.getLangCode(position);
                changeLiveData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerOutput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTo = Languages.getLangCode(position);
                changeLiveData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void changeLiveData() {
        if (previousLiveData != null) {
            previousLiveData.removeObservers(this);
        }

        previousLiveData = mViewModel.getTranslateByLang(selectedFrom, selectedTo);

        previousLiveData.observe(this, dictionaryAdapter::setDictionaryItemList);
    }

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
