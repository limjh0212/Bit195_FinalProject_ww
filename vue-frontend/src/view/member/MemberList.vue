<template>
    <div class="memberList">
        <h1>Member List</h1>

        <LoadingSpinner v-if="isLoading"></LoadingSpinner>


        <p class="post" v-for="(item, idx) in members" v-bind:key="idx">
            <a v-bind:href="`member/${item.id}`">
                {{ item.id }}</a></p>
    </div>
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