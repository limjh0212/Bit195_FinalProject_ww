import {store} from '@/store/index';

export function setInterceptors(instance) {
    //Request 인터셉터
    instance.interceptors.request.use(
        function (config) {
            //Header에 인증 토큰 작성
            config.headers.Authorization = store.state.token;
            return config;
        },
        function (error) {
            return Promise.reject(error);
        },
    );
    return instance;
}
