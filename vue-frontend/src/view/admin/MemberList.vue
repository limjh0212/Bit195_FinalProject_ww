<template>
  <div class="row mt-4 mb-4">
      <div class="col-md-12">
        <v-simple-table>
            <template v-slot:default>
                <thead>
                    <tr>
                        <th class="text-left">선택</th>
                        <th class="text-left">아이디</th>
                        <th class="text-left">닉네임</th>
                        <th class="text-left">가입일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(item, idx) in members" :key="idx">
                        <td><div><input type="checkbox" v-model="deleteSelect" v-bind:value="item.id"></div></td>
                        <td>{{ item.id }}</td>
                        <td>{{ item.nickname }}</td>
                        <td>{{ $moment(item.regdate).format('YYYY-MM-DD') }}</td>
                    </tr>
                </tbody>
                            <div class="mt-3 mb-3 d-fixed">
                              <button class="btn btn-success" id="fusionexport-btn" @click="deleteId">
                            <span
                                class="spinner-border spinner-border-sm"
                                role="status"
                                aria-hidden="true"
                            ></span>
                                <span class="btn-text">삭제</span>
                              </button>
                            </div>
<!--                <button @click="deleteId">삭제</button>-->
            </template>
        </v-simple-table>
      </div>
  </div>
</template>
<script>
import {deleteId, fetchMemberList} from "@/api/admin";
import LoadingSpinner from "@/components/common/LoadingSpinner";

export default {
    components: {LoadingSpinner},
    data() {
        return {
            members  : [],
            deleteSelect: [],
            isLoading: false,
        }
    },
    methods: {
        async fetchData() {
            this.isLoading = true;
            const {data} = await fetchMemberList();
            this.isLoading = false;
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

</style>