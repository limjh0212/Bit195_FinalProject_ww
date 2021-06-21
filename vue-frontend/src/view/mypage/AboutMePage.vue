<template>
    <div>
      <div class="mypageImg">
        <img class="profile" :src="src">
      </div>
      <div>
        <p>이름 : {{ item.id }}</p>
        <p>닉네임 : {{ item.nickname }}</p>
        <p>email : {{ item.email }}</p>
        <p>가입일 : {{ $moment(item.regdate).format('YYYY-MM-DD') }}</p>
      </div>
    </div>
</template>

<script>
import {aboutme, getimg} from "@/api/member";

export default {
    data() {
        return {
            item: [],
            src : "",
        };
    },
    methods: {
        async fetchData() {
            const {data} = await aboutme(this.$store.state.id);
            this.item = data
        },
        async fetchImg() {
            var base = this;
            await getimg(this.$store.state.img).then(function (response){
                let base64String = btoa(String.fromCharCode.apply(null, new Uint8Array(response.data)));
                base.src = "data:image/png;base64,"+base64String;
            });
        }
    },
    created() {
        this.fetchData();
        this.fetchImg();
    }
}
</script>

<style scoped>
.mypageImg{

  width: 150px;
  height: 150px;
  border-radius: 70%;
  overflow: hidden;
  margin-left: 20px;
  margin-top : 20px;
  margin-bottom: 20px;
  margin-right: 20px;
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>