<template>
    <v-container class="content-view">
        <div id="board-list-form">
            <v-simple-table>
                <template v-slot:default>
                    <thead>
                        <tr>
                            <th class="th" style="width: 50px;">No.</th>
                            <th class="th" style="width: 90px;">작성자</th>
                            <th class="th" style="width: 300px;">Title</th>
                            <th class="th" style="width: 120px;">작성일</th>
                            <th class="th" style="width: 50px;">좋아요</th>
                            <th class="th" style="width: 50px;">조회</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(item, idx) in items.posts" :key="idx">
                            <td class="text_center">{{ item.postnum }}</td>
                            <td class="text_center">{{ item.writer }}</td>
                            <td><a :href="`/post/freeBoard/${item.num}`">{{ item.title }}</a></td>
                            <td v-if="$moment().format('YYYY-MM-DD') === $moment(item.regdate).format('YYYY-MM-DD')"
                                class="text_center">
                                {{ $moment(item.regdate).format('HH:mm:ss') }}
                            </td>
                            <td v-else class="text_center">{{ $moment(item.regdate).format('YYYY-MM-DD') }}</td>
                            <td class="text_center">{{ item.likecount }}</td>
                            <td class="text_center">{{ item.readcount }}</td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <th colspan="6">
                            <div style="justify-content: space-between; display: flex">
                                <div>
                                    <v-btn elevation="1" rounded small>
                                        <router-link to="/post/freeBoard">글 작성<i class="material-icons"
                                                                                 style="font-size: 15px">create</i>
                                        </router-link>
                                    </v-btn>&nbsp;&nbsp;&nbsp;
                                </div>
                                <div>
                                    <div class="page-list" style="display: flex">
                                        <div v-for="page in items.pageList" style="display: flex">
                                            <div class="page-box-default" @click="fetchMyList(page)">{{ page }}</div>&nbsp;&nbsp;&nbsp;
                                        </div>
                                    </div>
                                </div>
                                <div>
                                </div>
                            </div>
                        </th>
                    </tfoot>
                </template>
            </v-simple-table>
        </div>
    </v-container>
</template>

<script>
import {boardUserList} from "@/api/post";

export default {
    data() {
        return {
            items   : [],
            pageList: [],
        }
    },
    methods: {
        //내글 목록
        async fetchMyList(num) {
            const {data} = await boardUserList(this.$store.state.id, num, 'freeBoard');
            this.items = data;
            this.pageList = data.pageList;
            this.myList = true;
        },
    },
    created() {
        this.fetchMyList(1);
    }
}
</script>

<style scoped>
/*table 상단*/
.th {
    width: 10rem;
    text-align: center;
}

/*table 하단*/
.page-list {
    width: 50%;
    text-align: center;
    position: relative;
    height: auto;
    font-size: 15px;
    margin: auto;
}

.page-box-default {
    margin: 3px;
}

.text_center {
    text-align: center;
}

</style>