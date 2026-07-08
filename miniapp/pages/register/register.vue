<template>
  <view class="login-container">
    <!-- 登录头部 -->
    <view class="login-header">
      <text class="title">手机号绑定</text>
      <text class="subtitle">输入手机号获取验证码</text>
    </view>

    <!-- 登录表单 -->
    <view class="login-form">
      <!-- 手机号 -->
      <view class="form-group">
        <view class="input-wrap">
          <input
              type="number"
              v-model="phone"
              placeholder="请输入手机号"
              class="input-field"
              maxlength="11"
              confirm-type="next"
          />
        </view>
        <text class="input-hint" v-if="phoneError">{{ phoneError }}</text>
      </view>

      <!-- 验证码 -->
      <view class="form-group">
        <view class="input-wrap code-wrap">
          <input
              type="number"
              v-model="code"
              placeholder="请输入验证码"
              class="input-field code-input"
              maxlength="6"
              confirm-type="done"
              @confirm="handleLogin"
          />
          <button
              class="code-btn"
              @click="sendCode"
              :disabled="getCode"
          >
            <text>{{codeText}}</text>
          </button>
        </view>
        <text class="input-hint" v-if="codeError">{{ codeError }}</text>
		<view>
		        <button
		            class="code-btn"
		            @click="bindPhone"
		        >
		          <text>绑定</text>
		        </button>
		      </view>
      </view>
    </view>
  </view>
</template>

<script>
import http from "@/utils/http";
export default {
  data() {
    return {
      getCode:false,
      codeText:"获取验证码",
      phone: '',
      code: '',
      codeCountdown: 0,
      countdownTimer: null,
      toastTimer: null,
      phoneError: '',
      codeError: '',
      openId:''
    };
  },
  computed: {
    isPhoneValid() {
      const phoneReg = /^1[3-9]\d{9}$/;
      if (!this.phone) return false;
      return phoneReg.test(this.phone);
    }
  },
  watch: {
    phone(val) {
      if (val && !/^1[3-9]\d{9}$/.test(val)) {
        this.phoneError = '请输入正确的手机号';
      } else {
        this.phoneError = '';
      }
    },
    code(val) {
      if (val && val.length < 4) {
        this.codeError = '验证码至少4位';
      } else {
        this.codeError = '';
      }
    }
  },
  onLoad: function (option) {
    this.openId = option.openId
  },
  methods: {
    sendCode() {
      if (this.phone && !/^1[3-9]\d{9}$/.test(this.phone)) {
        uni.showToast({
          title: '请输入正确的手机号',
          icon: 'error'
        })
        this.phone = ""
      }
      this.getCode = true
      this.codeCountdown = 60;
      if (this.countdownTimer) {
        clearInterval(this.countdownTimer);
      }
      http.get("/wechat/getPhoneCode/"+this.phone).then(res=>{
        if(res.code == 200){
          this.countdownTimer = setInterval(() => {
            this.codeCountdown--;
            this.codeText = this.codeCountdown+"S"

            if (this.codeCountdown <= 0) {
              clearInterval(this.countdownTimer);
              this.countdownTimer = null;
              this.codeCountdown = 0;
              this.getCode = false
            }
          }, 1000);
        }
      })

    },
    bindPhone() {
		//把手机 验证码  openid 全部传入到后台	
		//1.验证
			if(!this.phone){
				uni.showToast({
					title:"请输入手机号",
					icon:"error"
				})
				
				return;}
			if(!this.code){
				uni.showToast({
					title:"请输入验证码",
					icon:"error"
				})
				return;
		}
		//发送请求到后端
		var param = {
			"openId":this.openId,
			"code":this.code,
			"phone":this.phone
		}
		http.post("/wechat/bindPhone",param).then(res=>{
			if(res.token){
				uni.setStorageSync('token', res.token);
				uni.setStorageSync('user', res.user);
				uni.navigateTo({
				  url: '/pages/home/home'
				});
				}else{
					uni.showToast({
						title:res.mag,
						icon:"error"
					})
					return;
					
				}
				
			})
    }
  }
};
</script>

<style scoped>
.login-container {
  width: 100%;
  max-width: 420px;
  margin: 0 auto;
  background: #ffffff;
  border-radius: 36px;
  padding: 60px 32px 40px;
  box-sizing: border-box;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

/* Toast 样式 */
.toast-message {
  position: fixed;
  top: 30px;
  left: 50%;
  transform: translateX(-50%);
  background: #1e293b;
  color: white;
  padding: 12px 24px;
  border-radius: 40px;
  font-size: 15px;
  font-weight: 500;
  box-shadow: 0 12px 32px rgba(0,0,0,0.18);
  z-index: 999;
  opacity: 0;
  transition: opacity 0.25s ease;
  pointer-events: none;
  max-width: 90%;
}
.toast-message.show {
  opacity: 1;
}
.toast-text {
  color: #ffffff;
  font-size: 15px;
}

/* 头部 */
.login-header {
  text-align: center;
  margin-bottom: 48px;
}
.title {
  display: block;
  font-size: 28px;
  font-weight: 700;
  color: #1e293b;
  letter-spacing: -0.5px;
}
.subtitle {
  display: block;
  color: #94a3b8;
  font-size: 15px;
  margin-top: 8px;
}

/* 表单 */
.login-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.form-group {
  display: flex;
  flex-direction: column;
  gap: 6px;
}
.input-wrap {
  display: flex;
  align-items: center;
  background: #f1f5f9;
  border-radius: 18px;
  padding: 4px 16px;
  transition: all 0.2s;
  border: 2px solid transparent;
}
.input-wrap:focus-within {
  border-color: #3b82f6;
  background: #ffffff;
  box-shadow: 0 4px 12px rgba(59, 130, 246, 0.12);
}
.icon {
  color: #94a3b8;
  font-size: 20px;
  margin-right: 10px;
  line-height: 1;
  width: 24px;
  text-align: center;
  flex-shrink: 0;
}
.input-field {
  width: 100%;
  border: none;
  background: transparent;
  padding: 16px 0;
  font-size: 16px;
  color: #0f172a;
  outline: none;
}
.input-field::placeholder {
  color: #cbd5e1;
  font-weight: 400;
  font-size: 15px;
}
.input-hint {
  font-size: 12px;
  color: #ef4444;
  margin-left: 4px;
  margin-top: 2px;
}

/* 验证码 - 蓝色按钮 */
.code-wrap {
  padding-right: 8px;
}
.code-input {
  flex: 1;
}
.code-btn {
  background: #3b82f6;
  border: none;
  border-radius: 40px;
  padding: 10px 20px;
  color: white;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  white-space: nowrap;
  flex-shrink: 0;
  height: 44px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: none;
  box-shadow: 0 4px 12px rgba(59, 130, 246, 0.3);
  transition: all 0.2s;
}
.code-btn:active {
  transform: scale(0.95);
}
.code-btn-disabled {
  background: #94a3b8;
  opacity: 0.7;
  pointer-events: none;
  box-shadow: none;
}

/* 适配小屏 */
@media (max-width: 440px) {
  .login-container { padding: 40px 20px 30px; }
  .title { font-size: 24px; }
}
</style>