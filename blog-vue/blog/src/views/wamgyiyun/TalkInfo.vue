<template>
  <div class="all">
    <div class="banner" :style="cover">
      <h1 class="banner-title">网易云模拟</h1>
    </div>
    
    
    <iframe src="http://193.112.190.161:8102/#/" class="mass"></iframe>
  
  
  </div>
</template>

<script>
export default {
  
  
  computed: {
    cover() {
      return "background: url(https://img0.baidu.com/it/u=1900226001,669563525&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500) center center / cover no-repeat;";
    }
  }
};
</script>

<style scoped>
.all {
  width: 100%;
  height: 100%;
}

.title {
  text-align: center; /*水平居中*/
  width: 100%;
}

.mass {
  margin-top: 361px;
  overflow: auto;
  text-align: center;
  margin-left: 41%;
  width: 375px;
  height: 667px
  
}
</style>
