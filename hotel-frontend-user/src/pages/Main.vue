<template>
  <el-container>
    <el-container>
      <el-header>
        <common-header></common-header>
      </el-header>
      <el-main>
        <!-- 主要内容路由 -->
        <router-view></router-view>
      </el-main>
      <el-footer>
        <CommonFooter/>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script>
import CommonHeader from '../components/CommonHeader.vue'
import CommonFooter from '../components/CommonFooter.vue'

export default {
  name: 'Home',
  components: {CommonHeader, CommonFooter},
  data() {
    return {}
  },
  onLoad() {
  },
  methods: {}
}
</script>

<style lang="scss" scoped>
.el-container {
  height: 100%;

  .el-container {
    .el-header {
      background-color: #f3eee7;
      color: #333;
      line-height: 60px;

      header {
        max-width: 1300px;
        margin: 0 auto;
      }
    }

    .el-main {
      background-color: #f3eee7;
      color: #333;
      overflow: inherit;
      // text-align: center;
      // line-height: 160px;
    }

    .el-footer {
      height: auto !important;
    }
  }
}


</style>
