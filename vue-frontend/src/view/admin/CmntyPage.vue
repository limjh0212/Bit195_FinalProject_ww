<template>
  <div class="content-view">
    <div class="row sparkboxes mt-4 mb-4">
      <div class="col-md-6">
        <div class="box box1">
          <div id="cntTotalPosts" class="spark">
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <div class="box box2">
          <div id="cntRecentPosts" class="spark">
          </div>
        </div>
      </div>
    </div>

    <div class="row mt-4 mb-4">
      <div class="col-md-6">
        <div class="box">
          <div id="donut"></div>
        </div>
      </div>
      <div class="col-md-6">
        <div class="box">
          <div id="bar">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {fetchCmnty} from "@/api/admin";
import {randomizeArray, sparklineData} from "@/js/chartScripts";


export default {
    data() {
        return {
            cntFreeBoard  : '',
            cntTotalPosts : '',
            cntRecentFB   : '',
            cntRecentOOTD : '',
            cntRecentPosts: '',
            cntOOTD       : '',
        }
    },
    methods: {
        async fetchData() {
            const {data} = await fetchCmnty();
            this.cntFreeBoard = data.cntFreeBoard;
            this.cntTotalPosts = data.cntTotalPosts;
            this.cntRecentFB = data.cntRecentFB;
            this.cntRecentOOTD = data.cntRecentOOTD;
            this.cntRecentHD = data.cntRecentHD;
            this.cntRecentPosts = data.cntRecentPosts
            this.cntOOTD = data.cntOOTD;
            var spark1 = {
              chart: {
                id: 'sparkline1',
                group: 'sparklines',
                type: 'area',
                width: '100%',
                height: 160,
                sparkline: {
                  enabled: true
                },
              },
              stroke: {
                curve: 'straight'
              },
              fill: {
                opacity: 1,
              },
              series: [{
                name: '포스팅 수',
                data: randomizeArray(sparklineData)
              }],
              labels: [...Array(24).keys()].map(n => `2021-06-0${n+1}`),
              yaxis: {
                labels:{
                  minWidth:24
                },
                min: 0
              },
              xaxis: {
                type: 'datetime',
              },
              colors: ['#008FFB'],
              title: {
                text: this.cntTotalPosts+' 개',
                offsetX: 30,
                style: {
                  fontSize: '24px',
                  cssClass: 'apexcharts-yaxis-title'
                }
              },
              subtitle: {
                text: '전체 포스팅 수',
                offsetX: 30,
                style: {
                  fontSize: '14px',
                  cssClass: 'apexcharts-yaxis-title'
                }
              }
            }
            var elSpark1 = document.getElementById("cntTotalPosts");
            var spark1Chart =new ApexCharts(elSpark1, spark1);
            await spark1Chart.render();
            var spark2 = {
              chart: {
                id: 'sparkline2',
                group: 'sparklines',
                type: 'area',
                width: '100%',
                height: 160,
                sparkline: {
                  enabled: true
                },
              },
              stroke: {
                curve: 'straight'
              },
              fill: {
                opacity: 1,
              },
              series: [{
                name: '포스팅 수',
                data: randomizeArray(sparklineData)
              }],
              labels: [...Array(24).keys()].map(n => `2021-06-0${n+1}`),
              yaxis: {
                labels:{
                  minWidth:24
                },
                min: 0
              },
              xaxis: {
                type: 'datetime',
              },
              colors: ['#FF4560'],
              title: {
                text: this.cntRecentPosts+' 개',
                offsetX: 30,
                style: {
                  fontSize: '24px',
                  cssClass: 'apexcharts-yaxis-title'
                }
              },
              subtitle: {
                text: '주간 포스팅 수',
                offsetX: 30,
                style: {
                  fontSize: '14px',
                  cssClass: 'apexcharts-yaxis-title'
                }
              }
            }
            var elSpark2 = document.getElementById("cntRecentPosts");
            var spark2Chart =new ApexCharts(elSpark2, spark2);
            await spark2Chart.render();
            var bar = {
                chart: {
                  id: '게시판 별 포스팅 수',
                  type: 'bar',
                  height: 380,
                  width: '100%',
                  stacked: true,
                },
                plotOptions: {
                  bar: {
                    columnWidth: '45%',
                  }
                },
                colors: ['#00D8B6','#008FFB'],
                series: [{
                  name: "최신 포스팅 수",
                  data: [this.cntRecentFB, this.cntRecentOOTD],
                }],
                xaxis: {
                  categories:['자유게시판', 'OOTD'],
                  labels: {
                    show: true,
                  },
                  axisBorder: {
                    show: false
                  },
                  axisTicks: {
                    show: false
                  },
                },
                yaxis: {
                  axisBorder: {
                    show: false
                  },
                  axisTicks: {
                    show: false
                  },
                  labels: {
                    style: {
                      colors: '#78909c'
                    }
                  }
                },
                title: {
                  text: '게시판 별 주간 포스팅 수',
                  align: 'left',
                  style: {
                    fontSize: '18px'
                  }
                }

              }
            var elBar = document.getElementById("bar");
            var chartBar = new ApexCharts(elBar, bar);
            await chartBar.render();
            var donut = {
                chart: {
                  type: 'donut',
                  width: '100%',
                  height: 400
                },
                dataLabels: {
                  enabled: false,
                },
                plotOptions: {
                  pie: {
                    customScale: 0.8,
                    donut: {
                      size: '75%',
                    },
                    offsetY: 20,
                  },
                  stroke: {
                    colors: undefined
                  }
                },
                colors: ['#FEB019', '#775DD0'],
                title: {
                  text: '게시판 별 전체 포스팅 수',
                  style: {
                    fontSize: '18px'
                  }
                },
                series: [this.cntFreeBoard, this.cntOOTD],
                labels: ['자유게시판', 'OOTD'],
                legend: {
                  position: 'left',
                  offsetY: 80
                }
              }
            var elDonut = document.getElementById("donut");
            var ChartDonut = new ApexCharts(elDonut, donut);
            await ChartDonut.render();
        },
    },
    created() {
        this.fetchData()
    }

}
</script>

<style scoped>

</style>