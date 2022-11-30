<template>
    <div>
        <!-- 添加区域 -->
        <div class="addRoom">
            <el-button type="primary" size="mini"  class="addRoomButton" @click="addClick">Add Room</el-button>
        </div>
        <!-- 表格区域 -->
        <el-table :data="tableData" style="width: 98%; min-width: 1000px"
            :cell-style="{ textAlign: 'center', background: '#f3eee7', fontSize: '18px', borderBottomColor: 'black' }"
            :header-cell-style="{ color: 'black', fontSize: '20px', fontFamily: 'nano', background: '#f3eee7', textAlign: 'center', borderBottomColor: 'black' }"
            :row-style="setRowStyle" stripe fit highlight-current-row border empty-text="No Room Added">

            <el-table-column label="Room No" width="180">
                <template slot-scope="scope">
                    {{ scope.row.room_num }}
                </template>
            </el-table-column>

            <el-table-column label="Type" width="180">
                <template slot-scope="scope">

                    {{ scope.row.room_type }}

                </template>
            </el-table-column>
            <el-table-column label="Floor" width="180">
                <template slot-scope="scope">

                    {{ scope.row.floor }}

                </template>
            </el-table-column>
            <el-table-column label="Floor Plan" width="180">
                <template slot-scope="scope">

                    {{ scope.row.floor_plan }}
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
                        <h1>Edit Room</h1>
                    </div>
                </el-form-item>
                <el-form-item label="Room No" prop="room_num">
                    <el-input v-model="form2.room_num"></el-input>
                </el-form-item>
                <el-form-item label="Room Type" prop="room_type">
                    <el-input v-model="form2.room_type"></el-input>
                </el-form-item>

                <el-form-item label="Room Floor" prop="floor">
                    <el-input v-model="form2.floor"></el-input>
                </el-form-item>

                <el-form-item label="Floor Plan" prop="floor_plan">
                    <el-input v-model="form2.floor_plan"></el-input>
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
            <el-pagination background layout="prev, pager, next" :total="count" :page-size="8" class="pageClass"
                @current-change="currentChange">
            </el-pagination>
        </div>

        <!-- 抽屉区域 -->
        <el-drawer title="Add a Room No" text-align="center" :visible.sync="drawer" direction="rtl"
            :before-close="handleDrawerClose" class="drawerStyle" size="50%">
            <!-- 表单 -->

            <el-form ref="form" :model="form" :rules="rules" label-width="100px">


                <el-form-item label="Room No" prop="room_num">
                    <el-input v-model="form.room_num"></el-input>
                </el-form-item>

                <el-form-item label="Type" prop="type">
                    <el-input v-model="form.room_type"></el-input>
                </el-form-item>
                <el-form-item label="Floor" prop="floor">
                    <el-select v-model="form.floor" placeholder="The 1st floor">
                        <el-option label="The 1st floor" value="1"></el-option>
                        <el-option label="The 2nd floor" value="2"></el-option>
                        <el-option label="The 3rd floor" value="3"></el-option>
                        <el-option label="The 4th floor" value="4"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Floor Plan" prop="floor">
                    <el-input v-model="form.floor_plan"></el-input>
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
        var validateRoomNum = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('Please enter Room Number.'));
            } else {
                let validName = /^[a-zA-Z]*[0-9.]+$/.test(value)
                if (!validName) {
                    callback(new Error('Room Number must be a combination of digits'));
                }
                else {
                    callback();
                }
            }
        };

        var validateRoomType = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('Please enter the type.'));
            } else {
                let validName = /^[0-9a-zA-Z .]+$/.test(value)
                if (!validName) {
                    callback(new Error('Room type must be a combination of digits or letters'));
                }
                else {
                    callback();
                }
            }
        };

        var validateFloor = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('Please enter the floor.'));
            } else {
                let validName = /^[+-]?[0-9.]+$/.test(value)
                if (!validName) {
                    callback(new Error('Room floor must be number'));
                }
                else {
                    callback();
                }
            }
        };
        var validateFloorPlan = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('Please enter the floor plan.'));
            } else {
                let validName = /^[0-9.]+$/.test(value)
                console.log(parseInt(value))
                if (!validName) {
                    callback(new Error('Room floor plan must be a combination of digits'));
                }
                else if (parseInt(value) < 1 || parseInt(value) > 16) {

                    callback(new Error('Room floor plan number must be within 1 and 16'));
                } else {
                    callback();
                }
            }
        };


        return {
            // 表格数据
            tableData: [{
                room_num: '114514',
                room_type: 'presidential suite',
                floor: -1,
                floor_plan: 2,

            },
            {
                room_num: '114',
                room_type: 'standard suite',
                floor: 3,
                floor_plan: 2,
            },
            {
                room_num: '514',
                room_type: 'three suite',
                floor: 3,
                floor_plan: 2,
            }],
            num: '',
            count: 5,
            imageUrl: '',
            dialogFormVisible: false,
            form: {
                room_num: '',
                room_type: '',
                floor: '',
                floor_plan: '',
            },
            form2: {
                room_num: '',
                room_type: '',
                floor: -1,
                floor_plan: 2,
                index: -1
            },
            rules: {

                room_num: [
                    { validator: validateRoomNum, trigger: 'blur' },

                ],
                room_type: [
                    { validator: validateRoomType, trigger: 'blur' },
                ],
                floor: [
                    { validator: validateFloor, trigger: 'blur' },
                ],
                floor_plan: [
                    { validator: validateFloorPlan, trigger: 'blur' }
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
        editClass(index) {

            this.form2.room_num = this.tableData[index].room_num
            this.form2.room_type = this.tableData[index].room_type
            this.form2.floor = this.tableData[index].floor
            this.form2.floor_plan = this.tableData[index].floor_plan

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
            // if (row.row.isPart == true) {
            return 'color:black;'
            // }
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
.addRoomButton {
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

.form_header {
    color: black;
    font-size: medium;
    font-weight: lighter;
    font-family: 'nano';
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


.el-button--primary:hover {
    background-color: #ffffff;
}
::v-deep .el-table tbody tr:hover>td {
    background-color: #8dfae1;
    opacity: 70%;
}
</style>