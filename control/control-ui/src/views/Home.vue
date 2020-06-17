<template>
<div>
  <h2>home页面</h2>
  <el-button type="primary" @click="testAxios">测试axios调用</el-button>
  <el-button type="primary" @click="getUsers">获得用户信息</el-button>
  <el-button type="primary" @click="getMenus">获得菜单信息</el-button>
</div>
</template>

<script>
  import mock from '@/mock/mock.js';
  import axios from 'axios'
export default {
  methods: {
    testAxios () {

      axios.get('http://localhost:9527').then(function (res) {
        alert(res.data)
      }).catch(function (res) {
        alert(res)
      })
    },
    getUsers () {
      axios.get('http://localhost:9527/user').then(function (res) {
        alert(JSON.stringify(res.data))
      }).catch(function (res) {
        alert(res)
      })
    },
    getMenus () {
      axios.get('http://localhost:9527/menu').then(function (res) {
        alert(JSON.stringify(res.data))
      }).catch(function (res) {
        alert(res)
      })
    }

  }
}
</script>

<style scoped>

</style>
