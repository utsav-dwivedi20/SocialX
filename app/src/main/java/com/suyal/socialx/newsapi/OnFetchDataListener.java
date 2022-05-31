package com.suyal.socialx.newsapi;

import com.suyal.socialx.newsapi.modal.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {

    void onFetchData(List<NewsHeadlines> list, String message);
    void onError(String message);
}
