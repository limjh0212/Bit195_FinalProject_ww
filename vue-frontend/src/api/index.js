import axios from "axios";


function fetchMemberList() {
    return axios.get('/member/findall')
}

function fetchUser(userId) {
    return axios.get('/member/findbyid/${userId}')
}

export {
    fetchMemberList,
    fetchUser,
}
