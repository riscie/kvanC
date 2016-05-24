System.register(['angular2/platform/browser', './email/email.component', 'angular2/http'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var browser_1, email_component_1, http_1;
    return {
        setters:[
            function (browser_1_1) {
                browser_1 = browser_1_1;
            },
            function (email_component_1_1) {
                email_component_1 = email_component_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            browser_1.bootstrap(email_component_1.EmailComponent, http_1.HTTP_PROVIDERS);
        }
    }
});
//# sourceMappingURL=boot.js.map