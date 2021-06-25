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

        <!--댓글 입력창-->
        <div>
            <textarea v-model="writeCmnt" type="textarea"/>
            <button @click="saveCmnt">저장</button>
        </div>

        <!--수정 Btn-->
        <div v-if="isEdit===false">
            <button v-if="post.uid===checkUid" @click="doEdit">수정</button>
            |
            <button v-if="post.uid===checkUid" @click="doDelete(post.num)">삭제</button>
        </div>


        <!--저장/취소 Btn-->
        <div v-if="isEdit===true">
            <button v-if="post.uid===checkUid" @click="update">저장</button>
            |
            <button v-if="post.uid===checkUid" @click="goBack">취소</button>
        </div>

        <!--좋아요-->
        <div>
            <button v-if="data.existLike===true" @click="likePost">좋아요🐸</button>
            <button v-else @click="likePost">좋아요</button>
        </div>

        <!--댓글-->
        <div>
            <!--댓글-->
            <div v-for="(item, i) in cmnt">{{ item.content }}
                <span @click="pushCmnt2(i)">대댓작성</span> |
                <span @click="editCmntdo(i)">수정</span> |
                <span @click="deleteCmnt1(i)">삭제</span> |

                <!--댓글 수정-->
                <div v-if="showCmnt1(i)">
                    <textarea v-model="editCmnt" placeholder="수정 내용을 입력하세요." type="textarea"/>
                    <button @click="editCmnt1">저장</button>
                </div>

                <!--대댓글 작성-->
                <div v-if="showCmnt2(i)">
                    <div>
                        <textarea v-model="writeCmnt2" placeholder="대댓글 내용을 입력하세요." type="textarea"/>
                        <button @click="saveCmnt2">저장</button>
                    </div>
                </div>
                <!--대댓글-->
                <ul>
                    <li v-for="(item, i) in cmnt2[i]">{{ item.content }}</li>
                </ul>
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
import {deletePost, freeBoardView, likePost, updatePost} from "@/api/post";
import {api_deleteCmnt1, api_deleteCmnt1exist, api_editCmnt1, api_saveCmnt1, api_saveCmnt2} from "@/api/cmnt";


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
            //게시글
            title  : '',
            content: ``,

            //댓글, 좋아요
            data : {},
            post : {},
            cmnt : [],
            cmnt2: [],

            //수정 여부 확인
            isEdit: false,

            //댓글, 대댓글 작성
            cmnt1Array: [],
            cmnt2Array: [],
            writeCmnt : '',
            writeCmnt2: '',
            editCmnt  : '',
            cmntnum   : '',
        }
    },
    methods : {

        //댓글 삭제
        async deleteCmnt1(i) {
            this.cmntnum = this.cmnt[i].num;
            if (this.cmnt2[i].length === 0) {
                await api_deleteCmnt1(this.cmntnum);
                await this.createContent();
            } else {
                const Data = {
                    num     : this.cmntnum,
                    boardnum: this.post.boardnum,
                    postnum : this.$route.params.postnum,
                }
                api_deleteCmnt1exist(this.cmntnum, Data)
                await this.createContent();
            }
        },

        //댓글 수정 실행
        async editCmnt1() {
            const Data = {
                boardnum: this.post.boardnum,
                content : this.editCmnt,
                num     : this.cmntnum,
                postnum : this.$route.params.postnum,
                writer  : this.$store.state.id
            }
            await api_editCmnt1(this.cmntnum, Data)
            await this.createContent()
        },

        //댓글 수정 버튼 작동 (Text area 보여주기)
        editCmntdo(i) {
            if (this.cmnt1Array.includes(i)) {
                this.cmnt1Array.pop(i);
            } else {
                this.cmnt2Array = [];
                this.cmnt1Array = [];
                this.cmnt1Array.push(i);
                this.cmntnum = this.cmnt[i].num;
            }
        },
        //댓글 수정 Textarea 표시 조건문
        showCmnt1(i) {
            return this.cmnt1Array.includes(i);
        },

        //대댓글 저장
        async saveCmnt2(i) {
            const Data = {
                boardnum: this.post.boardnum,
                content : this.writeCmnt2,
                postnum : this.$route.params.postnum,
                writer  : this.$store.state.id
            }
            await api_saveCmnt2(this.cmntnum, Data)
            this.writeCmnt2 = '';
            this.cmnt2Array.pop(i);
            await this.createContent();
        },

        //대댓글 작성폼 선택
        pushCmnt2(i) {
            if (this.cmnt2Array.includes(i)) {
                this.cmnt2Array.pop(i);
            } else {
                this.cmnt1Array = [];
                this.cmnt2Array = [];
                this.cmnt2Array.push(i);
                this.cmntnum = this.cmnt[i].num;
            }
        },

        //대댓글 작성폼 보여주기
        showCmnt2(i) {
            return this.cmnt2Array.includes(i);
        },

        //수정모드
        doEdit() {
            this.isEdit = true;
        },

        //뒤로가기
        goBack() {
            this.$router.go();
        },

        //게시글 수정
        async update() {
            const postData = {
                boardname: this.$route.params.boardname,
                content  : this.content,
                title    : this.title,
                uid      : this.$store.state.id,
                writer   : this.$store.state.nickname
            }
            const {data} = await updatePost(this.post.num, postData);
            this.isEdit = false;
        },

        //게시글 삭제
        async doDelete(num) {
            await deletePost(num);
            await this.$router.push(`/list/${this.$route.params.boardname}`)
        },

        //좋아요 적용, 해제
        async likePost() {
            const Data = {
                boardname: this.$route.params.boardname,
                postnum  : this.post.postnum,
                uid      : this.$store.state.id
            }
            await likePost(this.post.num, Data)
            await this.createContent()
        },

        // 댓글 저장
        async saveCmnt() {
            const Data = {
                boardnum: this.post.boardnum,
                content : this.writeCmnt,
                postnum : this.post.num,
                writer  : this.$store.state.id
            }
            await api_saveCmnt1(Data);
            this.writeCmnt = '';
            await this.createContent()
        },

        //FETCH_DATA
        async createContent() {
            const postNum = this.$route.params.postnum;
            const {data} = await freeBoardView(postNum, this.$store.state.id);
            this.data = data;
            this.post = data.post;
            this.cmnt = data.cmntList;
            this.cmnt2 = data.cmnt2List;
            this.title = data.post.title;
            this.content = this.post.content;
        },
    },
    computed: {
        checkUid() {
            return this.$store.state.id;
        }
    },
    created() {
        this.createContent()
    }
}
;
</script>
