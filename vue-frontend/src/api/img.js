import {api} from '@/api/index'

//이미지 저장
// const ButtonElement = document.querySelector('#Button');
// const FileElement = document.querySelector('#File');
// export function saveImg(){
//         try{
//             const formData = new FormData();
//             formData.append("boardname", "imgtest");
//             formData.append("uid", this.$store.state.id);
//             formData.append("title","title");
//             formData.append("content", "thisiscontent");
//             for(let i = 0; i < FileElement.files.length; i++){
//                 formData.append("files", FileElement.files[i]);
//             api.post('/api/cmnty/postimg', formData);
//             }
//         }catch (error){
//             console.error(error);
//         }
//         return ButtonElement.addEventListener('click', getData);
// }