<template>
    <!--    <v-container>-->
    <div class="search-location" style="margin: 0 auto; justify-content: center">
        <!--검색-->
        <div class="search" style="margin: 0 auto;">
            <h5 style="text-align: left;margin: 20px">위치 검색</h5>
            <div style="margin: 20px">
                <input v-model="keyword" placeholder="동 이름을 입력하세요" style="width: 500px" type="text"></input>
                <button @click="SearchKeyword"><i class="material-icons" style="font-size: 25px">search</i></button>
                <v-card v-if="data!=''" style="width: 100%; margin: 0 auto;">
                    <v-simple-table dense>
                        <thead>
                            <tr>
                                <td style="text-align: center">no.</td>
                                <td style="text-align: center">주소1</td>
                                <td style="text-align: center">주소2</td>
                                <td style="text-align: center">행정구역</td>
                                <td style="text-align: center">추가</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item, i) in data">
                                <td style="text-align: center">{{ i + 1 }}</td>
                                <td style="text-align: center">{{ item.city }}</td>
                                <td style="text-align: center">{{ item.address1 }}</td>
                                <td style="text-align: center">{{ item.address2 }}</td>
                                <td style="text-align: center">
                                    <button @click="save(i)"><i class="material-icons" style="font-size: 20px">add</i>
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            {{ message }}
                        </tfoot>
                    </v-simple-table>
                </v-card>
            </div>
        </div>

        <!--조회-->
        <!--          <div style="margin: 0 auto; ">-->
        <div class="search-result" style="margin: 0 auto;">
            <h5 style="text-align: left;margin-bottom: 20px">위치 정보</h5>
            <table style="width: 200px;">
                <tr v-for="(item, i) in locations" class="current-location">
                    <td>{{ item.address2 }}</td>
                    <td @click.prevent="deleteLocation(item.num)"><i class="material-icons" style="font-size: 20px">clear</i>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <!--    </v-container>-->
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
.search-location {
    width: 100%;
    position: relative;
    text-align: center;
    background-color: #ffffff;
    border-radius: 5px;
}

.search-location div {
    display: inline-block;
}

.search {
    float: left;
    width: 50%;
    padding-top: 20px;
}

.search-result {
    float: right;
    width: 40%;
    margin: 20px;
    text-align: center;
    padding-top: 20px;
    padding-left: 20px;
}

.current-location {
    width: 160px;
    height: 50px;
    background-color: #ffffff;
    padding-top: 30px;
    margin-bottom: 10px;
    border: 1px solid #D8D8D8;

}

td {
    text-align: center;
}
</style>