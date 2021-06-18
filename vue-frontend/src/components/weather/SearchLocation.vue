<template>
    <div>
        <input type="text" placeholder="검색어" v-model="keyword"></input>
        <button @click="SearchKeyword">입력</button>
        <ul>
            <li v-for="(item, idx) in data"><span>{{ item.city }} {{ item.address1 }} {{ item.address2 }}</span>
                <button @click="takeLocation(idx)">선택</button>
            </li>
        </ul>
        <button @click="save">저장</button>
        <button @click="listing">조회</button>
        <ul>
            <li v-for="(item, idx) in userlist"><span>{{ item.address2 }}{{ item.latitude }}{{ item.longitude }}</span>
            </li>
        </ul>
        {{ message }}
    </div>
</template>

<script>
import {countLo, saveLo, searchLo, userLo} from "@/api/weather";

export default {
    name: "SearchLocation",
    data() {
        return {
            keyword : '',
            message : '',
            data    : [],
            userlist: [],
            location: {
                userid   : '',
                address2 : '',
                latitude : '',
                longitude: '',
            }
        }
    },
    methods: {
        async SearchKeyword() {
            const {data} = await searchLo(this.keyword);
            this.data = data;
        },
        // 선택 값 저장
        takeLocation(i) {
            console.log(this.data[i]);
            this.location = {
                userid   : this.$store.state.id,
                address2 : this.data[i].address2,
                latitude : this.data[i].latitude,
                longitude: this.data[i].longitude
            }
        },
        //저장
        async save() {
            const {data} = await countLo(this.$store.state.id)
            if (data === 3) {
                return this.message = "저장 개수가 초과되었습니다."
            } else {
                await saveLo(this.location)
            }
        },

        //저장 목록 불러오기
        async listing() {
            const {data} = await userLo(this.$store.state.id)
            this.userlist = data;
        }

    }
}
</script>

<style scoped>

</style>