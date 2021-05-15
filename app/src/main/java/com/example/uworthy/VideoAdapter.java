package com.example.uworthy;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VideoAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    public static final int VIEW_TYPE_NORMAL = 1;

    private List<YoutubeVideo> mYoutubeVideos;
    DisplayMetrics displayMetrics = new DisplayMetrics();

    public VideoAdapter(List<YoutubeVideo> youtubeVideos) {
        mYoutubeVideos = youtubeVideos;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.videowebview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }


    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        if (mYoutubeVideos != null && mYoutubeVideos.size() > 0) {
            return mYoutubeVideos.size();
        } else {
            return 1;
        }
    }

    public void setItems(List<YoutubeVideo> youtubeVideos) {
        mYoutubeVideos = youtubeVideos;
        notifyDataSetChanged();
    }


    public class ViewHolder extends BaseViewHolder {
     //   @BindView(R.id.textViewTitle)
      //  TextView textWaveTitle;
      @BindView(R.id.btnPlay)
        ImageView playButton;

        @BindView(R.id.imageViewItem)
        ImageView imageViewItems;
        @BindView(R.id.youtube_view)
        YouTubePlayerView youTubePlayerView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);
           playButton.setVisibility(View.GONE);
            final YoutubeVideo mYoutubeVideo = mYoutubeVideos.get(position);
            ((Activity) itemView.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            if (mYoutubeVideo.getTitle() != null)
             //   textWaveTitle.setText(mYoutubeVideo.getTitle());

//            if (mYoutubeVideo.getImageUrl() != null) {
//                Glide.with(itemView.getContext())
//                        .load(mYoutubeVideo.getImageUrl()).
//                        apply(new RequestOptions().override(width - 36, 200))
//                        .into(imageViewItems);
//            }
            imageViewItems.setVisibility(View.VISIBLE);
          playButton.setVisibility(View.VISIBLE);
            youTubePlayerView.setVisibility(View.VISIBLE);

            playButton.setOnClickListener(view -> {
                imageViewItems.setVisibility(View.GONE);
                youTubePlayerView.setVisibility(View.VISIBLE);
                playButton.setVisibility(View.GONE);
                youTubePlayerView.initialize(initializedYouTubePlayer -> initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        initializedYouTubePlayer.loadVideo(mYoutubeVideo.getVideoId(), 0);
                    }
                }), true);

            });
        }
    }
}