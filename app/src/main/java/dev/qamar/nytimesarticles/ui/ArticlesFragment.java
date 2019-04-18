package dev.qamar.nytimesarticles.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import dev.qamar.nytimesarticles.R;
import dev.qamar.nytimesarticles.data.model.MediaMetadatum;
import dev.qamar.nytimesarticles.data.model.Medium;
import dev.qamar.nytimesarticles.data.model.NewsArticle;
import dev.qamar.nytimesarticles.helpers.ItemViewClickListener;
import dev.qamar.nytimesarticles.helpers.LolAdapter;
import dev.qamar.nytimesarticles.helpers.LolViewHold;

public class ArticlesFragment extends BaseFragment {

    public static final String NEWS_PERIOD = "news_period";
    private ArticlesViewModel mViewModel;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.emptyView)
    View mEmptyView;
    @BindView(R.id.progressBar)
    View mProgressBar;

    LolAdapter<NewsArticle,ItemViewHold> mAdapter = new LolAdapter<>(itemViewClickListener(),ItemViewHold::new);
    private int mNewsPeriod;

    private ItemViewClickListener<NewsArticle> itemViewClickListener() {
        return (v, item, position) -> {
            Toast.makeText(getContext(),item.getTitle(),Toast.LENGTH_SHORT).show();
        };
    }

    public static ArticlesFragment newInstance(int period) {
        ArticlesFragment fragment = new ArticlesFragment();
        Bundle args = new Bundle();
        args.putInt(NEWS_PERIOD,period);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.articles_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
        observeViewModel();

        if(getArguments() !=null){
            mNewsPeriod = getArguments().getInt(NEWS_PERIOD);
            loadArticles(mNewsPeriod);
        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void observeViewModel() {
        mViewModel.getArticles().observe(this,newsArticles -> {
            mAdapter.items.clear();
            if(newsArticles.size() > 0){
                mAdapter.items.addAll(newsArticles);
                mEmptyView.setVisibility(View.GONE);
            }else {
                mEmptyView.setVisibility(View.VISIBLE);
            }
            mAdapter.notifyDataSetChanged();
        });

        mViewModel.getLoading().observe(this,loading -> mProgressBar.setVisibility(loading? View.VISIBLE: View.GONE));
    }

    public void loadArticles(int period) {
        mViewModel.loadArticles(period);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NEWS_PERIOD,mNewsPeriod);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mNewsPeriod = savedInstanceState.getInt(NEWS_PERIOD);
        }
    }


    class ItemViewHold extends LolViewHold<NewsArticle> {
        @BindView(R.id.textTitle)
        TextView textTitle;
        @BindView(R.id.textAuthor)
        TextView textDesc;
        @BindView(R.id.textDate)
        TextView textDate;
        @BindView(R.id.imageProfile)
        ImageView imageProfile;

        ItemViewHold(ViewGroup parent) {
            super(parent,R.layout.list_item);
        }
        @Override
        public void bind() {
            textTitle.setText(data.getTitle());
            textDesc.setText(data.getByline());
            textDate.setText(data.getPublishedDate());

            List<Medium> medias = data.getMedia();
            if (medias != null) {
                for (Medium media : medias) {
                    if (media.getType().equalsIgnoreCase("image")) {
                        //choose small size
                        for (MediaMetadatum mediaMetadatum : media.getMediaMetadata()) {
                            if (mediaMetadatum.getWidth() >= 320 && mediaMetadatum.getWidth() < 800) {
                                Picasso.with(imageProfile.getContext()).load(media.getMediaMetadata().get(0).getUrl()).into(imageProfile);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
}
