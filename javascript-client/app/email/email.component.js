System.register(['angular2/core', './email.model', './email.service', 'angular2/http', 'rxjs/Rx'], function(exports_1, context_1) {
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
    var core_1, email_model_1, email_service_1, http_1;
    var EmailComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (email_model_1_1) {
                email_model_1 = email_model_1_1;
            },
            function (email_service_1_1) {
                email_service_1 = email_service_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (_1) {}],
        execute: function() {
            EmailComponent = (function () {
                function EmailComponent(emailService) {
                    this.emailService = emailService;
                    this.emails = [];
                    this.errorMessage = '';
                    this.email = new email_model_1.Email;
                }
                EmailComponent.prototype.ngOnInit = function () {
                    this.getEmails();
                };
                EmailComponent.prototype.resetForm = function () {
                    this.email = {};
                };
                EmailComponent.prototype.sendEmail = function () {
                    this.emailService.sendEmail(this.email);
                };
                EmailComponent.prototype.getEmails = function () {
                    var _this = this;
                    this.emailService.getEmails()
                        .subscribe(function (emails) { return _this.handleResponse(emails); }, function (error) { return _this.handleError(error); });
                };
                EmailComponent.prototype.handleResponse = function (emails) {
                    this.emails = emails;
                };
                EmailComponent.prototype.handleError = function (error) {
                    console.log(error);
                };
                EmailComponent = __decorate([
                    core_1.Component({
                        selector: 'email',
                        templateUrl: './app/email/email-form.tpl.html',
                        providers: [email_service_1.EmailService, http_1.HTTP_PROVIDERS],
                    }), 
                    __metadata('design:paramtypes', [email_service_1.EmailService])
                ], EmailComponent);
                return EmailComponent;
            }());
            exports_1("EmailComponent", EmailComponent);
        }
    }
});
//# sourceMappingURL=email.component.js.map