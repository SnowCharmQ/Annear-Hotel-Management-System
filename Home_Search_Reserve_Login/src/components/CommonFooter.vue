<template>
  <div>
    <div class="page-main" v-if="show">
      <el-divider></el-divider>

      <!--homepage底部的一堆按钮-->
      <el-row class="card-img" :gutter="20">
        <el-col :span="6">
          <h3 class="link-title">More information</h3>
          <el-link href="">About Us</el-link>
          <el-link href="">Contact Us</el-link>
          <el-link href="">Gift Card</el-link>
          <el-link href="">Forthcoming Developments</el-link>
          <el-link href="">Careers</el-link>
          <el-link href="">Leadership</el-link>
          <el-link href="">Privacy Notice</el-link>
          <el-link href="">Digital Accessibility</el-link>
          <el-link href="">Sustainablitity</el-link>
          <h3 class="link-title">Contact us</h3>
          <el-link href="">Call reservations</el-link>
        </el-col>

        <el-col :span="6">
          <h3 class="link-title">Destinations</h3>
          <el-link href="">Americas & Caribbean</el-link>
          <el-link href="">Amanera, Dominican Republic</el-link>
          <el-link href="">Amanvari, Coming soon</el-link>
          <el-link href="">Amanyara,Turks & caicos Islands</el-link>
          <el-link href="">Sman Beverly Hills,Coming soon</el-link>
          <el-link href="">Aman New York, United States</el-link>
          <el-link href="">Amangani, United States</el-link>
          <el-link href="">Amangiri, United States</el-link>
          <h3 class="link-title">Europe & Africa</h3>
          <el-link href="">Aman Le Mélézin, France</el-link>
          <el-link href="">Amanzoe, Greece</el-link>
          <el-link href="">Aman Venice, Italy</el-link>
          <el-link href="">Rosa Alpina, Italy</el-link>
          <el-link href="">Aman Sveti Stefan, Montenegro</el-link>
          <el-link href="">Amanjena, Morocco</el-link>
        </el-col>

        <el-col :span="6">
          <h3 class="link-title">Asia</h3>
          <el-link href="">Amankora, Bhutan</el-link>
          <el-link href="">Amansara, Cambodia</el-link>
          <el-link href="">Aman Summer Palace, China</el-link>
          <el-link href="">Amandayan, China</el-link>
          <el-link href="">Amanfayun, China</el-link>
          <el-link href="">Amanyangyun, China</el-link>
          <el-link href="">Aman-i-Khas, India</el-link>
          <el-link href="">Amanbagh, India</el-link>
          <el-link href="">Aman Villas at Nusa Dua, Indonesia</el-link>
          <el-link href="">Amandari, Indonesia</el-link>
          <el-link href="">Amandira, Indonesia</el-link>
          <el-link href="">Amanjiwo, Indonesia</el-link>
          <el-link href="">Amankila, Indonesia</el-link>
        </el-col>

        <el-col :span="6">
          <h3 class="link-title">Asia (continued).</h3>
          <el-link href="">Aman Niseko, Coming soon</el-link>
          <el-link href="">Aman Residences, Tokyo, Coming soon</el-link>
          <el-link href="">Aman Tokyo, Japan</el-link>
          <el-link href="">Amanemu, Japan</el-link>
          <el-link href="">Amantaka, Laos</el-link>
          <el-link href="">Amanpulo, Philippines</el-link>
          <el-link href="">Amangalla, Sri Lanka</el-link>
          <el-link href="">Amanwella, Sri Lanka</el-link>
          <el-link href="">Aman Nai Lert Bangkok, Coming soon</el-link>
          <el-link href="">Amanpuri, Thailand</el-link>
          <el-link href="">Amanoi, Vietnam</el-link>
        </el-col>

      </el-row>
      <el-divider></el-divider>

      <!--homepage底部的图标和social media icon们-->
      <div class="flex-row">
        <div><img src="https://www.aman.com/themes/custom/aman/logo.svg" class="logo"/></div>
        <div style="display:flex;">
          <div class="icon-title">Follow us on:</div>
          <div class="icon-list">
            <img src="../assets/images/icon/1.jpg"/>
            <img src="../assets/images/icon/2.jpg"/>
            <img src="../assets/images/icon/3.jpg"/>
            <img src="../assets/images/icon/4.jpg"/>
            <img src="../assets/images/icon/5.jpg"/>
            <img src="../assets/images/icon/6.jpg"/>
            <img src="../assets/images/icon/7.jpg"/>
            <img src="../assets/images/icon/8.jpg"/>
            <img src="../assets/images/icon/9.jpg"/>
          </div>
        </div>
        <p class="copyright">Copyright 2022, Aman Group S.a.r.l.</p>
      </div>
      <br><br><br><br> <br>

      <!--homepage底部的selection bar-->
      <div class="flex-row fixedbox">
        <div style="margin-top:15px;color:#666;">Book your stay ></div>

        <div>
          <div class="bottom-title">LOCATION</div>
          <el-select v-model="value" placeholder="selection" size="small" style="width:270px">
            <el-option label="Begin your journey" value="1"></el-option>
            <el-option label="Begin your one" value="2"></el-option>
            <el-option label="Begin your two" value="3"></el-option>
            <el-option label="Begin your three" value="4"></el-option>
          </el-select>
        </div>

        <div>
          <div class="bottom-title">ARRIVAL</div>
          <el-date-picker v-model="date1" type="date" placeholder="ARRIVAL" size="small"></el-date-picker>
        </div>

        <div>
          <div class="bottom-title">DEPARTURE</div>
          <el-date-picker v-model="date2" type="date" placeholder="DEPARTURE" size="small"></el-date-picker>
        </div>

        <div>
          <div class="bottom-title">GUESTS</div>
          <el-select v-model="value2" placeholder="selection" size="small" style="width:160px">
            <el-option label="1" value="1"></el-option>
            <el-option label="2" value="2"></el-option>
            <el-option label="3" value="3"></el-option>
            <el-option label="4" value="4"></el-option>
          </el-select>
        </div>

        <div>
          <el-button style="margin-top:10px;">Check availatity</el-button>
        </div>

      </div>
    </div>

    <!--search page底部的字-->
    <div v-else style="width:100%; margin-top:-20px;">
      <div class="flex-row2">
        <img src="https://www.aman.com/themes/custom/aman/logo.svg" style="width:100px;"/>
        <div>Copyright 2022, Aman Resorts Ltd.</div>
      </div>
    </div>

    <br>
  </div>
