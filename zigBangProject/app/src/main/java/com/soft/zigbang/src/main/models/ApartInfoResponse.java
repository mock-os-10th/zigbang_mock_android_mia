
package com.soft.zigbang.src.main.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ApartInfoResponse {

    @SerializedName("code")
    private int mCode;
    @SerializedName("isSuccess")
    private Boolean mIsSuccess;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("result")
    private List<Result> mResult;

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public Boolean getIsSuccess() {
        return mIsSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        mIsSuccess = isSuccess;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<Result> getResult() {
        return mResult;
    }

    public void setResult(List<Result> result) {
        mResult = result;
    }

    public static class Advertise {

        @SerializedName("advertiseIndex")
        private int mAdvertiseIndex;
        @SerializedName("image")
        private Object mImage;
        @SerializedName("url")
        private String mUrl;

        public int getAdvertiseIndex() {
            return mAdvertiseIndex;
        }

        public void setAdvertiseIndex(int advertiseIndex) {
            mAdvertiseIndex = advertiseIndex;
        }

        public Object getImage() {
            return mImage;
        }

        public void setImage(Object image) {
            mImage = image;
        }

        public String getUrl() {
            return mUrl;
        }

        public void setUrl(String url) {
            mUrl = url;
        }

    }

    public static class Like {

        @SerializedName("apartIndex")
        private int mApartIndex;
        @SerializedName("image")
        private String mImage;
        @SerializedName("name")
        private String mName;

        public int getApartIndex() {
            return mApartIndex;
        }

        public void setApartIndex(int apartIndex) {
            mApartIndex = apartIndex;
        }

        public String getImage() {
            return mImage;
        }

        public void setImage(String image) {
            mImage = image;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

    }

    public static class News {

        @SerializedName("image")
        private Object mImage;
        @SerializedName("newsIndex")
        private int mNewsIndex;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("url")
        private String mUrl;

        public Object getImage() {
            return mImage;
        }

        public void setImage(Object image) {
            mImage = image;
        }

        public int getNewsIndex() {
            return mNewsIndex;
        }

        public void setNewsIndex(int newsIndex) {
            mNewsIndex = newsIndex;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public String getUrl() {
            return mUrl;
        }

        public void setUrl(String url) {
            mUrl = url;
        }

    }

    public static class Result {

        @SerializedName("advertise")
        private List<Advertise> mAdvertise;
        @SerializedName("like")
        private List<Like> mLike;
        @SerializedName("news")
        private List<News> mNews;

        public List<Advertise> getAdvertise() {
            return mAdvertise;
        }

        public void setAdvertise(List<Advertise> advertise) {
            mAdvertise = advertise;
        }

        public List<Like> getLike() {
            return mLike;
        }

        public void setLike(List<Like> like) {
            mLike = like;
        }

        public List<News> getNews() {
            return mNews;
        }

        public void setNews(List<News> news) {
            mNews = news;
        }

    }
}
