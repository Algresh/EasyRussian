package ru.tulupov.alex.easyrussian.views.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ru.tulupov.alex.easyrussian.Constants;
import ru.tulupov.alex.easyrussian.R;
import ru.tulupov.alex.easyrussian.models.Word;
import ru.tulupov.alex.easyrussian.presenters.TrainPresenter;
import ru.tulupov.alex.easyrussian.views.activities.TrainView;

import static ru.tulupov.alex.easyrussian.views.activities.TrainActivity.TYPE_TRAIN;
import static ru.tulupov.alex.easyrussian.views.activities.TrainActivity.EN_RU_TRAIN;
import static ru.tulupov.alex.easyrussian.views.activities.TrainActivity.RU_EN_TRAIN;
import static ru.tulupov.alex.easyrussian.views.activities.TrainActivity.IDIOM_TRAIN;

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

    private String currentTypeTrain;

    private Word currentQuestWord;
    private Word[] currentVariousWord;

    private boolean answered;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        currentTypeTrain = getArguments().getString(TYPE_TRAIN);
        currentVariousWord = new Word[4];

        presenter = new TrainPresenter();
        presenter.onCreate(this);

        View view = inflater.inflate(LAYOUT, container, false);
        initHeightBlockQuest(view);
        wordQuest = (TextView) view.findViewById(R.id.train_panel_quest_word);
        wordCorrect = (TextView) view.findViewById(R.id.train_panel_quest_correctWord);
        wordWrong = (TextView) view.findViewById(R.id.train_panel_quest_wrongWord);

        wordAnswerOne = (TextView) view.findViewById(R.id.train_panel_answer_leftTop);
        wordAnswerTwo = (TextView) view.findViewById(R.id.train_panel_answer_rightTop);
        wordAnswerThree = (TextView) view.findViewById(R.id.train_panel_answer_leftBottom);
        wordAnswerFour = (TextView) view.findViewById(R.id.train_panel_answer_rightBottom);

        presenter.downloadWords();
        numTotal = (TextView) view.findViewById(R.id.train_panel_score_total_digit);
        numTotal.setText("0");
        numCorrect = (TextView) view.findViewById(R.id.train_panel_score_correct_digit);
        numCorrect.setText("0");
        numWrong = (TextView) view.findViewById(R.id.train_panel_score_wrong_digit);
        numWrong.setText("0");

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
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWordQuestion(Word word, Word[] wordsVarious, int correctPosition, boolean isLast) {
        setCurrentWords(word, wordsVarious, correctPosition);
        nextWordQuest.setVisibility(View.GONE);
        skipWordQuest.setVisibility(View.VISIBLE);
        wordCorrect.setVisibility(View.GONE);
        wordWrong.setVisibility(View.GONE);
        this.answered = false;
        String questWordStr = "";
        String correctWordStr = "";
        String[] variousWordStr = new String[3];
        if (currentTypeTrain.equals(EN_RU_TRAIN)) {
            questWordStr = word.getEnglishWord();
            correctWordStr = word.getRussianWord();

            for (int i = 0; i < 3; i++) {
                variousWordStr[i] = wordsVarious[i].getRussianWord();
            }
        }

        if (currentTypeTrain.equals(RU_EN_TRAIN)) {
            questWordStr = word.getRussianWord();
            correctWordStr = word.getEnglishWord();

            for (int i = 0; i < 3; i++) {
                variousWordStr[i] = wordsVarious[i].getEnglishWord();
            }
        }

        int i = 0;
        int index = 0;
        while (index < 4) {
            TextView tvQuest = getTVQuestion(index);
            if (tvQuest != null) {
                if (index == correctPosition) {
                    tvQuest.setText(correctWordStr);
                } else {
                    tvQuest.setText(variousWordStr[i]);
                    i++;
                }
            }
            index++;
        }
        wordQuest.setText(questWordStr);

    }

    @Override
    public void errorWordQuestion(int typeError) {
        Toast.makeText(getActivity(), "type error: " + typeError , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWrongWord(Word correctWord, Word wrongWord, int numErrors, int totalWords) {
        this.answered = true;
        skipWordQuest.setVisibility(View.GONE);
        nextWordQuest.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), "wrong", Toast.LENGTH_SHORT).show();
        if (currentTypeTrain.equals(EN_RU_TRAIN)) {
            wordCorrect.setText(correctWord.getRussianWord());
            wordWrong.setText(wrongWord.getRussianWord());
        }

        if (currentTypeTrain.equals(RU_EN_TRAIN)) {
            wordCorrect.setText(correctWord.getEnglishWord());
            wordWrong.setText(wrongWord.getEnglishWord());
        }

        wordCorrect.setVisibility(View.VISIBLE);
        wordWrong.setVisibility(View.VISIBLE);

        this.numWrong.setText(String.valueOf(numErrors));
        this.numTotal.setText(String.valueOf(totalWords));

    }

    @Override
    public void showCorrectWord(Word correctWord, int numCorrect, int totalWords) {
        this.answered = true;
        skipWordQuest.setVisibility(View.GONE);
        nextWordQuest.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), "correct", Toast.LENGTH_SHORT).show();
        if (currentTypeTrain.equals(EN_RU_TRAIN)) {
            wordCorrect.setText(correctWord.getRussianWord());
        }

        if (currentTypeTrain.equals(RU_EN_TRAIN)) {
            wordCorrect.setText(correctWord.getEnglishWord());
        }

        wordCorrect.setVisibility(View.VISIBLE);
        this.numCorrect.setText(String.valueOf(numCorrect));
        this.numTotal.setText(String.valueOf(totalWords));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.train_panel_answer_leftTop:
                answerOnQuestion(0);
                break;
            case R.id.train_panel_answer_rightTop:
                answerOnQuestion(1);
                break;
            case R.id.train_panel_answer_leftBottom:
                answerOnQuestion(2);
                break;
            case R.id.train_panel_answer_rightBottom:
                answerOnQuestion(3);
                break;
            case R.id.train_panel_next_skip:
                /**
                 * @TODO нужен новый метод для пропуска
                 */
                presenter.getWordQuestion();
                break;
            case R.id.train_panel_next_next:
                presenter.getWordQuestion();
                break;
        }

    }

