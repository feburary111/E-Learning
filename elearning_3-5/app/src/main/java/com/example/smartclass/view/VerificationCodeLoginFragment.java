package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartclass.R;

import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/1/30
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class VerificationCodeLoginFragment extends Fragment {

    public static VerificationCodeLoginFragment newInstance() {

        Bundle args = new Bundle();

        VerificationCodeLoginFragment fragment = new VerificationCodeLoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_verification_code_login, container, false);
        ButterKnife.bind(this, root);
        return root;
    }
}
