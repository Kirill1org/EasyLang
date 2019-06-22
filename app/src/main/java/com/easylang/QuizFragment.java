package com.easylang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

public class QuizFragment extends Fragment {
    private QuizViewModel mViewModel;

    public static QuizFragment newInstance() {
        return new QuizFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);

        if (savedInstanceState == null) {
            QuizSetupFragment quizSetupFragment = QuizSetupFragment.newInstance();

            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, quizSetupFragment);
            fragmentTransaction.commit();
        }

        mViewModel.getCurrentQuestion().observe(this, questionNum -> {
            Fragment nextFragment;
            if (questionNum == mViewModel.getQuestionCount()) {
                nextFragment = QuizSetupFragment.newInstance();

            } else {
                nextFragment = QuizQuestionFragment.newInstance();
                Bundle args = new Bundle();
                args.putInt(QuizQuestionFragment.QUESTION_NUM, questionNum);
                nextFragment.setArguments(args);
            }

            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, nextFragment);
            fragmentTransaction.commit();
        });
    }
}
