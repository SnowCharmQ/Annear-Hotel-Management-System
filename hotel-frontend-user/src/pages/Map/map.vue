<template>
  <div style="height:300px">

    <!--
      amap-manager： 地图管理对象
      vid：地图容器节点的ID
      zooms： 地图显示的缩放级别范围，在PC上，默认范围[3,18]，取值范围[3-18]；在移动设备上，默认范围[3-19]，取值范围[3-19]
      center： 地图中心点坐标值
      plugin：地图使用的插件
      events： 事件
    -->
    <div class="amap-box">
      <el-amap
          :amap-manager="amapManager"
          :vid="'amap-vue'"
          :zoom="zoom"
          :plugin="plugin"
          :center="center"
          :events="events"
      >
        <!-- 标记 -->
        <el-amap-marker
            v-for="(marker, index) in markers"
            :position="marker"
            :key="index"
        ></el-amap-marker>
      </el-amap>
    </div>
  </div>
</template>
<!-- <script  type="text/javascript" src="https://webapi.amap.com/maps?v=2.0;key=f3b4637de1fe910d5be8399da35b8ce7&plugin=AMap.Geolocation"></script> -->

<script>

import {AMapManager, lazyAMapApiLoaderInstance} from "vue-amap";

let amapManager = new AMapManager();
export default {
  props: ["city", "value", "longitude", "latitude", "isEdit", "center"],
  data() {
    let self = this;
    return {
      address: null,
      searchKey: "",
      amapManager,
      markers: [],
      searchOption: {
        city: this.city ? this.city : "全国",
        citylimit: true
      },
      // center: [121.329402, 31.228667],
      zoom: 5,
      lng: 0,
      lat: 0,
      loaded: false,
      events: {
        init() {
          lazyAMapApiLoaderInstance.load().then(() => {
            // self.initSearch();
          });
        },
        // 点击获取地址的数据
        click(e) {
          console.log(e)
          self.markers = [];
          let {lng, lat} = e.lnglat;
          self.lng = lng;
          self.lat = lat;
          self.center = [lng, lat];
          self.markers.push([lng, lat]);
          // 这里通过高德 SDK 完成
          let geocoder = new AMap.Geocoder({
            radius: 10000,
            extensions: "all"
          });
          geocoder.getAddress([lng, lat], function (status, result) {
            if (status === "complete" && result.info === "OK") {
              if (result && result.regeocode) {
                self.address = result.regeocode.formattedAddress;
                self.searchKey = result.regeocode.formattedAddress;
                self.$emit("updateLocation", lng, lat, self.searchKey);
                self.$nextTick();
              }
            }
          });
        }
      },
      // 一些工具插件
      plugin: [
        {
          // 定位
          showMaker: true,
          pName: "Geolocation",
          events: {
            init(o) {
              // o是高德地图定位插件实例
              o.getCurrentPosition((status, result) => {
                if (result && result.position) {
                  if (self.isEdit) {
                    // 设置经度
                    self.lng = self.longitude;
                    // 设置维度
                    self.lat = self.latitude;
                    // 设置坐标
                    self.center = [self.longitude, self.latitude];
                    self.markers.push([self.longitude, self.latitude]);
                  } else {
                    // 设置经度
                    self.lng = result.position.lng;
                    // 设置维度
                    self.lat = result.position.lat;
                    // 设置坐标
                    self.center = [self.lng, self.lat];
                    self.markers.push([self.lng, self.lat]);
                    self.$emit("updateLocation", self.lng, self.lat, result.formattedAddress);
                  }
                  // load
                  self.loaded = true;
                  // 页面渲染好后
                  self.$nextTick();
                }
              });

            }
          }
        }, {
          // 定位城市
          pName: "CitySearch",
          events: {
            init(o) {
              // o是高德地图定位插件实例
              o.getLocalCity(function (status, result) {
                if (status === "complete" && result.info === "OK") {
                  // 查询成功，result即为当前所在城市信息
                  console.log(result)
                }
              });
            }
          }
        },
      ]
    };
  },
  created() {
    console.log(this.center)
    console.log(this.value)
    this.getLocation()

    if (this.value) {
      console.log(this.center)
      this.searchKey = this.value;
      this.address = this.value;
      this.markers.push(this.center);
    }
    if (this.longitude && this.latitude) {
      this.lng = this.longitude;
      this.lat = this.latitude;
      this.center = [this.longitude, this.latitude];
      this.markers.push([this.longitude, this.latitude]);
    }
    if (this.center) {
      this.markers.push(this.center);
    }
  },
  methods: {
    getLocation() {
      const that = this;
      AMap.plugin("AMap.Geolocation", function () {
        var geolocation = new AMap.Geolocation({
          // 是否使用高精度定位，默认：true
          enableHighAccuracy: true,
          // 设置定位超时时间，默认：无穷大
          timeout: 10000,
          // 定位按钮的停靠位置的偏移量，默认：Pixel(10, 20)
          buttonOffset: new AMap.Pixel(10, 20),
          //  定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
          zoomToAccuracy: true,
          //  定位按钮的排放位置，RB表示右下
          buttonPosition: "RB"
        });

        geolocation.getCurrentPosition();
        AMap.event.addListener(geolocation, "complete", onComplete);
        AMap.event.addListener(geolocation, "error", onError);

        function onComplete(data) {
          // data是具体的定位信息
          console.log(data);
        }

        function onError(data) {
          // 定位出错
          console.log(data);
          // 失败之后调用这个方法，使用IP定位获取当前城市信息
          that.getLngLatLocation();
        }
      });
    },
    // IP定位获取当前城市信息
    getLngLatLocation() {
      AMap.plugin("AMap.CitySearch", function () {
        var citySearch = new AMap.CitySearch();
        citySearch.getLocalCity(function (status, result) {
          if (status === "complete" && result.info === "OK") {
            // 查询成功，result即为当前所在城市信息
            console.log(result)
          }
        });
      });
    },
    // searchOption(pois){
    //   console.log(pois)
    // },
    onSearchResult(pois) {
      //点击搜索后触发的事件
      this.input = document.querySelector(".search-box-wrapper input").value
      //这边类似模糊查询 会返回一个数组，我就直接取第一个值了。
      this.center = [pois[0].lng, pois[0].lat];
      this.markers.push([pois[0].lng, pois[0].lat]);
    },
    // 选择地址后自动定位到当前地址附近
    updateAddress(value, longitude, latitude) {
      this.searchKey = value;
      this.address = value;
      this.lng = longitude;
      this.lat = latitude;
      this.center = [longitude, latitude];
      this.markers.push([longitude, latitude]);
    },
    initSearch() {
      let vm = this;
      let map = this.amapManager.getMap();
      AMapUI.loadUI(["misc/PoiPicker"], function (PoiPicker) {
        console.log(PoiPicker)
        let poiPicker = new PoiPicker({
          input: "search",
          placeSearchOptions: {
            map: map,
            pageSize: 10
          },
          suggestContainer: "searchTip",
          searchResultsContainer: "searchTip"
        });
        vm.poiPicker = poiPicker;
        // 监听poi选中信息
        poiPicker.on("poiPicked", function (poiResult) {
          let source = poiResult.source;
          let poi = poiResult.item;
          if (source !== "search") {
            poiPicker.searchByKeyword(poi.name);
          } else {
            poiPicker.clearSearchResults();
            vm.markers = [];
            let lng = poi.location.lng;
            let lat = poi.location.lat;
            let address = poi.name; // poi.cityname + poi.adname + poi.name
            vm.center = [lng, lat];
            vm.markers.push([lng, lat]);
            vm.lng = lng;
            vm.lat = lat;
            vm.address = address;
            vm.searchKey = address;
            vm.$emit("updateLocation", lng, lat, vm.searchKey);
          }
        });
      });
    },
    searchByHand() {
      console.log('zh ')
      // if (this.searchKey !== "" && this.poiPicker) {

      this.poiPicker.searchByKeyword(this.searchKey);
      // }
    }
  },
  watch: {
    center: function (val) {
      this.markers.push(val);
    }
  }
};
</script>
<style lang="scss">
.search-box {
  margin-top: 6px;
  width: 100%;
}

.el-vue-search-box-container {
  /*width: 100% !important;*/
  margin-bottom: 10px;
}

.search-box input {
  padding: 0 15px;
  width: 100%;
  height: 32px;
  line-height: 32px;
  color: #606266;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
}

.search-box input:focus {
  border-color: #409eff;
  outline: 0;
}

.search-box input::-webkit-input-placeholder {
  color: #c0c4cc;
}

.tip-box {
  width: 100%;
  max-height: 280px;
  position: absolute;
  top: 72px;
  z-index: 10000;
  overflow-y: auto;
  background-color: pink;
}
</style>
<style>
.amap-ui-poi-picker-sugg,
.amap_lib_placeSearch {
  border: 1px solid #eee;
  border-radius: 4px;
}

.amap-box {
  height: inherit;
  width: 100%;
  height: 550px;
}

</style>