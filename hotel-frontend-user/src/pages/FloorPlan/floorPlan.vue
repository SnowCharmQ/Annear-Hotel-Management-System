<template>
  <div class="page-main">

    <!--页面顶部的四个短语-->
    <div class="item-img">
      <el-divider></el-divider>
      <div class="top-link">
        <el-link>Hotel Information</el-link>
        <el-link>Find Reservations</el-link>
        <el-link>Property Currency</el-link>
      </div>
    </div>
    <el-divider></el-divider>

    <el-row class="card-search" :gutter="20">
      <div style="background:#f3eee7;line-height:80px;padding-left:15px;font-size:18px">
        Select a Room
      </div>
      <div class="roomList">
        <img src="../../assets/images/floorplan.jpg" style="width:1240px;"/>
        <div class="flex-row">
          <div style="width: 5px;height: 240px;position: relative;margin-top: 100px;"></div>
          <div class="roomNum1" v-for="(item, index) in 9" :key="index" @click="toOpen(index)"></div>
        </div>
        <div class="flex-row flex-row3" style="margin-top:380px">
          <div style="width: 5px;height: 240px;position: relative;margin-top: 100px;"></div>
          <div class="roomNum2" v-for="(item, index) in 9" :key="index" @click="toOpen(9 + index)"></div>
        </div>
      </div>

      <el-dropdown split-button type="primary" @command="handleCommand">
        Floor: {{ curFloor }}
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item v-for="f in this.floors" :command="f">Floor {{ f }}</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>

      <!--详细信息弹窗-->
      <el-dialog :visible.sync="dialogVisible" width="700px" title="Room Details">
          <span slot="title" class="dialog-footer"
                style="height: 50px;font-size: 32px;font-family: 'Times New Roman',serif;margin-top: 5px">
              <span style="margin-top: 10px">Room Details</span>
          </span>
        <div class="view-room">
          <div>
            <div class="room-title" style="font-size: 22px">{{ curRoomType.typeName }}</div>
            <el-breadcrumb separator="|" style="margin:10px 0;">
              <el-breadcrumb-item>{{ curRoomType.upperLimit }} Guests Max</el-breadcrumb-item>
              <el-breadcrumb-item style="margin-top: -3px">{{ curRoomType.area }} m<sup>2</sup></el-breadcrumb-item>
              <el-breadcrumb-item>Score: {{ parseFloat(curRoomType.averageScore).toFixed(1) }}</el-breadcrumb-item>
            </el-breadcrumb>
            <div>{{ curRoomType.description }}</div>
          </div>
          <br>
          <div>
<!--            <el-image-->
<!--                style="width: 260px; height: 180px"-->
<!--                :src="updatePicturePath()"-->
<!--                :preview-src-list="this.roomTypeImages[this.curRoomType.typeId]">-->
<!--            </el-image>-->
          </div>
        </div>
        <el-divider></el-divider>
        <div style="margin:10px 0;font-size: 20px;font-family: 'Times New Roman',serif">
          <span>Breakfast: <i v-if="curRoomType.breakfast===1" class="el-icon-check"></i><i
              v-if="curRoomType.breakfast!==1"
              class="el-icon-close"></i></span>
          <span> | </span>
          <span>Windows: <i v-if="curRoomType.windows===1" class="el-icon-check"></i><i v-if="curRoomType.windows!==1"
                                                                                        class="el-icon-close"></i></span>
          <span> | </span>
          <span>Television: <i v-if="curRoomType.television===1" class="el-icon-check"></i><i
              v-if="curRoomType.television!==1"
              class="el-icon-close"></i></span>
          <span> | </span>
          <span>Bathtub: <i v-if="curRoomType.bathtub===1" class="el-icon-check"></i><i v-if="curRoomType.bathtub!==1"
                                                                                        class="el-icon-close"></i></span>
          <span> | </span>
          <span>Thermos: <i v-if="curRoomType.thermos===1" class="el-icon-check"></i><i
              v-if="curRoomType.thermos!==1" class="el-icon-close"></i></span>
        </div>

        <el-divider></el-divider>
        <el-link style="font-family: 'Times New Roman',serif;font-size: 18px" @click="toComments">View Comments
        </el-link>

        <!--用户评论区-->
        <!--          <div>-->
        <!--            <div class="detail">User Comment</div>-->
        <!--            <div class="comment-flex" v-for="(item,index) in commentList" :key="index">-->
        <!--              <div class="comment-user">{{ item.name }}：</div>-->
        <!--              <div class="comment-des">-->
        <!--                <div class="comment-content">{{ item.content }}</div>-->
        <!--                <div class="time">{{ item.ctime }}</div>-->
        <!--              </div>-->
        <!--            </div>-->
        <!--          </div>-->
        <span slot="footer" class="dialog-footer"><el-button type="primary" @click="toCheckOut(curRoomType.typeId)"
                                                             class="v-btn2">Book Now</el-button></span>
      </el-dialog>

    </el-row>
  </div>
