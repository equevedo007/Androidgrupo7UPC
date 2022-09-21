package com.example.androidgrupo7upc.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidgrupo7upc.R;
import com.example.androidgrupo7upc.model.PatientType;

import java.util.List;

public class PacienteAdapter extends RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder> {

    private final Context mContext;
    private final List<PatientType> mListaPaciente;

    public PacienteAdapter(Context mContext, List<PatientType> listaPaciente) {
        this.mContext = mContext;
        this.mListaPaciente = listaPaciente;
    }

    @NonNull
    @Override
    public PacienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.listview_patient_row, parent, false);

        return new PacienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PacienteViewHolder holder, int position) {
        PatientType patientBean = mListaPaciente.get(position);
        TextView txtNombresItem, txtApellidosItem, txtNroDocItem;

        txtNombresItem = holder.txtNombresItem;
        txtApellidosItem = holder.txtApellidosItem;
        txtNroDocItem = holder.txtNroDocItem;

        txtNombresItem.setText(patientBean.getNombres());
        txtApellidosItem.setText(String.format("%s %s", patientBean.getApePaterno(), patientBean.getApeMaterno()));
        txtNroDocItem.setText(patientBean.getNumeroDocumento());
    }

    @Override
    public int getItemCount() {
        return mListaPaciente.size();
    }

    public static class PacienteViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombresItem, txtApellidosItem, txtNroDocItem;

        public PacienteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombresItem = itemView.findViewById(R.id.txtName);
            txtApellidosItem = itemView.findViewById(R.id.txtLastName);
            txtNroDocItem = itemView.findViewById(R.id.txtDocumentNumber);
        }
    }
}
