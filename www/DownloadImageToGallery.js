var exec = require('cordova/exec'),
DownloadImageToGallery = function () {};

DownloadImageToGallery.prototype.download = download;

module.exports = new DownloadImageToGallery();

/**
 * Invoke plugin for downloading image from URL
 *
 * @param {String} url
 * @param {Function} successFn
 * @param {Function} failureFn
 */
function download(url, successFn, failureFn) {
    exec(successFn, failureFn, 'DownloadImageToGallery', 'download', [ url ]);
}