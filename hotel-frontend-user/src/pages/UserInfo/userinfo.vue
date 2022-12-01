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
          <el-input placeholder="Name" v-model="name" class="input-with-select">
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
        <el-col :span="5.5" class="form-row">
          <el-input placeholder="UserName" v-model="user_name"/>
        </el-col>
        <el-col :span="5" class="form-row">
          <el-date-picker
              v-model="birthday"
              type="date"
              placeholder="Birthday">
          </el-date-picker>
        </el-col>
        <el-col :span="7" class="form-row">
          <el-input placeholder="Phone" v-model="dataForm.phone"/>
        </el-col>
        <el-col :span="7" class="form-row">
          <el-input placeholder="Email Address" v-model="email"/>
        </el-col>
        <el-col :span="4" class="form-row">
          <el-input :placeholder="growth" disabled/>
        </el-col>
        <el-col :span="4" class="form-row">
          <el-input :placeholder="balance" disabled/>
        </el-col>
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
        <el-col :span="6" class="form-row">
          <el-input placeholder="City" v-model="city"/>
        </el-col>
        <el-col :span="11" class="form-row">
          <el-input placeholder="Address" v-model="address"/>
        </el-col>
        <el-button @click="alter_info()" class="submit-btn">Alter Info</el-button>
        <el-button @click="view_orders()" class="submit-btn">View Orders</el-button>
      </el-col>

    </el-row>
    <el-collapse class="collection" accordion>
      <el-collapse-item title="My Collection" name="1">
        <div style="clear:both;">
          <el-col :span="24" v-for="item in collectionList" :key="item.name" class="room-list">
            <img :src="item.img" style="margin-left:5px">
            <div class="room-right">
              <div style="font-size:18px">{{ item.name }}</div>
              <div style="font-weight:600;">{{ item.province }}, {{ item.city }}, {{ item.district }}</div>
              <el-breadcrumb separator="|" style="margin:10px 0;">
                <el-breadcrumb-item>star level: {{ item.starlevel }}</el-breadcrumb-item>
                <el-breadcrumb-item>telephone: {{ item.telephone }}</el-breadcrumb-item>
              </el-breadcrumb>
              <div>A contemporary retreat for a restful night’s sleep</div>
              <el-link @click="toHotel(item.hotelId)">Hotel details</el-link>
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

  span {
    color: red;
  }
}

.submit-btn {
  margin-left: 370px;
  width: 160px;
}

.collection {
  // width:550px;margin:1px auto;
  width: 920px;
  margin-top: 15px;
  margin-left: 150px;
  // margin-right:200px;
  // center;
}

.room-list {
  display: flex;
  border-bottom: 15px solid #f3eee7;
  padding: 15px 0;
}

.room-list img {
  width: 250px;
  height: 175px;
}

.room-right {
  padding-left: 20px;
  line-height: 30px;
  color: #444;
}
</style>

<script>
import axios from 'axios'
import cookie from "js-cookie";

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
      user_id: '',
      name_begin: '',
      name: '',
      user_name: '',
      email: '',
      province: '',
      address: '',
      city: '',
      birthday: '',
      growth: 'growth: ' + 0,
      balance: 'balance: ' + 0,
      dataForm: {
        phone: '',
        password: '',
        re_password: ''
      },
      collectionList: [],
      orderList: [
        {
          orderId: '0000098',
          hotel: 'Anneartaka',
          room: 'Two bed rooms',
          orderStatus: 'Finish',
          startTime: '2022-10-1',
          endTime: '2022-10-3',
          originMoney: '330',
          afterDiscount: '270',
          img: require('../../assets/images/hotel/2.jpeg'),
          comments: ''
        }
      ],
      dialogVisible: false,
      star: '',
      comment: '',
      fileList: [],
      videoList: [],
      tempUrl: '',
      selectedOrderId: ''

    }
  }, methods: {
    view_orders() {
      this.$router.push('orderList');
    },
    alter_info() {
      this.$http({
        url: this.$http.adornUrl('/member/member/userinfo/alterUserInfo'),
        method: 'get',
        params: this.$http.adornParams({
          toEditId: cookie.get('token'),
          phone: this.dataForm.phone,
          email: this.email,
          gender: 0,
          birthday: new Date(this.birthday).getTime(),
          province: this.province,
          city: this.city,
          detailAddress: this.address,
          socialName: this.user_name,
          name: this.name
        })
      }).then(data => {
        if (data.data.state === 200) {
          this.$message({
            type: 'success',
            message: 'alter info done'
          });
        } else {
          this.$message.error(data.data.message);
        }
      })
    }
    ,
    init() {
      //init userInfo
      this.$http({
        url: this.$http.adornUrl('/member/member/userinfo/queryUserInfoById'),
        method: 'get',
        params: this.$http.adornParams({
          token: cookie.get('token')
        })
      }).then(data => {
        let info = data.data.data
        this.user_id = info.userId
        this.name = info.socialName
        this.name_begin = info.gender === 0 ? 'Mr.' : 'Mis.'
        this.user_name = info.username
        this.email = info.email
        this.province = info.province
        this.address = info.detailAddress
        this.city = info.city
        this.birthday = info.birthday
        this.growth = 'growth: ' + info.growth
        this.balance = 'balance: ' + info.balance
        this.dataForm.phone = info.phone

        this.$http({
          url: this.$http.adornUrl('/member/member/collecthotel/collectedList'),
          method: 'get',
          params: this.$http.adornParams({
            userId: cookie.get('token')
          })
        }).then(data => {
          let resp = data.data.data
          for (let i = 0; i < resp.length; i++) {
            let item = resp[i]
            let p = {
              hotelId: item.hotelId,
              name: item.hotelName,
              province: item.province,
              city: item.city,
              district: item.district,
              starlevel: item.starLevel,
              telephone: item.telephone,
              price: item.averagePrice,
              img: item.picturePath
            }
            this.collectionList.push(p)
          }
        })
      })
      //TODO: init user order
    }
    ,
    show_comments_page() {
      this.dialogVisible = true
    }
    ,
    submit_comments(star, comment) {
      this.dialogVisible = false
      this.$message({
        type: 'success',
        message: 'upload comments done'
      });
    },
    toHotel(hotelId) {
      this.$router.push("reserve?hotel=" + hotelId);
    }
    ,
    handleRemove(file, fileList) {
      console.log(file, fileList);
    }
    ,
    handlePreview(file) {
      console.log(file);
    }
    ,
    uploadPicture(file) {
      let picture = file.file
      let formData = new FormData();
      formData.append('file', picture)
      formData.append('orderId', this.selectedOrderId)
      let url = this.$http.adornUrl('/auth/uploadPicture')

      axios.post(url, formData, {
        headers: {'Content-Type': 'multipart/form-data'}
      }).then(data => {
        let pictureUrl = data.data.data
        console.log(pictureUrl)
        this.fileList.push({name: picture.name, url: pictureUrl})
      });
    }
    ,
    uploadVideo(file) {
      let video = file.file
      let formData = new FormData();
      formData.append('file', video)
      formData.append('orderId', this.selectedOrderId)
      let url = this.$http.adornUrl('/auth/uploadVideo')

      axios.post(url, formData, {
        headers: {'Content-Type': 'multipart/form-data'}
      }).then(data => {
        let videoUrl = data.data.data
        this.videoList.push({name: video.name, url: videoUrl})
      });
    }
  }
  ,
  mounted() {
    this.init()
  }
}


</script>