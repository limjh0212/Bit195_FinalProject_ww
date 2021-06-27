<template>
    <div>
        <h15>자유게시판</h15>
        <v-container class="content-view">
            <div>
                <v-card
                    elevation="2"
                    outlined
                    style="width: 800px">
                    <v-simple-table dense >
                        <template v-slot:default>
                            <thead>
                                <td style="text-align: center; width: 130px">제목</td>
                                <td style="text-align: center; width: 30px">작성자</td>
                                <td style="text-align: center; width: 30px">작성일</td>
                                <td style="text-align: center; width: 30px">조회수</td>
                            </thead>
                            <tbody>
                                <tr v-for="(item,i) in freeBoard" v-if="i < 5">
                                    <td><a :href="`/post/freeBoard/${item.num}`">{{ item.title }}</a></td>
                                    <td style="text-align: center">{{ item.writer }}</td>
                                    <td style="text-align: center;"
                                        v-if="$moment().format('YYYY-MM-DD') === $moment(item.regdate).format('YYYY-MM-DD')">
                                        {{ $moment(item.regdate).format('HH:mm:ss') }}
                                    </td>
                                    <td style="text-align: center;" v-else>{{
                                            $moment(item.regdate).format('YYYY-MM-DD')
                                        }}
                                    </td>
                                    <td style="text-align: center">{{ item.readcount }}</td>
                                </tr>
                            </tbody>
                        </template>
                    </v-simple-table>
                </v-card>
            </div>
        </v-container>
        <br>
        <h15>Q&A</h15>
        <v-container class="content-view">
            <div>
                <v-card
                    elevation="2"
                    outlined
                    style="width: 800px">
                    <v-simple-table dense>
                        <template v-slot:default>
                            <thead>
                                <td style="text-align: center; width: 130px">제목</td>
                                <td style="text-align: center; width: 30px">작성자</td>
                                <td style="text-align: center; width: 30px">작성일</td>
                                <td style="text-align: center; width: 30px">조회수</td>
                            </thead>
                            <tbody>
                                <tr v-for="(item,i) in qna" v-if="i < 5">
                                    <td><a :href="`/post/qna/${item.num}`">{{ item.title }}</a></td>
                                    <td style="text-align: center">{{ item.writer }}</td>
                                    <td style="text-align: center;"
                                        v-if="$moment().format('YYYY-MM-DD') === $moment(item.regdate).format('YYYY-MM-DD')">
                                        {{ $moment(item.regdate).format('HH:mm:ss') }}
                                    </td>
                                    <td style="text-align: center;" v-else>{{
                                            $moment(item.regdate).format('YYYY-MM-DD')
                                        }}
                                    </td>
                                    <td style="text-align: center">{{ item.readcount }}</td>
                                </tr>
                            </tbody>
                        </template>
                    </v-simple-table>
                </v-card>
            </div>
        </v-container>
    </div>
</template>

<script>
import {boardUserList, freeboardList, qnaAdminList} from "@/api/post";

export default {
    data() {
        return {
            freeBoard: {},
            qna      : {}
        }
    },
    methods: {
        async fetchFreeBoard(num) {
            const {data} = await freeboardList(num);
            this.freeBoard = data.posts;
        },
        async fetchQna(num) {
            if (this.isAdmin) {
                const {data} = await qnaAdminList(num);
                this.qna = data;
            } else {
                const {data} = await boardUserList(this.$store.state.id, num, 'qna');
                console.log(data);
                console.log(this.$store.state.id, num);
                this.qna = data.posts;
            }
        },
    },
    created() {
        this.fetchFreeBoard(1);
        this.fetchQna(1);
    }
}

</script>

<style scoped>

</style>