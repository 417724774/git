<template>
  <div id="main" style="width:auto;height: 400px;"></div>
</template>
<script>
import * as echarts from 'echarts'
export default {
  name:'Tpie',
  data(){
    return {
      chartColumn: null,
      piedata:[]
    }
  },
  mounted() {
    const _this = this
    _this.$axios.get('/teacher/countstuwork',{
      headers: {
        Authorization: localStorage.getItem("token")
      }
    }).then(res=>{
      _this.piedata = res.data.data
    }).finally(()=>{
      this.drawLine(_this.piedata);
    })

  },
  methods: {
    drawLine(data){
      this.chartColumn = echarts.init(document.getElementById('main'));
      this.chartColumn.setOption({
        title: {
          text: '学生就业统计图',
          subtext: '总人数：'+this.piedata[0],
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
        },
        series: [
          {
            name: '人数:',
            type: 'pie',
            radius: '50%',
            data: [
              {value: this.piedata[1], name: '已就业'},
              {value: this.piedata[2], name: '未就业'},
            ],
            roseType: 'angle',
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      });
    }
  },
  created() {

  }
}
</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
  list-style: none;
}
</style>