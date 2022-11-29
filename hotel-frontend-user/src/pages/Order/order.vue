<template>
  <div class="page-main">
    <div class="item-img">
    </div>
    <el-divider></el-divider>
    <el-row class="card-search" :gutter="20" style="width:1250px;margin:20px auto;">
      <el-col :span="17" style="background:#fff; padding: 0 0;">

        <el-col :span="24" class="backBtn"><i class="el-icon-back"></i> Guest Details</el-col>

        <!--左侧输入区-->
        <el-col :span="24" class="btn-item info-sel">

          <!--Contact Info-->
          <el-col :span="24" class="form-title">Contact Info</el-col>
          <div style="height: 120px;">
            <el-col :span="8" style="display: flex">
              <el-col style="font-size: 20px; margin-top: 6px">Name:</el-col>
              <el-input style="width: 480px" placeholder="Name" v-model="truename"/>
            </el-col>
            <el-col :span="10" style="display: flex">
              <el-col style="font-size: 20px; margin-top: 6px">Username:</el-col>
              <el-input style="width: 480px" placeholder="Username" v-model="username"/>
            </el-col>
          </div>
          <div style="height: 70px;">
            <el-col :span="8" style="display: flex">
              <el-col style="font-size: 20px; margin-top: 6px">Phone:</el-col>
              <el-input style="width: 480px" placeholder="Phone" v-model="phone"/>
            </el-col>
            <el-col :span="9" style="display: flex">
              <el-col style="font-size: 20px; margin-top: 6px">Email:</el-col>
              <el-input style="width: 450px;margin-right: -44px" placeholder="Email" v-model="email"/>
            </el-col>
          </div>


          <!--ADD GUEST (OPTIONAL)-->
          <el-col :span="24" v-if="isReload">
            <el-collapse v-model="activeNames">
              <el-collapse-item title="ADD GUESTS" name="1">
                <div v-for="i in this.data.upperLimit">
                  <el-col :span="24" class="form-row" style="font-size:15px;">Guest {{ i }}</el-col>
                  <el-col :span="10" class="form-row">
                    <el-input placeholder="Name" v-model="names[i]" v-bind:disabled="disables[i]"/>
                  </el-col>
                  <el-col :span="10" class="form-row">
                    <el-input placeholder="Phone Number" v-model="phones[i]" v-bind:disabled="disables[i]"/>
                  </el-col>
                  <br>
                  <el-col :span="10" class="form-row">
                    <el-input placeholder="Identity Number" v-model="identities[i]" v-bind:disabled="disables[i]"/>
                  </el-col>
                  <el-col :span="2" class="form-row"></el-col>
                  <el-col :span="3" class="form-row" v-if="!disables[i]">
                    <el-link style="margin-top: 6px;font-size: 16px" @click="addGuest(i)">
                      <i class="el-icon-circle-plus"></i>Add
                    </el-link>
                  </el-col>
                  <el-col :span="3" class="form-row" v-if="disables[i]">
                    <el-col style="margin-top: 7px;font-size: 16px;color: cornflowerblue">Added</el-col>
                  </el-col>
                  <el-col :span="3" class="form-row">
                    <el-link style="margin-top: 6px;font-size: 16px" @click="removeGuest(i)"><i class="el-icon-delete"></i>Remove</el-link>
                  </el-col>
                </div>
              </el-collapse-item>
            </el-collapse>
          </el-col>

          <!--Additional Details and Preferences-->
          <el-col :span="24">
            <el-col :span="24" class="form-title">Additional Details and Preferences</el-col>
            <el-col :span="24">
              <el-input type="textarea" rows="7" placeholder="Please note your requests or special needs"
                        v-model="value"/>
            </el-col>
          </el-col>
        </el-col>

        <!--Acknowledgement-->
        <el-col :span="24" style="background:#f3eee7;line-height:20px;height:20px;"></el-col>
        <el-col :span="24" style="font-size:16px; line-height:40px;">
          <el-col :span="24" class="form-title">Acknowledgement</el-col>
          <div>
            <el-checkbox v-model="checked2">* I agree with the Privacy Terms.</el-checkbox>
          </div>
          <div>
            <el-checkbox v-model="checked3">* I agree with the Booking Conditions.</el-checkbox>
          </div>
        </el-col>

        <!--Complete Booking-->
        <el-col :span="24" style="background:#f3eee7; height:80px;text-align: right;padding-top:20px;">
          <el-button @click="book" type="info" style="background:black;height: 55px;" v-bind:disabled="!(checked2&&checked3)">Complete Booking<i class="el-icon-lock" v-if="!(checked2&&checked3)"></i>
          </el-button>
        </el-col>
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
          <div>Room Number: {{this.data.roomNumber}}</div>
          <div>{{ new Date(this.data.startDate).toDateString() }} - {{ new Date(this.data.endDate).toDateString() }}
          </div>
          <div>{{ this.data.upperLimit }} Guests Max</div>
        </el-col>
        <el-col :span="24" v-if="data.breakfast===1">Breakfast Provided</el-col>
        <el-col :span="24" v-if="data.breakfast!==1">Breakfast Not Provided</el-col>
        <el-col :span="24" style="display:flex;justify-content: space-between;padding-top: 20px;">
          <div>Unit Price Per Night:</div>
          <div style="font-weight:600;">￥{{ this.data.unitPrice }}</div>
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
          <div style="font-weight:600;">Total:</div>
          <div>
            <div style="font-weight:600;text-align: right;">￥{{ this.data.totalPrice }}</div>
          </div>
        </el-col>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import cookie from "js-cookie";
