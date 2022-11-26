<template>
  <div class="page-main">

    <!--页面顶部的四个短语-->
    <div class="item-img">
      <el-divider></el-divider>
      <div class="top-link">
        <el-link>Hotel Info</el-link>
        <el-link>Find Reservations</el-link>
        <el-link>Property Currency</el-link>
        <el-link><i class="el-icon-user"></i>Sign In</el-link>
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
                <el-form-item label="Guests"><el-input-number v-model="count1" :min="0"></el-input-number></el-form-item>
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
              <div class="title-name">{{item.province}}</div>
              <div class="desc-name">{{item.city}}</div>
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
          <el-popover placement="bottom" width="1300" trigger="click">
            <div style="padding:0 15px;">
              <div class="title-popover" style="font-size:18px;">Filters</div>
              <el-col :span="12" style="border-right:1px solid #ddd;">
                <div style="display:flex;justify-content: space-between;font-size: 18px;line-height: 50px;;">
                  <div>Destinations</div>
                  <el-link>Clear</el-link>
                </div>
                <div style="line-height:30px;">
                  <div><el-checkbox v-model="checked1">IndoChina</el-checkbox></div>
                  <div><el-checkbox v-model="checked2">China</el-checkbox></div>
                  <div><el-checkbox v-model="checked3">Japan</el-checkbox></div>
                  <div><el-checkbox v-model="checked4">Europe</el-checkbox></div>
                  <div><el-checkbox v-model="checked5">USA & Caribbean</el-checkbox></div>
                </div>
                <el-link>Show All Destinations</el-link>
                <br>
              </el-col>
              <el-col :span="24" style="line-height:50px;border-top:1px solid #ddd;text-align:right;"><el-button type="info" style="background:#333;">Apply</el-button></el-col>
            </div>
            <!--下拉菜单hide filters-->
            <div class="flex-row" slot="reference" style="margin-left:20px;background: #82847f;margin-right: 20px;padding:8px 2px;color: #fff;">
              <div><div class="row-label">Hide Filters</div></div>
              <i class="el-icon-caret-bottom user-icon" style="font-size:18px;"></i>
            </div>
          </el-popover>

        </div>
      </div>

      <!--每个酒店的展示部分-->
      <div style="clear:both;">
        <el-col :span="8" v-for="item in hotels" :key="item.hotelName">
          <el-card :body-style="{ padding: '15px' }">
            <img :src="item.hotelPicture" class="image">
            <div style="padding: 14px;">
              <div class="card-name">{{ item.hotelName }}</div>
              <div class="card-addr">{{item.province}}, {{item.city}}, {{item.district}}, {{item.detailAddress}}</div>
              <div class="card-detail">Detail</div>
              <el-divider></el-divider>
              <div style="text-align:center;color:#666;">
                <div>Average <span style="font-weight:700;font-size:17px;">￥{{ item.averagePrice }}</span></div>
                <div>Per Night</div>
              </div>
              <div class="v-btn2" @click="toHotel(item.hotelId)">View Hotel</div>
            </div>
          </el-card>
        </el-col>
      </div>

    </el-row>

  </div>
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      date1: new Date().toDateString(),
      date2: this.generateTomorrow().toDateString(),
      date3: '',
      date4: '',
      showCheck: false,
      checked1: false,
      checked2: false,
      checked3: false,
      checked4: false,
      checked5: false,
      count1: 0,
      count2: 0,
      locations: [],
      curSel: 'Choose The Location',
      hotels: [],
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
    }
  },
  created() {
    this.$http({
      url: this.$http.adornUrl('/room/room/hotel/initSearch'),
      method: 'get'
    }).then(data => {
      let obj = data.data.data;
      this.locations = obj.locations;
      this.hotels = obj.hotels;
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
<style>

</style>
