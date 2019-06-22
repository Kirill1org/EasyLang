package com.easylang;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

public class QuizQuestionFragment extends Fragment {
    public static final String QUESTION_NUM = "com.easylang.QuizQuestionFragment.QUESTION_NUM";

    private QuizViewModel mViewModel;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private Button buttonCheck;
    private TextView word;

    public static QuizQuestionFragment newInstance() {
        return new QuizQuestionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz_question, container, false);

        radioGroup = rootView.findViewById(R.id.radioGroup);
        radioButton1 = rootView.findViewById(R.id.radio1);
        radioButton2 = rootView.findViewById(R.id.radio2);
        radioButton3 = rootView.findViewById(R.id.radio3);
        radioButton4 = rootView.findViewById(R.id.radio4);
        word = rootView.findViewById(R.id.word);
        buttonCheck = rootView.findViewById(R.id.check_btn);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getParentFragment()).get(QuizViewModel.class);

        int questionNum = getArguments().getInt(QUESTION_NUM);
        List<Dictionary> wordsForQuestion = mViewModel.getWordsForQuestion(questionNum);

        word.setText(wordsForQuestion.get(mViewModel.getCurrentAnswer()).getTextFrom());

        radioButton1.setText(wordsForQuestion.get(0).getTextTo());
        radioButton2.setText(wordsForQuestion.get(1).getTextTo());
        radioButton3.setText(wordsForQuestion.get(2).getTextTo());
        radioButton4.setText(wordsForQuestion.get(3).getTextTo());

        buttonCheck.setOnClickListener(this::check);
    }

    private void check(View view) {
        int id = -1;
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio1:
                id = 0;
                break;
            case R.id.radio2:
                id = 1;
                break;
            case R.id.radio3:
                id = 2;
                break;
            case R.id.radio4:
                id = 3;
                break;
        }
        if (id == mViewModel.getCurrentAnswer()) {
            Toast.makeText(getActivity(), "Верно!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "Неверно!", Toast.LENGTH_LONG).show();
        }
        mViewModel.nextQuestion();
    }
}
