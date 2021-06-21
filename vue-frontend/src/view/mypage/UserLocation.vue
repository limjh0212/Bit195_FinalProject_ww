<template>
    <div>
        <!--검색 창-->
        <div>
            <SearchLocation></SearchLocation>
        </div>

        <!--목록-->
        <div>
            <table>
                <tr v-for="(item, i) in locations">
                    <td>{{ item.address2 }}</td>
                    <td @click.prevent="deleteLocation(item.num)">🐸</td>

                </tr>
            </table>
        </div>
    </div>

</template>

<script>
import SearchLocation from "@/components/weather/SearchLocation";
import {userLo} from "@/api/weather";

export default {
    name      : "UserLocation",
    components: {SearchLocation},
    data() {
        return {
            locations: [],
        }

    },
    methods: {
        async fetchData() {
            const {data} = await userLo(this.$store.state.id)
            this.locations = data;
            console.log(data)
        },

    },
//    created() {
//        this.fetchData();
//    }
}


</script>

<style scoped>

</style>