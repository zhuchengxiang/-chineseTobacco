import Mock from 'mockjs'
// 模拟数据层
Mock.mock('http://localhost:9527/user',{
  data: {
    'name': '@name', // 随机生成名字
    'email': '@email',// 随机生成邮箱
    'age|1-10': 5 // 取值
  }
});
// 模拟菜单数据
Mock.mock('http://localhost:9527/menu',{
  data: {
    'id': '@increment', // 自动增长
    'name': 'menu',
    'order|10-20': 12
  // 其他数据
  }
});
Mock.mock('http://localhost:9527/login',{
  data: {
    'token': '12345678'
  }
})
