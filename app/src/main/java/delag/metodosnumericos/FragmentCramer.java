package delag.metodosnumericos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by Santino on 13/05/2017.
 */

public class FragmentCramer extends Fragment implements Serializable{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cramer, container, false);
        double[][] matrix = (double[][]) getArguments().get("Matrix");
        Cramer cr = new Cramer(matrix, null);
        double[] results = cr.doMethod();
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.cramer_results);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 20);
        for (int i = 0; i < results.length; i++) {
            TextView tv = new TextView(getActivity());
            tv.setLayoutParams(lp);
            tv.setText("x" + (i + 1) + ": " + results[i]);
            layout.addView(tv);
        }
        return view;
    }
}

