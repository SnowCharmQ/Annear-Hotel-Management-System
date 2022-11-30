<template>
  <el-col :span="24" class="form-row">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" status-icon>
      <br><br>
      <el-form-item prop="phone" class="form-item" label="Phone Number" label-width="200px">
        <!--        <el-col class="form-title">Phone Number</el-col>-->
        <el-input class="form-input" v-model="dataForm.phone" auto-complete="off"></el-input>
      </el-form-item>
      <br><br>
      <el-form-item prop="code" class="form-item" label="Verification Code" label-width="200px">
        <!--        <el-col class="form-title">Password</el-col>-->
        <el-input class="code-input" v-model="dataForm.code" auto-complete="off"></el-input>
        <el-button class="send-btn" @click="sendMsg()" :disabled="isSend">{{ sendInfo }}</el-button>
      </el-form-item>
      <br>

      <br>
      <el-form-item>
        <el-button @click="dataFormSubmit()" class="submit-btn" :disabled="disabled">Login In
        </el-button>
      </el-form-item>
    </el-form>
  </el-col>
</template>

<script>
import cookie from 'js-cookie'
import {isMobile, isNumber} from "../../utils/validate";

export default {
  name: "LoginByVerificationCode",
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
    return {
      countdown: 60,
      disabled: false,
      isSend: false,
      sendInfo: "Send Verification Code",
      dataForm: {
        phone: '',
        code: ''
      },
      dataRule: {
        phone: [{validator: checkPhone, trigger: 'blur'}],
        code: [{validator: checkCode, trigger: 'blur'}]
      }
    }
  },
  methods: {
    enableBtn() {
      this.disabled = false;
    },
    sendMsg() {
      if (!this.isSend) {
        this.timeoutChange();
        this.$http({
          url: this.$http.adornUrl('/auth/message/login'),
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
    dataFormSubmit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl('/auth/login/code'),
            method: 'post',
            data: this.$http.adornData({
              'phone': this.dataForm.phone,
              'code': this.dataForm.code
            })
          }).then(data => {
            let resp = data.data;
            if (resp && resp.state === 200) {
              this.$message.success("Successfully Login In");
              cookie.set('token', resp.data.token, {expires: 1});
              let url = this.$route.query.redirect;
              if (url) {
                this.$router.push(url);
              } else {
                this.$router.push('home');
              }
            } else if (resp && resp.state === 10008) {
              let errors = resp.errors;
              let msg = [];
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
          }).catch(err => {
            this.$message.error("Network Error");
          })
          this.disabled = true;
          window.setTimeout(this.enableBtn, 3000);
        }
      })
    }
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
  width: 160px;
}

</style>