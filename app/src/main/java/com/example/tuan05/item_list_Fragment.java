package com.example.tuan05;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link item_list_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class item_list_Fragment extends Fragment implements FragmentCallbacks {

    MainActivity main;
    Context context = null;
    String message = "";

    TextView tv_id;
    ListView lv_item_list;

    CustomIconLabelAdapter info;

    String ids[] = {"A1_0001", "A1_0002", "A1_0003", "A2_0001","A2_0002","A2_0003","A2_0004","A3_0001","A3_0002","A3_0003"};
    Integer icons[] = {R.drawable.face_1, R.drawable.face_2, R.drawable.face_3,
            R.drawable.face_4, R.drawable.face_5, R.drawable.face_6, R.drawable.face_1, R.drawable.face_2, R.drawable.face_3,
            R.drawable.face_4};
    String names[] = {"Nguyen Van A", "Nguyen Van B", "Nguyen Van C", "Nguyen Van D", "Nguyen Van E", "Nguyen Van F", "Nguyen Van G", "Nguyen Van H", "Nguyen Van I", "Nguyen Van K", "Nguyen Van L"};
    String lops[] = {"A1", "A1", "A1", "A2", "A2", "A2", "A2", "A3", "A3", "A3"};
    String scores[] = {"9", "0", "7", "8", "5", "1", "4", "9", "8", "6"};



//    lưu vị trí item được chọn
    int index=-1, pre_index=-1;
    String in;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public item_list_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment item_list_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static item_list_Fragment newInstance(String param1, String param2) {
        item_list_Fragment fragment = new item_list_Fragment();
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
        try {
            context = getActivity();
            main = (MainActivity) getActivity();
        }catch (IllegalStateException e)
        {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout layout_list_item = (LinearLayout) inflater.inflate(R.layout.layout_list_item, null);
        tv_id = (TextView) layout_list_item.findViewById(R.id.tv_id);
        lv_item_list = (ListView) layout_list_item.findViewById(R.id.lv_item_list);
        info = new CustomIconLabelAdapter(context, R.layout.custom_layout_information,ids, names, lops, scores ,icons);
        lv_item_list.setAdapter(info);
        lv_item_list.setSelection(0);
        lv_item_list.smoothScrollToPosition(0);

        lv_item_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                pre_index=index;
                index=i;
                in=Integer.toString(i);
                if(pre_index!=-1){
                    lv_item_list.getChildAt(pre_index).setBackgroundResource(androidx.cardview.R.color.cardview_light_background);
                }

                lv_item_list.getChildAt(index).setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);

                String[] data = {ids[i], names[i], lops[i], scores[i], in};
                main.onMsgFromFragLToMain("LIST-ITEM-FRAG", data);
                tv_id.setText("Mã số: " + info.ids[i]);
                index = i;
            }
        });




        return layout_list_item;
    }

    //Khong su dung
    @Override
    public void onMsgFromMainToFragmentI(String[] data) {

    }

    @Override
    public void onMsgFromMainToFragmentL(String in) {
        Integer i=Integer.parseInt(in);
        pre_index=index;
        index=i;
        in=Integer.toString(i);
        if(pre_index!=-1){
            lv_item_list.getChildAt(pre_index).setBackgroundResource(androidx.cardview.R.color.cardview_light_background);
        }

        lv_item_list.getChildAt(index).setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);

        String[] data = {ids[i], names[i], lops[i], scores[i], in};
        main.onMsgFromFragLToMain("LIST-ITEM-FRAG", data);
        tv_id.setText("Mã số: " + info.ids[i]);
        index = i;
    }
}


class CustomIconLabelAdapter extends ArrayAdapter<String> {
    Context context;
    String[] ids;
    String[] names;
    String[] lops;
    String[] scores;
    Integer[] icons;
    //    hàm tạo với tham số đầu vào
    public CustomIconLabelAdapter(Context context, int layoutToBeInflated, String[] ids, String[] names, String[] lops, String[] scores, Integer[] icons) {
        super(context, R.layout.custom_layout_information, ids);
        this.context = context;
        this.ids = ids;
        this.names = names;
        this.lops = lops;
        this.scores = scores;
        this.icons = icons;
    }


    //    hàm getView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.custom_layout_information, null);
        TextView name = (TextView) row.findViewById(R.id.tv_name);
        ImageView icon = (ImageView) row.findViewById(R.id.icon);
        name.setText(ids[position]);
        icon.setImageResource(icons[position]);
        return (row);
    }


} // CustomAdapter
