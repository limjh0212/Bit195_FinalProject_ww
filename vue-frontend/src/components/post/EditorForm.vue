<template>
    <div>
        <form @submit.prevent="c">
    <el-tiptap :extensions="extensions" :content="content" v-model="content" :width="700"
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
import "codemirror/addon/edit/closetag.js"; // autoCloseTags

export default {
    name: "Editor",

    data: () => ({
        extensions: [
            new Doc(),
            new Text(),
            new Paragraph(),
            new Heading({level: 5}),
            new Bold({bubble: true}),
            new Underline({bubble: true}),
            new Italic({bubble: true}),
            new Strike({bubble: true}),
            new Link({ bubble: true }),
            new Image(),
            new Blockquote(),
            new HorizontalRule({bubble: true}),
            new History()
        ],

        content: ``
    }),
    methods:{
        c(){
            console.log(this.content);
        }
    }
};
</script>
