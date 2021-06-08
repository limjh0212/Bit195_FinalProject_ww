<template>
    <table>
        <thead>
            <tr>
                <th class="text-left">No.</th>
                <th class="text-left">Title</th>
                <th class="text-left">작성자</th>
                <th class="text-left">작성일</th>

            </tr>
        </thead>
        <tbody>
            <tr v-for="(item, idx) in items" :key="idx">
                <td>{{ item.num }}</td>
                <td>{{ item.title }}</td>
                <td>{{ item.writer }}</td>
                <td v-if="$moment(item.regdate).format('YYYY-MM-DD')===$moment().format('YYYY-MM-DD')">
                    {{ $moment(item.regdate).format('hh:mm:ss') }}
                </td>
                <td v-else>{{ $moment(item.regdate).format('YYYY-MM-DD') }}</td>
                <!--                    <td>{{ item.calories }}</td>-->
            </tr>
        </tbody>
    </table>
</template>

<script>
import LoadingSpinner from "@/components/common/LoadingSpinner";
import {freeboardList} from "@/api/post";


export default {
    components: {LoadingSpinner},
    data() {
        return {
            items    : [],
            isLoading: false,
        }
    },
    methods: {
        async fetchData() {
            this.isLoading = true;
            const {data} = await freeboardList();
            this.isLoading = false;
            this.items = data.posts;
        },
    },
    created() {
        this.fetchData();
    }
}
</script>

<style scoped>
.text-left {
    width: 10rem;

}

</style>