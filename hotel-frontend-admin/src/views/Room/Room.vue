<template>
  <div>
    <!-- 添加区域 -->
    <div class="addRoom">
      <el-button type="primary" size="mini" class="addRoomButton" @click="addClick">Add Room</el-button>
    </div>
    <!-- 表格区域 -->
    <el-table :data="tableData" style="width: 98%; min-width: 1000px"
              :cell-style="{ textAlign: 'center', background: '#f3eee7', fontSize: '18px', borderBottomColor: 'black' }"
              :header-cell-style="{ color: 'black', fontSize: '20px', fontFamily: 'nano', background: '#f3eee7', textAlign: 'center', borderBottomColor: 'black' }"
              :row-style="setRowStyle" stripe fit highlight-current-row border empty-text="No Room Added" :key="num">

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
          <el-input style="width: 280px" v-model="rn"></el-input>
        </el-form-item>
        <el-form-item label="Type" prop="type">
          <el-select style="width: 280px" v-model="rt" placeholder="Choose The Room Type">
            <el-option v-for="roomType in roomTypes" :value="roomType"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="Floor" prop="floor">
          <el-select style="width: 280px" v-model="f" placeholder="Choose The Floor">
            <el-option v-for="floor in floors" :value="floor"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="Floor Plan" prop="floor">
          <el-select style="width: 280px" v-model="form2.floor_plan" placeholder="Choose The Floor Plan Index">
            <el-option v-for="f in 15" :value="f"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <div class="button_div">
            <el-button type="primary" size="small" @click="editForm('form2')">Edit</el-button>
          </div>
        </el-form-item>

      </el-form>
    </el-dialog>
    <div class="flex j-c" style="margin-top:5px">
      <el-pagination background layout="prev, pager, next"
                     :total="count"
                     :page-size="pageSize"
                     :current-page="pageIndex" class="pageClass"
                     @current-change="currentChange">
      </el-pagination>
    </div>

    <!-- 抽屉区域 -->
    <el-drawer title="Add a Room No" textAlign="center" :visible.sync="drawer" direction="rtl"
               :before-close="handleDrawerClose" width="20%">
      <!-- 表单 -->

      <el-form ref="form" :model="form" :rules="rules" label-width="100px">


        <el-form-item label="Room No" prop="roomNumber">
          <el-input style="width: 220px" v-model="form.roomNumber"></el-input>
        </el-form-item>

        <el-form-item label="Type" prop="type">
          <el-select style="width: 280px" v-model="form.roomType" placeholder="Choose The Room Type">
            <el-option v-for="f in roomTypes" :value="f"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Floor" prop="floor">
          <el-select style="width: 280px" v-model="form.floor" placeholder="Choose The Floor">
            <el-option v-for="f in floors" :value="f"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Floor Plan" prop="floor">
          <el-select style="width: 280px" v-model="form.floorPlan" placeholder="Choose The Floor Plan Index">
            <el-option v-for="f in 15" :value="f"></el-option>
          </el-select>
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
        } else {
          callback();
        }
      }
    };

    return {
      // 表格数据
      tableData: [],
      pageSize: 8,
      pageIndex: 1,
      hotelId: '',
      num: '',
      count: 5,
      imageUrl: '',
      dialogFormVisible: false,
      rules: {
        room_num: [
          {validator: validateRoomNum, trigger: 'blur'},
        ],
      },
      // 是否打开抽屉
      drawer: false,
      form: {roomNumber: '', roomType: '', floor: '', floorPlan: ''},
      form2: {roomNumber: '', roomType: '', floor: '', floorPlan: '', index: ''},
      roomTypes: [],
      floors: 0,
      roomNumber: '',
      rn: 0,
      rt: 0,
      f: 0,
      fp: 0
    }
  },
  created() {
    let params = {'token': sessionStorage.getItem('token')};
    this.$get(this.$baseUrl + '/auth/getUsernameByToken', params).then(data => {
      let resp = data.data;
      let para = {'name': resp}
      this.$get(this.$baseUrl + '/room/room/hotel/initAdminRoom', para).then(data => {
        let resp = data.data
        this.hotelId = resp.hotelId
        this.roomTypes = resp.typeNames;
        this.floors = resp.floors;
        this.getTableData();
      })
    })
  },
  methods: {
    //用来获取表格数据
    getTableData() {
      let params = {}
      Object.assign(params, {
        page: this.pageIndex,
        limit: this.pageSize,
        hotel: this.hotelId,
      })
      this.tableData = []
      this.$get(this.$baseUrl + '/room/room/room/list', params).then(data => {
        let resp = data.data.list
        for (let i = 0; i < resp.length; i++) {
          let item = resp[i]
          this.tableData.push({
            room_num: item.roomNumber,
            room_type: item.typeName,
            floor: item.layoutId,
            floor_plan: item.floorPlanId
          })
        }
      }).catch(err => {
        this.$message.error("Network Error");
      })
    },
    addClick() {
      this.drawer = true;
    },
    editClass(index) {

      this.form2.room_num = this.tableData[index].room_num
      this.form2.room_type = this.tableData[index].room_type
      this.form2.floor = this.tableData[index].floor
      this.form2.floor_plan = this.tableData[index].floor_plan

      this.rn = this.form2.room_num;
      this.rt = this.form2.room_type;
      this.f = this.form2.floor;
      this.fp = this.form2.floor_plan;

      this.form2.index = index

      this.dialogFormVisible = true
    },
    deleteClass(index) {
      let data = this.tableData[index];
      let params = {}
      Object.assign(params, {
        hotelId: this.hotelId,
        roomNumber: data.room_num,
        roomType: data.room_type,
        floor: data.floor,
        floorPlan: data.floor_plan,
      })
      this.$get(this.$baseUrl + '/room/room/room/deleteRoom', params).then(data => {
        if (data.state === 200) {
          this.$message.success("Successfully Deleted");
          this.tableData.splice(index, 1);
        } else {
          this.$message.error(data.message);
        }
      })
    },
    handleDrawerClose(done) {
      done();
    },
    editForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          let params = {}
          Object.assign(params, {
            hotelId: this.hotelId,
            roomNumber: this.rn,
            roomType: this.rt,
            floor: this.f,
            floorPlan: this.fp,
            oldFloor: this.tableData[this.form2.index].floor,
            oldFloorPlan: this.tableData[this.form2.index].floor_plan
          })
          this.num = Math.random();
          this.$get(this.$baseUrl + '/room/room/room/editRoom', params).then(data => {
            if (data.state === 200) {
              this.$message.success("Successfully Edited");
              location.reload();
            } else {
              this.$message.error(data.message);
            }
          }).catch(err => {
            this.$message.error("Network Error");
          })
          this.dialogFormVisible = false;
        } else {
          this.$message.error("Wrong Input");
          return false;
        }
      });
    },
    handleDialogFormClose() {

      // this.$refs['form2'].resetFields();
      this.dialogFormVisible = false;
    },
    currentChange(pageIndex) {
      this.pageIndex = pageIndex;
    },
    submitForm(formName) {
      let params = {};
      Object.assign(params, {
        hotelId: this.hotelId,
        roomNumber: this.form.roomNumber,
        roomType: this.form.roomType,
        floor: this.form.floor,
        floorPlan: this.form.floorPlan
      });
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.$get(this.$baseUrl + '/room/room/room/addRoom', params).then(data => {
            if (data.state === 200) {
              this.$message.success("Successfully Added");
              location.reload();
            } else {
              this.$message.error(data.message);
            }
          }).catch(err => {
            this.$message.error("Network Error");
          })
        } else {
          this.$message.error("Wrong Input");
          return false;
        }
      });
    },
    setRowStyle(row) {
      // if (row.row.isPart == true) {
      return 'color:black;'
      // }
    },
  }
}
</script>
<style scoped lang="scss">
.addRoomButton {
  font-family: 'nano', serif;
  font-size: medium;
  color: black;
  border-color: black;
  background-color: #f3eee7;
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

.addRoom .el-drawer {
  width: 800px !important;
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

.el-drawer.rtl {
  background: #6aabc5;
}

.el-button--primary:hover {
  background-color: #ffffff;
}

::v-deep .el-table tbody tr:hover > td {
  background-color: #8dfae1;
  opacity: 70%;
}
</style>