<template>
    <div>
        <!-- 添加区域 -->
        <div class="addRoom">
            
            <i class="el-icon-s-home"></i>
            <!-- <el-button type="primary" size="mini"  class="addRoomButton" @click="addClick">Add Room</el-button> -->
            <el-select v-model="form.type" placeholder="King Bed Suite" class="selection">
                <el-option label="Standard Suite" value="Standard Suite"></el-option>
                <el-option label="Presidential Suite" value="Presidential Suite"></el-option>
                <el-option label="King Bed Suite" value="King Bed Suite"></el-option>
                <el-option label="Cancelled" value="Cancelled"></el-option>
            </el-select>
            <i class="el-icon-data-line"></i>
            <el-select v-model="form.status" placeholder="Finished" class="selection">
                <el-option label="Finished" value="Finished"></el-option>
                <el-option label="Waiting to Pay" value="Waiting to Pay"></el-option>
                <el-option label="Order Cancelled" value="Cancelled"></el-option>
                <el-option label="Undertaking" value="Undertaking"></el-option>
                <el-option label="Waiting Comment" value="Waiting Comment"></el-option>
                <el-option label="Cancelled" value="Cancelled"></el-option>
            </el-select>
            <i class="el-icon-date"></i>
            <el-date-picker type="date" placeholder="check-in" v-model="form2.date1" style="width: 15%;">
            </el-date-picker>
            <i class="el-icon-date"></i>
            <el-date-picker type="date" placeholder="check-out" v-model="form2.date2" style="width: 15%;">
            </el-date-picker>
        </div>
        <!-- 表格区域 -->
        <el-table :data="tableData" style="width: 98%; min-width: 1000px"
            :cell-style="{ textAlign: 'center', background: '#f3eee7', fontSize: '18px', borderBottomColor: 'black' }"
            :header-cell-style="{ color: 'black', fontSize: '20px', fontFamily: 'nano', background: '#f3eee7', textAlign: 'center', borderBottomColor: 'black' }"
            :row-style="setRowStyle" stripe fit highlight-current-row border empty-text="No Room Added">

            <el-table-column label="orderID" width="100">
                <template slot-scope="scope">
                    {{ scope.row.orderID }}
                </template>
            </el-table-column>

            <el-table-column label="userID" width="100">
                <template slot-scope="scope">

                    {{ scope.row.userID }}

                </template>
            </el-table-column>
            <el-table-column label="check-in date" width="250">
                <template slot-scope="scope">

                    {{ scope.row.date1 }}

                </template>
            </el-table-column>
            <el-table-column label="check-out date" width="250">
                <template slot-scope="scope">

                    {{ scope.row.date2 }}
                </template>
            </el-table-column>
            <el-table-column label="score" width="80">
                <template slot-scope="scope">

                    {{ scope.row.score }}
                </template>
            </el-table-column>
            <el-table-column label="name" width="100">
                <template slot-scope="scope">

                    {{ scope.row.name }}
                </template>
            </el-table-column>
            <el-table-column label="phone" width="150">
                <template slot-scope="scope">

                    {{ scope.row.phone }}
                </template>
            </el-table-column>
            <el-table-column label="email">
                <template slot-scope="scope">

                    {{ scope.row.email }}
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
=======
  <div>
    <!-- 添加区域 -->
    <div class="addRoom">
      <i class="el-icon-office-building"></i>
      <!-- <el-button type="primary" size="mini"  class="addRoomButton" @click="addClick">Add Room</el-button> -->
<!--      <el-select v-model="form.hotel" placeholder="Shanghai Hotel" class="selection">-->
<!--        <el-option label="Shanghai Hotel" value="Shanghai Hotel"></el-option>-->
<!--        <el-option label="Beijing Hotel" value="Beijing Hotel"></el-option>-->
<!--        <el-option label="Shenzhen Hotel" value="Shenzhen Hotel"></el-option>-->
<!--      </el-select>-->
      <i class="el-icon-s-home"></i>
      <!-- <el-button type="primary" size="mini"  class="addRoomButton" @click="addClick">Add Room</el-button> -->
      <el-select v-model="form.type" placeholder="King Bed Suite" class="selection">
        <el-option v-for="(item) in typeList"
                   :label="item.label"
                   :value='item.value'>
        </el-option>
      </el-select>
      <i class="el-icon-data-line"></i>
      <el-select v-model="form.status" placeholder="Finished" class="selection">
        <el-option label="Finished" value='5'></el-option>
        <el-option label="Waiting to Pay" value='4'></el-option>
        <el-option label="Cancelled" value='3'></el-option>
        <el-option label="Undertaking" value='2'></el-option>
        <el-option label="Waiting Comment" value='1'></el-option>
      </el-select>
      <i class="el-icon-date"></i>
      <el-date-picker type="date" placeholder="check-in" v-model="form.date1" style="width: 15%;">
      </el-date-picker>
      <i class="el-icon-date"></i>
      <el-date-picker type="date" placeholder="check-out" v-model="form.date2" style="width: 15%;">
      </el-date-picker>
      <div class="addRoom">
        <el-button type="primary" size="mini" class="addRoomButton" @click="getOrderList">Add Room</el-button>
      </div>