import {convertToDate} from "@/utils/utils";

let sd = '';
let ed = '';

export default {
  name: "home",
  data() {
    return {
      show1: false,
      show2: false,
      truename: '',
      username: '',
      phone: '',
      email: '',
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
      isReload: true
    };
  },
  methods: {
    addGuest(i) {
      this.guests[i] = {'name': this.names[i], 'phone': this.phones[i], 'identity': this.identities[i]};
      this.disables[i] = true;
      this.reloadGuests();
    },
    removeGuest(i){
      this.guests[i] = '';
      this.names[i] = '';
      this.phones[i] = '';
      this.identities[i] = '';
      this.disables[i] = false;
      this.reloadGuests();
    },
    reloadGuests() {
      this.isReload = false;
      this.$nextTick(() => {
        this.isReload = true;
      })
    },
    book() {
      let strings = []
      for (let i = 0;i < this.guests.length;i++) {
        let s = this.guests[i].name + ',' + this.guests[i].phone + ',' + this.guests[i].identity;
        strings.push(s);
      }
      this.$http({
        url: this.$http.adornUrl('/order/order/order/generateOrder'),
        method: 'post',
        data: this.$http.adornData({
          'userToken': cookie.get('token'),
          'orderToken': this.data.token,
          'roomId': this.data.roomId,
          'startDate': sd,
          'endDate': ed,
          'additional': this.value,
          'guestInfo': strings
        })
      }).then(data => {
        console.log(data);
      }).catch(err => {
        console.log(err)
        this.$message.error("Network Error");
      })
    }
  },
  created() {
    let startDate = this.$route.query.startDate;
    let endDate = this.$route.query.endDate;
    let roomTypeId = this.$route.query.roomTypeId;
    let hotelId = this.$route.query.hotelId;
    if (startDate === undefined || endDate === undefined ||
        roomTypeId === undefined || hotelId === undefined) {
      this.$router.push('404');
      return;
    }
    let roomId = this.$route.query.roomId;
    let userToken = cookie.get('token');
    let d1 = convertToDate(startDate);
    let d2 = convertToDate(endDate);
    sd = d1;
    ed = d2;
    this.$http({
      url: this.$http.adornUrl('/order/order/order/confirmOrder'),
      method: 'post',
      data: this.$http.adornData({
        'startDate': d1,
        'endDate': d2,
        'userToken': userToken,
        'roomTypeId': roomTypeId,
        'roomId': roomId,
        'hotelId': hotelId
      })
    }).then(data => {
      if (data.data.state === 200) {
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
        this.truename = this.data.user.username;
        this.username = this.data.user.socialName;
        this.phone = this.data.user.phone;
        this.email = this.data.user.email;
        for (let i = 0; i < this.data.upperLimit; i++) {
          this.names.push('');
          this.phones.push('');
          this.identities.push('');
          this.guests.push('');
          this.disables.push(false);
        }
      } else {
        this.$message.error(data.data.message);
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
