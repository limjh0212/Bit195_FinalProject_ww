<template>
    <v-container>
        <div class="board-router-view">
            <div v-if="this.$route.params.boardname === 'freeBoard'">
                <div>
                    <form @submit.prevent="save">
                        <input v-model="title" placeholder='제목' type="text"></input>
                        <el-tiptap v-model="content" :content="content" :extensions="extensions" :width="700"
                                   class="editor__content"
                                   height="400px" placeholder="내용을 입력하세요."/>
                        <br>
                        <div style="width:700px; display: flex; justify-content: space-between">
                            <div><v-btn elevation="1" rounded small @click="goback">뒤로
                            </v-btn></div>
                            <div>
                                <v-btn elevation="1" rounded small type="submit">저장<i class="material-icons"
                                                                                      style="font-size: 20px">save</i>
                                </v-btn>

                            </div>
                        </div>

                    </form>
                </div>
            </div>
            <div v-if="this.$route.params.boardname === 'OOTD'">
                <div>
                    <div>
                        <form enctype="multipart/form-data" method="post" @submit.prevent="saveOotd">
                            <input v-model="title" placeholder='제목' type="text"></input>
                            <el-tiptap v-model="content" :content="content" :extensions="extensions" :width="700"
                                       class="editor__content"
                                       height="100%" placeholder="내용을 입력하세요."/>
                            <p>대표 이미지</p>
                            <input id="File" multiple="multiple" name="files" type="file"
                                   @change="onFileSelected"></input>
                            <br>
                            <div style="width:700px; display: flex; justify-content: space-between">
                                <div><v-btn elevation="1" rounded small @click="goback">뒤로
                                </v-btn></div>
                                <div>
                                    <v-btn elevation="1" rounded small type="submit">저장<i class="material-icons"
                                                                                          style="font-size: 20px">save</i>
                                    </v-btn>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div>
                        <img ref="uploadImg" :src="this.imgsrc">
                    </div>
                </div>
            </div>
            <div v-if="this.$route.params.boardname === 'qna'">
                <div>
                    <form @submit.prevent="save">
                        <input v-model="title" placeholder='제목' type="text"></input>
                        <el-tiptap v-model="content" :content="content" :extensions="extensions" :width="700"
                                   class="editor__content"
                                   height="100%" placeholder="내용을 입력하세요."/>
                        <br>
                        <div style="width:700px; display: flex; justify-content: space-between">
                            <div></div>
                            <div>
                                <v-btn elevation="1" rounded small type="submit">저장<i class="material-icons"
                                                                                      style="font-size: 20px">save</i>
                                </v-btn>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </v-container>
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
import {saveOotdPost} from "@/api/img";

let FileElement = document.querySelector('#File');
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
            title     : '',
            imgsrc    : "",
        };
    },
    methods: {
        goback() {
            this.$router.go(-1);
        },
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
        },
        async saveOotd() {
            if (this.title === '') {
                this.title = '제목 없음';
            }
            const ootdData = new FormData();
            ootdData.append("boardname", this.$route.params.boardname);
            ootdData.append("title", this.title);
            ootdData.append("content", this.content);
            ootdData.append("uid", this.$store.state.id);
            for (let i = 0; i < FileElement.files.length; i++) {
                ootdData.append("images", FileElement.files[i]);
            }
            const {data} = await saveOotdPost(ootdData);
            await this.$router.push('/list/OOTD');
        },
        onFileSelected: function (event) {
            FileElement = event.target;
            if (FileElement.files[0]) {
                let img = this.$refs.uploadImg;
                img.src = URL.createObjectURL(FileElement.files[0]);
                this.imgsrc = img.src;
                img.width = 200;
                img.onload = () => {
                    URL.revokeObjectURL(this.src)
                }
            }
        },
    }
};
</script>
<style></style>