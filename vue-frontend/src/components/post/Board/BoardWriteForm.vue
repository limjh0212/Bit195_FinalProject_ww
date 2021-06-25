<template>
    <div>
        <form @submit.prevent="save">
            <input v-model="title" placeholder='제목을 적어주세요' type="text"></input>
            <el-tiptap :extensions="extensions" class="editor__content" :content="content" v-model="content"
                       :width="700"
                       height="100%" placeholder="Write something ..."/>
            <button type="submit">저장</button>
        </form>
    </div>
</template>

<script>
import {
    Blockquote,
    Bold,
    Doc,
    Heading,
    History,
    HorizontalRule,
    Image,
    Italic,
    Link,
    Paragraph,
    Strike,
    Text,
    Underline
} from "element-tiptap";
import "codemirror/lib/codemirror.css"; // import base style
import "codemirror/mode/xml/xml.js"; // language
import "codemirror/addon/selection/active-line.js"; // require active-line.js
import "codemirror/addon/edit/closetag.js";
import {savePost} from "@/api/post";

export default {
    name: "Editor",

    data() {
        return {
            extensions: [
                new Doc(),
                new Text(),
                new Paragraph(),
                new Heading({level: 5}),
                new Bold({bubble: true}),
                new Underline({bubble: true}),
                new Italic({bubble: true}),
                new Strike({bubble: true}),
                new Link({bubble: true}),
                new Image(),
                new Blockquote(),
                new HorizontalRule({bubble: true}),
                new History()
            ],
            content   : ``,
            title     : ''
        };
    },
    methods: {
        async save() {
            if (this.title === '') {
                this.title = '제목 없음';
            }
            const postData = {
                boardname: this.$route.params.boardname,
                content  : this.content,
                title    : this.title,
                uid      : this.$store.state.id,
                writer   : this.$store.state.nickname
            }
            await savePost(postData);
            await this.$router.push(`/list/${postData.boardname}`);
        }
    }
};
</script>
<style></style>