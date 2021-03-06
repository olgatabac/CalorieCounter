package de.fhdw.bfws115a.team1.caloriecounter.activities.quantityunitmanagement;

import android.view.View;
import de.fhdw.bfws115a.team1.caloriecounter.R;
import de.fhdw.bfws115a.team1.caloriecounter.database.DatabaseUnit;

/**
 * Created by Florian on 08.11.2016.
 */


public class ClickListener implements View.OnClickListener {

    private ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {
        /* IDs müssen vom Layout noch zugewiesen werden! */
        switch (view.getId()) {
            //case für + Button
            case R.id.idQuantityUnitManagementAddNewQuantityUnit:
                mApplicationLogic.onAddNewQuantityUnitClicked();
                break;
        }
    }
}