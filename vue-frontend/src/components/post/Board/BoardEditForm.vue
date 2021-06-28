<template>
    <div align="center"
         justify="center">
        <!--읽기 Form-->
        <form v-if="isEdit===false">
            <h1>{{ this.title }}</h1>
            <el-tiptap :extensions="extensions" class="editor__content" :readonly="true" :charCounterCount="false"
                       v-model="content" :content="content" :showMenubar="false" :spellcheck="false" :tooltip="false"
                       :width="700" height="100%" style="border: 1px solid lightgray"
                       placeholder="Write something ..."/>
        </form>

        <!--수정 Form-->
        <form v-else>
            <input v-model="title" style="width: 600px" type="text"></input>

            <el-tiptap :extensions="extensions" class="editor__content" :spellcheck="false" :content="content"
                       v-model="content" :width="700" style="border: 1px solid lightgray"
                       height="100%" placeholder="Write something ..."/>
        </form>
        <div style="width: 55%; display: flex; justify-content: space-between">
            <!--좋아요-->
            <div>
                <button v-if="data.existLike===true" @click="likePost">좋아요<i class="material-icons"
                                                                             style="font-size: 18px; color:crimson">favorite</i>
                </button>
                <button v-else @click="likePost">좋아요<i class="material-icons" style="font-size: 18px ; color: crimson">favorite_border</i>
                </button>
            </div>

            <!--저장/취소 Btn-->
            <div v-if="isEdit===true">
                <button v-if="post.uid===checkUid" @click="update">저장<i class="material-icons"
                                                                        style="font-size: 18px;">save</i></button>
                |
                <button v-if="post.uid===checkUid" @click="goBack">취소<i class="material-icons"
                                                                        style="font-size: 18px;">clear</i></button>
            </div>

            <!--수정 Btn-->
            <div v-if="isEdit===false">
                <button v-if="post.uid===checkUid" @click="doEdit">수정<i class="material-icons"
                                                                        style="font-size: 18px;">edit</i></button>&nbsp;&nbsp;&nbsp;
                <button v-if="post.uid===checkUid" @click="doDelete(post.num)">삭제<i class="material-icons"
                                                                                    style="font-size: 18px;">clear</i>
                </button>
            </div>
        </div>

        <!--댓글 입력창-->
        <br>
        <div v-if="showCmnt===true">
            <div>
                <v-textarea v-model="writeCmnt" name="input-7-4" placeholder="댓글을 입력하세요." solo
                            style="width: 700px;"></v-textarea>
                <div style="display: flex; justify-content: space-between">
                    <div>
                    </div>
                    <div style="width: 50%;">
                        <button @click="saveCmnt">저장<i class="material-icons"
                                                       style="font-size: 18px;">save</i></button>
                    </div>
                </div>
            </div>
        </div>

        <!--댓글 수정-->
        <div v-if="showCmnt===false">
            <v-textarea v-model="editCmnt" name="input-7-4" solo style="width: 700px;"></v-textarea>
            <div style="display: flex; justify-content: space-between">
                <div>
                </div>
                <div style="width: 50%;">
                    <button @click="editCmnt1">저장<i class="material-icons"
                                                    style="font-size: 18px;">save</i></button>
                </div>
            </div>
        </div>
        <br><br>
        <!--댓글-->
        <div style="width: 80%;">
            <table v-for="(item, idx) in cmnt" style="width: 750px">
                <tr>
                    <td style="width: 80px; text-align: left">{{ item.writer }}</td>
                    <td style="width: 300px; ">{{ item.content }}</td>
                    <td v-if="$moment().format('YYYY-MM-DD') === $moment(item.regdate).format('YYYY-MM-DD')"
                        style="width: 150px; text-align: center">
                        {{ $moment(item.regdate).format('HH:mm:ss') }}
                    </td>
                    <td v-else style="width: 150px; text-align: center">{{
                            $moment(item.regdate).format('YYYY-MM-DD')
                        }}
                    </td>
                    <td style="width: 100px; text-align: center">
                        <span @click="pushCmnt2(idx)"><i class="material-icons"
                                                         style="font-size: 18px;">reply</i></span>&nbsp;&nbsp;&nbsp;
                        <span v-if="isWriter(item.writer)" @click="editCmntdo(idx)"><i class="material-icons"
                                                                                       style="font-size: 18px;">edit</i></span>&nbsp;&nbsp;&nbsp;
                        <span v-if="isWriter(item.writer)" @click="deleteCmnt1(idx)"><i class="material-icons"
                                                                                        style="font-size: 18px;">clear</i></span>&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
                <tr v-for="(item, i) in cmnt2[idx]">
                    <td style="width: 80px; text-align: center"><i class="material-icons"
                                                                   style="font-size: 18px;">subdirectory_arrow_right</i>&nbsp;&nbsp;{{
                            item.writer
                        }}
                    </td>
                    <td style="width: 300px; ">{{ item.content }}</td>
                    <td v-if="$moment().format('YYYY-MM-DD') === $moment(item.regdate).format('YYYY-MM-DD')"
                        style="width: 150px; text-align: center">
                        {{ $moment(item.regdate).format('HH:mm:ss') }}
                    </td>
                    <td v-else style="width: 150px; text-align: center">{{ $moment(item.regdate).format('YYYY-MM-DD') }}
                    </td>
                    <td style="width: 100px; text-align: center">
                        <span v-if="isWriter(item.writer)" @click=""><i class="material-icons"
                                                                                     style="font-size: 18px;">edit</i></span>&nbsp;&nbsp;&nbsp;
                        <span v-if="isWriter(item.writer)" @click=""><i class="material-icons"
                                                                                      style="font-size: 18px;">clear</i></span>&nbsp;&nbsp;&nbsp;
                    </td>
                    <!--댓글 수정-->
                </tr>

            </table>


            <!--대댓글 작성-->
            <div>
                <div>
                    <textarea v-model="writeCmnt2" placeholder="대댓글 내용을 입력하세요." type="textarea"/>
                    <button @click="saveCmnt2">저장</button>
                </div>
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
            showCmnt  : false,
        }
    },
    methods: {
        isWriter(id) {
            return this.$store.state.id === id;
        },

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
            this.showCmnt = true;
        },

        //댓글 수정 버튼 작동 (Text area 보여주기)
        editCmntdo(idx) {
            this.showCmnt = false;
            this.cmntnum = this.cmnt[idx].num;
            this.editCmnt = this.cmnt[idx].content
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
        pushCmnt2(idx) {
            if (this.cmnt2Array.includes(idx)) {
                console.log(idx)
                this.cmnt2Array.pop(idx);
            } else {
                this.cmnt1Array = [];
                this.cmnt2Array = [];
                this.cmnt2Array.push(idx);
                this.cmntnum = this.cmnt[idx].num;
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
    }
    ,
    computed: {
        checkUid() {
            return this.$store.state.id;
        }
    }
    ,
    created() {
        this.createContent()
    }
}
;
</script>
