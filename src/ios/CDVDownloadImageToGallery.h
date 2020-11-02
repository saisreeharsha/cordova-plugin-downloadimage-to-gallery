#import <Cordova/CDVPlugin.h>

@interface CDVDownloadImageToGallery : CDVPlugin {}

- (void)download:(CDVInvokedUrlCommand*)command;
- (void)downloadWithLocalPath:(CDVInvokedUrlCommand*)command;

@end
