package com.pla.Enum;

public enum ResultEnum implements CommonEnum {
    SUCCESS(200, "成功"),
    FAIL(404, "失败"),
    UNVALID(3000, null),
    UNAUTH_POST(3012, "失败"),
    MOBILE_UNAUTH(3060, "已投票"),
    MOBILE_AUTHED(3061, "手机号已验证"),
    MOBILE_UNVALID(3062, "手机号不合法"),
    MACHINE_UNVALID(3070, "提货柜编号不存在"),
    VERIFICATION_ERROR(4000, "验证码不匹配"),
    VERIFICATION_NULL(4003, "验证码过期或未发送"),
    CODE_NULL(4008, "code不存在"),
    CODE_ERROR(4009, "code过期"),
    OPENID_NULL(4010, "openId不存在"),
    OPENID_ERROR(4011, "openId错误"),
    MOBILE_BLANK(4025, "手机号为空"),
    MOBILE_INVALID(4026, "手机号格式不正确"),
    PARAMS_BLANK(4027, "参数为空"),
    ERROR(5000, null);

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
