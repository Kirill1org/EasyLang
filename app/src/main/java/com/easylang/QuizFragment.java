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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class QuizFragment extends Fragment {

    private QuizViewModel mViewModel;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private Button buttonCheck;
    private TextView word;
    private Quiz quiz;
    private Question question;
    private int count = 10;
    private String langFrom = "en";
    private String langTo = "ru";

    public static QuizFragment newInstance() {
        return new QuizFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);

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
        mViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);

        quiz = new Quiz();
        question = new Question();

        Dictionary dictionary = AppDatabase.getInstance(getContext()).noteDao().getDataById(getRandomize());
        question.setData(dictionary);
        word.setText(question.getData().getTextFrom());
        question.addCase(dictionary.getTextTo());
        question.addCase("test1");
        question.addCase("test2");
        question.addCase("test3");
//        question.addCase(AppDatabase.getInstance(getContext()).noteDao().getDataById(getRandomize()).getTextTo());
//        question.addCase(AppDatabase.getInstance(getContext()).noteDao().getDataById(getRandomize()).getTextTo());
//        question.addCase(AppDatabase.getInstance(getContext()).noteDao().getDataById(getRandomize()).getTextTo());
        question.shuffle();
        quiz.addQuestion(question);

        radioButton1.setText(question.getCases().get(0));
        radioButton2.setText(question.getCases().get(1));
        radioButton3.setText(question.getCases().get(2));
        radioButton4.setText(question.getCases().get(3));

        buttonCheck.setOnClickListener(this::check);
        // TODO: Use the ViewModel
    }

    private void check(View view) {
        radioButton = getActivity().findViewById(radioGroup.getCheckedRadioButtonId());
        if (question.getData().getTextTo().equals(radioButton.getText())) {
            quiz.addRightQuestion(question);
            Toast.makeText(getActivity(), "Верно!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "Неверно!", Toast.LENGTH_LONG).show();
        }
    }

    private int getRandomize() {
        int countData = AppDatabase.getInstance(getContext()).noteDao().getNotesCountByParams(langFrom, langTo);
        return (int) (Math.random() * (countData - 1));
    }
}
