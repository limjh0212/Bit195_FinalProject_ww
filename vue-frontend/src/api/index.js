import axios from "axios";
import {setInterceptors} from "@/api/common/interceptors";

function createInstance() {
    return axios.create({});
}

function createInstanceWithAuth() {
    const instance = axios.create({});
    return setInterceptors(instance);
}

export const instance = createInstance();
export const api = createInstanceWithAuth();
