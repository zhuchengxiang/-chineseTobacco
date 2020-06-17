<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">BOS系统登录</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="用户名"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="密码"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleLogin">登录</el-button>
    </el-form>
  </div>
</template>

<script>
  import {validUsername } from "../../../../vue-element-admin-master/src/utils/validate";

  export default {
        name: "index",
      data: function () {
        return {
          logining: false,
          loginForm: {
            name: 'admin',
            password: 'admin'
          },
          fieldRules: {
            name: [
              {required: true, message: '请输入账号', trigger: 'blur'},
            ],
            password: [
              {required: true, message: '请输入密码', trigger: 'blur'}
            ]
          },
          checked: true
        }
      },

      methods:{
          handleLogin () {
            this.$refs.loginForm.validate(valid => {
              //如果表单校验通过
              if(valid) {
                this.loading = true
                // 调用store里的user下的login方法
                this.$store.dispatch('user/login', this.loginForm).then(() => {
                  //是路由跳转
                  this.$router.push({path: this.redirect || '/', qurey: this.otherQuery})
                  this.loading = false
                }).catch(() => {
                  this.loading =false
                })
              }else {
                console.log('error submit!')
                return false
              }
            })
          },
       /* login() {
          let userInfo= {name:this.loginForm.name,password:this.loginForm.password}
          console.debug(userInfo)
          this.$api.login(userInfo).then( (res) => {
            console.debug(res)
            console.debug(res.token)
            Cookies.set('token',res.token) // 放置token到Cookie
            sessionStorage.setItem('user',res.loginUser)// 保存本地
            this.$router.push('/') //登录成功 跳转页面
          }).catch(function (res) {
            alert(res)
          })
        },*/
        reset(){
          alert()
          this.$refs.loginForm.resetFields();
        }

      }
    }

</script>

<style lang="scss" scoped>
  .login-container {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 100px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    .title {
      margin: 0px auto 30px auto;
      text-align: center;
      color: #505458;
    }
    .remember {
      margin: 0px 0px 35px 0px;
    }
  }
</style>
