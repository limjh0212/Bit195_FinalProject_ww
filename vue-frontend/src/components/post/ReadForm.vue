<template>
    <div>
        <h1 v-html="title"></h1>
        <h3 v-html="content"></h3>

    </div>
</template>

<script>
import {freeBoardView} from "@/api/post";

export default {
    data() {
        return {
            title  : '',
            content: ``,

        }
    },
    methods: {
        async createContent() {
            const postNum = this.$route.params.postnum;
            const {data} = await freeBoardView(postNum, this.$store.state.id);
            console.log(data.post);
            this.content = data.post.content;
            this.title = data.post.title;
        }
    },
    created() {
        this.createContent()
    },

};
</script>
