<template>
    <div>
        <p>이름 : {{ item.id }}</p>
        <p>닉네임 : {{ item.nickname }}</p>
        <p>email : {{ item.email }}</p>
        <p>가입일 : {{ $moment(item.regdate).format('YYYY-MM-DD') }}</p>
        <p>img : {{ item.img }}</p>
        <p v-html="img"></p>
    </div>
</template>

<script>
import {aboutme, getimg} from "@/api/member";

export default {
    data() {
        return {
            item: [],
            img : ``,
        };
    },
    methods: {
        async fetchData() {
            const {data} = await aboutme(this.$store.state.id);
            this.item = data
        },
        async fetchImg() {
            const {data} = await getimg(this.$store.state.img);
            this.img = data;
        }
    },
    created() {
        this.fetchData();
        this.fetchImg();
    }
}
</script>

<style scoped>

</style>