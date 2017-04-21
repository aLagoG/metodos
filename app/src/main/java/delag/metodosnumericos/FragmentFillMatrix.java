package delag.metodosnumericos;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentFillMatrix extends Fragment implements DialogMatrixSize.DialogListener {

    int altoMatriz;
    int anchoMatriz;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DialogFragment dialog = new DialogMatrixSize();
        dialog.show(getFragmentManager(), "Matrix Size Dialog");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fill_matrix, container, false);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        altoMatriz = Integer.parseInt(((EditText) dialog.getDialog().findViewById(R.id.alto_matriz)).getText().toString());
        anchoMatriz = Integer.parseInt(((EditText) dialog.getDialog().findViewById(R.id.ancho_matriz)).getText().toString());
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        ((NavigationView) getActivity().findViewById(R.id.nav_view)).getMenu().performIdentifierAction(R.id.inicio, 0);
    }
}
