<template>
    <view class="login-container">
        <!-- 顶部装饰区域 -->
        <view class="header-area">
            <view class="logo-wrapper">
                <view class="logo-icon">
                    <image class="logo" :src="src"></image>
                </view>
                <text class="app-name">Ai模拟面试</text>
            </view>
            <text class="slogan">欢迎回来，请登录您的账号</text>
        </view>

        <!-- 登录表单 -->
        <view class="form-area">
            <form @submit="handleLogin">
                <!-- 账号输入框 -->
                <view class="input-group">
                    <view class="input-icon"></view>
                    <input
                        class="input-field"
                        type="text"
                        placeholder="请输入手机号/账号"
                        placeholder-class="placeholder-style"
                        v-model="loginForm.username"
                        maxlength="20"
                    />
                </view>

                <!-- 密码输入框 -->
                <view class="input-group">
                    <view class="input-icon"></view>
                    <input
                        class="input-field"
                        :password="showPassword"
                        placeholder="请输入密码"
                        placeholder-class="placeholder-style"
                        v-model="loginForm.password"
                        maxlength="20"
                    />
                    <view class="eye-icon" @tap="togglePasswordVisibility">
                        <text>{{ showPassword ? '👁️' : '👁️‍🗨️' }}</text>
                    </view>
                </view>

                <!-- 额外选项 -->
                <view class="options-row">
                    <label class="remember-me">
                        <checkbox value="remember" :checked="rememberMe" @change="toggleRemember" />
                        <text>记住我</text>
                    </label>
                    <text class="forgot-password" @tap="handleForgotPassword">忘记密码？</text>
                </view>

                <!-- 登录按钮 -->
                <button
                    class="login-btn"
                    type="primary"
                    form-type="submit"
                    :loading="isLoading"
                    :disabled="isLoading"
                    @click="handleLogin"
                >
                    {{ isLoading ? '登录中...' : '登 录' }}
                </button>

                <!-- ========== 社交登录（新样式） ========== -->
                <view class="social-section">
                    <view class="divider">
                        <view class="line"></view>
                        <text class="divider-text">其他方式</text>
                        <view class="line"></view>
                    </view>
                    <view class="social-login">
                        <!-- 微信登录 -->
                        <button class="social-btn wechat" @click="socialLogin('wechat')">
                            <text class="social-icon">💬</text>
                        </button>
                    </view>
                </view>

                <!-- 注册入口（移入 form 内部） -->
                <view class="register-row">
                    <text>还没有账号？</text>
                    <text class="register-link" @tap="handleRegister">立即注册</text>
                </view>
            </form>
        </view>

        <!-- 底部版权信息（移入 login-container 内部） -->
        <view class="footer-area">
            <text class="footer-text">登录即代表同意《用户协议》和《隐私政策》</text>
        </view>
    </view>
</template>

<script>
	import http from"@/utils/http";
export default {
    data() {
        return {
			src:"../../static/11799.jpg",
            loginForm: {
                username: 'admin',
                password: 'admin123'
            },
            showPassword: true,
            rememberMe: false,
            isLoading: false
        }
    },
    methods: {
        // 切换密码显隐
        togglePasswordVisibility() {
            this.showPassword = !this.showPassword
        },

        // 切换记住我
        toggleRemember(e) {
            this.rememberMe = e.detail.value.length > 0
        },

        // 登录提交
        handleLogin(e) {
            // this.isLoading = true;
			
			http.post("/Applogin",{"username":this.loginForm.username,"password":this.loginForm.password}).then(res=>{
				console.log(res)
				uni.setStorageSync('token',res.token)
				uni.switchTab({
				  url: '/pages/home/home'
				})

			})
        },

        // 忘记密码
        handleForgotPassword() {
           
        },

        // 注册跳转
        handleRegister() {
          
        },
		socialLogin(){
			wx.login({
			          success (res) {
			            if (res.code) {
			              //发起网络请求
			              http.get("/wechat/login/"+res.code).then(res=>{
			                console.log(res)
			                if(res.token){
			                  //有token进入主页
			                  //保存token信息
			                  uni.setStorageSync('token', res.token);
			                  uni.setStorageSync('user', res.user);
			                  uni.switchTab({
			                    url: '/pages/home/home'
			                  });
			                }else {
			                  //没有token进入注册页面
			                  uni.navigateTo({
			                    url: '/pages/register/register?openId='+res.openid
			                  });
			                }
			              })
			            } else {
			              console.log('登录失败！' + res.errMsg)
			            }
			          }
			        })
			      },

		}
    }

</script>

<style scoped>
/* 您原有的样式，完全未动 */
.login-container {
    min-height: 100vh;
    background: linear-gradient(145deg, #f5f7fa 0%, #e8edf3 100%);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 40rpx 60rpx;
    box-sizing: border-box;
}

/* ===== 头部区域 ===== */
.header-area {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 80rpx;
}

.logo-wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 20rpx;
}

	.logo {
		width: 160rpx;
		height: 160rpx;
		border-radius: 40rpx;
		padding: 30rpx;
		box-shadow: 0 16rpx 40rpx rgba(41, 121, 255, 0.25);
		margin-bottom: 30rpx;
	}

