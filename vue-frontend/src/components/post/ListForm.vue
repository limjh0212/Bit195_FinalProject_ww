<template>
    <v-simple-table style="width: 70%">
        <template v-slot:default>
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
                    <td><a :href="`/post/freeBoard/${item.num}`">{{ item.title }}</a></td>
                    <td>{{ item.writer }}</td>
                    <td v-if="$moment().format('YYYY-MM-DD') === $moment(item.regdate).format('YYYY-MM-DD')">
                        {{ $moment(item.regdate).format('HH:mm:ss') }}
                    </td>
                    <td v-else>{{ $moment(item.regdate).format('YYYY-MM-DD') }}</td>
                </tr>
            </tbody>
        </template>
    </v-simple-table>
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
            const {data} = await freeboardList(1);
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