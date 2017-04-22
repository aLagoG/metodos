package delag.metodosnumericos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.Space;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class FragmentGaussJordan extends Fragment {

    GaussJordan gj;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        double[][] matrix = (double[][]) getArguments().get("Matrix");
        gj = new GaussJordan(matrix);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gauss_jordan, container, false);
        TableLayout layout = (TableLayout) view.findViewById(R.id.table_gauss_jordan);
        do {
            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            TextView text = new TextView(getActivity());
            text.setText(gj.numberOfSteps==0?"Matriz inicial":"Paso "+gj.numberOfSteps);
            text.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT, 1));
            text.setTextSize(20);
            text.setTextColor(getResources().getColor(R.color.colorBlack));
            row.addView(text);
            layout.addView(row);
            for (int i = 0; i < gj.height; i++) {
                row = new TableRow(getActivity());
                row.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.WRAP_CONTENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                row.setPadding(40,0,0,40);
                for (int j = 0; j < gj.length; j++) {
                    text = new TextView(getActivity());
                    text.setText(String.valueOf(gj.matrix[i][j]));
                    text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    text.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT, 1));
                    text.setPadding(10,5,10,5);
                    text.setTextSize(20);
                    text.setTextColor(getResources().getColor(R.color.colorBlack));
                    row.addView(text);
                }
                layout.addView(row);
            }
            row = new TableRow(getActivity());
            row.setMinimumHeight(10);
            layout.addView(row);
        } while (gj.doGaussStep() != null);
        return view;
    }

}
