<template>
    <div id="signup-form" class="card-select">
        <h4>선호하는 스타일을 선택하세요</h4>
        <p style="margin: 10px; font-size: 15px">최대 3개 선택 가능</p>

        <div>
            <div class="card-select1">
                <div v-for="(item,i) in recoImgList" v-if="i<6" class="card-select-box">
                    <div class="card-select-box-default"><img :src="item"
                                                              :style="isEffectImg(i)?'border: 3px solid crimson;':''" class="card-select-img" @click="checkImg(i)">
                    </div>
                </div>
            </div>
            <div class="card-select2">
                <div v-for="(item,i) in recoImgList" v-if="i>5" class="card-select-box">
                    <div class="card-select-box-default"><img :src="item"
                                                              :style="isEffectImg(i)?'border: 3px solid crimson;':''"
                                                              class="card-select-img" @click="checkImg(i)">
                    </div>
                </div>
            </div>
        </div>


        <div style="margin: 20px;">
            <v-btn elevation="1" rounded small @click="submitForm">
                확인<i class="material-icons" style="font-size: 15px">check</i>
            </v-btn>
        </div>


        <div style="font-size: 14px; display: block">
            {{ message }}
        </div>

    </div>
</template>

<script>
import {getFileNameList, getRecoImgList, userPreference} from "@/api/img";

export default {
    name: "SelectImg",
    data() {
        return {
            recoImgList: [],
            imgFileName: [],
            selectImg  : [],
            effectImg  : [],
            message    : ''
        }
    },
    methods: {
        isEffectImg(i) {
            if (this.effectImg.includes(i)) {
                return true;
            }
        },
        async fetchRecoImgList() {
            const {data} = await getRecoImgList();
            let imgsrc = '';
            let imgList = [];
            for (var i = 0; i < data.length; i++) {
                imgsrc = "data:image/png;base64," + data[i];
                imgList.push(imgsrc);
            }
            this.recoImgList = imgList;
        },
        async fetchFileNameList() {
            const {data} = await getFileNameList();
            this.imgFileName = data
        },
        checkImg(i) {
            if (this.selectImg.includes(this.imgFileName[i])) {
                this.selectImg.pop(this.imgFileName[i]);
                this.effectImg.pop(i);
            } else if (this.selectImg.length < 3) {
                this.selectImg.push(this.imgFileName[i]);
                this.effectImg.push(i);
            }

            if (this.selectImg.length === 3) {
                this.message = '선택이 완료되었습니다.'
            } else {
                this.message = '';
            }
        },
        async submitForm() {
            if (this.selectImg.length < 1) {
                this.message = '스타일이 선택되지 않았습니다.';
            } else {
                const Data = {
                    user_id: this.$store.state.id,
                    img_id : this.selectImg
                };
                await userPreference(Data);
                console.log(Data);
                await this.$router.push('/login');
            }
        }
    },
    created() {
        this.fetchRecoImgList();
        this.fetchFileNameList();
    }
}
</script>

<style scoped>
@import '../../css/common/signup.css';

on {

}
</style>