package com.neusoft.springboottest.user.dto;

public class BSSException extends RuntimeException {
    public static final BSSException USERNAME_NOT_EXISTS = new BSSException(504, "User doesn't exist", new Object[0]);
    public static final BSSException PASSWORD_WRONG = new BSSException(504, "Password is wrong", new Object[0]);
    public static final BSSException DUPLICATE_USERNAME = new BSSException(504, "Duplicate User name", new Object[0]);
    public static final BSSException NO_MAN_ID = new BSSException(504, "User doesn't register manufacture");
    public static final BSSException NO_PRODUCT = new BSSException(504, "No product available");
    public static final BSSException NO_BRAND = new BSSException(504, "No brand available");
    public static final BSSException PERMISSION_DENY = new BSSException(504, "Permission denied");
    public static final BSSException INSERT_FAIL = new BSSException(504, "Add operation failed", new Object[0]);
    public static final BSSException DELETE_FAIL = new BSSException(504, "Delete operation failed", new Object[0]);
    public static final BSSException UPDATE_FAIL = new BSSException(504, "Update operation failed", new Object[0]);
    public static final BSSException USERID_NULL_ERROR = new BSSException(504, "User null error", new Object[0]);
    public static final BSSException WALLET_NOT_EXISTS = new BSSException(504, "Wallet doesn't exist", new Object[0]);
    public static final BSSException HAVE_WALLET_FUND = new BSSException(504, "Has wallet fund", new Object[0]);
    public static final BSSException NOT_SUFFICIENT_FUNDS = new BSSException(504, "Not sufficient funds", new Object[0]);
    public static final BSSException BUYER_NOT_SUFFICIENT_FUNDS = new BSSException(504, "Not sufficient fund, please recharge", new Object[0]);


    int code;
    String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BSSException() {
    }


    public BSSException(int code, String msg, Object... args) {
        super(String.format(msg, args));
        this.code = code;
        this.msg = String.format(msg, args);
    }

    public BSSException(String message) {
        super(message);
    }

    public BSSException(String message, Throwable cause) {
        super(message, cause);
    }

    public BSSException(Throwable cause) {
        super(cause);
    }

    public BSSException newInstance(String msg, Object... args) {
        return new BSSException(this.code, msg, args);
    }

}
