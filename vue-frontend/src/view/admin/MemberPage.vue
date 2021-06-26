<template>
  <div class="content-view">
    <div class="row sparkboxes mt-4 mb-4">
      <div class="col-md-4">
        <div class="box box1">
          <div id="cntMembers" class="spark">
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="box box2">
          <div id="cntJoinMem" class="spark">

          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="box box3">
          <div id="cntWthdrMem" class="spark">

          </div>
        </div>
      </div>
    </div>

    <div class="row mt-5 mb-4">
      <div class="col-md-12">
        <div class="box">
          <div id="cnt7daysMem">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {fetchMember} from "@/api/admin";
import {colorPalette, randomizeArray, sparklineData} from "@/js/chartScripts";

export default {
    data() {
        return {
            cntWthdrFalse: '',
            cntWthdrTrue : '',
            cntMembers   : '',
            cnt7days     : [],
            date7days : [],
            join7days:[],
            wthdr7days:[],
            cntTotal : [],
        }
    },
    methods: {
        async fetchData() {
            const {data} = await fetchMember();
            console.log(data);
            this.cntWthdrFalse = data.cntWthdrFalse;
            this.cntWthdrTrue = data.cntWthdrTrue;
            this.cntMembers = data.cntMembers;
            this.cnt7days = data.cnt7days;
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
                name: '회원 수',
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
                text: this.cntMembers+' 명',
                offsetX: 30,
                style: {
                  fontSize: '24px',
                  cssClass: 'apexcharts-yaxis-title'
                }
              },
              subtitle: {
                text: '전체 회원 수',
                offsetX: 30,
                style: {
                  fontSize: '14px',
                  cssClass: 'apexcharts-yaxis-title'
                }
              }
            }
            var elSpark1 = document.getElementById("cntMembers");
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
                name: '회원 수',
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
                text: this.cntWthdrFalse+' 명',
                offsetX: 30,
                style: {
                  fontSize: '24px',
                  cssClass: 'apexcharts-yaxis-title'
                }
              },
              subtitle: {
                text: '가입 회원 수',
                offsetX: 30,
                style: {
                  fontSize: '14px',
                  cssClass: 'apexcharts-yaxis-title'
                }
              }
            }
            var elSpark2 = document.getElementById("cntJoinMem");
            var spark2Chart =new ApexCharts(elSpark2, spark2);
            await spark2Chart.render();

            var spark3 = {
              chart: {
                id: 'sparkline3',
                group: 'sparklines',
                type: 'area',
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
                name: '회원 수',
                data: randomizeArray(sparklineData)
              }],
              labels: [...Array(24).keys()].map(n => `2021-06-0${n+1}`),
              xaxis: {
                type: 'datetime',
              },
              yaxis: {
                labels:{
                  minWidth:24
                },
                min: 0
              },
              colors: ['#00D8B6'],
              //colors: ['#5564BE'],
              title: {
                text: this.cntWthdrTrue+' 명',
                offsetX: 30,
                style: {
                  fontSize: '24px',
                  cssClass: 'apexcharts-yaxis-title'
                }
              },
              subtitle: {
                text: '탈퇴 회원 수',
                offsetX: 30,
                style: {
                  fontSize: '14px',
                  cssClass: 'apexcharts-yaxis-title'
                }
              }
            }
            var elSpark3 = document.getElementById("cntWthdrMem");
            var spark3Chart =new ApexCharts(elSpark3, spark3);
            await spark3Chart.render();

            var cnt1day = [];
            var cntWeek = [];
            for (var i = 0; i < this.cnt7days.length; i+=3) {
              cntWeek.push(this.cnt7days[i]);
              cntWeek.push(this.cnt7days[i+1]);
              cntWeek.push(this.cnt7days[i+2]);
              cnt1day.push(cntWeek.slice(i,i+3));
            }
            var dates = [];
            var joins = [];
            var wthdrs = [];
            var cntTotal =[];
            for (var k = 0; k<cnt1day.length; k++){
              dates.push(cnt1day[k][0]);
              joins.push(cnt1day[k][1]);
              wthdrs.push(cnt1day[k][2]);
              cntTotal.push(cnt1day[k][1]+cnt1day[k][2]);
            }
            this.date7days = dates;
            this.join7days = joins;
            this.wthdr7days = wthdrs;
            this.cntTotal = cntTotal;
            var bar = {
                chart: {
                  id: '일주일 간 가입 및 탈퇴 회원 현황',
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
                colors: ['#FEB019', '#775DD0'],
                series: [{
                  name: "가입 회원",
                  data: [this.join7days[0], this.join7days[1],this.join7days[2],this.join7days[3],this.join7days[4],this.join7days[5],this.join7days[6],this.join7days[7]],
                }, {
                  name: "탈퇴 회원",
                  data: [this.wthdr7days[0], this.wthdr7days[1],this.wthdr7days[2],this.wthdr7days[3],this.wthdr7days[4],this.wthdr7days[5],this.wthdr7days[6],this.wthdr7days[7]],
                }],
                labels: [this.cntTotal[0],this.cntTotal[1],this.cntTotal[2],this.cntTotal[3],this.cntTotal[4],this.cntTotal[5],this.cntTotal[6],this.cntTotal[7]],
                xaxis: {
                  categories:['오늘', this.date7days[1],this.date7days[2],this.date7days[3],this.date7days[4],this.date7days[5],this.date7days[6],this.date7days[7]],
                  labels: {
                    show: true
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
                  text: '주간 회원 현황',
                  align: 'left',
                  style: {
                    fontSize: '18px'
                  }
                }

              }
            var elBar = document.getElementById("cnt7daysMem");
            var chartBar = new ApexCharts(elBar, bar);
            await chartBar.render();
        },
        // list: function () {
        //   var cnt1day = [];
        //   var cntWeek = [];
        //   for (var i = 0; i < this.cnt7days.length; i+=3) {
        //     cntWeek.push(this.cnt7days[i]);
        //     cntWeek.push(this.cnt7days[i+1]);
        //     cntWeek.push(this.cnt7days[i+2]);
        //     cnt1day.push(cntWeek.slice(i,i+3));
        //   }
        //   console.log(cnt1day);
        //   this.listData = cnt1day;
        //   return cnt1day;
        // },
    },
    created() {
        this.fetchData();
    }

}
</script>

<style scoped>

</style>