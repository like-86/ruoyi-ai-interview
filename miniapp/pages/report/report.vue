<template>
  <view class="list-demo">
    <!-- 加载状态 -->
    <view v-if="loading" class="loading-text">加载中...</view>
    
    <!-- 列表 -->
    <view 
      class="list-item" 
      v-for="(item, index) in listData" 
      :key="index"
      @click="handleItemClick(item)"
    >
      <view class="item-avatar">
        <text class="avatar-text">{{ item.positionId || '面' }}</text>
      </view>
      <view class="item-content">
        <view class="item-title">{{ item.name }}</view>
        <view class="item-note">
          <text>开始时间：{{ item.startTime }}</text>
          <text v-if="item.duration" class="duration"> | 时长：{{ item.duration }}分钟</text>
        </view>
		<view class="item-note">
		  <text>结束时间：{{ item.endTime }}</text>
		  <text v-if="item.duration" class="duration"> | 时长：{{ item.duration }}分钟</text>
		</view>
        <!-- 修改1: 使用对象语法绑定class -->
        <view class="item-status" :class="{
          'status-pending': item.status === 0,
          'status-progress': item.status === 1,
          'status-done': item.status === 2,
          'status-cancel': item.status === 3
        }">
          <!-- 修改2: 使用三元运算符或计算属性 -->
          {{ getStatusText(item.status) }}
        </view>
      </view>
      <view class="item-arrow">></view>
    </view>
    
    <!-- 空状态 -->
    <view v-if="!loading && listData.length === 0" class="empty-text">暂无数据</view>
  </view>
</template>

<script>
	import http from "@/utils/http.js"
  export default {
    data() {
      return {
        // 模拟列表数据
        listData: [],
        loading: false
      }
    },
	onLoad: function (option) {
	    this.getReport();
		
	  },
    methods: {
	getReport(){
		
		 loading: true
			http.get("/interview/record/listAll").then(res=>{
				this.listData = res
				console.log(res)
			})
		},
		
	
    handleItemClick(item) {
		uni.navigateTo({
		  url: "/pages/detail/detail?id="+item.id
		});
      },
	  
	getStatusText(status) {
  if (status == 0) {
    return '进行中'
  } else if (status == 1) {
    return '已完成'
  }
}
    }
  }
</script>

<style>
  .list-demo {
    padding: 20rpx;
    background-color: #f8f8f8;
    min-height: 100vh;
  }
  
  .list-item {
    display: flex;
    align-items: center;
    background-color: #ffffff;
    border-radius: 16rpx;
    padding: 24rpx 30rpx;
    margin-bottom: 20rpx;
    box-shadow: 0 2rpx 12rpx rgba(0, 0, 0, 0.06);
    transition: all 0.2s;
    cursor: pointer;
  }
  
  .list-item:active {
    background-color: #f0f0f0;
    transform: scale(0.98);
  }
  
  .item-thumb {
    width: 100rpx;
    height: 100rpx;
    border-radius: 12rpx;
    flex-shrink: 0;
    background-color: #e0e0e0;
  }
  
  .item-content {
    flex: 1;
    margin-left: 24rpx;
    overflow: hidden;
  }
  
  .item-title {
    font-size: 32rpx;
    font-weight: 500;
    color: #333333;
    line-height: 1.4;
    margin-bottom: 8rpx;
  }
  
  .item-note {
    font-size: 26rpx;
    color: #999999;
    line-height: 1.4;
  }
  
  .item-arrow {
    font-size: 32rpx;
    color: #cccccc;
    margin-left: 16rpx;
    flex-shrink: 0;
  }
</style>