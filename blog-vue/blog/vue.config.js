module.exports = {
  transpileDependencies: ["vuetify"],
  devServer: {
    proxy: {
      "/api": {
        // target: "http://localhost:8088",
        target: "http://193.112.190.161:8088",
        changeOrigin: true,
        pathRewrite: {
          "^/api": ""
        }
      }
    },
    disableHostCheck: true
  },
  productionSourceMap: false,
  css: {
    extract: true,
    sourceMap: false
  }
};
