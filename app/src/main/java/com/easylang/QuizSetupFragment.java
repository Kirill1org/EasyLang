package com.easylang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class QuizSetupFragment extends Fragment {

    private QuizViewModel mViewModel;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private EditText editTextCount;
    private Button buttonStart;

    public static QuizSetupFragment newInstance() {
        return new QuizSetupFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz_setup, container, false);

        spinnerFrom = rootView.findViewById(R.id.spinner_langFrom);
        spinnerTo = rootView.findViewById(R.id.spinner_langTo);
        editTextCount = rootView.findViewById(R.id.editText_quiz_count);
        buttonStart = rootView.findViewById(R.id.button_start);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getParentFragment()).get(QuizViewModel.class);

        ArrayAdapter<String> adapterInputLanguages = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_item,
                Languages.getLangNames());

        adapterInputLanguages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapterInputLanguages);
        spinnerTo.setAdapter(adapterInputLanguages);

        spinnerFrom.setSelection(adapterInputLanguages.getPosition("English"));
        spinnerTo.setSelection(adapterInputLanguages.getPosition("Russian"));

        buttonStart.setOnClickListener(this::startQuiz);
    }

    private void startQuiz(View view) {
        int idFrom = spinnerFrom.getSelectedItemPosition();
        int idTo = spinnerTo.getSelectedItemPosition();
        int questionCount = Integer.parseInt(editTextCount.getText().toString());

        mViewModel.startQuiz(idFrom, idTo, questionCount);
    }
}
