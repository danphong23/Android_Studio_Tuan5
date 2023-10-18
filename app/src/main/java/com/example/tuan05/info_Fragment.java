package com.example.tuan05;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link info_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class info_Fragment extends Fragment implements FragmentCallbacks {
    MainActivity main;
    static TextView tv_id;
    static TextView tv_name;
    static TextView tv_class;
    static TextView tv_score;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public info_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment info_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static info_Fragment newInstance(String param1, String param2) {
        info_Fragment fragment = new info_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException( "Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout layout_info = (LinearLayout) inflater.inflate(R.layout.layout_information, null);
        tv_id = (TextView) layout_info.findViewById(R.id.tv_id);
        tv_name = (TextView) layout_info.findViewById(R.id.tv_name);
        tv_class = (TextView) layout_info.findViewById(R.id.tv_class);
        tv_score = (TextView) layout_info.findViewById(R.id.tv_score);
        try {
            Bundle arguments = getArguments();
            tv_id.setText(arguments.getString("id", ""));
            tv_name.setText(arguments.getString("name", ""));
            tv_class.setText(arguments.getString("class", ""));
            tv_score.setText(arguments.getString("score", ""));

        }
        catch (Exception e)
        {
            Log.e("RED BUNDLE ERROR – ", "" + e.getMessage());
        }
        return layout_info;
    }


    @Override
    public void onMsgFromMainToFragment(String[] data)
    {
        tv_id.setText(data[0]);
        tv_name.setText("Họ tên: "+data[1]);
        tv_class.setText("Lớp: "+ data[2]);
        tv_score.setText(("Điểm trung bình: "+ data[3]));
    }
}