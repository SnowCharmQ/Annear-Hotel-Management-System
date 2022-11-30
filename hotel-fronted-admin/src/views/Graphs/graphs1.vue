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
    this.$get(baseUrl + '/order/order/analysis/getLastMonthOrderNumber', params).then(data => {
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
          text: 'The order number in last month',
          textAlign: "left",
          left: "center",
          top: "0px",
          textStyle: {
            fontFamily: "'Open Sans Condensed', sans-serif",
            fontSize: 30,
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
            name: "order number",
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
  margin: 50px auto auto;
  width: 1000px;
  height: 600px;
}
</style>