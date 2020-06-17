import axios from 'axios'
import config from './config'
import qs from 'qs'
import Cookies from 'js-cookie'
import router from '@/router'
// 使用vuex做全局loading时用

export default function $axios(options) {
  return new Promise((resolve, reject) => {
    const instance = axios.create({
      baseURL: config.baseURL,
      headers: {},
      data: config.data,
      transformResponse: [function (data) {
      }]
    })
    // request拦截器
    instance.interceptors.request.use(
      config => {
        let token=Cookies.get('token')
        // 1.请求开始时候可以结合vuex开启全屏 loading动画
        // console.log(store.state.loading)
        // console.log('准备发送请求...')
        //2.带上 token
        if(token){
          config.headers.accessToken = token
        }else {
          // 重新定向
          router.push('/users/login')
        }
        console.debug('axios.js')
        // 根据请求方法 序列化传来参数 根据后端需求是否序列
        // 如果 post提交 后台加上requestbody
        if(config.method === 'get'){
          console.debug('no post')
          //     序列化参数
          config.data =qs.stringify(config.data)
          console.debug(config.data)
        }
        return config
      },
      error => {
        // 请求错误
        console.log('request:',error)
        // 1.判断请求
        if (error.code === 'ECONNABORTED' && error.message.indexOf('timeout')!==-1){
          console.log('timeout请求超时')
          // return service.request(originalRequest);// 再重复请求一次
        }
        // 2.需要重定向错误
        const errorInfo = error.response
        console.log(errorInfo)
        if(errorInfo){
          error=errorInfo.data // 页面那边catch的时候就能拿到详细的错误信息,看最下边的Promise.reject
          const errorStatus= errorInfo.status;// 404 403 500 ...
          router.push({
            path: '/error/${errorStatus}'
          })
        }
        return Promise.reject(error) // 在调用的那边可以拿到(catch)你想返回的错误信息
      }
    )
    // response拦截器
    instance.interceptors.response.use(
      response => {
        let data;
        // ie9response.data是undefind ，因此需要使用response.requese.responseText(Stringify后的字符串）
        if(response.data==undefined){
          data = JSON.parse(response.request.responseText)
        }else {
          data=response.data
        }
        // 根据返回的code值做不同的处理
        switch(data.rc){
          case 1:
            console.log(data.desc)
                break;
          case 0:
          // store.commit('changeState')
          // console.log('登录成功')
          default:
        }// 若不是正确的返回code，且已经登录，就抛出错误
        // const err = new Error(data.desc)
        // err.data = data
        // err.response = response
        // throw err
        return data
      },
      err => {
        if (err&&err.response) {
          switch (err.response.status) {
            case 400:
              err.message = '请求错误'
                  break
            case 401:
              err.message = '未授权，请登录'
              break
            case 403:
              err.message = '拒绝访问'
              break
            case 404:
              err.message = '请求地址错误:${err.response.config.url}'
              break
            case 408:
              err.message = '请求超时'
              break
            case 500:
              err.message = '服务器内部错误'
              break
            case 501:
              err.message = '服务未实现'
              break
            case 502:
              err.message = '网关错误'
              break
            case 503:
              err.message = '服务不可用'
              break
            case 504:
              err.message = '网关超时'
              break
            case 505:
              err.message = 'HTTP版本不受支持'
              break
            default:
          }
        }
        console.error(err)
        return Promise.reject(err) //返回接口返回错误信息
      }
    )
    // 请求处理
    instance(options).then(res => {
      resolve(res)
      return false
    }).catch(error => {
      reject(error)
    })
  })
}
