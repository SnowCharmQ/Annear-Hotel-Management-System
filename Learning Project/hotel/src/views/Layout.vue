<template>
    <div class="main">
        <!-- 左侧导航栏 -->
        <div class="left" :style="{ width: (!isCollapse ? '200px' : 'auto') }">
            <div class="logo"><b>Annear</b></div>
            <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
                background-color="#f3eee7" text-color="black" active-text-color="#2775b6" :collapse="isCollapse"
                :collapse-transition="false" router>
                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-user"></i>
                        <span>Account</span>
                    </template>
                    <el-menu-item-group>

                        <el-menu-item index="/layout/customer">Customer</el-menu-item>
                        <el-menu-item index="/layout/admin">Administrator</el-menu-item>
                    </el-menu-item-group>

                </el-submenu>
                <el-submenu index="2">
                    <template slot="title">
                        <i class="el-icon-office-building"></i>
                        <span>Room</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="/layout/type">Type</el-menu-item>
                        <el-menu-item index="2-2">Room</el-menu-item>
                    </el-menu-item-group>

                </el-submenu>
                <el-submenu index="3">
                    <template slot="title">
                        <i class="el-icon-s-data"></i>
                        <span>Analysis</span>
                    </template>
                    <el-menu-item-group>
                        <el-submenu index="3-1">
                            <template slot="title">Room Order Quantity</template>
                            <el-menu-item index="3-1-1">Day</el-menu-item>
                            <el-menu-item index="3-1-2">Month</el-menu-item>
                            <el-menu-item index="3-1-3">Year</el-menu-item>
                            <el-menu-item index="3-1-4">Type of Room</el-menu-item>
                        </el-submenu>
                        <el-submenu index="4-1">
                            <template slot="title">Turnover</template>
                            <el-menu-item index="4-1-1">Day</el-menu-item>
                            <el-menu-item index="4-1-2">Month</el-menu-item>
                            <el-menu-item index="4-1-3">Year</el-menu-item>
                            <el-menu-item index="4-1-4">Type of Room</el-menu-item>
                        </el-submenu>

                    </el-menu-item-group>
                </el-submenu>
            </el-menu>
        </div>
        <!-- 右侧内容栏 -->
        <div class="right">
            <!-- 顶部导航 -->
            <div class="nav">
                <div class="icon" @click="isCollapse = !isCollapse">
                    <i :class="!isCollapse ? 'el-icon-s-fold' : 'el-icon-s-unfold'"></i>
                </div>
                <el-menu :default-active="activeIndex2" class="el-menu-demo" mode="horizontal" @select="handleSelect"
                    background-color="#f3eee7" text-color="grey" active-text-color="black">
                    <el-menu-item index="1"><i class="el-icon-s-home"></i>Home</el-menu-item>
                    <el-menu-item index="1"><i class="el-icon-s-comment"></i>Message</el-menu-item>
                    <el-menu-item index="1"><i class="el-icon-message"></i>Email</el-menu-item>



                    <el-submenu index="4">
                        <template slot="title"><i class="el-icon-user-solid"></i>Administrator</template>
                        <el-menu-item index="4-1">Personal Center</el-menu-item>
                        <el-menu-item index="4-2">Modify Password</el-menu-item>
                        <el-menu-item index="4-3" @click="exit">Exit</el-menu-item>

                    </el-submenu>
                </el-menu>
            </div>
            <!-- 视图区域 -->
            <div class="content">

                <!-- 路由视图 -->
                <router-view></router-view>
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
            activeIndex2: "1",
            // 折叠左侧菜单
            isCollapse: false
        };
    },

    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        exit() {
            this.$router.push('/')
            console.log("EXIT")
        }

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

    // 左侧样式
    .left {
        width: 200px;
        background-color: #f3eee7;


        .logo {
            font-family: 'nano';
            width: 90%;
            color: black;
            font-size: 24px;
            text-align: center;
            padding: 10px 2px;
            border: 1px solid black;

            margin: 10px auto;
        }

        // 图标颜色
        .el-submenu__title i {
            color: #3d424a;

        }
    }

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
        }


        .content {
            padding: 8px;
            background-color: #f3eee7;
            
        }

        // 中间内容样式

    }
}
</style>