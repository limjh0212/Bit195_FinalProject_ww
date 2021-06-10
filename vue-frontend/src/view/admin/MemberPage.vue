<template>
    <div>
        <p>현재 회원 : {{ cntWthdrFalse }}</p>
        <p>탈퇴 회원 : {{ cntWthdrTrue }}</p>
        <p>전체 회원 : {{ cntMembers }}</p>
    </div>
</template>

<script>
import {fetchMember} from "@/api/admin";

export default {
    data() {
        return {
            cntWthdrFalse: '',
            cntWthdrTrue : '',
            cntMembers   : '',
        }
    },
    methods: {
        async fetchData() {
            const {data} = await fetchMember();
            this.cntWthdrFalse = data.cntWthdrFalse;
            this.cntWthdrTrue = data.cntWthdrTrue;
            this.cntMembers = data.cntMembers;
        },
    },
    created() {
        this.fetchData()
    }

}
</script>

<style scoped>

</style>