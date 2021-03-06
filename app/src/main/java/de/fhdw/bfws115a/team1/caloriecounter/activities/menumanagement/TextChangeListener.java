package de.fhdw.bfws115a.team1.caloriecounter.activities.menumanagement;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import de.fhdw.bfws115a.team1.caloriecounter.R;

/**
 * Created by Niklas on 17.11.2016.
 */
public class TextChangeListener implements TextWatcher {

    private ApplicationLogic mApplicationLogic;
    private View mView;

    public TextChangeListener(ApplicationLogic applicationLogic, View view) {
        mApplicationLogic = applicationLogic;
        mView = view;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        switch (mView.getId()) {
            case R.id.idMenuManagementSelectMenuName: {
                mApplicationLogic.onMenuNameChanged(charSequence.toString());
                break;
            }
            case R.id.idMenuManagementSelectPortionSize: {
                mApplicationLogic.onPortionSizeChanged(charSequence.toString());
                break;
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