//    private void nextQuestion() {
//        presenter.getWordQuestion();
//    }

    private void answerOnQuestion (int posAnswered) {
        if (!answered && posAnswered >= 0 && posAnswered <= 3) {
            presenter.answerOnQustion(currentQuestWord, currentVariousWord[posAnswered]);
        }
    }

    private TextView getTVQuestion(int pos) {
        switch (pos) {
            case 0:
                return wordAnswerOne;
            case 1:
                return wordAnswerTwo;
            case 2:
                return wordAnswerThree;
            case 3:
                return wordAnswerFour;
        }
        return null;
    }

    private void setCurrentWords(Word questWord, Word[] variousWords, int correctPos) {
        currentQuestWord = questWord;
        for (int i = 0, index = 0; i < 4; i++) {
            if (correctPos == i) {
                currentVariousWord[i] = questWord;
            } else  {
                currentVariousWord[i] = variousWords[index];
                index++;
            }

        }
    }

    protected void initHeightBlockQuest(View view) {
        int blockHeight = getHeightScreen() - 400;//400 столько занимает все остальное кроме блока с вопросом
        blockHeight = blockHeight < 120 ? 120 : blockHeight;

        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.train_panel_quest);
        layout.setMinimumHeight(dpToPx(blockHeight));
    }

    protected int getHeightScreen() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);


        int dp = pxToDp(size.y);
        Log.d(Constants.MY_TAG, "dp: " + dp + " px: " + size.y);

        return dp;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
