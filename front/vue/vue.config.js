const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // devServer: {
  //   port: '8080', // 设置端口号
  //   proxy: {
  //     '/Code': {
  //       target: 'http://localhost:9090', //API服务器的地址
  //       ws: true, //代理websockets
  //       changeOrigin: true, // 是否跨域，虚拟的站点需要更管origin
  //       pathRewrite: {'^/Code': ''}
  //     }
  //   },
  // }
})