</template>

<script>
import {convertToDate} from "@/utils/utils";

export default {
  name: "floorPlan",
  data() {
    return {
      dialogVisible: false,
      curRoomType: '',
      roomTypes: [],
      floors: 0,
      curFloor: 1,
      idxMap: {1: 17, 2: 16, 3: 15, 4: 14, 5: 13, 6: 12, 7: 11, 8: 10, 9: 9, 10: 0, 11: 1, 12: 2, 13: 6, 14: 7, 15: 8},
      disables: [],
    };
  },
  methods: {
    toOpen(index) {
      if (index === 3 || index === 4 || index === 5) {
        return;
      }
      this.curRoomType = this.roomTypes[index];
      if (this.disables[index]) {
        this.dialogVisible = true;
      }
    },
    handleCommand(command) {
      this.curFloor = command;
    }
  },
  created() {
    let hotelId = this.$route.query.hotel;
    let startDate = this.$route.query.startDate;
    let endDate = this.$route.query.endDate;
    for (let i = 0; i < 16; i++) {
      this.disables.push(false);
      this.roomTypes.push({});
    }
    if (hotelId === undefined || startDate === undefined || endDate === undefined) {
      this.$router.push('404');
    } else {
      let floor = this.curFloor;
      let d1 = convertToDate(startDate)
      let d2 = convertToDate(endDate)
      this.$http({
        url: this.$http.adornUrl('/order/order/booking/bookingRoomInfo'),
        method: 'get',
        params: this.$http.adornParams({
          hotelId: hotelId,
          floor: floor,
          startDate: d1,
          endDate: d2
        })
      }).then(data => {
        if (data.data.state !== 200) {
          this.$message.error(data.data.message);
        } else {
          let result = data.data.data;
          for (let i = 0; i < result.length; i++) {
            let info = result[0];
            let floorPlanId = info.floorPlanId;
            let floorPlanIdx = this.idxMap[floorPlanId];
            this.disables[floorPlanIdx] = true;
            this.roomTypes[floorPlanIdx] = info;
          }
        }
      }).catch(err => {
        this.$message.error("Network Error");
      })
    }
  }
};
</script>

<style lang="scss" scoped>

.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}

.el-icon-arrow-down {
  font-size: 12px;
}

.page-main {
  background: #f3eee7;

  .roomList {
    background: #fff;
    padding: 20px 40px;
  }

  .item-img {
    width: 1300px;
    height: 100%;
  }

  .top-link {
    width: 500px;
    margin: 0 auto;
    text-align: center;
  }

  .top-link .el-link {
    margin: 0 15px;
  }

  .flex-row {
    display: flex;
    align-items: center;
    padding: 20px 40px;
    position: absolute;
    top: 0;
  }

  .roomNum {
    border: 3px solid #333;
    width: 70px;
    height: 110px;
    cursor: pointer;
  }

  .roomNum1 {
    // border: 3px solid #333;
    width: 127px;
    height: 240px;
    cursor: pointer;
    position: relative;
    margin-top: 100px;
  }

  .roomNum2 {
    // border: 3px solid #333;
    width: 127px;
    height: 240px;
    cursor: pointer;
    position: relative;
    margin-top: -50px;
  }

  .roomDoor {
    border-bottom: 3px solid #fff;
    width: 20px;
    margin-top: 48px;
    margin-left: 40px;
  }

  .flex-row2 .roomDoor {
    margin-top: -106px;
  }

  .roomNO {
    background: #f3dfc3;
    width: 21px;
    height: 21px;
    border-radius: 21px;
    margin: 41px auto;
    text-align: center;
    font-weight: 600;
  }

  .view-room {
    display: flex;
    justify-content: space-between;
  }

  .room-title {
    font-size: 18px;
    line-height: 28px;
    color: #333;
  }

  .detail {
    color: #333;
    font-size: 15px;
    margin-bottom: 15px;
  }

  .dline::before {
    content: "●";
    margin-right: 5px;
  }

  .dline {
    line-height: 25px;
  }

  .v-btn2 {
    border: 1px solid black;
    background: black;
    text-align: center;
    color: #fff;
    margin-top: 10px;
    cursor: pointer;
  }

  .comment-flex {
    display: flex;
    border-bottom: 1px solid #ddd;
    margin-top: 10px;
    padding-bottom: 10px;
  }

  .comment-user {
    color: #333;
    font-weight: 700;
    font-size: 14px;
  }

  .comment-content {
    color: #666;
    margin-bottom: 10px;
  }

  .time {
    color: #999;
    font-size: 13px;
  }
}
</style>
<style scoped>
.page-main >>> .el-dialog__header {
  padding: 20px 20px 10px;
}
</style>
