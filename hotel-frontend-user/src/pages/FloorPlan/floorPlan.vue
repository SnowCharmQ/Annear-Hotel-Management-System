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
          <div class="roomNum2" v-for="(item, index) in 9" :key="index" @click="toOpen(8 + index)"></div>
        </div>
      </div>

      <el-dropdown split-button type="primary" @command="handleCommand">
        Floor: {{curFloor}}
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item v-for="f in this.floors" :command="f">Floor {{f}}</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>

      <!--弹窗-->
      <!--      <el-dialog :visible.sync="dialogVisible" width="700px" title="Book">-->
      <!--        <div class="view-room">-->
      <!--          <div>-->
      <!--            <div class="room-title">{{ curRow.name }}</div>-->
      <!--            <div style="margin:10px 0">-->
      <!--              <span>{{ curRow.twin }} Twin</span>-->
      <!--              <span> | </span>-->
      <!--              <span>{{ curRow.area }}</span>-->
      <!--            </div>-->
      <!--            <div>Room Amenities</div>-->
      <!--          </div>-->
      <!--          <div>-->
      <!--            <el-image-->
      <!--                style="width: 260px; height: 180px"-->
      <!--                :src="curRow.img"-->
      <!--                :preview-src-list="[curRow.img]">-->
      <!--            </el-image>-->
      <!--          </div>-->
      <!--        </div>-->
      <!--        <el-divider></el-divider>-->

      <!--        <div>-->
      <!--          <div class="detail">{{ curRow.detail }}</div>-->
      <!--          <div class="dline">{{ curRow.d1 }}</div>-->
      <!--          <div class="dline">{{ curRow.d2 }}</div>-->
      <!--          <div class="dline">{{ curRow.d3 }}</div>-->
      <!--          <div class="dline">{{ curRow.d4 }}</div>-->
      <!--          <div class="dline">{{ curRow.d5 }}</div>-->
      <!--          <div class="dline">{{ curRow.d6 }}</div>-->
      <!--        </div>-->
      <!--        <el-divider></el-divider>-->

      <!--        <span slot="footer" class="dialog-footer">-->
      <!--          <el-button type="primary" @click="dialogVisible = false" class="v-btn2">Book Now</el-button>-->
      <!--        </span>-->
      <!--      </el-dialog>-->

    </el-row>
  </div>
</template>

<script>
export default {
  name: "floorPlan",
  data() {
    return {
      dialogVisible: false,
      roomType: '',
      roomTypes: [],
      floors: 3,
      curFloor: 1
    };
  },
  methods: {
    toOpen(index) {
      if (index === 3 || index === 4 || index === 5) {
        return;
      }
      this.roomType = this.roomTypes[index]
      this.dialogVisible = true;
    },
    handleCommand(command) {
      this.curFloor = command;
    }
  },
  created() {

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
