package edu.upi.cs.yudiwbs.uas_template;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderHasil extends RecyclerView.ViewHolder {
    public TextView tvHasilWaktu, tvHasilAction;

    public ViewHolderHasil(@NonNull View itemView) {
        super(itemView);
        tvHasilWaktu = itemView.findViewById(R.id.tvRowWaktu);
        tvHasilAction = itemView.findViewById(R.id.tvRowAction);
    }
}
