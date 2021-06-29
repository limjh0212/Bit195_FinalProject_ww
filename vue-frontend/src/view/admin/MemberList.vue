<template>
    <div class="row mt-4 mb-4">
        <div class="col-md-12">
            <div class="table-box">
                <div>
                    <v-simple-table>
                        <template>
                            <thead>
                                <tr>

                                    <th style="text-align: center">아이디</th>
                                    <th style="text-align: center">닉네임</th>
                                    <th style="text-align: center">가입일</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(item, idx) in members" :key="idx">
                                    <!--<td style="width: 50px; text-align: center"><input type="checkbox" v-model="deleteSelect"-->
                                    <!--                               v-bind:value="item.id" /></td>-->
                                    <td style="width: 100px; text-align: center">{{ item.id }}</td>
                                    <td style="width: 100px; text-align: center">{{ item.nickname }}</td>
                                    <td style="width: 120px; text-align: center">
                                        {{ $moment(item.regdate).format('YYYY-MM-DD') }}
                                    </td>
                                </tr>
                            </tbody>

                            <!--<tfoot>-->
                            <!--    <td colspan="4">-->
                            <!--        <v-btn elevation="1" rounded small @click="deleteId">삭제-->
                            <!--            <i class="material-icons" style="font-size: 20px">clear</i>-->
                            <!--        </v-btn>-->
                            <!--    </td>-->
                            <!--</tfoot>-->
                        </template>
                    </v-simple-table>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import {deleteId, fetchMemberList} from "@/api/admin";

export default {
    data() {
        return {
            members     : [],
            deleteSelect: [],
        }
    },
    methods: {
        async fetchData() {
            const {data} = await fetchMemberList();
            this.members = data;
        },

        async deleteId() {
            for (let i = 0; i < this.deleteSelect.length; i++) {
                deleteId(this.deleteSelect[i]);
                await this.fetchData();
            }
        }
    },
    created() {
        this.fetchData();
    }
}
</script>

<style scoped>
@import '../../css/common/admin.css';


input {

}
</style>