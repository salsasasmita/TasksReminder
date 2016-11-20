package id.sch.smktelkom_mlg.project.xiirpl104142434.tasksreminder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimas Bramantyo on 11/20/2016.
 */
public class RecyclerAdapterSubject extends RecyclerView.Adapter<RecyclerAdapterSubject.ViewHolder> {

    static List<DatabaseModelSubject> dbList;
    static Context context;

    RecyclerAdapterSubject(Context context, List<DatabaseModelSubject> dbList) {
        RecyclerAdapterSubject.dbList = new ArrayList<DatabaseModelSubject>();
        RecyclerAdapterSubject.context = context;
        RecyclerAdapterSubject.dbList = dbList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_subject, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterSubject.ViewHolder holder, int position) {

        holder.subject.setText(dbList.get(position).getSubject());
        holder.teacher.setText(dbList.get(position).getTeacher());

    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView subject, teacher;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            subject = (TextView) itemLayoutView.findViewById(R.id.tvSubject);
            teacher = (TextView) itemLayoutView.findViewById(R.id.tvTeacher);
            itemLayoutView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ListSubject.class);

            Bundle extras = new Bundle();
            extras.putInt("position", getAdapterPosition());
            intent.putExtras(extras);

            /*
            int i=getAdapterPosition();
            intent.putExtra("position", getAdapterPosition());*/
            context.startActivity(intent);
            Toast.makeText(RecyclerAdapterSubject.context, "you have clicked Row " + getAdapterPosition(), Toast.LENGTH_LONG).show();
        }
    }
}

