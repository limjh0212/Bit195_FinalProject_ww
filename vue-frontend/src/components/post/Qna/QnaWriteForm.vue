<template>
    <div>
        <form @submit.prevent="save">
            <input v-model="title" placeholder='제목을 적어주세요' type="text"></input>
            <el-tiptap v-model="content" :content="content" :extensions="extensions" :width="700"
                       class="editor__content"
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

    data   : () => ({
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
    }),
    methods: {
        async save() {
            const postData = {
                boardname: this.$route.params.boardname,
                content  : this.content,
                title    : this.title,
                uid      : this.$store.state.id,
                writer   : this.$store.state.nickname
            }
            const {data} = await savePost(postData);
            console.log(data);
            await this.$router.push(`/list/freeBoard`);
        }
    }
};
</script>
<style></style>