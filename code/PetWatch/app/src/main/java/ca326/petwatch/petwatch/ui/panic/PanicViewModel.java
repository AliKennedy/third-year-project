package ca326.petwatch.petwatch.ui.panic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PanicViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PanicViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}