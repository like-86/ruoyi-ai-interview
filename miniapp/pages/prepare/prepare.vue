
<template>
  <view class="interview-container">
    <!-- 开始面试按钮 - 完全保持原样 -->
    <button v-if="showStartBut" class="start-interview-btn" @tap="handleStartInterview">
      开始面试
    </button>

    <!-- Loading遮罩 - 点击按钮后显示 -->
    <view v-if="isLoading" class="loading-overlay">
      <view class="loading-content">
        <view class="loading-spinner"></view>
        <text class="loading-text">准备面试中...</text>
        <text class="loading-sub">请稍候</text>
      </view>
    </view>

    <!-- 面试进行中：纯聊天界面（无顶部导航） -->
    <view v-else-if="!showStartBut" class="chat-wrapper">
      <!-- 聊天消息列表 - 与输入框分离 -->
      <scroll-view class="chat-scroll" scroll-y="true" :scroll-into-view="scrollToId" scroll-with-animation>
        <view v-for="(msg, index) in messages" :id="'msg-' + index" :key="index" class="message-item">
          <!-- AI 消息 -->
          <view v-if="msg.role === 'ai'" class="message message-ai">
            <view class="msg-label msg-label-ai">面试官</view>
            <text class="msg-content">{{ msg.content }}</text>
			 <view v-if="msg.prompt && msg.prompt.trim() !== ''" class="prompt-section">
			     <view class="msg-label msg-label-ai">提示:</view>
			     <text class="msg-content">{{ msg.prompt }}</text>
			   </view>
          </view>
          <!-- 用户消息 -->
          <view v-else class="message message-user">
            <view class="msg-label msg-label-user">我</view>
            <text class="msg-content">{{ msg.content }}</text>
          </view>
        </view>
        <!-- 底部占位，用于滚动定位 -->
        <view id="scroll-bottom" style="height: 2px;"></view>
      </scroll-view>

      <!-- 输入区域 - 独立于聊天区，紧贴底部 -->
      <view class="input-area" v-if="!isInterviewEnd">
        <view class="input-wrapper">
          <textarea
              v-model="inputText"
              placeholder="输入你的回答..."
              :disabled="isWaitingAI"
              @confirm="handleSendMessage"
              class="input-textarea"
              :auto-height="true"
          />
        </view>
        <view class="action-group">
          <view class="voice-btn" @tap="handleVoiceInput" :class="{ recording: isRecording }">
            <text class="voice-icon">🎙</text>
          </view>
          <view class="send-btn" @tap="handleSendMessage" :class="{ disabled: !inputText.trim() || isWaitingAI }">
            <text>发送</text>
          </view>
        </view>
      </view>

      <!-- 面试结束 -->
      <view v-else class="end-section">
        <view class="end-icon">🎉</view>
        <text class="end-title">面试完成！</text>
        <text class="end-desc">你已完成全部 5 轮问答</text>
        <view class="end-btn" @tap="handleGoBack">返回上一页</view>
      </view>
    </view>
  </view>
</template>

