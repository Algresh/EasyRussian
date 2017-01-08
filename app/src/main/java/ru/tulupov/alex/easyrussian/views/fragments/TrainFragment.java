package ru.tulupov.alex.easyrussian.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.tulupov.alex.easyrussian.R;
import ru.tulupov.alex.easyrussian.models.Word;
import ru.tulupov.alex.easyrussian.presenters.TrainPresenter;
import ru.tulupov.alex.easyrussian.views.activities.TrainView;

public class TrainFragment extends Fragment implements TrainView, View.OnClickListener{

    private static final int LAYOUT = R.layout.fragment_word_translate_train;
    private TrainPresenter presenter;

    private TextView wordQuest;
    private TextView wordCorrect;
    private TextView wordWrong;

    private TextView wordAnswerOne;
    private TextView wordAnswerTwo;
    private TextView wordAnswerThree;
    private TextView wordAnswerFour;

    private TextView numTotal;
    private TextView numCorrect;
    private TextView numWrong;

    private TextView skipWordQuest;
    private TextView nextWordQuest;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = new TrainPresenter();
        presenter.onCreate(this);

        View view = inflater.inflate(LAYOUT, container, false);
        wordQuest = (TextView) view.findViewById(R.id.train_panel_quest_word);
        wordCorrect = (TextView) view.findViewById(R.id.train_panel_quest_correctWord);
        wordWrong = (TextView) view.findViewById(R.id.train_panel_quest_wrongWord);

        wordAnswerOne = (TextView) view.findViewById(R.id.train_panel_answer_leftTop);
        wordAnswerTwo = (TextView) view.findViewById(R.id.train_panel_answer_rightTop);
        wordAnswerThree = (TextView) view.findViewById(R.id.train_panel_answer_leftBottom);
        wordAnswerFour = (TextView) view.findViewById(R.id.train_panel_answer_rightBottom);

        presenter.downloadWords();
        numTotal = (TextView) view.findViewById(R.id.train_panel_score_total_digit);
        numCorrect = (TextView) view.findViewById(R.id.train_panel_score_correct_digit);
        numWrong = (TextView) view.findViewById(R.id.train_panel_score_wrong_digit);

        nextWordQuest = (TextView) view.findViewById(R.id.train_panel_next_next);
        skipWordQuest = (TextView) view.findViewById(R.id.train_panel_next_skip);

        wordAnswerOne.setOnClickListener(this);
        wordAnswerTwo.setOnClickListener(this);
        wordAnswerThree.setOnClickListener(this);
        wordAnswerFour.setOnClickListener(this);
        nextWordQuest.setOnClickListener(this);
        skipWordQuest.setOnClickListener(this);

        return view;
    }

    @Override
    public void wordsDownloaded() {
        presenter.getWordQuestion();
    }

    @Override
    public void wordsDownloadedError(String msg) {

    }

    @Override
    public void showWordQuestion(Word word, Word[] wordsVarious, int correctPosition, boolean isLast) {

    }

    @Override
    public void errorWordQuestion(int typeError) {

    }

    @Override
    public void showWrongWord(Word correctWord, Word wrongWord, int numErrors, int totalWords) {

    }

    @Override
    public void showCorrectWord(Word correctWord, int numCorrect, int totalWords) {

    }

    @Override
    public void onClick(View view) {

    }
}
