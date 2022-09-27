package com.example.androidgrupo7upc.ui.adapter.doublecheckbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidgrupo7upc.R;
import com.example.androidgrupo7upc.model.IllnessType;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;
import java.util.List;

public class DoubleCheckBoxAdapter extends RecyclerView.Adapter<DoubleCheckBoxAdapter.DoubleCheckBoxViewHolder> {

    private final Context mContext;
    private final List<IllnessType> mListaEnfermedad;

    private final DoubleCheckBoxListener mListener;
    private final List<IllnessType> finalList = new ArrayList<>();

    public DoubleCheckBoxAdapter(Context mContext, List<IllnessType> listaEnfermedad, DoubleCheckBoxListener listener) {
        this.mContext = mContext;
        this.mListaEnfermedad = listaEnfermedad;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public DoubleCheckBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.listview_illness_double_checkbox_row, parent, false);

        return new DoubleCheckBoxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoubleCheckBoxViewHolder holder, int position) {
        IllnessType illnessBean = mListaEnfermedad.get(position);

        TextView txtIllnessTitle;
        MaterialCheckBox chkOD, chkOI;

        txtIllnessTitle = holder.txtIllnessTitle;
        chkOD = holder.chkOD;
        chkOI = holder.chkOI;

        txtIllnessTitle.setText(mListaEnfermedad.get(position).getIllnessDesc());

        chkOD.setOnClickListener(view -> {
            if (chkOD.isChecked()) {
                finalList.remove(illnessBean);
                illnessBean.setOD(true);
            } else {
                finalList.remove(illnessBean);
                illnessBean.setOD(false);
            }
            finalList.add(illnessBean);
            mListener.onDoubleItemClick(finalList);
        });

        chkOI.setOnClickListener(view -> {
            if (chkOI.isChecked()) {
                finalList.remove(illnessBean);
                illnessBean.setOI(true);
            } else {
                finalList.remove(illnessBean);
                illnessBean.setOI(false);
            }
            finalList.add(illnessBean);
            mListener.onDoubleItemClick(finalList);
        });
    }

    @Override
    public int getItemCount() {
        return mListaEnfermedad.size();
    }

    public static class DoubleCheckBoxViewHolder extends RecyclerView.ViewHolder {

        TextView txtIllnessTitle;
        MaterialCheckBox chkOD, chkOI;

        public DoubleCheckBoxViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIllnessTitle = itemView.findViewById(R.id.txtIllnessTitle);
            chkOD = itemView.findViewById(R.id.chkOD);
            chkOI = itemView.findViewById(R.id.chkOI);
        }
    }
}
