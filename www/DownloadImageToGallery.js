var exec = require('cordova/exec'),
    DownloadImageToGallery = function () { };

DownloadImageToGallery.prototype.download = download;
DownloadImageToGallery.prototype.downloadWithLocalPath = downloadWithLocalPath;

module.exports = new DownloadImageToGallery();

/**
 * Invoke plugin for downloading image from URL
 *
 * @param {String} url
 * @param {Function} successFn
 * @param {Function} failureFn
 */
function download(url, successFn, failureFn) {
    exec(successFn, failureFn, 'DownloadImageToGallery', 'download', [url]);
}

function downloadWithLocalPath(src, onsuccess, onerror) {
    cordova.exec(onsuccess, onerror, "ImgDownloader", "download", [src]);
};

