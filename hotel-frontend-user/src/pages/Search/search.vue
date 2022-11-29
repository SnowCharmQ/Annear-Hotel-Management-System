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

      <!--选择guests数量-->
      <el-col :span="6" class="btn-item">
        <el-popover placement="bottom" width="320" trigger="click">

          <div style="padding:0 15px;">
            <div class="title-popover">Select Guests</div>
            <div style="padding:15px 0;" class="add-form">
              <el-form ref="form" label-width="80px">
                <el-form-item label="Guests">
                  <el-input-number v-model="count1" :min="0"></el-input-number>
                </el-form-item>
                <div style="line-height:50px;border-top:1px solid #ddd;text-align:right;">
                  <el-button type="info" @click="updateGuest">Apply</el-button>
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

      <!--选择check-in日期-->
      <el-col :span="6" class="btn-item">
        <div class="flex-row" @click="showDate"><i class="el-icon-date user-icon"></i>
          <div>
            <div class="row-label">Check-In</div>
            <div>{{ date1 }}</div>
          </div>
        </div>
      </el-col>

      <!--选择check-out日期-->
      <el-col :span="6" class="btn-item">
        <div class="flex-row" @click="showDate">
          <i class="el-icon-date user-icon"></i>
          <div>
            <div class="row-label">Check-Out</div>
            <div>{{ date2 }}</div>
          </div>
        </div>
      </el-col>

      <!--选择location-->
      <el-col :span="6" class="btn-item">
        <el-dropdown>

					<span class="el-dropdown-link">
						<div class="flex-row"><i class="el-icon-location user-icon"></i>
              <div>
								<div class="row-label">Location</div>
								<div>{{ curSel }}</div>
							</div>
						</div>
					</span>

          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-for="item in this.locations" @click.native="handleSel(item)">
              <div class="title-name">{{ item.province }}</div>
              <div class="desc-name">{{ item.city }}</div>
            </el-dropdown-item>
          </el-dropdown-menu>

        </el-dropdown>
      </el-col>

      <!--日历控件-->
      <el-col :span="24" v-show="showCheck">
        <div class="flex-row1">
          <el-calendar v-model="date3"/>
          <el-calendar v-model="date4"/>
        </div>
        <div class="date-btn" style="background:#fff;line-height:60px;text-align:right;">
          <el-link class="date-item" @click="showCheck = false;">Cancel</el-link>
          <el-button class="date-item" type="info" style="background:#333;margin:0 15px;">
            Search
          </el-button>
        </div>
      </el-col>

      <div style="background:#f3eee7;line-height:80px;padding-left:15px;font-size:18px">Select a Hotel</div>

      <!--hotel选择部分-->
      <div class="hotel-select">
        <div class="flex-row" style="margin-top:15px;">

          <!--下拉菜单hide filters的弹出框-->
          <el-popover placement="bottom" width="800" trigger="click" style="cursor: pointer">
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
                    <el-radio v-model="checked1" label="name">Name</el-radio>
                    <br>
                    <el-radio v-model="checked1" label="price">Average Price</el-radio>
                  </div>
                  <br>
                  <div>
                    <el-radio v-model="checked2" label="hl">From Highest to Lowest</el-radio>
                    <el-radio v-model="checked2" label="lh">From Lowest to Highest</el-radio>
                  </div>
                </div>
              </el-col>
            </div>
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
                  <el-checkbox v-model="checked3">Dining Room</el-checkbox>
                  <el-checkbox v-model="checked4">Parking Lot</el-checkbox>
                  <br>
                  <el-checkbox v-model="checked5">Spa</el-checkbox>
                  <el-checkbox v-model="checked6">Bar</el-checkbox>
                  <el-checkbox v-model="checked7">Gym</el-checkbox>
                  <el-checkbox v-model="checked8">Chess Room</el-checkbox>
                  <el-checkbox v-model="checked9">Swimming Pool</el-checkbox>
                </div>
              </div>
              <br>
            </el-col>

            <el-col :span="24" style="line-height:50px;border-top:1px solid #ddd;text-align:right;">
              <el-button type="info" style="background:#333;" @click="search">Apply</el-button>
            </el-col>

            <!--下拉菜单hide filters-->
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

      <!--每个酒店的展示部分-->
      <div style="clear:both;">
        <el-col :span="8" v-for="item in hotels" :key="item.hotelName">
          <el-card :body-style="{ padding: '15px' }">
            <el-image :src="item.hotelPicture"
                      :preview-src-list="[item.hotelPicture]"
                      class="image"></el-image>
            <div style="padding: 14px;">
              <div class="card-name">{{ item.hotelName }}</div>
              <div class="card-addr">{{ item.province }}, {{ item.city }}, {{ item.district }},
                {{ item.detailAddress }}
              </div>
              <br>
              <div class="card-detail" @click="openDialog(item)" style="font-size: 16px">Detail</div>
              &ensp;
              <el-image
                  @click="collectHotel(item.hotelId, item.isCollect)"
                  v-if="isLogin"
                  style="width:30px;height:30px;cursor:pointer;"
                  :src="item.isCollect?require('../../assets/images/red-heart.png'):require('../../assets/images/heart.png')"
              ></el-image>
              &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
              <i class="el-icon-star-on" style="display: inline" v-for="_ in item.starLevel"></i>
              <el-divider ></el-divider>
              <div style="text-align:center;color:#666;">
                <div>Average <span style="font-weight:700;font-size:17px;">￥{{ item.averagePrice }}</span></div>
                <div>Per Night</div>
              </div>
              <div class="v-btn2" @click="toHotel(item.hotelId)">View Hotel</div>
            </div>
          </el-card>
        </el-col>
      </div>

      <el-dialog :visible.sync="dialogVisible" width="700px">
        <template slot="title" style="margin-top: 10px">
          <div style="font-size: 28px;font-family: 'Times New Roman',serif;">{{ curHotel.hotelName }}</div>
          <i class="el-icon-star-on" style="width: 20px;height: 20px" v-for="_ in curHotel.starLevel"></i>
        </template>
        <el-image
            style="width: 660px; height: 400px"
            :src="curHotel.hotelPicture"
            :preview-src-list="[curHotel.hotelPicture]">
        </el-image>
        <div style="margin:10px 0;font-size: 20px;font-family: 'Times New Roman',serif">
          <span>{{ curHotel.province }}</span>
          <span> | </span>
          <span>{{ curHotel.city }}</span>
          <span> | </span>
          <span>{{ curHotel.district }}</span>
          <span> | </span>
          <span>{{ curHotel.detailAddress }}</span>
        </div>
        <br>
        <div style="font-size: 18px;font-family: 'Times New Roman',serif">
          <i class="el-icon-phone">&ensp;{{ curHotel.telephone }}</i>
          <br>
          <i class="el-icon-message">&ensp;{{ curHotel.email }}</i>
        </div>
        <br>
        <div style="font-size: 18px;font-family: 'Times New Roman',serif">
          {{ curHotel.description }}
        </div>
        <el-divider style="height: 5px"></el-divider>
        <div style="margin:10px 0;font-size: 20px;font-family: 'Times New Roman',serif">
          <span>Dining Room: <i v-if="curHotel.diningRoom===1" class="el-icon-check"></i></span>
          <span> | </span>
          <span>Parking Lot: <i v-if="curHotel.parking===1" class="el-icon-check"></i></span>
        </div>
        <div style="margin:10px 0;font-size: 20px;font-family: 'Times New Roman',serif">
          <span>Spa: <i v-if="curHotel.spa===1" class="el-icon-check"></i><i v-if="curHotel.spa!==1"
                                                                             class="el-icon-close"></i></span>
          <span> | </span>
          <span>Bar: <i v-if="curHotel.bar===1" class="el-icon-check"></i><i v-if="curHotel.bar!==1"
                                                                             class="el-icon-close"></i></span>
          <span> | </span>
          <span>Gym: <i v-if="curHotel.gym===1" class="el-icon-check"></i><i v-if="curHotel.gym!==1"
                                                                             class="el-icon-close"></i></span>
          <span> | </span>
          <span>Chess Room: <i v-if="curHotel.chessRoom===1" class="el-icon-check"></i><i v-if="curHotel.chessRoom!==1"
                                                                                          class="el-icon-close"></i></span>
          <span> | </span>
          <span>Swimming Pool: <i v-if="curHotel.swimmingPool===1" class="el-icon-check"></i><i
              v-if="curHotel.swimmingPool!==1" class="el-icon-close"></i></span>
        </div>
        <el-divider style="height: 5px"></el-divider>
        <el-image v-if="dialogVisible&&isLogin"
                  style="width:50px;height:50px;cursor:pointer;margin-left:600px"
                  :src="curHotel.isCollect?require('../../assets/images/red-heart.png'):require('../../assets/images/heart.png')"
                  @click="collectHotel(curHotel.hotelId, curHotel.isCollect)"></el-image>
      </el-dialog>

    </el-row>

  </div>
