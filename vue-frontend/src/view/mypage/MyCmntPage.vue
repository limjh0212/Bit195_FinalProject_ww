<template>
    <v-container class="content-view">
        <div id="board-list-form">
            <v-simple-table>
                <template v-slot:default>
                    <thead>
                        <tr>
                            <th class="th" style="width: 50px;">No.</th>
                            <th class="th" style="width: 90px;">작성자</th>
                            <th class="th" style="width: 200px;">내용</th>
                            <th class="th" style="width: 120px;">작성일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(item, i) in items" :key="i">
                            <td class="text_center">{{ item.num }}</td>
                            <td class="text_center">{{ item.writer }}</td>
                            <td>{{ item.content }}</td>
                            <td v-if="$moment().format('YYYY-MM-DD') === $moment(item.regdate).format('YYYY-MM-DD')"
                                class="text_center">
                                {{ $moment(item.regdate).format('HH:mm:ss') }}
                            </td>
                            <td v-else class="text_center">{{ $moment(item.regdate).format('YYYY-MM-DD') }}</td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <th colspan="6">
                            <div style="justify-content: space-between; display: flex">
                                <div>
                                </div>
                                <div>
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
import LoadingSpinner from "@/components/common/LoadingSpinner";
import {myCmnt} from "@/api/post";

export default {
    components: {LoadingSpinner},
    data() {
        return {
            items: [],
            pageList:[],
        }
    },
    methods: {
        //내글 목록
        async fetchMyList() {
            const {data} = await myCmnt(this.$store.state.id,1);
            this.items = data.myCmntList;
            this.pageList = data.pageList;
            console.log(this.items)
        },
    },
    created() {
        this.fetchMyList();
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