package com.esgi.framework_JEE.basket.infrastructure.web.response;

public class BasketResponse {

    private int userId;

    public int getUserId() {
        return userId;
    }

    public BasketResponse setUserId(int userId) {
        this.userId = userId;
        return this;
    }
}
