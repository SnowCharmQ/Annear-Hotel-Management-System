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
          <el-col :span="12" class="form-row">
            <el-input placeholder="First Name" v-model="value1" class="input-with-select">
              <el-select v-model="value2" slot="prepend" placeholder="Prefix">
                <el-option label="Mr." value="1"></el-option>
                <el-option label="Ms." value="2"></el-option>
                <el-option label="Mrs." value="3"></el-option>
                <el-option label="Madame" value="4"></el-option>
                <el-option label="Miss" value="5"></el-option>
                <el-option label="Lady" value="6"></el-option>
                <el-option label="Dr." value="7"></el-option>
                <el-option label="Professor" value="8"></el-option>
              </el-select>
            </el-input>
          </el-col>
          <el-col :span="12" class="form-row">
            <el-input placeholder="Last Name" v-model="value3"/>
          </el-col>
          <el-col :span="12" class="form-row">
            <el-input placeholder="Phone" v-model="value4"/>
          </el-col>
          <el-col :span="12" class="form-row">
            <el-input placeholder="Email Address" v-model="value5"/>
          </el-col>

          <!--Address-->
          <el-col :span="24" class="form-title">Address</el-col>
          <el-col :span="24" class="form-row  info-sel1">
            <el-select v-model="value6" placeholder="Country" style="width:370px">
              <el-option label="China" value="1"></el-option>
              <el-option label="United States" value="2"></el-option>
              <el-option label="Afghanistan" value="3"></el-option>
              <el-option label="Aland Islands" value="4"></el-option>
              <el-option label="Albania" value="5"></el-option>
              <el-option label="Algeria" value="6"></el-option>
              <el-option label="American Samoa" value="7"></el-option>
              <el-option label="Andorra" value="8"></el-option>
            </el-select>
          </el-col>
          <el-col :span="24" class="form-row">
            <el-input placeholder="Address" v-model="value7" style="width:410px"/>
          </el-col>
          <el-col :span="12" class="form-row">
            <el-input placeholder="City" v-model="value8"/>
          </el-col>
          <el-col :span="12" class="form-row">
            <el-input placeholder="Zip / Postal Code " v-model="value9"/>
          </el-col>

          <!--ADD GUEST (OPTIONAL)-->
          <el-col :span="24">
            <el-collapse v-model="activeNames">
              <el-collapse-item title="ADD GUEST (OPTIONAL)" name="1">
                <div>
                  <el-col :span="24" class="form-row" style="font-size:15px;">Guest 1</el-col>
                  <el-col :span="11" class="form-row">
                    <el-input placeholder="First Name" v-model="value10"/>
                  </el-col>
                  <el-col :span="10" class="form-row">
                    <el-input placeholder="Last Name" v-model="value11"/>
                  </el-col>
                  <el-col :span="3" class="form-row">
                    <el-link style="margin-top:10px;"><i class="el-icon-delete"></i>Remove</el-link>
                  </el-col>
                </div>
              </el-collapse-item>
            </el-collapse>
          </el-col>

          <el-col :span="24">
            <el-divider></el-divider>
          </el-col>

          <!--Additional Details and Preferences-->
          <el-col :span="24">
            <el-col :span="24" class="form-title">Additional Details and Preferences</el-col>
            <el-col :span="24">
              <el-input type="textarea" rows="7" placeholder="Please note your requests or special needs"
                        v-model="value12"/>
            </el-col>
          </el-col>
          <el-col :span="24">
            <el-divider></el-divider>
          </el-col>

          <!--Payment Information-->
          <el-col :span="24" class="form-title">Payment Information</el-col>
          <el-col :span="24">A$7,473.36 due November 6, 2022 (Hotel Local Time). Please provide a valid payment
            method.
          </el-col>
          <el-col :span="24" class="form-row img-icon">
            <img src="https://reservations.Annear.com/public/images/Visa.png"/>
            <img src="https://reservations.Annear.com/public/images/MasterCard.png"/>
            <img src="https://reservations.Annear.com/public/images/Amex.png"/>
            <img src="https://reservations.Annear.com/public/images/Discover.png"/>
          </el-col>
          <el-col :span="24" class="form-row">
            <el-input placeholder="Card Number" v-model="value13" style="width:370px;"/>
          </el-col>
          <el-col :span="24" class="form-row">
            <el-input placeholder="Expiration Date (MM/YY) " v-model="value14" style="width:230px;"/>
            <el-input placeholder="CVV * " v-model="value15" style="width:120px;margin-left:15px"/>
          </el-col>
          <el-col :span="24" class="form-row">
            <el-input placeholder="Name on Card *" v-model="value16" style="width:370px;"/>
          </el-col>
        </el-col>

        <!--Annear New York Policies-->
        <el-col :span="24" style="background:#f3eee7;line-height:20px;height:20px;"></el-col>
        <el-col :span="24" style="font-size:16px;padding-bottom: 20px;">
          <el-col :span="24" class="form-title">Annear New York Policies</el-col>
          <el-col class="form-row" style="display:flex;">
            <div>
              <div style="font-weight:600;">Check-in</div>
              <div>After 3:00 PM</div>
            </div>
            <div style="margin-left:60px;">
              <div style="font-weight:600;">Check-out</div>
              <div>Before 12:00 PM</div>
            </div>
          </el-col>
          <el-col>ROOM 1 FIFTH AVENUE JUNIOR SUITE, DELUXE TWIN</el-col>
          <el-col style="margin-top:10px;">Guarantee Policy</el-col>
          <el-col>One night room plus and tax deposit is required at time of booking.</el-col>
          <el-col style="margin-top:10px;">Cancel Policy</el-col>
          <el-col>Cancel before 18:00EST two days prior to arrival to avoid one night plus tax penalty.</el-col>
        </el-col>

        <!--Acknowledgement-->
        <el-col :span="24" style="background:#f3eee7;line-height:20px;height:20px;"></el-col>
        <el-col :span="24" style="font-size:16px; line-height:40px;">
          <el-col :span="24" class="form-title">Acknowledgement</el-col>
          <div>
            <el-checkbox v-model="checked1">Yes, I would like to receive newsletters and special offers by email.
            </el-checkbox>
          </div>
          <div>
            <el-checkbox v-model="checked2">* I agree with the Privacy Terms.</el-checkbox>
          </div>
          <div>
            <el-checkbox v-model="checked3">* I agree with the Booking Conditions.</el-checkbox>
          </div>
        </el-col>

        <!--Complete Booking-->
        <el-col :span="24" style="background:#f3eee7; height:80px;text-align: right;padding-top:20px;">
          <el-button type="info" style="background:black;height: 55px;">Complete Booking<i class="el-icon-lock"></i>
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
          <div @click="show1=!show1" style="cursor: pointer;">{{this.lastDays}} Night<i class="el-icon-arrow-down"></i></div>
          <div v-if="show1" style="justify-content: space-between;padding-left:30px;margin-top: 10px">
            <div style="display: flex" v-for="date in this.dates">
              <div>{{date.toDateString()}}</div>
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
            <div style="font-weight:600;text-align: right;">￥{{this.data.totalPrice}}</div>
          </div>
        </el-col>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import cookie from "js-cookie";
import {convertToDate} from "@/utils/utils";

export default {
  name: "home",
  data() {
    return {
      show1: false,
      show2: false,
      value1: '',
      value2: '8',
      value3: '',
      value4: '',
      value5: '',
      value6: '',
      value7: '',
      value8: '',
      value9: '',
      value10: '',
      value11: '',
      value12: '',
      value13: '',
      value14: '',
      value15: '',
      value16: '',
      filterText: '',
      activeNames: "0",
      showCheck: false,
      checked1: false,
      checked2: false,
      checked3: false,
      data: '',
      lastDays: 0,
      dates: []
    };
  },
  methods: {},
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
        for (let i = 0;i < this.lastDays;i++) {
          d +=  1000 * 60 * 60 *24;
          dates.push(new Date(d));
        }
        this.dates = dates;
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
