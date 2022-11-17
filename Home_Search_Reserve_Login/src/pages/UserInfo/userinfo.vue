<template>
  <div class="page-main">
    <el-row class="card-search" :gutter="20" style="width:1000px;margin:14px auto;">
      <el-col :span="22" style="background:#fff; padding: 0 0;">
        <el-col class="login-title">User Information</el-col>
   
        <el-col :span="24">
          <el-divider></el-divider>
        </el-col>
                  <!--Contact Info-->
        <el-col :span="24" class="form-title">Contact Info</el-col>
        <el-col :span="6" class="form-row">
          <el-input placeholder="First Name" v-model="first_name" class="input-with-select">
            <el-select v-model="name_begin" slot="prepend" placeholder="Prefix">
              <el-option label="Mr." value="1"></el-option>
              <el-option label="Ms." value="2"></el-option>
              <!-- <el-option label="Mrs." value="3"></el-option>
              <el-option label="Madame" value="4"></el-option>
              <el-option label="Miss" value="5"></el-option>
              <el-option label="Lady" value="6"></el-option>
              <el-option label="Dr." value="7"></el-option>
              <el-option label="Professor" value="8"></el-option> -->
            </el-select>
          </el-input>
        </el-col>
        <el-col :span="5.5" class="form-row"><el-input placeholder="Last Name" v-model="last_name"/></el-col>
        <el-col :span="5.5" class="form-row"><el-input placeholder="UserName" v-model="user_name"/></el-col>
        <el-col :span="5" class="form-row">
            <el-date-picker
              v-model="birthday"
              type="date"
              placeholder="Birthday">
            </el-date-picker>
         </el-col>
        <el-col :span="7" class="form-row"><el-input placeholder="Phone" v-model="dataForm.phone"/></el-col>
        <el-col :span="7" class="form-row"><el-input placeholder="Email Address" v-model="email"/></el-col>
        <el-col :span="4" class="form-row"><el-input :placeholder="growth" disabled="true"/></el-col>
        <el-col :span="4" class="form-row"><el-input :placeholder="balance" disabled="true"/></el-col>
        <!-- <el-col :span="8" class="form-row" style="font-size:22px">Your growth: {{growth}}</el-col> -->
        <!-- <el-input
  placeholder="请输入内容"
  v-model="input1"
  :disabled="true">
</el-input> -->
        <!--Address-->
        <el-col :span="24" class="form-title">Address</el-col>
        <el-col :span="6" class="form-row  info-sel1">
          <el-select v-model="province" placeholder="Province">
            <el-option label="Hubei" value="Hubei"></el-option>
            <el-option label="Beijing" value="Beijing"></el-option>
            <el-option label="Tianjing" value="Tianjing"></el-option>
            <el-option label="Heibei" value="Heibei"></el-option>
            <el-option label="Guangdong" value="Guangdong"></el-option>
            <el-option label="Shanxi" value="Shanxi"></el-option>
            <el-option label="Fujian" value="Fujian"></el-option>
            <el-option label="Shanghai" value="Shanghai"></el-option>
          </el-select>
        </el-col>
        <el-col :span="6" class="form-row"><el-input placeholder="City" v-model="city"/></el-col>
        <el-col :span="11" class="form-row"><el-input placeholder="Address" v-model="address"/></el-col>
        <!-- <el-col :span="24" class="form-title">Your Password</el-col>
        <el-col :span="12" class="form-row">
          <el-input :rules="dataRule" placeholder="Password" v-model="dataForm.password" show-password  @keyup.enter.native="dataFormSubmit()"/>
        </el-col>
        <el-col :span="12" class="form-row">
          <el-input placeholder="Repeated" v-model="dataForm.re_password" show-password/>
        </el-col> -->
        <el-button @click="alter_info()" class="submit-btn">Alter Info</el-button>
      </el-col>
     
    </el-row>
    <el-collapse class="collection" v-model="activeName" accordion>
    <el-collapse-item title="My Collection" name="1">
      <div style="clear:both;">
					<el-col :span="24" v-for="item in collectionList" :key="item.name" class="room-list">
						<img :src="item.img" style="margin-left:5px">
						<div class="room-right">
							<div style="font-size:18px">{{item.name}}</div>
							<div style="font-weight:600;">{{item.province}}, {{item.city}}, {{item.district}}</div>
							<el-breadcrumb separator="|" style="margin:10px 0;">
								<el-breadcrumb-item>star level: {{item.starlevel}}</el-breadcrumb-item>
								<el-breadcrumb-item>telephone: {{item.telephone}}</el-breadcrumb-item>
							</el-breadcrumb>
							<div>A contemporary retreat for a restful night’s sleep</div>
							<div style="font-weight:600;">Average price: ${{item.price}}</div>
							<el-link>Room details</el-link>
							<!-- <el-divider></el-divider> -->
						</div>
          </el-col>
				</div>
    </el-collapse-item>
    </el-collapse>
    <el-collapse class="collection" v-model="activeName" accordion>
    <el-collapse-item title="My Orders" name="1">
      <div style="clear:both;">
					<el-col :span="24" v-for="item in orderList" :key="item.orderId" class="room-list">
						<img :src="item.img" style="margin-left:5px">
						<div class="room-right">
							<div style="font-size:10px">OrderId: {{item.orderId}}</div>
							<div style="font-weight:600;">Hotel: {{item.hotel}}</div>
							<div style="">Room: {{item.room}}</div>
              <el-breadcrumb separator="|" style="margin:10px 0;">
								<el-breadcrumb-item>Start Time: {{item.startTime}}</el-breadcrumb-item>
								<el-breadcrumb-item>Leave Time: {{item.endTime}}</el-breadcrumb-item>
							</el-breadcrumb>
							<div>Order Status: {{item.orderStatus}}</div>
              <el-breadcrumb separator="|" style="margin:10px 0;">
								<el-breadcrumb-item>Origin Money: ${{item.originMoney}}</el-breadcrumb-item>
								<el-breadcrumb-item>After Discount: ${{item.afterDiscount}}</el-breadcrumb-item>
							</el-breadcrumb>
							<el-link>Give Comments</el-link>
							<!-- <el-divider></el-divider> -->
						</div>
          </el-col>
				</div>
    </el-collapse-item>


    </el-collapse>
    
  </div>
