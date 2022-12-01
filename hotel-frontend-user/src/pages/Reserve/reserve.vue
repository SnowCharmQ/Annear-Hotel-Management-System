<template>
  <div class="page-main">
    <!--ANNEAR-->

    <!--背景图和左下角的text part-->
    <el-carousel height="430px">
      <el-carousel-item v-for="item in this.images" :key="item">
        <img
            :src="item"
            alt="Network Error"
            class="item-img">
      </el-carousel-item>
      <div class="carousel-box" style="height: 160px;">
        <div style="font-size:25px;">{{ this.hotelName }} &ensp; <i @click="toChat" class="el-icon-chat-dot-round"
                                                                    style="cursor: pointer"></i></div>
        <div style="display:flex;margin-top:10px;">
          <div>
            <div><i class="el-icon-school"></i></div>
            <div style="margin-top:22px;"><i class="el-icon-phone"></i></div>
            <div style="margin-top:22px;"><i class="el-icon-message"></i></div>
          </div>
          <div>
            <div>{{ this.province }} {{ this.city }} <br> {{ this.district }} {{ this.detailAddress }}</div>
            <div style="margin-top:20px;">{{ this.telephone }}</div>
            <div style="margin-top:20px;">{{ this.email }}</div>
          </div>
        </div>
      </div>
    </el-carousel>

    <el-row class="card-search" :gutter="20" style="width:1250px;margin:20px auto;">
      <el-col :span="17" style="background:#fff; padding: 0 0;">

        <!--选择客人数量-->
        <el-col :span="8" class="btn-item">
          <el-popover placement="bottom" width="320" trigger="click">
            <div style="padding:0 15px;">
              <div class="title-popover">Select Guests</div>
              <div style="padding:15px 0;" class="add-form">
                <el-form ref="form" label-width="80px">
                  <el-form-item label="Guests">
                    <el-input-number v-model="count1" :min="0"></el-input-number>
                  </el-form-item>
                  <div style="line-height:50px;border-top:1px solid #ddd;text-align:right;">
                    <el-button type="info" @click="updateGuests">Apply</el-button>
                  </div>
                </el-form>
              </div>
            </div>
            <div class="flex-row" slot="reference">
              <i class="el-icon-user-solid user-icon"></i>
              <div>
                <div class="row-label">Guests</div>
                <div>{{ count2 }} Guests</div>
              </div>
            </div>
          </el-popover>
        </el-col>

        <!--check in按钮-->
        <el-col :span="8" class="btn-item">
          <div class="flex-row" @click="showDate">
            <i class="el-icon-date user-icon"></i>
            <div>
              <div class="row-label">Check-In</div>
              <div>{{ date1.toDateString() }}</div>
            </div>
          </div>
        </el-col>

        <!--check out按钮-->
        <el-col :span="8" class="btn-item">
          <div class="flex-row" @click="showDate">
            <i class="el-icon-date user-icon"></i>
            <div>
              <div class="row-label">Check-Out</div>
              <div>{{ date2.toDateString() }}</div>
            </div>
          </div>
        </el-col>

        <!--日历控件-->
        <el-col :span="24" v-show="showCheck">
          <div class="flex-row1">
            <el-calendar v-model="date3"/>
            <el-calendar v-model="date4"/>
          </div>
          <div class="date-btn">
            <el-link class="date-item" @click="showCheck = false;">Cancel</el-link>
            <el-button class="date-item" type="info" style="background:#333;" @click="updateDates">Search</el-button>
          </div>
        </el-col>

        <div style="background:#f3eee7;line-height:80px;padding-left:15px;font-size:22px">Select a Room</div>
        <div class="hotel-select">
          <div class="flex-row" style="margin-top:15px;">
						<span class="el-dropdown-link">
							<div class="flex-row" style="cursor: pointer;margin-right:20px;" @click="toFloorPlan">
								<div>
									<div>View</div>
									<div class="row-label">Floor Plan</div>
								</div>
							</div>
						</span>


            <!--filters-->
            <el-popover placement="top-end" width="800" trigger="click" style="cursor: pointer;z-index: 10">
              <div style="padding:0 15px;">
                <div class="title-popover" style="font-size:18px;">Filters</div>

                <el-col :span="12">
                  <div style="display:flex;justify-content: space-between;font-size: 18px;line-height: 50px;;">
                    <div>Sort By</div>
                  </div>
                  <div style="line-height:30px;">
                    <div>
                      <el-radio v-model="checked1" label="default">Default</el-radio>
                      <br>
                      <el-radio v-model="checked1" label="score">Score</el-radio>
                      <br>
                      <el-radio v-model="checked1" label="price">Price</el-radio>
                    </div>
                    <br>
                    <div>
                      <el-radio v-model="checked2" label="hl">From Highest to Lowest</el-radio>
                      <el-radio v-model="checked2" label="lh">From Lowest to Highest</el-radio>
                    </div>
                  </div>
                </el-col>

                <el-col :span="12">
                  <div style="display:flex;justify-content: space-between;font-size: 18px;line-height: 50px;;">
                    <div>Price</div>
                  </div>
                  <div style="padding:15px;justify-content: space-between;">
                    <el-slider v-model="value" range show-stops :min="100" :max="10000"/>
                    <br>
                    <div style="display: flex;">
                      <div class="price-box">
                        <div>Price range from</div>
                        <div>{{ value[0] }}</div>
                      </div>
                      <div style="width:20px;">---</div>
                      <div class="price-box">
                        <div>Price range from</div>
                        <div>{{ value[1] }}</div>
                      </div>
                    </div>
                  </div>
                  <br>
                </el-col>

                <el-col :span="24">
                  <el-divider></el-divider>
                </el-col>

                <el-col :span="24">
                  <div style="display:flex;justify-content: space-between;font-size: 18px;line-height: 50px;;">
                    <div>Related Supporting Infrastructure</div>
                  </div>
                  <div style="line-height:30px;">
                    <div>
                      <el-checkbox v-model="checked3">Breakfast</el-checkbox>
                      <el-checkbox v-model="checked4">Windows</el-checkbox>
                      <el-checkbox v-model="checked5">Television</el-checkbox>
                      <el-checkbox v-model="checked6">Bathtub</el-checkbox>
                      <el-checkbox v-model="checked7">Thermos</el-checkbox>
                    </div>
                  </div>
                  <br>
                </el-col>

                <el-col :span="24" style="line-height:50px;border-top:1px solid #ddd;text-align:right;">
                  <el-button type="info" style="background:#333;" @click="search">Apply</el-button>
                </el-col>

              </div><!--filters end-->

              <div class="flex-row" slot="reference"
                   style="margin-left:20px;background: #82847f;margin-right: 20px;padding:8px 2px;color: #fff;">
                <div>
                  <div class="row-label">Hide Filters</div>
                </div>
                <i class="el-icon-caret-bottom user-icon" style="font-size:18px;"></i>
              </div>

            </el-popover>
          </div>
        </div>

        <!--每个酒店的具体信息--> <!------------>
        <div style="clear:both;">
          <el-col :span="24" v-for="(item, index) in roomTypes" v-if="item.num > 0" :key="index" class="room-list">
            <img :src="roomTypeImages[item.typeId][0]" class="image">
            <div class="room-right">
              <div class="card-name" style="font-size: 22px">{{ hotelName }}</div>
              <div style="font-weight:600;font-size: 20px">{{ item.typeName }}</div>
              <el-breadcrumb separator="|" style="margin:10px 0;">
                <el-breadcrumb-item>{{ item.upperLimit }} Guests Max</el-breadcrumb-item>
                <el-breadcrumb-item style="margin-top: -3px">{{ item.area }} m<sup>2</sup></el-breadcrumb-item>
                <el-breadcrumb-item>Score: {{ parseFloat(item.averageScore).toFixed(1) }}</el-breadcrumb-item>
              </el-breadcrumb>
              <div>{{ item.description }}</div>
              <el-link @click="openDialog(item)">Room details</el-link>
              <div style="width: 560px">
                <el-divider></el-divider>

                <div class="flex-row2" style="margin-top:-10px;">
                  <div style="font-size: 14px">Standard Daily Rate</div>
                  <div style="font-weight:600;font-size: 18px">￥{{ item.price }}</div>
                </div>
                <div class="flex-row2" style="font-size:15px;color: #666;">
                  <div>{{ item.detail }}</div>
                  <div>Per Night</div>
                </div>
                <div style="text-align:right;">
                  <el-button type="info" style="background:black;color:#fff;margin-right: -5px"
                             @click="toCheckOut(item.typeId)">Book Now
                  </el-button>
                </div>
              </div>

            </div>
          </el-col>
        </div>

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
              <el-image
                  style="width: 260px; height: 180px"
                  :src="updatePicturePath()"
                  :preview-src-list="this.roomTypeImages[this.curRoomType.typeId]">
              </el-image>
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
      </el-col>

      <!--右侧信息-->
      <el-col :span="7" class="right-box">
        <div style="font-size:20px;line-height:45px;font-weight:600;">Tips for booking</div>
        <el-col :span="12" style="margin-top:20px;">
          <div style="font-weight:600;">Check-in</div>
          <div>After 2:00 PM</div>
        </el-col>
        <el-col :span="12" style="margin-top:20px;">
          <div style="font-weight:600;">Check-out</div>
          <div>Before 12:00 PM</div>
        </el-col>
        <el-col :span="24" style="padding-top:20px;">
          <div>Hope you enjoy your stay at Annear :)</div>
          <!--          <div>2 Adults</div>-->
        </el-col>
        <!--        <el-col :span="24"><el-divider></el-divider></el-col>-->
        <!--        <el-col :span="24" style="display:flex;justify-content: space-between;">-->
        <!--          <div style="font-weight:600;">Total:</div>-->
        <!--          <div style="font-weight:600;">A$0.00</div>-->
        <!--        </el-col>-->
        <el-col :span="24">
          <el-divider></el-divider>
        </el-col>
        <el-col :span="24">
          <div style='font-size:20px;margin-bottom:20px;color:black;'>Book direct for peace of mind</div>

          <div>
            <div class="right-flex">
              <div class="icon-img"><img src="../../assets/images/1.jpg"/></div>
              <div>24/7 Dedicated Global Reservation Team offering personalised service</div>
            </div>
            <div class="right-flex">
              <div class="icon-img"><img src="../../assets/images/2.jpg"/></div>
              <div style="margin-top:5px;">Exclusive offers with inspiring itineraries, only when booking direct</div>
            </div>
            <div class="right-flex">
              <div class="icon-img"><img src="../../assets/images/3.jpg"/></div>
              <div style="margin-top:5px;">A range of unique experiences to enhance your stay, accessible online</div>
            </div>
            <div class="right-flex">
              <div class="icon-img"><img src="../../assets/images/4.jpg"/></div>
              <div style="margin-top:5px;">Awe-inspiring locations with 34 resorts and hotels in 20 locations</div>
            </div>
          </div>

        </el-col>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import cookie from "js-cookie";

