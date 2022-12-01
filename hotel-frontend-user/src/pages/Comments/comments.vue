<template>
  <div class="page-main">
    <div style="background:#f3eee7;line-height:80px;padding-left:15px;font-size:22px">View The Comments Of Our Hotels
    </div>

    <el-row class="card-search" :gutter="20" style="width:1250px;margin:20px auto;">
      <el-col :span="17" style="background:#fff; padding: 0 0;margin-left: 120px">
        <div style="clear:both;">
          <el-col :span="24" v-for="(comment, idx) in comments" :key="idx" class="room-list">
            <img :src="comment.picture" v-if="comment.picture" class="image">
<!--            <vam-video-->
<!--                :properties="videoOption.properties"-->
<!--                :videoStyle="videoOption.videoStyle"-->
<!--                :controlsConfig="videoOption.controlsConfig"-->
<!--                @play="playVideo"-->
<!--                @canplay="canplayVideo"-->
<!--                @pause="pauseVideo"-->
<!--            ></vam-video>-->
            <div class="room-right">
              <div class="card-name" style="font-size: 22px">{{ comment.hotelName }}</div>
              <div style="font-weight:600;font-size: 20px">{{ comment.typeName }}</div>
              <br>
              <div>{{ comment.comments }}</div>
              <div>Comment Time: {{comment.commentTime}}</div>
              <div>Score: {{parseFloat(comment.score).toFixed(1)}}</div>
            </div>
          </el-col>
        </div>
      </el-col>
    </el-row>

    <div class="pagination">
      <span style="margin-right:-16px;font-size: 20px;font-family: 'Times New Roman',serif; color: #000000;">Total Records: {{ totalCount }} &ensp;&ensp;&ensp;  Current Page:</span>
      <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="pageSize"
          :total="totalCount"
          layout="jumper, total, sizes, prev, pager, next, ->"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "comments",
  data() {
    return {
      comments: [],
      pageIndex: 1,
      pageSize: 10,
      totalCount: 50
    }
  },
  methods: {
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getCommentList();
    },
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getCommentList();
    },
    getCommentList() {
      let params = {};
      let typeId = this.$route.query.roomType;
      Object.assign(params, {
        typeId: typeId,
        page: this.pageIndex,
        limit: this.pageSize
      })
      this.$http({
        url: this.$http.adornUrl('/order/order/ordercomments/getComments'),
        method: 'get',
        params: this.$http.adornParams(params)
      }).then(data => {
          let result = data.data.data;
          this.comments = result.list;
          this.totalCount = result.totalCount;
      }).catch(err => {
        this.$message.error("Network Error");
      })
    }
  },
  created() {
    this.getCommentList();
  }
};
</script>

<style lang="scss" scoped>

//  分页 前文字+分页
.pagination {
  margin-top: 23px;
  margin-left: -80px;
  text-align: center;
  padding: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.page-main {
  background: #f3eee7;

  .item-img {
    width: 1300px;
    height: 100%;
  }

  .top-link {
    width: 500px;
    margin: 0 auto;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .top-link .el-link {
    margin: 0 15px;
  }

  .flex-row {
    display: flex;
    align-items: center;
  }

  .user-icon {
    font-size: 30px;
    margin-right: 5px;
  }

  .card-search {
    background: #f3eee7;
  }

  .btn-item {
    border: 1px solid #ddd;
    padding: 12px 0;
    cursor: pointer;
    background: #fff;
  }

  .btn-item:hover {
    background: #f3eee7;
  }

  .row-label {
    font-weight: 600;
    font-size: 16px;
  }

  .flex-row1 {
    display: flex;
  }

  .image {
    height: 230px;
    width: 100%;
  }

  .card-name {
    font-size: 18px;
  }

  .card-detail {
    margin-top: 25px;
    cursor: pointer;
  }

  .hotel-select {
    height: 70px;
    float: right;
  }

  .v-btn1 {
    border: 1px solid #333;
    text-align: center;
    line-height: 45px;
    width: 100%;
    margin-top: 10px;
    cursor: pointer;
  }

  .v-btn2 {
    border: 1px solid black;
    background: black;
    text-align: center;
    line-height: 25px;
    width: 130px;
    color: #fff;
    //margin-top: 10px;
    cursor: pointer;
  }
}

.select-item {
  line-height: 34px;
  margin-right: 10px;
  padding-left: 10px;
  cursor: pointer;
}

.select-item:hover {
  background: rgba(130, 132, 127, 1);
  color: #fff;
}

.carousel-box {
  z-index: 5;
  position: absolute;
  bottom: 0;
  left: 30px;
  height: 120px;
  width: 320px;
  color: #666;
  padding: 15px;
  background: rgba(255, 255, 255, 0.7);
}

.carousel-box [class^=el-icon-] {
  font-size: 24px;
}

.date-btn {
  display: flex;
  justify-content: right;
  background: #fff;
  padding-bottom: 15px;
}

.date-item {
  margin-right: 20px;
}

.price-box {
  border: 1px solid #666;
  padding: 5px 15px;
}

.right-box {
  background: #fff;
  padding: 15px;
  border-left: 15px solid rgb(243, 238, 231);
}

.rate-sel {
  text-align: right;
  background: #fff;
  line-height: 30px;
  cursor: pointer;
}

.input-with-select {
  width: 300px;
}

.room-list {
  display: flex;
  border-bottom: 15px solid #f3eee7;
  padding: 15px 0;
}

.room-list img {
  width: 250px !important;
  height: 175px !important;
}

.room-right {
  padding-left: 20px;
  line-height: 30px;
  color: #444;
}

.flex-row2 {
  display: flex;
  justify-content: space-between;
}

.right-flex {
  display: flex;
  font-size: 13px;
  line-height: 22px;
  align-items: center;
  margin-bottom: 10px;
}

.icon-img img {
  width: 40px;
  vertical-align: middle;
}

</style>
