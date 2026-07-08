<template>
  <view class="detail-container">
    <!-- 加载状态 -->
    <view v-if="loading" class="loading-text">加载中...</view>
    
    <!-- 详情内容 -->
    <view v-else-if="detailData" class="detail-content">
      <!-- 头部卡片 -->
      <view class="header-card">
        <view class="header-title">面试报告</view>
        <view class="header-sub">报告 ID：{{ detailData.id }}</view>
        <view class="header-status" :class="{
          'status-pending': detailData.status == 0 || detailData.status == '0',
          'status-progress': detailData.status == 1 || detailData.status == '1',
          'status-done': detailData.status == 2 || detailData.status == '2',
          'status-cancel': detailData.status == 3 || detailData.status == '3'
        }">
          {{ getStatusText(detailData.status) }}
        </view>
      </view>
      
      <!-- 评分卡片（突出显示） -->
      <view class="score-card">
        <view class="score-number">{{ detailData.overallScore || '未评分' }}</view>
        <view class="score-label">综合评分</view>
      </view>
      
      <!-- 面试评价 -->
      <view class="detail-section">
        <view class="section-title">
          <text class="title-icon">📋</text>
          面试评价
        </view>
        <view class="info-item">
          <text class="info-label">综合评价</text>
          <text class="info-value">{{ detailData.overallEvaluation || '暂无' }}</text>
        </view>
      </view>
      
      <!-- 优劣势 -->
      <view class="detail-section">
        <view class="section-title">
          <text class="title-icon">📊</text>
          优劣势分析
        </view>
        <view class="info-item">
          <text class="info-label">💪 优势</text>
          <text class="info-value highlight-strength">{{ detailData.strengths || '暂无' }}</text>
        </view>
        <view class="info-item">
          <text class="info-label">⚠️ 不足</text>
          <text class="info-value highlight-weakness">{{ detailData.weaknesses || '暂无' }}</text>
        </view>
      </view>
      
      <!-- 时间信息 -->
      <view class="detail-section">
        <view class="section-title">
          <text class="title-icon">🕐</text>
          时间信息
        </view>
        <view class="info-item">
          <text class="info-label">生成时间</text>
          <text class="info-value">{{ detailData.generateTime || '未知' }}</text>
        </view>
        <view class="info-item">
          <text class="info-label">创建时间</text>
          <text class="info-value">{{ detailData.createTime || '未知' }}</text>
        </view>
      </view>
      
      <!-- 备注信息 -->
      <view class="detail-section" v-if="detailData.remark">
        <view class="section-title">
          <text class="title-icon">📝</text>
          备注信息
        </view>
        <view class="remark-content">
          {{ detailData.remark }}
        </view>
      </view>
      
      <!-- 底部操作按钮 -->
      
    </view>
    
    <!-- 空状态 -->
    <view v-else class="empty-text">暂无数据</view>
  </view>
</template>

<script>
  import http from "@/utils/http.js"
  
  export default {
    data() {
      return {
        detailData: null,
        loading: false,
        id: null
      }
    },
    onLoad: function (options) {
      console.log('接收到的参数:', options)
      if (options.id) {
        this.id = options.id
        this.getDetail()
      } else {
        uni.showToast({
          title: '参数错误',
          icon: 'none'
        })
      }
    },
    methods: {
      getDetail() {
        this.loading = true
        http.get("/interview/report/" + this.id)
          .then(res => {
            this.detailData = res
            console.log('详情数据:', this.detailData)
          })
          .catch(err => {
            console.error('获取详情失败:', err)
            uni.showToast({
              title: '加载失败，请重试',
              icon: 'none'
            })
          })
          .finally(() => {
            this.loading = false
          })
      },
      
      getStatusText(status) {
        if (status == 0 || status == '0') {
          return '进行中'
        } else if (status == 1 || status == '1') {
          return '已完成'
        }
        return '未知'
      },
      
      handleEdit() {
        uni.showToast({
          title: '编辑功能开发中',
          icon: 'none'
        })
      },
      
      handleDelete() {
        uni.showModal({
          title: '提示',
          content: '确定要删除这条面试记录吗？',
          success: (res) => {
            if (res.confirm) {
              this.deleteRecord()
            }
          }
        })
      },
      
      deleteRecord() {
        http.delete("/interview/record/" + this.id)
          .then(res => {
            uni.showToast({
              title: '删除成功',
              icon: 'success'
            })
            setTimeout(() => {
              uni.navigateBack()
            }, 1500)
          })
          .catch(err => {
            console.error('删除失败:', err)
            uni.showToast({
              title: '删除失败，请重试',
              icon: 'none'
            })
          })
      }
    }
  }
