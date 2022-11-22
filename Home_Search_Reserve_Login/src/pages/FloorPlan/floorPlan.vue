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
      <div style="background:#f3eee7;line-height:80px;padding-left:15px;font-size:18px">Select a Room</div>
      <div class="roomList">
        <img src="../../assets/images/floor.jpg" style="width:1240px;"/>
        <div class="flex-row">
          <div class="roomNum1" v-for="(item, index) in 9" :key="index" @click="toOpen(index)"></div>
        </div>
        <div class="flex-row flex-row3" style="margin-top:300px">
          <div class="roomNum1" v-for="(item, index) in 9" :key="index" @click="toOpen(8 + index)"></div>
        </div>
      </div>

      <!--弹窗-->
      <el-dialog :visible.sync="dialogVisible" width="700px" title="Book">
        <div class="view-room">
          <div>
            <div class="room-title">{{ curRow.name }}</div>
            <div style="margin:10px 0">
              <span>{{ curRow.twin }} Twin</span>
              <span> | </span>
              <span>{{ curRow.area }}</span>
            </div>
            <div>Room Amenities</div>
          </div>
          <div>
            <el-image
                style="width: 260px; height: 180px"
                :src="curRow.img"
                :preview-src-list="[curRow.img]">
            </el-image>
          </div>
        </div>
        <el-divider></el-divider>

        <div>
          <div class="detail">{{ curRow.detail }}</div>
          <div class="dline">{{ curRow.d1 }}</div>
          <div class="dline">{{ curRow.d2 }}</div>
          <div class="dline">{{ curRow.d3 }}</div>
          <div class="dline">{{ curRow.d4 }}</div>
          <div class="dline">{{ curRow.d5 }}</div>
          <div class="dline">{{ curRow.d6 }}</div>
        </div>
        <el-divider></el-divider>

        <!--用户评论区-->
        <div>
          <div class="detail">User Comment</div>
          <div class="comment-flex" v-for="(item,index) in commentList" :key="index">
            <div class="comment-user">{{ item.name }}：</div>
            <div class="comment-des">
              <div class="comment-content">{{ item.content }}</div>
              <div class="time">{{ item.ctime }}</div>
            </div>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="dialogVisible = false" class="v-btn2">Book Now</el-button>
        </span>
      </el-dialog>

    </el-row>
  </div>
</template>

<script>
export default {
  name: "floorPlan",
  data() {
    return {
      dialogVisible: false,
      curRow: '',
      list: [
        {
          roomNO: '01',
          twin: '3',
          area: '70m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 1',
          d2: 'Linving arca 1',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 1',
          d4: 'Subtle Shoji screens made of delicate washi paper 1',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 1',
          d6: 'Personal bar 1',
          img: require('../../assets/images/hotel/1.jpeg')
        },
        {
          roomNO: '02',
          twin: '2',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/2.jpeg')
        },
        {
          roomNO: '03',
          twin: '3',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/3.jpeg')
        },
        {
          roomNO: '04',
          twin: '3',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/4.jpeg')
        },
        {
          roomNO: '05',
          twin: '2',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/5.jpeg')
        },
        {
          roomNO: '06',
          twin: '0',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/6.jpeg')
        },
        {
          roomNO: '07',
          twin: '7',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/7.jpeg')
        },
        {
          roomNO: '08',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/8.jpeg')
        },
        {
          roomNO: '09',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/9.jpeg')
        },
        {
          roomNO: '10',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/3.jpeg')
        },
        {
          roomNO: '11',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/4.jpeg')
        },
        {
          roomNO: '12',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/5.jpeg')
        },
        {
          roomNO: '13',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/6.jpeg')
        },
        {
          roomNO: '14',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/7.jpeg')
        },
        {
          roomNO: '15',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/1.jpeg')
        },
        {
          roomNO: '16',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/2.jpeg')
        },
        {
          roomNO: '17',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/3.jpeg')
        },
        {
          roomNO: '18',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/4.jpeg')
        },
        {
          roomNO: '19',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/1.jpeg')
        },
        {
          roomNO: '20',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/4.jpeg')
        },
        {
          roomNO: '21',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/2.jpeg')
        },
        {
          roomNO: '22',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/3.jpeg')
        },
        {
          roomNO: '23',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/9.jpeg')
        },
        {
          roomNO: '24',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/8.jpeg')
        },
        {
          roomNO: '25',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/7.jpeg')
        },
        {
          roomNO: '26',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/6.jpeg')
        },
        {
          roomNO: '27',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/5.jpeg')
        },
        {
          roomNO: '28',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/4.jpeg')
        },
        {
          roomNO: '29',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/3.jpeg')
        },
        {
          roomNO: '30',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/3.jpeg')
        },
        {
          roomNO: '31',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/4.jpeg')
        },
        {
          roomNO: '31',
          twin: '4',
          area: '72m²',
          name: 'Aman Kyoto',
          detail: 'Deluxe Rooms are the argest entry-level rooms in the city,offering space and privacy at 7amz(764sqft)overkooking the Imperiall Pallace Gardens.',
          d1: 'Views of lmpcrial Palace GardersTwin beds 2',
          d2: 'Linving arca 2',
          d3: 'Barhroom with tradlitional Fuaro soaking tub 2',
          d4: 'Subtle Shoji screens made of delicate washi paper 2',
          d5: 'WiP, TV with video camcorder connection, sound systean, safe 2',
          d6: 'Personal bar 2',
          img: require('../../assets/images/hotel/5.jpeg')
        },
      ],
      commentList: [
        {name: 'user1', content: 'The view is goods', ctime: '2022-11-15'},
        {name: 'user2', content: 'The room is comfortable', ctime: '2022-11-14'},
        {name: 'user3', content: 'A good check-in experience', ctime: '2022-11-12'}
      ]
    };
  },
  mounted() {
    this.curRow = this.list[0]
  },
  methods: {
    toReserve() {
      this.$router.push('reserve')
    },
    toOpen(index) {
      if (index == 3 || index == 4 || index == 5) {
        return;
      }
      this.curRow = this.list[index]
      this.dialogVisible = true;
    }
  }
};
</script>

<style lang="scss" scoped>
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
    width: 121px;
    height: 240px;
    cursor: pointer;
    position: relative;
    margin-top: 100px;
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
