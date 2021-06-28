<template>
    <div class="mypage">
        <div class="mypageImg">
            <img :src="src" class="profile">
        </div>
        <div class="mypageInfo">
            <p>ID : {{ item.id }}</p>
            <p>email : {{ item.email }}</p>
            <p>닉네임 : {{ item.nickname }}</p>
            <p>가입일 : {{ $moment(item.regdate).format('YYYY-MM-DD') }}</p>
        </div>
        <br>
        <div>
            <v-btn elevation="1" rounded small type="submit" @click="goback">뒤로
            </v-btn>&nbsp;&nbsp;&nbsp;&nbsp;

            <v-btn elevation="1" rounded small type="submit">수정
            </v-btn>
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
            await getimg(this.$store.state.img).then(function (response) {
                let base64String = btoa(String.fromCharCode.apply(null, new Uint8Array(response.data)));
                base.src = "data:image/png;base64," + base64String;
            });
        },
        goback(){
            this.$router.go(-1)
        }
    },
    created() {
        this.fetchData();
        this.fetchImg();
    }
}
</script>

<style scoped>
.mypage{
  margin: 20px;
}
.mypageImg {
    width: 150px;
    height: 150px;
    border-radius: 70%;
    overflow: hidden;
    margin: auto;

}

.profile {
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.mypageInfo{
  margin: 20px;
}
</style>