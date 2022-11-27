<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style lang="scss">
// 字体自定义
@font-face {
  font-family: 'nano';
  src: url('./assets/fonts/LXGWWenKaiMono-Regular.ttf');
}
//定义全局样式
*{
  margin: 0;
  padding: 0;
  list-style: none;
  outline: none;
  text-decoration: none;
} 
.flex{
  display: flex;
}
.j-c{
  justify-content: center;
}
.j-s{
  justify-content: space-between;
}
.a-c{
  align-items: center;
}
</style>
