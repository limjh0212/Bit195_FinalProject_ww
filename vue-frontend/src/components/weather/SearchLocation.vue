<template>
    <div>
        <!--검색-->
        <div style="margin: 20px">
            <input v-model="keyword" placeholder="검색어" type="text"></input>
            <button @click="SearchKeyword">입력</button>
            <ul>
                <li v-for="(item, i) in data"><span>{{ item.city }} {{ item.address1 }} {{ item.address2 }}</span>
                    <button @click="save(i)">선택</button>
                </li>
            </ul>
        </div>

        <!--조회-->
        <div style="margin: 20px">
            <table>
                <tr v-for="(item, i) in locations">
                    <td>{{ item.address2 }}</td>
                    <td @click.prevent="deleteLocation(item.num)">🐸</td>
                </tr>
            </table>
        </div>
        {{ message }}
    </div>
</template>

<script>
import {countLo, deleteLo, saveLo, searchLo, userLo,} from "@/api/weather";

export default {
    name: "SearchLocation",
    data() {
        return {
            keyword  : '',
            message  : '',
            data     : [],
            locations: [],
            location : {
                userid   : '',
                address2 : '',
                latitude : '',
                longitude: '',
            },

        }
    },
    methods: {
        //위치 검색
        async SearchKeyword() {
            const {data} = await searchLo(this.keyword);
            this.data = data;
        },

        //위치 저장
        async save(i) {
            const {data} = await countLo(this.$store.state.id)
            if (data === 3) {
                return this.message = "저장 개수가 초과되었습니다."
            } else {
                this.location = {
                    userid   : this.$store.state.id,
                    address2 : this.data[i].address2,
                    latitude : this.data[i].latitude,
                    longitude: this.data[i].longitude,
                }
                await saveLo(this.location);
                await this.fetchData();
            }
        },
        //저장 삭제
        async deleteLocation(num) {
            await deleteLo(num);
            await this.fetchData();
        },
        //조회
        async fetchData() {
            const {data} = await userLo(this.$store.state.id)
            this.locations = data;
        },

    },
    created() {
        this.fetchData();
    }
}
</script>

<style scoped>

</style>