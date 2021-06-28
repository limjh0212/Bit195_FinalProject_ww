<template>
  <div class="content-view">
    <div class="row sparkboxes mt-4 mb-4">
      <div class="col-md-6">
        <div class="box box1">
          <div id="cntRecentQna" class="spark">
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <div class="box box2">
          <div id="cntNotAnswered" class="spark">
          </div>
        </div>
      </div>
    </div>

    <div class="row mt-4 mb-4">
      <div class="col-md-12">
        <div class="table-box">
          <div>
            <v-simple-table>
              <template v-slot:default>
                <thead>
                <tr>
                  <th class="text-left">요청번호</th>
                  <th class="text-left">제목</th>
                  <th class="text-left">작성자</th>
                  <th class="text-left">작성일</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(item, idx) in NotAnsweredQuestion" :key="idx">
                  <td>{{ item.postnum }}</td>
                  <td><a :href="`/post/qna/${item.num}`">{{ item.title }}</a></td>
                  <td>{{ item.writer }}</td>
                  <td v-if="$moment().format('YYYY-MM-DD') === $moment(item.regdate).format('YYYY-MM-DD')">
                    {{ $moment(item.regdate).format('HH:mm:ss') }}
                  </td>
                  <td v-else>{{ $moment(item.regdate).format('YYYY-MM-DD') }}</td>
                </tr>
                </tbody>
                <tfoot>
                <th colspan="6">
                    <div class="board-page">
                      <div class="page-list">
                        <div v-for="page in pageList">
                            <div class="page-box-default" @click="fetchData(page)">{{ page }}</div>
                        </div>
                      </div>
                    </div>
                </th>
                </tfoot>
              </template>
            </v-simple-table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {fetchQna} from "@/api/admin";
import {randomizeArray, sparklineData} from "@/js/chartScripts";

export default {
    data() {
        return {
            cntNotAnswered     : '',
            cntRecentQuestion  : '',
            NotAnsweredQuestion: [],
            pageList : [],
        }
    },
    methods: {
        async fetchData(num) {
            const {data} = await fetchQna(num);
            this.cntNotAnswered = data.cntNotAnswered;
            this.cntRecentQuestion = data.cntRecentQuestion;
            this.NotAnsweredQuestion = data.NotAnsweredQuestion;
            this.pageList = data.pageList;
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
              name: '문의 수',
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
              text: this.cntRecentQuestion+' 개',
              offsetX: 30,
              style: {
                fontSize: '24px',
                cssClass: 'apexcharts-yaxis-title'
              }
            },
            subtitle: {
              text: '최근 문의 현황',
              offsetX: 30,
              style: {
                fontSize: '14px',
                cssClass: 'apexcharts-yaxis-title'
              }
            }
          }
          var elSpark1 = document.getElementById("cntRecentQna");
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
              name: '문의 수',
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
              text: this.cntNotAnswered+' 개',
              offsetX: 30,
              style: {
                fontSize: '24px',
                cssClass: 'apexcharts-yaxis-title'
              }
            },
            subtitle: {
              text: '미답변 문의',
              offsetX: 30,
              style: {
                fontSize: '14px',
                cssClass: 'apexcharts-yaxis-title'
              }
            }
          }
          var elSpark2 = document.getElementById("cntNotAnswered");
          var spark2Chart =new ApexCharts(elSpark2, spark2);
          await spark2Chart.render();
        },
    },
    created() {
        this.fetchData(1);
    }
}
</script>

<style scoped>
</style>