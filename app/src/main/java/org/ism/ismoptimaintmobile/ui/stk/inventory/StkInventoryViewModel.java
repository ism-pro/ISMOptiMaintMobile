package org.ism.ismoptimaintmobile.ui.stk.inventory;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StkInventoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StkInventoryViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("...scan...");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String contents) {
        mText.setValue(contents);
    }

    public void newScanned(String contents) {
        setText(contents);
    }


}