<template>
  <div class="main">
    <!-- 背景画布 -->
    <canvas id="particle-canvas" width="2232" height="608"></canvas>
    <div class='content'>

      <div class="login">
        <h4 class="title">Hotel Management System</h4>
        <el-form size="mini" :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="50px"
                 class="demo-ruleForm">
          <el-form-item style="margin-left: 100px" label="Hotel Name" prop="loginId">
            <el-cascader
                v-model="ruleForm.loginId"
                :options="options"
            ></el-cascader>
          </el-form-item>
          <el-form-item style="margin-left: 80px" label="Password" prop="loginPwd">
            <el-input type="password" v-model="ruleForm.loginPwd" autocomplete="off"
            style="width: 200px;margin-left: 20px"></el-input>
          </el-form-item>
          <el-form-item class="item_button" style="margin-left: 140px;margin-top: 30px">
            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>


  </div>
</template>

<meta charset=​"UTF-8">​
<script>

let baseUrl = 'http://localhost:88';

import {strToMd5} from '@/utils/md5'
import {start} from '@/assets/js/login';

export default {
  // DOM 加载完毕
  mounted() {
    start()
  },
  data() {
    let validateLoginId = (rule, value, callback) => {
      if (value === '' || value === 'Choose The Hotel') {
        callback(new Error('Please Choose The Hotel'));
      } else {
        callback();
      }
    };
    //验证密码
    const validateLoginPwd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please Enter The Password'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };

    return {
      // 表单数据
      ruleForm: {
        loginId: 'Choose The Hotel',
        loginPwd: '',
      },
      rules: {
        // 验证登录名
        loginId: [
          {validator: validateLoginId, trigger: 'blur'}
        ],
        // 验证密码
        loginPwd: [
          {validator: validateLoginPwd, trigger: 'blur'}
        ]
      },
      options: []
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          // 对密码进行加密
          this.ruleForm.loginPwd = strToMd5(this.ruleForm.loginPwd);
          let {message, success} = await this.$get(baseUrl + '/', this.ruleForm);
          if (success) {
            // 服务器会返回令牌，保存身份信息，通过 token 去控制权限
            // 所以需要保存该token 信息，通常保存在浏览器的缓存空间当中
            // 浏览器的缓存空间有两种，local-storage 保存的数据会一直存在，session-storage 保存的数据随着浏览器的关闭而自动清空
            sessionStorage.setItem('token', success);
            // 将token 信息放到请求当中
            this.$setToken()
            // 要跳转到后台管理 push
            await this.$router.push('/layout')
          }
        } else {
          return false;
        }
      });
    },
  },
  created() {
    this.$get(baseUrl + '/room/room/hotel/getHotels').then(data => {
      if (data.state === 200) {
        data = data.data
        this.options = [];
        for (let l = 0; l < data.length;l++) {
          let hotel = data[l];
          this.options.push({'label':hotel.hotelName, 'value':hotel.hotelId})
        }
      }
    });
  }
}
</script>
<style lang="scss">
#particle-canvas {
  width: 100%;
  height: 100vh;
  background: linear-gradient(to bottom, rgb(10, 10, 50) 0%, rgb(60, 10, 60) 100%);
  vertical-align: middle;
}

.el-button--primary {
  height: 30px;
  line-height: 0.4;
  background-color: rgb(236, 133, 14);
  border: 1px solid rgb(236, 133, 14);
  border-radius: 1px;

  span {
    font-family: 'nano', serif;
    color: #fff;
    font-size: 14px;
    letter-spacing: 1px;
  }
}


.el-button {
  height: 30px;
  line-height: 0.4;
  position: center;

  span {
    font-family: 'nano', serif;
    font-size: 14px;
    letter-spacing: 1px;
  }
}

.el-form-item__label {
  color: white;
}


.main {
  width: 100vw;
  height: 100vh;
  position: relative;

  .content {
    top: 0;
    left: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    width: 100%;
    height: 100%;

    .login {
      font-family: nano, serif;

      width: 460px;
      height: 200px;
      border: 1px solid #ccc;
      padding: 20px;
      border-radius: 10px;

      .title {
        font-size: 18px;
        text-align: center;
        color: white;
        margin-bottom: 20px;
      }
    }
  }

}
</style>