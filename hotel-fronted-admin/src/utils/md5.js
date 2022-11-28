import md5 from 'js-md5'

function strToMd5(str){
    return md5(md5(str).split('').reverse().join(''))
}
export {
    strToMd5
}