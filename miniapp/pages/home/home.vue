<template>
  <view class="tag-container">
    <!-- 标题区域 -->
    <view class="tag-header">
      <text class="tag-title">🏷️ 热门标签</text>
      <text class="tag-hint">悬停高亮</text>
    </view>

    <!-- 标签网格：一行两个 -->
    <view class="tag-grid">
      <view class="tag-item" v-for="(tag, index) in tagList" @click="clickPosition(tag.id)">
        <button>{{ tag.name || tag }}</button>
      </view>
    </view>
	
	 <view v-if="loading" class="loading-mask">
	      <view class="loading-content" >
	        <button :loading="loading" class="loading-btn" ></button>
	        <text class="loading-text" >AI 正在生成面试题，请稍候...</text>
	      </view>
	    </view>
  </view>
</template>

<script>
import http from "@/utils/http";

export default {
  data() {
    return {
      tagList:[],
	  loading:false	,
    }
  },
onLoad: function (option) {
    this.getPosition();
  },
  methods: {
    getPosition(){
      http.get("/interview/position/listAll").then(res=>{
          this.tagList = res	
      })
    },
    async  clickPosition(id)  {
      //根据id去调用生成对应的面试
	  this.loading = true;
	  try{
			await http.get("/ai/genInterview",{"positionId":id}).then(res=>{
		    
			uni.navigateTo({
			  url: '/pages/prepare/prepare?reportId='+res
			});
			
		  });
		
	  }
	  catch(error){}
	  finally{
		  this.loading = false;
	  }
     
    }
  }
}
</script>

<style>
/* 容器 */
.tag-container {
  width: 100%;
  max-width: 420px;
  margin: 0 auto;
  padding: 28px 20px 32px;
  background-color: #ffffff;
  border-radius: 32px;
  box-shadow: 0 12px 30px rgba(0, 20, 30, 0.08);
  box-sizing: border-box;
}

/* 头部 */
.tag-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 22px;
  padding: 0 4px;
}
.tag-title {
  font-size: 1.25rem;
  font-weight: 600;
  color: #1a2e3f;
  letter-spacing: -0.2px;
}
.tag-hint {
  font-size: 0.75rem;
  color: #7f8fa3;
  background: #edf2f7;
  padding: 4px 14px;
  border-radius: 40px;
}

/* 网格布局：一行两个 */
.tag-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 16px 14px;
  list-style: none;
  margin: 0;
  padding: 0;
}

/* 标签基础样式 */
.tag-item {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 14px 8px;
  border-radius: 60px;
  font-size: 1rem;
  font-weight: 500;
  letter-spacing: 0.3px;
  color: #1a2639;
  border: 1.5px solid transparent;
  transition: all 0.2s ease;
  text-align: center;
  line-height: 1.3;
  word-break: break-word;
  min-height: 56px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.02);
  box-sizing: border-box;
}

/* 使用 CSS :hover 实现悬停高亮（兼容 Web 和 UniApp） */
.tag-item:hover {
  background-color: #179b16 !important;
  border-color: #179b16 !important;
  color: #ffffff !important;
  box-shadow: 0 8px 18px rgba(23, 155, 22, 0.25);
  transform: scale(1.02);
}

.loading-mask {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 9999;
  backdrop-filter: blur(4px);
}

.loading-content {
  background: #ffffff;
  border-radius: 20px;
  padding: 40px 32px 32px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
  min-width: 200px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
}

.loading-text {
  font-size: 0.95rem;
  color: #1a2e3f;
  font-weight: 500;
  text-align: center;
}
    
.loading-btn {
  /* 关键：设为透明 */
  background: transparent !important;
  border: none !important;
  /* 设置大小让 loading 图标显示 */
  width: 60px;
  height: 60px;
  min-height: 60px;
  padding: 0;
  margin: 0;
  /* 移除默认样式 */
  background-color: transparent !important;
  border-color: transparent !important;
  box-shadow: none !important;
}

/* 微信小程序需要清除按钮的伪元素 */
.loading-btn::after {
  border: none !important;
}

/* 如果按钮有默认文字，隐藏 */
.loading-btn .uni-button-text {
  display: none;
}

/* 小屏适配 */
@media (max-width: 380px) {
  .tag-container {
    padding: 20px 14px;
  }
  .tag-grid {
    gap: 12px 10px;
  }
  .tag-item {
    font-size: 0.9rem;
    padding: 12px 6px;
    min-height: 48px;
  }
}
</style>
