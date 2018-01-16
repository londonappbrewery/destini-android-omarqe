package com.londonappbrewery.destini;

public class DestiniStory {
    private int mStoryId;
    private int mButtonTop;
    private int mButtonBottom;
    private int mNextStoryTop, mNextStoryBottom;

    public DestiniStory(int storyId, int buttonTop, int buttonBottom, int nextStoryTop, int nextStoryBottom){
        mStoryId        = storyId;
        mButtonTop      = buttonTop;
        mButtonBottom   = buttonBottom;
        mNextStoryTop   = nextStoryTop-1;
        mNextStoryBottom = nextStoryBottom-1;
    }

    public DestiniStory(int storyId){
        mStoryId = storyId;
        mButtonTop = mButtonBottom = 0;
    }

    public int getStoryId() {
        return mStoryId;
    }

    public void setStoryId(int storyId) {
        mStoryId = storyId;
    }

    public int getButtonTop() {
        return mButtonTop;
    }

    public void setButtonTop(int buttonTop) {
        mButtonTop = buttonTop;
    }

    public int getButtonBottom() {
        return mButtonBottom;
    }

    public void setButtonBottom(int buttonBottom) {
        mButtonBottom = buttonBottom;
    }

    public int getNextStoryTop() {
        return mNextStoryTop;
    }

    public int getNextStoryBottom() {
        return mNextStoryBottom;
    }
}
