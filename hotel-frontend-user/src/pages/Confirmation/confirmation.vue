<template>
  <!--  <div v-if="alipay!==''" v-html="alipay"></div>-->
  <div class="page-main">
    <div class="item-img">
    </div>
    <el-divider></el-divider>
    <el-row class="card-search" :gutter="20" style="width:1250px;margin:20px auto;">
      <el-col :span="17" style="background:#fff; padding: 0 0;">

        <el-col :span="20" class="backBtn"><i class="el-icon-back"></i> Back</el-col>

        <!--左侧输入区-->
        <el-col :span="24" class="btn-item info-sel">

          <!--Contact Info-->
          <el-col :span="24" class="form-title" >Contact Info</el-col>
          <div style="height: 120px;">
            <el-col :span="8" style="display: flex">
              <el-col style="font-size: 18px; margin-top: 6px">Name: {{ this.data.contactName }}</el-col>
            </el-col>

          </div>
          <div style="height: 70px;">
            <el-col :span="8" style="display: flex">
              <el-col style="font-size: 18px; margin-top: 6px">Phone: {{ this.data.contactPhone }}</el-col>
              <!-- <el-input style="width: 480px" placeholder="Phone" v-model="phone"/> -->
            </el-col>
            <el-col :span="9" style="display: flex">
              <el-col style="font-size: 18px; margin-top: 6px">Email: {{ this.data.contactEmail }}</el-col>
              <!-- <el-input style="width: 450px;margin-right: -44px" placeholder="Email" v-model="email"/> -->
            </el-col>
          </div>


          <!--ADD GUEST (OPTIONAL)-->
          <el-col :span="24" v-if="isReload">
            <el-col :span="24" class="form-title">Guests:</el-col>
            <el-col style="font-size: 16px" v-for="(tenant,idx) in this.data.tenants">Guest {{ idx + 1 }}: &ensp;&ensp;
              Name:{{ tenant.tenantName }}&ensp; Phone:{{ tenant.telephone }} &ensp;Identity Card
              Number:{{ tenant.identityCard }}
            </el-col>
          </el-col>

          <!--Additional Details and Preferences-->
          <el-col :span="24">
            <el-col :span="24" class="form-title">Additional Details and Preferences:</el-col>
            <el-col style="font-size: 16px">
              {{ this.data.additional === '' ? 'No Additional Details' : this.data.additional }}
            </el-col>
          </el-col>
        </el-col>
        <div class="demo-image">

          <div class="block" fill>
            <span class="demonstration"></span>
            <el-image style="width: 150px; height: 100px" :src="alipay_url"></el-image>
          </div>
        </div>
        <div class="op_button">
          <el-button type="success" plain @click="pay">Pay</el-button>
          <el-button type="warning" plain @click="cancel">Cancel</el-button>
        </div>
      </el-col>


      <!--右侧信息-->
      <el-col :span="7" class="right-box" style="font-size:15px;">
        <div style="font-size:20px;line-height:45px;">Your Stay at {{ this.data.hotelName }}</div>
        <el-col :span="12" style="margin-top:20px;">
          <div style="font-weight:600;">Check-in</div>
          <div>After 3:00 PM</div>
        </el-col>
        <el-col :span="12" style="margin-top:20px;">
          <div style="font-weight:600;">Check-out</div>
          <div>Before 12:00 PM</div>
        </el-col>
        <el-col :span="24">
          <el-divider></el-divider>
        </el-col>

        <el-col :span="24">
          <div>Room Number: {{ this.data.roomNumber }}</div>
          <div>{{ new Date(this.data.startDate).toDateString() }} - {{ new Date(this.data.endDate).toDateString() }}
          </div>
        </el-col>
        <el-col :span="24" style="margin-top:10px">
          <div @click="show1=!show1" style="cursor: pointer;">{{ this.lastDays }} Night<i
              class="el-icon-arrow-down"></i></div>
          <div v-if="show1" style="justify-content: space-between;padding-left:30px;margin-top: 10px">
            <div style="display: flex" v-for="date in this.dates">
              <div>{{ date.toDateString() }}</div>
            </div>
          </div>
        </el-col>
        <el-col :span="24" style="padding-top:20px;display: flex;">
          <img src="../../assets/images/icon.jpg"/>
          <div style="font-weight:600;margin-left:10px;margin-top:5px;">Alternate currency displays are for information
            purposes only.
          </div>
        </el-col>
        <el-col :span="24">
          <el-divider></el-divider>
        </el-col>

        <el-col :span="24" style="display:flex;justify-content: space-between;">
          <div style="font-weight:600;">Price:</div>
          <div>
            <div style="font-weight:600;text-align: right;">￥{{ this.data.afterDiscount }}</div>
          </div>
        </el-col>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: "confirmation",
  data() {
    return {
      isEdit: true,
      alipay_url: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsaJJyj6to4vN1YGHSlJoWLOPxm0MOhxIeGg&usqp=CAU',
      show1: false,
      show2: false,
      value: '',
      filterText: '',
      activeNames: "1",
      showCheck: false,
      checked1: false,
      checked2: false,
      checked3: false,
      data: '',
      lastDays: 0,
      dates: [],
      guests: [],
      names: [],
      phones: [],
      identities: [],
      disables: [],
      isReload: true,
      alipay: ''
    };
  },
  methods: {
    pay() {
      this.$http({
        url: this.$http.adornUrl('/order/payOrder'),
        method: 'get',
        params: this.$http.adornParams({
          orderId: this.$route.query.orderId
        })
      }).then(data => {
        if (data.data.state === 200) {
          this.alipay = data.data.data;
          let routerData = this.$router.resolve({path: '/alipay', query: {htmlData: this.alipay}});
          window.open(routerData.href, '_ blank')
        } else {
          this.$message.error(data.data.message);
          this.$router.push('home');
        }
      })
    },
    cancel() {
      this.$router.push('home');
    }
  },
  created() {
    let orderId = this.$route.query.orderId;
    if (orderId === undefined || orderId === '') {
      this.$router.push('404');
    }
    this.$http({
      url: this.$http.adornUrl('/order/order/order/orderInfo'),
      method: 'get',
      params: this.$http.adornParams({
        orderId: orderId
      })
    }).then(data => {
      if (data.data.state !== 200) {
        this.$message.error(data.data.message);
        this.$router.push('404');
      } else {
        this.data = data.data.data;
        let d1 = new Date(this.data.startDate);
        let d2 = new Date(this.data.endDate);
        this.lastDays = Math.ceil((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
        let dates = [];
        dates.push(d1);
        let d = d1.getTime();
        for (let i = 0; i < this.lastDays; i++) {
          d += 1000 * 60 * 60 * 24;
          dates.push(new Date(d));
        }
        this.dates = dates;
      }
    }).catch(err => {
      this.$message.error("Network Error");
    })
  }
};
</script>

<style lang="scss" scoped>
.page-main {
  background: #f3eee7;

  .item-img {
    width: 1300px;
    height: 100%;
  }
  .page-main .btn-item[data-v-3aefd34a] {
    border: 1px solid #ddd;
    padding: 12px 0;
    cursor: pointer;
    background: #f3eee7;
  }

  .top-link {
    width: 480px;
    margin: 0 auto;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .top-link .el-link {
    margin: 0 15px;
  }

  .demo-image{
    text-align: center;
    background-color: #f3eee7;
  }

  .op_button {
    text-align: center;
    padding: 20px;
    background-color: #f3eee7;

  }

  .flex-row {
    display: flex;
    align-items: center;
  }

  .user-icon {
    font-size: 30px;
    margin-right: 5px;
  }

  .card-search {
    background: #f3eee7;
  }

  .btn-item {
    border: 1px solid #ddd;
    padding: 12px 0;
    cursor: pointer;
    background: #f3eee7;
  }

  .row-label {
    font-weight: 600;
    font-size: 16px;
  }

  .flex-row1 {
    display: flex;
  }

  .image {
    height: 230px;
    width: 100%;
  }

  .card-name {
    font-size: 18px;
  }

  .card-detail {
    margin-top: 25px;
    cursor: pointer;
  }

  .hotel-select {
    height: 70px;
    float: right;
  }

  .v-btn1 {
    border: 1px solid #333;
    text-align: center;
    line-height: 45px;
    width: 100%;
    margin-top: 10px;
    cursor: pointer;
  }

  .v-btn2 {
    border: 1px solid black;
    background: black;
    text-align: center;
    line-height: 45px;
    width: 100%;
    color: #f3eee7;
    margin-top: 10px;
    cursor: pointer;
  }

  .backBtn {
    font-size: 30px;
    line-height: 50px;
    margin-bottom: 20px;
  }

  .form-title {
    font-size: 20px;
    font-weight: bold;
    margin: 15px 0;
  }

  .info-sel .el-select .el-input {
    width: 130px;
  }

  .info-sel1 .el-select .el-input {
    width: 410px;
  }

  .el-textarea__inner {
    border: 1px solid #999 !important;
  }

  .el-input__inner {
    border: 1px solid #999 !important;
    height: 50px;
    line-height: 50px;
  }

  .input-with-select .el-input-group__prepend {
    background-color: #f3eee7;
  }

  .form-row {
    margin-bottom: 15px;
  }

  .el-collapse-item__wrap {
    background: #F3EEE7;
    padding-top: 20px;
  }

  .img-icon img {
    height: 30px;
    opacity: 0.5;
    margin-top: 15px;
    margin-right: 15px;
  }

  .el-checkbox__label {
    font-size: 17px;
    font-weight: 600;
    color: #333;
  }

  input::-webkit-input-placeholder {
    color: #999;
    font-size: 15px;
  }
.btn-item info-se{
  background-color: #f3eee7;
}
  .el-collapse-item__header {
    font-size: 16px;
  }
}

.select-item {
  line-height: 34px;
  margin-right: 10px;
  padding-left: 10px;
  cursor: pointer;
}

.select-item:hover {
  background: rgba(130, 132, 127, 1);
  color: #fff;
}

.right-box {
  background: #f3eee7;
  padding: 15px;
  border-left: 15px solid rgb(243, 238, 231);
}

.el-button--success {
  background-color: #f3eee7;
  font-family: 'nano';
  border-color: #000000;
  color: black;
}

.el-button--warning {
  font-family: 'nano';
  background-color: #f9f4dc;
  border-color: #000000;
  color: black;
}
.el-button--warning:hover{
  background-color: #f7e8aa;
  border-color: #000000;
  color: black;
}
.el-button--success:hover{
  background-color: white;
  border-color: #000000;
  color: black;
}

.rate-sel {
  text-align: right;
  background: #f3eee7;
  line-height: 30px;
  cursor: pointer;
}

.room-list {
  display: flex;
  border-bottom: 15px solid #f3eee7;
  padding: 15px 0;
}

.room-list img {
  width: 250px !important;
  height: 175px !important;
}

.room-right {
  padding-left: 20px;
  line-height: 30px;
  color: #444;
}

.flex-row2 {
  display: flex;
  justify-content: space-between;
}

.right-flex {
  display: flex;
  font-size: 13px;
  line-height: 22px;
  align-items: center;
  margin-bottom: 10px;
}

.icon-img img {
  width: 40px;
  vertical-align: middle;
}
</style>
  