<template>
    <v-simple-table>
        <template v-slot:default>
            <thead>
                <tr>
                    <th class="text-left">No.</th>
                    <th class="text-left">제목</th>
                    <th class="text-left">작성자</th>
                    <th class="text-left">조회수</th>
                    <th class="text-left">작성일</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in items" :key="item">
                    <td>{{ item.postnum }}</td>
                    <td>{{ item.title }}</td>
                    <!--                    <td>{{ item.calories }}</td>-->
                </tr>
            </tbody>
        </template>
    </v-simple-table>
</template>

<script>
import LoadingSpinner from "@/components/common/LoadingSpinner";
import {freeboardList} from "@/api/post";


export default { components: {LoadingSpinner},
    data() {
        return {
            items  : [],
            isLoading: false,
        }
    },
    methods: {
        async fetchData() {
            this.isLoading = true;
            const {data} = await freeboardList();
            this.isLoading = false;
            this.items = data;
        },
    },
    created() {
        this.fetchData();
    }}
</script>

<style scoped>

</style>