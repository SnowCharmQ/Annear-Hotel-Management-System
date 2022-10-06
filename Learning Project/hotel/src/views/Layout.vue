<template>
    <div class="main">
        <!-- 左侧导航栏 -->
        <div class="left">
            <div class="logo">酒店后台管理系统</div>
            <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
                background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-user"></i>
                        <span>账号管理</span>
                    </template>
                    <el-menu-item-group>

                        <el-menu-item index="1-1">角色管理</el-menu-item>
                        <el-menu-item index="1-2">账号管理</el-menu-item>
                    </el-menu-item-group>

                </el-submenu>
                <el-submenu index="2">
                    <template slot="title">
                        <i class="el-icon-office-building"></i>
                        <span>客房管理</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="2-1">类型管理</el-menu-item>
                        <el-menu-item index="2-2">客房管理</el-menu-item>
                    </el-menu-item-group>

                </el-submenu>
                <el-submenu index="3">
                    <template slot="title">
                        <i class="el-icon-user-solid"></i>
                        <span>客户管理</span>
                    </template>
                    <el-menu-item-group>

                        <el-menu-item index="3-1">客户管理</el-menu-item>
                    </el-menu-item-group>

                </el-submenu>
                <el-submenu index="4">
                    <template slot="title">
                        <i class="el-icon-setting"></i>
                        <span>权限管理</span>
                    </template>
                    <el-menu-item-group>

                        <el-menu-item index="4-1">权限管理</el-menu-item>
                    </el-menu-item-group>

                </el-submenu>
            </el-menu>
        </div>
        <!-- 右侧内容栏 -->
        <div class="right">
            <!-- 顶部导航 -->
            <div class="nav">
                <div class="icon"></div>
                <el-menu :default-active="activeIndex2" class="el-menu-demo" mode="horizontal" @select="handleSelect"
                    background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
                    <el-menu-item index="1"><i class="el-icon-s-home"></i>Home</el-menu-item>
                    <el-menu-item index="1"><i class="el-icon-s-comment"></i>Message</el-menu-item>
                    <el-menu-item index="1"><i class="el-icon-message"></i>Email</el-menu-item>

                    <el-submenu index="4">
                        <template slot="title">
                            <i class="
                            el-icon-help"></i>Theme</template>
                        <el-menu-item index="4-1" v-for="(item, index) in theme" :key="index"> {{item.name}}</el-menu-item>
                        <!-- <el-menu-item index="4-2">灰色主题</el-menu-item>
                        <el-menu-item index="4-3">红色主题</el-menu-item>
                        <el-menu-item index="4-4">黑色主题</el-menu-item>
                        <el-menu-item index="4-5">绿色主题</el-menu-item> -->
                    </el-submenu>

                    <el-submenu index="5">
                        <template slot="title"><i class="el-icon-user-solid"></i>管理员</template>
                        <el-menu-item index="5-1">个人中心</el-menu-item>
                        <el-menu-item index="5-2">修改密码</el-menu-item>
                        <el-menu-item index="5-3">退出系统</el-menu-item>

                    </el-submenu>
                </el-menu>
            </div>
            <!-- 视图区域 -->
            <div class="content">2
            </div>
        </div>
    </div>
</template>

<script>
// 导入 vuex 的映射函数
import { mapState } from 'vuex';
export default {
    data(){
        return {
            activeIndex2: "1",
        };
    },
    computed:{
        ...mapState('theme', ['theme'])
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
        }
    }
}
</script>


<style scoped lang='scss'>
.main {
    width: 100vw;
    height: 100vh;
    display: flex;

    // 左侧样式
    .left {
        width: 200px;
        background-color: rgb(245, 142, 25);

        .logo {
            font-family: 'nano';
            width: 90%;
            color: white;
            font-size: 18px;
            text-align: center;
            padding: 8px 0;
            border: 1px solid white;
            margin: 10px auto;
        }
    }

    // 右侧样式
    .right {

        flex: 1;


        // 顶部导航样式
        .nav {
            height: 60px;
            display: flex;
            justify-content: space-between;
            background-color: rgb(245, 142, 25);
        }

        .content {
            padding: 8px;
        }

        // 中间内容样式

    }
}
</style>