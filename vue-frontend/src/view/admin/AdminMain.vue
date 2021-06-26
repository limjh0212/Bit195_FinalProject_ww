<template>
    <div class="content-view">
      <div class="row sparkboxes mt-4 mb-4">
        <div class="col-md-4">
          <div class="box box1">
            <div id="cntTotalMembers" class="spark">
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="box box2">
            <div id="cntTotalPosts" class="spark">
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="box box3">
            <div id="cntRecentQna" class="spark"></div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import {fetchAdminMain, fetchMember, fetchQna} from "@/api/admin";
import {randomizeArray, sparklineData} from "@/js/chartScripts";

export default {
  data() {
    return {
      cntTotalPosts : '',
      cntMembers   : '',
      cntRecentQuestion  : '',
    }
  },
  methods: {
    async fetchMemberData() {
      const {data} = await fetchMember();
      this.cntMembers = data.cntMembers;
      this.cntWthdrFalse = data.cntWthdrFalse;
      this.cntWthdrTrue = data.cntWthdrTrue;
      var spark1 = {
        chart: {
          id: 'sparkline1',
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
          text: this.cntMembers+' 명',
          offsetX: 30,
          style: {
            fontSize: '24px',
            cssClass: 'apexcharts-yaxis-title'
          }
        },
        subtitle: {
          text: '회원 현황',
          offsetX: 30,
          style: {
            fontSize: '14px',
            cssClass: 'apexcharts-yaxis-title'
          }
        }
      }
      var elSpark1 = document.getElementById("cntTotalMembers");
      var spark1Chart = new ApexCharts(elSpark1, spark1);
      await spark1Chart.render();
    },
    async fetchCmntyData() {
      const {data} = await fetchAdminMain();
      this.cntTotalPosts = data.cntTotalPosts;
      var spark2 = {
        chart: {
          id: 'sparkline2',
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
          text: '커뮤니티 현황',
          offsetX: 30,
          style: {
            fontSize: '14px',
            cssClass: 'apexcharts-yaxis-title'
          }
        }
      }
      var elSpark2 = document.getElementById("cntTotalPosts");
      var spark2Chart =new ApexCharts(elSpark2, spark2);
      await spark2Chart.render();
    },
    async fetchQnaData(){
      const {data} = await fetchQna();
      this.cntRecentQuestion = data.cntRecentQuestion;

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
          name: '문의 수',
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
          text: this.cntRecentQuestion+' 개',
          offsetX: 30,
          style: {
            fontSize: '24px',
            cssClass: 'apexcharts-yaxis-title'
          }
        },
        subtitle: {
          text: '주간 QnA 현황',
          offsetX: 30,
          style: {
            fontSize: '14px',
            cssClass: 'apexcharts-yaxis-title'
          }
        }
      }
      var elSpark3 = document.getElementById("cntRecentQna");
      var spark3Chart =new ApexCharts(elSpark3, spark3);
      await spark3Chart.render();
    }
  },
  created() {
    this.fetchMemberData();
    this.fetchCmntyData();
    this.fetchQnaData();
  },
}
</script>

<style scoped>
</style>