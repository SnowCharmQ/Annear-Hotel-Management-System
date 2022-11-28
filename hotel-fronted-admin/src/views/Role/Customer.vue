<template>
    <div>
        <!-- 添加区域 -->
        <div class="addCustomer">
            <el-button type="primary" size="mini" class="addCustomerButton" @click="addClick">Add Customer</el-button>
        </div>
        <!-- 表格区域 -->
        <el-table :data="tableData" style="width: 98%; min-width: 1000px"
            :header-cell-style="{ color: 'black', fontSize: '14px', fontFamily: 'nano', background: '#f3eee7' }"
            :row-style="setRowStyle" border-collapse:collapse stripe fit highlight-current-row border
            empty-text="No Customer Added">
            <el-table-column label="Date" text-align="center" width="180">
                <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ scope.row.date }}</span>
                </template>
            </el-table-column>
            <el-table-column label="Name" width="180">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>Name: {{ scope.row.customer_name }}</p>

                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{ scope.row.customer_name }}</el-tag>
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
        <el-drawer title="Add an Customer" text-align="center" :visible.sync="drawer" direction="rtl"
            :before-close="handleDrawerClose" class="drawerStyle">
            <!-- 表单 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="100px">

                <el-form-item label="Name" prop="customer_name">
                    <el-input v-model="form.customer_name"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="customer_password">
                    <el-input v-model="form.customer_password"></el-input>
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
        var validateCustomerName = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('Please enter name of customer.'));
            } else {
                let validName = /^[0-9a-zA-Z.]+$/.test(value)
                if (!validName) {
                    callback(new Error('Customer name must be a combination of number and letter'));
                }
                else {
                    callback();
                }
            }
        };
        var validateCustomerPassword = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('Please enter password of customer.'));
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
                customer_name: 'Tom',
                customer_password: 'xxx'

            }],
            count: 5,
            form: {
                date: '',
                customer_name: '',
                customer_password: '',
            },
            rules: {

                customer_name: [
                    { validator: validateCustomerName, trigger: 'blur' },

                ],
                customer_password: [
                    { validator: validateCustomerPassword, trigger: 'blur' },

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
.addCustomerButton {
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