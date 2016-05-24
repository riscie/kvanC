System.register(['angular2/core', 'angular2/http', 'rxjs/add/operator/map'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1;
    var EmailService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (_1) {}],
        execute: function() {
            EmailService = (function () {
                function EmailService(http) {
                    this.http = http;
                    this.emailUrl = 'http://localhost:8080/emails'; // URL to web api
                }
                // private emailUrl = 'http://86.119.38.74/emails';  // URL to web api
                EmailService.prototype.getEmails = function () {
                    return this.http.get(this.emailUrl)
                        .map(this.extractData)
                        .catch(this.handleError);
                };
                EmailService.prototype.sendEmail = function (email) {
                    var body = JSON.stringify(email);
                    console.log(body);
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    this.http.post(this.emailUrl, body, { headers: headers })
                        .subscribe(function (res) {
                        console.log(res);
                    });
                };
                EmailService.prototype.extractData = function (res) {
                    if (res.status < 200 || res.status >= 300) {
                        throw new Error('Bad response status: ' + res.status);
                    }
                    var body = res.json();
                    return body || {};
                };
                EmailService.prototype.handleError = function (error) {
                    // In a real world app, we might send the error to remote logging infrastructure
                    var errMsg = error.message || 'Server error';
                    console.error(errMsg); // log to console instead
                    return Observable.throw(errMsg);
                };
                EmailService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], EmailService);
                return EmailService;
            }());
            exports_1("EmailService", EmailService);
        }
    }
});
//# sourceMappingURL=email.service.js.map