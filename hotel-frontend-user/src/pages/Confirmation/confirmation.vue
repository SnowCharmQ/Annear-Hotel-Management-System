<template>
    <div class="main">
        <!-- 左侧导航栏 -->

        <!-- 右侧内容栏 -->
        <div class="right">
            <!-- 顶部导航 -->
            <div class="nav">
                <div class="logo"><b>Grows Mall</b></div>

                <el-menu :default-active="activeIndex2" class="el-menu-demo" mode="horizontal" @select="handleSelect"
                    background-color="#f3eee7" text-color="grey" active-text-color="black">
                    <el-menu-item index="1" @click="goHome"><i class="el-icon-s-home"></i>Home</el-menu-item>
                    <el-menu-item index="2"><i class="el-icon-s-comment"></i>Message</el-menu-item>

                    <el-submenu index="3">
                        <template slot="title"><i class="el-icon-money"></i>grows</template>
                        <el-menu-item index="4-1">Your grow is {{ this.grow }}</el-menu-item>


                    </el-submenu>


                    <el-submenu index="4">
                        <template slot="title"><i class="el-icon-user-solid"></i>He SUI</template>
                        <el-menu-item index="4-1">Personal Center</el-menu-item>
                        <el-menu-item index="4-2">Modify Password</el-menu-item>
                        <el-menu-item index="4-3" @click="exit">Exit</el-menu-item>

                    </el-submenu>
                </el-menu>
            </div>
            <!-- 视图区域 -->
            <div class="content">

                <el-col :span="7" class="right-box" style="font-size:15px;">
                    <div style="font-size:20px;line-height:45px;">Your Stay at </div>
                    <el-col :span="12" style="margin-top:20px;">
                      <div style="font-weight:600;">Check-in</div>
                      <div>After 3:00 PM</div>
                    </el-col>
                    <el-col :span="12" style="margin-top:20px;">
                      <div style="font-weight:600;">Check-out</div>
                      <div>Before 12:00 PM</div>
                    </el-col>
                    <el-col :span="24">
                      <el-divider></el-divider>
                    </el-col>
            
                    <el-col :span="24">
                      <div>Room Number: 114</div>
                      <div> date1-date2
                      </div>
                      <div>Guests Max</div>
                    </el-col>
                    <el-col :span="24">Breakfast Provided</el-col>
                    <!-- <el-col :span="24" v-if="data.breakfast!==1">Breakfast Not Provided</el-col> -->
                    <el-col :span="24" style="display:flex;justify-content: space-between;padding-top: 20px;">
                      <div>Unit Price Per Night:</div>
                      <div style="font-weight:600;">￥100 TBD</div>
                    </el-col>
                    <el-col :span="24" style="margin-top:10px">
                      <div  style="cursor: pointer;"> Night<i
                          class="el-icon-arrow-down"></i></div>
                      <div  style="justify-content: space-between;padding-left:30px;margin-top: 10px">
                        <div style="display: flex">
                          <div></div>
                        </div>
                      </div>
                    </el-col>
                    <el-col :span="24" style="padding-top:20px;display: flex;">
                      <img src="../../assets/images/icon.jpg"/>
                      <div style="font-weight:600;margin-left:10px;margin-top:5px;">Alternate currency displays are for information
                        purposes only.
                      </div>
                    </el-col>
                    <el-col :span="24">
                      <el-divider></el-divider>
                    </el-col>
            
                    <el-col :span="24" style="display:flex;justify-content: space-between;">
                      <div style="font-weight:600;">Total:</div>
                      <div>
                        <div style="font-weight:600;text-align: right;">￥</div>
                      </div>
                    </el-col>
</el-col>            
            </div>
        </div>
    </div>
</template>

