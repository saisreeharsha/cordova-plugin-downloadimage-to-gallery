# cordova-plugin-downloadimage-to-gallery
The cordova plugin for downloading image into gallery

Supported platforms
-------------------
* Android

Installation
------------

```
cordova plugin add https://github.com/saisreeharsha/cordova-plugin-downloadimage-to-gallery

or

cordova plugin add cordova-plugin-downloadimage-to-gallery

```

Usage
-----

Downloads an images from URL to gallery Android

```js
window.DownloadImageToGallery.download(
        'http://cordova.apache.org/static/img/cordova_bot.png',
        function success() {
            alert('Image downloaded successfully');
        },
        function failure() {
            alert('Image download failed');
        }
    );
```

License
-------

[Apache License 2.0](/LICENSE)
