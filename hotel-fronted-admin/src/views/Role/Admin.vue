<template>
    <div>
        <!-- 添加区域 -->
        <div class="addAdmin">
            <el-button type="primary" size="mini" class="addAdminButton" @click="addClick">Add Admin</el-button>
        </div>
        <!-- 表格区域 -->
        <el-table :data="tableData" style="width: 98%; min-width: 1000px"
            :header-cell-style="{ color: 'black', fontSize: '14px', fontFamily: 'nano', background: '#f3eee7' }"
            :row-style="setRowStyle" border-collapse:collapse stripe fit highlight-current-row border
            empty-text="No Administator Added">
            <el-table-column label="Date" text-align="center" width="180">
                <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ scope.row.date }}</span>
                </template>
            </el-table-column>
            <el-table-column label="Name" width="180">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>Name: {{ scope.row.admin_name }}</p>

                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{ scope.row.admin_name }}</el-tag>
                        </div>
                    </el-popover>
                </template>
            </el-table-column>

            <el-table-column label="Operations">
                <template slot-scope="scope">
                    <el-button size="mini" type="danger" @click="deleteClass(scope.$index)">Delete
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="flex j-c" style="margin-top:5px">
            <el-pagination background layout="prev, pager, next" :total="count" :page-size="8"
                @current-change="currentChange">
            </el-pagination>
        </div>

        <!-- 抽屉区域 -->
        <el-drawer title="Add an Administrator" text-align="center" :visible.sync="drawer" direction="rtl"
            :before-close="handleDrawerClose" class="drawerStyle">
            <!-- 表单 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="100px">

                <el-form-item label="Name" prop="admin_name">
                    <el-input v-model="form.admin_name"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="admin_password">
                    <el-input v-model="form.admin_password"></el-input>
                </el-form-item>
                <el-form-item>
                    <div class="button_div">
                        <el-button type="primary" size="small" @click="submitForm('form')">Submit</el-button>
                        <el-button size="small" class="normal_button" @click="resetForm('form')">Cancel
                        </el-button>
                    </div>
                </el-form-item>
            </el-form>
        </el-drawer>
    </div>
</template>

<script>
import { done } from 'nprogress';

export default {
    data() {
        var validateAdminName = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('Please enter name of administrator.'));
            } else {
                let validName = /^[0-9a-zA-Z.]+$/.test(value)
                if (!validName) {
                    callback(new Error('Duration must be a combination of number and letter'));
                }
                else {
                    callback();
                }
            }
        };
        var validateAdminPassword = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('Please enter password of administrator.'));
            } else {
                let validName = /^[0-9a-zA-Z.]+$/.test(value)
                if (!validName) {
                    callback(new Error('Password must be a combination of number and letter'));
                }
                else {
                    callback();
                }
            }
        };
        return {
            // 表格数据
            tableData: [{
                date: '2016-05-03',
                admin_name: 'Tom',
                admin_password: 'xxx'

            }],
            count: 5,
            form: {
                date: '',
                admin_name: '',
                admin_password: '',
            },
            rules: {

                admin_name: [
                    { validator: validateAdminName, trigger: 'blur' },

                ],
                admin_password: [
                    { validator: validateAdminPassword, trigger: 'blur' },

                ],

            },
            // 是否打开抽屉
            drawer: false,

        }
    },
    // created() {
    //     this.getTableData();
    // },
    // 方法
    methods: {
        //用来获取表格数据
        // async getTableData() {
        //     let res = await this.$get('/Role/List')
        //     console.log(res);
        // },
        addClick() {
            this.drawer = true;
        },
        handleEdit(index, row) {
            console.log(index, row);
        },
        deleteClass(index) {
            this.tableData.splice(index, 1);
        },
        handleDrawerClose(done) {
            done();

        },
        submitForm(formName) {
            this.$refs[formName].validate(async (valid) => {
                if (valid) {
                    // alert('submit!');

                    this.form.date = '2022/11/27'
                    this.tableData.push(
                        JSON.parse(JSON.stringify(this.form))
                    );

                    console.log("hello world!");

                } else {
                    console.log('error submit!!');
                    return false;
                }
                this.$refs[formName].resetFields();

            });
        },
        setRowStyle(row) {
            if (row.row.isPart == true) {
                return 'color:#1772b4;'
            }
        },
        async resetForm(formName) {
            this.$refs[formName].resetFields();
            this.dialogFormVisible = false;
        },

    }
}
</script>
<style scoped lang="scss">
.addAdminButton {
    font-family: 'nano';
    font-size: medium;
    color: black;
    border-color: black;
    background-color: #f3eee7;
}

.drawerStyle {
    color: #dc8917;
}
</style>