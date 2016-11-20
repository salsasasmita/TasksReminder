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
 * Created by user_adnig on 11/14/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    static List<DatabaseModel> dbList;
    static Context context;

    RecyclerAdapter(Context context, List<DatabaseModel> dbList) {
        RecyclerAdapter.dbList = new ArrayList<DatabaseModel>();
        RecyclerAdapter.context = context;
        RecyclerAdapter.dbList = dbList;

    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_row, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {

        holder.taskname.setText(dbList.get(position).getTaskname());
        holder.idsubject.setText(dbList.get(position).getSubject());
        holder.duedate.setText("due " + dbList.get(position).getDuedates());

    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView taskname, idsubject, duedate;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            taskname = (TextView) itemLayoutView
                    .findViewById(R.id.rvname);
            idsubject = (TextView) itemLayoutView.findViewById(R.id.rvidsubject);
            itemLayoutView.setOnClickListener(this);
            duedate = (TextView) itemLayoutView.findViewById(R.id.textViewdue);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, EditTask.class);

            Bundle extras = new Bundle();
            extras.putInt("position", getAdapterPosition());
            intent.putExtras(extras);

            /*
            int i=getAdapterPosition();
            intent.putExtra("position", getAdapterPosition());*/
            context.startActivity(intent);
            Toast.makeText(RecyclerAdapter.context, "you have clicked Row " + getAdapterPosition(), Toast.LENGTH_LONG).show();
        }
    }
}
