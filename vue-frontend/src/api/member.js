import {api} from '@/api/index'

//전체 회원 조회
function fetchMemberList() {
    return api.get('/api/member/findall')
}

export {fetchMemberList}