</template>

<script>
export default {
  name: 'CommonFooter',
  data() {
    return {
      value: '1',
      value2: '',
      value3: 'Check a',
      date1: '',
      date2: '',
      show: true
    }
  },
  watch: {
    '$route': {
      handler: function () {
        this.show = !(this.$route.name === 'search' || this.$route.name === 'reserve' || this.$route.name === 'order' || this.$route.name === 'login');
      },
      // 深度观察监听
      deep: true,
      immediate: true,
    }
  }
}
</script>

<style scoped>
.page-main {
  background: #fff;
}

.link-title {
  font-size: 16px;
  line-height: 35px;
  margin-top: 10px;
  padding-left: 15px;
}

.el-link {
  display: block;
  line-height: 26px;
  padding-left: 15px;
}

.page-main >>> .el-link.is-underline:hover:after {
  border-bottom: none !important;
  text-decoration: underline;
}

.flex-row {
  display: flex;
  justify-content: space-between;
}

.logo {
  width: 100px;
}

.icon-title {
  vertical-align: MIDDLE;
  margin-top: 13px;
  margin-right: 20px;
}

.icon-list img {
  padding: 0 10px;
  cursor: pointer;
  border-radius: 1px;
}

.icon-list img:hover {
  opacity: 0.8;
}

.bottom-title {
  font-size: 12px;
  color: #666;
}

.fixedbox {
  position: fixed;
  bottom: 0;
  width: 1300px;
  background: #fff;
  padding: 20px 10px;
  z-index: 100;
}

.flex-row2 {
  width: 1300px;
  display: flex;
  justify-content: space-between;
  margin-top: auto;
  padding: 0 15px;
  padding-top: 25px;
  background: #fff;
}
</style>
