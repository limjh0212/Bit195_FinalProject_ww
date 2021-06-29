import {store} from '@/store';

export function setInterceptors(instance) {
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
