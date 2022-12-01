<template>
    <div>
        <!-- 添加区域 -->
        <div class="addType">
            <el-button type="primary" size="mini" class="addTypeButton" @click="addClick">Add Type</el-button>
        </div>
        <!-- 表格区域 -->
        <el-table :data="tableData" style="width: 98%; min-width: 1000px"
            :cell-style="{ textAlign: 'center', background: '#f3eee7', fontSize: '18px', borderBottomColor: 'black' }"
            :header-cell-style="{ color: 'black', fontSize: '20px', fontFamily: 'nano', background: '#f3eee7', textAlign: 'center', borderBottomColor: 'black' }"
            :row-style="setRowStyle" border-collapse:collapse stripe fit highlight-current-row border
            empty-text="No Room Added">

            <el-table-column label="Name" width="180">
                <template slot-scope="scope">
                    {{ scope.row.type_name }}
                </template>
            </el-table-column>

            <el-table-column label="Price" width="180">
                <template slot-scope="scope">
                    {{ scope.row.price }}
                </template>
            </el-table-column>
            <el-table-column label="Photo" width="180">
                <template slot-scope="scope">
                    <img :src="scope.row.photo" width="100" height="100" class="head_pic" />
                    <!-- <img v-for="item in scope.row.photo" :src="item" width="80" height="80" class="head_pic"/> -->
                </template>
            </el-table-column>

            <el-table-column label="Operations">
                <template slot-scope="scope">
                    <el-button size="mini" type="primary" @click="editClass(scope.$index)" class="editButton">Edit
                    </el-button>
                    <el-button size="mini" type="danger" @click="deleteClass(scope.$index)">Delete
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog :visible.async="dialogFormVisible" :beforeClose="handleDialogFormClose">
            <el-form ref="form2" :model="form2" :rules="rules" label-width="100px">
                <el-form-item>
                    <div class="form_header">
                        <h1><b>Edit Room Type</b></h1>
                    </div>
                </el-form-item>
                <el-form-item label="Type Name" prop="type_name">
                    <el-input v-model="form2.type_name"></el-input>
                </el-form-item>
                <el-form-item label="Price" prop="price">
                    <el-input v-model="form2.price"></el-input>
                </el-form-item>

                <el-form-item label="Photo" prop="photo">
                    <el-upload class="avatar-uploader" action="https://jsonplaceholder.typicode.com/posts/"
                        :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                        <img v-if="imageUrl" :src="imageUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>

                <el-form-item>
                    <div class="button_div">
                        <el-button type="primary" size="small" @click="editForm('form2')">Edit</el-button>
                        <el-button size="small" class="normal_button" @click="resetForm('form2')">Cancel
                        </el-button>
                    </div>
                </el-form-item>


            </el-form>
        </el-dialog>
        <div class="flex j-c" style="margin-top:5px">
            <el-pagination background layout="prev, pager, next" :total="count" :page-size="8"
                @current-change="currentChange">
            </el-pagination>
        </div>

        <!-- 抽屉区域 -->
        <el-drawer title="Add a Room Type" text-align="center" :visible.sync="drawer" direction="rtl"
            :before-close="handleDrawerClose" class="drawerStyle" size="50%">
            <!-- 表单 -->

            <el-form ref="form" :model="form" :rules="rules" label-width="100px">
                <el-form-item label="Photo">
                    <el-upload class="avatar-uploader" action="https://jsonplaceholder.typicode.com/posts/"
                        :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                        <img v-if="imageUrl" :src="imageUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>

                <el-form-item label="Room Type" prop="type_name">
                    <el-input v-model="form.type_name"></el-input>
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


export default {
    data() {
        var validateTypeName = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('Please enter Room Type.'));
            } else {
                let validName = /^[a-z A-Z.]+$/.test(value)
                if (!validName) {
                    callback(new Error('Room Type must be a combination of letters'));
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
                photo: ['data:image/jpeg;base64,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'],
                type_name: 'Standard Room',

                price: 100,


            },
            {
                photo: ['https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_uEGCrtv5GYL4hn2ACPo-3GvYyvZljdPbyqsCnVkoqg&s'],
                type_name:'Presidential Suite',

                price: 1000,


            },
            {
                photo:['data:image/jpeg;base64,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'],
                type_name:'King bed room',

                price: 300,


            }],
            num: '',
            count: 5,
            imageUrl: '',
            dialogFormVisible: false,
            form: {

                type_name: '',

                price: '',
                photo: ''
            },
            form2: {
                type_name: '',
                price: '',
                photo: '',
                index: -1
            },
            rules: {

                type_name: [
                    { validator: validateTypeName, trigger: 'blur' },

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
        editClass(index) {
            this.form2.type_name = this.tableData[index].type_name
            this.form2.price = this.tableData[index].price
            this.form2.photo = this.tableData[index].photo

            this.form2.index = index

            this.dialogFormVisible = true
        },
        deleteClass(index) {
            this.tableData.splice(index, 1);
        },
        handleDrawerClose(done) {
            done();

        },
        editForm(formName) {
            this.$refs[formName].validate(async (valid) => {
                if (valid) {

                    this.tableData[this.form2.index] = JSON.parse(JSON.stringify(this.form2))
                    console.log(this.tableData[this.form2.index])
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
        handleDialogFormClose() {

            // this.$refs['form2'].resetFields();
            this.dialogFormVisible = false;

        },
        currentChange(pageIndex) {
            this.pageIndex = pageIndex;
            console.log("To be done current");
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
        // 上传成功
        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('Avatar picture must be JPG format!');
            }
            if (!isLt2M) {
                this.$message.error('Avatar picture size can not exceed 2MB!');
            }
            return isJPG && isLt2M;
        }

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

// 上传头像样式
.avatar-uploader {
    width: 178px;
    height: 178px;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.avatar {
    width: 178px;
    height: 178px;
    display: block;
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
    background-color: #f3eee7;
    border: 1px solid rgb(0, 0, 0);
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

::v-deep .el-table tbody tr:hover>td {
    background-color: #8dfae1;
    opacity: 70%;
}


.el-button--primary:hover {
    background-color: #fffefd;
}
</style>