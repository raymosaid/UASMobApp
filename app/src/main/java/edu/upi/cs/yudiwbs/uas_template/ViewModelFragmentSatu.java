package edu.upi.cs.yudiwbs.uas_template;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelFragmentSatu extends ViewModel {
    public MutableLiveData<String> data;

    public void setNama(String pertanyaan) {
        data.setValue(pertanyaan);
    }

    public ViewModelFragmentSatu() {
        //inisialisasi
        this.data = new MutableLiveData<String>();
        this.data.setValue("default");
    }
}
