<template>
  <el-col :span="24" class="form-row">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" status-icon>
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
      <el-checkbox class="remember-me">Remember me</el-checkbox>
      <br>
      <br>
      <el-form-item>
        <el-button @click="dataFormSubmit()" class="submit-btn">Login In</el-button>
      </el-form-item>
    </el-form>
  </el-col>
</template>

<script>
export default {
  name: "LoginByPassword",
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
      dataForm: {
        phone: '',
        password: '',
      },
      dataRule: {
        phone: [{validator: checkPhone, trigger: 'blur'}],
        password: [{validator: checkPassword, trigger: 'blur'}]
      }
    }
  }, methods: {
    dataFormSubmit() {
      // this.$refs['dataForm'].validate((valid) => {
      //   if (valid) {
      //
      //   }
      // })
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

.remember-me {
  margin-left: 300px;
}

.submit-btn {
  margin-left: 290px;
  width: 160px;
}

//.form-title {
//  font-size: 18px;
//  font-family: "Times New Roman", serif;
//}
</style>