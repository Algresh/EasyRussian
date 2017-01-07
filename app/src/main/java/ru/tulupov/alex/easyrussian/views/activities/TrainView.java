package ru.tulupov.alex.easyrussian.views.activities;


import ru.tulupov.alex.easyrussian.models.Word;

public interface TrainView {

    void wordsDownloaded();
    void wordsDownloadedError(String msg);

    void showWordQuestion(Word word, Word[] wordsVarious, int correctPosition, boolean isLast);
    void errorWordQuestion(int typeError);

    void showWrongWord(Word correctWord, Word wrongWord, int numErrors, int totalWords);
    void showCorrectWord(Word correctWord, int numCorrect, int totalWords);
}
