package com.easylang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class TranslateFragment extends Fragment {

    private TranslateViewModel mViewModel;
    String[] inputDataSpinner = {"eng"};
    String[] outputDataSpinner = {"ru"};
    Spinner spinnerInput;
    Spinner spinnerOutput;

    public static TranslateFragment newInstance() {
        return new TranslateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translate, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TranslateViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<CharSequence> adapterInput = ArrayAdapter.createFromResource(getContext(), R.array.input_data_spinner, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterOutput = ArrayAdapter.createFromResource(getContext(), R.array.output_data_spinner, android.R.layout.simple_spinner_item);
        adapterInput.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterOutput.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInput.setAdapter(adapterInput);
        spinnerOutput.setAdapter(adapterOutput);

    }
}
