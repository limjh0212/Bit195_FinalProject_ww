import axios from "axios";
import {setInterceptors} from "@/api/common/interceptors";

//토큰 없이 Instance 생성
function createInstance() {
    return axios.create({});
}

//토큰 포함 Instance 생성
function createInstanceWithAuth() {
    const instance = axios.create({});
    return setInterceptors(instance);
}

export const instance = createInstance();
export const api = createInstanceWithAuth();
