function saveAuthToCookie(value) {
    document.cookie = `ww_auth=${value}`;
}

function saveUserToCookie(value) {
    document.cookie = `ww_user=${value}`;
}

function getAuthFromCookie() {
    return document.cookie.replace(
        /(?:(?:^|.*;\s*)ww_auth\s*=\s*([^;]*).*$)|^.*$/,
        '$1',
    );
}

function getUserFromCookie() {
    return document.cookie.replace(
        /(?:(?:^|.*;\s*)ww_user\s*=\s*([^;]*).*$)|^.*$/,
        '$1',
    );
}

function deleteCookie(value) {
    document.cookie = `${value}=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
}

export {
    saveAuthToCookie,
    saveUserToCookie,
    getAuthFromCookie,
    getUserFromCookie,
    deleteCookie,
};
