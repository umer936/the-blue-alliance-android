package com.thebluealliance.androidclient.database.tables;

import android.content.ContentValues;
import android.database.Cursor;

import com.thebluealliance.androidclient.database.Database;
import com.thebluealliance.androidclient.database.ModelInflater;
import com.thebluealliance.androidclient.models.StoredNotification;

import java.util.ArrayList;

public class NotificationsTable {
    public static final String
            ID = "_id",
            TYPE = "type",
            TITLE = "title",
            BODY = "body",
            INTENT = "intent",
            TIME = "time",
            SYSTEM_ID = "system_id",
            ACTIVE = "active";

    private Database mDb;

    public NotificationsTable(Database mDb) {
        this.mDb = mDb;
    }

    public void add(StoredNotification... in) {
        mDb.beginTransaction();
        for (StoredNotification notification : in) {
            mDb.insert(Database.TABLE_NOTIFICATIONS, null, notification.getParams());
        }
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
    }

    public ArrayList<StoredNotification> get() {
        ArrayList<StoredNotification> out = new ArrayList<>();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + Database.TABLE_NOTIFICATIONS + " ORDER BY " + ID + " DESC", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                out.add(ModelInflater.inflateStoredNotification(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return out;
    }

    public ArrayList<StoredNotification> getActive() {
        ArrayList<StoredNotification> out = new ArrayList<>();
        Cursor cursor = mDb.query(Database.TABLE_NOTIFICATIONS, null, ACTIVE + " = 1", null, null, null, ID + " DESC", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                out.add(ModelInflater.inflateStoredNotification(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return out;
    }

    public void dismissAll() {
        ContentValues cv = new ContentValues();
        cv.put(ACTIVE, 0);
        mDb.update(Database.TABLE_NOTIFICATIONS, cv, ACTIVE + "= 1", null);
    }

    private void delete(int id) {
        mDb.delete(Database.TABLE_NOTIFICATIONS, ID + " = ? ", new String[]{Integer.toString(id)});
    }

    // Only allow 50 notifications to be stored
    public void prune() {
        mDb.beginTransaction();
        Cursor cursor = mDb.query(Database.TABLE_NOTIFICATIONS, new String[]{ID}, "", new String[]{}, null, null, ID + " ASC", null);
        if (cursor != null && cursor.moveToFirst()) {
            for (int i = cursor.getCount(); i > 50; i--) {
                mDb.delete(Database.TABLE_NOTIFICATIONS, ID + " = ?", new String[]{cursor.getString(cursor.getColumnIndex(ID))});
                if (!cursor.moveToNext()) {
                    break;
                }
            }
        }
        cursor.close();
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
    }
}
