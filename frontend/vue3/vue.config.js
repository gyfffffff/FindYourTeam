const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false, /*关闭语法检查*/
  devServer: {
    hot: true,
    proxy: {
      '/api': {
        target: 'http://localhost:9090',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      },
      '/oauth/': {
        target: 'https://aip.baidubce.com',
        changeOrigin: true,
        pathRewrite: {
          '^/oauth/': '/oauth/'
        }
      },
      '/rpc': {
        target: 'https://aip.baidubce.com',
        changeOrigin: true,
        pathRewrite: {
          '^/rpc': '/rpc'
        }
      },
      '/oauth2': {
        target: 'https://api.ecnu.edu.cn',
        changeOrigin: true,
        pathRewrite: {
          '^/oauth2': '/oauth2'
        }
      },
    }
  }
})
