<template>
  <div class="page-main">


    <!--背景图和左下角的text part-->
    <el-carousel height="430px">
      <el-carousel-item v-for="item in this.images" :key="item">
        <img
            :src="item"
            alt="Network Error"
            class="item-img">
      </el-carousel-item>
      <div class="carousel-box">
        <div style="font-size:25px;">{{ this.hotelName }}</div>
        <div style="display:flex;margin-top:10px;">
          <div>
            <div><i class="el-icon-school"></i></div>
            <div style="margin-top:30px;"><i class="el-icon-phone"></i></div>
          </div>
          <div>
            <div>{{ this.province }} {{ this.city }} <br> {{ this.district }} {{ this.detailAddress }}</div>
            <div style="margin-top:20px;">{{ this.telephone }}</div>
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
              <div>{{ date1 }}</div>
            </div>
          </div>
        </el-col>

        <!--check out按钮-->
        <el-col :span="8" class="btn-item">
          <div class="flex-row" @click="showDate">
            <i class="el-icon-date user-icon"></i>
            <div>
              <div class="row-label">Check-Out</div>
              <div>{{ date2 }}</div>
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
            <el-popover placement="top-end" width="800" trigger="click" style="cursor: pointer">
              <div style="padding:0 15px;">
                <div class="title-popover" style="font-size:18px;">Filters</div>

                <el-col :span="12">
                  <div style="display:flex;justify-content: space-between;font-size: 18px;line-height: 50px;;">
                    <div>Sort By</div>
                  </div>
                  <div style="line-height:30px;">
                    <div>
                      <el-radio v-model="checked1" label="score">Score</el-radio>
                      <br>
                      <el-radio v-model="checked1" label="price">Price</el-radio>
                      <br>
                      <el-radio v-model="checked1" label="booking-number">Booking Number</el-radio>
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
                  <el-button type="info" style="background:#333;">Apply</el-button>
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
          <el-col :span="24" v-for="item in imgList" :key="item.name" class="room-list">
            <img :src="item.img" class="image">
            <div class="room-right">
              <div class="card-name">{{ item.name }}</div>
              <div style="font-weight:600;">King</div>
              <el-breadcrumb separator="|" style="margin:10px 0;">
                <el-breadcrumb-item>1King</el-breadcrumb-item>
                <el-breadcrumb-item>815ft</el-breadcrumb-item>
              </el-breadcrumb>
              <div>A contemporary retreat for a restful night’s sleep</div>
              <el-link>Room details</el-link>
              <el-divider></el-divider>

              <div class="flex-row2" style="margin-top:-10px;">
                <div>Standard Daily Rate</div>
                <div style="font-weight:600;">A${{ item.price }}</div>
              </div>
              <div class="flex-row2" style="font-size:13px;color: #666;">
                <div>{{ item.detail }}</div>
                <div>Per Night</div>
              </div>
              <div style="text-align:right;font-size:12px;margin-top: -10px;">{{ item.detail1 }}</div>
              <div style="text-align:right;">
                <el-button type="info" style="background:black;color:#fff;" @click="toCheckOut">Book Now</el-button>
              </div>
              <el-divider></el-divider>

              <div class="flex-row2" style="margin-top:-10px;">
                <div>{{ item.detail4 }}</div>
                <div style="font-weight:600;">A${{ item.price }}</div>
              </div>
              <div class="flex-row2" style="font-size:13px;color: #666;">
                <div>The stay includes</div>
                <div>{{ item.detail3 }}</div>
              </div>
              <div style="text-align:right;font-size:12px;margin-top: -10px;">{{ item.detail1 }}</div>
              <div style="display:flex;width:560px;justify-content: space-between;">
                <div style="width:380px;">{{ item.detail2 }}</div>
                <div style="text-align:right;">
                  <el-button type="info" style="background:black;color:#fff;" @click="toCheckOut">Book Now</el-button>
                </div>
              </div>
            </div>
          </el-col>
        </div>
      </el-col>

      <!--右侧信息-->
      <el-col :span="7" class="right-box">
        <div style="font-size:20px;line-height:45px;">Your Stay at Aman New York</div>
        <el-col :span="12" style="margin-top:20px;">
          <div style="font-weight:600;">Check-in</div>
          <div>After 3:00 PM</div>
        </el-col>
        <el-col :span="12" style="margin-top:20px;">
          <div style="font-weight:600;">Check-out</div>
          <div>Before 12:00 PM</div>
        </el-col>
        <el-col :span="24" style="padding-top:20px;">
          <div>Sun, Oct 23, 2022 - Wed, Nov 30, 2022</div>
          <div>2 Adults</div>
        </el-col>
        <el-col :span="24">
          <el-divider></el-divider>
        </el-col>
        <el-col :span="24" style="display:flex;justify-content: space-between;">
          <div style="font-weight:600;">Total:</div>
          <div style="font-weight:600;">A$0.00</div>
        </el-col>
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
              <div style="margin-top:5px;">A range of unique experiences to enbance your stay,</div>
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
export default {
  name: "home",
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
      images: [],
      date1: new Date().toDateString(),
      date2: this.generateTomorrow().toDateString(),
      date3: '',
      date4: '',
      showCheck: false,
      checked1: 'score',
      checked2: 'hl',
      checked3: true,
      checked4: true,
      checked5: true,
      checked6: false,
      checked7: false,
      checked8: false,
      checked9: false,
      checked10: false,
      checked11: false,
      count1: 0,
      count2: 0,
      imgList: [
        {
          price1: '2560',
          btn: 'Dates Unavailable',
          name: 'Amantaka',
          detail1: 'Excluding Taxes & Fees 1',
          detail2: 'Daily American breakfast in Arva or via in-room dining up to two people 1',
          addr: "Luang Prabang, Lao People's Dem Republic",
          price: '1980',
          img: require('../../assets/images/hotel/2.jpeg'),
          detail: 'Rates are on room only basis 1',
          detail3: 'Per Night 1',
          detail4: 'Breakfast in Manhattan 1'
        },
        {
          price1: '1780',
          btn: 'Dates Unavailable',
          name: 'Amanwella',
          detail1: 'Excluding Taxes & Fees 2',
          detail2: 'Daily American breakfast in Arva or via in-room dining up to two people 2',
          addr: 'Tangalle, Sri Lanka',
          price: '1560',
          img: require('../../assets/images/hotel/3.jpeg'),
          detail: 'Rates are on room only basis 2',
          detail3: 'Per Night 2',
          detail4: 'Breakfast in Manhattan 2'
        },
        {
          price1: '5860',
          btn: 'Dates Unavailable',
          name: 'Amanera',
          detail1: 'Excluding Taxes & Fees 3',
          detail2: 'Daily American breakfast in Arva or via in-room dining up to two people 3',
          addr: 'Rio San Juan, Dominican Republic',
          price: '4060',
          img: require('../../assets/images/hotel/4.jpeg'),
          detail: 'Rates are on room only basis 3',
          detail3: 'Per Night 3',
          detail4: 'Breakfast in Manhattan 3'
        },
        {
          price1: '1809',
          btn: 'View rates',
          name: 'Aman Tokyo',
          detail1: 'Excluding Taxes & Fees 4',
          detail2: 'Daily American breakfast in Arva or via in-room dining up to two people 4',
          addr: 'Chiyoda-ku, Japan',
          price: '1390',
          img: require('../../assets/images/hotel/5.jpeg'),
          detail: 'Rates are on room only basis 4',
          detail3: 'Per Night1 4',
          detail4: 'Breakfast in Manhattan 4'
        },
        {
          price1: '2230',
          btn: 'View rates',
          name: 'Amanfayun',
          detail1: 'Excluding Taxes & Fees 5',
          detail2: 'Daily American breakfast in Arva or via in-room dining up to two people 5',
          addr: 'Hangzhou 33, China',
          price: '1880',
          img: require('../../assets/images/hotel/9.jpeg'),
          detail: 'Rates are on room only basis 5',
          detail3: 'Per Night 5',
          detail4: 'Breakfast in Manhattan 5'
        },
      ]
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
    toCheckOut() {
      this.$router.push('Order')
    },
    toFloorPlan() {
      this.$router.push('floorPlan')
    },
    updateGuests() {
      this.count2 = this.count1;
      // TODO: 搜索对应的房间
    },
    updateDates() {
      if (this.date3 !== undefined && this.date3 !== "") {
        this.date1 = this.date3.toDateString();
      }
      if (this.date4 !== undefined && this.date4 !== "") {
        this.date2 = this.date4.toDateString();
      }
      //TODO: 搜索对应的日期
    },
    generateTomorrow() {
      const today = new Date();
      const current = today.getDate();
      let finalDate = new Date();
      finalDate.setDate(current + 1);
      return finalDate;
    }
  },
  mounted() {
    let hotelId = this.$route.query.hotel;
    let url = '/room/room/hotel/initReserve';
    this.$http({
      url: this.$http.adornUrl(url),
      method: 'get',
      params: this.$http.adornParams({
        hotelId: hotelId,
        today: new Date().toDateString()
      })
    }).then(data => {
      let obj = data.data.data;
      this.hotelName = obj.hotelName;
      this.province = obj.province;
      this.city = obj.city;
      this.district = obj.district;
      this.detailAddress = obj.detailAddress;
      this.telephone = obj.telephone;
      this.images = obj.images;
    }).catch(err => {
      this.$message.error("Network Error");
    })
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
    line-height: 45px;
    width: 100%;
    color: #fff;
    margin-top: 10px;
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
  z-index: 10000;
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
