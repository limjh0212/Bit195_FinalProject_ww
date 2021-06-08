<template>

    <table>
        <thead>
            <tr>
                <th class="text-left">
                    Name
                </th>

            </tr>
        </thead>
        <tbody>
            <tr v-for="item in members" :key="item.id">
                <td>{{ item.id }}</td>
                <!--                    <td>{{ item.calories }}</td>-->
            </tr>
        </tbody>
    </table>
</template>
<script>
import {fetchMemberList} from "@/api/member";
import LoadingSpinner from "@/components/common/LoadingSpinner";

export default {
    components: {LoadingSpinner},
    data() {
        return {
            members  : [],
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
    },
    created() {
        this.fetchData();
    }
}
</script>

<style scoped>
.memberList {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;

}

.post {
    display: flex;
    align-items: center;
    list-style: none;
    border-bottom: 1px solid #eee;
}

</style>