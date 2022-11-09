<template>
	<div class="page-main">

		<div class="item-img">
			<el-divider></el-divider>
			<div class="top-link">

        <!--顶部的按键和电话号码-->
				<el-link>Hotel Info</el-link>
				<el-link>Find Reservations</el-link>
				<el-link><i class="el-icon-phone-outline"></i>+1 212 970 2626</el-link>

        <!--货币选择-->
				<el-popover placement="bottom" width="885" trigger="click">
						<div style="padding:0 15px;">
							<div class="title-popover" style="font-size:18px;border: none;display: flex;">
								<div>Top Currencies</div>
								<div style="margin-left: 100px">All Currencies</div>
							</div>

							<el-col :span="6" class="top-select">
								<div class="select-item">Property Currency</div>
								<div class="select-item">AUD - Australian Dollars</div>
								<div class="select-item">CAD - Canadian Dollars</div>
								<div class="select-item">CNY - China Yuan Renminbi</div>
								<div class="select-item">EUR - Euros</div>
								<div class="select-item">GBP - British Pounds</div>
								<div class="select-item">INR - Indian Rupees</div> 
								<div class="select-item">SGD - Singapore Dollars</div> 
								<div class="select-item">USD - US Dollars</div>
                <br><br>
							</el-col>

							<el-col :span="18" style="margin-top: 0">
								<el-input v-model="filterText" placeholder="Filter"></el-input>
								<el-divider></el-divider>

								<el-col :span="12" class="top-select">
									<div class="select-item">AFN - Afghanistan Afghanis</div>
									<div class="select-item">DZD - Algerian Dinar</div> 
									<div class="select-item">AED - Arab Emirate Dirham</div> 
									<div class="select-item">AMD - Armenia Drams</div> 
									<div class="select-item">AUD - Australian Dollars</div> 
									<div class="select-item">BSD - Bahamas Bahamian Dollar</div> 
									<div class="select-item">BDT - Bangladeshi Taka</div> 
									<div class="select-item">LSL - Basotho Loti</div> 
									<div class="select-item">BZD - Belize Dollar</div> 
									<div class="select-item">BTN - Bhutan Ngultrum</div> 
									<div class="select-item">BWP - Botswana Pula</div> 
									<div class="select-item">AMD - Armenia Drams</div> 
									<div class="select-item">AUD - Australian Dollars</div>
                  <br><br>
								</el-col>

								<el-col :span="12" class="top-select">
									<div class="select-item">ALL - Albania Leke</div>
									<div class="select-item">AOA - Angola Kwanza</div> 
									<div class="select-item">ARS - Argentina Pesos</div> 
									<div class="select-item">AWG - Aruban Florin</div> 
									<div class="select-item">AZN - Azerbaijani New Manat</div> 
									<div class="select-item">BHD - Bahrain Dinar</div> 
									<div class="select-item">BBD - Barbadian Dollar</div> 
									<div class="select-item">BYN - Belarusian Ruble</div> 
									<div class="select-item">BMD - Bermudian Dollars</div> 
									<div class="select-item">BAM - Bosnian Convertible Marka</div> 
									<div class="select-item">BRL - Brazilian Reals</div> 
									<div class="select-item">BBD - Barbadian Dollar</div> 
									<div class="select-item">BYN - Belarusian Ruble</div>
                  <br><br>
								</el-col>

							</el-col> 
						</div>

						<div class="flex-row" slot="reference" >
							<el-link>Sign In<i class="el-icon-arrow-down"></i></el-link>
						</div>
					</el-popover>

			</div>
		</div>
		<el-divider></el-divider>

    <!--背景图和左下角的text part-->
		<el-carousel height="430px">
			<el-carousel-item >
				<img src="https://be-cms-api-p1.synxis.com/shs-bedesigner-services/assets/chain/16840/hotel/35050/fileStorage/image/BE_Aman%20New%20York%20New%20(1).png" class="item-img">
				<div class="carousel-box">
					<div style="font-size:25px;">Aman New York</div>
					<div style="display:flex;margin-top:10px;">
						<div>
							<div><i class="el-icon-school"></i></div>
							<div style="margin-top:30px;"><i class="el-icon-phone"></i></div>
							<div style="margin-top:20px;"><i class="el-icon-link"></i></div>
						</div>
						<div>
							<div>730 Fifth Avenue, The Crown Building, New York, New York, United States, 10019</div>
							<div style="margin-top:20px;">+1 212 970 2626</div>
							<div style="margin-top:25px;">https://www.aman.com/hotels/aman-new-york</div>
						</div>
					</div>
				</div>
			</el-carousel-item>
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
										<el-form-item label="Adults"><el-input-number v-model="count1" :min="0"></el-input-number></el-form-item>
										<el-form-item label="Children"><el-input-number v-model="count2" :min="0"></el-input-number></el-form-item>
										<div style="line-height:50px;border-top:1px solid #ddd;text-align:right;"><el-button type="info">Apply</el-button></div>
									</el-form>
								</div>
							</div>
							<div class="flex-row" slot="reference">
								<i class="el-icon-user-solid user-icon"></i>
								<div>
									<div class="row-label">Guests</div>
									<div>{{count1}}Adults,{{count2}}Children</div>
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
							<div>{{date1}}</div>
						</div>
					</div>
				</el-col>

        <!--check out按钮-->
				<el-col :span="8" class="btn-item">
					<div class="flex-row"  @click="showDate">
						<i class="el-icon-date user-icon"></i>
						<div>
							<div class="row-label">Check-Out</div>
							<div>{{date2}}</div>
						</div>
					</div>
				</el-col>

        <!--rate filter-->
				<el-col :span="24" class="rate-sel">
					<p @click="showSel = true">Special Codes or Rates<i class="el-icon-arrow-down"></i></p>
				</el-col>
				<el-col :span="24"  v-show="showSel" style=" background: #fff;">
					<el-select v-model="selCode" placeholder="Promo Code">
						<el-option label="Promo Code" value="1"></el-option>
						<el-option label="Group Code" value="2"></el-option>
					</el-select>
					<el-input placeholder="Enter Code" v-model="input3" class="input-with-select" />
					<div class="date-btn" style="margin-top:15px;"> 
						<el-link class="date-item" @click="showSel=false">Cancel</el-link>
						<el-button class="date-item" type="info" style="background:#fff;color:black;">Apply</el-button>
					</div>
				</el-col>

        <!--日历控件-->
				<el-col :span="24" v-show="showCheck">
					<div class="flex-row1">
						<el-calendar v-model="date3" />
						<el-calendar v-model="date4" />
					</div>
					<div class="date-btn">
						<div class="date-item">
							<div style="font-weight:600;">From A$256,977 total for 38 nights</div>
							<div style="font-size:12px;text-align: right;">Excluding Taxes & Fees</div>
						</div>
						<el-link class="date-item" @click="showCheck = false;">Cancel</el-link>
						<el-button class="date-item" type="info" style="background:#333;">Search</el-button>
					</div>
				</el-col>

				<div style="background:#f3eee7;line-height:80px;padding-left:15px;font-size:22px">Select a Room</div>
				<div class="hotel-select">
					<div class="flex-row" style="margin-top:15px;">

            <!--view results by room下拉菜单-->
						<el-dropdown>
							<span class="el-dropdown-link">
								<div class="flex-row" style="cursor: pointer;margin-right:15px;">
									<div>
										<div>View Results By</div>
										<div class="row-label">Rooms</div>
									</div>
									<i class="el-icon-caret-bottom el-icon--right" style="font-size:18px;"></i>
								</div> 
							</span>
							<el-dropdown-menu slot="dropdown">
								<el-dropdown-item>Relevance</el-dropdown-item>
								<el-dropdown-item>Hotel Name (A-Z)</el-dropdown-item> 
							</el-dropdown-menu>
						</el-dropdown>

            <!--sort by relevance 下拉菜单-->
						<el-dropdown>
							<span class="el-dropdown-link">
								<div class="flex-row" style="cursor: pointer;">
									<div>
										<div>Sort By</div>
										<div class="row-label">Relevance</div>
									</div>
									<i class="el-icon-caret-bottom el-icon--right" style="font-size:18px;"></i>
								</div> 
							</span>
							<el-dropdown-menu slot="dropdown">
								<el-dropdown-item>Relevance</el-dropdown-item>
								<el-dropdown-item>Hotel Name (A-Z)</el-dropdown-item> 
							</el-dropdown-menu>
						</el-dropdown>

            <!--filters-->
						<el-popover placement="top-end" width="800" trigger="click">
								<div style="padding:0 15px;">
									<div class="title-popover" style="font-size:18px;">Filters</div>

									<el-col :span="12" style="border-right:1px solid #ddd;">
										<div style="display:flex;justify-content: space-between;font-size: 18px;line-height: 50px;;"><div>Display</div></div>
										<div style="line-height:30px;">
											<div><el-radio v-model="checked1">View By Rooms</el-radio></div>
											<div><el-radio v-model="checked2">View By Rates</el-radio></div>
										</div>
									</el-col>

									<el-col :span="12" >
										<div style="display:flex;justify-content: space-between;font-size: 18px;line-height: 50px;;"><div>Sort By</div></div>
										<div style="line-height:30px;">
											<div><el-radio v-model="checked1">Recommended</el-radio></div>
											<div><el-radio v-model="checked2">Lowest Price</el-radio></div>
											<div><el-radio v-model="checked2">Highest Price</el-radio></div>
										</div>
									</el-col>

									<el-col :span="24"><el-divider></el-divider></el-col>

                  <el-col :span="12" style="border-right:1px solid #ddd;">
										<div style="display:flex;justify-content: space-between;font-size: 18px;line-height: 50px;;">
											<div>View</div>
											<el-link style="font-size:13px;">Clear</el-link>
										</div>
										<div style="line-height:30px;"> 
											<div><el-checkbox v-model="checked3">57th Street</el-checkbox></div>
											<div><el-checkbox v-model="checked4">Fifth Avenue</el-checkbox></div>
										</div>
                    <br>
									</el-col>

									<el-col :span="12">
										<div style="display:flex;justify-content: space-between;font-size: 18px;line-height: 50px;;">
											<div>Rate Type</div>
											<div style="font-size:13px;">Clear</div>
										</div>
										<div style="line-height:30px;"> 
											<div><el-checkbox v-model="checked5">Breakfast in Manhattan</el-checkbox></div>
											<div><el-checkbox v-model="checked6">Standard Daily Rate</el-checkbox></div>
										</div>
                    <br>
									</el-col>

									<el-col :span="24"><el-divider></el-divider></el-col>

									<el-col :span="12" style="border-right:1px solid #ddd;">
										<div style="display:flex;justify-content: space-between;font-size: 18px;line-height: 50px;;">
											<div>Room Category</div>
											<el-link>Clear</el-link>
										</div>
										<div style="line-height:30px;">
											<div><el-checkbox v-model="checked7">Grand Suite on 57th</el-checkbox></div>
											<div><el-checkbox v-model="checked8">Fifth Avenue Suite</el-checkbox></div>
											<div><el-checkbox v-model="checked9">Premier Suite on 57th Sanctuary</el-checkbox></div>
											<div><el-checkbox v-model="checked9">Premier Suite on 57th</el-checkbox></div>
										</div>
									</el-col>

									<el-col :span="12" >
										<div style="display:flex;justify-content: space-between;font-size: 18px;line-height: 50px;;">
											<div>Price (avg./night)</div>
											<el-link>Reset</el-link>
										</div>
										<div style="padding:15px;justify-content: space-between;">
											<el-slider v-model="value" range show-stops :min="6763" :max="11637" />
											<div style="display: flex;">
												<div class="price-box">
													<div>Price range from</div>
													<div>{{value[0]}}</div>
												</div>
												<div style="width:20px;">-</div>
												<div class="price-box">
													<div>Price range from</div>
													<div>{{value[1]}}</div>
												</div>
											</div>
										</div>
                    <br>
									</el-col>

									<el-col :span="24" style="line-height:50px;border-top:1px solid #ddd;text-align:right;">
										<span style="margin-right:50px;">6 matching rooms</span> 
										<el-button type="info" style="background:#333;">Apply</el-button>
									</el-col>

								</div><!--filters end-->

								<div class="flex-row" slot="reference" style="margin-left:20px;background: #82847f;margin-right: 20px;padding:8px 2px;color: #fff;">
									<div><div class="row-label">Hide Filters</div></div>
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
							<div class="card-name">{{item.name}}</div>
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
								<div style="font-weight:600;">A${{item.price}}</div>
							</div>
							<div class="flex-row2" style="font-size:13px;color: #666;">
								<div>{{item.detail}}</div>
								<div>Per Night</div>
							</div>
							<div style="text-align:right;font-size:12px;margin-top: -10px;">{{item.detail1}}</div> 
							<div style="text-align:right;"><el-button type="info" style="background:black;color:#fff;" @click="toCheckOut">Book Now</el-button></div>
							<el-divider></el-divider>

							<div class="flex-row2" style="margin-top:-10px;">
								<div>{{item.detail4}}</div>
								<div style="font-weight:600;">A${{item.price}}</div>
							</div>
							<div class="flex-row2" style="font-size:13px;color: #666;">
								<div>The stay includes</div>
								<div>{{item.detail3}}</div>
							</div>
							<div style="text-align:right;font-size:12px;margin-top: -10px;">{{item.detail1}}</div> 
							<div style="display:flex;width:560px;justify-content: space-between;">
								<div style="width:380px;">{{item.detail2}}</div>
								<div style="text-align:right;"><el-button type="info" style="background:black;color:#fff;" @click="toCheckOut">Book Now</el-button></div>
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
				<el-col :span="24"><el-divider></el-divider></el-col>
				<el-col :span="24" style="display:flex;justify-content: space-between;">
					<div style="font-weight:600;">Total:</div>
					<div style="font-weight:600;">A$0.00</div>
				</el-col>
				<el-col :span="24"><el-divider></el-divider></el-col>
				<el-col :span="24">
					<div style='font-size:20px;margin-bottom:20px;color:black;'>Book direct for peace of mind</div>

					<div>
						<div class="right-flex">
							<div class="icon-img"><img src="../../assets/images/1.jpg" /></div>
							<div>24/7 Dedicated Global Reservation Team offering personalised service</div>
						</div>
						<div class="right-flex">
							<div class="icon-img"><img src="../../assets/images/2.jpg" /></div>
							<div style="margin-top:5px;">Exclusive offers with inspiring itineraries, only when booking direct</div>
						</div>
						<div class="right-flex">
							<div class="icon-img"><img src="../../assets/images/3.jpg" /></div>
							<div style="margin-top:5px;">A range of unique experiences to enbance your stay,</div>
						</div>
						<div class="right-flex">
							<div class="icon-img"><img src="../../assets/images/4.jpg" /></div>
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
		showSel:false,
		input3:'',
		selCode:'1',
		value: [6763, 11637],
		filterText:'',
		date1: new Date().toDateString(),
		date2: new Date().toDateString(),
		date3:'',
		date4:'',
		showCheck: false,
		checked1:false,
		checked2:false,
		checked3:false,
		checked4:false,
		checked5:false,
		checked6:false,
		checked7:false,
		checked8:false,
		checked9:false,
		checked10:false,
		checked11:false,
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
  methods:{  
	// 显示日历
	showDate(){
		this.showCheck = true;
	},
    toMore(){
        this.$message.info('more')
    },
	toCheckOut(){
		this.$router.push('Order')
	}

  }
};
</script>

<style lang="scss" scope>
.page-main {
  background: #f3eee7;

  .item-img {
    width: 1300px;
    height: 100%;
  }

  .top-link {
    width: 480 p;
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
  height: 180px;
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