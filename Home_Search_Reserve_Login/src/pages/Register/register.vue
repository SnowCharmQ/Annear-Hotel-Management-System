<template>
  <div class="page-main">
    <el-row class="card-search" :gutter="20" style="width:600px;margin:14px auto;">
      <el-col :span="22" ref="dataForm" style="background:#fff; padding: 0 0;">
        <el-col class="login-title">Register</el-col>
        <el-col class="privacy-info">We are committed to protecting the privacy and security of your personal
          information. More information
          about how we use and protect your data is provided in our Privacy Notice.
        </el-col>
        <el-col :span="24">
          <el-divider></el-divider>
        </el-col>
        <!--Personal Info-->
        <el-col :span="24" class="form-title">Personal Information</el-col>
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" status-icon>
          <el-form-item prop="firstName">
            <el-input placeholder="First Name" v-model="dataForm.firstName" class="input-with-select">
              <el-select v-model="dataForm.nameBegin" slot="prepend" placeholder="Prefix">
                <el-option label="Mr." value="1"></el-option>
                <el-option label="Ms." value="2"></el-option>
              </el-select>
            </el-input>
          </el-form-item>
          <el-form-item :span="6" class="form-row" prop="lastName">
            <el-input placeholder="Last Name" v-model="dataForm.lastName"/>
          </el-form-item>
          <el-form-item :span="10" class="form-row" prop="username">
            <el-input placeholder="Username" v-model="dataForm.username"/>
          </el-form-item>
          <el-form-item :span="12" class="form-row" prop="phone">
            <el-input placeholder="Phone" v-model="dataForm.phone"/>
          </el-form-item>
          <el-form-item :span="12" class="form-row" prop="email">
            <el-input placeholder="Email Address" v-model="dataForm.email"/>
          </el-form-item>
          <el-form-item :span="10" class="form-row" prop="birthday">
            <el-date-picker
                v-model="dataForm.birthday"
                type="date"
                placeholder="Birthday">
            </el-date-picker>
          </el-form-item>
          <!--Address-->
          <el-col :span="24" class="form-title">Address</el-col>
          <el-form-item :span="10" class="form-row  info-sel1" prop="province">
            <el-select span="10" v-model="dataForm.province" placeholder="Province">
              <el-option label="Hubei" value="Hubei"></el-option>
              <el-option label="Beijing" value="Beijing"></el-option>
              <el-option label="Tianjing" value="Tianjing"></el-option>
              <el-option label="Hebei" value="Heibei"></el-option>
              <el-option label="Guangdong" value="Guangdong"></el-option>
              <el-option label="Shanxi" value="Shanxi"></el-option>
              <el-option label="Fujian" value="Fujian"></el-option>
              <el-option label="Shanghai" value="Shanghai"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item :span="14" class="form-row" prop="city">
            <el-input placeholder="City" v-model="dataForm.city"/>
          </el-form-item>
          <el-form-item :span="24" class="form-row" prop="address">
            <el-input placeholder="Detailed Address" v-model="dataForm.address"/>
          </el-form-item>
          <!-- <el-col :span="14" class="form-row"><el-input placeholder="Zip / Postal Code " v-model="zip_code"/></el-col> -->
          <el-col :span="24" class="form-title">Your Password</el-col>
          <el-form-item :span="12" class="form-row" prop="password">
            <el-input placeholder="Password" v-model="dataForm.password" show-password/>
          </el-form-item>
          <el-form-item :span="12" class="form-row" prop="rePassword">
            <el-input placeholder="Repeated" v-model="dataForm.rePassword" show-password/>
          </el-form-item>
        </el-form>
        <el-button @click="dataFormSubmit()" class="submit-btn">Register</el-button>
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

  span {
    color: red;
  }
}

.submit-btn {
  margin-left: 200px;
  width: 160px;
}

</style>

<script>
import {isEmail, isMobile} from "../../utils/validate";

export default {
  name: "register",
  data() {
    let checkPhone = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("The phone number can not be left empty"));
      } else if (!isMobile(value)) {
        callback(new Error("The phone number is invalid"));
      } else {
        callback();
      }
    };
    let checkPassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("The password can not be left empty"));
      } else if (value.length < 6 || value.length > 18) {
        callback(new Error("The length of the password must between 6 and 18"));
      } else {
        callback();
      }
    };
    let checkEmail = (rule, value, callback) => {
      if (value === "" || isEmail(value)) {
        callback();
      } else {
        callback(new Error("The email address is invalid"));
      }
    };
    let checkRePassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("The password can not be left empty"));
      } else if (value !== this.dataForm.password) {
        callback(new Error("The two passwords are different"));
      } else {
        callback();
      }
    };
    return {
      dataForm: {
        firstName: '',
        nameBegin: 0,
        lastName: '',
        username: '',
        email: '',
        province: '',
        address: '',
        city: '',
        birthday: '',
        phone: '',
        password: '',
        rePassword: ''
      },
      dataRule: {
        phone: [{validator: checkPhone, trigger: 'blur'}],
        password: [{validator: checkPassword, trigger: 'blur'}],
        rePassword: [{validator: checkRePassword, trigger: 'blur'}],
        email: [{validator: checkEmail, trigger: 'blur'}]
      }
    }
  }, methods: {
    dataFormSubmit() {
      this.$refs.dataForm.$children[4].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl('/auth/register'),
            method: 'post',
            data: this.$http.adornData({
              'username': this.dataForm.firstName + this.dataForm.lastName,
              'password': this.dataForm.password,
              'phone': this.dataForm.phone,
              'email': this.dataForm.email,
              'gender': parseInt(this.dataForm.nameBegin),
              'province': this.dataForm.province,
              'city': this.dataForm.city,
              'address': this.dataForm.address,
              'socialName': this.dataForm.username,
              'birthday': new Date(this.dataForm.birthday),
            })
          }).then(data => {
            let resp = data.data;
            if (resp && resp.state === 200) {
              this.$router.push("login");
            } else if (resp && resp.state === 10008) {
              let errors = resp.errors;
              let msg = [];
              if (errors['password']) {
                msg.push(errors['password']);
              }
              if (errors['phone']) {
                msg.push(errors['phone']);
              }
              if (errors['email']) {
                msg.push(errors['email']);
              }
              this.$message.error({
                message: msg.join("! ")
              });
            }
          })
        } else {
          this.$message.error("Invalid Input");
        }
      })
    }
  }
}


</script>