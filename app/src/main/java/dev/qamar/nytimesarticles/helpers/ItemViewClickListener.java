package dev.qamar.nytimesarticles.helpers;

import android.view.View;

/**
 * @author Qamar4P
 */
public interface ItemViewClickListener<D> {
    void onViewClicked(View v, D item, int position);
}