<script>
import http from '@/utils/http'
export default {
  data() {
    return {
      showStartBut: true,
      isLoading: false, // 新增loading状态
      messages: [],
      inputText: '',
      currentStep: 0,
      isWaitingAI: false,
      isInterviewEnd: false,
      isRecording: false,
      scrollToId: '',
      reportId: '',
      questionList: [],
	  interviewSize:5,
	  currentQuestionId:null
    }
  },
  onLoad: function (option) {
    this.reportId = option.reportId
	this. getinterview();
  },
  methods: {
	  //向后端传入reportId
	  getinterview(){
		  http.get("/interview/question/getinterview/"+this.reportId).then(res=>{
			  
			  
			  this.questionList = res
			   this.interviewSize = res.length
			   console.log( this.interviewSize); 
			    
			  //初始化聊天消息
			  this.messages = [
			    { role: 'ai', content: '你好！欢迎参加本次模拟面试。请放松，就像平常一样回答即可。' },
			    { role: 'ai', content: this.questionList[0].content,prompt: this.questionList[0].questionPrompt }
			  ];
			  this.currentStep = 1;
			  this.currentQuestionId=this.questionList[0].id
			  })
	  	
	  },
    handleStartInterview() {
		//发送一个get请求到后端 记录面试开始时间
		http.get("/interview/record/start/"+this.reportId).then(res=>{})
      // 显示loading
      this.isLoading = true;

      // 模拟加载过程
		this. getinterview();
        // 隐藏loading
        this.isLoading = false;
        this.showStartBut = false;

        // 显示开始提示
        uni.showToast({
          title: '面试开始！',
          icon: 'success',
          duration: 1500
        });

        

        // 滚动到底部
        this.$nextTick(() => {
          this.scrollToBottom();
        });
      
    },

    handleSendMessage() {
      const text = this.inputText.trim();
      if (!text || this.isWaitingAI || this.isInterviewEnd) return;
	  //发送请求给后端保存用户回答的问题id和答案
	  http.post("/interview/answer",{"questionId":this.currentQuestionId,"content":text}).then(res=>{
		  
	  })

      this.messages.push({ role: 'user', content: text });
      this.inputText = '';
      this.scrollToBottom();
      this.currentStep++;

      if (this.currentStep > this.interviewSize) {
        this.isInterviewEnd = true;
        this.messages.push({
          role: 'ai',
          content: '🎯 面试已全部完成！感谢你的参与，我们会尽快反馈结果。'
        });
        this.scrollToBottom();
        return;
      }

      this.isWaitingAI = true;
    

        if (this.currentStep === this.interviewSize) {
          this.messages.push({
            role: 'ai',
            content: '最后一题：' + this.questionList[this.currentStep - 1].content,
			prompt: this.questionList[this.currentStep - 1].questionPrompt,
			
			
          });
		
		  this.currentQuestionId=this.questionList[this.currentStep - 1].id
        } else if (this.currentStep < this.interviewSize) {
          this.messages.push({
            role: 'ai',
            content: this.questionList[this.currentStep - 1].content,
			prompt: this.questionList[this.currentStep - 1].questionPrompt,
			
		
          });
		  this.currentQuestionId=this.questionList[this.currentStep - 1].id
        }
        this.isWaitingAI = false;
        this.scrollToBottom();
		
      
    },

    handleVoiceInput() {
      if (this.isWaitingAI || this.isInterviewEnd) return;
      this.isRecording = !this.isRecording;
      if (this.isRecording) {
        uni.showToast({
          title: '🎤 录音中... 点击停止',
          icon: 'none',
          duration: 1000
        });
        setTimeout(() => {
          if (this.isRecording) {
            this.inputText = '（通过语音转文字的回答）感谢提问，我的看法是...';
            this.isRecording = false;
            uni.showToast({
              title: '录音已转文字',
              icon: 'success',
              duration: 800
            });
          }
        }, 2000);
      } else {
        uni.showToast({
          title: '录音已停止',
          icon: 'none',
          duration: 500
        });
      }
    },

    handleGoBack() {
		
		
		
      uni.showModal({
        title: '提示',
        content: '确定要结束面试并返回吗？',
        success: (res) => {
			//发送请求到后端 记录结束时间
			http.get("/interview/record/end/"+this.reportId).then(res=>{})
			http.get("/ai/genReport/"+this.reportId).then(res=>{})
          if (res.confirm) {
            uni.navigateBack({
              delta: 1,
              fail: () => {
                this.showStartBut = true;
                this.messages = [];
                this.currentStep = 0;
                this.isInterviewEnd = false;
                this.isWaitingAI = false;
                this.inputText = '';
                uni.showToast({ title: '已返回', icon: 'none' });
              }
            });
          }
        }
      });
    },

    scrollToBottom() {
      this.$nextTick(() => {
        this.scrollToId = 'scroll-bottom';
      });
    }
  }
}
</script>

<style>
/* ===== 开始按钮样式 - 完全保持原样 ===== */
.interview-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(145deg, #f5f7fa 0%, #e9edf5 100%);
  padding: 0;
  box-sizing: border-box;
}

.start-interview-btn {
  width: 80%;
  max-width: 400px;
  height: 64px;
  line-height: 64px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 22px;
  font-weight: 600;
  color: #ffffff;
  background: linear-gradient(135deg, #4a7cf7 0%, #6c5ce7 100%);
  border: none;
  border-radius: 50px;
  box-shadow: 0 8px 20px rgba(74, 124, 247, 0.4);
  letter-spacing: 2px;
  transition: all 0.3s ease;
  padding: 0;
  margin: 0;
}
.start-interview-btn:active {
  transform: scale(0.96);
  box-shadow: 0 4px 12px rgba(74, 124, 247, 0.3);
  opacity: 0.9;
}
.start-interview-btn::after {
  border: none;
}
.start-interview-btn {
  position: relative;
  overflow: hidden;
}
.start-interview-btn::before {
  content: '';
  position: absolute;
  top: -50%;
  left: -50%;
  width: 200%;
  height: 200%;
  background: radial-gradient(circle at 30% 40%, rgba(255, 255, 255, 0.2) 0%, transparent 60%);
  pointer-events: none;
}

/* ===== Loading样式 ===== */
.loading-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(255, 255, 255, 0.92);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
  backdrop-filter: blur(8px);
  -webkit-backdrop-filter: blur(8px);
}

.loading-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
  padding: 40px;
}

.loading-spinner {
  width: 60px;
  height: 60px;
  border: 4px solid #e9edf5;
  border-top-color: #4a7cf7;
  border-radius: 50%;
  animation: spin 0.8s linear infinite;
}

.loading-text {
  font-size: 18px;
  font-weight: 600;
  color: #1c1e24;
  letter-spacing: 1px;
}

.loading-sub {
  font-size: 14px;
  color: #8a94a6;
  margin-top: -6px;
}

/* ===== 手机端纯聊天样式 - 完全保持原样 ===== */
.chat-wrapper {
  width: 100%;
  max-width: 480px;
  height: 100vh;
  background: #f8faff;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  position: relative;
  border-radius: 0;
}

