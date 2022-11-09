<template>
  <div class="page-main">
    <!--homepage最开始几张滚动的图片，轮播图组件-->
    <el-carousel height="700px">
      <el-carousel-item v-for="item in imgList" :key="item"><img :src="item" class="item-img"></el-carousel-item>
    </el-carousel>

    <!--homepage--中间的图文，待改成优惠减免信息-->
    <el-row class="card-img" :gutter="20">

      <!--left part-->
      <el-col :span="8">
        <img src="../../assets/images/img1.png" class="left-img"/>
        <div style="color:#888;">NEW & NOTEWORTHY</div>
        <div class="left-title">A Quest for Happiness</div>
        <div class="content">lnspircd by Bhutani's dedication to happiness over economic prosperity, Amankorai's new seven- to 1z-night journey invites you to give back to local communities.</div>
        <el-link type="info" @click="toMore">Discover more</el-link>
      </el-col>

      <!--right part-->
      <el-col :span="16">
        <img src="../../assets/images/img2.jpeg" class="right-img"/>
        <div style="color:#888;">STORIES</div>
        <div class="left-title">Mist and mysticism in Borobodur</div>
        <div class="content">Join webby-honoured editor Xerxes Cook and celebrated French photographer ferome Gallnd as they uncover Central ava's cultural heartland from Amanjiwwo, sharing firsthand the secrets of its storied landscapcs.</div>
        <el-link type="info" @click="toMore">Discover more</el-link>
      </el-col>

    </el-row>
  </div>
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      imgList: [
        require('../../assets/images/1.jpeg'),
        require('../../assets/images/2.jpeg'),
        require('../../assets/images/3.jpeg')
      ]
    };
  },
  methods: {
    toMore() {
      this.$message.info('more')
    },
  }
};
</script>

<style lang="scss" scoped>
.page-main {
  background: #f3eee7;  /////////////背景的米白色here

  .item-img {
    width: 1300px;
    height: 100%;
  }

  .card-img {
    margin-top: 40px;
  }

  .left-img {
    width: 98%;
    height: 500px;
    margin-bottom: 20px;
  }

  .right-img {
    width: 98%;
    height: 500px;
    margin-bottom: 20px;
  }

  .left-title {
    font-size: 17px;
    line-height: 50px;
  }

  .content {
    color: #888;
    margin-bottom: 10px;
  }
}
</style>
