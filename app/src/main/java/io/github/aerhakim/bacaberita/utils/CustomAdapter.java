package io.github.aerhakim.bacaberita.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.aerhakim.bacaberita.R;
import io.github.aerhakim.bacaberita.models.Article;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<Article> headlines;
    private SelectListener listener;

    public CustomAdapter(Context context, List<Article> headlines, SelectListener listener) {
        this.context = context;
        this.listener = listener;
        this.headlines = headlines;
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        else return 2;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_cover, parent, false));
        }  else {
            return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_item, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
    holder.title.setText(headlines.get(position).getTitle());
    holder.source.setText(headlines.get(position).getSource().getName());

    if (headlines.get(position).getUrlToImage()!=null){
        Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.iv_headline);
    }

    holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listener.OnNewsClicked(headlines.get(position));
        }
    });

    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
