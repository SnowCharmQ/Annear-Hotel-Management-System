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
          <el-input placeholder="First Name" v-model="name" class="input-with-select">
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
              <div style="font-weight:600;">Average price: ${{ item.price }}</div>
              <el-link>Room details</el-link>
            </div>
          </el-col>
        </div>
      </el-collapse-item>
    </el-collapse>
    <el-collapse class="collection" accordion>
      <el-collapse-item title="My Orders" name="1">
        <div style="clear:both;">
          <el-col :span="24" v-for="item in orderList" :key="item.orderId" class="room-list">
            <img :src="item.img" style="margin-left:5px">
            <div class="room-right">
              <div style="font-size:10px">OrderId: {{ item.orderId }}</div>
              <div style="font-weight:600;">Hotel: {{ item.hotel }}</div>
              <div style="">Room: {{ item.room }}</div>
              <el-breadcrumb separator="|" style="margin:10px 0;">
                <el-breadcrumb-item>Start Time: {{ item.startTime }}</el-breadcrumb-item>
                <el-breadcrumb-item>Leave Time: {{ item.endTime }}</el-breadcrumb-item>
              </el-breadcrumb>
              <div>Order Status: {{ item.orderStatus }}</div>
              <el-breadcrumb separator="|" style="margin:10px 0;">
                <el-breadcrumb-item>Origin Money: ${{ item.originMoney }}</el-breadcrumb-item>
                <el-breadcrumb-item>After Discount: ${{ item.afterDiscount }}</el-breadcrumb-item>
              </el-breadcrumb>
              <el-button v-if="item.comments === ''" type="text" @click="show_comments_page">Given Comments</el-button>
              <div v-if="item.comments">Comments: {{ item.comments }}</div>
            </div>
          </el-col>
        </div>
      </el-collapse-item>


    </el-collapse>
    <el-dialog :visible.sync="dialogVisible" center width="50%">
      <div style="font-size: 18px;line-height: 28px;color: #333;">Give your comments</div>
      <div style="margin:10px 0">
        <span> Thanks for sharing your experience staying in Annear</span>
      </div>
      <div style="display: flex;flex-direction: column;align-items: center;justify-content: center;">
        <br>
        <el-form :inline="true">
          <el-select v-model="star" placeholder="Star" style="width: 80px">
            <el-option label="1" value="1"></el-option>
            <el-option label="2" value="2"></el-option>
            <el-option label="3" value="3"></el-option>
            <el-option label="4" value="4"></el-option>
            <el-option label="5" value="5"></el-option>
          </el-select>
          <br><br>
          <el-input v-model="comment"
                    style="width: 500px"
                    maxlength="50"
                    show-word-limit
                    placeholder="your comments">
          </el-input>
          <br><br>
          <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :http-request="uploadPicture"
              :file-list="fileList"
              :limit=1
              list-type="picture">
            <el-button size="small" type="primary">Upload Picture</el-button>
            <div slot="tip" class="el-upload__tip">please upload .one jpg/png file</div>
          </el-upload>
          <br>
          <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :http-request="uploadVideo"
              :limit=1
              :file-list="videoList">
            <el-button size="small" type="primary">Upload Video</el-button>
            <div slot="tip" class="el-upload__tip">please upload one .mp4 file</div>
          </el-upload>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submit_comments(star, comment)">Submit</el-button>
      </span>
    </el-dialog>
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
      collectionList: [
        // {
        //   name: 'Anneartaka',
        //   province: 'Guangdong',
        //   city: 'Shenzhen',
        //   district: "Luang Prabang Lao People's Dem Republic",
        //   starlevel: 5,
        //   telephone: 15566888888,
        //   price: '1980',
        //   img: require('../../assets/images/hotel/2.jpeg'),
        // }
      ],
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
    alter_info() {
      console.log(this.user_id)
      // //TODO: use the API to alter user info
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
          socialName: this.name
        })
      }).then(data => {
        console.log(data)
        this.$message({
          type: 'success',
          message: 'alter info done'
        });
      })
    }
    ,
    init() {
      console.log('hi1')
      //init userInfo
      this.$http({
        url: this.$http.adornUrl('/member/member/userinfo/queryUserInfoById'),
        method: 'get',
        params: this.$http.adornParams({
          token: cookie.get('token')
        })
      }).then(data => {
        let info = data.data.data
        console.log(info)
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
      console.log(videoUrl)
      this.videoList.push({name: video.name, url: videoUrl})
    });
  }
}
,
mounted()
{
  this.init()
}
}


</script>