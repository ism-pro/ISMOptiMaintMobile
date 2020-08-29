package org.ism.ismoptimaintmobile.ui.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DatabaseViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DatabaseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is base de donnée fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}