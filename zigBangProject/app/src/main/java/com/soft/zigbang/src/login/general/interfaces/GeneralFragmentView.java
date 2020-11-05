package com.soft.zigbang.src.login.general.interfaces;

public interface GeneralFragmentView {
    void generalLoginSuccess(int userNo);

    void generalLoginFalse(String text);

    void generalLoginFailure(String message);
}
