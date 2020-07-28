package ru.samsung.itschool.rvdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.samsung.itschool.rvdemo.database.Task;

public class TaskAdapter extends ListAdapter<Task, TaskAdapter.ItemViewHolder> {

    protected TaskAdapter(@NonNull TaskDiffCallback diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ItemViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (getCurrentList() == null) {
            holder.taskNameTextView.setText("No tasks");
        }
        else {
            Task task = getItem(position);
            holder.bind(task);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView taskNameTextView;
        private TextView taskDescrTextView;
        private ImageView taskStatusImageView;
        private ImageView taskImpImageView;

        private ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            taskNameTextView = itemView.findViewById(R.id.taskNametextView);
            taskDescrTextView = itemView.findViewById(R.id.taskDescrTextView);
            taskImpImageView = itemView.findViewById(R.id.taskImpImageView);
            taskStatusImageView = itemView.findViewById(R.id.taskStatusImageView);
        }

        public static ItemViewHolder from(@NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.rv_list_item, parent, false);
            return new ItemViewHolder(view);
        }

        public void bind(Task task) {
            this.taskNameTextView.setText(task.getTaskName());
            this.taskDescrTextView.setText(task.getTaskDescription());
            int resImp;
            switch (task.getTaskImportance()) {
                case Task.TASK_IMPORTANCE_HIGH: resImp = R.drawable.ic_baseline_looks_one_60; break;
                case Task.TASK_IMPORTANCE_LOW: resImp = R.drawable.ic_baseline_looks_3_60; break;
                default: resImp = R.drawable.ic_baseline_looks_two_60;
            }
            this.taskImpImageView.setImageResource(resImp);
            int resSt;
            switch (task.getTaskStatus()) {
                case Task.TASK_STATUS_FINISHED: resSt = R.drawable.ic_baseline_check_box_60; break;
                default: resSt = R.drawable.ic_baseline_check_box_outline_blank_60;
            }
            this.taskStatusImageView.setImageResource(resSt);
        }
    }
}
