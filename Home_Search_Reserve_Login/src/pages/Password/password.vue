<template>
  <div className="page-main">
    <el-row className="card-search" :gutter="20" style="width:900px;margin:14px auto;">
      <el-col :span="22" class="login-block">
        <el-col className="login-title">Modify Your Password</el-col>
        <el-col className="privacy-info">We are committed to protecting the privacy and security of your personal
          information. More information
          about how we use and protect your data is provided in our Privacy Notice.
        </el-col>
        <el-col :span="24">
          <el-divider></el-divider>
        </el-col>
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" status-icon>
          <br><br>
          <el-form-item prop="phone" className="form-item" label="Phone Number" label-width="200px">
            <!--        <el-col class="form-title">Phone Number</el-col>-->
            <el-input className="form-input" v-model="dataForm.phone" auto-complete="off"></el-input>
          </el-form-item>
          <br><br>
          <el-form-item prop="code" className="form-item" label="Verification Code" label-width="200px">
            <!--        <el-col class="form-title">Password</el-col>-->
            <el-input className="code-input" v-model="dataForm.code" auto-complete="off"></el-input>
            <el-button className="send-btn" @click="sendMsg()" :disabled="isSend">{{ sendInfo }}</el-button>
          </el-form-item>
          <br><br>
          <el-form-item prop="password" className="form-item" label="New Password" label-width="200px">
            <!--        <el-col class="form-title">Password</el-col>-->
            <el-input className="form-input" v-model="dataForm.password" type="password" auto-complete="off"></el-input>
          </el-form-item>
          <br>

          <br>
          <el-form-item>
            <el-button @click="dataFormSubmit()" class="submit-btn" :disabled="disabled">Modify The Password
            </el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>

#curType {
  height: 300px;
}

.login-block {
  background: #fff;
  padding: 0 0;
  height: 600px;
  overflow: hidden;
}

.page-main {
  background: #f3eee7;
}

.privacy-info {
  width: 500px;
  margin-top: -10px;
  margin-left: 150px;
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
  margin-top: 100px;
}

.active-type {
  font-weight: 800;
  font-size: 16px;
}

.inactive-type {
  font-weight: 400;
  font-size: 16px;
}

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

.code-input {
  width: 200px;
}

.send-btn {
  width: 200px;
}

.remember-me {
  margin-left: 300px;
}

.submit-btn {
  margin-left: 290px;
  width: 200px;
}
</style>

<script>
import cookie from "js-cookie";
import {isMobile, isNumber} from "../../utils/validate";

export default {
  name: "password",
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
    let checkCode = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("The verification code can not be left empty"));
      } else if (!isNumber(value)) {
        callback(new Error("The verification code can only be a number"));
      } else if (value.length !== 6) {
        callback(new Error("The length of the verification code must be 6"));
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
      countdown: 60,
      disabled: false,
      isSend: false,
      sendInfo: "Send Verification Code",
      dataForm: {
        phone: '',
        code: '',
        password: ''
      },
      dataRule: {
        phone: [{validator: checkPhone, trigger: 'blur'}],
        code: [{validator: checkCode, trigger: 'blur'}],
        password: [{validator: checkPassword, trigger: 'blur'}]
      }
    }
  },
  methods: {
    dataFormSubmit() {
      this.$refs.dataForm.validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl('/auth/modifypwd'),
            method: 'post',
            data: this.$http.adornData({
              'phone': this.dataForm.phone,
              'code': this.dataForm.code,
              'password': this.dataForm.password
            })
          }).then(data => {
            let resp = data.data;
            if (resp && resp.state === 200) {
              this.$message("Successfully Modify the Password")
              cookie.remove('token');
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
              if (errors['code']) {
                msg.push(errors['code']);
              }
              this.$message.error({
                message: msg.join("! ")
              });
            } else if (resp && resp.state !== 200 && resp.state !== 10008) {
              this.$message.error(resp.message);
            } else {
              this.$message.error("Network Error");
            }
          })
        } else {
          this.$message.error("Invalid Input");
        }
      })
    },
    sendMsg() {
      if (!this.isSend) {
        this.$refs.dataForm.validateField('phone', (error) => {
          if (!error) {
            this.timeoutChange();
            this.$http({
              url: this.$http.adornUrl('/auth/message/pwd'),
              method: 'get',
              params: this.$http.adornParams({
                phone: this.dataForm.phone
              })
            }).then(data => {
              let resp = data.data;
              if (resp && resp.state === 200) {
                this.$message.success("Successfully Sent");
              } else if (resp && resp.state !== 200) {
                this.$message.error(resp.message);
              } else {
                this.$message.error("Network Error");
              }
            }).catch(err => {
              this.$message.error("Network Error");
            })
          } else {
            this.$message.error("Invalid Input");
          }
        })
      }
    },
    timeoutChange() {
      if (!this.isSend) {
        this.isSend = true;
        let countdownTimer = setInterval(() => {
          if (this.countdown > 0) {
            this.sendInfo = this.countdown + " seconds to send again"
            this.countdown--;
          } else {
            this.sendInfo = "Send Verification Code";
            this.isSend = false;
            this.countdown = 60;
            clearInterval(countdownTimer);
          }
        }, 1000);
      }
    },
  }
}


</script>