</template>

<script>
import cookie from "js-cookie";

export default {
  name: "home",
  data() {
    return {
      date1: new Date().toDateString(),
      date2: this.generateTomorrow().toDateString(),
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
      count1: 0,
      count2: 0,
      locations: [],
      value: [100, 10000],
      curSel: 'Choose The Location',
      hotels: [],
      curHotel: {},
      dialogVisible: false,
      isLogin: false,
      location: ''
    };
  },
  methods: {
    // 显示日历
    showDate() {
      this.showCheck = true;
    },
    toMore() {
      this.$message.info('more')
    },
    handleSel(val) {
      this.curSel = val.province + " " + val.city;
      this.location = val.province + "/" + val.city;
      this.search();
    },
    updateGuest() {
      this.count2 = this.count1;
    },
    generateTomorrow() {
      const today = new Date();
      const current = today.getDate();
      let finalDate = new Date();
      finalDate.setDate(current + 1);
      return finalDate;
    },
    toHotel(hotelId) {
      this.$router.push('reserve?hotel=' + hotelId);
    },
    openDialog(item) {
      this.curHotel = item;
      this.dialogVisible = true;
    },
    collectHotel(hotelId, isCollect) {
      if (!isCollect) {
        this.$http({
          url: this.$http.adornUrl('/member/member/collecthotel/collectHotel'),
          method: 'get',
          params: this.$http.adornParams({
            token: cookie.get('token'),
            hotelId: hotelId
          })
        }).then(data => {
          let state = data.data.state;
          if (state === 200) {
            this.curHotel.isCollect = true;
            for (let i = 0; i < this.hotels.length; i++) {
              let hotel = this.hotels[i];
              if (hotel.hotelId === hotelId) {
                hotel.isCollect = true;
              }
            }
            this.$message.success("Successfully Collect!")
          } else {
            this.$message.error(data.data.message);
          }
        }).catch(err => {
          this.$message.error("Network Error");
        })
      } else {
        this.$http({
          url: this.$http.adornUrl('/member/member/collecthotel/cancelCollectHotel'),
          method: 'get',
          params: this.$http.adornParams({
            token: cookie.get('token'),
            hotelId: hotelId
          })
        }).then(data => {
          let state = data.data.state;
          if (state === 200) {
            this.curHotel.isCollect = false;
            for (let i = 0; i < this.hotels.length; i++) {
              let hotel = this.hotels[i];
              if (hotel.hotelId === hotelId) {
                hotel.isCollect = false;
              }
            }
            this.$message.success("Successfully Cancel!")
          }else {
            this.$message.error(data.data.message);
          }
        }).catch(err => {
          this.$message.error("Network Error");
        })
      }
    },
    search() {
      let token = cookie.get('token');
      if (token === undefined || token === '') {
        token = '';
      }
      this.$http({
        url: this.$http.adornUrl('/room/room/hotel/search/hotel'),
        method: 'get',
        params: this.$http.adornParams({
          token: token,
          sortBy: this.checked1,
          reversed: this.checked2 === 'hl',
          diningRoom: this.checked3,
          parking: this.checked4,
          spa: this.checked5,
          bar: this.checked6,
          gym: this.checked7,
          chessRoom: this.checked8,
          swimmingPool: this.checked9,
          lowest: this.value[0],
          highest: this.value[1],
          location: this.location
        })
      }).then(data => {
        let obj = data.data.data;
        this.isLogin = obj.isLogin;
        this.locations = obj.locations;
        this.hotels = obj.hotels;
      }).catch(err => {
        this.$message.error("Network Error")
      })
    }
  },
  created() {
    let token = cookie.get('token');
    if (token === undefined || token === '') {
      token = '';
    }
    this.$http({
      url: this.$http.adornUrl('/room/room/hotel/initSearch'),
      method: 'get',
      params: this.$http.adornParams({
        token: token
      })
    }).then(data => {
      let obj = data.data.data;
      this.isLogin = obj.isLogin;
      this.locations = obj.locations;
      this.hotels = obj.hotels;
    }).catch(err => {
      this.$message.error("Network Error");
    })
  }
};
</script>

<style lang="scss" scoped>
color-icon {
  background: #d21919;
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
    background: #fff;
  }

  .btn-item {
    border: 1px solid #ddd;
    padding: 12px 0;
    cursor: pointer;
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
    width: 20px;
    display: inline;
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
    line-height: 45px;
    width: 100%;
    color: #fff;
    margin-top: 10px;
    cursor: pointer;
  }
}
</style>
