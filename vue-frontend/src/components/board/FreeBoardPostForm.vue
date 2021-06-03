<template>
    <div>
        <h5>글 작성</h5>
        <form @submit.prevent="submitForm">
            <div>
                <label for="title">Title:</label>
                <input id="title" v-model="title" type="text"/>
            </div>
            <div>
                <label for="content">Content:</label>
                <input id="content" v-model="content" type="text"/>
            </div>
            <button type="submit">저장</button>
        </form>
    </div>
</template>

<script>
import {createPost} from "@/api";

export default {
    data() {
        return {
            title  : '',
            content: '',
            writer : 'ab'
        }
    },
    methods: {
        async submitForm() {
            const response = await createPost({
                title  : this.title,
                content: this.content,
                writer: this.writer
            });
            await this.$router.push('/board');
        }
    }
}
</script>

<style scoped>

</style>