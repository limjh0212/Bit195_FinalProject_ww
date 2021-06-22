<template>
    <div>
        <!--읽기 Form-->
        <form v-if="isEdit===false">
            <h1>{{ this.title }}</h1>
            <el-tiptap :extensions="extensions" class="editor__content" :readonly="true" :charCounterCount="false"
                       v-model="content" :content="content" :showMenubar="false" :spellcheck="false" :tooltip="false"
                       :width="700" height="100%" placeholder="Write something ..."/>
        </form>

        <!--수정 Form-->
        <form v-else>
            <input v-model="title" type="text"></input>
            <el-tiptap :extensions="extensions" class="editor__content" :spellcheck="false" :content="content"
                       v-model="content" :width="700"
                       height="100%" placeholder="Write something ..."/>
        </form>

        <!--수정 Btn-->
        <div v-if="isEdit===false">
            <button v-if="post.uid===checkUid" @click="doEdit">수정</button>
            <button v-if="post.uid===checkUid" @click="doDelete(post.num)">삭제</button>
        </div>


        <!--저장/취소 Btn-->
        <div v-if="isEdit===true">
            <button v-if="post.uid===checkUid" @click="update">저장</button>
            <button v-if="post.uid===checkUid" @click="goBack">취소</button>
        </div>

        <!--댓글-->
        <div>
            <!--댓글-->
            <div v-for="(item, idx) in cmnt">{{ item.content }}
                <!--대댓글-->
                <div v-for="(item) in cmnt2[idx]">{{ item.content }}</div>
            </div>
        </div>
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
import {deletePost, freeBoardView, updatePost} from "@/api/post";


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
            title     : '',
            content   : ``,
            post      : {},
            cmnt      : [],
            cmnt2     : [],
            isEdit    : false,
        }
    },
    methods : {
        async createContent() {
            const postNum = this.$route.params.postnum;
            const {data} = await freeBoardView(postNum, this.$store.state.id);
            console.log(data);
            this.post = data.post;
            this.cmnt = data.cmntList;
            this.cmnt2 = data.cmnt2List;
            this.title = data.post.title;
            this.content = this.post.content;
        }
        ,
        doEdit() {
            this.isEdit = true;
        },
        goBack() {
            this.$router.go();
        },
        async update() {
            const postData = {
                boardname: this.$route.params.boardname,
                content  : this.content,
                title    : this.title,
                uid      : this.$store.state.id,
                writer   : this.$store.state.nickname
            }
            const {data} = await updatePost(this.post.num, postData);
            console.log(data);
            this.isEdit = false;
        },
        async doDelete(num) {
            await deletePost(num);
            await this.$router.push(`/list/${this.$route.params.boardname}`)
        }
    },
    computed: {
        checkUid() {
            return this.$store.state.id;
        }
    }
    ,
    created() {
        this.createContent()
    }
};
</script>
