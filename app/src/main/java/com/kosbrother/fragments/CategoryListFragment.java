package com.kosbrother.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.analytics.AnalyticsName;
import com.analytics.NovelReaderAnalyticsApp;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.novel.reader.CategoryActivity;
import com.novel.reader.adapter.ListAdapter;
import com.novel.reader.api.NovelAPI;
import com.novel.reader.entity.Category;

import java.util.ArrayList;


public class CategoryListFragment extends ListFragment {

    private ArrayList<Category> categories = new ArrayList<Category>();
    private Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        categories = NovelAPI.getCategories();
        ListAdapter adapter = new ListAdapter(mActivity, categories);

        setListAdapter(adapter);
    }

    public static ListFragment newInstance(Activity myActivity) {
        CategoryListFragment fragment = new CategoryListFragment();
        return fragment;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        trackCategoryClick(position);

        Intent intent = new Intent(mActivity, CategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("CategoryId", categories.get(position).getId());
        bundle.putString("CategoryName", categories.get(position).getCateName());
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }

    private void trackCategoryClick(int position) {
        Tracker t = ((NovelReaderAnalyticsApp) getActivity().getApplication()).getTracker(NovelReaderAnalyticsApp.TrackerName.APP_TRACKER);
        t.send(new HitBuilders.EventBuilder().setCategory(AnalyticsName.Category).setAction(categories.get(position).getCateName()).setLabel(AnalyticsName.Index).build());
    }

} 
