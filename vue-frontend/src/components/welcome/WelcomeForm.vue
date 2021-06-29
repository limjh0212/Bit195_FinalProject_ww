<template>
    <div id="welcome" class="welcome">
        <div class="welcome-logo">
            <div v-for="(item,i) in welcomeImgList" v-if="i<2" class="welcome-logo-box">
                <img :src="item" class="welcome-logo-img">
            </div>
        </div>
        <div class="welcome-service">
            <div v-for="(item,i) in welcomeImgList" v-if="i>1" class="welcome-service-box">
                <img :src="item" class="welcome-service-img">
            </div>
        </div>
        <div class="welcome-btn">
            <div class="welcome-btn-left">
                <router-link to="/login">
                    <div class="welcome-btn-login">LOGIN</div>
                </router-link>
            </div>
            <div class="welcome-btn-right">
                <router-link to="/signup">
                    <div class="welcome-btn-signup">JOIN</div>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script>
import {getWelcomeImg} from "@/api/img";

export default {
    name: "WelcomeForm",
    data() {
        return {
            welcomeImgList: [],
        }
    },
    methods: {
        async fetchImgList() {
            const {data} = await getWelcomeImg();
            let imgsrc = '';
            let imgList = [];
            for (let i = 0; i < data.length; i++) {
                imgsrc = "data:image/png;base64," + data[i];
                imgList.push(imgsrc);
            }
            this.welcomeImgList = imgList;
        },
    },
    created() {
        this.fetchImgList()
    }
}
</script>

<style scoped>
@import "../../css/common/welcome.css";
</style>