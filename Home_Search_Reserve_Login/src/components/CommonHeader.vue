<template>
  <header>
    <div class="l-content">
      <i class="el-icon-s-unfold" @click="drawer=true"></i>
      <span class="titles">Menu</span>
    </div>

    <!--------to be changed--------->
    <div><img src="https://www.aman.com/themes/custom/aman/logo.svg" class="logo"/></div>

    <!--homepage右上角的两个按钮-->
    <div class="r-content">
      <el-button type="text" @click="loginDialog=true">Login</el-button>
      <el-button @click="toReserve">Reserve</el-button>
    </div>

    <!-- 登录弹窗 -->
    <el-dialog title="Login" :visible.sync="loginDialog" width="30%">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="account"><el-input v-model="form.account"></el-input></el-form-item>
        <el-form-item label="password"><el-input v-model="form.password" type="password"></el-input></el-form-item>
        <el-form-item><el-button type="primary" @click="loginHandle" style="width:100%;">Login</el-button></el-form-item>
      </el-form>
    </el-dialog>

    <!-- 菜单抽屉 -->
    <el-drawer
        title="Menu"
        :visible.sync="drawer"
        direction="ltr">
      <div style="padding:0 15px">
        <!-- 第一级菜单 -->
        <el-tabs tab-position="left" @tab-click="menuClick">
          <el-tab-pane label="" v-for="(item,index) in menu" :key="index" :name="item.url+'-'+index">
            <template slot="label">{{ item.name }}<i class="el-icon-arrow-right right-icon" v-if="item.children.length > 0"></i></template>
            <!-- 第二级菜单 -->
            <div>
              <el-tabs tab-position="left" @tab-click="menuClick">
                <el-tab-pane v-for="(child,idx) in item.children" :key="idx" :name="child.url+'-'+idx">
                  <template slot="label">{{ child.name }}<i class="el-icon-arrow-right right-icon" v-if="child.children.length > 0"></i></template>
                </el-tab-pane>
              </el-tabs>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-drawer>

  </header>
</template>

<script>
export default {
  name: 'CommonHeader',
  data() {
    return {
      loginDialog: false,
      drawer: false,
      form: {},
      menu: [
        {name: 'Home', url: '/', children: []},
        {
          name: 'Destinations', url: '',
          children: [
            {name: 'Aman Journeys', url: '', children: []},
            {name: 'Celebrations', url: '', children: []},
            {name: 'Active Adventure', url: '', children: []},
            {name: 'Wellness', url: '', children: []}
          ]
        },
        {name: 'Hotels & Resorts', url: '', children: []},
        {name: 'Villas', url: '', children: []},
        {name: 'Residences', url: '', children: []},
        {
          name: 'Yachts', url: '',
          children: [
            {name: 'Aman Journeys', url: '', children: []},
            {name: 'Celebrations', url: '', children: []},
            {name: 'Culture & Conservation', url: '', children: []},
            {name: 'Active Adventure', url: '', children: []}
          ]
        },
        {name: 'Private Travel ', url: '', children: []}
      ]
    }
  },
  methods: {
    toReserve() {
      this.$router.push('Search')
    },
    // 登录
    loginHandle() {
      this.$message.info('Order')
    },
    //点击菜单
    menuClick(curMenu) {
      console.log('当前路由：' + curMenu.name)
      this.$router.push(curMenu.name.split('-')[0])
      this.drawer = false
    }
  }
}
</script>

<style lang="scss" scoped>
header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;

  .l-content {
    .titles {
      color: #333;
      display: inline-block;
      margin-left: 20px;
    }

    .el-icon-s-unfold {
      font-size: 22px;
      cursor: pointer;
    }

    .el-icon-s-unfold:hover {
      opacity: 0.8;
    }
  }

  .logo {
    width: 120px;
  }

  ::v-deep.el-tabs--left .el-tabs__item.is-left {
    font-size: 16px;
    color: #888;
    text-align: left;
  }

  ::v-deep.right-icon {
    float: right;
    vertical-align: middle;
    margin-top: 12px;
  }

  ::v-deep.el-tabs--left .el-tabs__item.is-left:hover {
    color: #333;
  }

  ::v-deep.el-drawer {
    width: 700px !important;
  }

  .r-content {
    .el-dropdown-link {
      img {
        width: 40px;
        height: 40px;
        border-radius: 50%;
      }
    }
  }
}
</style>
