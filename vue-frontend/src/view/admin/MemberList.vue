<template>
    <v-simple-table style="width: 50%; margin: auto">
        <template v-slot:default>
            <thead>
                <tr>
                    <th class="text-left">선택</th>
                    <th class="text-left">Id</th>
                    <th class="text-left">Nickname</th>
                    <th class="text-left">가입일</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, idx) in members" :key="idx">
                    <td><input type="checkbox" v-model="deleteSelect" v-bind:value="item.id"></td>
                    <td>{{ item.id }}</td>
                    <td>{{ item.nickname }}</td>
                    <td>{{ $moment(item.regdate).format('YYYY-MM-DD') }}</td>
                </tr>
            </tbody>
            <button @click="deleteId">삭제</button>
        </template>
    </v-simple-table>
</template>
<script>
import {deleteId, fetchMemberList} from "@/api/admin";
import LoadingSpinner from "@/components/common/LoadingSpinner";

export default {
    components: {LoadingSpinner},
    data() {
        return {
            members  : [],
            deleteSelect: [],
            isLoading: false,
        }
    },
    methods: {
        async fetchData() {
            this.isLoading = true;
            const {data} = await fetchMemberList();
            this.isLoading = false;
            this.members = data;
        },

        async deleteId() {
            for (let i = 0; i < this.deleteSelect.length; i++) {
                deleteId(this.deleteSelect[i]);
                await this.fetchData();
            }
        }
    },
    created() {
        this.fetchData();
    }
}
</script>

<style scoped>

</style>