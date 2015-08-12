package com.yadea.budgetplanner.common;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by yadea on 9/8/2015.
 */
public class Logger {
    final String FILE_PATH = "//sdcard//";
    Context context;
    public Logger(Context c){
        context = c;
    }

    public void newLog(String title, String message) {
        Date dateTime = new Date();
        final String FILENAME = "current";

        //insert into the log
        insertLog(FILENAME, String.format("%s - %s: %s", dateTime, title, message));
    }

    public void newSeparator(char separatorType) {
        final String FILENAME = "current";
        String line = "";

        //generate the separator line
        for(int i=0; i<20; i++) {
            line+=separatorType;
        }

        //insert into the log
        insertLog(FILENAME, line);
    }

    private void insertLog(String filename, String message) {
        ArrayList<String> logItems = logReader(filename);

        //append the StringBuilder with the new logged item
        logItems.add(message);

        logSaver(filename, logItems);
    }

    private ArrayList<String> logReader(String filename) {
        File file = new File(FILE_PATH + filename + ".log");
        ArrayList <String> logItems = new ArrayList();
        InputStream inputStream;

        Log.e("path", file.getAbsolutePath());
        //Log.e("parent", file.getParent());

        try{
            inputStream = new BufferedInputStream(new FileInputStream(filename+".log"));//context.openFileInput(filename+".log");
            Log.e("location", inputStream.)
            Toast.makeText(context, inputStream.read(), Toast.LENGTH_SHORT);
        }
        catch (Exception ex) {
            Log.e("read","error reading: " + ex);
        }
        /*if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file.getPath());
                Log.e("read","file exist");
                //read the file and load it to StringBuilder
                while (scanner.hasNext()){
                    String text = scanner.nextLine();
                    Log.e("reading", text);
                    logItems.add(text);
                    //logItems.add(scanner.nextLine());
                }

                //close the Scanner once its done
                scanner.close();
            }
            catch (Exception ex){
                Log.e("BudgetPlanner-logread",ex.toString());
                return null;
            }
        }
        else {
            Log.e("reader","file doesnt exist");
        }*/

        return logItems;
    }

    private void logSaver(String filename, ArrayList <String> logItems) {
        try {
            //File file = new File(context.getFilesDir(), filename+".log");
            //OutputStreamWriter writer = new OutputStreamWriter(fileWriter);
            FileOutputStream outputStream;

                outputStream = context.openFileOutput(filename + ".log", Context.MODE_PRIVATE);
            //writes the log file
            for (String logLine:logItems) {
                outputStream.write(logLine.getBytes());
            }
            Log.e(">>>>>>>", "file saved");
            //close the BufferedWriter when completed
            outputStream.close();
        }
        catch (Exception ex) {
            Log.e("BudgetPlanner-logsave", ex.toString()); }
    }

}
