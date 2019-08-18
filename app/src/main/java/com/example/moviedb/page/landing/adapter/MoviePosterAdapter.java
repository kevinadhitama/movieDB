package com.example.moviedb.page.landing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.databinding.ItemMoviePosterBinding;
import com.example.moviedb.datamodel.MovieItem;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by kevin.adhitama on 2019-08-15.
 */
public class MoviePosterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_LOADING = 1;
    Context mContext;
    Listener mListener;
    List<MovieItem> mMovieList;

    public MoviePosterAdapter(Context context, Listener listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return position == mMovieList.size() ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ViewDataBinding itemBinding = DataBindingUtil.inflate(inflater, R.layout.item_movie_poster, parent, false);
            return new ItemViewHolder(itemBinding.getRoot());
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_bottom_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemMoviePosterBinding binding = (ItemMoviePosterBinding) ((ItemViewHolder) holder).getDataBinding();

            if (mMovieList.get(position).getPosterPath() == null) {
                Glide.with(mContext)
                        .clear(binding.imageViewPoster);

                Glide.with(mContext)
                        .load(R.drawable.background_no_image_placeholder)
                        .transition(withCrossFade())
                        .into(binding.imageViewPoster);
            } else {
                Glide.with(mContext)
                        .load(mMovieList.get(position).getFullPosterPath())
                        .placeholder(R.drawable.background_image_placeholder)
                        .transition(withCrossFade())
                        .into(binding.imageViewPoster);
            }
            binding.getRoot().setOnClickListener(v -> {
                mListener.onPosterClickListener(mMovieList.get(position));
            });
        }
    }

    @Override
    public int getItemCount() {
        return mMovieList == null ? 0 : mMovieList.size() + 1;
    }

    public void setMovieList(List<MovieItem> movieList) {
        mMovieList = movieList;
        notifyDataSetChanged();
    }

    public void addMovieList(List<MovieItem> movieList) {
        if (mMovieList == null)
            setMovieList(movieList);
        else
            mMovieList.addAll(movieList);

        notifyDataSetChanged();
    }

    //region ViewHolder

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public ViewDataBinding getDataBinding() {
            return DataBindingUtil.getBinding(itemView);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    //endregion

    //region Listener
    public interface Listener {
        void onPosterClickListener(MovieItem movieItem);
    }

    //endregion
}
