package com.soft.zigbang.src.login.register.interfaces;

public interface RegisterFragmentView {
    void signUpSuccess(String text, int code);

    void signUpFailure(String message);
}
