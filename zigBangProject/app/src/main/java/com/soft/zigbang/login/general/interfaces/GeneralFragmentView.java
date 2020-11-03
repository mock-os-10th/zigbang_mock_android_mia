package com.soft.zigbang.src.login.general.interfaces;

public interface GeneralFragmentView {
    void generalLoginSuccess(String text, int userNo);

    void generalLoginFailure(String message);
}
