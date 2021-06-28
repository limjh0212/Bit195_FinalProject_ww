<template>
    <v-container>
        <div style="justify-content: center">
            <!--검색-->
            <div style="margin: 20px">
                <input v-model="keyword" placeholder="동 이름을 입력하세요" style="width: 500px" type="text"></input>
                <button @click="SearchKeyword"><i class="material-icons" style="font-size: 25px">search</i></button>
                <v-card v-if="data!=''" style="width: 600px; margin: 0 auto;">
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

            <!--조회-->
            <div style="margin: 0 auto; ">
                <table style="width: 200px;">
                    <tr v-for="(item, i) in locations">
                        <td>{{ item.address2 }}</td>
                        <td @click.prevent="deleteLocation(item.num)"><i class="material-icons" style="font-size: 20px">clear</i>
                        </td>
                    </tr>
                </table>
            </div>

        </div>
    </v-container>
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