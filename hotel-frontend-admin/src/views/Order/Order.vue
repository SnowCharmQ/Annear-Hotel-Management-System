<template>
  <div>
    <!-- 添加区域 -->
    <div class="addRoom">
      <!-- <el-button type="primary" size="mini"  class="addRoomButton" @click="addClick">Add Room</el-button> -->
      <!--      <el-select v-model="form.hotel" placeholder="Shanghai Hotel" class="selection">-->
      <!--        <el-option label="Shanghai Hotel" value="Shanghai Hotel"></el-option>-->
      <!--        <el-option label="Beijing Hotel" value="Beijing Hotel"></el-option>-->
      <!--        <el-option label="Shenzhen Hotel" value="Shenzhen Hotel"></el-option>-->
      <!--      </el-select>-->
      <i class="el-icon-s-home"></i>
      <!-- <el-button type="primary" size="mini"  class="addRoomButton" @click="addClick">Add Room</el-button> -->
      <el-input v-model="form.roomId" placeholder="King Bed Suite" class="selection">
        <!--        <el-option v-for="(item) in typeList"-->
        <!--                   :label="item.label"-->
        <!--                   :value='item.value'>-->
        <!--        </el-option>-->
      </el-input>
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
      <i class="el-icon-search"></i>
      <el-button type="primary" size="mini" class="addRoomButton" @click="getOrderList">Search</el-button>
      <i class="el-icon-refresh-right"></i>

      <el-button type="primary" size="mini" class="addRoomButton" @click="clearSelected">clear</el-button>
    </div>
    <!-- 表格区域 -->
    <el-table :data="tableData" style="width: 98%; min-width: 1000px"
              :cell-style="{ textAlign: 'center', background: '#f3eee7', fontSize: '18px', borderBottomColor: 'black' }"
              :header-cell-style="{ color: 'black', fontSize: '20px', fontFamily: 'nano', background: '#f3eee7', textAlign: 'center', borderBottomColor: 'black' }"
              :row-style="setRowStyle" stripe fit highlight-current-row border empty-text="No Room Added">

      <el-table-column label="orderID" width="180">
        <template slot-scope="scope">
          {{ scope.row.orderID }}
        </template>
      </el-table-column>

      <el-table-column label="userID" width="100">
        <template slot-scope="scope">

          {{ scope.row.userID }}

        </template>
      </el-table-column>

      <el-table-column label="room id" width="80">
        <template slot-scope="scope">

          {{ scope.row.roomId }}

        </template>
      </el-table-column>

      <el-table-column label="check-in date" width="160">
        <template slot-scope="scope">

          {{ scope.row.date1 }}

        </template>
      </el-table-column>

      <el-table-column label="check-out date" width="160">
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
      hotelId: '',
      tableData: [],
      pageIndex: 1,
      pageSize: 8,
      count: 5,
      form: {
        roomId: null,
        status: null,
        date1: null,
        date2: null,
      },
    }
  },
  created() {
    let params = {'token': sessionStorage.getItem('token')};
    this.$get(this.$baseUrl + '/auth/getUsernameByToken', params).then(data => {
      let resp = data.data;
      console.log(resp)
      let para = {'name': resp}
      this.$get(this.$baseUrl + '/room/room/hotel/initAdminRoom', para).then(data => {
        let resp = data.data
        console.log(resp)
        this.hotelId = resp.hotelId
        console.log(this.hotelId)
        this.getOrderList()
      })
    })
  },
  methods: {
    clearSelected() {
      this.form.roomId = null
      this.form.status = null
      this.form.date2 = null
      this.form.date1 = null
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
        hotel: this.hotelId,
        roomId: this.form.roomId,
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
            roomId: item.roomId,
            status: item.orderStatus,
            name: item.contactName,
            phone: item.contactPhone,
            email: item.contactEmail
          })
        }
        this.count = resp.length
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

.el-icon-search {
  padding-left: 10px;
  padding-right: 5px;
  width: 18px;
  height: 25px;
}

.el-icon-refresh-right {
  padding-left: 10px;
  padding-right: 5px;
  width: 18px;
  height: 25px;
}
</style>