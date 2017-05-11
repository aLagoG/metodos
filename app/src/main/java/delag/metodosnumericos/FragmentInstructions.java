package delag.metodosnumericos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;

public class FragmentInstructions extends Fragment {

    Fragment next;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instructions, container, false);
        next = (Fragment) getArguments().get("Next Fragment");
        FloatingActionButton btn = (FloatingActionButton) view.findViewById(R.id.instructions_ok_button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (next instanceof FragmentGaussSeidel || next instanceof FragmentGaussJordan) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Next Fragment", (Serializable) next);
                    next = new FragmentFillMatrix();
                    next.setArguments(bundle);
                }
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, next).commit();
            }
        });
        //noinspection ConstantConditions
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.instruction_texts);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 20);
        for (String s : (String[]) getArguments().get("Strings")) {
            TextView tv = new TextView(getActivity());
            tv.setLayoutParams(lp);
            tv.setText(s);
            layout.addView(tv);
        }
        return view;
    }
}
