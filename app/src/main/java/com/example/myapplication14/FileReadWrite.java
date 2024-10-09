package com.example.myapplication14;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileReadWrite {
    private static final String textFileName = "persons2.txt";

    public static void writeFile(ArrayList<Person> personArrList, Context context) {
        if (checkExternalStorageState()) {
            File txtFile = new File(context.getExternalFilesDir(null), textFileName);
            try (FileOutputStream fos = new FileOutputStream(txtFile);
                 OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF8");
                 BufferedWriter writer = new BufferedWriter(osw)) {

                writeHeader(writer);

                for (Person p : personArrList) {
                    write2File(p, writer);
                }
            } catch (IOException e) {
                Log.e("WriteToFile", "Error writing to file: " + textFileName);
                e.printStackTrace();
            }
        } else {
            Log.e("writeFile", "Cannot access file '" + textFileName + "' - external storage not available or not writable");
        }
    }
    public static void readFileFromResources(ArrayList<Person> personArrList, Context context) {
        try (InputStream is = context.getResources().openRawResource(R.raw.persons);
             InputStreamReader isr = new InputStreamReader(is, "UTF8");
             BufferedReader reader = new BufferedReader(isr)) {
            personArrList.clear();
            String strLine = reader.readLine();
            strLine = reader.readLine();

            while (strLine != null) {
                Person p = FileReadWrite.readPerson(strLine);
                personArrList.add(p);
                Log.d("Read Person ", p.toString());
                strLine = reader.readLine();
            }
        }catch (IOException e) {
            Log.e("ReadFromFile", "Error reading from file: persons.txt");
            e.printStackTrace();
        }
    }


    public static void readFile(ArrayList<Person> personArrList, Context context) {
        if (checkExternalStorageState())
        {
            File txtFile = new File(context.getExternalFilesDir(null), textFileName);
            try (FileInputStream fis = new FileInputStream(txtFile);
                 InputStreamReader isr = new InputStreamReader(fis, "UTF8");
                 BufferedReader reader = new BufferedReader(isr)                         )
            {
                personArrList.clear();
                String strLine = reader.readLine();
                strLine = reader.readLine();
                while (strLine != null)
                {
                    Person p = readPerson(strLine);
                    personArrList.add(p);
                    Log.d("Read Person", p.toString());
                    strLine = reader.readLine();
                }
            }
            catch (IOException e) {
                Log.e("ReadFromFile", "Error reading from file: persons.txt");
                e.printStackTrace();
            }
        }
        else {
            Log.e("readFile", "Cannot access file '" + textFileName + "' - external storage not available or not readable");
        }
    }

    private static boolean checkExternalStorageState() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            return true;
        }
        return false;
    }
    private static void write2File(Person p, BufferedWriter writer) throws IOException {
        writer.append(p.firstName  + ",");
        writer.append(p.lastName  + ",");
        writer.append(p.phone + ",");
        writer.append(p.gender.toString()  + "\n");
    }
    private static void writeHeader(BufferedWriter writer) throws IOException {
        writer.append("First name"  + ",");
        writer.append("Last name " + ",");
        writer.append("phone" + ",");
        writer.append("Gender" + "\n");
    }

    private static Person readPerson(String line){
        String[] data = line.split(",");

        Person p = new Person();
        p.firstName = data[0].trim();
        p.lastName = data[1].trim();
        p.phone = data[2].trim();
        p.gender = Gender.valueOf(data[3].trim());
        return p;
    }

}
