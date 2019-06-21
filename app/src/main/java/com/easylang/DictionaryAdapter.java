package com.easylang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder> {

    private List<Dictionary> dictionaryItemList;

    public DictionaryAdapter(List<Dictionary> dictionaryItemList) {
        this.dictionaryItemList = dictionaryItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.dictionary_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(dictionaryItemList.get(i));
    }

    @Override
    public int getItemCount() {
        return dictionaryItemList.size();
    }

    static public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textFrom;
        private final TextView textTo;

        public ViewHolder(View view) {
            super(view);
            textFrom = view.findViewById(R.id.textFrom);
            textTo = view.findViewById(R.id.textTo);
        }

        public void bind(Dictionary dictionaryItem) {
            textFrom.setText(dictionaryItem.getTextFrom());
            textTo.setText(dictionaryItem.getTextTo());
        }
    }
}