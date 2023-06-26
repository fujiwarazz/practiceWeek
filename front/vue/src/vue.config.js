module.exports = {
    devServer: {
        port:8080,//端口号
        open:true,//自动在浏览器上打开项目
        proxy:'http://localhost:9090'
    }
}


