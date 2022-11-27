<template>
  <div>
    <!-- echarts -->
    <div id="line_graph" class="main_container"></div>
    <div id="pie_graph" class="main_container"></div>
    <div id="bar_graph" class="main_container"></div>
    <div id="star_graph" class="main_container"></div>
  </div>
</template>
<script>
export default {
  data() {
    return {}
  },
  mounted() {
    this.initCharts()
  },
  methods: {
    initCharts() {
      // 初始化echarts实例
      var myChart = this.$echarts.init(document.getElementById("line_graph"))
      // 绘制图表
      myChart.setOption({
        title: {
          text: 'The income in the past 10 years',
          textAlign: "left",
          left: "center",
          top: "0px",
          textStyle: {
            // fontFamily: "'Open Sans Condensed', sans-serif",
            fontSize: 20,
            // fontStyle: 'italic',
            fontWeight: 'bold'
          }
        },
        tooltip: {},
        xAxis: {
          data:  [2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022]
        },
        yAxis: {},
        series: [
          {
            name: "销量",
            type: "line",
            data: [5, 20, 36, 23, 10, 20, 23, 23, 45, 23, 23],
            smooth: true
          },
        ],
      })

      var myChart2 = this.$echarts.init(document.getElementById("pie_graph"))
      // 绘制图表
      myChart2.setOption({
        title: {
          text: 'The income in different cities',
          textAlign: "left",
          left: "center",
          top: "10px",
          textStyle: {
            // fontFamily: "'Open Sans Condensed', sans-serif",
            fontSize: 20,
            // fontStyle: 'italic',
            fontWeight: 'bold'
          }
        },
        // legend: {
        //   top: 'bottom'
        // },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        series: [
          {
            name: 'Nightingale Chart',
            type: 'pie',
            radius: [50, 250],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: [
              { value: 40, name: 'rose 1' },
              { value: 38, name: 'rose 2' },
              { value: 32, name: 'rose 3' },
              { value: 30, name: 'rose 4' },
              { value: 28, name: 'rose 5' },
              { value: 26, name: 'rose 6' },
              { value: 22, name: 'rose 7' },
              { value: 18, name: 'rose 8' }
            ]
          }
        ]
      })

      var myChart3 = this.$echarts.init(document.getElementById("bar_graph"))

      myChart3.setOption(
        {
          title: {
            text: 'The income in this year',
            textAlign: "left",
            left: "center",
            top: "10px",
            textStyle: {
              // fontFamily: "'Open Sans Condensed', sans-serif",
              fontSize: 20,
              // fontStyle: 'italic',
              fontWeight: 'bold'
            }
          },
          xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              data: [
                120,
                {
                  value: 200,
                  itemStyle: {
                    color: '#a90000'
                  }
                },
                150,
                80,
                70,
                110,
                130
              ],
              type: 'bar'
            }
          ]
        }
      )
    
      var myChart4 = this.$echarts.init(document.getElementById("star_graph"))

      myChart4.setOption(
          {
          title: {
            text: 'The comments star distribution',
            textAlign: "left",
            left: "center",
            top: "0px",
            textStyle: {
              // fontFamily: "'Open Sans Condensed', sans-serif",
              fontSize: 20,
              // fontStyle: 'italic',
              fontWeight: 'bold'
            }
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '7%',
            left: 'center'
          },
          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: ['40%', '70%'],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
              },
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: '40',
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: [
                { value: 1048, name: '5 Star' },
                { value: 735, name: '4 Star' },
                { value: 580, name: '3 Star' },
                { value: 484, name: '2 Star' },
                { value: 300, name: '1 Star' }
              ]
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