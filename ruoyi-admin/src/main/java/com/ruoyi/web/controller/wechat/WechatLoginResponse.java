package com.ruoyi.web.controller.wechat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WechatLoginResponse {

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    private String session_key;

    /**
     * 用户在开放平台的唯一标识
     */
    private String unionid;

    /**
     * 错误码
     * 0: 成功
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;
}
