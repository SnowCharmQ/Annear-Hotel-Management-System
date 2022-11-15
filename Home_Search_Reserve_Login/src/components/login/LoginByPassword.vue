<template>
  <el-col :span="24" class="form-row">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" status-icon>
      <br><br>
      <el-form-item prop="phone" class="form-item" label="Phone Number" label-width="200px">
        <!--        <el-col class="form-title">Phone Number</el-col>-->
        <el-input class="form-input" v-model="dataForm.phone" auto-complete="off"></el-input>
      </el-form-item>
      <br><br>
      <el-form-item prop="password" class="form-item" label="Password" label-width="200px">
        <!--        <el-col class="form-title">Password</el-col>-->
        <el-input class="form-input" v-model="dataForm.password" type="password" auto-complete="off"></el-input>
      </el-form-item>
      <br><br>
      <el-checkbox class="remember-me" v-model="checked">Remember me</el-checkbox>
      <br><br>
      <a class="forget-pwd" href="">Forget Password?</a>
      <br><br>
      <el-form-item>
        <el-button @click="dataFormSubmit()" class="submit-btn" :disabled="disabled">Login In
        </el-button>
      </el-form-item>
    </el-form>
  </el-col>
</template>

<script>
import cookie from 'js-cookie'
import {isMobile} from "../../utils/validate";

const Base64 = require("js-base64").Base64;
export default {
  name: "LoginByPassword",
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
      } else {
        callback();
      }
    };
    return {
      checked: false,
      disabled: false,
      dataForm: {
        phone: '',
        password: '',
      },
      dataRule: {
        phone: [{validator: checkPhone, trigger: 'blur'}],
        password: [{validator: checkPassword, trigger: 'blur'}]
      }
    }
  },
  mounted() {
    let phone = localStorage.getItem("phone");
    if (phone) {
      this.dataForm.phone = phone;
      this.dataForm.password = localStorage.getItem("pwd");
      this.checked = true;
    }
  },
  methods: {
    enableBtn() {
      this.disabled = false;
    },
    dataFormSubmit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl('/auth/login/password'),
            method: 'post',
            data: this.$http.adornData({
              'phone': this.dataForm.phone,
              'password': this.dataForm.password
            })
          }).then(data => {
            let resp = data.data;
            if (resp && resp.state === 200) {
              cookie.set('token', resp.data.token, {expires: 1});
              let url = this.$route.query.redirect;
              if (url){
                this.$router.push(url);
              } else {
                this.$router.push('home');
              }
            } else if (resp && resp.state !== 200) {
              this.$message.error(resp.message);
            } else {
              this.$message.error("Network Error");
            }
          }).catch(err => {
            this.$message.error("Network Error");
          })
          this.disabled = true;
          window.setTimeout(this.enableBtn, 3000);
          if (this.checked) {
            let pwd = Base64.encode(this.dataForm.password);
            localStorage.setItem("phone", this.dataForm.phone);
            localStorage.setItem("pwd", pwd);
          } else {
            localStorage.removeItem("phone");
            localStorage.removeItem("pwd");
          }
        } else {
          this.$message.error("Invalid Input");
        }
      })
    },

  }
}
</script>

<style lang="scss" scoped>
.form-row {
  margin-left: 36px;
}

.form-item {
  width: 1000px;
  display: inline;
}

.form-input {
  width: 400px;
}

.remember-me {
  margin-left: 300px;
}

.forget-pwd {
  margin-left: 310px;
  text-decoration: underline;
  color: deepskyblue;
}

.submit-btn {
  margin-left: 290px;
  width: 160px;
}

</style>