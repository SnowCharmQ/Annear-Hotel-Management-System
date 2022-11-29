export function convertToDate (msec) {
    let datetime = new Date(msec);
    let year = datetime.getUTCFullYear();
    let month = datetime.getUTCMonth() + 1;
    let date = datetime.getUTCDate();
    return year + "-" + month + "-" + date;
}