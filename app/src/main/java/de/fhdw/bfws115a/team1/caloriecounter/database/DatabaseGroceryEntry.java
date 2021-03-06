package de.fhdw.bfws115a.team1.caloriecounter.database;

import de.fhdw.bfws115a.team1.caloriecounter.entities.FixGrocery;
import de.fhdw.bfws115a.team1.caloriecounter.entities.GroceryEntry;

/**
 * Created by xySha on 18.11.2016.
 */
public class DatabaseGroceryEntry extends GroceryEntry implements DatabaseEntity {
    private long mId;

    public DatabaseGroceryEntry(int year, int month, int day, FixGrocery fixGrocery, long id) {
        super(year, month, day, fixGrocery);
        mId = id;
    }

    @Override
    public long getId() {
        return mId;
    }

    public String toString() {
        return "DatabaseGroceryEntry(Id: " + getId() +", GroceryEntry: " + super.toString() + ")";
    }
}