.icon-text {
    font-size: 72rpx;
    line-height: 1;
}

.app-name {
    font-size: 48rpx;
    font-weight: 700;
    color: #2c3e50;
    letter-spacing: 4rpx;
}

.slogan {
    font-size: 28rpx;
    color: #7f8c8d;
    letter-spacing: 2rpx;
    margin-top: 8rpx;
}

/* ===== 表单区域 ===== */
.form-area {
    width: 100%;
    background: #ffffff;
    border-radius: 32rpx;
    padding: 50rpx 40rpx 40rpx;
    box-shadow: 0 16rpx 60rpx rgba(0, 0, 0, 0.06);
    box-sizing: border-box;
}

/* 输入框组 */
.input-group {
    display: flex;
    align-items: center;
    background: #f4f6f9;
    border-radius: 16rpx;
    padding: 0 24rpx;
    margin-bottom: 30rpx;
    height: 96rpx;
    transition: all 0.25s ease;
    border: 2rpx solid transparent;
}

.input-group:focus-within {
    background: #ffffff;
    border-color: #4a90d9;
    box-shadow: 0 0 0 6rpx rgba(74, 144, 217, 0.12);
}

.input-icon {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 48rpx;
    font-size: 36rpx;
    margin-right: 16rpx;
    flex-shrink: 0;
}

.input-field {
    flex: 1;
    height: 100%;
    font-size: 30rpx;
    color: #2c3e50;
    background: transparent;
    border: none;
    outline: none;
}

.input-field::placeholder {
    color: #b0b8c4;
    font-size: 28rpx;
}

.placeholder-style {
    color: #b0b8c4;
    font-size: 28rpx;
}

.eye-icon {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 48rpx;
    font-size: 34rpx;
    flex-shrink: 0;
    padding: 8rpx;
    cursor: pointer;
}

/* 选项行 */
.options-row {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin: 12rpx 0 40rpx;
}

.remember-me {
    display: flex;
    align-items: center;
    font-size: 26rpx;
    color: #5a6a7a;
    gap: 8rpx;
}

.remember-me checkbox {
    transform: scale(0.8);
    transform-origin: left center;
}

.forgot-password {
    font-size: 26rpx;
    color: #4a90d9;
    cursor: pointer;
}

.forgot-password:active {
    opacity: 0.6;
}

/* 登录按钮 */
.login-btn {
    width: 100%;
    height: 96rpx;
    line-height: 96rpx;
    font-size: 34rpx;
    font-weight: 600;
    letter-spacing: 6rpx;
    border-radius: 48rpx;
    background: linear-gradient(135deg, #4a90d9, #357abd);
    border: none;
    color: #ffffff;
    box-shadow: 0 12rpx 32rpx rgba(53, 122, 189, 0.35);
    transition: all 0.25s ease;
}

.login-btn:active {
    transform: scale(0.97);
    box-shadow: 0 6rpx 20rpx rgba(53, 122, 189, 0.25);
}

.login-btn[disabled] {
    opacity: 0.65;
    transform: none;
}

/* 注册入口 */
.register-row {
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 36rpx;
    font-size: 26rpx;
    color: #7f8c8d;
}

.register-link {
    color: #4a90d9;
    font-weight: 600;
    margin-left: 8rpx;
    cursor: pointer;
}

.register-link:active {
    opacity: 0.6;
}

/* ===== 社交登录样式 ===== */
.social-section {
    margin-top: 50rpx;
    width: 100%;
}

.divider {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 30rpx;
}

.line {
    flex: 1;
    height: 1rpx;
    background: #e0e5ec;
}

.divider-text {
    font-size: 24rpx;
    color: #b0b8c4;
    padding: 0 20rpx;
    white-space: nowrap;
}

.social-login {
    display: flex;
    justify-content: center;
    gap: 40rpx;
}

.social-btn {
    width: 96rpx;
    height: 96rpx;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    border: 2rpx solid #e8edf3;
    background: #ffffff;
    box-shadow: 0 4rpx 12rpx rgba(0, 0, 0, 0.05);
    transition: all 0.2s ease;
    padding: 0;
    font-size: 44rpx;
}

.social-btn:active {
    transform: scale(0.92);
    opacity: 0.7;
}

.social-btn.wechat {
    background: #07c160;
    border-color: #07c160;
    color: #ffffff;
}

.social-icon {
    line-height: 1;
    font-size: 44rpx;
}

/* ===== 底部版权 ===== */
.footer-area {
    width: 100%;
    text-align: center;
    margin-top: 50rpx;
    padding-bottom: 20rpx;
}

.footer-text {
    font-size: 22rpx;
    color: #b0b8c4;
    letter-spacing: 1rpx;
}

/* ===== 响应式微调 ===== */
@media screen and (max-width: 375px) {
    .login-container {
        padding: 30rpx 36rpx;
    }
    .form-area {
        padding: 36rpx 28rpx 32rpx;
    }
    .app-name {
        font-size: 40rpx;
    }
    .logo-icon {
        width: 120rpx;
        height: 120rpx;
    }
    .icon-text {
        font-size: 60rpx;
    }
}
</style>