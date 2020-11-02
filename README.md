# cordova-plugin-downloadimage-to-gallery

The cordova plugin for downloading image into android and iphone photos gallery.


## Supported platforms

- Android
- iOS

## Installation

```
cordova plugin add https://github.com/saisreeharsha/cordova-plugin-downloadimage-to-gallery

or

cordova plugin add cordova-plugin-downloadimage-to-gallery

```

## Methods:

-Android & IOS
```
    download(url,onsuccess,onerror)  //'url' can be any online resource url or dataURI
```

- IOS Only

```
    downloadWithLocalPath(relativeUrl,onsuccess,onerror) //'relativeUrl' relative to Library/NoCloud  e.g. "/myimage/logo.png"
```

## Usage

## Android & IOS
1. Download from a url when device is ready

```js
document.addEventListener("deviceready", onDeviceReady);

onDeviceReady = function () {
  window.DownloadImageToGallery.download(
    "http://cordova.apache.org/static/img/cordova_bot.png",
    function success() {
      alert("Image downloaded successfully");
    },
    function failure() {
      alert("Image download failed");
    }
  );
};
```
## (IOS only)
2.download a local image file:
*If you have a file inside your app's dataDirectory(/Library/NoCloud/) and the path is /Library/NoCloud/myImages/logo.png, then you can move it to image gallery using the following code snippit

```js
  document.addEventListener("deviceready",onDeviceReady);

  onDeviceReady = function(){
      window.DownloadImageToGallery.downloadWithLocalPath("/myImages/logo.png",function(){
          alert("success");
      },function(){
          alert("error");
      });
  }
```

## License

[Apache License 2.0](/LICENSE)

