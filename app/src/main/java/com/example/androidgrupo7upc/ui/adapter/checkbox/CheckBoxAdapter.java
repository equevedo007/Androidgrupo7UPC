package com.example.androidgrupo7upc.ui.adapter.checkbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidgrupo7upc.R;
import com.example.androidgrupo7upc.model.IllnessType;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxAdapter extends RecyclerView.Adapter<CheckBoxAdapter.CheckBoxViewHolder> {

    private final Context mContext;
    private final List<IllnessType> mListaEnfermedad;

    private final CheckBoxListener mListener;
    private final List<String> finalList = new ArrayList<>();

    public CheckBoxAdapter(Context mContext, List<IllnessType> listaEnfermedad, CheckBoxListener listener) {
        this.mContext = mContext;
        this.mListaEnfermedad = listaEnfermedad;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public CheckBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.listview_illness_checkbox_row, parent, false);

        return new CheckBoxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckBoxViewHolder holder, int position) {
        IllnessType illnessBean = mListaEnfermedad.get(position);

        MaterialCheckBox chkIllness;

        chkIllness = holder.chkIllness;

        chkIllness.setText(mListaEnfermedad.get(position).getIllnessDesc());

        chkIllness.setOnClickListener(view -> {
            if (chkIllness.isChecked()) {
                finalList.add(illnessBean.getIllnessId());
            } else {
                finalList.remove(illnessBean.getIllnessId());
            }
            mListener.onItemClick(finalList);
        });
    }

    @Override
    public int getItemCount() {
        return mListaEnfermedad.size();
    }

    public static class CheckBoxViewHolder extends RecyclerView.ViewHolder {

        MaterialCheckBox chkIllness;

        public CheckBoxViewHolder(@NonNull View itemView) {
            super(itemView);
            chkIllness = itemView.findViewById(R.id.chkIllness);
        }
    }
}
