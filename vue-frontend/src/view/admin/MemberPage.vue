<template>
    <div>
        <p>현재 회원 : {{ cntWthdrFalse }}</p>
        <p>탈퇴 회원 : {{ cntWthdrTrue }}</p>
        <p>전체 회원 : {{ cntMembers }}</p>
        <p>일주일 간 회원 현황</p>
        <table>
          <thead>
            <tr>
              <th>날짜</th>
              <th>가입 회원</th>
              <th>탈퇴 회원</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in list()">
              <td>{{item[0]}}</td>
              <td>{{item[1]}}</td>
              <td>{{item[2]}}</td>
            </tr>
          </tbody>
        </table>
    </div>
</template>

<script>
import {fetchMember} from "@/api/admin";

export default {
    data() {
        return {
            cntWthdrFalse: '',
            cntWthdrTrue : '',
            cntMembers   : '',
            cnt7days     : [],
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
        },
        list: function () {
          var cnt1day = [];
          var cntWeek = [];
          for (var i = 0; i < this.cnt7days.length; i+=3) {
            cntWeek.push(this.cnt7days[i]);
            cntWeek.push(this.cnt7days[i+1]);
            cntWeek.push(this.cnt7days[i+2]);
            cnt1day.push(cntWeek.slice(i,i+3));
          }
          console.log(cnt1day);
          return cnt1day;
        },
    },
    created() {
        this.fetchData()
    }

}
</script>

<style scoped>

</style>