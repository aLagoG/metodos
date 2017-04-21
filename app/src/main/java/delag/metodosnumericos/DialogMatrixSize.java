package delag.metodosnumericos;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class DialogMatrixSize extends DialogFragment {

    public interface DialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);

        public void onDialogNegativeClick(DialogFragment dialog);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mListener = (DialogListener) getActivity().getSupportFragmentManager().findFragmentById(R.id.frame_container);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_matrix_size, null))
                .setTitle("Dimensiones de la matriz")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(DialogMatrixSize.this);
                    }
                });

        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((EditText) DialogMatrixSize.this.getDialog().findViewById(R.id.alto_matriz)).getText().length() == 0 ||
                        ((EditText) DialogMatrixSize.this.getDialog().findViewById(R.id.ancho_matriz)).getText().length() == 0) {
                    return;
                }
                DialogMatrixSize.this.mListener.onDialogPositiveClick(DialogMatrixSize.this);
                dialog.dismiss();
            }
        });

//        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogMatrixSize.this.mListener.onDialogNegativeClick(DialogMatrixSize.this);
//                dialog.dismiss();
//            }
//        });
        return dialog;
    }

    DialogListener mListener;
}
