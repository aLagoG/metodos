package delag.metodosnumericos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

public class FragmentInterpolationPolynomials extends Fragment implements Serializable {

    ArrayList<EditText[]> points = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_interpolation_polys, container, false);
        final Button btn = (Button) view.findViewById(R.id.button_ok_polys);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = ((EditText) view.findViewById(R.id.cantidad_puntos)).getText().toString();
                if (txt.length() == 0) {
                    return;
                }
                int n = Integer.parseInt(txt);
                if (n == 0) {
                    return;
                }
                LinearLayout layout = (LinearLayout) view.findViewById(R.id.polys_points_layout);
                if (layout.getChildCount() > n) {
                    layout.removeViews(n, layout.getChildCount() - n);
                    for (int i = points.size() - 1; i >= n; i--) {
                        points.remove(i);
                    }
                } else {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(20, 10, 20, 10);
                    lp.weight = 1;
                    for (int i = layout.getChildCount(); i < n; i++) {
                        LinearLayout lay = new LinearLayout(getActivity());
                        lay.setOrientation(LinearLayout.HORIZONTAL);
                        lay.setLayoutParams(lp);
                        EditText[] point = new EditText[2];
                        for (int k = 0; k < 2; k++) {
                            EditText text = new EditText(getActivity());
                            text.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                            text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            text.setText("0");
                            text.setLayoutParams(lp);
                            lay.addView(text);
                            point[k] = text;
                        }
                        layout.addView(lay);
                        points.add(point);
                    }
                }
            }
        });
        Button run = (Button) view.findViewById(R.id.button_run_polys);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TreeSet<Point> set = new TreeSet<>();
                for (EditText[] point : points) {
                    String txt = point[0].getText().toString();
                    if (txt.length() == 0) {
                        continue;
                    }
                    double x = Double.parseDouble(txt);

                    txt = point[1].getText().toString();
                    if (txt.length() == 0) {
                        continue;
                    }
                    double y = Double.parseDouble(txt);

                    set.add(new Point(x, y));
                }
                double[] xs = new double[set.size()];
                double[] ys = new double[set.size()];
                int cnt = 0;
                for (Point p : set) {
                    xs[cnt] = p.x;
                    ys[cnt++] = p.y;
                }
                String res = InterpolationPolynomials.getInterpolationPolinomial(xs, ys);
                ((TextView) view.findViewById(R.id.interpolation_result_text)).setText(res);
            }
        });
        return view;
    }
}
