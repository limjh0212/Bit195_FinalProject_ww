<template>
    <div>
        <div>
            <form enctype="multipart/form-data" method="post" @submit.prevent="saveimg">
                <input id="File" multiple="multiple" name="files" type="file" @change="onFileSelected"></input>
                <button id="Button" type="submit">저장</button>
            </form>
        </div>
        <div>
            <img ref="uploadImg" :src="this.imgsrc">
        </div>
    </div>
</template>

<script>
import {saveImg} from "@/api/img";

let FileElement = document.querySelector('#File');
export default {
    name   : "PostImgForm",
    data   : () => ({
        postnum: "",
        imgsrc : "",
    }),
    methods: {
        // 이미지 저장만 하는 것
        async saveimg() {
            const formData = new FormData();
            for (let i = 0; i < FileElement.files.length; i++) {
                formData.append("images", FileElement.files[i]);
            }
            const {data} = await saveImg(formData);
        },
        onFileSelected: function (event) {
            FileElement = event.target;
            if (FileElement.files[0]) {
                let img = this.$refs.uploadImg;
                img.src = URL.createObjectURL(FileElement.files[0]);
                this.imgsrc = img.src;
                img.onload = () => {
                    URL.revokeObjectURL(this.src)
                }
            }
        },
    },
};
</script>

<style scoped>

</style>