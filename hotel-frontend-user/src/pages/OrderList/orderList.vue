<template>
  <div class="page-main">
    <div style="background:#f3eee7;line-height:80px;padding-left:15px;font-size:22px">View All Your Orders In Our Hotels
    </div>

    <el-row class="card-search" :gutter="20" style="width:1250px;margin:20px auto;">
      <el-col :span="17" style="background:#fff; padding: 0 0;margin-left: 120px">
        <div style="clear:both;" v-if="reload">
          <el-col :span="24" v-for="(item, idx) in orderList" :key="idx" class="room-list">
            <div class="room-right">
              <div style="font-size:14px">OrderId: {{ item.orderId }}</div>
              <div style="font-weight:600;">Hotel: {{ item.hotelName }}</div>
              <div style="font-weight:600;">RoomType: {{ item.typeName }}</div>
              <div style="font-weight:600;">Room: {{ item.roomNumber }}</div>
              <el-breadcrumb separator="|" style="margin:10px 0;">
                <el-breadcrumb-item>Start Time: {{ item.startDate }}</el-breadcrumb-item>
                <el-breadcrumb-item>Leave Time: {{ item.endDate }}</el-breadcrumb-item>
              </el-breadcrumb>
              <el-breadcrumb separator="|" style="margin:10px 0;">
                <el-breadcrumb-item>Origin Money: ${{ item.originMoney }}</el-breadcrumb-item>
                <el-breadcrumb-item>After Discount: ${{ item.afterDiscount }}</el-breadcrumb-item>
              </el-breadcrumb>
              <div>Order Status: {{ statusMap[item.orderStatus] }}</div>
              <div>Additional Information: {{item.additional}}</div>
              <el-button v-if="item.score === null" type="text" @click="showCommentPage(item)">Give Comments</el-button>
              <div v-if="item.score !== null">Score: {{item.score}}</div>
            </div>
          </el-col>
        </div>
        <el-dialog :visible.sync="dialogVisible" center width="50%">
          <div style="font-size: 18px;line-height: 28px;color: #333;">Give your comments</div>
          <div style="margin:10px 0">
            <span> Thanks for sharing your experience staying in Annear</span>
          </div>
          <div style="display: flex;flex-direction: column;align-items: center;justify-content: center;">
            <br>
            <el-form :inline="true">
              <el-select v-model="score" placeholder="Score" style="width: 80px">
                <el-option label="1" value="1"></el-option>
                <el-option label="2" value="2"></el-option>
                <el-option label="3" value="3"></el-option>
                <el-option label="4" value="4"></el-option>
                <el-option label="5" value="5"></el-option>
              </el-select>
              <br><br>
              <el-input v-model="comment"
                        style="width: 500px"
                        maxlength="50"
                        show-word-limit
                        placeholder="your comments">
              </el-input>
              <br><br>
              <el-upload
                  class="upload-demo"
                  action="https://jsonplaceholder.typicode.com/posts/"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :http-request="uploadPicture"
                  :file-list="fileList"
                  :limit=1
                  list-type="picture">
                <el-button size="small" type="primary">Upload Picture</el-button>
                <div slot="tip" class="el-upload__tip">please upload .one jpg/png file</div>
              </el-upload>
              <br>
              <el-upload
                  class="upload-demo"
                  action="https://jsonplaceholder.typicode.com/posts/"
                  :http-request="uploadVideo"
                  :limit=1
                  :file-list="videoList">
                <el-button size="small" type="primary">Upload Video</el-button>
                <div slot="tip" class="el-upload__tip">please upload one .mp4 file</div>
              </el-upload>
            </el-form>
          </div>
          <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submitComment()">Submit</el-button>
      </span>
        </el-dialog>
      </el-col>
    </el-row>

    <div class="pagination">
      <span style="margin-right:-16px;font-size: 20px;font-family: 'Times New Roman',serif; color: #000000;">Total Records: {{
          totalCount
        }} &ensp;&ensp;&ensp; </span>
    </div>
  </div>
</template>

<script>
import cookie from "js-cookie";
import axios from "axios";
import {convertToDate} from "@/utils/utils";

export default {
  name: "orderList",
  data() {
    return {
      orderList: [],
      pageIndex: 1,
      pageSize: 10,
      totalCount: 50,
      dialogVisible: false,
      score: 5,
      comment: '',
      fileList: [],
      videoList: [],
      tempUrl: '',
      selectedOrderId: '',
      statusMap: {0: 'unpaid', 1: 'paid', 2: 'be canceled', 3: 'be evaluated', 4: 'finished'},
      curComment: '',
      reload: true
    }
  },
  methods: {
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getOrderList();
    },
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getOrderList();
    },
    getOrderList() {
      let token = cookie.get('token');
      this.$http({
        url: this.$http.adornUrl('/order/order/order/getUserOrders'),
        method: 'get',
        params: this.$http.adornParams({
          token: token
        })
      }).then(data => {
        if (data.data.state === 200) {
          this.orderList = data.data.data.list;
          this.totalCount = this.orderList.length;
        } else {
          this.$message.error(data.data.message);
        }
      })
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    uploadPicture(file) {
      let picture = file.file
      let formData = new FormData();
      formData.append('file', picture)
      formData.append('orderId', this.selectedOrderId)
      let url = this.$http.adornUrl('/auth/uploadPicture')
      axios.post(url, formData, {
        headers: {'Content-Type': 'multipart/form-data'}
      }).then(data => {
        let pictureUrl = data.data.data
        this.fileList.push({name: picture.name, url: pictureUrl})
      });
    },
    uploadVideo(file) {
      let video = file.file
      let formData = new FormData();
      formData.append('file', video)
      formData.append('orderId', this.selectedOrderId)
      let url = this.$http.adornUrl('/auth/uploadVideo')
      axios.post(url, formData, {
        headers: {'Content-Type': 'multipart/form-data'}
      }).then(data => {
        let videoUrl = data.data.data
        this.videoList.push({name: video.name, url: videoUrl})
      });
    },
    showCommentPage(item) {
      this.dialogVisible = true;
      this.curComment = item;
    },
    submitComment() {
      this.dialogVisible = false;
      let date = new Date();
      let str = convertToDate(date);
      this.$http({
        url: this.$http.adornUrl('/order/order/ordercomments/commentOrder'),
        method: 'post',
        data: this.$http.adornData({
          'orderId': this.curComment.orderId,
          'typeId': this.curComment.typeId,
          'comments': this.comment,
          'commentTime': str,
          'picture': this.fileList[0] === undefined ? '' : this.fileList[0].url,
          'video': this.videoList[0] === undefined ? '' : this.videoList[0].url,
          'score': this.score
        })
      }).then(data => {
        if (data.data.state === 200) {
          this.$message.success("Successfully Commented")
          location.reload();
        } else {
          this.$message.error(data.data.message);
        }
      }).catch(err => {
        this.$message.error("Network Error")
      })
    }
  },
  created() {
    this.getOrderList();
  },
};
</script>

<style lang="scss" scoped>

.room-right {
  padding-left: 20px;
  line-height: 30px;
  color: #444;
}

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
