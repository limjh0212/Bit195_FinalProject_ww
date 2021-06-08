<template>
    <div>
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
                    <td>{{ item.postnum }}</td>
                    <td><a :href="`/post/qna/${item.postnum}`">{{ item.title }}</a></td>
                    <td>{{ item.writer }}</td>
                    <td v-if="$moment(item.regdate).format('YYYY-MM-DD')===$moment().format('YYYY-MM-DD')">
                        {{ $moment(item.regdate).format('HH:mm:ss') }}
                    </td>
                    <td v-else>{{ $moment(item.regdate).format('YYYY-MM-DD') }}</td>
                </tr>
            </tbody>
        </table>
        <LoadingSpinner v-if="isLoading"></LoadingSpinner>
    </div>
</template>

<script>
import LoadingSpinner from "@/components/common/LoadingSpinner";
import {freeboardList, qnaList} from "@/api/post";


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
            const {data} = await qnaList();
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