</template>

<style lang="scss" scoped>
.page-main {
  background: #f3eee7;
}

.privacy-info {
  text-align: center;
  font-size: 16px;
  font-family: "Times New Roman", serif;
}

.login-title {
  font-size: 34px;
  line-height: 50px;
  margin-bottom: 20px;
  text-align: center;
  font-family: "Times New Roman", serif;
}

.login-type {
  margin-left: 270px;
  text-align: center;
  font-family: "Times New Roman", serif;
  display: inline-flex;
}

.active-type {
  font-weight: 800;
}

.inactive-type {
  font-weight: 400;
}

.form-title {
  font-size: 20px;
  margin: 15px 0;
  font-family: "Times New Roman", serif;
}

.form-row {
  margin-bottom: 15px;
  font-family: "Times New Roman", serif;
  span{
        color: red;
        margin-left: px;
  }
}
.submit-btn {
  margin-left: 370px;
  width: 160px;
}
.collection {
  // width:550px;margin:1px auto;
  width:920px;
  margin-top: 15px;
  margin-left:150px;
  // margin-right:200px;
  // center;
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
</style>

<script>
import axios from 'axios'

export default {
  name: "register",
  data() {
    let checkPhone = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("The phone number can not be left empty!"));
      } else if (!Number.isInteger(value)) {
        callback(new Error("The phone number is invalid!"));
      } else {
        callback();
      }
    };
    let checkPassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("The password can not be left empty"));
      } else {
        callback();
      }
    }
    return {
      first_name: '',
      name_begin: '',
      last_name: '',
      user_name: '',
      email: '',
      province: '',
      address: '',
      city: '',
      zip_code: '',
      birthday: '',
      growth: 'growth: ' + 0,
      balance: 'balance: ' + 0,
      dataForm: {
        phone: '',
        password: '',
        re_password: ''
      },  
      collectionList: [
        {
          name: 'Amantaka',
          province: 'Guangdong',
          city: 'Shenzhen',
          district: "Luang Prabang Lao People's Dem Republic",
          starlevel: 5,
          telephone: 15566888888,
          price: '1980',
          img: require('../../assets/images/hotel/2.jpeg'),
        }
      ],
      orderList: [
        {
          orderId: '0000098',
          hotel: 'Amantaka',
          room: 'Two bed rooms',
          orderStatus: 'Finish',
          startTime: '2022-10-1',
          endTime: '2022-10-3',
          originMoney: '330',
          afterDiscount: '270',
          img: require('../../assets/images/hotel/2.jpeg'),
        }
      ]
    }
  }, methods: {
      alter_info() {
      //TODO: use the API to alter user info
    }, info_init() {
      //TODO get the API to get the info and init the page 
        axios.get('http://localhost:11000/member/userinfo/queryUserInfoById', {
            params: {
              userId: 12012705
            }
        })
        .then(function (response) {
          console.log(response.data.data.phone);
          let result = response.data.data
          console.log(result.phone)
          console.log(this.email)
          // this.phone//= result.phone
        })
        .catch(function (error) {
          console.log(error);
        }); 
    }
  }
}


</script>