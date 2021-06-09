<template>
    <div>
        <div style="height: 50px">
        </div>
        <form>
            <el-tiptap :showMenubar="false" :readonly="true" :charCounterCount="false" :extensions="extensions"
                       v-model="title" :width="700"/>
            <el-tiptap :showMenubar="false" :readonly="true" :charCounterCount="false" :extensions="extensions"
                       :content="content"
                       v-model="content" :width="700"
                       height="100%" placeholder="Write something ..."/>
            <button type="submit">목록</button>
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
import {freeBoardView, savePost} from "@/api/post";

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
        title     : '',
    }),
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
