<template>
  <div>
    <!-- echarts -->
    <div id="pie_graph" class="main_container"></div>
  </div>
</template>
<script>

let baseUrl = 'http://localhost:12345';

export default {
  data() {
    return {
      xdata: [],
      ydata: [],
      data: []
    }
  },
  mounted() {
    let params = {'hotelId': 1};
    this.$get(baseUrl + '/order/order/analysis/getStarDistribution', params).then(data => {
      this.xdata = data.data.xdata
      this.ydata = data.data.ydata
      for (let i = 0; i < 5; i++) {
        let item ={
          value: this.ydata[i],
          name: this.xdata[i]
        }
        this.data.push(item)
      }
      console.log(this.data)
      this.initCharts()
    }).catch(err => {
      this.$message.error("Network Error")
    })
  },
  methods: {
    initCharts() {
      let myChart4 = this.$echarts.init(document.getElementById("pie_graph"))
      myChart4.setOption(
          {
          title: {
            text: 'The comments star distribution',
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
              data: this.data
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
  margin: 50px auto auto;
  width: 1000px;
  height: 600px;
//   overflow: hidden;
}
</style>