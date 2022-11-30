<template>
  <div class="page-main">
    <div class="item-img">
    </div>
    <el-divider></el-divider>
    <el-row class="card-search" :gutter="20" style="width:1250px;margin:20px auto;">
      <el-col :span="17" style="background:#fff; padding: 0 0;">

        <el-col :span="24" class="backBtn"><i class="el-icon-back"></i> Back</el-col>

        <!--左侧输入区-->
        <el-col :span="24" class="btn-item info-sel">

          <!--Contact Info-->
          <el-col :span="24" class="form-title">Contact Info</el-col>
          <div style="height: 120px;">
            <el-col :span="8" style="display: flex">
              <el-col style="font-size: 20px; margin-top: 6px">Name: {{ this.data.contactName }}</el-col>
            </el-col>

          </div>
          <div style="height: 70px;">
            <el-col :span="8" style="display: flex">
              <el-col style="font-size: 20px; margin-top: 6px">Phone: {{ this.data.contactPhone }}</el-col>
              <!-- <el-input style="width: 480px" placeholder="Phone" v-model="phone"/> -->
            </el-col>
            <el-col :span="9" style="display: flex">
              <el-col style="font-size: 20px; margin-top: 6px">Email: {{ this.data.contactEmail }}</el-col>
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

        <el-button type="success" plain @click="pay">Pay</el-button>
        <el-button type="warning" plain @click="cancel">Cancel</el-button>
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
      OrderID: 12114514,
      isEdit: true,
      alipay_url: 'data:image/png;base64,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',
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
    };
  },
  methods: {
    pay() {
      this.$http({

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
    background: #fff;
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
    color: #fff;
    margin-top: 10px;
    cursor: pointer;
  }

  .backBtn {
    font-size: 30px;
    line-height: 50px;
    margin-bottom: 20px;
  }

  .form-title {
    font-size: 24px;
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
    background-color: #fff;
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
  background: #fff;
  padding: 15px;
  border-left: 15px solid rgb(243, 238, 231);
}

.rate-sel {
  text-align: right;
  background: #fff;
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
  