package com.ruoyi.web.controller.wechat;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.common.core.domain.model.WxUser;
import com.ruoyi.interview.domain.po.BindPhone;
import com.ruoyi.interview.service.IWxUserService;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/wechat")
public class WeChatController {
    private static final String APPID = "wx79c37b3c46c29dcc";
    private static final String SECRET = "2a28d7de26df5324b56c71ea35c6561c";
    private static final String WECHAT_PATH = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=GRANT_TYPE";
    private static final String PHONE_CODE="phone_code";
    @Autowired
    private IWxUserService wxUserService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    RedisCache cache;



    @GetMapping("/login/{code}")
    public AjaxResult login(@PathVariable("code")String code){
        String result = HttpUtils.sendGet(String.format(WECHAT_PATH, APPID, SECRET, code));
        WechatLoginResponse wechatLoginResponse = JSONObject.parseObject(result, WechatLoginResponse.class);
        String openid = wechatLoginResponse.getOpenid();
        WxUser user = wxUserService.selectWxUserByOpenId(openid);
        if(user == null){//如果为null表示没有注册
            WxUser wxUser = new WxUser();
            wxUser.setOpenid(openid);
            wxUserService.insertWxUser(wxUser);
            return AjaxResult.success().put("openid",openid);
        }else {//如果已经注册了就使用若依的验证返回token
            if(user.getPhone()==null){
                return AjaxResult.success().put("openid",openid);

            }
            return getAjaxResult(user);
        }



    }

    @GetMapping("/getPhoneCode/{phone}")
    public AjaxResult getPhoneCode(@PathVariable("phone")String phone){
        //判断手机号是否被绑定
        WxUser user=wxUserService.selectWxUserByPhone(phone);

        if(user != null ){
            return  AjaxResult.success("手机号已被绑定，请重新确定手机号");
        }
        //没有绑定则生成一个验证码
        String code = generateCode();
        //计算方案
        //通过手机号给用户发送短信
        System.out.println(code);//发送验证码
        //验证验证码是否正确
        //技术方案 使用缓存技术 保存提前生成的验证码，再绑定的时候去验证缓存中的验证码和用户输入的验证码是否一致
        //验证码有时间限制
        String key = PHONE_CODE+phone;
        cache.setCacheObject(key,code);
        cache.expire(key,60);//设置过期时间
        return AjaxResult.success();
    }
    @PostMapping("/bindPhone")
    public AjaxResult bindPhone(@RequestBody  BindPhone bindPhone){
        //1.参数验证
        if(bindPhone.getPhone()  == null){
            return AjaxResult.success("请填写手机号");
        }
        if(bindPhone.getCode()  == null){
            return AjaxResult.success("请填写验证码");
        }
        if(bindPhone.getOpenId()  == null){
            return AjaxResult.success("参数异常,请重新注册");
        }
        //2.判读code是否已过期
        if (!cache.hasKey(PHONE_CODE+bindPhone.getPhone())) {
            return AjaxResult.success("验证码已过期");

        }
        //3.判断code是否正确
        if (cache.hasKey(PHONE_CODE+bindPhone.getPhone()).toString().equals(bindPhone.getCode())) {
            return AjaxResult.success("验证码出错");

        }
        //4.绑定手机号到数据库
        WxUser wxUser = wxUserService.selectWxUserByOpenId(bindPhone.getOpenId());
        if(wxUser.getPhone()!=null){
            return getAjaxResult(wxUser);

        }
        wxUser.setPhone(bindPhone.getPhone());
        wxUserService.updateWxUser(wxUser);

        //5.返回token，登录成功
        return getAjaxResult(wxUser);

    }

    private @Nullable AjaxResult getAjaxResult(WxUser wxUser) {
        LoginUser  loginUser = new LoginUser();
        loginUser.setWxUser(wxUser);
        String token = tokenService.createToken(loginUser);
        return AjaxResult.success().put("token", token).put("user", wxUser);
    }

    public static String generateCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10); // 生成0-9的随机数
            code.append(digit);
        }

        return code.toString();
    }

}
