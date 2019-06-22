package com.easylang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class TranslateFragment extends Fragment {

    private TranslateViewModel mViewModel;

    private Spinner spinnerInput;
    private Spinner spinnerOutput;

    private EditText editTextInput;
    private TextView textViewOutput;

    private Button buttonTranslate;

    public static TranslateFragment newInstance() {
        return new TranslateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_translate, container, false);

        spinnerInput = rootView.findViewById(R.id.input_lang);
        spinnerOutput = rootView.findViewById(R.id.output_lang);

        editTextInput = rootView.findViewById(R.id.input_word);
        textViewOutput = rootView.findViewById(R.id.output_word);
        buttonTranslate = rootView.findViewById(R.id.button_translate);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TranslateViewModel.class);

        ArrayAdapter<String> adapterInputLanguages = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_item,
                Languages.getLangNames());

        adapterInputLanguages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInput.setAdapter(adapterInputLanguages);
        spinnerOutput.setAdapter(adapterInputLanguages);

        mViewModel.getLiveDataTranslatedText().observe(this, s -> {
            textViewOutput.setText(s);
        });

        buttonTranslate.setOnClickListener(this::translate);
    }

    public void translate(View view) {
        String[] languages = Languages.getLangCodes();

        int inputSelectedItemPosition = spinnerInput.getSelectedItemPosition();
        int outputSelectedItemPosition = spinnerOutput.getSelectedItemPosition();

        if (inputSelectedItemPosition == AdapterView.INVALID_POSITION) {
            Toast.makeText(getActivity(), "Input language not selected", Toast.LENGTH_LONG).show();
            return;
        }
        if (outputSelectedItemPosition == AdapterView.INVALID_POSITION) {
            Toast.makeText(getActivity(), "Output language not selected", Toast.LENGTH_LONG).show();
            return;
        }
        if (inputSelectedItemPosition == outputSelectedItemPosition) {
            Toast.makeText(getActivity(), "Languages are the same", Toast.LENGTH_LONG).show();
            return;
        }
        mViewModel.translate(editTextInput.getText().toString(), languages[inputSelectedItemPosition], languages[outputSelectedItemPosition]);
    }
}