export default {
  name: "reserve",
  data() {
    return {
      input3: '',
      selCode: '1',
      value: [100, 10000],
      filterText: '',
      hotelName: 'Annear Hotel',
      province: '',
      city: '',
      district: '',
      detailAddress: '',
      telephone: '',
      email: '',
      images: [],
      date1: new Date(),
      date2: this.generateTomorrow(),
      date3: '',
      date4: '',
      showCheck: false,
      checked1: 'default',
      checked2: 'hl',
      checked3: false,
      checked4: false,
      checked5: false,
      checked6: false,
      checked7: false,
      checked8: false,
      checked9: false,
      checked10: false,
      checked11: false,
      count1: 0,
      count2: 0,
      roomTypes: {},
      roomTypeImages: [],
      curRoomType: {},
      dialogVisible: false,
    }
  },
  methods: {
    // 显示日历
    showDate() {
      this.showCheck = true;
    },
    toCheckOut(typeId) {
      let token = cookie.get('token');
      let startDate = this.date1;
      let endDate = this.date2;
      let roomTypeId = typeId;
      let hotelId = this.$route.query.hotel;
      if (token === undefined || token === '') {
        this.$message.info("You Have Not Login In");
      }
      this.$router.push('order?startDate=' + startDate + '&endDate=' + endDate + '&roomType=' + roomTypeId + '&hotel=' + hotelId);
    },
    toFloorPlan() {
      let startDate = this.date1;
      let endDate = this.date2;
      let hotelId = this.$route.query.hotel;
      this.$router.push('floorPlan?hotel=' + hotelId + "&startDate=" + startDate + "&endDate=" + endDate);
    },
    updateGuests() {
      this.count2 = this.count1;
      this.search();
    },
    updateDates() {
      if (this.date3 !== undefined && this.date3 !== "") {
        this.date1 = this.date3;
      }
      if (this.date4 !== undefined && this.date4 !== "") {
        this.date2 = this.date4;
      }
      this.showCheck = false;
      this.search();
    },
    generateTomorrow() {
      const today = new Date();
      const current = today.getDate();
      let finalDate = new Date();
      finalDate.setDate(current + 1);
      return finalDate;
    },
    search() {
      let hotelId = this.$route.query.hotel;
      this.$http({
        url: this.$http.adornUrl('/room/room/roomtype/search'),
        method: 'get',
        params: {
          hotelId: hotelId,
          guests: this.count2,
          startDate: this.date1,
          endDate: this.date2,
          sortBy: this.checked1,
          reversed: this.checked2 === 'hl',
          lowest: this.value[0],
          highest: this.value[1],
          breakfast: this.checked3,
          windows: this.checked4,
          television: this.checked5,
          bathtub: this.checked6,
          thermos: this.checked7
        }
      }).then(data => {
        let obj = data.data.data;
        this.roomTypes = obj.roomTypes;
        this.roomTypeImages = obj.roomTypeImages;
      }).catch(err => {
        this.$message.error("Network Error");
      })
    },
    openDialog(item) {
      this.curRoomType = item;
      this.dialogVisible = true;
    },
    updatePicturePath() {
      let paths = this.roomTypeImages[this.curRoomType.typeId];
      for (let p in paths) {
        return paths[p];
      }
    },
    toChat() {
      this.$router.push('chat?hotel=' + this.hotelName);
    },
    toComments() {
      this.$router.push('comments?roomType=' + this.curRoomType.typeId);
    }
  },
  created() {
    let hotelId = this.$route.query.hotel;
    if (hotelId === undefined || hotelId === '') {
      this.$router.push('404');
    } else {
      let url = '/room/room/hotel/initReserve';
      this.$http({
        url: this.$http.adornUrl(url),
        method: 'get',
        params: this.$http.adornParams({
          hotelId: hotelId,
          today: new Date(),
          tomorrow: this.generateTomorrow()
        })
      }).then(data => {
        let obj = data.data.data;
        this.hotelName = obj.hotelName;
        this.province = obj.province;
        this.city = obj.city;
        this.district = obj.district;
        this.detailAddress = obj.detailAddress;
        this.telephone = obj.telephone;
        this.email = obj.email;
        this.images = obj.images;
        this.roomTypes = obj.roomTypes;
        this.roomTypeImages = obj.roomTypeImages;
      }).catch(err => {
        this.$message.error("Network Error");
      })
    }
  }
};
</script>

<style lang="scss" scoped>
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
