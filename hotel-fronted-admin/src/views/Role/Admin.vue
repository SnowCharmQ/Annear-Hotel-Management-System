<template>
    <div>
        <!-- 添加区域 -->
        <div class="addAdmin">
            <el-button type="primary" size="mini" round class="addAdminButton" @click="addClick">Add Admin</el-button>
        </div>
        <!-- 表格区域 -->
        <el-table :data="tableData" style="width: 98%; min-width: 1000px"
            :header-cell-style="{textAlign:'center', color: 'black', fontSize: '20px', fontFamily: 'nano', background: '#f3eee7', borderBottomColor: 'black'}"
            :cell-style="{ textAlign: 'center', background: '#f3eee7', fontSize: '18px', borderBottomColor: 'black' }"
            :row-style="setRowStyle" border-collapse:collapse stripe fit highlight-current-row border
            empty-text="No Administator Added">
            <el-table-column label="Date" text-align="center" width="180">
                <template slot-scope="scope">
                   {{ scope.row.date }}
                </template>
            </el-table-column>
            <el-table-column label="Name" width="180">
                <template slot-scope="scope">
                    {{ scope.row.admin_name }}

                    
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
                date: '2022-11-23',
                admin_name: '邱逸伦',
                admin_password: '123456'

            },
            {
                date: '2022-11-24',
                admin_name: '眭眭',
                admin_password: '171070'

            },],
            count: 5,
            form: {
                date: '',
                admin_name: '',
                admin_password: '',
            },
            rules: {

                
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
.addAdminButton {
    font-family: 'nano';
    font-size: medium;
    color: black;
    border-color: black;
    background-color: #f3eee7;
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
.el-button--primary {
    background-color: #f3eee7;
    border-color: #000000;
    color: black;
}
.el-button--primary:hover {
    background-color: #ffffff;
}
</style>