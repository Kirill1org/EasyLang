package com.easylang;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Random;

public class QuizViewModel extends AndroidViewModel {
    private static final int WORD_COUNT_PER_QUESTION = 4;

    private List<Dictionary> randomWords;
    private Random random;

    private MutableLiveData<Integer> currentQuestion = new MutableLiveData<>();
    private int questionCount;
    private int currentAnswer;
    private DictionaryDAO dictionaryDAO;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        dictionaryDAO = AppDatabase.getInstance(getApplication()).dictionaryDAO();
    }

    public static int getWordCountPerQuestion() {
        return WORD_COUNT_PER_QUESTION;
    }

    public MutableLiveData<Integer> getCurrentQuestion() {
        return currentQuestion;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public int getCurrentAnswer() {
        return currentAnswer;
    }

    public void startQuiz(int idFrom, int idTo, int questionCount) {
        String from = Languages.getLangCode(idFrom);
        String to = Languages.getLangCode(idTo);
        if (!checkEnoughWords(from, to, questionCount * WORD_COUNT_PER_QUESTION)) {
            Toast.makeText(getApplication(), "You don't have enough words", Toast.LENGTH_LONG).show();
            return;
        }
        loadQuestionsFromDB(from, to, questionCount);
        this.questionCount = questionCount;
        currentQuestion.setValue(0);
        random = new Random();

        currentAnswer = random.nextInt(WORD_COUNT_PER_QUESTION);
    }

    public void nextQuestion() {
        currentQuestion.setValue(currentQuestion.getValue() + 1);

        currentAnswer = random.nextInt(WORD_COUNT_PER_QUESTION);
    }

    public List<Dictionary> getWordsForQuestion(int question) {
        return randomWords.subList(question * WORD_COUNT_PER_QUESTION, (question + 1) * WORD_COUNT_PER_QUESTION);
    }

    private void loadQuestionsFromDB(String langFrom, String langTo, int questionCount) {
        randomWords = dictionaryDAO
                .getRandomWords(langFrom, langTo, questionCount * WORD_COUNT_PER_QUESTION);
    }

    public boolean checkEnoughWords(String from, String to, int words) {
        return dictionaryDAO.getNotesCountByParams(from, to) >= words;
    }
}
