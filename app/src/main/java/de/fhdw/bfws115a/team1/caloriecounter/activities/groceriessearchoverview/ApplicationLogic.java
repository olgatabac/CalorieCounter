package de.fhdw.bfws115a.team1.caloriecounter.activities.groceriessearchoverview;

import android.app.Activity;
import android.content.Intent;
import de.fhdw.bfws115a.team1.caloriecounter.constants.SearchSettings;
import de.fhdw.bfws115a.team1.caloriecounter.entities.*;

import java.util.ArrayList;

public class ApplicationLogic {

    private Data mData;
    private Gui mGui;

    private ListAdapter mListAdapter;

    public ApplicationLogic(Data data, Gui gui) {
        mData = data;
        mGui = gui;

        initGui();
        initAdapter();
        initListener();
    }

    private void initGui() {
        ArrayList<GroceriesEntity> mGroceriesEntityList = mData.getGroceriesEntityList();
        mGui.getListView().setEmptyView(mGui.getEmptyListTextView());
    }

    private void initAdapter() {
        mListAdapter = new ListAdapter(mData, this);
        mGui.getListView().setAdapter(mListAdapter);
    }

    private void initListener() {
        ClickListener cl = new ClickListener(this);
        TextListener tl = new TextListener(this);

        mGui.getListView().setOnItemClickListener(mListAdapter);
        mGui.getListView().setOnItemSelectedListener(mListAdapter);
        mGui.getSearchView().setOnQueryTextListener(tl);
        mGui.getAddGroceryButton().setOnClickListener(cl);
        mGui.getAddMenuButton().setOnClickListener(cl);
    }

    public void reload() {
        mData.getActivity().finish();
        mData.getActivity().startActivity(mData.getActivity().getIntent());
    }

    public void filterListByName(String name) {
        mListAdapter.getFilter().filter(name);
    }

    public void onItemSelected(GroceriesEntity groceriesEntity) {
        mData.setSelectedEntity(groceriesEntity);

        Intent intent = new Intent(mData.getActivity(), de.fhdw.bfws115a.team1.caloriecounter.activities.selectamount.Init.class);
        intent.putExtra("groceriesEntity", mData.getSelectedEntity());
        mData.getActivity().startActivityForResult(intent, ResultCodes.SELECT_AMOUNT);
    }

    public void onSelectAmountResult(Intent data) {
        Unit selectedUnit = (Unit) data.getSerializableExtra("unit");
        double selectedAmount = data.getDoubleExtra("amount", 0.0);

        GroceriesEntity groceriesEntity = null;

        if (mData.getSelectedEntity() instanceof Grocery) {
            Grocery selectedGrocery = (Grocery) mData.getSelectedEntity();
            GroceryUnit groceryUnit = null;

            for (GroceryUnit gu : selectedGrocery.getGroceryUnits()) {
                if (gu.getUnit().getName().equals(selectedUnit.getName())) {
                    groceryUnit = gu;
                    break;
                }
            }

            if (groceryUnit != null) {
                groceriesEntity = new FixGrocery(
                        mData.getSelectedEntity().getName(),
                        groceryUnit.getUnit(),
                        selectedAmount,
                        (int) Math.round((selectedGrocery.getKcal() / groceryUnit.getAmount()) * selectedAmount)
                );
            }
        } else if (mData.getSelectedEntity() instanceof Menu) {
            Menu selectedMenu = (Menu) mData.getSelectedEntity();
            // TODO: check calculation of menu kcal with set amount
            selectedMenu.setAmount(selectedAmount);
            groceriesEntity = selectedMenu;
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra("groceriesEntity", groceriesEntity);
        mData.getActivity().setResult(Activity.RESULT_OK, resultIntent);
        mData.getActivity().finish();
    }

    public void onCreateNewGroceryClicked() {
        Intent intent = new Intent(mData.getActivity(), de.fhdw.bfws115a.team1.caloriecounter.activities.grocerymanagement.Init.class);
        mData.getActivity().startActivityForResult(intent, ResultCodes.RELOAD);
    }

    public void onCreateNewMenuClicked() {
        Intent intent = new Intent(mData.getActivity(), de.fhdw.bfws115a.team1.caloriecounter.activities.menumanagement.Init.class);
        mData.getActivity().startActivityForResult(intent, ResultCodes.RELOAD);
    }
}
