import {Component, OnInit} from 'angular2/core';
import {Email} from './email.model';
import {EmailService} from './email.service';
import { HTTP_PROVIDERS }    from 'angular2/http';
import 'rxjs/Rx';

@Component({
    selector: 'email',
    templateUrl: './app/email/email-form.tpl.html',
    providers: [EmailService, HTTP_PROVIDERS],
})

export class EmailComponent implements OnInit {
    email : Email;
    emails = [];
    errorMessage = '';

    constructor(private emailService: EmailService){
        this.email = new Email;
    }

   ngOnInit() {
       this.getEmails();
   }

    public resetForm(){
        this.email = {}
    }

    public sendEmail() {
        this.emailService.sendEmail(this.email);
    }

    public getEmails() {
        this.emailService.getEmails()
            .subscribe(
            emails => this.handleResponse(emails),
            error => this.handleError(error));
    }

    private handleResponse(emails) {
         this.emails = emails;
    }

    private handleError(error){
          console.log(error);
    }
}
