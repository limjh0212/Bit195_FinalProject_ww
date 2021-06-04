import {api} from '@/api/index'

function fetchMemberList() {
    return api.get('/api/member/findall')
}

export {fetchMemberList}