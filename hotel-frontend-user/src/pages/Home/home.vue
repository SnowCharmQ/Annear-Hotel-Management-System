<template>
  <div class="page-main">
    <!--homepage最开始几张滚动的图片，轮播图组件-->
    <el-carousel height="700px">
      <el-carousel-item v-for="item in imgList" :key="item"><img :src="item" class="item-img"></el-carousel-item>
    </el-carousel><br>

    <el-row class="card-img" :gutter="20">

      <el-col :span="16">
        <img src="../../assets/images/home11.jpg" class="right-img"/>
        <div style="color:#888;">STORIES</div>
        <div class="left-title">Mist and mysticism in Borobodur</div>
        <div class="content">Join webby-honoured editor Xerxes Cook and celebrated French photographer uncover Central ava's cultural heartland from Annearjiwwo, sharing firsthand the secrets of its storied landscapes.</div>
        <el-link type="info" @click="toMore">Discover more</el-link>
      </el-col>

      <el-col :span="8">
        <img src="../../assets/images/home12.jpg" class="left-img"/>
        <div style="color:#888;">NEW & NOTEWORTHY</div>
        <div class="left-title">Beauty, serenity and cultural heritage</div>
        <div class="content">Enjoy seclusion and privacy in locations of unprecedented serenity and cultural heritage.</div>
        <el-link type="info" @click="toMore">Discover more</el-link>
      </el-col>

    </el-row><br>


    <!--homepage--中间的图文，待改成优惠减免信息-->
    <el-row class="card-img" :gutter="20">

      <!--left part-->
      <el-col :span="8">
        <img src="../../assets/images/home21.jpg" class="left-img"/>
        <div style="color:#888;">HOTELS & RESORTS</div>
        <div class="left-title">Under one sky</div>
        <div class="content">Celebrate the festive season with Annear</div>
        <el-link type="info" @click="toMore">Discover more</el-link>
      </el-col>

      <!--right part-->
      <el-col :span="16">
        <img src="../../assets/images/home22.jpg" class="right-img"/>
        <div style="color:#888;">FESTIVAL RETREATS</div>
        <div class="left-title">A Quest for Happiness</div>
        <div class="content">Inspired by Bhutani's dedication to happiness over economic prosperity, Annearkorai's new journey invites you to give back to local communities.</div>
        <el-link type="info" @click="toMore">Discover more</el-link>
      </el-col>

    </el-row><br>


    <el-row class="card-img" :gutter="20">

      <el-col :span="12">
        <img src="../../assets/images/home31.jpg" class="left-img"/>
        <div style="color:#888;">DISCOUNT</div>
        <div class="left-title">Book at lower price</div>
        <div class="content">Certain types of room in Annear will have a discount on 15th, each month.</div>
        <el-link type="info" @click="toMore">Discover more</el-link>
      </el-col>

      <el-col :span="12">
        <img src="../../assets/images/home32.jpg" class="right-img"/>
        <div style="color:#888;">MEMBERSHIP</div>
        <div class="left-title">Preferential policy are granted to Annear membership</div>
        <div class="content">You become a member of Annear once you book for more than 5 times, and can enjoy a lower price from then on.</div>
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
        require('../../assets/images/home1.png'),
        require('../../assets/images/home2.png'),
        require('../../assets/images/home3.png')
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
