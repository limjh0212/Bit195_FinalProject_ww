import axios from "axios";


function fetchMemberList() {
    return axios.get('member/findall')
}

function fetchUser(id) {
    return axios.get('member/findall/${id}')
}

export {
    fetchMemberList,
    fetchUser,
}
