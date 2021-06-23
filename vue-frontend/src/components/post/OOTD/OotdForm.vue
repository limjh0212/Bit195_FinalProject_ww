<template>
  <v-container>
    <div>
<!--        <table>-->
<!--            <thead>-->
<!--                <tr>-->
<!--                    <th class="text-left">No.</th>-->
<!--                    <th class="text-left">Title</th>-->
<!--                    <th class="text-left">작성자</th>-->
<!--                    <th class="text-left">작성일</th>-->

<!--                </tr>-->
<!--            </thead>-->
<!--            <tbody>-->
<!--                <tr v-for="(item, idx) in items" :key="idx">-->
<!--                    <td>{{ item.postnum }}</td>-->
<!--                    <td><a :href="`/post/OOTD/${item.postnum}`">{{ item.title }}</a></td>-->
<!--                    <td>{{ item.writer }}</td>-->
<!--                    <td v-if="$moment(item.regdate).format('YYYY-MM-DD')===$moment().format('YYYY-MM-DD')">-->
<!--                        {{ $moment(item.regdate).format('HH:mm:ss') }}-->
<!--                    </td>-->
<!--                    <td v-else>{{ $moment(item.regdate).format('YYYY-MM-DD') }}</td>-->
<!--                </tr>-->
<!--            </tbody>-->
<!--        </table>-->
        <table>
<!--          <tr class="row" v-for="(ootd, idx) in ootdList" :key="idx">-->
<!--            <td class="card"><img class="imgCard" :src="ootd"></td>-->
<!--          </tr>-->
          <tr v-for="item in list()" class="row" >
            <td class="card"><img :src="item[0]" class="imgCard"></td>
            <td class="card"><img :src="item[1]" class="imgCard"></td>
            <td class="card"><img :src="item[2]" class="imgCard"></td>
            <td class="card"><img :src="item[3]" class="imgCard"></td>
          </tr>
        </table>
        <LoadingSpinner v-if="isLoading"></LoadingSpinner>
    </div>
  </v-container>
</template>

<script>
import LoadingSpinner from "@/components/common/LoadingSpinner";
import {getOotdList} from "@/api/img";

export default {
    components: {LoadingSpinner},
    data() {
        return {
            items    : [],
            isLoading: false,
            ootdList : [],
        }
    },
    methods: {
        // async fetchData() {
        //     this.isLoading = true;
        //     const {data} = await OOTDList();
        //     this.isLoading = false;
        //     this.items = data.posts;
        // },
          async fetchOotd(){
              this.isLoading = true;
              let imgSrc = '';
              const {data} = await getOotdList();
              this.isLoading = false;
              for (var i = 0; i< data.length; i++){
                imgSrc = "data:image/png;base64," + data[i];
                this.ootdList.push(imgSrc);
              }
              console.log(this.ootdList);
          },
          list: function () {
            var line = [];
            var imgList = [];
            for (var i = 0; i < this.ootdList.length; i+=4) {
              imgList.push(this.ootdList[i]);
              imgList.push(this.ootdList[i+1]);
              imgList.push(this.ootdList[i+2]);
              imgList.push(this.ootdList[i+3]);
              line.push(imgList.slice(i,i+4));
            }
            console.log(line);
            return line;
          },
    },
    created() {
        //this.fetchData();
      this.fetchOotd();
    }
}
</script>

<style scoped>
.text-left {
    width: 10rem;
}
.row{
  margin: 30px;
}
.card{
  width: 20%;
  height: 300px;
  border-radius: 15px;
  overflow: hidden;
  margin-left: 15px;
  margin-right: 15px;
}
.imgCard{
  width: 100%;
  height: 100%;
  object-fit: revert;
}
</style>