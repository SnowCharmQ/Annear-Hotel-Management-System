<template>
  <div id="GaodeMapContainer">
    <el-dialog title="Hotel Info" :visible.sync="showHotel">
      <el-form ref="form" :model="curHotel" label-width="80px">
        <el-form-item label="Name:">{{ curHotel.name }}</el-form-item>
        <el-form-item label="Address:">{{ curHotel.addr }}</el-form-item>
        <el-form-item label="Price:">${{ curHotel.price }}</el-form-item>
        <el-form-item label="Pic:"><img :src="curHotel.img" style="height: 100px"/></el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>

let areaPolygons = [] // 查询的行政区域
export default {
  data() {
    return {
      myMap: "", // 地图实例
      mouseTool: "", // 鼠标工具实例
      district: '',  // 行政区划搜索实例
      inputValue: '',  // 用来搜索行政区
      // timer: null  // 防抖处理
      showHotel: false,
      curHotel: {},
      hotelList: [
        {
          name: 'Aman Kyoto',
          addr: 'beijin',
          price: '230',
          lng: 116.20,
          lat: 39.56,
          img: require('../../assets/images/hotel/1.jpeg')
        }, {
          name: 'Aman Kyoto',
          addr: 'Cape St Charles',
          price: '230',
          lng: 55.40,
          lat: 52.13,
          img: require('../../assets/images/hotel/1.jpeg')
        },
        {
          name: 'Amantaka',
          addr: "Mexico",
          price: '980',
          lng: 98.52,
          lat: 19.03,
          img: require('../../assets/images/hotel/2.jpeg')
        },
        {
          name: 'Amanwella',
          addr: 'hangzhou',
          price: '560',
          lng: 118.50,
          lat: 29.11,
          img: require('../../assets/images/hotel/3.jpeg')
        },
        {
          name: 'Amanera',
          addr: 'nanjing',
          price: '460',
          lng: 118.90,
          lat: 31.99,
          img: require('../../assets/images/hotel/4.jpeg')
        },
        {
          name: 'Aman Tokyo',
          addr: 'Chiyoda-ku, Japan',
          price: '390',
          lng: 106.20,
          lat: 29.56,
          img: require('../../assets/images/hotel/5.jpeg')
        },
        {
          name: 'Aman Tokyo',
          addr: 'shenzheng',
          price: '390',
          lng: 114.085947,
          lat: 22.547,
          img: require('../../assets/images/hotel/5.jpeg')
        },
        {
          name: 'Aman Tokyo',
          addr: 'lanzhou',
          price: '390',
          lng: 103.51,
          lat: 36.04,
          img: require('../../assets/images/hotel/5.jpeg')
        }
      ]
    };
  },
  watch: {
    inputValue() {
      if (this.timer) clearTimeout(this.timer)
      this.timer = setTimeout(() => {
        // this.seachArea1()

      }, 1500)
    }
  },
  mounted() {
    window._AMapSecurityConfig = {
      securityJsCode: 'f3b4637de1fe910d5be8399da35b8ce7',
    }
    console.log(AMap, 'AMap-->>')
    // 初始化一个地图
    this.initMap();
    this.curHotel = this.hotelList[0]
  },
  methods: {
    initMap() {
      // 实例化
      this.myMap = new AMap.Map("GaodeMapContainer", {
        resizeEnable: true,
        center: [117.0, 36.4], //中心点坐标(东经， 北纬) (jinan)
        zoom: 4, //级别
        //   zooms: [6, 23],  //设置地图级别范围
        pitch: 35, // 地图俯仰角度，有效范围 0 度- 83 度
        viewMode: "2D", // 地图模式
        terrain: true,
        // mapStyle: "amap://styles/whitesmoke",//设置地图背景
      });

      // 坐标点
      // 循环添加酒店坐标点 

      this.hotelList.forEach(item => {
        var m = new AMap.Marker({
          position: [item.lng, item.lat], // 和上面的center 一样的 item.lag,item.lat
          offset: new AMap.Pixel(-10, -40),
        })
        this.myMap.add(m);
        m.setExtData(item)
        m.on("click", e => {  // 监听Marker的点击
          this.curHotel = e.target.getExtData();
          this.showHotel = true;

          return;
          // 放大视图
          var newCenter = [117.0, 36.4];
          this.myMap.setZoom(19);
          this.myMap.setCenter(newCenter);
          //map.remove(this.videoMarkers); //移除之前的marker标记
        })
      });

      // this.auto = new AMap.AutoComplete(this.autoOptions)

      // 异步加载多个插件 -- 同时引入工具条插件，比例尺插件和鹰眼插件
      AMap.plugin(
          [
            "AMap.ToolBar", // 工具条，控制地图的缩放、平移等
            "AMap.Scale", // 比例尺，显示当前地图中心的比例尺
            // "AMap.OverView",
            'AMap.Autocomplete', 'AMap.PlaceSearch',
            "AMap.MapType", // 图层切换，用于几个常用图层切换显示
            "AMap.Geolocation", // 定位，提供了获取用户当前准确位置、所在城市的方法
            "AMap.MouseTool", // 鼠标工具插件
            "AMap.DistrictSearch", // 行政区查询服务，提供了根据名称关键字、citycode、adcode 来查询行政区信息的功能
            // "Map3D"
          ], () => {
            // 实例化Autocomplete
            var autoOptions = {
              //city 限定城市，默认全国
              city: '全国'
            }

            // console.log(new AMap,'new AMap')
            // var autoComplete= new AMap.Autocomplete(autoOptions);
            // autoComplete.search(keyword, function(status, result) {
            // 搜索成功时，result即是对应的匹配数据
            // })

            // 在图面添加工具条控件，工具条控件集成了缩放、平移、定位等功能按钮在内的组合控件
            this.myMap.addControl(new AMap.ToolBar());

            // 在图面添加比例尺控件，展示地图在当前层级和纬度下的比例尺
            this.myMap.addControl(new AMap.Scale());

            // // 在图面添加鹰眼控件，在地图右下角显示地图的缩略图
            // this.myMap.addControl(new AMap.OverView({isOpen: true}));

            // 在图面添加类别切换控件，实现默认图层与卫星图、实施交通图层之间切换的控制
            this.myMap.addControl(new AMap.MapType());

            // 在图面添加定位控件，用来获取和展示用户主机所在的经纬度位置
            this.myMap.addControl(new AMap.Geolocation());

            // 测距、测面积使用
            this.mouseTool = new AMap.MouseTool(this.myMap);

            this.myMap.addControl(new AMap.MapType())
            /**
             *  行政区搜索使用
             */
            this.district = new AMap.DistrictSearch({
              level: 'city',// 关键字对应的行政区级别，country：国家 province：省/直辖市 city：市 district：区/县 biz_area：商圈
              showbiz: true,  // 是否显示商圈，默认值true 可选为true/false，为了能够精准的定位到街道，特别是在快递、物流、送餐等场景下，强烈建议将此设置为false
              extensions: 'all', //否返回行政区边界坐标点，默认值：base，不返回行政区边界坐标点，取值：all，返回完整行政区边界坐标点
              subdistrict: 1, //  0：不返回下级行政区 1：返回下一级行政区 2：返回下两级行政区 3：返回下三级行政区(默认值 1)
            });
          }
      );
      /**
       *  鼠标工具---------------------------
       */
      // this.startRanging()  // 测距调用
      // this.startMeasuredArea()  // 测面积调用
      // this.startPolygon()  // 鼠标绘制区域
      // 停止 测距、面积...
      // setTimeout(() => {
      //   this.mouseTool.close(true); //关闭，并清除覆盖物
      // }, 10000);
    },
    //  开始测距
    startRanging() {
      this.mouseTool.rule({
        startMarkerOptions: {
          //可缺省
          icon: new AMap.Icon({
            size: new AMap.Size(19, 31), //图标大小
            imageSize: new AMap.Size(19, 31),
            image: "//webapi.amap.com/theme/v1.3/markers/b/start.png",
          }),
          offset: new AMap.Pixel(-9, -31),
        },
        endMarkerOptions: {
          //可缺省
          icon: new AMap.Icon({
            size: new AMap.Size(19, 31), //图标大小
            imageSize: new AMap.Size(19, 31),
            image: "//webapi.amap.com/theme/v1.3/markers/b/end.png",
          }),
          offset: new AMap.Pixel(-9, -31),
        },
        midMarkerOptions: {
          //可缺省
          icon: new AMap.Icon({
            size: new AMap.Size(19, 31), //图标大小
            imageSize: new AMap.Size(19, 31),
            image: "//webapi.amap.com/theme/v1.3/markers/b/mid.png",
          }),
          offset: new AMap.Pixel(-9, -31),
        },
        lineOptions: {
          //可缺省
          strokeStyle: "solid",
          strokeColor: "#FF33FF",
          strokeOpacity: 1,
          strokeWeight: 2,
        },
        //同 RangingTool 的 自定义 设置，缺省为默认样式
      });
    },
    //  开始测面积
    startMeasuredArea() {
      this.mouseTool.measureArea({
        strokeColor: "#80d8ff",
        fillColor: "#80d8ff",
        fillOpacity: 0.3,
      });
    },
    //  鼠标绘制区域
    startPolygon() {
      this.mouseTool.polygon({
        strokeColor: "#1791fc",
        strokeOpacity: 1,
        strokeWeight: 1,
        fillColor: "#1791fc",
        fillOpacity: 0.4,
        strokeStyle: "solid",
        extData: {
          id: "123",
        },
      });
      this.mouseTool.on("draw", (event) => {
        let poly = event.obj; // 绘制完多边形实例对象
        console.log(poly.getOptions(), "绘制图形顶点信息列表-------->");
        console.log(poly.getOptions().path, "绘制图形的自定义属性--------> ");
        // this.mouseTool.close();
        // this.mouseTool = null;
      });
    },
    /**
     *  可以监听input框 去调用该方法
     *  最好做防抖处理
     *  行政区域查询
     */
    seachArea1() {
      if (!this.inputValue) {
        this.myMap.remove(areaPolygons) //清除上次结果
        return
      }
      console.log(this.inputValue, 'value-->>')
      console.log(this.district, 'district-->>')
      this.district.search(this.inputValue, (status, result) => {
        console.log(result, 'result-->>')
        this.myMap.remove(areaPolygons) //清除上次结果
        areaPolygons = [];
        var bounds = result.districtList[0].boundaries;
        if (bounds) {
          for (var i = 0, l = bounds.length; i < l; i++) {
            //生成行政区划polygon
            var polygon = new AMap.Polygon({
              strokeWeight: 3,
              path: bounds[i],
              fillOpacity: 0,
              fillColor: '#80d8ff',
              strokeColor: 'red'
            });
            areaPolygons.push(polygon);
          }
        }
        //添加高度面 - 3D
        var object3Dlayer = new AMap.Object3DLayer({zIndex: 1});
        this.myMap.add(object3Dlayer);
        var wall = new AMap.Object3D.Wall({
          path: bounds,
          height: 30000,
          color: "#0088ffcc",
        });
        wall.transparent = true;
        wall.backOrFront = "both";
        object3Dlayer.add(wall);
        //  - 3D 结束
        console.log(areaPolygons)
        this.myMap.add(areaPolygons)
        this.myMap.setFitView(areaPolygons);//视口自适应
      });
    },
    //  只显示搜索的区域
    seachArea2() {
      if (!this.inputValue) {
        this.myMap.remove(areaPolygons)//清除上次结果
        return
      }
      console.log(this.inputValue, 'value-->>')
      console.log(this.district, 'district-->>')
      this.district.search(this.inputValue, (status, result) => {
        // 外多边形坐标数组和内多边形坐标数组
        let outer = [
          new AMap.LngLat(-360, 90, true),
          new AMap.LngLat(-360, -90, true),
          new AMap.LngLat(360, -90, true),
          new AMap.LngLat(360, 90, true),
        ];
        let holes = result.districtList[0].boundaries;
        let pathArray = [outer];
        pathArray.push.apply(pathArray, holes);
        let polygon = new AMap.Polygon({
          pathL: pathArray,
          strokeColor: "rgb(35,226,246)",
          strokeWeight: 4,
          strokeOpacity: 0.5,
          fillColor: "#f4f4f4",
          fillOpacity: 1,
          strokeDasharray: [10, 2, 10],
        });
        polygon.setPath(pathArray);
        this.myMap.add(polygon);
      });
    }
  },
};
</script>
<style scoped>
#GaodeMapContainer {
  width: 100%;
  height: 500px;
  position: relative;
}

#myInput {
  position: absolute;
  left: 10%;
  top: 5%;
  z-index: 999;
}

#GaodeMapContainer >>> .el-dialog__header {
  padding: 20px 20px 10px;
}
</style> 