>>>>>>> 6fc73e0c913013e6c1ec4dffd79bcc826ecd4d9d
    </div>
    <!-- 表格区域 -->
    <el-table :data="tableData" style="width: 98%; min-width: 1000px"
              :cell-style="{ textAlign: 'center', background: '#f3eee7', fontSize: '18px', borderBottomColor: 'black' }"
              :header-cell-style="{ color: 'black', fontSize: '20px', fontFamily: 'nano', background: '#f3eee7', textAlign: 'center', borderBottomColor: 'black' }"
              :row-style="setRowStyle" stripe fit highlight-current-row border empty-text="No Room Added">

      <el-table-column label="orderID" width="100">
        <template slot-scope="scope">
          {{ scope.row.orderID }}
        </template>
      </el-table-column>

      <el-table-column label="userID" width="100">
        <template slot-scope="scope">

          {{ scope.row.userID }}

        </template>
      </el-table-column>

      <el-table-column label="room type" width="250">
        <template slot-scope="scope">

          {{ scope.row.type}}

        </template>
      </el-table-column>

      <el-table-column label="check-in date" width="250">
        <template slot-scope="scope">

          {{ scope.row.date1 }}

        </template>
      </el-table-column>

      <el-table-column label="check-out date" width="250">
        <template slot-scope="scope">

          {{ scope.row.date2 }}
        </template>
      </el-table-column>
      <el-table-column label="status" width="80">
        <template slot-scope="scope">

          {{ scope.row.status }}
        </template>
      </el-table-column>
      <el-table-column label="name" width="100">
        <template slot-scope="scope">

          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="phone" width="150">
        <template slot-scope="scope">

          {{ scope.row.phone }}
        </template>
      </el-table-column>
      <el-table-column label="email">
        <template slot-scope="scope">

          {{ scope.row.email }}
        </template>
      </el-table-column>
    </el-table>

    <div class="flex j-c" style="margin-top:5px">
      <el-pagination background layout="prev, pager, next"
                     :total="count"
                     :page-size="pageSize"
                     :current-page="pageIndex" class="pageClass"
                     @current-change="currentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>


export default {
  data() {
    return {
      tableData: [],
      pageIndex: 1,
      pageSize: 8,
      count: 5,
      form: {
        hotel: null,
        type: null,
        status: null,
        date1: null,
        date2: null,
      },
      typeList: []
    }
  },
  created() {

    let params = {'token': sessionStorage.getItem('token')};
    this.$get(this.$baseUrl + '/auth/getUsernameByToken', params).then(data => {
      let resp = data.data;
      console.log(resp)
      this.getTypeList(resp)
    })


  },
  methods: {
    getTypeList(hotel) {
      let params = {'hotel': hotel};
      this.$get(this.$baseUrl + '/room/room/roomtype/getRoomType', params).then(data => {
        let resp = data.data
        console.log(resp)
        for (let i = 0; i < resp.length; i++) {
          let item = resp[i]
          this.typeList.push({
            label: item.typeName,
            value: item.typeId
          })
          this.form.hotel = item.hotelId
        }
        if (resp.length > 0) {
          this.getOrderList()
        }
        console.log(this.form.hotel)
      }).catch(err => {
        console.log('network err')
      })
    },
    currentChange(pageIndex) {
      this.pageIndex = pageIndex;
      console.log("To be done current");
      this.getOrderList();
    },
    getOrderList() {
      let params = {}
      let tempDate1 = new Date(this.form.date1)
      let tempDate2 = new Date(this.form.date2)
      Object.assign(params, {
        page: this.pageIndex,
        limit: this.pageSize,
        hotel: this.form.hotel,
        type: this.form.type,
        status: this.form.status,
        date1: tempDate1.getTime(),
        date2: tempDate2.getTime()
      })
      this.tableData = []
      console.log('hi')
      console.log(params)
      this.$get(this.$baseUrl + '/order/order/order/list', params).then(data => {
        console.log(data)
        let resp = data.data.list
        for (let i = 0; i < resp.length; i++) {
          let item = resp[i]
          this.tableData.push({
            orderID: item.orderId,
            userID: item.userId,
            date1: item.startDate,
            date2: item.endDate,
            score: item.score,
            type: item.typeId,
            status: item.orderStatus,
            name: item.contactName,
            phone: item.contactPhone,
            email: item.contactEmail
          })
        }
      }).catch(err => {
        this.$message.error("Network Error");
      })
    },
    setRowStyle(row) {
      return 'color:black;'
    },
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

.selection {
  width: 150px;
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

::v-deep .el-table tbody tr:hover > td {
  background-color: #8dfae1;
  opacity: 70%;
}

.el-table--enable-row-hover .el-table__body tr:hover > td.el-table__cell {
  background-color: #8dfae1;
}

.el-icon-office-building {
  padding-left: 5px;
  padding-right: 0;
  width: 18px;
  height: 25px;
}

.el-icon-s-home {
  padding-left: 5px;
  width: 18px;
  height: 25px;
}

.el-icon-data-line {
  padding-left: 5px;
  width: 18px;
  height: 25px;
}

.el-icon-date {
  padding-left: 5px;
  width: 18px;
  height: 25px;
}

.el-pagination.is-background .el-pager {
  background-color: #f3eee7;
  color: #FFF;
}
</style>