/* 聊天滚动区 - 占据剩余空间 */
.chat-scroll {
  flex: 1;
  overflow-y: auto;
  padding: 16px 14px 6px;
  background: #f8faff;
  display: flex;
  flex-direction: column;
  -webkit-overflow-scrolling: touch;
  padding-bottom: 8px;
}
.message-item {
  margin-bottom: 10px;
  display: flex;
  flex-direction: column;
}
.message {
  max-width: 85%;
  padding: 10px 14px;
  border-radius: 18px;
  font-size: 15px;
  line-height: 1.5;
  word-break: break-word;
  animation: fadeIn 0.25s ease;
}
.message-ai {
  align-self: flex-start;
  background: #ffffff;
  border: 1px solid #eaedf2;
  border-bottom-left-radius: 4px;
  color: #1c1e24;
  box-shadow: 0 1px 4px rgba(0,0,0,0.02);
}
.message-user {
  align-self: flex-end;
  background: #4a7cf7;
  color: white;
  border-bottom-right-radius: 4px;
  box-shadow: 0 2px 8px rgba(74, 124, 247, 0.3);
}
.msg-label {
  font-size: 11px;
  opacity: 0.65;
  margin-bottom: 3px;
  display: flex;
  align-items: center;
  gap: 4px;
  font-weight: 500;
}
.msg-label-ai {
  color: #6c7a8a;
}
.msg-label-user {
  color: rgba(255,255,255,0.75);
  justify-content: flex-end;
}
.msg-content {
  display: block;
}

/* ===== 输入区域 - 独立且紧贴底部 ===== */
.input-area {
  background: #ffffff;
  border-top: 1px solid #eef1f6;
  padding: 8px 12px 12px;
  display: flex;
  align-items: flex-end;
  gap: 8px;
  flex-shrink: 0;
  /* 紧贴底部，由父容器 flex 布局自动贴底 */
}
.input-wrapper {
  flex: 1;
  background: #f5f8ff;
  border-radius: 22px;
  border: 1px solid #e4e9f2;
  transition: border 0.2s;
  padding: 2px 4px;
}
.input-wrapper:focus-within {
  border-color: #4a7cf7;
  background: #ffffff;
  box-shadow: 0 0 0 3px rgba(74,124,247,0.08);
}
.input-textarea {
  width: 100%;
  border: none;
  padding: 8px 12px;
  font-size: 15px;
  resize: none;
  background: transparent;
  outline: none;
  line-height: 1.4;
  color: #1c1e24;
  font-family: inherit;
  min-height: 36px;
  max-height: 80px;
}
.input-textarea::placeholder {
  color: #aab3c5;
}
.action-group {
  display: flex;
  gap: 6px;
  align-items: center;
  flex-shrink: 0;
}
.voice-btn {
  width: 42px;
  height: 42px;
  border-radius: 50%;
  background: #f0f4fc;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  transition: all 0.2s;
  cursor: pointer;
  color: #4a7cf7;
}
.voice-btn:active {
  transform: scale(0.92);
}
.voice-btn.recording {
  background: #ff6b6b;
  color: white;
  animation: pulse 1s infinite;
}
.send-btn {
  padding: 0 18px;
  height: 42px;
  border-radius: 30px;
  background: linear-gradient(135deg, #4a7cf7, #6c5ce7);
  color: white;
  font-weight: 600;
  font-size: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.2s;
  box-shadow: 0 3px 12px rgba(74,124,247,0.3);
}
.send-btn:active {
  transform: scale(0.95);
}
.send-btn.disabled {
  opacity: 0.4;
  pointer-events: none;
  box-shadow: none;
}

/* 结束界面 */
.end-section {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 30px 20px;
  background: #f8faff;
  gap: 10px;
}
.end-icon { font-size: 52px; }
.end-title { font-size: 22px; font-weight: 700; color: #1c1e24; }
.end-desc { font-size: 15px; color: #6c7a8a; }
.end-btn {
  margin-top: 18px;
  padding: 12px 44px;
  border-radius: 40px;
  background: linear-gradient(135deg, #4a7cf7, #6c5ce7);
  color: white;
  font-weight: 600;
  font-size: 16px;
  cursor: pointer;
  box-shadow: 0 4px 16px rgba(74,124,247,0.3);
  transition: all 0.2s;
}
.end-btn:active {
  transform: scale(0.95);
}

/* 动画 */
@keyframes fadeIn {
  from { opacity: 0; transform: translateY(6px); }
  to { opacity: 1; transform: translateY(0); }
}
@keyframes pulse {
  0% { transform: scale(1); }
  50% { transform: scale(1.06); }
  100% { transform: scale(1); }
}
@keyframes spin {
  from { transform: rotate(0deg); }
  to { transform: rotate(360deg); }
}

/* 滚动条隐藏 - 移动端 */
.chat-scroll::-webkit-scrollbar {
  width: 0px;
  background: transparent;
}
</style>
