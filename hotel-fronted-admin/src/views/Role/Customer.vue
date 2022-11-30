<template>
    <div>
        <!-- 添加区域 -->
        <div class="addCustomer">
            <el-button type="primary" round size="mini" class="addCustomerButton" @click="addClick">Add Customer</el-button>
        </div>
        <!-- 表格区域 -->
        <el-table :data="tableData" style="width: 98%; min-width: 1000px"
        :header-cell-style="{ color: 'black', fontSize: '20px', fontFamily: 'nano', background: '#f3eee7', textAlign: 'center', borderBottomColor: 'black' }"
        :cell-style="{ textAlign: 'center', background: '#f3eee7', fontSize: '18px', borderBottomColor: 'black' }"            
            :row-style="setRowStyle" border-collapse:collapse stripe fit highlight-current-row border
            empty-text="No Customer Added">
            <el-table-column label="Date" text-align="center" width="180">
                <template slot-scope="scope">
                   {{ scope.row.date }}
                </template>
            </el-table-column>
            <el-table-column label="Name" width="180">
                <template slot-scope="scope">
                   {{ scope.row.customer_name }}
                
                </template>
            </el-table-column>
            <el-table-column label="ID" width="180">
                <template slot-scope="scope">
                   {{ scope.row.customer_id }}

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
                date: '2022-11-22',
                customer_name: 'sui_h',
                customer_password: '11111',
                customer_id: '1'

            },
            {
                date: '2022-11-27',
                customer_name: 'SUI_H',
                customer_password: '111211',
                customer_id: '2'

            }],
            count: 5,
            form: {
                date: '',
                customer_name: '',
                customer_password: '',
                customer_id: ''
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

.el-button--primary {
    background-color: #f3eee7;
    border-color: #000000;
    color: black;
}

.editButton {
    margin-top: 0px;
    font-family: 'nano';
    height: 30px;
    font-size: 15px;
    line-height: 0.4;
    background-color: #f1e4a6;
    border: 1px solid rgb(238, 213, 184);
    text-align: center;
    color: black;


    span {
        font-family: 'nano';
        color: white;
        font-size: 14px;
        letter-spacing: 1px;
    }
}

.el-button--danger {
    background-color: #f15973;
    border-color: #ec9bad;
    font-family: 'nano';
    font-size: 15px;
}

.el-button--danger:hover {
    background-color: #e9173a;

}


.el-button--primary:hover {
    background-color: #ffffff;
}
</style>