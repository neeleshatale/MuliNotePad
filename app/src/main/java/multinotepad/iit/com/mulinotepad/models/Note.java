package multinotepad.iit.com.mulinotepad.models;

import java.io.Serializable;

public class Note implements Serializable {
    private String title;
    private String lastSavedDate;
    private String noteText;

    public Note(String title, String lastSavedDate, String noteText) {
        this.title = title;
        this.lastSavedDate = lastSavedDate;
        this.noteText = noteText;
    }

    public String getLastSavedDate() {
        return lastSavedDate;
    }

    public void setLastSavedDate(String lastSavedDate) {
        this.lastSavedDate = lastSavedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    @Override
    public String toString() {
        return "Note{" +
                "lastSavedDate='" + lastSavedDate + '\'' +
                ", title='" + title + '\'' +
                ", noteText='" + noteText + '\'' +
                '}';
    }
}