</script>

<style>
  .detail-container {
    min-height: 100vh;
    background: linear-gradient(135deg, #f5f7fa 0%, #e8ecf1 100%);
    padding: 24rpx;
  }
  
  .loading-text, .empty-text {
    text-align: center;
    padding: 100rpx 0;
    font-size: 28rpx;
    color: #999;
  }
  
  .detail-content {
    display: flex;
    flex-direction: column;
    gap: 24rpx;
  }
  
  /* ========== 头部卡片 ========== */
  .header-card {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    border-radius: 24rpx;
    padding: 40rpx 32rpx;
    color: #fff;
    box-shadow: 0 8rpx 32rpx rgba(102, 126, 234, 0.35);
  }
  
  .header-title {
    font-size: 40rpx;
    font-weight: 700;
    letter-spacing: 2rpx;
  }
  
  .header-sub {
    font-size: 24rpx;
    opacity: 0.8;
    margin-top: 8rpx;
  }
  
  .header-status {
    display: inline-block;
    margin-top: 16rpx;
    padding: 6rpx 28rpx;
    border-radius: 30rpx;
    font-size: 24rpx;
    font-weight: 500;
    background: rgba(255, 255, 255, 0.25);
    color: #fff;
    backdrop-filter: blur(10rpx);
  }
  
  /* ========== 评分卡片 ========== */
  .score-card {
    background: #fff;
    border-radius: 24rpx;
    padding: 36rpx 0;
    text-align: center;
    box-shadow: 0 4rpx 20rpx rgba(0, 0, 0, 0.06);
  }
  
  .score-number {
    font-size: 80rpx;
    font-weight: 700;
    color: #667eea;
    line-height: 1.2;
  }
  
  .score-number:empty::before {
    content: '未评分';
    font-size: 36rpx;
    color: #ccc;
  }
  
  .score-label {
    font-size: 26rpx;
    color: #999;
    margin-top: 8rpx;
    letter-spacing: 4rpx;
  }
  
  /* ========== 详情区块 ========== */
  .detail-section {
    background: #fff;
    border-radius: 24rpx;
    padding: 28rpx 32rpx;
    box-shadow: 0 4rpx 20rpx rgba(0, 0, 0, 0.06);
  }
  
  .section-title {
    font-size: 30rpx;
    font-weight: 600;
    color: #333;
    margin-bottom: 20rpx;
    padding-bottom: 16rpx;
    border-bottom: 2rpx solid #f0f0f0;
  }
  
  .title-icon {
    margin-right: 10rpx;
  }
  
  .info-item {
    display: flex;
    flex-direction: column;
    padding: 14rpx 0;
    border-bottom: 1rpx solid #f5f5f5;
  }
  
  .info-item:last-child {
    border-bottom: none;
  }
  
  .info-label {
    font-size: 24rpx;
    color: #999;
    margin-bottom: 6rpx;
    font-weight: 500;
  }
  
  .info-value {
    font-size: 28rpx;
    color: #333;
    line-height: 1.6;
    word-break: break-all;
  }
  
  /* 优势高亮 */
  .highlight-strength {
    color: #2d8a4e;
    padding: 12rpx 16rpx;
    background: #e8f5e9;
    border-radius: 12rpx;
  }
  
  /* 不足高亮 */
  .highlight-weakness {
    color: #c62828;
    padding: 12rpx 16rpx;
    background: #ffebee;
    border-radius: 12rpx;
  }
  
  /* ========== 备注 ========== */
  .remark-content {
    font-size: 28rpx;
    color: #555;
    padding: 20rpx 24rpx;
    background: #f8f9fa;
    border-radius: 16rpx;
    line-height: 1.8;
    border-left: 4rpx solid #667eea;
  }
  
  /* ========== 底部按钮 ========== */
  .detail-actions {
    display: flex;
    gap: 24rpx;
    margin-top: 8rpx;
  }
  
  .action-btn {
    flex: 1;
    height: 88rpx;
    line-height: 88rpx;
    border-radius: 44rpx;
    font-size: 28rpx;
    font-weight: 500;
    border: none;
    box-shadow: 0 4rpx 16rpx rgba(0, 0, 0, 0.08);
    transition: all 0.2s;
  }
  
  .action-btn:active {
    transform: scale(0.96);
    opacity: 0.85;
  }
  
  .edit-btn {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    color: #ffffff;
  }
  
  .delete-btn {
    background: #fff;
    color: #f56c6c;
    border: 2rpx solid #f56c6c !important;
  }
  
  .action-btn::after {
    border: none;
  }
</style>