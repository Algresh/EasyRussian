package ru.tulupov.alex.easyrussian.presenters;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.tulupov.alex.easyrussian.models.ModelTrainImpl;
import ru.tulupov.alex.easyrussian.models.Word;
import ru.tulupov.alex.easyrussian.views.activities.TrainView;
import rx.Observer;
import rx.functions.Func1;

public class TrainPresenter {

    private int numWrong = 0;
    private int numTotal = 0;
    private int numCorrect = 0;
    private int maxWords;

    private List<Word> listNotUsedWords;

    private TrainView view;
    private RealmResults<Word> trainWords;
    private ModelTrainImpl model;

    public void onCreate(TrainView view) {
        this.view = view;
        model = new ModelTrainImpl();
        listNotUsedWords = new LinkedList<>();
    }

    public void downloadWords() {
        model.getTrainWords().map(new Func1<RealmResults<Word>, RealmResults<Word>>() {
            @Override
            public RealmResults<Word> call(RealmResults<Word> words) {
                maxWords = words.size();
                int size = maxWords > 20 ? 20 : maxWords;
                for(int i = 0; i < size; i++) {
                    Word word = words.get(i);
                    listNotUsedWords.add(word);
                }

                return words;
            }
        }).subscribe(new Observer<RealmResults<Word>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.wordsDownloadedError(e.getMessage());
            }

            @Override
            public void onNext(RealmResults<Word> words) {
                trainWords = words;
                view.wordsDownloaded();
            }
        });
    }

    public void getWordQuestion() {

        if (numTotal < maxWords) {
            final Word word = getRandomWord();
            model.getThreeOtherWord(word).map(new Func1<RealmResults<Word>, Word[]>() {
                @Override
                public Word[] call(RealmResults<Word> words) {
                    Word[] otherWords = new Word[3];
                    for(int i = 0; i < otherWords.length; i++) {
                        otherWords[i] = words.get(i);
                    }
                    return otherWords;
                }
            }).subscribe(new Observer<Word[]>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Word[] otherWords) {
                    Random random = new Random();
                    int correctPos = random.nextInt(3);
                    view.showWordQuestion(word, otherWords, correctPos, numTotal == maxWords);
                }
            });
//            view.showWordQuestion();
        }

    }



    private Word getRandomWord() {
        Random random = new Random();
        Word word;

        if (listNotUsedWords.size() == 0) {
            /**
             * @TODO перенести в отдельный метод
             */
            int size = maxWords > numTotal + 20 ? numTotal + 20 : maxWords;
            for(int i = numTotal; i < size; i++) {
                Word newWord = trainWords.get(i);
                listNotUsedWords.add(newWord);
            }

            if (listNotUsedWords.size() == 0) {
                return null;
            }
        }

        int pos = random.nextInt(listNotUsedWords.size());
        word = listNotUsedWords.get(pos);
        listNotUsedWords.remove(pos);

        return word;

    }

    private void setListNotUsedWords () {

    }



}
