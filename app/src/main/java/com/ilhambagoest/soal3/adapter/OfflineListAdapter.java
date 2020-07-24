package com.ilhambagoest.soal3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ilhambagoest.soal3.R;
import com.ilhambagoest.soal3.data.entity.DataOffline;

import java.util.ArrayList;
import java.util.List;

public class OfflineListAdapter extends RecyclerView.Adapter<OfflineListAdapter.ClubHolder> {

    private Context context;
    private List<DataOffline> offlines;
    private OnItemSelected onItemSelected;

    public OfflineListAdapter(Context context, OnItemSelected onItemSelected) {
        this.context = context;
        this.offlines = new ArrayList<>();
        this.onItemSelected = onItemSelected;
    }

    public List<DataOffline> getOfflines() {
        return offlines;
    }

    public void setOfflines(List<DataOffline> offlines) {
        this.offlines = offlines;
    }

    public void updateOfflines(List<DataOffline> offlines){
        setOfflines(offlines);
    }

    @NonNull
    @Override
    public ClubHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_list_offline, viewGroup, false);
        return new ClubHolder(view);
    }

    static class ClubHolder extends RecyclerView.ViewHolder {

        TextView tvId, tvName, tvJabatan, tvKeperluan;

        ClubHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvJabatan = itemView.findViewById(R.id.tv_jabatan);
            tvKeperluan = itemView.findViewById(R.id.tv_keperluan);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ClubHolder holder, int position) {

        DataOffline offline = offlines.get(position);

        holder.tvId.setText(offline.getId());
        holder.tvName.setText(offline.getNama());
        holder.tvJabatan.setText(offline.getJabatan());
        holder.tvKeperluan.setText(offline.getKeperluan());

        holder.itemView.setOnClickListener(v -> onItemSelected.onSelected(offline));

    }

    @Override
    public int getItemCount() {
        return offlines.size();
    }

    public interface OnItemSelected {
        void onSelected(DataOffline offline);
    }

}