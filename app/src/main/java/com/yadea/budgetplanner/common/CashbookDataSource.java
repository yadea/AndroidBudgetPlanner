package com.yadea.budgetplanner.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yadea.budgetplanner.model.Cashbook;

import java.sql.SQLException;

public class CashbookDataSource {
    // Database fields
    private SQLiteDatabase database;
    private SqlLiteHelper dbHelper;

    public CashbookDataSource(Context context) {
        dbHelper = new SqlLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void newEntry (Cashbook cashbook) {
        try {
            open();
            ContentValues values = new ContentValues();
            values.put("Expense", cashbook.Expense);
            values.put("Title", cashbook.Title);
            values.put("Category", cashbook.Category);
            values.put("Date", cashbook.Date.toString());
            values.put("Amount", cashbook.Amount);
            long insertId = database.insert("Cashbook", null,
                    values);
            close();
        }catch(Exception ex) {
            Log.e("db ", ex.toString());
        }
    }

    /*public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(1));
        return comment;
    }*/
}
