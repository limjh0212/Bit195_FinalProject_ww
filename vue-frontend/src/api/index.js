import axios from "axios";


function fetchMemberList() {
    return axios.get('/member/findall')
}

function fetchBoardList() {
    return axios.get('/boards')
}

function fetchUser(userId) {
    return axios.get('/member/findbyid/${userId}')
}

// 게시글 저장 API
function createPost(postData) {
    return axios.post('/boards/post', postData);
}

// 회원가입 API
function signupMember(Data) {
    return axios.post('/member/save', Data);
}


export {
    fetchMemberList,
    fetchUser,
    fetchBoardList,
    createPost,
    signupMember
}
