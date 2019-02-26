package multinotepad.iit.com.mulinotepad.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import multinotepad.iit.com.mulinotepad.R;
import multinotepad.iit.com.mulinotepad.adapters.NoteListAdapter;
import multinotepad.iit.com.mulinotepad.models.Note;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView notesRecyclerView;
    private NoteListAdapter noteListAdapter;
    private TextView errorTextView;
    private Toolbar toolBar;
    private TextView toolBarTitle;
    private ImageButton imgAbout;
    private ImageButton imgEdit;
    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolBar = findViewById(R.id.toolbar);
        toolBarTitle = findViewById(R.id.toolbar_title);
        imgAbout = findViewById(R.id.img_about);
        imgEdit = findViewById(R.id.img_add);

        imgAbout.setVisibility(View.VISIBLE);
        imgEdit.setVisibility(View.VISIBLE);

        imgAbout.setOnClickListener(this);
        imgEdit.setOnClickListener(this);

        errorTextView = findViewById(R.id.tv_no_not_error);
        notesRecyclerView = findViewById(R.id.rv_notes);
        setSupportActionBar(toolBar);


        boolean isFilePresent = isFilePresent(MainActivity.this, "storage.json");
        if (isFilePresent) {
            String jsonString = read(MainActivity.this, "storage.json");
            Toast.makeText(MainActivity.this, jsonString, Toast.LENGTH_LONG).show();
            //do the json parsing here and do the rest of functionality of app
        } else {
            boolean isFileCreated = create(MainActivity.this, "storage.json");
            if (isFileCreated) {

                //proceed with storing the first todo  or show ui
            } else {
                //show error or try again.
            }
        }


        getNotes();
        setAdapter();
    }


    private String read(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }

    private boolean create(Context context, String fileName) {
        try {
            File newFile = new File(context.getFilesDir().getAbsolutePath(), fileName);
            newFile.createNewFile();
            return true;
        } catch (Exception fileNotFound) {
            return false;
        }

    }

    private void writeToFile(File file, String jsonString) {
        try {
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(jsonString);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }

    private void setAdapter() {
        if (noteList != null && !noteList.isEmpty()) {
            toolBarTitle.setText("Multi Note (" + noteList.size() + ")");
            errorTextView.setVisibility(View.GONE);
            notesRecyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            notesRecyclerView.setHasFixedSize(true);
            notesRecyclerView.setLayoutManager(llm);
            noteListAdapter = new NoteListAdapter(MainActivity.this, noteList);
            notesRecyclerView.setAdapter(noteListAdapter);
        } else {
            toolBarTitle.setText("Multi Note");
            errorTextView.setVisibility(View.VISIBLE);
            notesRecyclerView.setVisibility(View.GONE);
        }


    }

    @NonNull
    private void getNotes() {
        Note note = new Note("Title Buy USB Cable", "12/02/2018 Monday", "Lorem ipsum dolor sit amet, volutpat nullam nec,");
        noteList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            // noteList.add(note);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.img_about:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.img_add:
                intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, 101);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                Note note = (Note) data.getSerializableExtra("NEW_NOTE");
                noteList.add(note);
                setAdapter();
                writeToFile(new File(MainActivity.this.getFilesDir().getAbsolutePath(), "storage.json"), new Gson().toJson(note));
            }
        }
    }
}
