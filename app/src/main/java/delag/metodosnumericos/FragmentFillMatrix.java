package delag.metodosnumericos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

public class FragmentFillMatrix extends Fragment implements DialogMatrixSize.DialogListener {

    int altoMatriz;
    int anchoMatriz;
    EditText[][] textosMatriz;
    double[][] matriz;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DialogFragment dialog = new DialogMatrixSize();
        dialog.show(getFragmentManager(), "Matrix Size Dialog");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fill_matrix, container, false);
        FloatingActionButton btn = (FloatingActionButton)  view.findViewById(R.id.ok_button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                matriz = new double[altoMatriz][anchoMatriz];
                for(int i=0;i<altoMatriz;i++){
                    for(int j=0;j<anchoMatriz;j++){
                        matriz[i][j] = Double.parseDouble(textosMatriz[i][j].getText().toString());
                    }
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("Matrix",matriz);
                FragmentGaussJordan fragment = new FragmentGaussJordan();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container,fragment).commit();
            }
        });
        return view;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        altoMatriz = Integer.parseInt(((EditText) dialog.getDialog().findViewById(R.id.alto_matriz)).getText().toString());
        anchoMatriz = Integer.parseInt(((EditText) dialog.getDialog().findViewById(R.id.ancho_matriz)).getText().toString());
        TableLayout matrixTable = (TableLayout) getView().findViewById(R.id.matrix_table);
        textosMatriz = new EditText[altoMatriz][anchoMatriz];
        for (int i = matrixTable.getChildCount(); i < altoMatriz; i++) {
            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < anchoMatriz; j++) {
                EditText text = new EditText(getActivity());
                text.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                text.setLayoutParams(new TableRow.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT, 1));
                text.setText("0");
                text.setMinEms(3);
                row.addView(text, j);
                textosMatriz[i][j] = text;
            }
            matrixTable.addView(row, i);
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        ((NavigationView) getActivity().findViewById(R.id.nav_view)).getMenu().performIdentifierAction(R.id.inicio, 0);
    }
}
