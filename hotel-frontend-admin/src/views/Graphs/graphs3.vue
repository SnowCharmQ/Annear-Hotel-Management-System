<template>
  <div>
    <!-- echarts -->
    <div id="line_graph" class="main_container"></div>
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
    this.$get(baseUrl + '/order/order/analysis/getLastYearIncome', params).then(data => {
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
      let myChart = this.$echarts.init(document.getElementById("line_graph"))
      // 绘制图表
      myChart.setOption({
        title: {
          text: 'The income in the last year',
          textAlign: "left",
          left: "center",
          top: "0px",
          textStyle: {
            fontFamily: "'Open Sans Condensed', sans-serif",
            fontSize: 20,
            fontStyle: 'italic',
            fontWeight: 'bold'
          }
        },
        tooltip: {},
        xAxis: {
          data:  this.xdata//[2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022]
        },
        yAxis: {},
        series: [
          {
            name: "income",
            type: "line",
            data: this.ydata,//[5, 20, 36, 23, 10, 20, 23, 23, 45, 23, 23],
            smooth: true
          },
        ],
      })
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