<script>
// 导入 vuex 的映射函数
import { mapState } from 'vuex';
export default {
    data() {
        return {
            grow: 1888,
            activeIndex2: "1",
            // 折叠左侧菜单
            isCollapse: false,
            tableData: [{
                name: '300-50 coupon',
                grow: '1000',
                discription: 'This coupon can be used to deduct $50 when the order amount exceeds $300',

            },
            {
                name: '400-150 coupon',
                grow: '1000',
                discription: 'This coupon can be used to deduct $50 when the order amount exceeds $300',

            },
            {
                name: '500-200 coupon',
                grow: '3000',
                discription: 'This coupon can be used to deduct $200 when the order amount exceeds $600',

            },
            {
                name: '20% off coupon',
                grow: '1800',
                discription: 'After using the coupon, you can reduce the amount of the order by 20%.',
            },
            {
                name: '30% off coupon',
                grow: '2000',
                discription: 'After using the coupon, you can reduce the amount of the order by 30%.',
            },
            {
                name: '40% off coupon',
                grow: '2500',
                discription: 'After using the coupon, you can reduce the amount of the order by 20%.',
            },


            {
                name: '50% off coupon',
                grow: '5000',
                discription: 'After using the coupon, you can reduce the amount of the order by 20%.',
            },


            ],
            num: '',
            count: 5,
            imageUrl: '',
            dialogFormVisible: false,
            form: {
                name: '',
                grow: '',
                number: '',
                index: ''
            },

        };
    },

    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },

        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        exit() {
            this.$router.push('home')
            console.log("EXIT")
        },
        goHome() {
            this.$router.push('home')
            console.log("HOME")
        },
        exchangeClass(index) {
            this.form.name = this.tableData[index].name
            this.form.grow = this.tableData[index].grow
            this.form.index = index

            this.dialogFormVisible = true


        },
        async resetForm(formName) {
            this.$refs[formName].resetFields();
            this.dialogFormVisible = false;
        },
        exchangeForm(formName) {
            this.$refs[formName].validate(async (valid) => {
                if (valid) {

                    this.tableData[this.form.index] = JSON.parse(JSON.stringify(this.form))
                    console.log(this.tableData[this.form.index])
                    // 为了更新表单
                    this.num = Math.random();
                    console.log("hello world!");

                } else {
                    console.log('error submit!!');
                    return false;
                }
                this.$refs[formName].resetFields();
                this.dialogFormVisible = false;
            });
        },


    }
}
</script>


<style scoped lang='scss'>
* {

    background-color: #f3eee7;
}

.main {
    width: 100vw;
    height: 100vh;
    display: flex;

    // 右侧样式
    .right {

        flex: 1;


        // 顶部导航样式
        .nav {
            height: 60px;
            display: flex;
            color: #3d424a;
            justify-content: space-between;
            background-color: #f3eee7;
            align-items: center;


            .icon {
                padding-left: 5px;

                i {
                    color: black;
                    font-size: 20px;

                }
            }

            .logo {
                font-family: 'nano';
                width: 10%;
                color: black;
                font-size: 24px;
                text-align: center;
                padding: 10px 2px;
                border: 1px solid black;

                margin: 10px auto;
            }

        }


        .content {
            padding: 8px;
            background-color: #f3eee7;

        }



    }
}



.el-button--primary:hover {

    background-color: #f7e1c3;
}



.exchange_form_button {
    margin-top: 0px;
    font-family: 'nano';
    height: 30px;
    font-size: 15px;
    line-height: 0.4;
    background-color: #f1e4a6;
    border: 1px solid rgb(238, 213, 184);
    text-align: center;
    border-color: #f1e4a6;
    color: black;
}
.exchange_form_button:hover{
    background-color: #fbc82f;
}

.exchangeButton {
    margin-top: 0px;
    font-family: 'nano';
    height: 30px;
    font-size: 15px;
    line-height: 0.4;
    background-color: #f0e7d9;
    border: 1px solid rgb(238, 213, 184);
    text-align: center;
    border-color: #f1e4a6;
    color: black;

}

.normal_button {
    font-family: 'nano';
    font-size: 15px;
    color: black;
    background-color: rgb(220, 248, 248);
}

.normal_button:hover {
    background-color: rgb(186, 245, 245);
}
</style>