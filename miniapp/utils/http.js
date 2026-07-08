const BASE_URL = 'http://localhost:8080'
const TIMEOUT = 300000 // 请求超时时间（毫秒）

// 请求拦截器（可在此添加 token、loading 等）
const requestInterceptor = (config) => {
    // 从 storage 中获取 token
    const token = uni.getStorageSync('token')
    //有token才添加
    if (token) {
        config.header = {
            ...config.header,
            'Authorization': `Bearer `+token
        }
    }
    return config
}

// 响应拦截器（统一处理返回数据）
const responseInterceptor = (response) => {
    // 解构响应数据
    const { data, statusCode } = response
    const errorMsg = data.message || data.msg || '请求失败'
    // 统一处理 HTTP 状态码
    if (statusCode >= 200 && statusCode < 300) {
        // 业务状态码处理（根据实际后端接口调整）
        if (data.code === 0 || data.code === 200) {
            return data.data || data
        } else {
            // 业务错误
            uni.showToast({
                title: errorMsg,
                icon: 'error'
            })
            return Promise.reject(new Error(errorMsg))
        }
    } else {
        // HTTP 错误
        uni.showToast({
            title: errorMsg,
            icon: 'error'
        })
        return Promise.reject(new Error(errorMsg))
    }
}

// 请求方法
const request = (options = {}) => {
    // 合并配置
    const config = {
        url: options.url.startsWith('http') ? options.url : BASE_URL + options.url,
        method: options.method || 'GET',
        data: options.data || {},
        header: {
            'Content-Type': 'application/json',
            ...options.header
        },
        timeout: options.timeout || TIMEOUT,
        dataType: options.dataType || 'json',
        responseType: options.responseType || 'text'
    }

    // 请求拦截
    const interceptedConfig = requestInterceptor(config)

    // 返回 Promise
    return new Promise((resolve, reject) => {
        uni.request({
            ...interceptedConfig,
            success: (res) => {
                try {
                    const result = responseInterceptor(res)
                    resolve(result)
                } catch (error) {
                    reject(error)
                }
            },
            fail: (err) => {
                // 请求失败（网络问题）
                // uni.hideLoading()
                uni.showToast({
                    title: '网络连接失败，请检查网络',
                    icon: 'none'
                })
                reject(err)
            },
            complete: () => {
                // 请求完成
                // 可以在这里做全局完成处理
            }
        })
    })
}

// 封装常见请求方法
const http  = {
    /**
     * GET 请求
     * @param {string} url - 接口地址
     * @param {object} data - 请求参数
     * @param {object} options - 其他配置
     */
    get(url, data = {}, options = {}) {
        return request({
            url,
            method: 'GET',
            data,
            ...options
        })
    },

    /**
     * POST 请求
     * @param {string} url - 接口地址
     * @param {object} data - 请求参数
     * @param {object} options - 其他配置
     */
    post(url, data = {}, options = {}) {
        return request({
            url,
            method: 'POST',
            data,
            ...options
        })
    },

    /**
     * PUT 请求
     * @param {string} url - 接口地址
     * @param {object} data - 请求参数
     * @param {object} options - 其他配置
     */
    put(url, data = {}, options = {}) {
        return request({
            url,
            method: 'PUT',
            data,
            ...options
        })
    },

    /**
     * DELETE 请求
     * @param {string} url - 接口地址
     * @param {object} data - 请求参数
     * @param {object} options - 其他配置
     */
    delete(url, data = {}, options = {}) {
        return request({
            url,
            method: 'DELETE',
            data,
            ...options
        })
    },

    /**
     * 文件上传
     * @param {string} url - 接口地址
     * @param {object} filePath - 文件路径
     * @param {object} name - 文件对应的 key
     * @param {object} formData - 额外表单数据
     */
    upload(url, filePath, name = 'file', formData = {}) {
        return new Promise((resolve, reject) => {
            const token = uni.getStorageSync('token')
            uni.uploadFile({
                url: url.startsWith('http') ? url : BASE_URL + url,
                filePath,
                name,
                formData,
                header: {
                    'Authorization': token ? `Bearer ${token}` : ''
                },
                success: (res) => {
                    try {
                        const data = JSON.parse(res.data)
                        if (data.code === 0 || data.code === 200) {
                            resolve(data.data || data)
                        } else {
                            const errorMsg = data.message || data.msg || '上传失败'
                            uni.showToast({
                                title: errorMsg,
                                icon: 'none'
                            })
                            reject(new Error(errorMsg))
                        }
                    } catch (error) {
                        reject(error)
                    }
                },
                fail: (err) => {
                    uni.showToast({
                        title: '上传失败，请检查网络',
                        icon: 'none'
                    })
                    reject(err)
                }
            })
        })
    }
}

// 导出
export default http
