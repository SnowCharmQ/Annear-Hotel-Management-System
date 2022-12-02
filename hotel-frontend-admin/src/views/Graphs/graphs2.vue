<template>
  <div>
    <!-- echarts -->
    <div id="bar_graph" class="main_container"></div>
  </div>
</template>
<script>

let baseUrl = 'http://localhost:12345';

export default {
  data() {
    return {
      xdata: '',
      ydata: ''
    }
  },
  mounted() {
    let params = {'hotelId': 1};
    this.$get(baseUrl + '/order/order/analysis/getLastYearRoomTypeOrderNumber', params).then(data => {
      this.xdata = data.data.xdata
      this.ydata = data.data.ydata
      this.initCharts()
    }).catch(err => {
      this.$message.error("Network Error")
    })
  },
  methods: {
    initCharts() {
      // 初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("bar_graph"))

      myChart.setOption(
        {
          title: {
            text: 'The different room types\' order number in the last year',
            textAlign: "left",
            left: "center",
            top: "10px",
            textStyle: {
              fontFamily: "'Open Sans Condensed', sans-serif",
              fontSize: 30,
              fontStyle: 'italic',
              fontWeight: 'bold'
            }
          },
          xAxis: {
            type: 'category',
            data: this.xdata//['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              data: this.ydata,
              type: 'bar'
            }
          ]
        }
      )
    },
  },
}
</script>
<style lang="scss" scoped>
.main_container {
  margin: auto;
  margin-top: 50px;
  width: 1000px;
  height: 600px;
//   overflow: hidden;
}
</style>