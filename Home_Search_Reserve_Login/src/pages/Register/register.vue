<template>
  <div class="page-main">
    <el-row class="card-search" :gutter="20" style="width:600px;margin:14px auto;">
      <el-col :span="22" :rules="dataRule" ref="dataForm" style="background:#fff; padding: 0 0;">     
       <el-col class="login-title">Register</el-col>
        <el-col class="privacy-info">We are committed to protecting the privacy and security of your personal
          information. More information
          about how we use and protect your data is provided in our Privacy Notice.
        </el-col> 
        <el-col :span="24">
          <el-divider></el-divider>
        </el-col>
                  <!--Contact Info-->
        <el-col :span="24" class="form-title">Contact Info</el-col>
        <el-col :span="8" class="form-row">
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
        <el-col :span="6" class="form-row"><el-input placeholder="Last Name" v-model="last_name"/></el-col>
        <el-col :span="10" class="form-row"><el-input placeholder="UserName" v-model="user_name"/></el-col>
        <el-col :span="12" class="form-row"><el-input placeholder="Phone" v-model="dataForm.phone"/></el-col>
        <el-col :span="12" class="form-row"><el-input placeholder="Email Address" v-model="email"/></el-col>
        <el-col :span="10" class="form-row">
            <el-date-picker
              v-model="birthday"
              type="date"
              placeholder="Birthday">
            </el-date-picker>
        </el-col>
        <!--Address-->
        <el-col :span="24" class="form-title">Address</el-col>
        <el-col :span="10" class="form-row  info-sel1">
          <el-select span="10" v-model="province" placeholder="Province">
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
        <el-col :span="14" class="form-row"><el-input placeholder="City" v-model="city"/></el-col>
        <el-col :span="24" class="form-row"><el-input placeholder="Detailed Address" v-model="address"/></el-col>
        <!-- <el-col :span="14" class="form-row"><el-input placeholder="Zip / Postal Code " v-model="zip_code"/></el-col> -->
        <el-col :span="24" class="form-title">Your Password</el-col>
        <el-col :span="12"  class="form-row">
          <el-input placeholder="Password" v-model="dataForm.password" show-password/>
        </el-col>
        <el-col :span="12" class="form-row">
          <el-input placeholder="Repeated" v-model="dataForm.re_password" show-password/>
        </el-col>
        <el-button @click="register_submit()" class="submit-btn">Register</el-button>
      </el-col>
     
    </el-row>
    
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
  margin-left: 200px;
  width: 160px;
}

</style>

<script>
export default {
  name: "register",
  data() {
    let checkPhone = (rule, value, callback) => {
      console.log(rule, value)
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
      job: '',
      dataForm: {
        phone: '',
        password: '',
        re_password: ''
      },  
      dataRule: {
        phone: [{validator: checkPhone, trigger: 'blur'}],
        password: [{validator: checkPassword, trigger: 'blur'}]
      }
      
    }
  }, methods: {
      register_submit() {
        let gender = parseInt(this.name_begin)

        let day = new Date(this.birthday)
        console.log(day);
        console.log(gender)

        console.log(this.birthday);
        if(this.dataForm.password != this.dataForm.re_password) {
          alert('Password not the same')
          return
        }
        axios.get('http://localhost:11000/member/userinfo/register_user', {
            params: {
              name: this.first_name + ' ' + this.last_name,
              password: this.dataForm.password,
              phone: this.dataForm.phone,
              email: this.email,
              gender: gender,
              province: this.province,
              city: this.city,
              detailedAdr: this.address,
              userName: this.user_name,
              birthday: day.getTime()
            }
        })
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });
    }
  }
}


</script>