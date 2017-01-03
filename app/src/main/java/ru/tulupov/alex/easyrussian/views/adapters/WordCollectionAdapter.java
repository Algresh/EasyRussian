package ru.tulupov.alex.easyrussian.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.tulupov.alex.easyrussian.R;
import ru.tulupov.alex.easyrussian.models.Word;


public class WordCollectionAdapter extends RecyclerView.Adapter<WordCollectionAdapter.WordCollectionViewHolder> {

    private List<Word> listWords;
    private Context context;

    public WordCollectionAdapter(List<Word> listWords, Context context) {
        this.listWords = listWords;
        this.context = context;
    }

    @Override
    public WordCollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.word_item, parent, false);
        return new WordCollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordCollectionViewHolder holder, int position) {
        Word word = listWords.get(position);

        holder.getRussianLabel().setText(word.getRussianWord());
        holder.getEnglishLabel().setText(word.getEnglishWord());
    }

    @Override
    public int getItemCount() {
        return listWords.size();
    }

    public static class WordCollectionViewHolder extends RecyclerView.ViewHolder {

        private TextView russianLabel;
        private TextView englishLabel;

        public WordCollectionViewHolder(View itemView) {
            super(itemView);

            russianLabel = (TextView) itemView.findViewById(R.id.wordLabelRussian);
            englishLabel = (TextView) itemView.findViewById(R.id.wordLabelEnglish);
        }

        public TextView getRussianLabel() {
            return russianLabel;
        }

        public void setRussianLabel(TextView russianLabel) {
            this.russianLabel = russianLabel;
        }

        public TextView getEnglishLabel() {
            return englishLabel;
        }

        public void setEnglishLabel(TextView englishLabel) {
            this.englishLabel = englishLabel;
        }
    }
}
