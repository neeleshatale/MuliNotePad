package multinotepad.iit.com.mulinotepad.activities;

import android.app.Activity;
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
        getNotes();
        setAdapter();
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
        switch (v.getId()) {
            case R.id.img_about:
                Toast.makeText(MainActivity.this, "Coming Soon..", Toast.LENGTH_LONG).show();
                break;
            case R.id.img_add:
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
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
            }
        }
    }
}
