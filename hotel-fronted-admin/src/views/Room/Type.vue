<template>
    <div>
        <!-- 添加区域 -->
        <div class="addType">
            <el-button type="primary" size="mini" class="addTypeButton" @click="addClick">Add Type</el-button>
        </div>
        <!-- 表格区域 -->
        <el-table :data="tableData" style="width: 98%; min-width: 1000px"
            :header-cell-style="{ color: 'black', fontSize: '14px', fontFamily: 'nano', background: '#f3eee7' }"
            :row-style="setRowStyle" border-collapse:collapse stripe fit highlight-current-row border
            empty-text="No Room Added">

            <el-table-column label="Name" width="180">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>Name: {{ scope.row.type_name }}</p>

                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{ scope.row.type_name }}</el-tag>
                        </div>
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column label="Number" width="180">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>Number: {{ scope.row.num }}</p>
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{ scope.row.num }}</el-tag>
                        </div>
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column label="Price" width="180">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>Price: {{ scope.row.price }}</p>
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{ scope.row.price }}</el-tag>
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
        <el-drawer title="Add a Room Type" text-align="center" :visible.sync="drawer" direction="rtl"
            :before-close="handleDrawerClose" class="drawerStyle">
            <!-- 表单 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="100px">

                <el-form-item label="Room Type" prop="type_name">
                    <el-input v-model="form.type_name"></el-input>
                </el-form-item>
                <el-form-item label="Number" prop="number">
                    <el-input v-model="form.num"></el-input>
                </el-form-item>
                <el-form-item label="Price" prop="price">
                    <el-input v-model="form.price"></el-input>
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
        var validateTypeName = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('Please enter Room Type.'));
            } else {
                let validName = /^[a-zA-Z.]+$/.test(value)
                if (!validName) {
                    callback(new Error('Room Type must be a combination of letters'));
                }
                else {
                    callback();
                }
            }
        };
        var validateNum = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('Please enter Room Type.'));
            } else {
                let validName = /^[0-9.]+$/.test(value)
                if (!validName) {
                    callback(new Error('Number of room must be a combination of digits'));
                }
                else {
                    callback();
                }
            }
        };
        var validatePrice = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('Please enter the price.'));
            } else {
                let validName = /^[0-9.]+$/.test(value)
                if (!validName) {
                    callback(new Error('Room Price must be a combination of digits'));
                }
                else {
                    callback();
                }
            }
        };

        return {
            // 表格数据
            tableData: [{
                type_name: 'Standard Room',
                num: '200',
                price: '$100',


            }],
            count: 5,
            form: {
                type_name: '',
                num: '',
                price: ''
            },
            rules: {

                type_name: [
                    { validator: validateTypeName, trigger: 'blur' },

                ],
                num: [
                    { validator: validateNum, trigger: 'blur' },
                ],
                price: [
                    { validator: validatePrice, trigger: 'blur' },
                ]

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
.addTypeButton {
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