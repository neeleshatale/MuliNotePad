package multinotepad.iit.com.mulinotepad.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import multinotepad.iit.com.mulinotepad.R;
import multinotepad.iit.com.mulinotepad.models.Note;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> {
    private List<Note> notes;
    private Context context;

    public NoteListAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = notes.get(position);
        if (note != null) {
            holder.tvtitle.setText(note.getTitle());
            holder.tvLastSavedDate.setText(note.getLastSavedDate());
            holder.tvNote.setText(note.getNoteText());
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout llListItem;
        TextView tvtitle;
        TextView tvLastSavedDate;
        TextView tvNote;

        ViewHolder(View itemView) {
            super(itemView);
            llListItem = itemView.findViewById(R.id.ll_list_item);
            tvtitle = itemView.findViewById(R.id.tv_title);
            tvLastSavedDate = itemView.findViewById(R.id.tv_saved_date);
            tvNote = itemView.findViewById(R.id.tv_note);
            llListItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Coming Soon..", Toast.LENGTH_LONG).show();
        }
    }
}
