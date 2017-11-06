package wen.xiao.com.zhishiqi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Administrator on 2017/11/6.
 */

public class ViewPagerFragment extends Fragment {
    private static final String KEY = "extra";
    private String mMessage;
    public ViewPagerFragment() {
    }

    public static ViewPagerFragment newInstance(String extra) {
        Bundle args = new Bundle();
        args.putString(KEY, extra);
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mMessage = bundle.getString(KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager_item, container, false);
        TextView textView = (TextView) view.findViewById(R.id.fragment_text);
        textView.setText(mMessage);
        return view;
    }
}
